package com.srs.dict;

public enum DictUserType {

    ADMIN("Administrator", 1),
    FUNKCJ("Funkcjonariusz policji", 2),
    BIURO("Pracownik administracyjny", 3),
    DYREKTOR("Dyrektor/Inspektor", 4),
    TECH("Pracownik techniczny", 5);

    private final String description;
    private final Integer code;

    private DictUserType(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    public Integer getCode(){return this.code;}
}
