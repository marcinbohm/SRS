package com.srs.service.application;

import com.srs.OperationStatus;
import com.srs.dto.ApplicationDTO;
import com.srs.entity.Application;
import com.srs.repository.filters.ApplicationFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationService {

    ApplicationDTO getApplicationById(Integer applicationId);

    List<ApplicationDTO> getAllApplications();

    List<ApplicationDTO> getApplicationsByFilter(ApplicationFilter applicationFilter);

    OperationStatus upsert(Application application);

    OperationStatus delete(Integer applicationId);
}
