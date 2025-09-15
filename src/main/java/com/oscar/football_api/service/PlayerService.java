package com.oscar.football_api.service;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;

public interface PlayerService {

    PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO);
}
