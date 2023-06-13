package com.srs.repository.specs;

import com.srs.entity.Sentence;
import com.srs.entity.Sentence_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import java.time.LocalDate;
import java.util.List;

public class SentenceSpecs {

    public static Specification<Sentence> sentenceIdIn(List<Integer> sentenceIdList) {
        return (root, query, criteriaBuilder) -> {
            if (sentenceIdList != null && !sentenceIdList.isEmpty()) {
                Expression<String> exp = root.get(Sentence_.SENTENCE_ID);
                return exp.in(sentenceIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Sentence> prisonIdIn(List<Integer> prisonIdList) {
        return (root, query, criteriaBuilder) -> {
            if (prisonIdList != null && !prisonIdList.isEmpty()) {
                Expression<String> exp = root.get(Sentence_.PRISON_ID);
                return exp.in(prisonIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }


    public static Specification<Sentence> prisonerIdIn(List<Integer> prisonerIdList) {
        return (root, query, criteriaBuilder) -> {
            if (prisonerIdList != null && !prisonerIdList.isEmpty()) {
                Expression<String> exp = root.get(Sentence_.PRISONER_ID);
                return exp.in(prisonerIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Sentence> startDateLessThanOrEqualTo(LocalDate startDateTo) {
        return (root, query, criteriaBuilder) ->
                startDateTo != null ? criteriaBuilder.lessThanOrEqualTo(root.get(Sentence_.START_DATE), startDateTo) : criteriaBuilder.conjunction();
    }

    public static Specification<Sentence> startDateGreaterThanOrEqualTo(LocalDate startDateFrom) {
        return (root, query, criteriaBuilder) ->
                startDateFrom != null ? criteriaBuilder.greaterThanOrEqualTo(root.get(Sentence_.START_DATE), startDateFrom) : criteriaBuilder.conjunction();
    }
}
