package com.oscar.football_api.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import com.oscar.football_api.exception.ConflictException;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.ManagerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.ManagerRepository;
import java.util.Optional;
import com.oscar.football_api.service.impl.ManagerServiceImpl;
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
class ManagerServiceImplTest {

    @InjectMocks private ManagerServiceImpl managerService;

    @Mock private ManagerRepository managerRepository;
    @Mock private ClubRepository clubRepository;
    @Mock private ManagerMapper managerMapper;

    @Test
    void givenManagerRequestDTOWhenCreateManagerThenReturnDTO() {
        ManagerRequestDTO requestDTO = new ManagerRequestDTO();
        requestDTO.setClubId(1L);

        Club club = mock(Club.class);
        Manager manager = mock(Manager.class);
        Manager saved = mock(Manager.class);
        ManagerResponseDTO dto = mock(ManagerResponseDTO.class);

        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        when(club.getManager()).thenReturn(null);
        when(managerMapper.toEntity(requestDTO)).thenReturn(manager);
        when(managerRepository.save(manager)).thenReturn(saved);
        when(managerMapper.toDTO(saved)).thenReturn(dto);

        ManagerResponseDTO result = managerService.createManager(requestDTO);

        verify(clubRepository, times(1)).findById(1L);
        verify(managerRepository, times(1)).save(manager);
        verify(club, times(1)).setManager(saved);
        assertThat(result, is(dto));
    }

    @Test
    void givenNonExistentClubWhenCreateManagerThenThrowResourceNotFoundException() {
        ManagerRequestDTO requestDTO = new ManagerRequestDTO();
        requestDTO.setClubId(1L);

        when(clubRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> managerService.createManager(requestDTO));
    }

    @Test
    void givenClubWithManagerWhenCreateManagerThenThrowConflictException() {
        ManagerRequestDTO requestDTO = new ManagerRequestDTO();
        requestDTO.setClubId(1L);

        Club club = mock(Club.class);
        when(clubRepository.findById(1L)).thenReturn(Optional.of(club));
        when(club.getManager()).thenReturn(mock(Manager.class));

        assertThrows(ConflictException.class, () -> managerService.createManager(requestDTO));
    }

    @Test
    void givenPageableWhenGetAllManagersThenReturnPage() {
        PageRequest pageable = PageRequest.of(0, 10);
        Manager manager = mock(Manager.class);
        ManagerResponseDTO dto = mock(ManagerResponseDTO.class);
        Page<Manager> page = new PageImpl<>(Collections.singletonList(manager), pageable, 1);

        when(managerRepository.findAll(pageable)).thenReturn(page);
        when(managerMapper.toDTO(manager)).thenReturn(dto);

        Page<ManagerResponseDTO> result = managerService.getAllManagers(pageable);
        assertThat(result.getContent().get(0), is(dto));
    }
}
