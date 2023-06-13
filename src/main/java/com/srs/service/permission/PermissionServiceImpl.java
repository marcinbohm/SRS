package com.srs.service.permission;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.PermissionDTO;
import com.srs.entity.Application;
import com.srs.entity.Permission;
import com.srs.entity.Permission;
import com.srs.mapper.SmartMapper;
import com.srs.repository.PermissionRepository;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.PermissionFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final ModelMapper modelMapper;

    public PermissionServiceImpl(PermissionRepository permissionRepository, ModelMapper modelMapper) {
        this.permissionRepository = permissionRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PermissionDTO getPermissionById(Integer permissionId) {
        return permissionRepository.findById(permissionId)
                .map(permission -> this.modelMapper.map(permission, PermissionDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<PermissionDTO> getAllPermissions() {
        return permissionRepository.findAll()
                .stream()
                .map(permission -> this.modelMapper.map(permission, PermissionDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<PermissionDTO> getPermissionsByFilter(PermissionFilter permissionFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(Permission permission) {
        boolean adding = (permission.getPermissionId() == null);

        Permission permissionExisting =
                (adding ? new Permission() : permissionRepository.findById(permission.getPermissionId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + permission.getPermissionId())));

        OperationStatus opStatus =
                new OperationStatus(Permission.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(permission, permissionExisting);

        Permission permissionSaved =
                permissionRepository.save(permission);
        Integer id = permissionSaved.getPermissionId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer permissionId) {

        Permission permission =
                permissionRepository
                        .findById(permissionId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Permission.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(permissionId);
        permissionRepository.delete(permission);

        return opStatus.setSuccess(!permissionRepository.existsById(permissionId));
    }
}
