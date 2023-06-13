package com.srs.service.prisoner;

import com.srs.VerificationStatus;
import com.srs.entity.Prisoner;

public interface PrisonerVerificationService {

    VerificationStatus verifyPrisoner(Prisoner prisoner);
}
