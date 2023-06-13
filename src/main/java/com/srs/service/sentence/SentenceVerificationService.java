package com.srs.service.sentence;

import com.srs.VerificationStatus;
import com.srs.entity.Sentence;

public interface SentenceVerificationService {

    VerificationStatus verifySentence(Sentence sentence);
}
