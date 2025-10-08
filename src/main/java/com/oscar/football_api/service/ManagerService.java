package com.oscar.football_api.service;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {

  ManagerResponseDTO createManager(ManagerRequestDTO requestDTO);

  Page<ManagerResponseDTO> getAllManagers(Pageable pageable);

  ManagerResponseDTO getManagerById(Long id);

  ManagerResponseDTO updateManager(Long id, ManagerRequestDTO requestDTO);

  void deleteManager(Long id);

  Page<ManagerResponseDTO> searchManagers(
      String name, String nationality, String clubName, Pageable pageable);
}
