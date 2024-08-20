package com.maids.maidstask.patrons.services;

import com.maids.maidstask.patrons.Dtos.CreatePatronRequestDTO;
import com.maids.maidstask.patrons.Dtos.PatronResponseDto;
import com.maids.maidstask.patrons.Dtos.UpdatePatronResponseDto;
import com.maids.maidstask.patrons.models.Patron;
import org.springframework.stereotype.Service;

@Service
public class PatronMapper {

    Patron toPatron(CreatePatronRequestDTO dto) {
        return new Patron(dto.username(), dto.email(), dto.password(), dto.address(), dto.phoneNumber());
    }

    PatronResponseDto toPatronResponseDto(Patron patron) {
        return new PatronResponseDto(patron.getUsername(), patron.getEmail(), patron.getAddress(), patron.getPhoneNumber());
    }

    UpdatePatronResponseDto toUpdatedPatronResponseDto(Patron patron) {
        return UpdatePatronResponseDto.builder()
                .username(patron.getUsername())
                .phone_number(patron.getPhoneNumber())
                .address(patron.getAddress())
                .email(patron.getEmail())
                .build();
   }
}
