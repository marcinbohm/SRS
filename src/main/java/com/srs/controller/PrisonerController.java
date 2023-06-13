package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.entity.Prisoner;
import com.srs.repository.filters.PrisonerFilter;
import com.srs.dto.PrisonerDTO;
import com.srs.service.prisoner.PrisonerService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/prisoners")
public class PrisonerController {

    private final PrisonerService prisonerService;

    public PrisonerController(PrisonerService prisonerService) {
        this.prisonerService = prisonerService;
    }

    @GetMapping
    public ResponseEntity<List<PrisonerDTO>> getAllPrisoners() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonerService.getAllPrisoners(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PrisonerDTO>> getPrisonersByFilter(@RequestBody PrisonerFilter prisonerFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonerService.getPrisonersByFilter(prisonerFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addPrisoner(@RequestBody  @DTO(PrisonerDTO.class) Prisoner prisoner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        prisoner.setPrisonerId(null);
        OperationStatus status = prisonerService.upsert(prisoner);
        HttpStatus httpStatus = HttpStatus.OK;

        if (!status.getSuccess() && !status.getMsgList().isEmpty())
            httpStatus = HttpStatus.NOT_ACCEPTABLE;

        return new ResponseEntity<>(
                status,
                httpHeaders,
                httpStatus);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updatePrisoner(@RequestBody  @DTO(PrisonerDTO.class) Prisoner prisoner) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        OperationStatus status = prisonerService.upsert(prisoner);
        HttpStatus httpStatus = HttpStatus.OK;

        if (!status.getSuccess() && !status.getMsgList().isEmpty())
            httpStatus = HttpStatus.NOT_ACCEPTABLE;

        return new ResponseEntity<>(
                status,
                httpHeaders,
                httpStatus);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deletePrisoner(@PathVariable("id") Integer prisonerId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonerService.delete(prisonerId),
                httpHeaders,
                HttpStatus.OK);
    }
}
