package com.alcity.dto.puzzle;

import com.alcity.entity.alobject.Attribute;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor
public class PLRulePostActionDTO {
    private Long id;
    private Long ownerId;
    private String ownerType;
    private String subAction;
    private StringBuffer valueExperssion;
    private String variable;
    private Integer ordering;
    private String objectId;
    private String actionName;
    private String alertType;
    private String alertMessage;

    private String actionKey;
    private String plRulePostActionType;

    private Collection<Attribute> parameters ;

    public PLRulePostActionDTO(Long id, StringBuffer valueExperssion,String subAction, String variable, Integer ordering, String objectId,
                               String actionName, String alertType, String alertMessage,String actionKey, String plRulePostActionType,Long ownerId,String ownerType) {
        this.id = id;
        this.valueExperssion = valueExperssion;
        this.subAction = subAction;
        this.variable = variable;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.actionKey = actionKey;
        this.plRulePostActionType = plRulePostActionType;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }
}
