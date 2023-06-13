package com.srs.service.user.registration;

import com.srs.VerificationStatus;
import com.srs.service.user.registration.model.RegistrationDTO;

public interface RegistrationVerificationService {
    VerificationStatus verifyUserRegistration(RegistrationDTO registrationDTO);

    void checkIfLoginExistsInDb(String login);
    void isStrongPassword(String password);
}
