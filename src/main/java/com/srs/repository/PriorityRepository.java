package com.srs.repository;

import com.srs.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PriorityRepository extends JpaRepository<Priority, Integer>, JpaSpecificationExecutor<Priority> {
}
