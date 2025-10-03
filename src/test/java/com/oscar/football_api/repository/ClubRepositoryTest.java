package com.oscar.football_api.repository;

import com.oscar.football_api.entity.enums.League;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Test
    void testSearchByNameCityStadiumLeague() {
        var pageable = PageRequest.of(0, 10);

        var result = clubRepository.search("Barcelona", "Barcelona", "Camp Nou", League.LA_LIGA, pageable);
        assertThat(result).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("FC Barcelona");

        var allClubs = clubRepository.search(null, null, null, null, pageable);
        assertThat(allClubs.getTotalElements()).isGreaterThan(0);
    }
}