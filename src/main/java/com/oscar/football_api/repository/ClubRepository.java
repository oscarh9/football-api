package com.oscar.football_api.repository;

import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.enums.League;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("""
        SELECT c FROM Club c
        WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', COALESCE(:name, ''), '%'))
          AND LOWER(c.city) LIKE LOWER(CONCAT('%', COALESCE(:city, ''), '%'))
          AND LOWER(c.stadiumName) LIKE LOWER(CONCAT('%', COALESCE(:stadiumName, ''), '%'))
          AND (:league IS NULL OR c.league = :league)
        ORDER BY c.name
    """)
    Page<Club> search(
            @Param("name") String name,
            @Param("city") String city,
            @Param("stadiumName") String stadiumName,
            @Param("league") League league,
            Pageable pageable
    );
}
