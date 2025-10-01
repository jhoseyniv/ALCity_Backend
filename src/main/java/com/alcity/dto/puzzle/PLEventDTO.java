package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PLEventDTO {
    private Long id;
    private Long puzzleLevelId;
    private Long appMemberId;
    private String eventTime;
    private String eventType;
    private Float skillAmount;
    private Float walletItemAmount;
    private String gameStatus;
    private byte[] analyticalData;

    public PLEventDTO(Long id, Long puzzleLevelId, Long appMemberId, String eventTime, String eventType,
                      Float skillAmount, Float walletItemAmount, String gameStatus, byte[] analyticalData) {
        this.id = id;
        this.puzzleLevelId = puzzleLevelId;
        this.appMemberId = appMemberId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.skillAmount = skillAmount;
        this.walletItemAmount = walletItemAmount;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
    }
}
