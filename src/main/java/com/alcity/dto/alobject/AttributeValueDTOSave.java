package com.alcity.dto.alobject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AttributeValueDTOSave {
    private Long id;
    private Long longValue;
    private Float doubleValue;
    private Integer intValue;
    private Long binaryContentId;

    private String  expressionValue;
    private Boolean  expression;
    private String stringValue;
    private String objectValue;
    private Boolean booleanValue;
    private Long attributeId;
    private Long bindedAttributeId;

    private Long ownerId;

    private String ownerType;
    private Long newOwnerId;

    private String newOwnerType;


    public AttributeValueDTOSave(Long id, Long longValue, Float doubleValue, Integer intValue, Long binaryContentId, String expressionValue,Boolean expression, String stringValue, String objectValue, Boolean booleanValue, Long attributeId, Long bindedAttributeId, Long ownerId, String ownerType, Long newOwnerId, String newOwnerType) {
        this.id = id;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.binaryContentId = binaryContentId;
        this.expressionValue = expressionValue;
        this.expression =expression;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.booleanValue = booleanValue;
        this.attributeId = attributeId;
        this.bindedAttributeId = bindedAttributeId;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.newOwnerId = newOwnerId;
        this.newOwnerType = newOwnerType;
    }
}
