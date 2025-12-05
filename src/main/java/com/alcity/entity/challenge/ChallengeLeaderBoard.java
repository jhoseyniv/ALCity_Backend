package com.alcity.entity.challenge;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ChallengeLeaderBoard  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "leaderBoard", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ChallengeAppMemberPuzzle> playedChallenges;

    @Column(name="totalScore" )
    private Float totalScore;

    @Column(name="streak" )
    private Integer streak;

    @Column(name="description" )
    private String description;


}
