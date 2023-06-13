package com.srs.service.department;

import com.srs.OperationStatus;
import com.srs.dto.DepartmentDTO;
import com.srs.entity.Department;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.DepartmentFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface DepartmentService {

    DepartmentDTO getDepartmentById(Integer departmentId);

    List<DepartmentDTO> getAllDepartments();

    List<DepartmentDTO> getDepartmentsByFilter(DepartmentFilter departmentFilter);

    OperationStatus upsert(Department department);

    OperationStatus delete(Integer departmentId);
}
