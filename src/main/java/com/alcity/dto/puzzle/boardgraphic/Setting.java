package com.alcity.dto.puzzle.boardgraphic;

public class Setting {
    private String skyboxId;
    private String environmentId;
    private StartPosition startPosition;

    public String getSkyboxId() {
        return skyboxId;
    }

    public void setSkyboxId(String skyboxId) {
        this.skyboxId = skyboxId;
    }

    public String getEnvironmentId() {
        return environmentId;
    }

    public void setEnvironmentId(String environmentId) {
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

    public Setting(String skyboxId, String environmentId, StartPosition startPosition) {
        this.skyboxId = skyboxId;
        this.environmentId = environmentId;
        this.startPosition = startPosition;
    }
}
