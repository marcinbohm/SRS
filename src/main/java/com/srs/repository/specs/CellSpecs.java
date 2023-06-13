package com.srs.repository.specs;

import com.srs.entity.Cell_;
import com.srs.entity.Cell;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.time.LocalDate;
import java.util.List;

public class CellSpecs {

    public static Specification<Cell> cellIdIn(List<Integer> cellIdList) {
        return (root, query, criteriaBuilder) -> {
            if (cellIdList != null && !cellIdList.isEmpty()) {
                Expression<String> exp = root.get(Cell_.CELL_ID);
                return exp.in(cellIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Cell> prisonIdIn(List<Integer> prisonIdList) {
        return (root, query, criteriaBuilder) -> {
            if (prisonIdList != null && !prisonIdList.isEmpty()) {
                Expression<String> exp = root.get(Cell_.PRISON_ID);
                return exp.in(prisonIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Cell> segmentEquals(String segment) {
        return (root, query, criteriaBuilder) ->
                segment != null ? criteriaBuilder.equal(root.get(Cell_.SEGMENT), segment) : criteriaBuilder.conjunction();
    }

    public static Specification<Cell> blokEquals(String blok) {
        return (root, query, criteriaBuilder) ->
                blok != null ? criteriaBuilder.equal(root.get(Cell_.BLOK), blok) : criteriaBuilder.conjunction();
    }

    public static Specification<Cell> assignDateLessThanOrEqualTo(LocalDate assigndateTo) {
        return (root, query, criteriaBuilder) ->
                assigndateTo != null ? criteriaBuilder.lessThanOrEqualTo(root.get(Cell_.ASSIGN_DATE), assigndateTo) : criteriaBuilder.conjunction();
    }

    public static Specification<Cell> assignDateGreaterThanOrEqualTo(LocalDate assigndateTo) {
        return (root, query, criteriaBuilder) ->
                assigndateTo != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(Cell_.ASSIGN_DATE), assigndateTo) : criteriaBuilder.conjunction();
    }

    public static Specification<Cell> unassignDateLessThanOrEqualTo(LocalDate assigndateFrom) {
        return (root, query, criteriaBuilder) ->
                assigndateFrom != null ? criteriaBuilder.lessThanOrEqualTo(root.get(Cell_.UNASSIGN_DATE), assigndateFrom) : criteriaBuilder.conjunction();
    }

    public static Specification<Cell> unassignDateGreaterThanOrEqualTo(LocalDate assigndateFrom) {
        return (root, query, criteriaBuilder) ->
                assigndateFrom != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(Cell_.UNASSIGN_DATE), assigndateFrom) : criteriaBuilder.conjunction();
    }
}
