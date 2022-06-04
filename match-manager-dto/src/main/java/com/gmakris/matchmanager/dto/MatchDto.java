package com.gmakris.matchmanager.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record MatchDto(
    UUID id,
    String description,
    LocalDateTime matchDate,
    LocalDateTime matchTime,
    String teamA,
    String teamB,
    int sport
) {
}
