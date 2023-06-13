package com.srs.service.permission;

import com.srs.OperationStatus;
import com.srs.dto.PermissionDTO;
import com.srs.entity.Permission;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PermissionFilter;

import java.time.LocalDateTime;
import java.util.List;

public interface PermissionService {


    PermissionDTO getPermissionById(Integer permissionId);

    List<PermissionDTO> getAllPermissions();

    List<PermissionDTO> getPermissionsByFilter(PermissionFilter permissionFilter);

    OperationStatus upsert(Permission permission);

    OperationStatus delete(Integer permissionId);
}
