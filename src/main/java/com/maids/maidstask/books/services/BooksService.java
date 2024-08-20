package com.maids.maidstask.books.services;
import com.maids.maidstask.CustomResponseMessage;
import com.maids.maidstask.Utils;
import com.maids.maidstask.books.Dtos.CreateBookRequestDto;
import com.maids.maidstask.books.Dtos.BookResponseDto;
import com.maids.maidstask.books.Dtos.UpdateBookRequestDto;
import com.maids.maidstask.books.models.Book;
import com.maids.maidstask.books.repositories.BookRepository;
import com.maids.maidstask.exceptions.BadRequestException;
import com.maids.maidstask.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;


@Service
@CacheConfig(cacheNames = {"books"})
public class BooksService {
    private final BookRepository bookRepo;
    private final BookMapper bookMapper;
    private final Utils utils;
    @Autowired
    public BooksService(BookRepository bookRepo, BookMapper bookMapper, Utils utils) {
        this.bookRepo = bookRepo;
        this.bookMapper = bookMapper;
        this.utils = utils;
    }


    @Cacheable
    public List<BookResponseDto> getAllBooks() throws NotFoundException {
        List<Book> books = this.bookRepo.findAll();
        if(books.isEmpty()){
            throw new NotFoundException("No books found");
        }
       return books.stream().map(this.bookMapper::toBookResponseDto).toList();
    }

    @Cacheable
    @Transactional
    public BookResponseDto addNewBook(CreateBookRequestDto bookData) throws BadRequestException {
        // Check if book already exists
        if(this.bookRepo.existsByTitle(bookData.title())){
            throw new BadRequestException("Book already exists");
        }

        //Create new book
        Book book = this.bookMapper.toBook(bookData);
        this.bookRepo.save(book);
        return  this.bookMapper.toBookResponseDto(book);
    }

    @Cacheable
    public BookResponseDto getBookById(Integer id) throws NotFoundException {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        return this.bookMapper.toBookResponseDto(book);
    }

    @Transactional
    @Cacheable
    public BookResponseDto updateBookById(Integer id, UpdateBookRequestDto newData) throws NotFoundException {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));

        //assign new data to book
        this.utils.assignObjects(book, newData);

        this.bookRepo.save(book);
        return this.bookMapper.toBookResponseDto(book);
    }

    @Transactional
    @Cacheable
    public CustomResponseMessage deleteBookById(Integer id) throws ResponseStatusException {
        Book book = this.bookRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found"));
        this.bookRepo.delete(book);
        return new CustomResponseMessage(HttpStatus.OK,"Book deleted successfully");
    }

    @Cacheable
    public Book getFullBookById(Integer id) throws NotFoundException {
        return this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
    }

    @Cacheable
    public void increaseBookCountByOne(Integer id) throws NotFoundException {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        book.setQuantity(book.getQuantity()+1);
        this.bookRepo.save(book);
    }
    @Cacheable
    public void decreaseBookCountByOne(Integer id) throws NotFoundException {
        Book book = this.bookRepo.findById(id).orElseThrow(() -> new NotFoundException("Book not found"));
        book.setQuantity(book.getQuantity()-1);
        this.bookRepo.save(book);
    }
}
