package com.alcity.entity.challenge;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Challenge  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="title" )
    private String title;

    @Column(name="startTime" )
    private ZonedDateTime startTime;

    @Column(name="endTime" )
    private ZonedDateTime endTime;

    @Column(name="timeIntervalByHour" )
    private Integer timeIntervalByHour;

    @Column(name="sizeOfParticipantGroup" )
    private Integer sizeOfParticipantGroup;

    @Column(name="description" )
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "challenge_initiator_id", nullable = false)
    @JsonIgnore
    private ChallengeInitiator initiator;

    @OneToMany(mappedBy = "challenge", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<ChallengeAppMemberPuzzle> challengeAppMemberPuzzles;

    public Challenge(String title, ZonedDateTime startTime, ZonedDateTime endTime, Integer timeIntervalByHour, Integer sizeOfParticipantGroup, String description,
                     ChallengeInitiator initiator) {
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeIntervalByHour = timeIntervalByHour;
        this.sizeOfParticipantGroup = sizeOfParticipantGroup;
        this.description = description;
        this.initiator = initiator;
    }
}
