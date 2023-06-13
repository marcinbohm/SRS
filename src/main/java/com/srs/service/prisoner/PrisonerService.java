package com.srs.service.prisoner;

import com.srs.OperationStatus;
import com.srs.dto.PrisonerDTO;
import com.srs.entity.Prisoner;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PrisonerFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface PrisonerService {

    PrisonerDTO getPrisonerById(Integer prisonerId);

    List<PrisonerDTO> getAllPrisoners();

    List<PrisonerDTO> getPrisonersByFilter(PrisonerFilter prisonerFilter);

    OperationStatus upsert(Prisoner prisoner);

    OperationStatus delete(Integer prisonerId);
}
