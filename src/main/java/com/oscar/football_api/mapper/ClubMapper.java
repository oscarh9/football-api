package com.oscar.football_api.mapper;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClubMapper {

    Club toEntity(ClubRequestDTO dto);
    ClubResponseDTO toDTO(Club club);
}
