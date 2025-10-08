package com.oscar.football_api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.enums.League;
import com.oscar.football_api.mapper.ClubMapper;
import com.oscar.football_api.service.ClubService;
import com.oscar.football_api.utils.ApiConstant;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(ClubController.class)
@AutoConfigureMockMvc(addFilters = false)
class ClubControllerTest {

  @MockBean private ClubService clubService;
  @MockBean private ClubMapper clubMapper;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void givenValidClubRequest_whenCreateClub_thenReturn201() throws Exception {
    ClubRequestDTO requestDTO =
        ClubRequestDTO.builder()
            .name("FC Barcelona")
            .establishedDate(LocalDate.of(1899, 11, 29))
            .stadiumName("Camp Nou")
            .city("Barcelona")
            .league(League.LA_LIGA)
            .titlesWon(75)
            .build();

    ClubResponseDTO responseDTO = new ClubResponseDTO();
    when(clubService.createClub(requestDTO)).thenReturn(responseDTO);

    MockHttpServletResponse response =
        mockMvc
            .perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.CLUBS)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(requestDTO)))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
  }

  @Test
  void givenClubRequestWithEmptyName_whenCreateClub_thenReturn400() throws Exception {
    ClubRequestDTO requestDTO =
        ClubRequestDTO.builder()
            .name("")
            .establishedDate(LocalDate.of(1899, 11, 29))
            .stadiumName("Camp Nou")
            .city("Barcelona")
            .league(League.LA_LIGA)
            .titlesWon(75)
            .build();

    MockHttpServletResponse response =
        mockMvc
            .perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.CLUBS)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(requestDTO)))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
  }

  @Test
  void givenId_whenDeleteClub_thenReturn204() throws Exception {
    MockHttpServletResponse response =
        mockMvc
            .perform(delete(ApiConstant.API + ApiConstant.V1 + ApiConstant.CLUBS + "/1"))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.NO_CONTENT.value()));
  }
}
