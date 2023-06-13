package com.srs.repository;

import com.srs.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {
    Optional<User> findByUserId(Integer userId);

    Optional<User> findByLogin(String login);

    Optional<User> findByUserIdAndUserSessionsList_RefreshTicket(Integer userId, String refreshTicket);

    List<User> findByUserTypeIsNot(Integer userType);

}
