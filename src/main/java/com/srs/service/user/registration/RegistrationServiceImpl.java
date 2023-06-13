package com.srs.service.user.registration;

import com.srs.OperationStatus;
import com.srs.VerificationStatus;
import com.srs.dict.DictOperationName;
import com.srs.entity.User;
import com.srs.repository.UserRepository;
import com.srs.security.auth.AuthenticationFacade;
import com.srs.service.user.registration.model.RegistrationDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class RegistrationServiceImpl implements RegistrationService {

    private final UserRepository userRepository;
    private final RegistrationVerificationService registrationVerificationService;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    public RegistrationServiceImpl(UserRepository userRepository,
                                   RegistrationVerificationService registrationVerificationService,
                                   PasswordEncoder passwordEncoder, AuthenticationFacade authenticationFacade) {
        this.userRepository = userRepository;
        this.registrationVerificationService = registrationVerificationService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public OperationStatus registerUser(RegistrationDTO registrationDTO) {

        OperationStatus operationStatus = new OperationStatus()
                .setOperationName(DictOperationName.ADD.getCode())
                .setTargetClassName(User.class.getSimpleName());

        VerificationStatus verificationStatus = registrationVerificationService
                .verifyUserRegistration(registrationDTO);

        if (!verificationStatus.isAccepted())
            return operationStatus.setSuccess(false)
                    .addMessage(verificationStatus.getMessage());
        else {
            User user = new User();

            user.setPassword(registrationDTO.getPassword());
            user.setFirstname(registrationDTO.getFirstname());
            user.setLastname(registrationDTO.getLastname());
            user.setEmail(registrationDTO.getEmail());
            user.setLogin(registrationDTO.getLogin());
            user.setPassword(passwordEncoder.encode(registrationDTO.getPassword()));
            user.setActive(true);
            user.setBlocked(false);
            user.setDepartmentId(registrationDTO.getDepartmentId());
            user.setCreatedAt(LocalDateTime.now());
            user.setUserType(registrationDTO.getUserType());
            user.setCreatedBy(authenticationFacade.getCurrentUser().getId());
            user.setExpireAccountDate(LocalDate.now().plusYears(1));
            user.setExpirePasswordDate(LocalDate.now().plusMonths(1));

            User userSaved = userRepository.save(user);
            Integer id = userSaved.getUserId();
            operationStatus.setRecordId(id).setSuccess(id != null);

            return operationStatus;
        }
    }
}
