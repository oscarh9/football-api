package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.mapper.ClubMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.service.ClubService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    public ClubResponseDTO createClub(ClubRequestDTO requestDTO) {
        Club club = clubMapper.toEntity(requestDTO);
        Club saved = clubRepository.save(club);
        return clubMapper.toDTO(saved);
    }

}
