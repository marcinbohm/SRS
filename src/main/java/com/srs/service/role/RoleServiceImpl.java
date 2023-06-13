package com.srs.service.role;

import com.srs.OperationStatus;
import com.srs.dict.DictOperationName;
import com.srs.dto.RoleDTO;
import com.srs.entity.Application;
import com.srs.entity.Role;
import com.srs.entity.Role;
import com.srs.mapper.SmartMapper;
import com.srs.repository.RoleRepository;
import com.srs.repository.filters.CategoryFilter;
import com.srs.repository.filters.RoleFilter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Validated
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoleDTO getRoleById(Integer roleId) {
        return roleRepository.findById(roleId)
                .map(role -> this.modelMapper.map(role, RoleDTO.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        return roleRepository.findAll()
                .stream()
                .map(role -> this.modelMapper.map(role, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RoleDTO> getRolesByFilter(RoleFilter roleFilter) {
        return null;
    }

    @Override
    public OperationStatus upsert(Role role) {
        boolean adding = (role.getRoleId() == null);

        Role roleExisting =
                (adding ? new Role() : roleRepository.findById(role.getRoleId())
                        .orElseThrow(() -> new EntityNotFoundException("Nie znaleziono obiektu z Id: " + role.getRoleId())));

        OperationStatus opStatus =
                new OperationStatus(Role.class.getSimpleName(),
                        adding ? DictOperationName.ADD.getCode()
                                : DictOperationName.UPDATE.getCode());

        SmartMapper.transferData(role, roleExisting);

        Role roleSaved =
                roleRepository.save(role);
        Integer id = roleSaved.getRoleId();
        opStatus.setRecordId(id).setSuccess(id != null);
        return opStatus;
    }

    @Override
    public OperationStatus delete(Integer roleId) {

        Role role =
                roleRepository
                        .findById(roleId)
                        .orElseThrow(EntityNotFoundException::new);

        OperationStatus opStatus =
                new OperationStatus(
                        Role.class.getSimpleName(),
                        DictOperationName.DELETE.getCode()
                );

        opStatus.setRecordId(roleId);
        roleRepository.delete(role);

        return opStatus.setSuccess(!roleRepository.existsById(roleId));
    }
}
