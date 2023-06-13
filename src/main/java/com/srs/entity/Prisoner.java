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
@Table(name = "prisoner")
@EntityListeners(AuditListener.class)
public class Prisoner {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "prisoner_generator")
    @SequenceGenerator(name="prisoner_generator", sequenceName = "prisoner_prisoner_id_seq", allocationSize=1)
    @Column(name = "prisoner_id")
    private Integer prisonerId;


    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;


    @NotBlank
    @Size(min = 1, max = 45)
    @Column(name = "surname")
    private String surname;

    @NotBlank
    @Pattern(regexp = "/^[0-9]{2}([02468]1|[13579][012])(0[1-9]|1[0-9]|2[0-9]|3[01])[0-9]{5}$/gm")
    @Size(min = 11, max = 11)
    @Column(name = "pesel")
    private String pesel;


    @Size(min = 1, max = 45)
    @Column(name = "karta_pobytu_id")
    private String kartaPobytuId;


    @Size(min = 1, max = 45)
    @Column(name = "passport_id")
    private String passportId;

    @Column(name = "status")
    private Integer status;

    @OneToMany(mappedBy = "prisoner", fetch = FetchType.LAZY)
    private List<Application> prisonerApplicationList;

    public String getPesel() {
        return pesel;
    }

    public String getPassportId() {
        return passportId;
    }

    public String getKartaPobytuId() {
        return kartaPobytuId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(Integer prisonerId) {
        this.prisonerId = prisonerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public void setKartaPobytuId(String kartaPobytuId) {
        this.kartaPobytuId = kartaPobytuId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public List<Application> getPrisonerApplicationList() {
        return prisonerApplicationList;
    }

    public void setPrisonerApplicationList(List<Application> prisonerApplicationList) {
        this.prisonerApplicationList = prisonerApplicationList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Transient
    private String localId = null;

    private void lazyInitLocalId() {
        if (this.localId == null) {
            if (this.getPrisonerId() != null) {
                this.localId = Prisoner.class.getSimpleName() + this.getPrisonerId();
            } else {
                this.localId = UUID.randomUUID().toString();
            }
        }

    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other != null && this.getClass() == other.getClass()) {
            Prisoner that = (Prisoner)other;
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
