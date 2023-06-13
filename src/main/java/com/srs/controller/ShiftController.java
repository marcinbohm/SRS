package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.entity.Shift;
import com.srs.repository.filters.ShiftFilter;
import com.srs.dto.ShiftDTO;
import com.srs.service.shift.ShiftService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/shifts")
public class ShiftController {

    private final ShiftService shiftService;

    public ShiftController(ShiftService shiftService) {
        this.shiftService = shiftService;
    }

    @GetMapping
    public ResponseEntity<List<ShiftDTO>> getAllShifts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                shiftService.getAllShifts(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ShiftDTO>> getShiftsByFilter(@RequestBody ShiftFilter shiftFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                shiftService.getShiftsByFilter(shiftFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addShift(@RequestBody  @DTO(ShiftDTO.class) Shift shift) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        shift.setShiftId(null);
        return new ResponseEntity<>(
                shiftService.upsert(shift),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateShift(@RequestBody  @DTO(ShiftDTO.class) Shift shift) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                shiftService.upsert(shift),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteShift(@PathVariable("id") Integer shiftId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                shiftService.delete(shiftId),
                httpHeaders,
                HttpStatus.OK);
    }
}
