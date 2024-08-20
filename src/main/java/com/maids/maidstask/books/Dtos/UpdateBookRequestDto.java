package com.maids.maidstask.books.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.PrePersist;

public record UpdateBookRequestDto(
        String title,

        String author,

        String ISBN,

        @JsonProperty("publication_year")
        String publicationYear,

        Integer quantity

) {
}
