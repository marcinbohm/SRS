package com.srs.security.auth;

import com.srs.security.domain.AuthSession;
import com.srs.security.domain.CurrentUser;
import com.srs.security.domain.RefreshToken;

public interface AuthService {

    AuthSession login(String userName, String password);

    AuthSession refreshToken(RefreshToken refreshToken);

    void logout();
}
