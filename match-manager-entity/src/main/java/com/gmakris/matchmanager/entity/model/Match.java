package com.gmakris.matchmanager.entity.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "matches")
@Entity(name = "Match")
@EqualsAndHashCode(callSuper = true)
public class Match extends AbstractEntity {

    @Column(name = "description")
    private String description;

    @Column(name = "match_date")
    private LocalDate matchDate;

    @Column(name = "match_time")
    private LocalTime matchTime;

    @Column(name = "team_a")
    private String teamA;

    @Column(name = "team_b")
    private String teamB;

    @Column(name = "sport")
    @Enumerated(EnumType.ORDINAL)
    private SportType sport;
}
