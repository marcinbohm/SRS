package com.srs.service.prisoner;

import com.srs.VerificationStatus;
import com.srs.entity.Cell;
import com.srs.entity.Prisoner;
import com.srs.exception.VerificationStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PrisonerVerificationServiceImpl implements PrisonerVerificationService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public VerificationStatus verifyPrisoner(Prisoner prisoner) {

        VerificationStatus status = new VerificationStatus()
                .setAccepted(true);

        try {
            peselVerifier(prisoner.getPesel());
        } catch (VerificationStatusException exception) {
            verificationStatusExceptionLoggerAndStatusFalse(status, exception);
        }

        return status;
    }

    private void peselVerifier(String pesel) {
        if (!pesel.matches("\\b\\d{11}\\b"))
            throw new VerificationStatusException("Pesel jest w złym formacie lub jest za długi!");
    }

    private void verificationStatusExceptionLoggerAndStatusFalse(VerificationStatus status, VerificationStatusException exception) {
        logger.error("Cell validation error: " + exception.getMessage());
        status.setAccepted(false).setMessage(exception.getMessage());
    }
}
