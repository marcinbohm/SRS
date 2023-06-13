package com.srs.security.authentication;

import com.srs.entity.User;
import com.srs.security.domain.CurrentUser;

public interface CurrentUserFacade {

    CurrentUser convertToCurrentUser(User user);
}
