package com.oscar.football_api.service;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.enums.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PlayerService {

    PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO);
    Page<PlayerResponseDTO> getAllPlayers(Pageable pageable);
    PlayerResponseDTO getPlayerById(Long id);
    PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO requestDTO);
    void deletePlayer(Long id);
    Page<PlayerResponseDTO> searchPlayers(Position position, String name, String nationality, Pageable pageable);
}
