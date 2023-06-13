package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.entity.Permission;
import com.srs.repository.filters.PermissionFilter;
import com.srs.dto.PermissionDTO;
import com.srs.service.permission.PermissionService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {

    private final PermissionService permissionService;

    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @GetMapping
    public ResponseEntity<List<PermissionDTO>> getAllPermissions() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                permissionService.getAllPermissions(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<PermissionDTO>> getPermissionsByFilter(@RequestBody PermissionFilter permissionFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                permissionService.getPermissionsByFilter(permissionFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addPermission(@RequestBody  @DTO(PermissionDTO.class) Permission permission) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        permission.setPermissionId(null);
        return new ResponseEntity<>(
                permissionService.upsert(permission),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updatePermission(@RequestBody  @DTO(PermissionDTO.class) Permission permission) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                permissionService.upsert(permission),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deletePermission(@PathVariable("id") Integer permissionId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                permissionService.delete(permissionId),
                httpHeaders,
                HttpStatus.OK);
    }
}
