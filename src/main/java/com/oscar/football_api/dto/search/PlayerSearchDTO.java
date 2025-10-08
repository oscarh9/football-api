package com.oscar.football_api.dto.search;

import com.oscar.football_api.entity.enums.Position;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerSearchDTO {

  private Position position;
  private String name;
  private String nationality;
  private String sortBy = "name";
  private String sortDir = "asc";
  private int page = 0;
  private int size = 10;
}
