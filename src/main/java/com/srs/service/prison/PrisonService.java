package com.srs.service.prison;

import com.srs.OperationStatus;
import com.srs.dto.PrisonDTO;
import com.srs.dto.SentencePrisonerDTO;
import com.srs.entity.Prison;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PrisonFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface PrisonService {

    PrisonDTO getPrisonById(Integer prisonId);

    List<PrisonDTO> getAllPrisons();

    List<PrisonDTO> getPrisonsByFilter(PrisonFilter prisonFilter);

    OperationStatus upsert(Prison prison);

    OperationStatus delete(Integer prisonId);

    List<SentencePrisonerDTO> getPrisonSentencesWithPrisonerData(Integer prisonId);
}
