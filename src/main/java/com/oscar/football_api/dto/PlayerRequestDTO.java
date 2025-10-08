package com.oscar.football_api.dto;

import com.oscar.football_api.entity.enums.Position;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerRequestDTO {

  @NotBlank(message = "Name is required")
  @Size(max = 50, message = "Name must be at most 50 characters")
  private String name;

  @NotNull(message = "Position is required")
  private Position position;

  @Min(value = 1, message = "Jersey number must be between 1 and 99")
  @Max(value = 99, message = "Jersey number must be between 1 and 99")
  private int jerseyNumber;

  @Past(message = "Date of birth must be in the past")
  private LocalDate dateOfBirth;

  @NotBlank(message = "Nationality is required")
  @Size(max = 50, message = "Nationality must be at most 50 characters")
  private String nationality;

  @NotNull(message = "Club ID is required")
  private Long clubId;
}
