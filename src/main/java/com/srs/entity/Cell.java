package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "cell")
@EntityListeners(AuditListener.class)
public class Cell {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cell_generator")
    @SequenceGenerator(name="cell_generator", sequenceName = "cell_cell_id_seq", allocationSize=1)
    @Column(name = "cell_id")
    private Integer cellId;

    @PrePersist
    public void prePersist() {
        if(prisonId == null)
            prisonId = 1;
    }

    @Column(name = "prison_id")
    private Integer prisonId;

    @Size(min = 1, max = 45)
    @Column(name = "segment")
    private String segment;

    @Size(min = 1, max = 45)
    @Column(name = "blok")
    private String blok;

    @Column(name = "assign_date")
    private LocalDate assignDate;

    @Column(name = "unassign_date")
    private LocalDate unassignDate;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "prison_id",
            insertable = false,
            updatable = false
    )
    private Prison prison;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "sentence_cell",
            joinColumns = { @JoinColumn(name = "cell_id") },
            inverseJoinColumns = { @JoinColumn(name = "sentence_id") }
    )
    private Set<Sentence> cellSentenceSet;

    public Integer getCellId() {
        return cellId;
    }

    public void setCellId(Integer cellId) {
        this.cellId = cellId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getBlok() {
        return blok;
    }

    public void setBlok(String blok) {
        this.blok = blok;
    }

    public LocalDate getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(LocalDate assignDate) {
        this.assignDate = assignDate;
    }

    public LocalDate getUnassignDate() {
        return unassignDate;
    }

    public void setUnassignDate(LocalDate unassignDate) {
        this.unassignDate = unassignDate;
    }

    public Prison getPrison() {
        return prison;
    }

    public void setPrison(Prison prison) {
        this.prison = prison;
    }

    public Set<Sentence> getCellSentenceSet() {
        return cellSentenceSet;
    }

    public void setCellSentenceSet(Set<Sentence> cellSentenceSet) {
        this.cellSentenceSet = cellSentenceSet;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getCellId() != null) {
                this.localId = Cell.class.getSimpleName() + this.getCellId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Cell that = (Cell)other;
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
