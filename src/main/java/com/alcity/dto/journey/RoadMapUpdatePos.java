package com.alcity.dto.journey;

public class RoadMapUpdatePos {
    private Long roadMapId;
    private Integer newPosY;

    public Long getRoadMapId() {
        return roadMapId;
    }

    public void setRoadMapId(Long roadMapId) {
        this.roadMapId = roadMapId;
    }

    public Integer getNewPosY() {
        return newPosY;
    }

    public void setNewPosY(Integer newPosY) {
        this.newPosY = newPosY;
    }

    public RoadMapUpdatePos() {
    }

    public RoadMapUpdatePos(Long roadMapId, Integer newPosY) {
        this.roadMapId = roadMapId;
        this.newPosY = newPosY;
    }
}
