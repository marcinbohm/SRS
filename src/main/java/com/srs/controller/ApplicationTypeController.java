package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.dto.ApplicationDTO;
import com.srs.entity.ApplicationType;
import com.srs.repository.filters.ApplicationTypeFilter;
import com.srs.dto.ApplicationTypeDTO;
import com.srs.service.applicationtype.ApplicationTypeService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/applicationTypes")
public class ApplicationTypeController {

    private final ApplicationTypeService applicationTypeService;

    public ApplicationTypeController(ApplicationTypeService applicationTypeService) {
        this.applicationTypeService = applicationTypeService;
    }

    @GetMapping
    public ResponseEntity<List<ApplicationTypeDTO>> getAllApplicationTypes() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                applicationTypeService.getAllApplicationTypes(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ApplicationTypeDTO>> getApplicationTypesByFilter(@RequestBody ApplicationTypeFilter applicationTypeFilter) {
        return null;
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addApplicationType(@RequestBody @DTO(ApplicationTypeDTO.class) ApplicationType applicationType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        applicationType.setApplicationTypeId(null);
        return new ResponseEntity<>(
                applicationTypeService.upsert(applicationType),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateApplicationType(@RequestBody @DTO(ApplicationTypeDTO.class) ApplicationType applicationType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                applicationTypeService.upsert(applicationType),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteApplicationType(@PathVariable("id") Integer applicationTypeId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                applicationTypeService.delete(applicationTypeId),
                httpHeaders,
                HttpStatus.OK);
    }
}
