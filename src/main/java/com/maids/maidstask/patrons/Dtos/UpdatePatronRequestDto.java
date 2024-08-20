package com.maids.maidstask.patrons.Dtos;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
public record UpdatePatronRequestDto(
        String username,
        String email,
        String password,
        String address,
        String phoneNumber
) {
}
