package com.kazi.model;

public enum Type {
    COFFEE("Coffee"),
    JUICE("Freshly squeezed orange juice"),
    ROLL("Bacon Roll");

    private final String description;

    Type(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
