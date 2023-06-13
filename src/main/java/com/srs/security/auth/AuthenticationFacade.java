package com.srs.security.auth;

import com.srs.security.domain.CurrentUser;

public interface AuthenticationFacade {
    boolean isCurrentUserAuthenticated();

    CurrentUser getCurrentUser();
}
