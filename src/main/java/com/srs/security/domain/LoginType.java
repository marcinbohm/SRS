package com.srs.security.domain;

public enum LoginType {

    USER("USER"),
    ADMIN("ADMIN");

    private final String name;

    LoginType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
