package com.alcity.dto.alobject;

import com.alcity.dto.base.DataTypeِDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor

public class AttributeDTO  {
    private Long id;
    private String name;
    private Long ownerId;
    private String ownerType;
    private String dataType;
    private AttributeValueDTO attributeValueDTO;

    public AttributeDTO(Long id, String name, Long ownerId, String ownerType, String dataType, AttributeValueDTO attributeValueDTO ) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
        this.dataType = dataType;
        this.attributeValueDTO = attributeValueDTO;
    }
}
