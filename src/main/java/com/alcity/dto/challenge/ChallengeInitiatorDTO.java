package com.alcity.dto.challenge;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class ChallengeInitiatorDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private Long initiatorId;

    public ChallengeInitiatorDTO(Long id, String title, Long initiatorId) {
        this.id = id;
        this.title = title;
        this.initiatorId = initiatorId;
    }
}
