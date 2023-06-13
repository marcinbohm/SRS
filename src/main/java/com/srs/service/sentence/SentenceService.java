package com.srs.service.sentence;

import com.srs.OperationStatus;
import com.srs.dto.SentenceDTO;
import com.srs.dto.SentencePrisonerDTO;
import com.srs.entity.Sentence;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.SentenceFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface SentenceService {
    
    SentenceDTO getSentenceById(Integer sentenceId);

    List<SentenceDTO> getAllSentences();

    List<SentenceDTO> getSentencesByFilter(SentenceFilter sentenceFilter);

    OperationStatus upsert(Sentence sentence);

    OperationStatus delete(Integer sentenceId);

    OperationStatus setSentenceCell(Integer sentenceId, Integer cellId);
}
