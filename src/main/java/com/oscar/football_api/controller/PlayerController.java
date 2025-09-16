package com.oscar.football_api.controller;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.service.PlayerService;
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
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS)
@RequiredArgsConstructor
@Tag(name = "Players", description = "Endpoints for player management")
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    @Operation(summary = "Create a new player")
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(requestDTO));
    }

    @GetMapping
    @Operation(summary = "Get all players")
    public ResponseEntity<List<PlayerResponseDTO>> getAllPlayers() {
        return ResponseEntity.ok(playerService.getAllPlayers());
    }

    @GetMapping(ApiConstant.ID)
    @Operation(summary = "Get one player")
    public ResponseEntity<PlayerResponseDTO> getPlayerById(@PathVariable Long id) {
        return ResponseEntity.ok(playerService.getPlayerById(id));
    }

    @PutMapping(ApiConstant.ID)
    @Operation(summary = "Update a player")
    public ResponseEntity<PlayerResponseDTO> updatePlayer(@PathVariable Long id, @Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.ok(playerService.updatePlayer(id, requestDTO));
    }

    @DeleteMapping(ApiConstant.ID)
    @Operation(summary = "Delete a player")
    public ResponseEntity<Void> deletePlayer(@PathVariable Long id) {
        playerService.deletePlayer(id);
        return ResponseEntity.noContent().build();
    }
}
