package com.srs.entity;

import org.springframework.boot.actuate.audit.listener.AuditListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "prison")
@EntityListeners(AuditListener.class)
public class Prison {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prison_generator")
    @SequenceGenerator(name="prison_generator", sequenceName = "prison_prison_id_seq", allocationSize=1)
    @Column(name = "prison_id")
    private Integer prisonId;

    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "voivodeship")
    private String voivodeship;

    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "city")
    private String city;

    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "postal_code")
    @Pattern(regexp = "/^[0-9]{2}-[0-9]{3}/")
    private String postalCode;

    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "street")
    private String street;

    @OneToMany(mappedBy = "prison", fetch = FetchType.LAZY)
    private List<Sentence> prisonSentenceList;

    @OneToMany(mappedBy = "prison", fetch = FetchType.LAZY)
    private List<Cell> prisonCellList;

    @OneToMany(mappedBy = "prison", fetch = FetchType.LAZY)
    private List<Report> prisonReportList;

    public String getVoivodeship() {
        return voivodeship;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public List<Sentence> getPrisonSentenceList() {
        return prisonSentenceList;
    }

    public void setPrisonSentenceList(List<Sentence> prisonSentenceList) {
        this.prisonSentenceList = prisonSentenceList;
    }

    public List<Cell> getPrisonCellList() {
        return prisonCellList;
    }

    public void setPrisonCellList(List<Cell> prisonCellList) {
        this.prisonCellList = prisonCellList;
    }

    public List<Report> getPrisonReportList() {
        return prisonReportList;
    }

    public void setPrisonReportList(List<Report> prisonReportList) {
        this.prisonReportList = prisonReportList;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getPrisonId() != null) {
                this.localId = Prison.class.getSimpleName() + this.getPrisonId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Prison that = (Prison)other;
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
