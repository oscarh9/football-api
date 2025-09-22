package com.oscar.football_api.controller;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.dto.search.PlayerSearchDTO;
import com.oscar.football_api.dto.response.PageResponseDTO;
import com.oscar.football_api.exception.InvalidSortFieldException;
import com.oscar.football_api.service.PlayerService;
import com.oscar.football_api.utils.ApiConstant;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
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
    private static final List<String> ALLOWED_SORTS = List.of("name","position","jerseyNumber","dateOfBirth","nationality");

    @PostMapping
    @Operation(summary = "Create a new player")
    public ResponseEntity<PlayerResponseDTO> createPlayer(@Valid @RequestBody PlayerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(playerService.createPlayer(requestDTO));
    }

    @GetMapping
    @Operation(summary = "Get all players")
    public ResponseEntity<PageResponseDTO<PlayerResponseDTO>> getAllPlayers(
            @ParameterObject @PageableDefault(page = 0, size = 10, sort = "name") Pageable pageable
    ) {
        var page = playerService.getAllPlayers(pageable);
        return ResponseEntity.ok(PageResponseDTO.fromPage(page));
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

    @GetMapping(ApiConstant.SEARCH)
    @Operation(summary = "Search players with filters, pagination and sorting")
    public ResponseEntity<PageResponseDTO<PlayerResponseDTO>> searchPlayers(@ParameterObject PlayerSearchDTO criteria) {

        if (!ALLOWED_SORTS.contains(criteria.getSortBy())) {
            throw new InvalidSortFieldException(criteria.getSortBy());
        }

        Sort sort = Sort.by(Sort.Direction.fromString(criteria.getSortDir()), criteria.getSortBy());
        Pageable sortedPageable = PageRequest.of(criteria.getPage(), criteria.getSize(), sort);

        Page<PlayerResponseDTO> page = playerService.searchPlayers(
                criteria.getPosition(),
                criteria.getName(),
                criteria.getNationality(),
                sortedPageable
        );

        return ResponseEntity.ok(PageResponseDTO.fromPage(page));
    }
}
