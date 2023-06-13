package com.srs.service.priority;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.PriorityDTO;
import com.srs.dto.PriorityDTO;
import com.srs.entity.Application;
import com.srs.entity.Priority;
import com.srs.entity.Priority;
import com.srs.entity.Priority;
import com.srs.mapper.SmartMapper;
import com.srs.repository.PriorityRepository;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PriorityFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PriorityServiceImpl implements PriorityService {

    private final PriorityRepository priorityRepository;
    private final ModelMapper modelMapper;

    public PriorityServiceImpl(PriorityRepository priorityRepository, ModelMapper modelMapper) {
        this.priorityRepository = priorityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PriorityDTO getPriorityById(Integer priorityId) {
        return priorityRepository.findById(priorityId)
                .map(priority -> this.modelMapper.map(priority, PriorityDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PriorityDTO> getAllPrioritys() {
        return priorityRepository.findAll()
                .stream()
                .map(priority -> this.modelMapper.map(priority, PriorityDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PriorityDTO> getPrioritysByFilter(PriorityFilter priorityFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(Priority priority) {
        boolean adding = (priority.getPriorityId() == null);

        Priority priorityExisting =
                (adding ? new Priority() : priorityRepository.findById(priority.getPriorityId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + priority.getPriorityId())));

        OperationStatus opStatus =
                new OperationStatus(Priority.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(priority, priorityExisting);

        Priority prioritySaved =
                priorityRepository.save(priority);
        Integer id = prioritySaved.getPriorityId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer priorityId) {

        Priority priority =
                priorityRepository
                        .findById(priorityId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Priority.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(priorityId);
        priorityRepository.delete(priority);

        return opStatus.setSuccess(!priorityRepository.existsById(priorityId));
    }
}
