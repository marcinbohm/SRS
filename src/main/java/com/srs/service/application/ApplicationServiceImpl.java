package com.srs.service.application;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.ApplicationDTO;
import com.srs.entity.Application;
import com.srs.entity.Application;
import com.srs.mapper.SmartMapper;
import com.srs.repository.ApplicationRepository;
import com.srs.repository.filters.ApplicationFilter;
import com.srs.repository.specs.ApplicationSpecs;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ApplicationServiceImpl implements ApplicationService {

    private final ApplicationRepository applicationRepository;
    private final ModelMapper modelMapper;

    public ApplicationServiceImpl(ApplicationRepository applicationRepository, ModelMapper modelMapper) {
        this.applicationRepository = applicationRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('APPLICATION_READ_PRIVILEGE')")
    public ApplicationDTO getApplicationById(Integer applicationId) {
        return applicationRepository.findById(applicationId)
                .map(application -> this.modelMapper.map(application, ApplicationDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('APPLICATION_READ_PRIVILEGE')")
    public List<ApplicationDTO> getAllApplications() {
        return applicationRepository.findAll()
                .stream()
                .map(application -> this.modelMapper.map(application, ApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    @PreAuthorize("hasAuthority('APPLICATION_READ_PRIVILEGE')")
    public List<ApplicationDTO> getApplicationsByFilter(ApplicationFilter applicationFilter) {
        Specification<Application> spec = Specification
                .where(ApplicationSpecs.applicationIdIn(applicationFilter.applicationIdList));

        return applicationRepository.findAll(spec)
                .stream()
                .map(application -> this.modelMapper.map(application, ApplicationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('APPLICATION_ADD_PRIVILEGE') and hasAuthority('APPLICATION_MODIFY_PRIVILEGE')")
    public OperationStatus upsert(Application application) {
        boolean adding = (application.getApplicationId() == null);

        Application applicationExisting =
                (adding ? new Application() : applicationRepository.findById(application.getApplicationId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + application.getApplicationId())));

        OperationStatus opStatus =
                new OperationStatus(Application.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(application, applicationExisting);

        Application applicationSaved = applicationRepository.save(application);
        Integer id = applicationSaved.getApplicationId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('APPLICATION_DELETE_PRIVILEGE')")
    public OperationStatus delete(Integer applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus = new OperationStatus(Application.class.getSimpleName(), DictOperationName.DELETE.getCode());
        opStatus.setRecordId(applicationId);

        applicationRepository.delete(application);

        return opStatus.setSuccess(!applicationRepository.existsById(applicationId));
    }
}
