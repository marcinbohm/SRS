package com.srs.security.domain;

public enum PrivilegeType {

    ;

    private final String name;

    PrivilegeType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
