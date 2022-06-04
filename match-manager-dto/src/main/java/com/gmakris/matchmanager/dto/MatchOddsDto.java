package com.gmakris.matchmanager.dto;

import java.util.UUID;

public record MatchOddsDto(
    UUID id,
    UUID matchId,
    String specifier,
    Double odd
) {
}
