package com.oscar.football_api.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "managers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 50)
    private String name;

    @NotBlank
    @Size(max = 50)
    private String nationality;

    @Past
    private LocalDate dateOfBirth;

    @Min(0)
    private int titlesWon;

    @OneToOne
    @JoinColumn(name = "club_id", unique = true, nullable = false)
    private Club club;
}