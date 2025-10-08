package com.oscar.football_api.mapper;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ManagerMapper {

  @Mapping(source = "clubId", target = "club.id")
  Manager toEntity(ManagerRequestDTO dto);

  @Mapping(source = "club.id", target = "clubId")
  ManagerResponseDTO toDTO(Manager manager);
}
