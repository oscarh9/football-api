package com.oscar.football_api.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.oscar.football_api.dto.ClubRequestDTO;
import com.oscar.football_api.dto.response.ClubResponseDTO;
import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.Manager;
import com.oscar.football_api.entity.Player;
import com.oscar.football_api.entity.enums.League;
import com.oscar.football_api.entity.enums.Position;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ClubMapperTest {

  @Autowired private ClubMapper mapper;

  @Test
  void testToEntity() {
    ClubRequestDTO dto = new ClubRequestDTO();
    dto.setName("FC Barcelona");
    dto.setCity("Barcelona");
    dto.setStadiumName("Camp Nou");
    dto.setLeague(League.LA_LIGA);
    dto.setTitlesWon(27);
    dto.setEstablishedDate(LocalDate.of(1899, 11, 29));

    Club club = mapper.toEntity(dto);

    assertThat(club).isNotNull();
    assertThat(club.getName()).isEqualTo("FC Barcelona");
    assertThat(club.getLeague()).isEqualTo(League.LA_LIGA);
  }

  @Test
  void testToDTO() {
    Club club = new Club();
    club.setName("FC Barcelona");
    club.setCity("Barcelona");
    club.setStadiumName("Camp Nou");
    club.setLeague(League.LA_LIGA);
    club.setTitlesWon(27);
    club.setEstablishedDate(LocalDate.of(1899, 11, 29));

    club.setPlayers(new ArrayList<>());
    Player player = new Player();
    player.setName("Messi");
    player.setPosition(Position.FORWARD);
    club.getPlayers().add(player);

    Manager manager = new Manager();
    manager.setName("Xavi");
    club.setManager(manager);

    ClubResponseDTO dto = mapper.toDTO(club);

    assertThat(dto).isNotNull();
    assertThat(dto.getName()).isEqualTo("FC Barcelona");
    assertThat(dto.getPlayers()).hasSize(1);
    assertThat(dto.getPlayers().get(0).getName()).isEqualTo("Messi");
    assertThat(dto.getManager()).isNotNull();
    assertThat(dto.getManager().getName()).isEqualTo("Xavi");
  }
}
