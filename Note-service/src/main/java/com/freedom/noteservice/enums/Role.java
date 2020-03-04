package com.freedom.noteservice.enums;

public enum Role {
    USER("user");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
