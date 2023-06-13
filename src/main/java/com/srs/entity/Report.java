package com.srs.entity;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.UUID;

@Entity
@Table(name = "report")
@EntityListeners(AuditListener.class)
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_generator")
    @SequenceGenerator(name="report_generator", sequenceName = "report_report_id_seq", allocationSize=1)
    @Column(name = "report_id")
    private Integer reportId;

    @PrePersist
    public void prePersist() {
        if(prisonId == null)
            prisonId = 1;
    }

    @Column(name = "prison_id")
    private Integer prisonId;

    @Column(name = "priority_id")
    private Integer priorityId;

    @Column(name = "shift_id")
    private Integer shiftId;

    @Size(min = 1, max = 45)
    @Column(name = "segment")
    private String segment;

    @Size(min = 1, max = 45)
    @Column(name = "blok")
    private String blok;

    @Size(min = 1, max = 45)
    @Column(name = "description")
    private String description;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "prison_id",
            insertable = false,
            updatable = false
    )
    private Prison prison;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "priority_id",
            insertable = false,
            updatable = false
    )
    private Priority priority;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "shift_id",
            insertable = false,
            updatable = false
    )
    private Shift shift;

    public String getSegment() {
        return segment;
    }

    public String getBlok() {
        return blok;
    }

    public String getDescription() {
        return description;
    }

    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getReportId() != null) {
                this.localId = Report.class.getSimpleName() + this.getReportId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Report that = (Report)other;
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
