package com.srs.service.session;

import com.srs.entity.Session;
import com.srs.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Optional;

@Service
@Validated
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;

    public SessionServiceImpl(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Optional<Session> findByToken(String token) {
        return sessionRepository.findByTicket(token);
    }

    @Override
    public Optional<Session> findByUserId(Integer userId) {
        return sessionRepository.findByUserId(userId);
    }

    @Override
    public <S extends Session> S save(S session) {
        return sessionRepository.save(session);
    }

    @Override
    public void delete(Session session) {
        sessionRepository.delete(session);
    }
}
