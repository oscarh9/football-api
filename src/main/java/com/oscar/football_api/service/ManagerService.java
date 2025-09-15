package com.oscar.football_api.service;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;

public interface ManagerService {

    ManagerResponseDTO createManager(ManagerRequestDTO requestDTO);
}
