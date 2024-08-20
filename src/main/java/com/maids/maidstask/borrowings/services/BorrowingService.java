package com.maids.maidstask.borrowings.services;
import com.maids.maidstask.books.models.Book;
import com.maids.maidstask.books.services.BooksService;
import com.maids.maidstask.borrowings.Dtos.BorrowingResponseDto;
import com.maids.maidstask.borrowings.Dtos.ReturningResponseDto;
import com.maids.maidstask.borrowings.models.BorrowingRecord;
import com.maids.maidstask.borrowings.repositories.BorrowingRecordRepository;
import com.maids.maidstask.exceptions.BadRequestException;
import com.maids.maidstask.exceptions.NotFoundException;
import com.maids.maidstask.patrons.models.Patron;
import com.maids.maidstask.patrons.services.PatronsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@CacheConfig(cacheNames = {"borrowings"})
public class BorrowingService {
    private final BorrowingRecordRepository borrowingRepo;
    private final BooksService bookService;
    private final PatronsService patronService;
    @Autowired
    public BorrowingService(BorrowingRecordRepository borrowingRepo, BooksService bookService, PatronsService patronService) {
        this.borrowingRepo = borrowingRepo;
        this.bookService = bookService;
        this.patronService = patronService;
    }

    @Transactional
    @Cacheable
    public BorrowingResponseDto borrowBook(int bookId, int patronId) throws NotFoundException, BadRequestException {
        //Check if patron exists
        Patron patron = this.patronService.getFullPatronById(patronId);
        if(patron == null){
            throw new NotFoundException("Patron does not exist");
        }

        // Check if book exists
        Book book = this.bookService.getFullBookById(bookId);
        if(book == null){
            throw new NotFoundException("Book does not exist");
        }
        if(book.getQuantity() == 0){
            throw new NotFoundException("Book is out of stock");
        }

        // Create borrowing record
        BorrowingRecord record = new BorrowingRecord();
        record.setBook(book);
        record.setPatron(patron);
        record.setBorrowDate(LocalDate.now());
        record.setExpectedReturnDate(LocalDate.now().plusDays(30));
        this.borrowingRepo.save(record);

        // Decrease book count by 1
        this.bookService.decreaseBookCountByOne(bookId);

        return new BorrowingResponseDto("Book borrowed successfully", record.getBorrowDate(), record.getExpectedReturnDate());
    }


    @Transactional
    @Cacheable
    public ReturningResponseDto returnBook(int bookId, int patronId) throws NotFoundException, BadRequestException {
        BorrowingRecord record = this.borrowingRepo.findFirstByPatronIdAndBookIdAndReturnDateIsNull(bookId, patronId)
                .orElseThrow(() -> new NotFoundException("No such borrowing record exists"));
        this.bookService.increaseBookCountByOne(bookId);
        record.setReturnDate(LocalDate.now());
        this.borrowingRepo.save(record);
        return new ReturningResponseDto("Book returned successfully",record.getBorrowDate(),record.getExpectedReturnDate(), record.getReturnDate());
    }
}
