package com.srs.service.shift;

import com.srs.VerificationStatus;
import com.srs.entity.Shift;

import java.time.LocalDateTime;

public interface ShiftVerificationService {

    VerificationStatus verifyShift(Shift shift);
}
