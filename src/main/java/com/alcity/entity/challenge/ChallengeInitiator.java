package com.alcity.entity.challenge;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor

public class ChallengeInitiator  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name="title" )
    private String title;

    @Column(name="initiatorId" )
    private Long initiatorId;

    public ChallengeInitiator(String title, Long initiatorId) {
        this.title = title;
        this.initiatorId = initiatorId;
    }
}
