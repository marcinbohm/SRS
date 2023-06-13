package com.srs.service.sentence;

import com.srs.VerificationStatus;
import com.srs.entity.Sentence;
import com.srs.exception.VerificationStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SentenceVerificationServiceImpl implements SentenceVerificationService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public VerificationStatus verifySentence(Sentence sentence) {

        VerificationStatus status = new VerificationStatus()
                .setAccepted(true);

        try {
            startDateBeforeEndDate(sentence.getStartDate(), sentence.getEndDate());
            if (sentence.getAssignDate() != null
                    && sentence.getUnassignDate() != null)
                assignDateBeforeEndDate(sentence.getAssignDate(), sentence.getUnassignDate());
        } catch (VerificationStatusException exception) {
            verificationStatusExceptionLoggerAndStatusFalse(status, exception);
        }

        return status;
    }

    private void assignDateBeforeEndDate(LocalDate assignDate, LocalDate unassignDate) {
        if (assignDate.isAfter(unassignDate))
            throw new VerificationStatusException("Data przypisania nie może być po dacie wypisu!");
    }

    private void startDateBeforeEndDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate))
            throw new VerificationStatusException("Data początku nie może być po dacie końca!");
    }

    private void verificationStatusExceptionLoggerAndStatusFalse(VerificationStatus status, VerificationStatusException exception) {
        logger.error("Sentence validation error: " + exception.getMessage());
        status.setAccepted(false).setMessage(exception.getMessage());
    }
}
