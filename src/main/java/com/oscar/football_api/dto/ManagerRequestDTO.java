package com.oscar.football_api.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerRequestDTO {

    @NotBlank(message = "Manager name is required")
    @Size(max = 50, message = "Manager name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Nationality is required")
    @Size(max = 50, message = "Nationality must not exceed 50 characters")
    private String nationality;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dateOfBirth;

    @Min(value = 0, message = "Titles won must not be negative")
    private int titlesWon;

    @NotNull(message = "Club id is required")
    private Long clubId;
}
