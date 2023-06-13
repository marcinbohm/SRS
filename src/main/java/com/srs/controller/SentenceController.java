package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.dto.SentencePrisonerDTO;
import com.srs.entity.Sentence;
import com.srs.repository.filters.SentenceFilter;
import com.srs.dto.SentenceDTO;
import com.srs.service.sentence.SentenceService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/sentences")
public class SentenceController {

    private final SentenceService sentenceService;

    public SentenceController(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping
    public ResponseEntity<List<SentenceDTO>> getAllSentences() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                sentenceService.getAllSentences(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<SentenceDTO>> getSentencesByFilter(@RequestBody SentenceFilter sentenceFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                sentenceService.getSentencesByFilter(sentenceFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addSentence(@RequestBody  @DTO(SentenceDTO.class) Sentence sentence) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        sentence.setSentenceId(null);
        return new ResponseEntity<>(
                sentenceService.upsert(sentence),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateSentence(@RequestBody  @DTO(SentenceDTO.class) Sentence sentence) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                sentenceService.upsert(sentence),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteSentence(@PathVariable("id") Integer sentenceId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                sentenceService.delete(sentenceId),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/set/sentence/{sentenceId}/cell/{cellId}")
    public ResponseEntity<OperationStatus> setSentenceCell(@PathVariable("cellId") Integer cellId, @PathVariable("sentenceId") Integer sentenceId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                sentenceService.setSentenceCell(sentenceId, cellId),
                httpHeaders,
                HttpStatus.OK);
    }
}
