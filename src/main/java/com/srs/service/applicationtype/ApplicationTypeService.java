package com.srs.service.applicationtype;

import com.srs.OperationStatus;
import com.srs.dto.ApplicationTypeDTO;
import com.srs.entity.ApplicationType;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationTypeService {

    ApplicationTypeDTO getApplicationTypeById(Integer applicationTypeId);

    List<ApplicationTypeDTO> getAllApplicationTypes();

    List<ApplicationTypeDTO> getApplicationTypesByFilter(LocalDateTime startDate, LocalDateTime endDate);

    OperationStatus upsert(ApplicationType applicationType);

    OperationStatus delete(Integer applicationTypeId);
}
