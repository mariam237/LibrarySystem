package com.maids.maidstask.books.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequestDto(

    @NotBlank(message = "Title cannot be empty")
    String title,

    @NotBlank(message = "Author name cannot be empty")
    String author,

    @NotBlank(message = "ISBN cannot be empty")
    String ISBN,

    @JsonProperty("publication_year")
    @NotBlank(message = "Publication year cannot be empty")
//    @PastOrPresent(message = "Publication year cannot be in the future")
    String publicationYear,

    @NotNull(message = "Quantity cannot be empty")
    Integer quantity
) {
}
