package com.oscar.football_api.controller;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.service.PlayerService;
import com.oscar.football_api.utils.ApiConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS)
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(requestDTO));
    }

    @GetMapping
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping(ApiConstant.ID)
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PutMapping(ApiConstant.ID)
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.ok(playerService.updatePlayer(id, requestDTO));
    }

    @DeleteMapping(ApiConstant.ID)
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
