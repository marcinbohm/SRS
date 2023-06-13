package com.srs.service.user.registration;

import com.srs.OperationStatus;
import com.srs.service.user.registration.model.RegistrationDTO;

public interface RegistrationService {
    OperationStatus registerUser(RegistrationDTO registrationDTO);
}
