package com.gmakris.matchmanager.entity.model;

public enum SportType {
    FOOTBALL(1),
    BASKETBALL(2);

    final int ordinal;

    SportType(final int ordinal) {
        this.ordinal = ordinal;
    }
}
