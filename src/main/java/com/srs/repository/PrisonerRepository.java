package com.srs.repository;

import com.srs.entity.Prisoner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrisonerRepository extends JpaRepository<Prisoner, Integer>, JpaSpecificationExecutor<Prisoner> {
}
