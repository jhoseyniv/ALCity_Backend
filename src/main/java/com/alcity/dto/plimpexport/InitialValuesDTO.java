package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class InitialValuesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer zoom;
    private Integer zoomLimit;
    private Integer panLimit;
    private BoardCenterDTO boardCenter;
    private BoardCenterDTO initialPanOffset;
    private Long backgroundID;
    private Long skyboxID;
    private Integer backgroundScale;

    public Integer getZoom() {
        return zoom;
    }

    public void setZoom(Integer zoom) {
        this.zoom = zoom;
    }

    public Integer getZoomLimit() {
        return zoomLimit;
    }

    public void setZoomLimit(Integer zoomLimit) {
        this.zoomLimit = zoomLimit;
    }

    public Integer getPanLimit() {
        return panLimit;
    }

    public void setPanLimit(Integer panLimit) {
        this.panLimit = panLimit;
    }

    public BoardCenterDTO getBoardCenter() {
        return boardCenter;
    }

    public void setBoardCenter(BoardCenterDTO boardCenter) {
        this.boardCenter = boardCenter;
    }

    public BoardCenterDTO getInitialPanOffset() {
        return initialPanOffset;
    }

    public void setInitialPanOffset(BoardCenterDTO initialPanOffset) {
        this.initialPanOffset = initialPanOffset;
    }

    public InitialValuesDTO() {
    }

    public Long getBackgroundID() {
        return backgroundID;
    }

    public void setBackgroundID(Long backgroundID) {
        this.backgroundID = backgroundID;
    }

    public Long getSkyboxID() {
        return skyboxID;
    }

    public void setSkyboxID(Long skyboxID) {
        this.skyboxID = skyboxID;
    }

    public Integer getBackgroundScale() {
        return backgroundScale;
    }

    public void setBackgroundScale(Integer backgroundScale) {
        this.backgroundScale = backgroundScale;
    }

    public InitialValuesDTO(Integer zoom, Integer zoomLimit, Integer panLimit, BoardCenterDTO boardCenter, BoardCenterDTO initialPanOffset,Long backgroundID, Long skyboxID,Integer backgroundScale) {
        this.zoom = zoom;
        this.zoomLimit = zoomLimit;
        this.panLimit = panLimit;
        this.boardCenter = boardCenter;
        this.initialPanOffset = initialPanOffset;
        this.backgroundID = backgroundID;
        this.skyboxID = skyboxID;
        this.backgroundScale = backgroundScale;
    }
}
