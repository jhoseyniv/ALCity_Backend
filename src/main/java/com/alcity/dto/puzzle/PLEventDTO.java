package com.alcity.dto.puzzle;

public class PLEventDTO {
    private Long puzzleLevelId;
    private Long appMemberId;

    private String eventTime;

    private String eventType;

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public PLEventDTO() {
    }

    public PLEventDTO(Long puzzleLevelId, Long appMemberId, String eventTime, String eventType) {
        this.puzzleLevelId = puzzleLevelId;
        this.appMemberId = appMemberId;
        this.eventTime = eventTime;
        this.eventType = eventType;
    }
}
