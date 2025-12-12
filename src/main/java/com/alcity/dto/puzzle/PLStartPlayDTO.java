package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PLStartPlayDTO implements Serializable {
    private Long puzzleLevelId;
    private Long appMemberId;
    private String eventTime;

    public PLStartPlayDTO(Long puzzleLevelId, Long appMemberId, String eventTime) {
        this.puzzleLevelId = puzzleLevelId;
        this.appMemberId = appMemberId;
        this.eventTime = eventTime;
    }
}
