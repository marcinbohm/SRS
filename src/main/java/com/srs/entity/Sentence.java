package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "sentence")
@EntityListeners(AuditListener.class)
public class Sentence {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sentence_generator")
    @SequenceGenerator(name="sentence_generator", sequenceName = "sentence_sentence_id_seq", allocationSize=1)
    @Column(name = "sentence_id")
    private Integer sentenceId;

    @NotNull
    @Column(name = "prisoner_id")
    private Integer prisonerId;

    @PrePersist
    public void prePersist() {
        if(prisonId == null)
            prisonId = 1;
    }

    @Column(name = "prison_id")
    private Integer prisonId;

    @NotNull
    @Column(name = "start_date")
    private LocalDate startDate;

    @NotNull
    @Column(name = "end_date")
    private LocalDate endDate;

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

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "prisoner_id",
            insertable = false,
            updatable = false
    )
    private Prisoner prisoner;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "sentence_cell",
            joinColumns = { @JoinColumn(name = "sentence_id") },
            inverseJoinColumns = { @JoinColumn(name = "cell_id") }
    )
    private Set<Cell> sentenceCellSet;

    public Integer getSentenceId() {
        return sentenceId;
    }

    public void setSentenceId(Integer sentenceId) {
        this.sentenceId = sentenceId;
    }

    public Integer getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(Integer prisonerId) {
        this.prisonerId = prisonerId;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
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

    public Prisoner getPrisoner() {
        return prisoner;
    }

    public void setPrisoner(Prisoner prisoner) {
        this.prisoner = prisoner;
    }

    public Set<Cell> getSentenceCellSet() {
        return sentenceCellSet;
    }

    public void setSentenceCellSet(Set<Cell> sentenceCellSet) {
        this.sentenceCellSet = sentenceCellSet;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getSentenceId() != null) {
                this.localId = Sentence.class.getSimpleName() + this.getSentenceId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Sentence that = (Sentence)other;
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
