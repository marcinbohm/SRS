package com.srs.dto;

public class PrisonerDTO {

    private Integer prisonerId;


    private String name;


    private String surname;

    private String pesel;


    private String kartaPobytuId;


    private String passportId;

    private Integer status;

    public PrisonerDTO() {
    }

    public Integer getPrisonerId() {
        return prisonerId;
    }

    public void setPrisonerId(Integer prisonerId) {
        this.prisonerId = prisonerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getKartaPobytuId() {
        return kartaPobytuId;
    }

    public void setKartaPobytuId(String kartaPobytuId) {
        this.kartaPobytuId = kartaPobytuId;
    }

    public String getPassportId() {
        return passportId;
    }

    public void setPassportId(String passportId) {
        this.passportId = passportId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
