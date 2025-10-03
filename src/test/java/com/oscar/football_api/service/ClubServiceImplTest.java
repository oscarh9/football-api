package com.oscar.football_api.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.ClubMapper;
import com.oscar.football_api.repository.ClubRepository;
import java.util.Optional;

import com.oscar.football_api.service.impl.ClubServiceImpl;
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
class ClubServiceImplTest {

    @InjectMocks private ClubServiceImpl clubService;

    @Mock private ClubRepository clubRepository;
    @Mock private ClubMapper clubMapper;

    @Test
    void givenClubRequestDTOWhenCreateClubThenReturnClubResponseDTO() {
        ClubRequestDTO requestDTO = new ClubRequestDTO();
        requestDTO.setName("Real Madrid");

        Club club = mock(Club.class);
        Club saved = mock(Club.class);
        ClubResponseDTO dto = mock(ClubResponseDTO.class);

        when(clubMapper.toEntity(requestDTO)).thenReturn(club);
        when(clubRepository.save(club)).thenReturn(saved);
        when(clubMapper.toDTO(saved)).thenReturn(dto);

        ClubResponseDTO result = clubService.createClub(requestDTO);

        verify(clubRepository, times(1)).save(club);
        verify(clubMapper, times(1)).toEntity(requestDTO);
        verify(clubMapper, times(1)).toDTO(saved);

        assertThat(result, is(dto));
    }

    @Test
    void givenExistingClubIdWhenGetClubByIdThenReturnDTO() {
        Long id = 1L;
        Club club = mock(Club.class);
        ClubResponseDTO dto = mock(ClubResponseDTO.class);

        when(clubRepository.findById(id)).thenReturn(Optional.of(club));
        when(clubMapper.toDTO(club)).thenReturn(dto);

        ClubResponseDTO result = clubService.getClubById(id);

        verify(clubRepository, times(1)).findById(id);
        verify(clubMapper, times(1)).toDTO(club);
        assertThat(result, is(dto));
    }

    @Test
    void givenNonExistentClubIdWhenGetClubByIdThenThrowResourceNotFoundException() {
        Long id = 1L;
        when(clubRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> clubService.getClubById(id));
    }

    @Test
    void givenPageableWhenGetAllClubsThenReturnPage() {
        PageRequest pageable = PageRequest.of(0, 10);
        Club club = mock(Club.class);
        ClubResponseDTO dto = mock(ClubResponseDTO.class);
        Page<Club> page = new PageImpl<>(Collections.singletonList(club), pageable, 1);

        when(clubRepository.findAll(pageable)).thenReturn(page);
        when(clubMapper.toDTO(club)).thenReturn(dto);

        Page<ClubResponseDTO> result = clubService.getAllClubs(pageable);

        assertThat(result.getContent().get(0), is(dto));
    }
}
