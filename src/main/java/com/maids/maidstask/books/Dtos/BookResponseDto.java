package com.maids.maidstask.books.Dtos;

import java.time.LocalDate;
import java.util.Date;

public record BookResponseDto(
        String title,
        String author,
        String ISBN,
        String publication_year,
        Integer quantity
) {
}
