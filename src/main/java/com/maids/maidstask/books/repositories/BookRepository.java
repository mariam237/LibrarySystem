package com.maids.maidstask.books.repositories;

import com.maids.maidstask.books.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Integer> {
    boolean existsByTitle(String title);

}
