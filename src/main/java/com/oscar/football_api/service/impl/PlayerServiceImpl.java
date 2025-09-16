package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.exception.ConflictException;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.PlayerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.PlayerRepository;
import com.oscar.football_api.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final PlayerMapper playerMapper;

    @Override
    public PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO) {
        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + requestDTO.getClubId() + " not found"));

        playerRepository.findByClubIdAndJerseyNumber(club.getId(), requestDTO.getJerseyNumber())
                .ifPresent(p -> {
                    throw new ConflictException("Jersey number " + requestDTO.getJerseyNumber() + " is already taken in this club");
                });

        Player player = playerMapper.toEntity(requestDTO);
        player.setClub(club);

        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }

    @Override
    public List<PlayerResponseDTO> getAllPlayers() {
        return playerRepository.findAll()
                .stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PlayerResponseDTO getPlayerById(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id " + id + " not found"));
        return playerMapper.toDTO(player);
    }

    @Override
    public PlayerResponseDTO updatePlayer(Long id, PlayerRequestDTO requestDTO) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id " + id + " not found"));

        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + requestDTO.getClubId() + " not found"));

        playerRepository.findByClubIdAndJerseyNumber(club.getId(), requestDTO.getJerseyNumber())
                .ifPresent(existing -> {
                    if (!existing.getId().equals(id)) {
                        throw new ConflictException("Jersey number already taken in this club");
                    }
                });


        player.setName(requestDTO.getName());
        player.setPosition(requestDTO.getPosition());
        player.setJerseyNumber(requestDTO.getJerseyNumber());
        player.setDateOfBirth(requestDTO.getDateOfBirth());
        player.setNationality(requestDTO.getNationality());
        player.setClub(club);

        return playerMapper.toDTO(playerRepository.save(player));
    }

    @Override
    public void deletePlayer(Long id) {
        Player player = playerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Player with id " + id + " not found"));
        playerRepository.delete(player);
    }
}
