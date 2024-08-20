package com.maids.maidstask.books.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Entity
@Setter
@Getter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    private String ISBN;
    private String title;

    @Column(name = "publication_year")
    private String publicationYear;

    private String author;

    private Integer quantity;


    public Book(){}
    public Book(String ISBN, String title, String author,String publicationYear,Integer quantity){
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.quantity = quantity;
    }
}
