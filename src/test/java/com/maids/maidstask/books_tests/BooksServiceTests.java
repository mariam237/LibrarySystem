package com.maids.maidstask.books_tests;

import com.maids.maidstask.Utils;
import com.maids.maidstask.books.Dtos.BookResponseDto;
import com.maids.maidstask.books.Dtos.CreateBookRequestDto;
import com.maids.maidstask.books.models.Book;
import com.maids.maidstask.books.repositories.BookRepository;
import com.maids.maidstask.books.services.BookMapper;
import com.maids.maidstask.books.services.BooksService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class BooksServiceTests {

    private final BooksService booksService;

    @Autowired
    public BooksServiceTests(BooksService booksService){
        this.booksService = booksService;
    }

    @Test
    public void addBook_shouldReturnAllBooks() throws BadRequestException {
        // Arrange
        CreateBookRequestDto book = new CreateBookRequestDto("Book1", "Author1", "12345", "2020",3);
        BookResponseDto addedBook = this.booksService.addNewBook(book);

        // Act
        List<BookResponseDto> books = this.booksService.getAllBooks();
        // Assert
        if(books.isEmpty()){
            System.out.println("No books found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No books found");
        }

        Assertions.assertNotEquals(books.size(), 0);
    }
}
