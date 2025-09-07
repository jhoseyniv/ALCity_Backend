package com.alcity.utility;

import com.alcity.dto.plimpexport.PLData;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.dto.puzzle.object.ActionDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;
import com.alcity.dto.puzzle.object.PropertyDTO;
import com.alcity.dto.search.SearchResultCityObjectDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.puzzle.*;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.ActionService;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class PLDTOUtil {

    public static PropertyDTO getPropertyDTO(Attribute attribute) {
       Collection<AttributeValue> values = attribute.getAttributeValues();
        AttributeValue matchValue=null;
        Optional<AttributeValue> matchValueOptional = values.stream().filter(value -> value.getOwnerId().equals(attribute.getOwnerId())).findFirst();
        if(matchValueOptional.isPresent())
            matchValue=matchValueOptional.get();
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
    public static CityObjectDTO getCityObjectDTO(BaseObject co, ActionService actionService, AttributeService attributeService){
        Collection<PropertyDTO>  properties = new ArrayList<PropertyDTO>();
        Collection<ActionDTO>  actions = new ArrayList<ActionDTO>();
        Collection<Attribute>  attributes = attributeService.findByOwnerIdAndAttributeOwnerType(co.getId(), AttributeOwnerType.Object_Property);
        Collection<ObjectAction> objectActions = actionService.findByOwnerObjectidAndPoActionOwnerType(co.getId(), POActionOwnerType.Object);
        properties = getPropertyDTOS(attributes);
        actions =getActionDTOS(objectActions);
        CityObjectDTO dto= new CityObjectDTO(co.getId(), co.getTitle(), co.getObjectCategory().getId(),
                co.getObjectCategory().getLabel(),co.getIs3dObject(),co.getPic().getId(),co.getIcon().getId(),properties,actions,
                co.getVersion(),co.getCreated(), co.getUpdated(),
                co.getCreatedBy().getUsername(),co.getCreatedBy().getUsername());

        return dto;
    }
    public static SearchResultCityObjectDTO getSearchResultCityObjectDTO(PGObject alCityObjectInPG){
        BaseObject alCityObject = alCityObjectInPG.getAlCityObject();
        PuzzleGroup puzzleGroup = alCityObjectInPG.getPuzzleGroup();
        ObjectCategory  objectCategory= alCityObject.getObjectCategory();

        SearchResultCityObjectDTO dto= new SearchResultCityObjectDTO(alCityObject.getId(), alCityObject.getTitle(), objectCategory.getId(),
                objectCategory.getLabel(),puzzleGroup.getId(),puzzleGroup.getTitle(),alCityObjectInPG.getId(),alCityObjectInPG.getTitle(), alCityObject.getPic().getId(),alCityObject.getIcon().getId());

        return dto;
    }
    public static  Collection<SearchResultCityObjectDTO> getSearchResultCityObjectsInPGDTOS(Collection<PGObject> objects){
        Collection<SearchResultCityObjectDTO> dtos = new ArrayList<SearchResultCityObjectDTO>();
        Iterator<PGObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            SearchResultCityObjectDTO dto = new SearchResultCityObjectDTO();
            PGObject object = iterator.next();
            dto = getSearchResultCityObjectDTO(object);
            dtos.add(dto);
        }

        return dtos;
    }
    public static  Collection<CityObjectDTO> getCityObjectsDTOS(Collection<BaseObject> objects, ActionService actionService, AttributeService attributeService){
        Collection<CityObjectDTO> dtos = new ArrayList<CityObjectDTO>();
        Iterator<BaseObject> iterator = objects.iterator();
        while (iterator.hasNext()) {
            CityObjectDTO dto = new CityObjectDTO();
            BaseObject object = iterator.next();
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
                                    action.getPoActionOwnerType().name());
        return dto;
    }
    public static PLData getInterpreterJSON(PuzzleLevel pl) throws IOException, ClassNotFoundException {
        byte[] plData = pl.getInterpreterFile();
        ByteArrayInputStream bis = new ByteArrayInputStream(plData);
        ObjectInputStream ois = new ObjectInputStream(bis);
        //FileOutputStream outputStream = new FileOutputStream("file.ser");
        //outputStream.write(plData);
        //FileInputStream inputStream = new FileInputStream("file.ser");
        PLData plData1 = (PLData) ois.readObject();
        return plData1;
    }
//    public static BoardGraphicDTO getBoardGraphicJSON(PLGround plGround) throws IOException, ClassNotFoundException {
//        byte[] boardGraphic = plGround.getBoardGraphic();
//        ByteArrayInputStream bis = new ByteArrayInputStream(boardGraphic);
//        ObjectInputStream ois = new ObjectInputStream(bis);
//        BoardGraphicDTO boardGraphicDTO = (BoardGraphicDTO) ois.readObject();
//        return boardGraphicDTO;
//    }


    /*
    public static BoardGraphicDTO getBoardGraphicJSON(byte[] boardGraphic) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(boardGraphic);
        ObjectInputStream ois = new ObjectInputStream(bis);
        FileOutputStream outputStream = new FileOutputStream("file.ser");
        outputStream.write(boardGraphic);
        FileInputStream inputStream = new FileInputStream("file.ser");
        BoardGraphicDTO plData1 = (BoardGraphicDTO) ois.readObject();
        return plData1;
    }
*/

}
