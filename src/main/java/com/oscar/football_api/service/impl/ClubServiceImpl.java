package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.enums.League;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.ClubMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.specifications.ClubSpecifications;
import com.oscar.football_api.service.ClubService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;
    private final ClubMapper clubMapper;

    @Override
    public ClubResponseDTO createClub(ClubRequestDTO requestDTO) {
        Club club = clubMapper.toEntity(requestDTO);
        Club saved = clubRepository.save(club);
        return clubMapper.toDTO(saved);
    }

    @Override
    public Page<ClubResponseDTO> getAllClubs(Pageable pageable) {
        return clubRepository.findAll(pageable)
                .map(clubMapper::toDTO);
    }

    @Override
    public ClubResponseDTO getClubById(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + id + " not found"));
        return clubMapper.toDTO(club);
    }

    @Override
    public ClubResponseDTO updateClub(Long id, ClubRequestDTO requestDTO) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + id + " not found"));

        club.setName(requestDTO.getName());
        club.setEstablishedDate(requestDTO.getEstablishedDate());
        club.setStadiumName(requestDTO.getStadiumName());
        club.setCity(requestDTO.getCity());
        club.setLeague(requestDTO.getLeague());
        club.setTitlesWon(requestDTO.getTitlesWon());

        Club updated = clubRepository.save(club);
        return clubMapper.toDTO(updated);
    }

    @Override
    public void deleteClub(Long id) {
        Club club = clubRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + id + " not found"));
        clubRepository.delete(club);
    }

    @Override
    public Page<ClubResponseDTO> searchClubs(String name, String city, String stadiumName, League league, Pageable pageable) {
        Specification<Club> spec = Specification
                .where(ClubSpecifications.hasName(name))
                .and(ClubSpecifications.hasCity(city))
                .and(ClubSpecifications.hasStadiumName(stadiumName))
                .and(ClubSpecifications.hasLeague(league));

        return clubRepository.findAll(spec, pageable).map(clubMapper::toDTO);
    }
}
