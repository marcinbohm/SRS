package com.srs.repository;

import com.srs.dto.Privilege;
import com.srs.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Integer>, JpaSpecificationExecutor<Permission> {

    @Query("SELECT new com.srs.dto.Privilege(lc.code, true, true, true, true) FROM Category lc")
    List<Privilege> findAllAdminPrivileges();

    List<Permission> findByPermissionRole_RoleUserSet_UserId(Integer userId);

}
