package com.oscar.football_api.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ManagerResponseDTO {

  private Long id;
  private String name;
  private String nationality;
  private LocalDate dateOfBirth;
  private int titlesWon;
  private Long clubId;
}
