package com.maids.maidstask.borrowings.models;

import com.maids.maidstask.books.models.Book;
import com.maids.maidstask.patrons.models.Patron;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_records")
@Setter
@Getter
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "patron_id")
    private Patron patron;

    private LocalDate borrowDate;
    private LocalDate expectedReturnDate;
    private LocalDate returnDate;


}
