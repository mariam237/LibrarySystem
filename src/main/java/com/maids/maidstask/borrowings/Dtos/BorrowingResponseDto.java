package com.maids.maidstask.borrowings.Dtos;

import java.time.LocalDate;

public record BorrowingResponseDto(
        String message,
        LocalDate borrowDate,

        LocalDate expectedReturnDate
) {
}
