package com.alcity.dto.puzzle;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PLEndPlayDTO  implements Serializable {
    private Long instanceId;
    private String eventTime;
    private String gameStatus;
    private byte[] analyticalData;

    public PLEndPlayDTO(Long instanceId, String eventTime, String gameStatus,byte[] analyticalData) {
        this.instanceId = instanceId;
        this.eventTime = eventTime;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
    }
}
