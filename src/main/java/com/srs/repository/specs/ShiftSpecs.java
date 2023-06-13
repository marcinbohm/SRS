package com.srs.repository.specs;

import com.srs.entity.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.SetJoin;
import java.util.List;

public class ShiftSpecs {

    public static Specification<Shift> userTypeEquals(Integer userType) {
        return (root, query, criteriaBuilder) -> {
            if (userType != null && userType > 0) {
                SetJoin<Shift, User> shiftUserJoin = root.join(Shift_.shiftUserSet);
                return criteriaBuilder.equal(shiftUserJoin.get(User_.USER_TYPE), userType);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }

    public static Specification<Shift> userIdIn(List<Integer> userIdList) {
        return (root, query, criteriaBuilder) -> {
            if (userIdList != null && !userIdList.isEmpty()) {
                SetJoin<Shift, User> shiftUserJoin = root.join(Shift_.shiftUserSet);
                Expression<String> exp = shiftUserJoin.get(User_.USER_ID);
                return exp.in(userIdList);
            } else {
                return criteriaBuilder.conjunction();
            }
        };
    }
}
