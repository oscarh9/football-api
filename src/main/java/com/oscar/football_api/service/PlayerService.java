package com.oscar.football_api.service;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;

import java.util.List;

public interface PlayerService {

    PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO);
    List<PlayerResponseDTO> getAllPlayers();
    PlayerResponseDTO getPlayerById(Long id);
}
