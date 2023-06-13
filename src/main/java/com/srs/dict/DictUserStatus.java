package com.srs.dict;

public enum DictUserStatus {

    EMERYTOWANY("Użytkownik emerytowany", 1),
    AKTYWNY("Użytkownik aktywny", 2),
    W_PRACY("Użytkownik w pracy", 3),
    ZWOLNIONY("Użytkownik zwolniony", 4),
    PROCES_ZATRUDNIENIA("Użytkownik w trakcie zatrudnienia", 5);


    private final String description;
    private final Integer code;

    private DictUserStatus(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    public Integer getCode(){return this.code;}
}
