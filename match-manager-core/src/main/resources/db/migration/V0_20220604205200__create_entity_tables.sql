CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE matches
(
    id          UUID                        NOT NULL,
    description VARCHAR                     NOT NULL,
    match_date  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    match_time  TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    team_a      VARCHAR                     NOT NULL,
    team_b      VARCHAR                     NOT NULL,
    sport       INTEGER                     NOT NULL,

    PRIMARY KEY (id)
);

CREATE TABLE match_odds
(
    id        UUID             NOT NULL,
    match_id  UUID             NOT NULL,
    specifier VARCHAR          NOT NULL,
    odd       DOUBLE PRECISION NOT NULL,

    PRIMARY KEY (id)
);

ALTER TABLE match_odds
    ADD CONSTRAINT "fk_match_odds_match_id"
        FOREIGN KEY (match_id)
            REFERENCES matches (id);
