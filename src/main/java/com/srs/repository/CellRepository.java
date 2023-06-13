package com.srs.repository;

import com.srs.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CellRepository extends JpaRepository<Cell, Integer>, JpaSpecificationExecutor<Cell> {
}
