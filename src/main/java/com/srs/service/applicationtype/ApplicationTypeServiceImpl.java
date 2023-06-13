package com.srs.service.applicationtype;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.ApplicationTypeDTO;
import com.srs.entity.Application;
import com.srs.entity.ApplicationType;
import com.srs.entity.ApplicationType;
import com.srs.mapper.SmartMapper;
import com.srs.repository.ApplicationTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class ApplicationTypeServiceImpl implements ApplicationTypeService {

   private final ApplicationTypeRepository applicationTypeRepository;
   private final ModelMapper modelMapper;

    public ApplicationTypeServiceImpl(ApplicationTypeRepository applicationTypeRepository, ModelMapper modelMapper) {
        this.applicationTypeRepository = applicationTypeRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @PreAuthorize("hasAuthority('APPLICATION_TYPE_READ_PRIVILEGE')")
    public ApplicationTypeDTO getApplicationTypeById(Integer applicationTypeId) {
        return applicationTypeRepository.findById(applicationTypeId)
                .map(applicationType -> this.modelMapper.map(applicationType, ApplicationTypeDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @PreAuthorize("hasAuthority('APPLICATION_TYPE_READ_PRIVILEGE')")
    public List<ApplicationTypeDTO> getAllApplicationTypes() {
        return applicationTypeRepository.findAll()
                .stream()
                .map(applicationType -> this.modelMapper.map(applicationType, ApplicationTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    @PreAuthorize("hasAuthority('APPLICATION_TYPE_READ_PRIVILEGE')")
    public List<ApplicationTypeDTO> getApplicationTypesByFilter(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('APPLICATION_TYPE_ADD_PRIVILEGE') and hasAuthority('APPLICATION_TYPE_MODIFY_PRIVILEGE')")
    public OperationStatus upsert(ApplicationType applicationType) {
        boolean adding = (applicationType.getApplicationTypeId() == null);

        ApplicationType applicationTypeExisting =
                (adding ? new ApplicationType() : applicationTypeRepository.findById(applicationType.getApplicationTypeId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + applicationType.getApplicationTypeId())));

        OperationStatus opStatus =
                new OperationStatus(ApplicationType.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(applicationType, applicationTypeExisting);

        ApplicationType applicationTypeSaved =
                applicationTypeRepository.save(applicationType);
        Integer id = applicationTypeSaved.getApplicationTypeId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    @Transactional
    @PreAuthorize("hasAuthority('APPLICATION_TYPE_DELETE_PRIVILEGE')")
    public OperationStatus delete(Integer applicationTypeId) {

        ApplicationType applicationType =
                applicationTypeRepository
                        .findById(applicationTypeId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        ApplicationType.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(applicationTypeId);
        applicationTypeRepository.delete(applicationType);

        return opStatus.setSuccess(!applicationTypeRepository.existsById(applicationTypeId));
    }
}
