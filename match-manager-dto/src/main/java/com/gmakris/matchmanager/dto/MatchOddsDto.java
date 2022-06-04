package com.gmakris.matchmanager.dto;

import java.util.UUID;

public record MatchOddsDto(
    UUID id,
    MatchDto match,
    String specifier,
    Double odd
) {
}
