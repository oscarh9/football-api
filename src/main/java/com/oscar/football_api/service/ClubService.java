package com.oscar.football_api.service;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;

import java.util.List;

public interface ClubService {

    ClubResponseDTO createClub(ClubRequestDTO requestDTO);
    List<ClubResponseDTO> getAllClubs();
    ClubResponseDTO getClubById(Long id);
    ClubResponseDTO updateClub(Long id, ClubRequestDTO requestDTO);
    void deleteClub(Long id);
}
