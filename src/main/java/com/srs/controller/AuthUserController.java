package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.service.user.registration.RegistrationService;
import com.srs.service.user.registration.model.RegistrationDTO;
import com.srs.security.domain.*;
import com.srs.security.auth.AuthService;
import com.srs.security.auth.AuthenticationFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.nio.charset.StandardCharsets;

@RestController
public class AuthUserController {

    private final AuthenticationFacade authenticationFacade;
    private final AuthService authUserService;
    private final RegistrationService registrationService;

    public AuthUserController(AuthenticationFacade authenticationFacade,
                              @Qualifier("authUserService") AuthService authUserService, RegistrationService registrationService) {
        this.authenticationFacade = authenticationFacade;
        this.authUserService = authUserService;
        this.registrationService = registrationService;
    }

    @PostMapping(path = "auth/loginUser")
    public ResponseEntity<AuthSession> loginUser(@RequestBody Credentials credentials) {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                this.authUserService.login(credentials.getUserName(), credentials.getPassword()),
                httpHeaders,
                HttpStatus.OK
        );
    }

    @PostMapping(path = "auth/refreshToken")
    public AuthSession refreshToken(@RequestBody RefreshToken refreshToken) {
        return this.authUserService.refreshToken(refreshToken);
    }

    @PostMapping(path = "auth/logoutUser")
    public SuccessPayload logoutUser() {
        this.authUserService.logout();
        return new SuccessPayload("User has been logged out properly");
    }

    @GetMapping(path = "auth/currentUser")
    public CurrentUser getCurrentUser() {
        return authenticationFacade.getCurrentUser();
    }

    @PostMapping("/auth/sign-up")
    public ResponseEntity<OperationStatus> registration(@RequestBody @Valid RegistrationDTO registrationDTO) {

        OperationStatus operationStatus = registrationService.registerUser(registrationDTO);
        HttpStatus httpStatus = operationStatus.getSuccess() ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(operationStatus, httpStatus);
    }
}
