package com.gmakris.matchmanager.mapper;

import static java.util.Objects.requireNonNull;

import com.gmakris.matchmanager.dto.MatchDto;
import com.gmakris.matchmanager.entity.model.Match;
import com.gmakris.matchmanager.entity.model.SportType;
import org.springframework.stereotype.Component;

@Component
public class MatchMapper
    implements GenericMapper<Match, MatchDto> {

    @Override
    public Match from(final MatchDto matchDto) {
        requireNonNull(matchDto);

        final Match match = new Match();

        match.setId(matchDto.id());
        match.setDescription(matchDto.description());
        match.setMatchDate(matchDto.matchDate());
        match.setMatchTime(matchDto.matchTime());
        match.setTeamA(matchDto.teamA());
        match.setTeamB(matchDto.teamB());
        match.setSport(SportType.match(matchDto.sport()));

        return match;
    }

    @Override
    public MatchDto to(final Match match) {
        requireNonNull(match);

        return new MatchDto(
            match.getId(),
            match.getDescription(),
            match.getMatchDate(),
            match.getMatchTime(),
            match.getTeamA(),
            match.getTeamB(),
            match.getSport().ordinal());
    }
}
