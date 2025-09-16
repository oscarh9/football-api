package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.mapper.ClubMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.service.ClubService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    public ClubResponseDTO createClub(ClubRequestDTO requestDTO) {
        Club club = clubMapper.toEntity(requestDTO);
        Club saved = clubRepository.save(club);
        return clubMapper.toDTO(saved);
    }

    public List<ClubResponseDTO> getAllClubs() {
        return clubRepository.findAll()
                .stream()
                .map(clubMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ClubResponseDTO getClubById(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found."));
        return clubMapper.toDTO(club);
    }

    public ClubResponseDTO updateClub(Long id, ClubRequestDTO requestDTO) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found."));

        club.setName(requestDTO.getName());
        club.setEstablishedDate(requestDTO.getEstablishedDate());
        club.setStadiumName(requestDTO.getStadiumName());
        club.setCity(requestDTO.getCity());
        club.setLeague(requestDTO.getLeague());
        club.setTitlesWon(requestDTO.getTitlesWon());

        Club updated = clubRepository.save(club);
        return clubMapper.toDTO(updated);
    }

    public void deleteClub(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Club not found."));
        clubRepository.delete(club);
    }

}
