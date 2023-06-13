package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "shift")
@EntityListeners(AuditListener.class)
public class Shift {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shift_generator")
    @SequenceGenerator(name="shift_generator", sequenceName = "shift_shift_id_seq", allocationSize=1)
    @Column(name = "shift_id")
    private Integer shiftId;

     @Column(name = "start_date")
     @NotNull
     private LocalDateTime startDate;

     @Column(name = "end_date")
     private LocalDateTime endDate;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_shift",
            joinColumns = { @JoinColumn(name = "shift_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    private Set<User> shiftUserSet;

    @OneToMany(mappedBy = "shift", fetch = FetchType.LAZY)
    private List<Report> shiftReportList;

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public Integer getShiftId() {
        return shiftId;
    }

    public void setShiftId(Integer shiftId) {
        this.shiftId = shiftId;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public Set<User> getShiftUserSet() {
        return shiftUserSet;
    }

    public void setShiftUserSet(Set<User> shiftUserSet) {
        this.shiftUserSet = shiftUserSet;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getShiftId() != null) {
                this.localId = Shift.class.getSimpleName() + this.getShiftId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Shift that = (Shift)other;
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
