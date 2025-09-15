package com.oscar.football_api.controller;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.service.ManagerService;
import com.oscar.football_api.utils.ApiConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS)
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    public ResponseEntity<ManagerResponseDTO> createManager(@Valid @RequestBody ManagerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.createManager(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
    }
}
