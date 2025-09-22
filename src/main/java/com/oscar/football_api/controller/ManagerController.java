package com.oscar.football_api.controller;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.dto.response.PageResponseDTO;
import com.oscar.football_api.dto.search.ManagerSearchDTO;
import com.oscar.football_api.exception.InvalidSortFieldException;
import com.oscar.football_api.service.ManagerService;
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
@RequestMapping(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS)
@RequiredArgsConstructor
@Tag(name = "Managers", description = "Endpoints for manager management")
public class ManagerController {

    private final ManagerService managerService;
    private static final List<String> ALLOWED_SORTS = List.of("name","nationality","dateOfBirth","titlesWon");

    @PostMapping
    @Operation(summary = "Create a new manager")
    public ResponseEntity<ManagerResponseDTO> createManager(@Valid @RequestBody ManagerRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(managerService.createManager(requestDTO));
    }

    @GetMapping
    @Operation(summary = "Get all managers")
    public ResponseEntity<PageResponseDTO<ManagerResponseDTO>> getAllManagers(
            @ParameterObject @PageableDefault(page = 0, size = 10, sort = "name")Pageable pageable
            ) {
        var page = managerService.getAllManagers(pageable);
        return ResponseEntity.ok(PageResponseDTO.fromPage(page));
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

    @GetMapping(ApiConstant.SEARCH)
    @Operation(summary = "Search managers with filters, pagination and sorting")
    public ResponseEntity<PageResponseDTO<ManagerResponseDTO>> searchManagers(@ParameterObject ManagerSearchDTO criteria) {

        if (!ALLOWED_SORTS.contains(criteria.getSortBy())) {
            throw new InvalidSortFieldException(criteria.getSortBy());
        }

        Sort sort = Sort.by(Sort.Direction.fromString(criteria.getSortDir()), criteria.getSortBy());
        Pageable sortedPageable = PageRequest.of(criteria.getPage(), criteria.getSize(), sort);

        Page<ManagerResponseDTO> page = managerService.searchManagers(
                criteria.getName(),
                criteria.getNationality(),
                criteria.getClubName(),
                sortedPageable
        );

        return ResponseEntity.ok(PageResponseDTO.fromPage(page));
    }
}
