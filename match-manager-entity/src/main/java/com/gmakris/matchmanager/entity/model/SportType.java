package com.gmakris.matchmanager.entity.model;

import java.util.Arrays;

public enum SportType {
    FOOTBALL,
    BASKETBALL;

    public static SportType match(final int ordinal) {
        return Arrays.stream(SportType.values())
            .filter(sportType -> sportType.ordinal() == ordinal)
            .findFirst()
            .orElseThrow(() -> new RuntimeException(
                "Could not match '" + ordinal + "' to any sport type!"));
    }
}
