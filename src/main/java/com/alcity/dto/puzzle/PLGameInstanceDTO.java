package com.alcity.dto.puzzle;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.learning.LearningSkill;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PLGameInstanceDTO {

    Long id;
    Long appMemmberId;
    Long puzzleLevelId;
    String puzzleLevelTitle;
    String startPlayTime;
    String endPlayTime;
    String gameStatus;
    private byte[] analyticalData;
    public PLGameInstanceDTO(Long id, Long appMemmberId, Long puzzleLevelId, String puzzleLevelTitle, String startPlayTime,
                             String endPlayTime, String gameStatus, byte[] analyticalData) {
        this.id = id;
        this.appMemmberId = appMemmberId;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.startPlayTime = startPlayTime;
        this.endPlayTime = endPlayTime;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
    }
}
