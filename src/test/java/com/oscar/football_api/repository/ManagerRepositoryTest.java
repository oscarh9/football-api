package com.oscar.football_api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class ManagerRepositoryTest {

    @Autowired
    private ManagerRepository managerRepository;

    @Test
    void testSearchByNameNationalityClubName() {
        var pageable = PageRequest.of(0, 10);

        var result = managerRepository.search("Hansi Flick", "German", "Barcelona", pageable);
        assertThat(result).isNotEmpty();
        assertThat(result.getContent().get(0).getName()).isEqualTo("Hansi Flick");

        var allManagers = managerRepository.search(null, null, null, pageable);
        assertThat(allManagers.getTotalElements()).isGreaterThan(0);
    }
}
