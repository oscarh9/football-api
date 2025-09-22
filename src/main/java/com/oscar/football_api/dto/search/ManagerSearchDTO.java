package com.oscar.football_api.dto.search;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ManagerSearchDTO {

    private String name;
    private String nationality;
    private String clubName;
    private String sortBy = "name";
    private String sortDir = "asc";
    private int page = 0;
    private int size = 10;
}
