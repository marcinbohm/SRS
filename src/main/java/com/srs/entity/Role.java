package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "role")
@EntityListeners(AuditListener.class)
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name="role_generator", sequenceName = "role_role_id_seq", allocationSize=1)
    @Column(name = "role_id")
    private Integer roleId;

    @NotNull
    @Column(name = "role_code")
    private String roleCode;

    @NotNull
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean adminClass;

    @NotNull
    @Column(name = "admin_class")
    private Boolean active;

    @OneToMany(mappedBy = "permissionRole", fetch = FetchType.LAZY)
    private List<Permission> classPermissionList;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = { @JoinColumn(name = "role_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> roleUserSet;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public Boolean getAdminClass() {
        return adminClass;
    }

    public void setAdminClass(Boolean adminClass) {
        this.adminClass = adminClass;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Permission> getClassPermissionList() {
        return classPermissionList;
    }

    public void setClassPermissionList(List<Permission> classPermissionList) {
        this.classPermissionList = classPermissionList;
    }

    public Set<User> getRoleUserSet() {
        return roleUserSet;
    }

    public void setRoleUserSet(Set<User> roleUserSet) {
        this.roleUserSet = roleUserSet;
    }


    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getRoleId() != null) {
                this.localId = Role.class.getSimpleName() + this.getRoleId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Role that = (Role)other;
            this.lazyInitLocalId();
            that.lazyInitLocalId();
            return this.localId.equals(that.localId);
        } else {
            return false;
        }
    }

    public int hashCode() {
        this.lazyInitLocalId();
        return this.localId.hashCode();
    }

    public Object fieldGet(Field f) throws IllegalAccessException {
        return f.get(this);
    }
}
