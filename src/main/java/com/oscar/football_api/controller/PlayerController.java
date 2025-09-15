package com.oscar.football_api.controller;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.service.PlayerService;
import com.oscar.football_api.utils.ApiConstant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS)
@RequiredArgsConstructor
public class PlayerController {

    private final PlayerService playerService;

    @PostMapping
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(requestDTO));
    }
}
