package com.oscar.football_api.repository.specifications;

import com.oscar.football_api.entity.Manager;
import org.springframework.data.jpa.domain.Specification;

public class ManagerSpecifications {

    public static Specification<Manager> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Manager> hasNationality(String nationality) {
        return (root, query, cb) ->
                nationality == null ? null : cb.equal(root.get("nationality"), nationality);
    }

    public static Specification<Manager> hasClubName(String clubName) {
        return (root, query, cb) ->
                clubName == null ? null : cb.equal(root.join("club").get("name"), clubName);
    }
}
