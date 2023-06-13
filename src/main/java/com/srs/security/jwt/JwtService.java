package com.srs.security.jwt;

import com.srs.security.domain.CurrentUser;

import java.time.Duration;
import java.util.Optional;

public interface JwtService {

    TokenSubject parseToken(String authToken);

    String generateToken(TokenSubject tokenSubject, Duration expirationInMinutes);

    Optional<CurrentUser> loadUserByToken(String token);
}
