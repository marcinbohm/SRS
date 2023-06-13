package com.srs.repository.specs;

import com.srs.entity.Application;
import com.srs.entity.Application_;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.List;

public class ApplicationSpecs {

    public static Specification<Application> applicationIdIn(List<Integer> applicationIdList) {
        return (root, query, criteriaBuilder) -> {
            if (applicationIdList != null && !applicationIdList.isEmpty()) {
                Expression<String> exp = root.get(Application_.APPLICATION_ID);
                return exp.in(applicationIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }
}
