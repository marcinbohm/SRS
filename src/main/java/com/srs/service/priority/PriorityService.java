package com.srs.service.priority;

import com.srs.OperationStatus;
import com.srs.dto.PriorityDTO;
import com.srs.entity.Priority;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PriorityFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface PriorityService {

    PriorityDTO getPriorityById(Integer priorityId);

    List<PriorityDTO> getAllPrioritys();

    List<PriorityDTO> getPrioritysByFilter(PriorityFilter priorityFilter);

    OperationStatus upsert(Priority priority);

    OperationStatus delete(Integer priorityId);
}
