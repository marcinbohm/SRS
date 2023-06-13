package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.dto.SentencePrisonerDTO;
import com.srs.entity.Prison;
import com.srs.repository.filters.PrisonFilter;
import com.srs.dto.PrisonDTO;
import com.srs.dto.PrisonerDTO;
import com.srs.service.prison.PrisonService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/prisons")
public class PrisonController {

    private final PrisonService prisonService;

    public PrisonController(PrisonService prisonService) {
        this.prisonService = prisonService;
    }

    @GetMapping
    public ResponseEntity<List<PrisonDTO>> getAllPrisons() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonService.getAllPrisons(),
                httpHeaders,
                HttpStatus.OK);
    }

    @GetMapping("/{prisonId}/sentencesWithPrisonerData")
    public ResponseEntity<List<SentencePrisonerDTO>> getPrisonSentencesWithPrisonerData(@PathVariable("prisonId") Integer prisonId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonService.getPrisonSentencesWithPrisonerData(prisonId),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PrisonDTO>> getPrisonsByFilter(@RequestBody PrisonFilter prisonFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonService.getPrisonsByFilter(prisonFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addPrison(@RequestBody  @DTO(PrisonerDTO.class) Prison prison) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        prison.setPrisonId(null);
        return new ResponseEntity<>(
                prisonService.upsert(prison),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updatePrison(@RequestBody  @DTO(PrisonerDTO.class) Prison prison) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonService.upsert(prison),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deletePrison(@PathVariable("id") Integer prisonId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                prisonService.delete(prisonId),
                httpHeaders,
                HttpStatus.OK);
    }
}
