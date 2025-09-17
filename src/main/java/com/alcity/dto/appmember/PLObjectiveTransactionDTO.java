package com.alcity.dto.appmember;

import com.alcity.entity.appmember.AppMember;

import java.io.Serializable;

public class PLObjectiveTransactionDTO implements Serializable {
    private Float amount;
    private Long gameInstanceId;
    private Long appMemberId;
    private Long objectiveId;
    private String objectiveType;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getGameInstanceId() {
        return gameInstanceId;
    }

    public void setGameInstanceId(Long gameInstanceId) {
        this.gameInstanceId = gameInstanceId;
    }

    public String getObjectiveType() {
        return objectiveType;
    }

    public void setObjectiveType(String objectiveType) {
        this.objectiveType = objectiveType;
    }

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public PLObjectiveTransactionDTO() {
    }

    public PLObjectiveTransactionDTO(Long appMemberId, Float amount, Long gameInstanceId, Long objectiveId, String objectiveType) {
        this.amount = amount;
        this.gameInstanceId = gameInstanceId;
        this.appMemberId = appMemberId;
        this.objectiveId = objectiveId;
        this.objectiveType = objectiveType;
    }
}
