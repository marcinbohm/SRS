package com.srs.security.authentication;

import com.srs.service.user.UserService;
import com.srs.security.domain.CurrentUser;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.validation.constraints.NotNull;

public class UserDetailsServiceImpl implements UserDetailsService, MessageSourceAware {

    protected MessageSourceAccessor messages;

    private final UserService userService;
    private final CurrentUserFacade currentUserFacade;

    public UserDetailsServiceImpl(UserService userService,
                                  CurrentUserFacade currentUserFacade) {
        this.userService = userService;
        this.currentUserFacade = currentUserFacade;
    }

    @Override
    public void setMessageSource(@NotNull MessageSource messageSource) {
        this.messages = new MessageSourceAccessor(messageSource);
    }

    @Override
    public CurrentUser loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userService.findUserByLogin(username)
                .map(this.currentUserFacade::convertToCurrentUser)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                this.messages.getMessage(
                                        "AbstractUserDetailsAuthenticationProvider.badCredentials",
                                        "Bad Credentials")));
    }
}
