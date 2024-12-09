package com.sebastian.visitor;

public enum IdType {
    PASSPORT("Passport"),
    NATIONAL_ID("National ID"),
    DRIVER_LICENSE("Driver's License");

    private final String displayName;

    IdType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
