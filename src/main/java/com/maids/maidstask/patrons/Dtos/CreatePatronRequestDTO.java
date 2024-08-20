package com.maids.maidstask.patrons.Dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record CreatePatronRequestDTO(
        @NotBlank(message = "Name is required")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email address")
        String email,

        @NotBlank(message = "Name is required")
//        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$",
//                message = "Password must be at least 8 characters long, containing at least one uppercase letter, one lowercase letter, one digit, and one special character")
        String password,

        @NotBlank(message = "Name is required")
        @Size(max = 100, message = "Address cannot exceed 100 characters")
        String address,

        @JsonProperty("phone_number")
        @NotBlank(message = "Name is required")
        @Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Invalid phone number format")
        String phoneNumber
) {
}
