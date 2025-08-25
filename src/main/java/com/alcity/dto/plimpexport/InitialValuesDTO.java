package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class InitialValuesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer zoom;
    private Integer zoomLimit;
    private Integer panLimit;
    private BoardCenterDTO boardCenter;
    private BoardCenterDTO initialPanOffset;

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

    public InitialValuesDTO(Integer zoom, Integer zoomLimit, Integer panLimit, BoardCenterDTO boardCenter, BoardCenterDTO initialPanOffset) {
        this.zoom = zoom;
        this.zoomLimit = zoomLimit;
        this.panLimit = panLimit;
        this.boardCenter = boardCenter;
        this.initialPanOffset = initialPanOffset;
    }
}
