package com.alcity.dto.journey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class RoadMapUpdatePos {
    private Long roadMapId;
    private Integer newPosY;

    public RoadMapUpdatePos(Long roadMapId, Integer newPosY) {
        this.roadMapId = roadMapId;
        this.newPosY = newPosY;
    }
}
