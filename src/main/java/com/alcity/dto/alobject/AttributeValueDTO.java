package com.alcity.dto.alobject;

import com.alcity.entity.alenum.AttributeOwnerType;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AttributeValueDTO {
    private Long id;
    private Long longValue;
    private Float doubleValue;
    private Integer intValue;
    private Long binaryContentId;
    private String  expressionValue;
    private Boolean  isExpression;
    private String stringValue;
    private String objectValue;
    private Boolean booleanValue;
    private Long attributeId;
    private Long bindedAttributeId;

    private Long ownerId;

    private String ownerType;

    public AttributeValueDTO(Long id,Boolean booleanValue ,Long longValue, Float doubleValue, Integer intValue, Long binaryContentId,String expressionValue,Boolean isExpression, String stringValue, String objectValue,Long attributeId,Long bindedAttributeId,Long ownerId,String ownerType) {
        this.id = id;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.booleanValue = booleanValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.intValue = intValue;
        this.binaryContentId = binaryContentId;
        this.expressionValue = expressionValue;
        this.isExpression = isExpression;
        this.stringValue = stringValue;
        this.objectValue = objectValue;
        this.attributeId = attributeId;
        this.bindedAttributeId = bindedAttributeId;
    }
}
