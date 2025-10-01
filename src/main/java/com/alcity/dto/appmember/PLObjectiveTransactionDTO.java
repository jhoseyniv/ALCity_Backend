package com.alcity.dto.appmember;

import com.alcity.entity.appmember.AppMember;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class PLObjectiveTransactionDTO implements Serializable {
    private Float amount;
    private Long gameInstanceId;
    private Long appMemberId;
    private Long objectiveId;
    private String objectiveType;

    public PLObjectiveTransactionDTO(Long appMemberId, Float amount, Long gameInstanceId, Long objectiveId, String objectiveType) {
        this.amount = amount;
        this.gameInstanceId = gameInstanceId;
        this.appMemberId = appMemberId;
        this.objectiveId = objectiveId;
        this.objectiveType = objectiveType;
    }
}
