package com.alcity.entity.challenge;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ChallengeAppMemberPuzzle  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="groupTitle" )
    private String groupTitle;

    @Column(name="groupId" )
    private Long groupId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember applicationMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "challenge_id", referencedColumnName = "id")
    @JsonIgnore
    private Challenge challenge;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_id", referencedColumnName = "id")
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @Column(name="timePlanedToChallenge" )
    private ZonedDateTime timePlanedToChallenge;

    @Column(name="timePlayedChallenge" )
    private ZonedDateTime timePlayedChallenge;

    @Column(name="score" )
    private Float score;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "challenge_leader_board_id", referencedColumnName = "id")
    @JsonIgnore
    private ChallengeLeaderBoard leaderBoard;

    public ChallengeAppMemberPuzzle(String groupTitle, Long groupId, AppMember applicationMember, Challenge challenge, PuzzleLevel puzzleLevel,
                                    ZonedDateTime timePlanedToChallenge, ZonedDateTime timePlayedChallenge, Float score) {
        this.groupTitle = groupTitle;
        this.groupId = groupId;
        this.applicationMember = applicationMember;
        this.challenge = challenge;
        this.puzzleLevel = puzzleLevel;
        this.timePlanedToChallenge = timePlanedToChallenge;
        this.timePlayedChallenge = timePlayedChallenge;
        this.score = score;
    }

}
