package com.oscar.football_api.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.mapper.ManagerMapper;
import com.oscar.football_api.service.ManagerService;
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

@WebMvcTest(ManagerController.class)
@AutoConfigureMockMvc(addFilters = false)
class ManagerControllerTest {

  @MockBean private ManagerService managerService;
  @MockBean private ManagerMapper managerMapper;

  @Autowired private MockMvc mockMvc;
  @Autowired private ObjectMapper objectMapper;

  @Test
  void givenValidManagerRequest_whenCreateManager_thenReturn201() throws Exception {
    ManagerRequestDTO requestDTO =
        ManagerRequestDTO.builder()
            .name("Pep Guardiola")
            .nationality("Spanish")
            .dateOfBirth(LocalDate.of(1971, 1, 18))
            .titlesWon(30)
            .clubId(1L)
            .build();

    ManagerResponseDTO responseDTO = new ManagerResponseDTO();
    when(managerService.createManager(requestDTO)).thenReturn(responseDTO);

    MockHttpServletResponse response =
        mockMvc
            .perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(requestDTO)))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.CREATED.value()));
  }

  @Test
  void givenManagerRequestWithEmptyName_whenCreateManager_thenReturn400() throws Exception {
    ManagerRequestDTO requestDTO =
        ManagerRequestDTO.builder()
            .name("")
            .nationality("Spanish")
            .dateOfBirth(LocalDate.of(1971, 1, 18))
            .titlesWon(30)
            .clubId(1L)
            .build();

    MockHttpServletResponse response =
        mockMvc
            .perform(
                post(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS)
                    .contentType("application/json")
                    .content(objectMapper.writeValueAsString(requestDTO)))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.BAD_REQUEST.value()));
  }

  @Test
  void givenId_whenDeleteManager_thenReturn204() throws Exception {
    MockHttpServletResponse response =
        mockMvc
            .perform(delete(ApiConstant.API + ApiConstant.V1 + ApiConstant.MANAGERS + "/1"))
            .andReturn()
            .getResponse();

    assertThat(response.getStatus(), is(HttpStatus.NO_CONTENT.value()));
  }
}
