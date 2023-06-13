package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.UUID;

@Entity
@Table(name = "permission")
@EntityListeners(AuditListener.class)
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permission_generator")
    @SequenceGenerator(name="permission_generator", sequenceName = "permission_permission_id_seq", allocationSize=1)
    @Column(name = "permission_id")
    private Integer permissionId;

    @NotNull
    @Column(name = "category_id")
    private Integer categoryId;

    @NotNull
    @Column(name = "allow_read")
    private Boolean allowRead;

    @NotNull
    @Column(name = "allow_add")
    private Boolean allowAdd;

    @NotNull
    @Column(name = "allow_modify")
    private Boolean allowModify;

    @NotNull
    @Column(name = "allow_delete")
    private Boolean allowDelete;

    @NotNull
    @Column(name = "class_id")
    private Integer classId;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "category_id",
            insertable = false,
            updatable = false
    )
    private Category category;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "class_id",
            insertable = false,
            updatable = false
    )
    private Role permissionRole;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getAllowRead() {
        return allowRead;
    }

    public void setAllowRead(Boolean allowRead) {
        this.allowRead = allowRead;
    }

    public Boolean getAllowAdd() {
        return allowAdd;
    }

    public void setAllowAdd(Boolean allowAdd) {
        this.allowAdd = allowAdd;
    }

    public Boolean getAllowModify() {
        return allowModify;
    }

    public void setAllowModify(Boolean allowModify) {
        this.allowModify = allowModify;
    }

    public Boolean getAllowDelete() {
        return allowDelete;
    }

    public void setAllowDelete(Boolean allowDelete) {
        this.allowDelete = allowDelete;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Role getPermissionClass() {
        return permissionRole;
    }

    public void setPermissionClass(Role permissionRole) {
        this.permissionRole = permissionRole;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getPermissionId() != null) {
                this.localId = Permission.class.getSimpleName() + this.getPermissionId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Permission that = (Permission)other;
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
