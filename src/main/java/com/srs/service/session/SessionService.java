package com.srs.service.session;


import com.srs.entity.Session;

import java.util.Optional;

public interface SessionService {

    Optional<Session> findByToken(String token);

    Optional<Session> findByUserId(Integer userId);

    <S extends Session> S save(S session);

    void delete(Session session);
}
