package com.srs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srs.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer>, JpaSpecificationExecutor<Role> {
    List<Role> findByRoleUserSet_UserId(Integer userId);
}
