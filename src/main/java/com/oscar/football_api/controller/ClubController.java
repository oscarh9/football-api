package com.oscar.football_api.controller;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.service.ClubService;
import com.oscar.football_api.utils.ApiConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.CLUBS)
@RequiredArgsConstructor
public class ClubController {

    private final ClubService clubService;

    @PostMapping
    public ResponseEntity<ClubResponseDTO> createClub(@Valid @RequestBody ClubRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clubService.createClub(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<ClubResponseDTO>> getALlClubs() {
        return ResponseEntity.ok(clubService.getAllClubs());
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity<ClubResponseDTO> getClubById(@PathVariable Long id) {
        return ResponseEntity.ok(clubService.getClubById(id));
    }

    @PutMapping(ApiConstant.ID)
    public ResponseEntity<ClubResponseDTO> updateClub(@PathVariable Long id, @Valid @RequestBody ClubRequestDTO requestDTO) {
        return ResponseEntity.ok(clubService.updateClub(id, requestDTO));
    }

    @DeleteMapping(ApiConstant.ID)
    public ResponseEntity<Void> deleteClub(@PathVariable Long id) {
        clubService.deleteClub(id);
        return ResponseEntity.noContent().build();
    }
}
