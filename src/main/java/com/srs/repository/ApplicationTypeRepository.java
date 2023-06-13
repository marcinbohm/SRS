package com.srs.repository;

import com.srs.entity.ApplicationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApplicationTypeRepository extends JpaRepository<ApplicationType, Integer>, JpaSpecificationExecutor<ApplicationType> {
}
