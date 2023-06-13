package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "category")
@EntityListeners(AuditListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name="archiwum_kontakt_generator", sequenceName = "category_category_id_seq", allocationSize=1)
    @Column(name = "category_id")
    private Integer categoryId;

    @NotNull
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "help_description")
    private String helpDescription;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<Permission> categoryPermissionsList;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpDescription() {
        return helpDescription;
    }

    public void setHelpDescription(String helpDescription) {
        this.helpDescription = helpDescription;
    }

    public List<Permission> getCategoryPermissionsList() {
        return categoryPermissionsList;
    }

    public void setCategoryPermissionsList(List<Permission> categoryPermissionsList) {
        this.categoryPermissionsList = categoryPermissionsList;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getCategoryId() != null) {
                this.localId = Category.class.getSimpleName() + this.getCategoryId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Category that = (Category)other;
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
