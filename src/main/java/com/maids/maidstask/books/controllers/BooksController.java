package com.maids.maidstask.books.controllers;

import com.maids.maidstask.CustomResponseMessage;
import com.maids.maidstask.books.Dtos.BookResponseDto;
import com.maids.maidstask.books.Dtos.CreateBookRequestDto;
import com.maids.maidstask.books.Dtos.UpdateBookRequestDto;
import com.maids.maidstask.books.services.BooksService;
import com.maids.maidstask.exceptions.BadRequestException;
import com.maids.maidstask.exceptions.NotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {
    private final BooksService booksService;

    @Autowired
    public BooksController(BooksService booksService){
        this.booksService = booksService;
    }


    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<BookResponseDto> getAllBooks() throws NotFoundException {
       return this.booksService.getAllBooks();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookResponseDto addNewBook(@Valid @RequestBody CreateBookRequestDto bookData) throws BadRequestException {
        return this.booksService.addNewBook(bookData);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto getBookById(@PathVariable Integer id) throws NotFoundException {
        return this.booksService.getBookById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookResponseDto updateBookById(@PathVariable Integer id, @RequestBody UpdateBookRequestDto newData ) throws NotFoundException {
        return this.booksService.updateBookById(id, newData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomResponseMessage deleteBookById(@PathVariable Integer id) throws NotFoundException {
        return this.booksService.deleteBookById(id);
    }
}
