package com.maids.maidstask.books.services;

import com.maids.maidstask.books.Dtos.CreateBookRequestDto;
import com.maids.maidstask.books.Dtos.BookResponseDto;
import com.maids.maidstask.books.models.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {
    public Book toBook(CreateBookRequestDto bookData){
        return new Book(bookData.ISBN(), bookData.title(), bookData.author(), bookData.publicationYear(), bookData.quantity());
    }

    public BookResponseDto toBookResponseDto(Book book){
        return new BookResponseDto(book.getTitle(), book.getAuthor(), book.getISBN().toUpperCase(), book.getPublicationYear(), book.getQuantity());
    }
}
