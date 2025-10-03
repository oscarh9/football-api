package com.oscar.football_api.mapper;

import com.oscar.football_api.dto.PlayerRequestDTO;
import com.oscar.football_api.dto.response.PlayerResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.entity.enums.Position;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class PlayerMapperTest {

    @Autowired
    private PlayerMapper mapper;

    @Test
    void testToEntity() {
        PlayerRequestDTO dto = new PlayerRequestDTO();
        dto.setName("Messi");
        dto.setPosition(Position.FORWARD);
        dto.setJerseyNumber(10);
        dto.setDateOfBirth(LocalDate.of(1987, 6, 24));
        dto.setNationality("Argentinian");
        dto.setClubId(1L);

        Player player = mapper.toEntity(dto);

        assertThat(player).isNotNull();
        assertThat(player.getName()).isEqualTo("Messi");
        assertThat(player.getPosition()).isEqualTo(Position.FORWARD);
        assertThat(player.getJerseyNumber()).isEqualTo(10);
        assertThat(player.getClub().getId()).isEqualTo(1L);
    }

    @Test
    void testToDTO() {
        Club club = new Club();
        club.setId(1L);

        Player player = new Player();
        player.setName("Messi");
        player.setPosition(Position.FORWARD);
        player.setJerseyNumber(10);
        player.setNationality("Argentinian");
        player.setClub(club);

        PlayerResponseDTO dto = mapper.toDTO(player);

        assertThat(dto).isNotNull();
        assertThat(dto.getName()).isEqualTo("Messi");
        assertThat(dto.getPosition()).isEqualTo(Position.FORWARD);
        assertThat(dto.getClubId()).isEqualTo(1L);
    }
}
