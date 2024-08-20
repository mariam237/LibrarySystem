package com.maids.maidstask.patrons.Dtos;

import lombok.Builder;

@Builder
public record UpdatePatronResponseDto(
        String username,
        String email,
        String address,
        String phone_number,
        String token
) {
}
