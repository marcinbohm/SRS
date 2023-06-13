package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.dto.UserDTO;
import com.srs.dto.UserShiftDTO;
import com.srs.entity.User;
import com.srs.repository.filters.UserFilter;
import com.srs.service.user.UserService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.getAllUsers(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<UserDTO>> getUsersByFilter(@RequestBody UserFilter userFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.getUsersByFilter(userFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addUser(@RequestBody  @DTO(UserDTO.class) User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        user.setUserId(null);
        return new ResponseEntity<>(
                userService.upsert(user),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateUser(@RequestBody  @DTO(UserDTO.class) User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.upsert(user),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteUser(@PathVariable("id") Integer userId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.delete(userId),
                httpHeaders,
                HttpStatus.OK);
    }

    @GetMapping("/logged/shifts")
    public ResponseEntity<List<UserShiftDTO>> getLoggedUserShifts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.getLoggedUserShifts(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/set/user/{userId}/shift/{shiftId}")
    public ResponseEntity<OperationStatus> setSentenceCell(@PathVariable("userId") Integer userId, @PathVariable("shiftId") Integer shiftId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                userService.setUserShift(userId, shiftId),
                httpHeaders,
                HttpStatus.OK);
    }
}