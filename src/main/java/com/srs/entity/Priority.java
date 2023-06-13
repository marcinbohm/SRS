package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "priority")
@EntityListeners(AuditListener.class)
public class Priority {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "priority_generator")
    @SequenceGenerator(name="priority_generator", sequenceName = "priority_priority_id_seq", allocationSize=1)
    @Column(name = "priority_id")
    private Integer priorityId;

    @Size(min = 1, max = 45)
    @NotNull
    @Column(name = "type")
    private String type;

    @Size(min = 1, max = 300)
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "priority", fetch = FetchType.LAZY)
    private List<Report> priorityReportList;

    public String getDescription() {
        return description;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Report> getPriorityReportList() {
        return priorityReportList;
    }

    public void setPriorityReportList(List<Report> priorityReportList) {
        this.priorityReportList = priorityReportList;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getPriorityId() != null) {
                this.localId = Priority.class.getSimpleName() + this.getPriorityId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Priority that = (Priority)other;
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
