package com.srs.repository;

import com.srs.entity.Prison;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrisonRepository extends JpaRepository<Prison, Integer>, JpaSpecificationExecutor<Prison> {
}
