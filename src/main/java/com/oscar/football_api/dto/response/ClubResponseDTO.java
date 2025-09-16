package com.oscar.football_api.dto.response;

import com.oscar.football_api.entity.enums.League;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubResponseDTO {

    private Long id;
    private String name;
    private LocalDate establishedDate;
    private String stadiumName;
    private String city;
    private League league;
    private int titlesWon;

    private List<PlayerResponseDTO> players;
    private ManagerResponseDTO manager;
}
