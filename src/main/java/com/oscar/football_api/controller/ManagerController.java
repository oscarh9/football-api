package com.oscar.football_api.controller;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.service.ManagerService;
import com.oscar.football_api.utils.ApiConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS)
@RequiredArgsConstructor
@Tag(name = "Managers", description = "Endpoints for manager management")
public class ManagerController {

    private final ManagerService managerService;

    @PostMapping
    @Operation(summary = "Create a new manager")
    public ResponseEntity<ManagerResponseDTO> createManager(@Valid @RequestBody ManagerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.createManager(requestDTO));
    }

    @GetMapping
    @Operation(summary = "Get all managers")
    public ResponseEntity<List<ManagerResponseDTO>> getAllManagers() {
        return ResponseEntity.ok(managerService.getAllManagers());
    }

    @GetMapping(ApiConstant.ID)
    @Operation(summary = "Get one manager")
    public ResponseEntity<ManagerResponseDTO> getManagerById(@PathVariable Long id) {
        return ResponseEntity.ok(managerService.getManagerById(id));
    }

    @PutMapping(ApiConstant.ID)
    @Operation(summary = "Update a manager")
    public ResponseEntity<ManagerResponseDTO> updateManager(@PathVariable Long id, @Valid @RequestBody ManagerRequestDTO requestDTO) {
        return ResponseEntity.ok(managerService.updateManager(id, requestDTO));
    }

    @DeleteMapping(ApiConstant.ID)
    @Operation(summary = "Delete a manager")
    public ResponseEntity<Void> deleteManager(@PathVariable Long id) {
        managerService.deleteManager(id);
        return ResponseEntity.noContent().build();
    }
}
