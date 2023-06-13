package com.srs.service.user.registration;

import com.srs.VerificationStatus;
import com.srs.entity.User;
import com.srs.exception.VerificationStatusException;
import com.srs.repository.UserRepository;
import com.srs.service.user.registration.model.RegistrationDTO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationVerificationServiceImpl implements RegistrationVerificationService {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final UserRepository userRepository;

    public RegistrationVerificationServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public VerificationStatus verifyUserRegistration(RegistrationDTO registrationDTO) {

        VerificationStatus status = new VerificationStatus()
                .setAccepted(true);

        try {
            checkIfLoginExistsInDb(registrationDTO.getLogin());
            isStrongPassword(registrationDTO.getPassword());
        } catch (VerificationStatusException e) {
            logger.error("User registration verification not passed!");
            status.setAccepted(false).setMessage(e.getMessage());
        }

        return status;
    }

    public void checkIfLoginExistsInDb(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        if (user.isPresent())
            throw new VerificationStatusException("User with provided login exists in database!");
    }

    public void isStrongPassword(String password) {

        if (password == null)
            return;

        String atLeastOneDigit = "(?=.*[0-9])";
        String atLeastOneLowerCaseLetter = "(?=.*[a-z])";
        String atLeastOneUpperCaseLetter = "(?=.*[A-Z])";
        String atLeastOneSpecialCharacter = "(?=.*[+=!@#$%^&*(){}\\[\\]\\|:\";'<>?,./])";
        String noWhitespaceAllowed = "(?=\\S+$)";
        String atLeastEightCharacter = ".{8,}";

        String pattern = "^" +
                atLeastOneDigit +
                atLeastOneLowerCaseLetter +
                atLeastOneUpperCaseLetter +
                atLeastOneSpecialCharacter +
                noWhitespaceAllowed +
                atLeastEightCharacter +
                "$";


        if (!password.matches(pattern))
            throw new VerificationStatusException("Hasło nie jest wystarczająco trudne!");
    }
}
