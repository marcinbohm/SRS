package com.srs.repository.specs;

import com.srs.entity.Prison_;
import com.srs.entity.Prison;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.util.List;

public class PrisonSpecs {

    public static Specification<Prison> prisonIdIn(List<Integer> prisonIdList) {
        return (root, query, criteriaBuilder) -> {
            if (prisonIdList != null && !prisonIdList.isEmpty()) {
                Expression<String> exp = root.get(Prison_.PRISON_ID);
                return exp.in(prisonIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Prison> voivodeshipEquals(String name) {
        return (root, query, criteriaBuilder) ->
                name != null ? criteriaBuilder.equal(root.get(Prison_.VOIVODESHIP), name) : criteriaBuilder.conjunction();
    }

    public static Specification<Prison> cityEquals(String surname) {
        return (root, query, criteriaBuilder) ->
                surname != null ? criteriaBuilder.equal(root.get(Prison_.CITY), surname) : criteriaBuilder.conjunction();
    }

    public static Specification<Prison> postalCodeEquals(String pesel) {
        return (root, query, criteriaBuilder) ->
                pesel != null ? criteriaBuilder.equal(root.get(Prison_.POSTAL_CODE), pesel) : criteriaBuilder.conjunction();
    }

    public static Specification<Prison> streetEquals(String kartaPobytu) {
        return (root, query, criteriaBuilder) ->
                kartaPobytu != null ? criteriaBuilder.equal(root.get(Prison_.STREET), kartaPobytu) : criteriaBuilder.conjunction();
    }
}
