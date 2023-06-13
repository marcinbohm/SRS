package com.srs.controller;

import com.srs.OperationStatus;
import com.srs.entity.Role;
import com.srs.repository.filters.RoleFilter;
import com.srs.dto.RoleDTO;
import com.srs.service.role.RoleService;
import com.srs.util.DTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                roleService.getAllRoles(),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<RoleDTO>> getRolesByFilter(@RequestBody RoleFilter roleFilter) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                roleService.getRolesByFilter(roleFilter),
                httpHeaders,
                HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<OperationStatus> addRole(@RequestBody  @DTO(RoleDTO.class) Role role) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        role.setRoleId(null);
        return new ResponseEntity<>(
                roleService.upsert(role),
                httpHeaders,
                HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<OperationStatus> updateRole(@RequestBody  @DTO(RoleDTO.class) Role role) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                roleService.upsert(role),
                httpHeaders,
                HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<OperationStatus> deleteRole(@PathVariable("id") Integer roleId) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        return new ResponseEntity<>(
                roleService.delete(roleId),
                httpHeaders,
                HttpStatus.OK);
    }
}
