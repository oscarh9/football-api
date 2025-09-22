package com.oscar.football_api.service;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.enums.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClubService {

    ClubResponseDTO createClub(ClubRequestDTO requestDTO);
    Page<ClubResponseDTO> getAllClubs(Pageable pageable);
    ClubResponseDTO getClubById(Long id);
    ClubResponseDTO updateClub(Long id, ClubRequestDTO requestDTO);
    void deleteClub(Long id);
    Page<ClubResponseDTO> searchClubs(String name, String city, String stadiumName, League league, Pageable pageable);
}
