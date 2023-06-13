package com.srs.dto;

public class PrisonDTO {

    private Integer prisonId;

    private String voivodeship;

    private String city;

    private String postalCode;

    private String street;

    public PrisonDTO() {
    }

    public PrisonDTO(Integer prisonId, String voivodeship, String city, String postalCode, String street) {
        this.prisonId = prisonId;
        this.voivodeship = voivodeship;
        this.city = city;
        this.postalCode = postalCode;
        this.street = street;
    }

    public Integer getPrisonId() {
        return prisonId;
    }

    public void setPrisonId(Integer prisonId) {
        this.prisonId = prisonId;
    }

    public String getVoivodeship() {
        return voivodeship;
    }

    public void setVoivodeship(String voivodeship) {
        this.voivodeship = voivodeship;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
