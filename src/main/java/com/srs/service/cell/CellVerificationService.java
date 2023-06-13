package com.srs.service.cell;

import com.srs.VerificationStatus;
import com.srs.entity.Cell;
import com.srs.exception.VerificationStatusException;

public interface CellVerificationService {

    VerificationStatus verifyCell(Cell cell);
}
