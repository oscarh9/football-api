package com.oscar.football_api.dto.search;

import com.oscar.football_api.entity.enums.League;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClubSearchDTO {

  private String name;
  private String city;
  private String stadiumName;
  private League league;
  private String sortBy = "name";
  private String sortDir = "asc";
  private int page = 0;
  private int size = 10;
}
