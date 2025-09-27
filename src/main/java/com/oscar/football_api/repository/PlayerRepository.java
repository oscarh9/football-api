package com.oscar.football_api.repository;

import com.oscar.football_api.entity.Player;
import com.oscar.football_api.entity.enums.Position;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByClubIdAndJerseyNumber(Long clubId, int jerseyNumber);

    @Query("""
    SELECT p FROM Player p
    WHERE (:position IS NULL OR p.position = :position)
      AND LOWER(p.name) LIKE LOWER(CONCAT('%', COALESCE(:name, ''), '%'))
      AND LOWER(p.nationality) LIKE LOWER(CONCAT('%', COALESCE(:nationality, ''), '%'))
      ORDER BY p.name
    """)
    Page<Player> search(
            @Param("position") Position position,
            @Param("name") String name,
            @Param("nationality") String nationality,
            Pageable pageable
    );
}
