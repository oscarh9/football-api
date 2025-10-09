package com.oscar.football_api.entity;

import com.oscar.football_api.entity.enums.League;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "clubs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Club {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotBlank
  @Size(max = 50)
  private String name;

  @PastOrPresent private LocalDate establishedDate;

  @NotBlank
  @Size(max = 50)
  private String stadiumName;

  @NotBlank
  @Size(max = 50)
  private String city;

  @Enumerated(EnumType.STRING)
  @NotNull
  private League league;

  @Min(0)
  private int titlesWon;

  @OneToMany(mappedBy = "club", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<Player> players;

  @OneToOne(mappedBy = "club", cascade = CascadeType.ALL)
  private Manager manager;
}
