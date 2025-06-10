package com.alcity.dto.puzzle.boardgraphic;

import java.io.Serializable;

public class Setting implements Serializable {
    private Long skyboxId;
    private Long environmentId;
    private StartPosition startPosition;

    public Long getSkyboxId() {
        return skyboxId;
    }

    public void setSkyboxId(Long skyboxId) {
        this.skyboxId = skyboxId;
    }

    public Long getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(Long environmentId) {
        this.environmentId = environmentId;
    }

    public StartPosition getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(StartPosition startPosition) {
        this.startPosition = startPosition;
    }

    public Setting() {
    }

    public Setting(Long skyboxId, Long environmentId, StartPosition startPosition) {
        this.skyboxId = skyboxId;
        this.environmentId = environmentId;
        this.startPosition = startPosition;
    }
}
