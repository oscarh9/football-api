package com.oscar.football_api.service;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;

import java.util.List;

public interface ManagerService {

    ManagerResponseDTO createManager(ManagerRequestDTO requestDTO);
    List<ManagerResponseDTO> getAllManagers();
    ManagerResponseDTO getManagerById(Long id);
}
