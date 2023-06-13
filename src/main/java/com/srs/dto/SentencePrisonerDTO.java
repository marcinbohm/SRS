package com.srs.dto;

public class SentencePrisonerDTO {

    private Integer sentenceId;

    private Integer prisonerId;

    private Integer prisonId;

    private String startDate;

    private String endDate;

    private String assignDate;

    private String unassignDate;

    private String name;

    private String surname;

    private String pesel;

    private String kartaPobytuId;

    private String passportId;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getAssignDate() {
        return assignDate;
    }

    public void setAssignDate(String assignDate) {
        this.assignDate = assignDate;
    }

    public String getUnassignDate() {
        return unassignDate;
    }

    public void setUnassignDate(String unassignDate) {
        this.unassignDate = unassignDate;
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
}
