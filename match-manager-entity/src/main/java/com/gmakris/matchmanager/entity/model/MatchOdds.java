package com.gmakris.matchmanager.entity.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Table(name = "match_odds")
@Entity(name = "MatchOdds")
@EqualsAndHashCode(callSuper = true)
public class MatchOdds extends AbstractEntity{

    @OneToOne
    @Column(name = "match_id")
    private Match match;

    @Column(name = "specifier")
    private String specifier;

    @Column(name = "odd")
    private Double odd;
}
