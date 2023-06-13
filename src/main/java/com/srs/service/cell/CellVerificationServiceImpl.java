package com.srs.service.cell;

import com.srs.VerificationStatus;
import com.srs.entity.Cell;
import com.srs.exception.VerificationStatusException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CellVerificationServiceImpl implements CellVerificationService {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Override
    public VerificationStatus verifyCell(Cell cell) {

        VerificationStatus status = new VerificationStatus()
                .setAccepted(true);

        try {
            if (cell.getAssignDate() != null
                    && cell.getUnassignDate() != null)
                assignDateBeforeEndDate(cell.getAssignDate(), cell.getUnassignDate());
        } catch (VerificationStatusException exception) {
            verificationStatusExceptionLoggerAndStatusFalse(status, exception);
        }

        return status;
    }

    private void assignDateBeforeEndDate(LocalDate assignDate, LocalDate unassignDate) {
        if (assignDate.isAfter(unassignDate))
            throw new VerificationStatusException("Data przypisania nie może być po dacie wypisu!");
    }

    private void verificationStatusExceptionLoggerAndStatusFalse(VerificationStatus status, VerificationStatusException exception) {
        logger.error("Cell validation error: " + exception.getMessage());
        status.setAccepted(false).setMessage(exception.getMessage());
    }
}
