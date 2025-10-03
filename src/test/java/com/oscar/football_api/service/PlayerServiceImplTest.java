package com.oscar.football_api.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.exception.ConflictException;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.PlayerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.PlayerRepository;
import java.util.Optional;
import com.oscar.football_api.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;

@ExtendWith(MockitoExtension.class)
class PlayerServiceImplTest {

    @InjectMocks private PlayerServiceImpl playerService;

    @Mock private PlayerRepository playerRepository;
    @Mock private ClubRepository clubRepository;
    @Mock private PlayerMapper playerMapper;

    @Test
    void givenPlayerRequestDTOWhenCreatePlayerThenReturnDTO() {
        PlayerRequestDTO requestDTO = new PlayerRequestDTO();
        requestDTO.setClubId(1L);
        requestDTO.setJerseyNumber(10);

        Club club = mock(Club.class);
        Player player = mock(Player.class);
        Player saved = mock(Player.class);
        PlayerResponseDTO dto = mock(PlayerResponseDTO.class);

        when(club.getId()).thenReturn(1L);
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        when(playerRepository.findByClubIdAndJerseyNumber(1L, 10)).thenReturn(Optional.empty());
        when(playerMapper.toEntity(requestDTO)).thenReturn(player);
        when(playerRepository.save(player)).thenReturn(saved);
        when(playerMapper.toDTO(saved)).thenReturn(dto);

        PlayerResponseDTO result = playerService.createPlayer(requestDTO);

        verify(playerRepository, times(1)).save(player);
        verify(playerMapper, times(1)).toEntity(requestDTO);
        assertThat(result, is(dto));
    }

    @Test
    void givenNonExistentClubWhenCreatePlayerThenThrowResourceNotFoundException() {
        PlayerRequestDTO requestDTO = new PlayerRequestDTO();
        requestDTO.setClubId(1L);

        when(clubRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> playerService.createPlayer(requestDTO));
    }

    @Test
    void givenDuplicateJerseyNumberWhenCreatePlayerThenThrowConflictException() {
        PlayerRequestDTO requestDTO = new PlayerRequestDTO();
        requestDTO.setClubId(1L);
        requestDTO.setJerseyNumber(10);

        Club club = mock(Club.class);
        when(club.getId()).thenReturn(1L);
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        when(playerRepository.findByClubIdAndJerseyNumber(1L, 10)).thenReturn(Optional.of(mock(Player.class)));

        assertThrows(ConflictException.class, () -> playerService.createPlayer(requestDTO));
    }

    @Test
    void givenPageableWhenGetAllPlayersThenReturnPage() {
        PageRequest pageable = PageRequest.of(0, 10);
        Player player = mock(Player.class);
        PlayerResponseDTO dto = mock(PlayerResponseDTO.class);
        Page<Player> page = new PageImpl<>(Collections.singletonList(player), pageable, 1);

        when(playerRepository.findAll(pageable)).thenReturn(page);
        when(playerMapper.toDTO(player)).thenReturn(dto);

        Page<PlayerResponseDTO> result = playerService.getAllPlayers(pageable);
        assertThat(result.getContent().get(0), is(dto));
    }
}
