package com.alcity.dto.plimpexport;

import java.io.Serializable;

public class InitialValuesDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer zoom;
    private Integer zoomLimit;
    private Integer panLimit;
    private BoardCenterDTO boardCenter;
    private BoardCenterDTO initialPanOffset;
    private Long skybox_id;
    private Long background_id;
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

    public Long getSkybox_id() {
        return skybox_id;
    }

    public void setSkybox_id(Long skybox_id) {
        this.skybox_id = skybox_id;
    }

    public Long getBackground_id() {
        return background_id;
    }

    public void setBackground_id(Long background_id) {
        this.background_id = background_id;
    }

    public Integer getBackgroundScale() {
        return backgroundScale;
    }

    public void setBackgroundScale(Integer backgroundScale) {
        this.backgroundScale = backgroundScale;
    }

    public InitialValuesDTO(Integer zoom, Integer zoomLimit, Integer panLimit, BoardCenterDTO boardCenter, BoardCenterDTO initialPanOffset) {
        this.zoom = zoom;
        this.zoomLimit = zoomLimit;
        this.panLimit = panLimit;
        this.boardCenter = boardCenter;
        this.initialPanOffset = initialPanOffset;
    }
}
