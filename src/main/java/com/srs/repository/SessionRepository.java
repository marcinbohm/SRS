package com.srs.repository;

import com.srs.entity.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<Session, Integer>, JpaSpecificationExecutor<Session> {
    Optional<Session> findByTicket(String ticket);

    Optional<Session> findByUserId(Integer userId);
}
