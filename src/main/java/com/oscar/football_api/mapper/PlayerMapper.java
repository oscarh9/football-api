package com.oscar.football_api.mapper;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

  @Mapping(source = "clubId", target = "club.id")
  Player toEntity(PlayerRequestDTO dto);

  @Mapping(source = "club.id", target = "clubId")
  PlayerResponseDTO toDTO(Player player);
}
