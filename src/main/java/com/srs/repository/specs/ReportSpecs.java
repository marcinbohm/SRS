package com.srs.repository.specs;

import com.srs.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.SetJoin;
import java.util.List;

public class ReportSpecs {

    public static Specification<Report> userTypeEquals(Integer userType) {
        return (root, query, criteriaBuilder) -> {
            if (userType != null && userType > 0) {
                Join<Report, Shift> reportShiftJoin = root.join(Report_.SHIFT, JoinType.INNER);
                SetJoin<Shift, User> shiftUserJoin = reportShiftJoin.join(Shift_.shiftUserSet);
                return criteriaBuilder.equal(shiftUserJoin.get(User_.USER_TYPE), userType);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Report> userIdIn(List<Integer> userIdList) {
        return (root, query, criteriaBuilder) -> {
            if (userIdList != null && !userIdList.isEmpty()) {
                Join<Report, Shift> reportShiftJoin = root.join(Report_.SHIFT, JoinType.INNER);
                SetJoin<Shift, User> shiftUserJoin = reportShiftJoin.join(Shift_.shiftUserSet);
                Expression<String> exp = shiftUserJoin.get(User_.USER_ID);
                return exp.in(userIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }
}
