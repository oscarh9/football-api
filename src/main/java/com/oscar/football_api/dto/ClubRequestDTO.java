package com.oscar.football_api.dto;

import com.oscar.football_api.entity.enums.League;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubRequestDTO {

  @NotBlank(message = "Club name is required")
  @Size(max = 50, message = "Club name must not exceed 50 characters")
  private String name;

  @PastOrPresent(message = "Established date must be in the past or present")
  private LocalDate establishedDate;

  @NotBlank(message = "Stadium name is required")
  @Size(max = 50, message = "Stadium name must not exceed 50 characters")
  private String stadiumName;

  @NotBlank(message = "City is required")
  @Size(max = 50, message = "City name must not exceed 50 characters")
  private String city;

  @NotNull(message = "League is required")
  private League league;

  @Min(value = 0, message = "Titles won must not be negative")
  private int titlesWon;
}
