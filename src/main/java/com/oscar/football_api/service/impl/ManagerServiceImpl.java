package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import com.oscar.football_api.mapper.ManagerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.ManagerRepository;
import com.oscar.football_api.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ClubRepository clubRepository;
    private final ManagerMapper managerMapper;

    public ManagerResponseDTO createManager(ManagerRequestDTO requestDTO) {
        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new RuntimeException("Club not found."));

        if (club.getManager() != null) {
            throw new RuntimeException("This club already has a manager assigned.");
        }

        Manager manager = managerMapper.toEntity(requestDTO);
        manager.setClub(club);

        Manager saved = managerRepository.save(manager);
        club.setManager(saved);
        clubRepository.save(club);

        return managerMapper.toDTO(saved);
    }

    public List<ManagerResponseDTO> getAllManagers() {
        return managerRepository.findAll()
                .stream()
                .map(managerMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ManagerResponseDTO getManagerById(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found."));
        return managerMapper.toDTO(manager);
    }

    public ManagerResponseDTO updateManager(Long id, ManagerRequestDTO requestDTO) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found."));

        if (!manager.getClub().getId().equals(requestDTO.getClubId())) {
            throw new RuntimeException("Cannot change manager's club. Delete and create a new manager instead.");
        }

        manager.setName(requestDTO.getName());
        manager.setNationality(requestDTO.getNationality());
        manager.setDateOfBirth(requestDTO.getDateOfBirth());
        manager.setTitlesWon(requestDTO.getTitlesWon());

        Manager updated = managerRepository.save(manager);
        return managerMapper.toDTO(updated);
    }

    public void deleteManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Manager not found."));

        Club club = manager.getClub();
        if (club != null) {
            club.setManager(null);
            clubRepository.save(club);
            manager.setClub(null);
        }

        managerRepository.delete(manager);
    }
}
