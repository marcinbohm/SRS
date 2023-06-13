package com.srs.security.authentication;

import com.srs.dto.Privilege;
import com.srs.entity.User;
import com.srs.entity.Role;
import com.srs.repository.RoleRepository;
import com.srs.repository.PermissionRepository;
import com.srs.security.domain.CurrentUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CurrentUserFacadeImpl implements CurrentUserFacade {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public CurrentUserFacadeImpl(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public CurrentUser convertToCurrentUser(User user) {
        final CurrentUser currentUser = new CurrentUser();

        currentUser.setId(user.getUserId());
        currentUser.setFirstName(user.getFirstname());
        currentUser.setLastName(user.getLastname());
        currentUser.setEmail(user.getEmail());
        currentUser.setLogin(user.getLogin());
        currentUser.setPassword(user.getPassword());
        currentUser.setEnabled(user.getActive() != null ?
                user.getActive() :
                Boolean.FALSE);
        currentUser.setLocked(user.getBlocked() ? user.getBlocked() : Boolean.FALSE);
        currentUser.setAccountExpired(user.getExpireAccountDate());
        currentUser.setPasswordExpired(user.getExpirePasswordDate());
        currentUser.setLastLoginTime(user.getLastLoginTime());
        currentUser.setUserType(user.getUserType());

        List<Role> Roles = this.roleRepository.findByRoleUserSet_UserId(user.getUserId());

        currentUser.addGrantedAuthority(new SimpleGrantedAuthority("ROLE_USER"));
        Roles.stream()
                .filter(userRole -> userRole.getAdminClass() != null && userRole.getAdminClass() || StringUtils.equals("0", userRole.getRoleCode()))
                .map(lRole -> String.join("_", "ROLE", "ADMIN"))
                .map(SimpleGrantedAuthority::new)
                .forEach(currentUser::addGrantedAuthority);

        List<Privilege> privileges = currentUser.isAdmin() ?
                this.permissionRepository.findAllAdminPrivileges() :
                this.permissionRepository.findByPermissionRole_RoleUserSet_UserId(user.getUserId())
                        .stream()
                        .map(permission -> new Privilege(
                                permission.getCategory().getCode(),
                                permission.getAllowRead(),
                                permission.getAllowAdd(),
                                permission.getAllowModify(),
                                permission.getAllowDelete()))
                        .collect(Collectors.toList());
        mapPrivilegesToCurrentUser(currentUser, privileges);

        return  currentUser;
    }

    private void mapPrivilegesToCurrentUser(CurrentUser currentUser, List<Privilege> privileges) {

        privileges.stream()
                .flatMap(privilege -> {
                    List<String> authorities = new ArrayList<>();
                    if (currentUser.isAdmin()) {
                        authorities.add(String.join("_", privilege.getName().toUpperCase(), "ACCESS", "PRIVILEGE"));
                        authorities.add(String.join( "_", privilege.getName().toUpperCase(), "ADD",    "PRIVILEGE"));
                        authorities.add(String.join("_", privilege.getName().toUpperCase(), "READ",   "PRIVILEGE"));
                        authorities.add(String.join("_", privilege.getName().toUpperCase(), "MODIFY", "PRIVILEGE"));
                        authorities.add(String.join("_", privilege.getName().toUpperCase(), "DELETE", "PRIVILEGE"));

                    } else {
                        if (privilege.getAllowAdd() != null && privilege.getAllowAdd()) {
                            authorities.add(String.join("_", privilege.getName().toUpperCase(), "ADD", "PRIVILEGE"));
                        }

                        if (privilege.getAllowRead() != null && privilege.getAllowRead()) {
                            authorities.add(String.join("_", privilege.getName().toUpperCase(), "READ", "PRIVILEGE"));
                        }

                        if (privilege.getAllowModify() != null && privilege.getAllowModify()) {
                            authorities.add(String.join("_", privilege.getName().toUpperCase(), "MODIFY", "PRIVILEGE"));
                        }

                        if (privilege.getAllowDelete() != null && privilege.getAllowDelete()) {
                            authorities.add(String.join("_", privilege.getName().toUpperCase(), "DELETE", "PRIVILEGE"));
                        }
                    }

                    return authorities.stream();
                }).map(SimpleGrantedAuthority::new)
                .forEach(currentUser::addGrantedAuthority);
    }
}
