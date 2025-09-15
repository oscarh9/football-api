package com.oscar.football_api.service;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;

public interface ClubService {

    ClubResponseDTO createClub(ClubRequestDTO requestDTO);
}
