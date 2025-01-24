package com.alcity.utility;

import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.dto.puzzle.object.PropertyDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class PLDTOUtil {

    public static PropertyDTO getPropertyDTO(Attribute attribute) {
       Collection<AttributeValue> values = attribute.getAttributeValues();
        AttributeValue matchValue = values.stream().filter(value -> value.getOwnerId().equals(attribute.getOwnerId())).findFirst().get();
        PropertyDTO dto = new PropertyDTO(attribute.getId(), attribute.getName(), attribute.getDataType().name(),DTOUtil.getDataValue(matchValue));
        return dto;
    }
    public static Collection<PropertyDTO> getPropertyDTOS(Collection<Attribute> attributes) {
        Collection<PropertyDTO> dtos = new ArrayList<>();
        Iterator<Attribute> itr = attributes.iterator();
        while(itr.hasNext()){
            Attribute attribute = itr.next();
            PropertyDTO dto = getPropertyDTO(attribute);
            dtos.add(dto);
        }
        return dtos;
    }
    public static CityObjectDTO getCityObjectDTO(ALCityObject co, ActionService actionService, AttributeService attributeService){
        Collection<PropertyDTO>  properties = new ArrayList<PropertyDTO>();
        Collection<ActionDTO>  actions = new ArrayList<ActionDTO>();
        Collection<Attribute>  attributes = attributeService.findByOwnerIdAndAttributeOwnerType(co.getId(), AttributeOwnerType.AlCity_Object);
        Collection<ObjectAction> objectActions = actionService.findByOwnerObjectidAndPoActionOwnerType(co.getId(), POActionOwnerType.ALCity_Object);
        properties = getPropertyDTOS(attributes);
        actions =getActionDTOS(objectActions);
        CityObjectDTO dto= new CityObjectDTO(co.getId(), co.getTitle(), co.getObjectCategory().getId(),
                                                co.getObjectCategory().getLabel(),co.getPic().getId(),co.getIcon().getId(),properties,actions,
                                                co.getVersion(),co.getCreated(), co.getUpdated(),
                                                co.getCreatedBy().getUsername(),co.getCreatedBy().getUsername());

        return dto;
    }
    public static  Collection<CityObjectDTO> getCityObjectsDTOS(Collection<ALCityObject> objects, ActionService actionService, AttributeService attributeService){
        Collection<CityObjectDTO> dtos = new ArrayList<CityObjectDTO>();
        Iterator<ALCityObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            CityObjectDTO dto = new CityObjectDTO();
            ALCityObject object = iterator.next();
            dto = getCityObjectDTO(object,actionService,attributeService);
            dtos.add(dto);
        }

        return dtos;
    }
    public static  Collection<ActionDTO> getActionDTOS(Collection<ObjectAction> actions) {
        Collection<ActionDTO> dtos = new ArrayList<ActionDTO>();
        Iterator<ObjectAction> itr = actions.iterator();
        while (itr.hasNext()) {
            ActionDTO dto = new ActionDTO();
            ObjectAction poa = itr.next();
            dto = getActionDTO(poa);
            dtos.add(dto);
        }

        return dtos;
    }
    public static ActionDTO getActionDTO(ObjectAction action) {
        ActionDTO dto = new ActionDTO(action.getId(), action.getOwnerObjectid(), action.getObjectAction().name(),
                    Long.valueOf(action.getObjectAction().ordinal()),
                            action.getActionRenderer().getHandler(),
                                action.getActionRenderer().getId(),
                                    action.getPoActionOwnerType().name(),
                                      Long.valueOf(action.getPoActionOwnerType().ordinal()));

        return dto;
    }

}
