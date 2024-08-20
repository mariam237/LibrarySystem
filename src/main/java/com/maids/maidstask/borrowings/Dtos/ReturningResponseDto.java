package com.maids.maidstask.borrowings.Dtos;

import java.time.LocalDate;

public record ReturningResponseDto(
        String message,
        LocalDate borrowDate,

        LocalDate expectedReturnDate,
        LocalDate returnDate
) {
}
