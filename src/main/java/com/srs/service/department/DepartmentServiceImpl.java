package com.srs.service.department;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.DepartmentDTO;
import com.srs.entity.Application;
import com.srs.entity.Department;
import com.srs.entity.Department;
import com.srs.entity.Department;
import com.srs.mapper.SmartMapper;
import com.srs.repository.DepartmentRepository;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.DepartmentFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final ModelMapper modelMapper;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public DepartmentDTO getDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .map(department -> this.modelMapper.map(department, DepartmentDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(department -> this.modelMapper.map(department, DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<DepartmentDTO> getDepartmentsByFilter(DepartmentFilter departmentFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(Department department) {
        boolean adding = (department.getDepartmentId() == null);

        Department departmentExisting =
                (adding ? new Department() : departmentRepository.findById(department.getDepartmentId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + department.getDepartmentId())));

        OperationStatus opStatus =
                new OperationStatus(Department.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(department, departmentExisting);

        Department departmentSaved =
                departmentRepository.save(department);
        Integer id = departmentSaved.getDepartmentId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer departmentId) {

        Department department =
                departmentRepository
                        .findById(departmentId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Department.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(departmentId);
        departmentRepository.delete(department);

        return opStatus.setSuccess(!departmentRepository.existsById(departmentId));
    }
}
