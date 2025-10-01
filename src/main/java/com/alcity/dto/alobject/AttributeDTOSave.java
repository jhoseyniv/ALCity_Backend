package com.alcity.dto.alobject;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AttributeDTOSave {
    private Long id;
    private String name;
    private Long ownerId;
    private String ownerType;
    private String dataType;
    private AttributeValueDTOSave attributeValueDTOSave;
    public AttributeDTOSave(Long id, String name, Long ownerId, String ownerType, String dataType, AttributeValueDTOSave attributeValueDTOSave ) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.dataType = dataType;
        this.attributeValueDTOSave = attributeValueDTOSave;
    }
}
