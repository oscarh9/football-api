package com.oscar.football_api.mapper;

import com.oscar.football_api.dto.ManagerRequestDTO;
import com.oscar.football_api.dto.response.ManagerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class ManagerMapperTest {

    @Autowired
    private ManagerMapper mapper;

    @Test
    void testToEntity() {
        ManagerRequestDTO dto = new ManagerRequestDTO();
        dto.setName("Xavi");
        dto.setNationality("Spanish");
        dto.setDateOfBirth(LocalDate.of(1980, 1, 25));
        dto.setTitlesWon(10);
        dto.setClubId(1L);

        Manager manager = mapper.toEntity(dto);

        assertThat(manager).isNotNull();
        assertThat(manager.getName()).isEqualTo("Xavi");
        assertThat(manager.getClub().getId()).isEqualTo(1L);
    }

    @Test
    void testToDTO() {
        Club club = new Club();
        club.setId(1L);

        Manager manager = new Manager();
        manager.setName("Xavi");
        manager.setNationality("Spanish");
        manager.setClub(club);

        ManagerResponseDTO dto = mapper.toDTO(manager);

        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo("Xavi");
        assertThat(dto.getClubId()).isEqualTo(1L);
    }
}
