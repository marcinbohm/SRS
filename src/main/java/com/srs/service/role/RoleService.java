package com.srs.service.role;

import com.srs.OperationStatus;
import com.srs.dto.RoleDTO;
import com.srs.entity.Role;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.RoleFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface RoleService {

    RoleDTO getRoleById(Integer roleId);

    List<RoleDTO> getAllRoles();

    List<RoleDTO> getRolesByFilter(RoleFilter roleFilter);

    OperationStatus upsert(Role role);

    OperationStatus delete(Integer roleId);
}
