package com.oscar.football_api.entity;

import com.oscar.football_api.entity.enums.Position;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import lombok.*;

@Entity
@Table(name = "players")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Player {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String name;

  @Enumerated(EnumType.STRING)
  @NotNull
  private Position position;

  @Min(1)
  @Max(99)
  private int jerseyNumber;

  @Past private LocalDate dateOfBirth;

  @NotBlank
  @Size(max = 50)
  private String nationality;

  @ManyToOne
  @JoinColumn(name = "club_id", nullable = false)
  private Club club;
}
