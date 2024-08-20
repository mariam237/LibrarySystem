package com.maids.maidstask.borrowings.controllers;

import com.maids.maidstask.CustomResponseMessage;
import com.maids.maidstask.borrowings.Dtos.BorrowingResponseDto;
import com.maids.maidstask.borrowings.Dtos.ReturningResponseDto;
import com.maids.maidstask.borrowings.services.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BorrowingController {
    private BorrowingService borrowingService;
    @Autowired
    public BorrowingController(BorrowingService borrowingService) {
        this.borrowingService = borrowingService;
    }


    @PostMapping("/borrow/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public BorrowingResponseDto borrowingBook(@PathVariable int bookId, @PathVariable int patronId){
        return this.borrowingService.borrowBook(bookId, patronId);
    }

    @PutMapping("/return/{bookId}/patron/{patronId}")
    @ResponseStatus(HttpStatus.OK)
    public ReturningResponseDto returningBook(@PathVariable int bookId, @PathVariable int patronId){
        return this.borrowingService.returnBook(bookId, patronId);
    }
}
