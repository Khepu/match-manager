package com.gmakris.matchmanager.mapper;

import static java.util.Objects.requireNonNull;

import com.gmakris.matchmanager.dto.MatchOddsDto;
import com.gmakris.matchmanager.entity.model.MatchOdds;
import org.springframework.stereotype.Component;

@Component
public class MatchOddsMapper
    implements GenericMapper<MatchOdds, MatchOddsDto> {

    private final MatchMapper matchMapper;

    public MatchOddsMapper(
        final MatchMapper matchMapper
    ) {
        this.matchMapper = matchMapper;
    }

    @Override
    public MatchOdds from(final MatchOddsDto matchOddsDto) {
        requireNonNull(matchOddsDto);

        final MatchOdds matchOdds = new MatchOdds();

        matchOdds.setId(matchOddsDto.id());
        matchOdds.setMatch(
            matchMapper.from(
                matchOddsDto.match()));

        matchOdds.setSpecifier(matchOddsDto.specifier());
        matchOdds.setOdd(matchOddsDto.odd());

        return matchOdds;
    }

    @Override
    public MatchOddsDto to(final MatchOdds matchOdds) {
        requireNonNull(matchOdds);

        return new MatchOddsDto(
            matchOdds.getId(),
            matchMapper.to(matchOdds.getMatch()),
            matchOdds.getSpecifier(),
            matchOdds.getOdd());
    }
}
