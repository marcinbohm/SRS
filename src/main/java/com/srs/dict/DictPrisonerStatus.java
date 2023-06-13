package com.srs.dict;

public enum DictPrisonerStatus {

    ZBIEGLY("Więzień zbiegł", 1),
    OSADZONY("Więzień osadzony w celi", 2),
    PRZEPUSTKA("Więzień na przepustce", 3),
    IZOLATKA("Więzień w izolatce", 4),
    PRACE_PORZADKOWE("Więzień odbywa prace porządkowe", 5),
    WYKONANY("Resocjalizacja więźnia zakończona", 6);

    private final String description;
    private final Integer code;

    private DictPrisonerStatus(String description, Integer code) {
        this.description = description;
        this.code = code;
    }

    public String getDescription() {
        return this.description;
    }
    public Integer getCode(){return this.code;}
}
