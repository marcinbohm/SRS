package com.srs.repository.specs;

import com.srs.entity.Prisoner_;
import com.srs.entity.Prisoner;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.util.List;

public class PrisonerSpecs {

    public static Specification<Prisoner> prisonerIdIn(List<Integer> prisonIdList) {
        return (root, query, criteriaBuilder) -> {
            if (prisonIdList != null && !prisonIdList.isEmpty()) {
                Expression<String> exp = root.get(Prisoner_.PRISONER_ID);
                return exp.in(prisonIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Prisoner> nameEquals(String name) {
        return (root, query, criteriaBuilder) ->
                name != null ? criteriaBuilder.equal(root.get(Prisoner_.NAME), name) : criteriaBuilder.conjunction();
    }

    public static Specification<Prisoner> surnameEquals(String surname) {
        return (root, query, criteriaBuilder) ->
                surname != null ? criteriaBuilder.equal(root.get(Prisoner_.SURNAME), surname) : criteriaBuilder.conjunction();
    }

    public static Specification<Prisoner> peselEquals(String pesel) {
        return (root, query, criteriaBuilder) ->
                pesel != null ? criteriaBuilder.equal(root.get(Prisoner_.PESEL), pesel) : criteriaBuilder.conjunction();
    }

    public static Specification<Prisoner> kartaPobytuEquals(String kartaPobytu) {
        return (root, query, criteriaBuilder) ->
                kartaPobytu != null ? criteriaBuilder.equal(root.get(Prisoner_.KARTA_POBYTU_ID), kartaPobytu) : criteriaBuilder.conjunction();
    }

    public static Specification<Prisoner> passportEquals(String passport) {
        return (root, query, criteriaBuilder) ->
                passport != null ? criteriaBuilder.equal(root.get(Prisoner_.PASSPORT_ID), passport) : criteriaBuilder.conjunction();
    }
}
