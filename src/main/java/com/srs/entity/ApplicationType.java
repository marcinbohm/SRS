package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "application_type")
@EntityListeners(AuditListener.class)
public class ApplicationType {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "application_type_generator")
    @SequenceGenerator(name="application_type_generator", sequenceName = "application_type_application_type_id_seq", allocationSize=1)
    @Column(name = "application_type_id")
    private Integer applicationTypeId;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    @NotNull
    private String name;

    @OneToMany(mappedBy = "typeOfApplication", fetch = FetchType.LAZY)
    private List<Application> typeOfApplicationApplicationList;

    public String getName() {
        return name;
    }

    public Integer getApplicationTypeId() {
        return applicationTypeId;
    }

    public void setApplicationTypeId(Integer applicationTypeId) {
        this.applicationTypeId = applicationTypeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Application> getTypeOfApplicationApplicationList() {
        return typeOfApplicationApplicationList;
    }

    public void setTypeOfApplicationApplicationList(List<Application> typeOfApplicationApplicationList) {
        this.typeOfApplicationApplicationList = typeOfApplicationApplicationList;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getApplicationTypeId() != null) {
                this.localId = ApplicationType.class.getSimpleName() + this.getApplicationTypeId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            ApplicationType that = (ApplicationType)other;
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
