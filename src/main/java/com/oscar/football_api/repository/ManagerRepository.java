package com.oscar.football_api.repository;

import com.oscar.football_api.entity.Manager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query("""
    SELECT m FROM Manager m
    WHERE LOWER(m.name) LIKE LOWER(CONCAT('%', COALESCE(:name, ''), '%'))
      AND LOWER(m.nationality) LIKE LOWER(CONCAT('%', COALESCE(:nationality, ''), '%'))
      AND LOWER(m.club.name) LIKE LOWER(CONCAT('%', COALESCE(:clubName, ''), '%'))
      ORDER BY m.name
    """)
    Page<Manager> search(
            @Param("name") String name,
            @Param("nationality") String nationality,
            @Param("clubName") String clubName,
            Pageable pageable
    );
}
