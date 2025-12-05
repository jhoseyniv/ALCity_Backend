package com.alcity.dto.challenge;

import com.alcity.entity.challenge.ChallengeInitiator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ChallengeDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String title;
    private String description;
    private ZonedDateTime startTime;
    private ZonedDateTime endTime;
    private Integer timeIntervalByHour;
    private Integer sizeOfParticipantGroup;
    private String initiator;
    private Long initiatorId;

    public ChallengeDTO(Long id, String title, String description, ZonedDateTime startTime, ZonedDateTime endTime,
                        Integer timeIntervalByHour, Integer sizeOfParticipantGroup, String initiator, Long initiatorId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeIntervalByHour = timeIntervalByHour;
        this.sizeOfParticipantGroup = sizeOfParticipantGroup;
        this.initiator = initiator;
        this.initiatorId = initiatorId;
    }
}
