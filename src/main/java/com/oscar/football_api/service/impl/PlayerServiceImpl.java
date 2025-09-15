package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.mapper.PlayerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.PlayerRepository;
import com.oscar.football_api.service.PlayerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final PlayerMapper playerMapper;

    public PlayerResponseDTO createPlayer(PlayerRequestDTO requestDTO) {
        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found."));

        playerRepository.findByClubIdAndJerseyNumber(club.getId(), requestDTO.getJerseyNumber())
                .ifPresent(p -> {
                    throw new RuntimeException("Jersey number " + requestDTO.getJerseyNumber() + " is already taken in this club");
                });

        Player player = playerMapper.toEntity(requestDTO);
        player.setClub(club);

        Player saved = playerRepository.save(player);
        return playerMapper.toDTO(saved);
    }
}
