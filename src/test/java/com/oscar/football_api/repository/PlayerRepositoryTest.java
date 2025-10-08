package com.oscar.football_api.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.oscar.football_api.entity.Player;
import com.oscar.football_api.entity.enums.Position;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@ActiveProfiles("test")
class PlayerRepositoryTest {

  @Autowired private PlayerRepository playerRepository;

  @Test
  void testFindByClubIdAndJerseyNumber() {
    Optional<Player> playerOpt = playerRepository.findByClubIdAndJerseyNumber(1L, 1);
    assertThat(playerOpt).isPresent();
    assertThat(playerOpt.get().getName()).isEqualTo("Marc-André ter Stegen");
  }

  @Test
  void testSearchByPositionNameNationality() {
    var pageable = PageRequest.of(0, 10);

    var result = playerRepository.search(Position.MIDFIELDER, "Pedri", "Spanish", pageable);
    assertThat(result).isNotEmpty();
    assertThat(result.getContent().get(0).getName()).isEqualTo("Pedri");

    var allPlayers = playerRepository.search(null, null, null, pageable);
    assertThat(allPlayers.getTotalElements()).isGreaterThan(0);
  }
}
