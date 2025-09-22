package com.oscar.football_api.repository.specifications;

import com.oscar.football_api.entity.Player;
import com.oscar.football_api.entity.enums.Position;
import org.springframework.data.jpa.domain.Specification;

public class PlayerSpecifications {

    public static Specification<Player> hasPosition(Position position) {
        return (root, query, cb) ->
                position == null ? null : cb.equal(root.get("position"), position);
    }

    public static Specification<Player> hasName(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Player> hasNationality(String nationality) {
        return (root, query, cb) ->
                nationality == null ? null : cb.equal(root.get("nationality"), nationality);
    }
}
