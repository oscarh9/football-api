package com.oscar.football_api.repository.specifications;

import com.oscar.football_api.entity.Club;
import com.oscar.football_api.entity.enums.League;
import org.springframework.data.jpa.domain.Specification;

public class ClubSpecifications {

    public static Specification<Club> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Club> hasCity(String city) {
        return (root, query, cb) ->
                city == null ? null : cb.like(cb.lower(root.get("city")), "%" + city.toLowerCase() + "%");
    }

    public static Specification<Club> hasStadiumName(String stadiumName) {
        return (root, query, cb) ->
                stadiumName == null ? null : cb.like(cb.lower(root.get("stadiumName")), "%" + stadiumName.toLowerCase() + "%");
    }

    public static Specification<Club> hasLeague(League league) {
        return (root, query, cb) ->
                league == null ? null : cb.equal(root.get("league"), league);
    }
}
