package com.oscar.football_api.service.impl;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.exception.ConflictException;
import com.oscar.football_api.exception.ForbiddenException;
import com.oscar.football_api.exception.ResourceNotFoundException;
import com.oscar.football_api.mapper.ManagerMapper;
import com.oscar.football_api.repository.ClubRepository;
import com.oscar.football_api.repository.ManagerRepository;
import com.oscar.football_api.repository.specifications.ManagerSpecifications;
import com.oscar.football_api.repository.specifications.PlayerSpecifications;
import com.oscar.football_api.service.ManagerService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository managerRepository;
    private final ClubRepository clubRepository;
    private final ManagerMapper managerMapper;

    @Override
    public ManagerResponseDTO createManager(ManagerRequestDTO requestDTO) {
        Club club = clubRepository.findById(requestDTO.getClubId())
                .orElseThrow(() -> new ResourceNotFoundException("Club with id " + requestDTO.getClubId() + " not found"));

        if (club.getManager() != null) {
            throw new ConflictException("This club already has a manager assigned.");
        }

        Manager manager = managerMapper.toEntity(requestDTO);
        manager.setClub(club);

        Manager saved = managerRepository.save(manager);
        club.setManager(saved);
        clubRepository.save(club);

        return managerMapper.toDTO(saved);
    }

    @Override
    public Page<ManagerResponseDTO> getAllManagers(Pageable pageable) {
        return managerRepository.findAll(pageable)
                .map(managerMapper::toDTO);
    }

    @Override
    public ManagerResponseDTO getManagerById(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager with id " + id + " not found"));
        return managerMapper.toDTO(manager);
    }

    @Override
    public ManagerResponseDTO updateManager(Long id, ManagerRequestDTO requestDTO) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager with id " + id + " not found"));

        if (!manager.getClub().getId().equals(requestDTO.getClubId())) {
            throw new ForbiddenException("Cannot change manager's club. Delete and create a new manager instead");
        }

        manager.setName(requestDTO.getName());
        manager.setNationality(requestDTO.getNationality());
        manager.setDateOfBirth(requestDTO.getDateOfBirth());
        manager.setTitlesWon(requestDTO.getTitlesWon());

        Manager updated = managerRepository.save(manager);
        return managerMapper.toDTO(updated);
    }

    @Override
    public void deleteManager(Long id) {
        Manager manager = managerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Manager with id " + id + " not found"));

        Club club = manager.getClub();
        if (club != null) {
            club.setManager(null);
            clubRepository.save(club);
            manager.setClub(null);
        }

        managerRepository.delete(manager);
    }

    @Override
    public Page<ManagerResponseDTO> searchManagers(String name, String nationality, String clubName, Pageable pageable) {
        Specification<Manager> spec = Specification
                .where(ManagerSpecifications.hasName(name))
                .and(ManagerSpecifications.hasNationality(nationality))
                .and(ManagerSpecifications.hasClubName(clubName));

        return managerRepository.findAll(spec, pageable).map(managerMapper::toDTO);
    }
}
