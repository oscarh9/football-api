package com.oscar.football_api.dto.response;

import com.oscar.football_api.entity.enums.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponseDTO {

    private Long id;
    private String name;
    private Position position;
    private int jerseyNumber;
    private String nationality;
}
