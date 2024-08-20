package com.maids.maidstask.patrons.Dtos;

import lombok.Builder;

@Builder
public record PatronResponseDto(
        String username,
        String email,
        String address,
        String phone_number

) {
    public void setId(int i) {
    }
}
