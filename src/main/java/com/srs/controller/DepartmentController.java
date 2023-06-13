package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.entity.Department;
import com.srs.repository.filters.DepartmentFilter;
import com.srs.dto.DepartmentDTO;
import com.srs.service.department.DepartmentService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                departmentService.getAllDepartments(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<DepartmentDTO>> getDepartmentsByFilter(@RequestBody DepartmentFilter departmentFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                departmentService.getDepartmentsByFilter(departmentFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addDepartment(@RequestBody @DTO(DepartmentDTO.class) Department department) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        department.setDepartmentId(null);
        return new ResponseEntity<>(
                departmentService.upsert(department),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateDepartment(@RequestBody  @DTO(DepartmentDTO.class) Department department) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                departmentService.upsert(department),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteDepartment(@PathVariable("id") Integer departmentId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                departmentService.delete(departmentId),
                httpHeaders,
                HttpStatus.OK);
    }
}
