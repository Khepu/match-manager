package com.gmakris.matchmanager.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record MatchDto(
    UUID id,
    String description,
    LocalDate matchDate,
    LocalTime matchTime,
    String teamA,
    String teamB,
    int sport
) {
}
