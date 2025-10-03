package com.oscar.football_api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.enums.Position;
import com.oscar.football_api.mapper.PlayerMapper;
import com.oscar.football_api.service.PlayerService;
import com.oscar.football_api.utils.ApiConstant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(PlayerController.class)
@AutoConfigureMockMvc(addFilters = false)
class PlayerControllerTest {

    @MockBean private PlayerService playerService;
    @MockBean private PlayerMapper playerMapper;

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    void givenValidPlayerRequest_whenCreatePlayer_thenReturn201() throws Exception {
        PlayerRequestDTO requestDTO = PlayerRequestDTO.builder()
                .name("Lionel Messi")
                .position(Position.FORWARD)
                .jerseyNumber(10)
                .dateOfBirth(LocalDate.of(1987, 6, 24))
                .nationality("Argentinian")
                .clubId(1L)
                .build();

        PlayerResponseDTO responseDTO = new PlayerResponseDTO();
        when(playerService.createPlayer(requestDTO)).thenReturn(responseDTO);

        MockHttpServletResponse response = mockMvc.perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDTO))
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
    }

    @Test
    void givenPlayerRequestWithEmptyName_whenCreatePlayer_thenReturn400() throws Exception {
        PlayerRequestDTO requestDTO = PlayerRequestDTO.builder()
                .name("")
                .position(Position.FORWARD)
                .jerseyNumber(10)
                .dateOfBirth(LocalDate.of(1987, 6, 24))
                .nationality("Argentinian")
                .clubId(1L)
                .build();

        MockHttpServletResponse response = mockMvc.perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDTO))
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
    }

    @Test
    void givenId_whenDeletePlayer_thenReturn204() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
                delete(ApiConstant.API + ApiConstant.V1 + ApiConstant.PLAYERS + "/1")
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.NO_CONTENT.value()));
    }
}
