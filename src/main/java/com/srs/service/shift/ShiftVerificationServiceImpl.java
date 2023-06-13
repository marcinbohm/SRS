package com.srs.service.shift;

import com.srs.VerificationStatus;
import com.srs.entity.Shift;
import com.srs.exception.VerificationStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ShiftVerificationServiceImpl implements ShiftVerificationService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public VerificationStatus verifyShift(Shift shift) {

        VerificationStatus status = new VerificationStatus()
                .setAccepted(true);

        try {
            startDateBeforeEndDate(shift.getStartDate(), shift.getEndDate());
        } catch (VerificationStatusException exception) {
            verificationStatusExceptionLoggerAndStatusFalse(status, exception);
        }

        return status;
    }

    private void startDateBeforeEndDate(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate))
            throw new VerificationStatusException("Data początku nie może być po dacie końca!");
    }

    private void verificationStatusExceptionLoggerAndStatusFalse(VerificationStatus status, VerificationStatusException exception) {
        logger.error("Shift validation error: " + exception.getMessage());
        status.setAccepted(false).setMessage(exception.getMessage());
    }
}
