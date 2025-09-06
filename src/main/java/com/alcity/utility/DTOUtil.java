package com.alcity.utility;

import com.alcity.comparetors.*;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.plimpexport.*;
import com.alcity.dto.plimpexport.ruleexport.PostActionTreeExport;
import com.alcity.dto.plimpexport.ruleexport.RuleData;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.alobject.*;
import com.alcity.dto.appmember.*;
import com.alcity.dto.base.*;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.pgimport.PGObjectVariableImportDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;
import com.alcity.dto.puzzle.*;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.appmember.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
import com.alcity.repository.alobject.AttributeRepository;
import com.alcity.repository.alobject.AttributeValueRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.test.ruleimport_new.PostActionTreeImport_New;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.*;

import static java.util.stream.Collectors.toList;

public class DTOUtil {


    public static PLTemplateDTO getPLTemplateDTO(PLTemplate template){
        PLTemplateDTO dto= new PLTemplateDTO(template.getId(),template.getTitle(), template.getFromAge(), template.getToAge(), template.getPuzzleCategory().getId(),
                 template.getPuzzleGroupId(),template.getPuzzleLevelId(), template.getContent());
        return dto;
    }


    public static Collection<PLTemplateDTO> getPLTemplateDTOS(Collection<PLTemplate> templates){
        Iterator<PLTemplate> itr = templates.iterator();
        Collection<PLTemplateDTO> dtos = new ArrayList<>();
        while (itr.hasNext()) {
            PLTemplateDTO dto = getPLTemplateDTO(itr.next());
            dtos.add(dto);
        }
        return  dtos;
    }

    public static PLRuleEventDTO getPLRuleEventDTO(PLRuleEvent event){
        PLRuleEventDTO dto= new PLRuleEventDTO(event.getId(),event.getName(), event.getPlRuleEventType().name(), event.getEventId());
        return dto;
    }

    public static Collection<PLRuleEventDTO> getPLRuleEventDTOS(Collection<PLRuleEvent> plRuleEvents){
        Iterator<PLRuleEvent> itr = plRuleEvents.iterator();
        Collection<PLRuleEventDTO> dtos = new ArrayList<>();
        while (itr.hasNext()) {
            PLRuleEventDTO dto = getPLRuleEventDTO(itr.next());
            dtos.add(dto);
        }
        return  dtos;

    }
    public static PLGameInstanceDTO getPLGameInstanceDTO(PLGameInstance gameInstance){
        PLGameInstanceDTO dto = new PLGameInstanceDTO();
        dto.setId(gameInstance.getId());
        dto.setGameStatus(gameInstance.getGameStatus().name());
        dto.setAppMemmberId(gameInstance.getPlayer().getId());
        dto.setUserName(gameInstance.getPlayer().getUsername());
        dto.setStartPlayTime(gameInstance.getStartPlayTime());
        dto.setPuzzleLevelId(gameInstance.getPuzzleLevel().getId());
        dto.setPuzzleLevelTitle(gameInstance.getPuzzleLevel().getTitle());
        return  dto;
    }
    public static PLDTO getPuzzleLevelDTO(PuzzleLevel entity) {
        PLDTO dto = new PLDTO();
        Collection<PLGround> plGrounds = entity.getPlGrounds();
        Iterator<PLGround> iterator = plGrounds.iterator();
        PLGround plGround = new PLGround();
        while(iterator.hasNext()) {
            plGround = iterator.next();
        }
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setCode(entity.getCode());
        dto.setApproveDate(entity.getApproveDate());
        dto.setTitle(entity.getTitle());
        dto.setToAge(entity.getToAge());
        dto.setFromAge(entity.getFromAge());
        dto.setCol(plGround.getNumColumns());
        dto.setRow(plGround.getNumRows());
        dto.setOrdering(entity.getOrdering());
        dto.setPuzzleGroupId(entity.getPuzzleGroup().getId());
        dto.setPuzzleGroupTitle(entity.getPuzzleGroup().getTitle());
        dto.setPlGroundId(plGround.getId());
        dto.setMaxScore(entity.getMaxScore());
        dto.setFirstStarScore(entity.getFirstStarScore());
        dto.setSecondStarScore(entity.getSecondStarScore());
        dto.setThirdStartScore(entity.getThirdStartScore());
        dto.setPuzzleLevelPrivacy(entity.getPuzzleLevelPrivacy().getValue());
        dto.setPuzzleLevelStatus(entity.getPuzzleLevelStatus().name());
        dto.setPuzzleLevelDifficulty(entity.getPuzzleDifficulty().name());
        dto.setUpdated(entity.getUpdated());
        dto.setCreated(entity.getCreated());
        dto.setCreatedBy(entity.getCreatedBy().getUsername());
        dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
        dto.setCreatedById(entity.getCreatedBy().getId());
        dto.setUpdatedById(entity.getUpdatedBy().getId());


        dto.setIconId(entity.getIcon().getId());
        dto.setPicId(entity.getPicture().getId());

        return dto;
    }
    public static void copyActionFromTo(Long fromOwnerId, Long toOwnerId,AttributeOwnerType from,AttributeOwnerType to,
                                        ActionService actionService,POActionOwnerType  fromAction,POActionOwnerType toAction,
                                        AttributeService attributeService, AttributeValueService attributeValueService) {
        Collection<ObjectAction> actions = actionService.findByOwnerObjectidAndPoActionOwnerType(fromOwnerId,fromAction);
        Iterator<ObjectAction> itr = actions.iterator();
        while(itr.hasNext()){
            ObjectAction objectAction = itr.next();
            ObjectActionType objectActionType = ObjectActionType.getByTitle(objectAction.getObjectAction().name());
            ObjectAction newObjectAction = new ObjectAction(toAction,toOwnerId,objectActionType,objectAction.getActionRenderer(),
                    objectAction.getVersion(),DateUtils.getNow(),DateUtils.getNow(),
                    objectAction.getCreatedBy(), objectAction.getUpdatedBy());
            actionService.save(newObjectAction);
        }
    }
    public static PLDTO getPuzzleLevelDTO(Optional<PuzzleLevel> puzzleLevelOptional) {
        PuzzleLevel puzzleLevel = new PuzzleLevel();
        PLDTO dto= new PLDTO();

        if(puzzleLevelOptional.isPresent()){
            puzzleLevel = puzzleLevelOptional.get();
            dto = DTOUtil.getPuzzleLevelDTO(puzzleLevel);
        } else dto = null;

        return dto;
    }

    public static AttributeValueDTO getAttributeValueDTO(AttributeValue value){
        Long bindedAttribute =null;
        if(value.getBindedAttributeId() != null)
            bindedAttribute = value.getBindedAttributeId().getId();
        AttributeValueDTO valueDTO= new AttributeValueDTO(value.getId(),value.getBooleanValue(), value.getLongValue(), value.getDoubleValue(),
                value.getIntValue(), value.getBinaryContentId() ,value.getExpressionValue(),value.getExpression(),
                value.getStringValue(),value.getObjectValue(),value.getAttributeId().getId(),bindedAttribute, value.getOwnerId(), value.getOwnerType().name());
        return valueDTO;
    }
    public static AttributeValue getAttributeValueFromPGVariableImport(PGObjectVariableImportDTO dto,Attribute attribute,AppMember createdBy){
        Attribute bindedAttribute =null;
        DataType dataType =  DataType.getByTitle(dto.getDataType());
        Boolean booleanValue=null;
        Long longValue=null;
        Float floatValue=null;
        Integer intValue=null;
        Long binaryContentId=null;
        Boolean isExpressionValue=false;
        String expressionValue=null;
        String objectValue=null;
        String stringValue=null;
        if(dataType.equals(DataType.Boolean))     booleanValue=Boolean.valueOf(dto.getValue());
        if(dataType.equals(DataType.Long))     longValue=Long.valueOf(dto.getValue());
        if(dataType.equals(DataType.Float))     floatValue=Float.valueOf(dto.getValue());
        if(dataType.equals(DataType.Integer))     intValue=Integer.valueOf(dto.getValue());
        if(dataType.equals(DataType.Binary))     binaryContentId=Long.valueOf(dto.getValue());
        if(dataType.equals(DataType.String))     stringValue=dto.getValue();
        System.out.println("test");
        if(dto.getExpression())    {
            isExpressionValue=Boolean.TRUE;
            expressionValue = dto.getExpressionValue();
        }

        AttributeValue  attributeValue = new AttributeValue(booleanValue,intValue,longValue,stringValue,
                objectValue,floatValue,binaryContentId, expressionValue,isExpressionValue,bindedAttribute ,attribute,
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy,createdBy,attribute.getOwnerId(),attribute.getAttributeOwnerType());
        return attributeValue;
    }

    public static AttributeValue getAttributeValueFromPLVariableImport(AttributeData dto, Attribute attribute, AppMember createdBy, Long ownerId, AttributeOwnerType ownerType){
        Attribute bindedAttribute =null;
        DataType dataType =  DataType.getByTitle(dto.getType());
        Boolean booleanValue=null;
        Long longValue=null;
        Float floatValue=null;
        Integer intValue=null;
        Long binaryContentId=null;
        Boolean isExpressionValue=false;
        String expressionValue=null;
        String objectValue=null;
        String stringValue=null;
        if(dto.getValue() == null || dto.getValue().equalsIgnoreCase("")){
            if(dataType.equals(DataType.Boolean)) dto.setValue("false");
            if(dataType.equals(DataType.Long)) dto.setValue("1L");
            if(dataType.equals(DataType.Integer)) dto.setValue("1");
            if(dataType.equals(DataType.Binary)) dto.setValue("1");
            if(dataType.equals(DataType.String)) dto.setValue("1");
            if(dataType.equals(DataType.Float)) dto.setValue("1.0");

        }
        if(dto.getExpression()==null || !dto.getExpression()){
            isExpressionValue=Boolean.FALSE;
            expressionValue = null;
            if(dataType.equals(DataType.Boolean))     booleanValue=Boolean.valueOf(dto.getValue());
            if(dataType.equals(DataType.Long))     longValue=Long.valueOf(dto.getValue());
            if(dataType.equals(DataType.Float))     floatValue=Float.valueOf(dto.getValue());
            if(dataType.equals(DataType.Integer))     intValue=Integer.valueOf(dto.getValue());
            if(dataType.equals(DataType.Binary))     binaryContentId=Long.valueOf(dto.getValue());
            if(dataType.equals(DataType.String))     stringValue=dto.getValue();
        }
        else if(dto.getExpression()) {
            isExpressionValue=Boolean.TRUE;
            expressionValue = dto.getExpressionValue();
        }

        AttributeValue  attributeValue = new AttributeValue(booleanValue,intValue,longValue,stringValue,
                objectValue,floatValue,binaryContentId, expressionValue,isExpressionValue,bindedAttribute ,attribute,
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy,createdBy,ownerId,ownerType);
        return attributeValue;
    }

    public static PuzzleLevelStepMappingDTO puzzleLevelJourneyStepMapping(PuzzleLevel pl, JourneyStep step) {
        PuzzleLevelStepMappingDTO dto = new PuzzleLevelStepMappingDTO();
        dto.setPlId(pl.getId());
        dto.setPlTitle(pl.getTitle());
        dto.setPlCode(pl.getCode());
        dto.setPlApproveDate(pl.getApproveDate());
        dto.setPlFromAge(pl.getFromAge());
        dto.setPlToAge(pl.getToAge());
        dto.setFirstStarScore(pl.getFirstStarScore());
        dto.setSecondStarScore(pl.getSecondStarScore());
        dto.setThirdStartScore(pl.getThirdStartScore());
        dto.setPlMaxScore(pl.getMaxScore());
        dto.setPlOrdering(pl.getOrdering());
        dto.setPgId(pl.getPuzzleGroup().getId());
        dto.setPgTitle(pl.getPuzzleGroup().getTitle());

        dto.setStepId(step.getId());
        dto.setStepTitle(step.getTitle());
        dto.setStepOrdering(step.getOrdering());
        dto.setStepXpos(step.getXpos());
        dto.setStepYpos(step.getYpos());

        dto.setJourneyId(step.getJourney().getId());
        dto.setJourneyTitle(step.getJourney().getTitle());
        dto.setJourneyOrdering(step.getJourney().getOrdering());
        return dto;
    }
    public static Collection<AttributeValueDTO> getAttributesValueDTOS(Collection<AttributeValue> input) {
        Collection<AttributeValueDTO> dtos = new ArrayList<AttributeValueDTO>();
        Iterator<AttributeValue> itr = input.iterator();
        while (itr.hasNext()) {
            AttributeValueDTO dto = getAttributeValueDTO(itr.next());
            dtos.add(dto);
        }
        return  dtos;
    }
    public static AttributeValueDTO getFirstAttributeValueDTO(Collection<AttributeValueDTO> valueDTOS) {
        Iterator<AttributeValueDTO> itr = valueDTOS.iterator();
        if(itr.hasNext()) return itr.next();
        return null;
    }
    public static AttributeDTO getAttributeDTO(Attribute att){
        Collection<AttributeValueDTO> valueDTOS = getAttributesValueDTOS(att.getAttributeValues()) ;
        AttributeValueDTO valueDTO = getFirstAttributeValueDTO(valueDTOS);
        AttributeDTO dto = new AttributeDTO(att.getId(), att.getName(),
                att.getOwnerId(), att.getAttributeOwnerType().name(),
                att.getDataType().name(),valueDTO);
        dto.setAttributeValueDTO(valueDTO);
        return dto;
    }

    public static AttributeDTOSave mapAttributesDTO(PGObjectVariableImportDTO variableImportDTO) {
        AttributeDTOSave attributeDTOSave= new AttributeDTOSave();

        return  attributeDTOSave;
    }

    public static Collection<AttributeDTOSave> mapAttributesDTOS(Collection<PGObjectVariableImportDTO> variableImportDTOS) {
        Collection<AttributeDTOSave> dtos = new ArrayList<AttributeDTOSave>();
        Iterator<PGObjectVariableImportDTO> itr = variableImportDTOS.iterator();
        while(itr.hasNext()){
            PGObjectVariableImportDTO importDTO = itr.next();
            AttributeDTOSave attributeDTOSave= new AttributeDTOSave();

        }

        return dtos;

    }

    public static AttributeDTO getAttributeDTOSave(Attribute att){
        Collection<AttributeValueDTO> valueDTOS = getAttributesValueDTOS(att.getAttributeValues()) ;
        AttributeValueDTO valueDTO = getFirstAttributeValueDTO(valueDTOS);
        AttributeDTO dto = new AttributeDTO(att.getId(), att.getName(),
                att.getOwnerId(), att.getAttributeOwnerType().name(),
                att.getDataType().name(),valueDTO);
        return dto;
    }

    public static Collection<AttributeDTO> getAttributesDTOS(Collection<Attribute> attributes) {
        Collection<AttributeDTO> dtos = new ArrayList<AttributeDTO>();
        Iterator<Attribute> itr = attributes.iterator();
        while (itr.hasNext()) {
            Attribute attribute = itr.next();
            AttributeDTO dto = getAttributeDTO(attribute);
            dtos.add(dto);
        }
        return dtos;
    }

//    public static Collection<AttributeDTOSave> saveAttributesDTOSFromImport(Collection<RecordDataImport> imports,AttributeService attributeService) {
//        Collection<AttributeDTOSave> dtos = new ArrayList<AttributeDTOSave>();
//        Iterator<RecordDataImport> itr = imports.iterator();
//        while (itr.hasNext()) {
//            RecordDataImport dataImport = itr.next();
//            if(dataImport.getId()>0){  //attribute is exist and should be load
//                Optional<Attribute> attributeOptional = attributeService.findById(dataImport.getId());
//                if(attributeOptional.isPresent()){
// //                   AttributeValue attributeValue =
//                }
//            }
//            AttributeDTOSave dto = null;
//            dtos.add(dto);
//        }
//        return dtos;
//    }
    public static Set<Long> getBinaryContentFromAttributeDTOS(Collection<Attribute> attributes) {
        Set<Long> dtos = new HashSet<>();
        Iterator<Attribute> itr = attributes.iterator();
        while (itr.hasNext()) {
            Long contentId=-1L;
            Attribute attribute = itr.next();
            Optional<AttributeValue> valueOptional = attribute.getAttributeValues().stream().findFirst();
            if(valueOptional.isPresent())
                dtos.add(valueOptional.get().getBinaryContentId());
        }
        return dtos;
    }

    public static JourneyStepDTO getJorenyStepsDTO(JourneyStep entity) {
        JourneyStepDTO dto = new JourneyStepDTO();
        dto.setStepId(entity.getId());
        dto.setStepTitle(entity.getTitle());
        dto.setStepOrdering(entity.getOrdering());
        dto.setStepXpos(entity.getXpos());
        dto.setStepYpos(entity.getYpos());
        dto.setPuzzleGroupTitle(entity.getPuzzleGroup().getTitle());
        dto.setPuzzleGroupId(entity.getPuzzleGroup().getId());
        dto.setPuzzleGroupIconId(entity.getPuzzleGroup().getIcon().getId());
        dto.setJourneyId(entity.getJourney().getId());
        dto.setJourneyTitle(entity.getTitle());
        dto.setVersion(entity.getVersion());
        dto.setCreated(entity.getCreated());
        dto.setUpdated(entity.getUpdated());
        return dto;
    }
    public static Collection<JourneyStepDTO> getJorneyStepsDTOS(Collection<JourneyStep> input) {
        Collection<JourneyStepDTO> dtos = new ArrayList<JourneyStepDTO>();
        Iterator<JourneyStep> itr = input.iterator();
        while (itr.hasNext()) {
            JourneyStep journeyStep = itr.next();
            JourneyStepDTO dto = getJorenyStepsDTO(journeyStep);
            dtos.add(dto);
        }
        return dtos;
    }

    public static boolean isAttributeChanged(Attribute attribute , AttributeDTOSave newAttribute) {
        if(!attribute.getName().equalsIgnoreCase(newAttribute.getName())
             || attribute.getDataType().equals(newAttribute.getDataType())
        ){
            return true;
        }

        return false;
    }

    public static boolean isAttributeValueSChanged(Collection<AttributeValue> values , AttributeValueDTOSave newValues) {
        Iterator<AttributeValue> itr = values.iterator();
        while(itr.hasNext()){
            if(isAttributeValueChanged(itr.next(),newValues))
                return true;
        }
        return false;
    }
        public static boolean isAttributeValueChanged(AttributeValue value , AttributeValueDTOSave newValue) {
        Attribute bindedAttribute = value.getBindedAttributeId();
        Long bindedAttributeId=null;
        if(bindedAttribute != null)   bindedAttributeId = bindedAttribute.getId();

        Boolean booleanValue = value.getBooleanValue();
        Boolean newBooleanValue = newValue.getBooleanValue();

        String objectValue = value.getObjectValue();
        String newObjectValue = newValue.getObjectValue();

        Integer intValue = value.getIntValue();
        Integer newIntValue = newValue.getIntValue();

        Float doubleValue = value.getDoubleValue();
        Float newDoubleValue = newValue.getDoubleValue();

        String experssionValue = value.getExpressionValue();
        String newExperssionValue = newValue.getExpressionValue();

         Boolean isExpression = value.getExpression();
         Boolean newisExpression = newValue.getExpression();

            Long binaryContentdIdValue = value.getBinaryContentId();
        Long newBinaryContentdIdValue = newValue.getBinaryContentId();

        Long longValue = value.getLongValue();
        Long newLongValue = newValue.getLongValue();

        String stringValue = value.getStringValue();
        String newStringValue = newValue.getStringValue();


        Boolean isBindedAttributeChanged = false;
        Boolean isBooleanValueChanged = false;
        Boolean isObjectValueChanged = false;
        Boolean isIntValueChanged = false;
        Boolean isDoubleValueChanged = false;
        Boolean isExperssionValueChanged = false;
        Boolean isBinaryContentdIdValueChanged = false;
        Boolean isLongValueChanged = false;
        Boolean isStringValueChanged = false;

        if(bindedAttributeId != newValue.getBindedAttributeId())
            isBindedAttributeChanged = true;
        if(booleanValue != newBooleanValue)
            isBooleanValueChanged = true;

        if((objectValue !=null && newObjectValue !=null)  ) {
            if (!objectValue.equalsIgnoreCase(newObjectValue))
                isObjectValueChanged = true;
        }
        else if((objectValue==null && newObjectValue !=null) || (objectValue!=null && newObjectValue ==null)) {
            isObjectValueChanged = true;
        }else if(objectValue==null && newObjectValue ==null){
            isObjectValueChanged = false;
        }


        if(intValue != newIntValue)
            isIntValueChanged = true;
        if(doubleValue != newDoubleValue)
            isIntValueChanged = true;

        if((experssionValue !=null && newExperssionValue !=null)  ) {
            if (!experssionValue.equalsIgnoreCase(newExperssionValue))
                isExperssionValueChanged = true;
        }
        else if((experssionValue==null && newExperssionValue !=null) || (experssionValue!=null && newExperssionValue ==null)) {
            isExperssionValueChanged = true;
        }else if(experssionValue==null && newExperssionValue ==null){
            isExperssionValueChanged = false;
        }


        if(binaryContentdIdValue != newBinaryContentdIdValue)
            isBinaryContentdIdValueChanged = true;
        if(longValue != newLongValue)
            isLongValueChanged = true;

        if((stringValue !=null && newStringValue !=null)  ) {
            if (!stringValue.equalsIgnoreCase(newStringValue))
                isStringValueChanged = true;
        }
        else if((stringValue==null && newStringValue !=null) || (stringValue!=null && newStringValue ==null)) {
                isStringValueChanged = true;
        }else if(stringValue==null && newStringValue ==null){
            isStringValueChanged = false;
        }

        if( isBooleanValueChanged  ||  isBindedAttributeChanged || isObjectValueChanged || isIntValueChanged
         || isDoubleValueChanged || isExperssionValueChanged  || isStringValueChanged ||
                isBinaryContentdIdValueChanged  || isLongValueChanged  ||  isStringValueChanged)
        {
            return true;
        }
        return false;
   }

    public static void saveNewValue(AttributeValue oldAttributeValue, AttributeValueDTOSave newValue, AttributeRepository attributeRepository, AttributeValueRepository attributeValueRepository) {
        AttributeOwnerType newOwnerType = AttributeOwnerType.getByTitle(newValue.getNewOwnerType());
        Attribute  bindedAttribute=null;
        if(newValue.getBindedAttributeId() != null ){
            Optional<Attribute> bindedAttributeOptional = attributeRepository.findById(newValue.getBindedAttributeId());
            bindedAttribute = bindedAttributeOptional.get();
        }else{
            bindedAttribute=null;
        }
        AttributeValue attributeValue = new AttributeValue(newValue.getBooleanValue(),newValue.getIntValue(),newValue.getLongValue(),
                newValue.getStringValue(),newValue.getObjectValue(),
                newValue.getDoubleValue(), newValue.getBinaryContentId(),newValue.getExpressionValue(),newValue.getExpression(),bindedAttribute,oldAttributeValue.getAttributeId(),
                1L,DateUtils.getNow(),DateUtils.getNow(),oldAttributeValue.getCreatedBy(),oldAttributeValue.getUpdatedBy(), newValue.getNewOwnerId(),newOwnerType );
        attributeValueRepository.save(attributeValue);

    }
   public static void overwiteValue(AttributeValue oldValue , AttributeValueDTOSave newValue, AttributeRepository attributeRepository, AttributeValueRepository attributeValueRepository){
       Optional<Attribute> attributeOptional =attributeRepository.findById(newValue.getAttributeId());
       AttributeOwnerType newOwnerType = AttributeOwnerType.getByTitle(newValue.getNewOwnerType());
       Attribute  bindedAttribute=null;
       if(newValue.getBindedAttributeId() != null ){
           Optional<Attribute> bindedAttributeOptional = attributeRepository.findById(newValue.getBindedAttributeId());
           bindedAttribute = bindedAttributeOptional.get();
       }else{
           bindedAttribute=null;
       }
       oldValue.setLongValue(newValue.getLongValue());
       oldValue.setIntValue(newValue.getIntValue());
       oldValue.setDoubleValue(newValue.getDoubleValue());
       oldValue.setObjectValue(newValue.getObjectValue());
       oldValue.setStringValue(newValue.getStringValue());
       oldValue.setAttributeId(attributeOptional.get());
       oldValue.setExpression(newValue.getExpression());
       oldValue.setExpressionValue(newValue.getExpressionValue());
       oldValue.setOwnerId(newValue.getNewOwnerId());
       oldValue.setOwnerType(newOwnerType);
       oldValue.setBooleanValue(newValue.getBooleanValue());
       oldValue.setBindedAttributeId(bindedAttribute);
       attributeValueRepository.save(oldValue);

   }
    public static void saveNewAttributeValue(Attribute attribute , AttributeDTOSave newValue, AttributeRepository attributeRepository, AttributeValueRepository attributeValueRepository){
        AttributeValueDTOSave newAttributeValue = newValue.getAttributeValueDTOSave();
        AttributeOwnerType attributeOwnerType = AttributeOwnerType.getByTitle(newAttributeValue.getNewOwnerType());
        Optional<AttributeValue> attributeValueOptional = attributeValueRepository.findById(newAttributeValue.getId());
        if(attributeValueOptional.isPresent()){
            AttributeValue oldvalue = attributeValueOptional.get();
            boolean isValueChanged = isAttributeValueChanged(oldvalue,newAttributeValue);
            if(isValueChanged) {
                if ( oldvalue.getOwnerId().equals(newAttributeValue.getNewOwnerId()) ) {
                     overwiteValue(oldvalue, newAttributeValue, attributeRepository, attributeValueRepository);
                }else{
                    saveNewValue(oldvalue,newAttributeValue, attributeRepository, attributeValueRepository);
                }

            }else{
                System.out.println("value not changed");
            }

        }

    }

    public static Collection<PLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> inputs) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();

        Iterator<PuzzleLevel> itr = inputs.iterator();
        while (itr.hasNext()) {
            PuzzleLevel pl = itr.next();
            PLDTO dto = getPuzzleLevelDTO(pl);
            dtos.add(dto);
        }
        return dtos;
    }
    public static <E extends Enum<?>> Collection<EnumDTO> getEnumByClass(Class<E> c)
    {
        Collection<EnumDTO> enumDTOS = new ArrayList<EnumDTO>();
        for (E o: c.getEnumConstants()) {
            EnumDTO enumDTO = new EnumDTO(o.ordinal(),o.name(), o.name());
            enumDTOS.add(enumDTO);
        }
        return enumDTOS;
    }

    public static Collection<PGLearningSkillDTO> getPGLearningSkillContentDTOS(Collection<PGLearningSkill> input) {
        Collection<PGLearningSkillDTO> output = new ArrayList<PGLearningSkillDTO>();
        Iterator<PGLearningSkill> itr = input.iterator();
        while (itr.hasNext()) {
            PGLearningSkillDTO dto = new PGLearningSkillDTO();
            PGLearningSkill lsc = itr.next();

            dto.setId(lsc.getId());
            dto.setLearningSkillId(lsc.getLearningSkill().getId());
            dto.setLearningSkillTitle(lsc.getLearningSkill().getTitle());
            dto.setLearningContentId(lsc.getLearningContent().getId());
            dto.setPuzzleGroupId(lsc.getPuzzleGroup().getId());
            dto.setPuzzleGroupTitle(lsc.getPuzzleGroup().getTitle());
            dto.setLearningContentDescText(lsc.getLearningContent().getDescText());
            output.add(dto);
        }
        return output;
    }
    public static PGObjectDTO getALCityObjectInPGDTO(PGObject alCityObjectInPG) {
        PGObjectDTO dto = new PGObjectDTO();
        dto.setId(alCityObjectInPG.getId());
        dto.setCode(alCityObjectInPG.getCode());
        dto.setTitle(alCityObjectInPG.getTitle());

        dto.setPuzzleGroup(alCityObjectInPG.getPuzzleGroup().getTitle());
        dto.setPuzzleGroupId(alCityObjectInPG.getPuzzleGroup().getId());
        dto.setAlCityObjectTitle(alCityObjectInPG.getAlCityObject().getTitle());
        dto.setAlCityObjectId(alCityObjectInPG.getAlCityObject().getId());
//        dto.setVersion(alCityObjectInPG.getVersion());
//        dto.setCreated(alCityObjectInPG.getCreated());
//        dto.setUpdated(alCityObjectInPG.getUpdated());
//        dto.setCreatedBy(alCityObjectInPG.getCreatedBy().getUsername());
//        dto.setUpdatedBy(alCityObjectInPG.getUpdatedBy().getUsername());

        return  dto;
    }
    public static InstanceDTO getALCityObjectInPLDTO(Instance instance) {
        InstanceDTO dto = new InstanceDTO();
        dto.setId(instance.getId());
        dto.setPGObjectTitle(instance.getName());
        dto.setCol(instance.getCol());
        dto.setRow(instance.getRow());

        dto.setzOrder(instance.getzOrder());
        dto.setPGObjectId(instance.getAlCityObjectInPG().getId());
        dto.setPuzzleLevelId(instance.getPuzzleLevel().getId());

//        dto.setVersion(alCityObjectInPG.getVersion());
//        dto.setCreated(alCityObjectInPG.getCreated());
//        dto.setUpdated(alCityObjectInPG.getUpdated());
//        dto.setCreatedBy(alCityObjectInPG.getCreatedBy().getUsername());
//        dto.setUpdatedBy(alCityObjectInPG.getUpdatedBy().getUsername());

        return  dto;
    }
    public static Collection<PGObjectDTO> getALCityObjectInPGDTOS(Collection<PGObject> input) {
        Collection<PGObjectDTO> dtos = new ArrayList<PGObjectDTO>();
        Iterator<PGObject> itr = input.iterator();
        while (itr.hasNext()) {
            PGObject alCityObjectInPG = itr.next();
            PGObjectDTO dto = getALCityObjectInPGDTO(alCityObjectInPG);
            dtos.add(dto);
        }
        return dtos;

    }

    public static ActionData getActionDTO(ObjectAction input) {
        ActionData dto = new ActionData();
        dto.setId(input.getId());
        dto.setActionName(input.getObjectAction());
        dto.setHandler(input.getActionRenderer().getHandler());
        return dto;
    }
    public static Collection<ActionData> getObjectActionDTOS(Collection<ObjectAction> input) {
        Collection<ActionData> dtos = new ArrayList<ActionData>();
        Iterator<ObjectAction> itr = input.iterator();
        while (itr.hasNext()) {
            ActionData dto = getActionDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static Collection<PLDTO> getPuzzleLevelsByPuzzleGroup(PuzzleGroup input) {
       Collection<PLDTO> dtos = new ArrayList<PLDTO>();
       dtos = getPuzzleLevelDTOS(input.getPuzzleLevels());

        Comparator plComparetorByFromAge = new PLComparatorByFromAge();
        List<PLDTO> sortedList =new ArrayList<PLDTO>();
        sortedList = dtos.stream().collect(toList());
        sortedList.sort(plComparetorByFromAge);
        return sortedList;
    }

    public static Collection<PLDTO> getPuzzleLevelsDTOS(Collection<PuzzleGroup> inputs) {
        Collection<PLDTO> outputs = new ArrayList<PLDTO>();
        Iterator<PuzzleGroup> itr = inputs.iterator();
        while (itr.hasNext()) {
            Collection<PLDTO> dtos = new ArrayList<PLDTO>();
            dtos = getPuzzleLevelsByPuzzleGroup(itr.next());
            outputs.addAll(dtos);
        }
        return outputs;
    }

    public static Collection<PGDTO> getPuzzleGroupDTOS(Collection<PuzzleGroup> input) {
        Collection<PGDTO> dtos = new ArrayList<PGDTO>();
        Iterator<PuzzleGroup> itr = input.iterator();
        while (itr.hasNext()) {
            PGDTO dto = getPuzzleGroupDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static PGDTO getPuzzleGroupDTO(PuzzleGroup input) {
        PGDTO dto = new PGDTO();
        dto.setId(input.getId());
        dto.setVersion(input.getVersion());
        dto.setCreated(input.getCreated());
        dto.setUpdated(input.getUpdated());
        dto.setTitle(input.getTitle());
        dto.setPuzzleCategoryId(input.getPuzzleCategory().getId());
        dto.setIconId(input.getIcon().getId());
        dto.setPicId(input.getPic().getId());
        dto.setCreated(input.getCreated());
        dto.setUpdated(input.getUpdated());
        dto.setCreatedBy(input.getCreatedBy().getUsername());
        dto.setUpdatedBy(input.getUpdatedBy().getUsername());

        return dto;
    }

    public static Collection<PuzzleCategoryDTO> getPuzzleCategoryDTOS(Collection<PuzzleCategory> input) {
        Collection<PuzzleCategoryDTO> output = new ArrayList<PuzzleCategoryDTO>();
        PuzzleCategoryDTO dto = new PuzzleCategoryDTO();
        Iterator<PuzzleCategory> itr = input.iterator();
        while(itr.hasNext()) {
            PuzzleCategory puzzleCategory = itr.next();
            dto = DTOUtil.getPuzzleCategoryDTO(puzzleCategory);
            output.add(dto);
        }
        return output;
    }
    public static PuzzleCategoryDTO getPuzzleCategoryDTO(PuzzleCategory pc) {
        PuzzleCategoryDTO pcDTO = new PuzzleCategoryDTO();
        Collection<PuzzleGroup> puzzleGroupCollection = pc.getPuzzleGroupCollection();
        pcDTO.setId(pc.getId());
        pcDTO.setLabel(pc.getLabel());
        pcDTO.setValue(pc.getValue());
        pcDTO.setTemplateEditor(pc.getTemplateEditor());
        pcDTO.setVersion(pc.getVersion());
        pcDTO.setCreated(pc.getCreated());
        pcDTO.setCreatedBy(pc.getCreatedBy().getUsername());
        pcDTO.setCreatedById(pc.getCreatedBy().getId());

        pcDTO.setUpdated(pc.getUpdated());
        pcDTO.setUpdatedBy(pc.getUpdatedBy().getUsername());
        pcDTO.setUpdatedById(pc.getUpdatedBy().getId());
        return pcDTO;
    }

    public static PLObjectiveDTO getPuzzleLevelObjectiveDTO(PLObjective plObjective){
        PLObjectiveDTO plObjectiveDTO = new PLObjectiveDTO();
        plObjectiveDTO.setId(plObjective.getId());
        plObjectiveDTO.setVersion(plObjective.getVersion());
        plObjectiveDTO.setTitle(plObjective.getTitle());
        plObjectiveDTO.setCondition(plObjective.getCondition());
        plObjectiveDTO.setDescription(plObjective.getDescription());
        plObjectiveDTO.setRewardAmount(plObjective.getRewardAmount());
        plObjectiveDTO.setSkillAmount(plObjective.getSkillAmount());
        plObjectiveDTO.setSkillId(plObjective.getLearningSkill().getId());
        plObjectiveDTO.setPuzzleLevelId(plObjective.getPuzzleLevel().getId());
        plObjectiveDTO.setUpdated(plObjective.getUpdated());
        plObjectiveDTO.setCreated(plObjective.getCreated());
        plObjectiveDTO.setCreatedBy(plObjective.getCreatedBy().getUsername());
        plObjectiveDTO.setUpdatedBy(plObjective.getUpdatedBy().getUsername());
        plObjectiveDTO.setUpdatedById(plObjective.getUpdatedBy().getId());
        plObjectiveDTO.setCreatedById(plObjective.getCreatedBy().getId());
        plObjectiveDTO.setSkillLable(plObjective.getLearningSkill().getTitle());
        plObjectiveDTO.setSkillValue(plObjective.getLearningSkill().getTitle());
        plObjectiveDTO.setWalletItemId(plObjective.getWalletItem().getId());
        plObjectiveDTO.setWalletItemTitle(plObjective.getWalletItem().getValue());
        return  plObjectiveDTO;
    }

    public static Collection<PLObjectiveDTO> getPuzzleLevelObjectiveDTOS(PuzzleLevel input) {
        Collection<PLObjectiveDTO> output = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> plObjectiveCollection = input.getPlObjectives();
        Iterator<PLObjective> itr = plObjectiveCollection.iterator();

        while (itr.hasNext()) {
            PLObjectiveDTO dto = getPuzzleLevelObjectiveDTO(itr.next());
            output.add(dto);
        }
        return output;
    }


    //this method used for create Interpreter json
    public static Collection<PLObjectiveData> getPLObjectiveData(PuzzleLevel input) {
        Collection<PLObjectiveData> output = new ArrayList<PLObjectiveData>();
        Collection<PLObjective> puzzleLevelObjectiveCollection = input.getPlObjectives();

        Iterator<PLObjective> itr_objectives = puzzleLevelObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjective puzzleLevelObjective = itr_objectives.next();
            PLObjectiveData dto = new PLObjectiveData();
            dto.setId(puzzleLevelObjective.getId());
            dto.setTitle(puzzleLevelObjective.getTitle());
            dto.setDescription(puzzleLevelObjective.getDescription());
            dto.setCondition(puzzleLevelObjective.getCondition());
            dto.setRewardAmount(puzzleLevelObjective.getRewardAmount());
            dto.setRewardId(puzzleLevelObjective.getWalletItem().getId());
            dto.setSkillAmount(puzzleLevelObjective.getSkillAmount());
            dto.setSkillId(puzzleLevelObjective.getLearningSkill().getId());

            output.add(dto);
        }
        return output;
    }
  public static PlLearningTopicDTO getPl_LearningTopicDTO(PLLearningTopic entity){
        PlLearningTopicDTO dto = new PlLearningTopicDTO();
        PuzzleLevel puzzleLevel = entity.getPuzzleLevel();
        LearningTopic learningTopic = entity.getLearningTopic();
        LearningContent learningContent = entity.getLearningContent();
        BinaryContent binaryContent = learningContent.getBinaryContent();

        dto.setId(entity.getId());
        dto.setPuzzleLevelId(puzzleLevel.getId());
        dto.setPuzzleLevelTitle(puzzleLevel.getTitle());

        dto.setLearningTopicId(learningTopic.getId());
        dto.setLearningTopicTitle(learningTopic.getTitle());

        dto.setLearningContentId(learningContent.getId());
        dto.setLearningContentDescBrief(learningContent.getDescBrief());
        dto.setLearningContentDescText(learningContent.getDescText());

        dto.setBinaryContentId(binaryContent.getId());
        dto.setLearningContentFileName(binaryContent.getFileName());

        return dto;
    }
    public static String getBoardGraphicJSON(byte[] boardGraphic) throws IOException, ClassNotFoundException {
        ByteArrayInputStream bis = new ByteArrayInputStream(boardGraphic);
        ObjectInputStream ois = new ObjectInputStream(bis);
        String plData1 = (String) ois.readObject();
        return plData1;
    }
    public static Collection<PlLearningTopicDTO> getPl_LearningTopicDTOS(PuzzleLevel puzzleLevel) {
        Collection<PlLearningTopicDTO> dtos = new ArrayList<PlLearningTopicDTO>();
        Collection<PLLearningTopic> learningTopics = puzzleLevel.getLearningTopics();
        Iterator<PLLearningTopic> itr = learningTopics.iterator();
        PlLearningTopicDTO dto = new PlLearningTopicDTO();
        while(itr.hasNext()) {
            PLLearningTopic pl_learningTopic = itr.next();
            dto = getPl_LearningTopicDTO(pl_learningTopic);
            dtos.add(dto);
        }

        return dtos;
    }

    public static Collection<BinaryContentDTO> getBinaryContentsWithoutContent(Collection<BinaryContent> input) {
        Collection<BinaryContentDTO> dtos = new ArrayList<BinaryContentDTO>();
        Iterator<BinaryContent> itr = input.iterator();

        while (itr.hasNext()) {
            BinaryContentDTO dto = getBinaryContentDTOWithoutContent(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static BinaryContentDTO getBinaryContentDTOWithoutContent(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(), content.getCreatedBy().getUsername(), content.getUpdatedBy().getUsername(),
                content.getFileName(), content.getSize(), null, content.getThumbnail(),content.getIos3Dcontent(),content.getAndriod3Dcontent(),content.getWeb3Dcontent(),
                content.getContentType().name(),content.getIs3dContent(),
                content.getTag1(), content.getTag2(), content.getTag3());
        return binaryContentDTO;
    }


    public static AppMemberDTO getAppMemberDTO(AppMember member){
        String userName="admin";
        if(member.getCreatedBy() == null || member.getCreatedBy() == null)
            userName="admin";
        if(member.getLanguage() ==null) member.setLanguage(Language.English);

        AppMemberDTO dto = new AppMemberDTO(member.getId(),member.getAge(),member.getLanguage().name(),
                member.getUsername(),member.getPassword(),member.getIcon().getThumbnail(),member.getIcon().getId(), member.getNickname(),
                member.getMobile(), member.getEmail(),member.getGender().name(),member.getMemberType().getValue(),
                member.getVersion(), member.getCreated(), member.getUpdated(), userName, userName);

        return dto;
    }
    public static AdvertisementDTO getAdvertisementDTO(Advertisement ads){

        AdvertisementDTO dto = new AdvertisementDTO(ads.getId(),ads.getAdText(), ads.getAdsType().name(),
                ads.getVersion(), ads.getCreated(), ads.getUpdated(), ads.getCreatedBy().getUsername(), ads.getUpdatedBy().getUsername());

        return dto;
    }
    public static AdvertisementDTO getTermsAndCondDTO(Advertisement ads){
        AdvertisementDTO dto = new AdvertisementDTO(ads.getId(),ads.getAdText(), ads.getAdsType().name(),
                ads.getVersion(), ads.getCreated(), ads.getUpdated(), ads.getCreatedBy().getUsername(), ads.getUpdatedBy().getUsername());
        return dto;
    }

    public static LearningSkillRadarDTO getLearningSkillRadarDTO(AppMember_LearningSkill appMemberLearningSkill) {
        LearningSkillRadarDTO dto = new LearningSkillRadarDTO();
        dto.setAmount(appMemberLearningSkill.getAmount());
        dto.setLevel(appMemberLearningSkill.getLevel());
        dto.setSkillType(appMemberLearningSkill.getLearningSkill().getType().name());
        dto.setSkillTitle(appMemberLearningSkill.getLearningSkill().getTitle());
        dto.setSkillId(appMemberLearningSkill.getLearningSkill().getId());
        dto.setMemberId(appMemberLearningSkill.getApplicationMember().getId());
        return dto;
    }

    public static Collection<LearningSkillRadarDTO> getLearningSkillRadarDTOS(Collection<AppMember_LearningSkill> skillTransactions) {
        Collection<LearningSkillRadarDTO> dtos = new ArrayList<LearningSkillRadarDTO>();
        Iterator<AppMember_LearningSkill> iterator = skillTransactions.iterator();
        while (iterator.hasNext()) {
            LearningSkillRadarDTO dto = getLearningSkillRadarDTO(iterator.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static AppMemberSkillXPDTO getXPForAAppMemberSkillDTO(AppMember appMember, LearningSkill learningSkill, Collection<LearningSkillTransaction> transactions, BinaryContentService binaryContentService){
        Double sum = transactions.stream().mapToDouble(d-> d.getAmount()).sum();
        Float sumAmount = Float.valueOf(sum.toString());
        Long levelUpSize = learningSkill.getLevelUpSize();
        Long level = (long) (sumAmount / levelUpSize);
        Float reminder = (Float) (sumAmount % levelUpSize);

        BinaryContent icon = null;
        if(learningSkill.getIcon() == null){
            icon = binaryContentService.findByfileName("no_photo_avatar");
        }else{
            icon = learningSkill.getIcon();
        }
        AppMemberSkillXPDTO   dto = new AppMemberSkillXPDTO(learningSkill.getId(),learningSkill.getTitle(),
                learningSkill.getDescription(),level,learningSkill.getType().name(),
                reminder,appMember.getId(),icon.getId());

        return dto;
    }

    public static AppMemberXPDTO getXPForADate(Collection<LearningSkillTransaction> transactions, LocalDateTime date, Long appMemberId) {
        AppMemberXPDTO dto = new AppMemberXPDTO();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        Float xp=0f;
        Iterator<LearningSkillTransaction> iterator = transactions.iterator();

        while(iterator.hasNext()){
            LearningSkillTransaction transaction = iterator.next();
            xp += transaction.getAmount();
        }
        dto.setDate(DateUtils.getDateByString(date));
        dto.setDayOfWeek(dayOfWeek.getValue());
        dto.setDayOfWeekName(dayOfWeek.name());
        dto.setXp(xp);
        dto.setMemberId(appMemberId);
        return dto;
    }

    public static AppMemberXPDTO getAppMemberWeekXPDTO(LearningSkillTransaction transaction) {
        AppMemberXPDTO dto = new AppMemberXPDTO();
        dto.setDate(transaction.getTransactionDate().toString());
        dto.setMemberId(transaction.getAppMember().getId());
        dto.setXp(transaction.getAmount());
        return dto;
    }

    public static Collection<AppMemberXPDTO> getAppMemberWeekXPDTOS(Collection<LearningSkillTransaction> transactions) {
        Collection<AppMemberXPDTO> dtos = new ArrayList<AppMemberXPDTO>();
        LocalDateTime date = LocalDateTime.now();
//        AppMemberWeekXPDTO today = getXPForADate(transactions, date);
//        AppMemberWeekXPDTO today_1 = getXPForADate(transactions, date.minusDays(1));
//        AppMemberWeekXPDTO today_2 = getXPForADate(transactions, date.minusDays(2));

//        dtos.add(today);
//        dtos.add(today_1);
//        dtos.add(today_2);

        return dtos;
    }

    public static Collection<AppMemberDTO> getAppMemberDTOS(Collection<AppMember> appMemberCollection) {
        Collection<AppMemberDTO> dtos = new ArrayList<AppMemberDTO>();
        Iterator<AppMember> iterator = appMemberCollection.iterator();
        while (iterator.hasNext()) {
            AppMemberDTO dto = getAppMemberDTO(iterator.next());
            dtos.add(dto);
        }
        return dtos;
    }
    public static Collection<JourneyDTO> getJourneyDTOS(Collection<Journey> journeys) {
        Collection<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeys.iterator();
        while (itr.hasNext()) {
            JourneyDTO dto = getJourneyDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static RoadMapDTO getJourneyRoadMapDTO(RoadMap entity) {
        RoadMapDTO dto = new RoadMapDTO();
        dto.setId(entity.getId());
        dto.setXpos(entity.getXpos());
        dto.setYpos(entity.getYpos());
        dto.setGraphicId(entity.getGraphic().getId());
        return dto;
    }

    public static Collection<RoadMapDTO> getJourneyRoadMapsDTOS(Collection<RoadMap> roadMaps) {
        Collection<RoadMapDTO> dtos = new ArrayList<RoadMapDTO>();
        Iterator<RoadMap> itr = roadMaps.iterator();
        while (itr.hasNext()) {
            RoadMapDTO dto = getJourneyRoadMapDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
    /*
    public static AppMemberJourneyDTO getAppmemberJourneyDTO(AppMember member ,Journey entity) {
        AppMemberJourneyDTO dto = new AppMemberJourneyDTO();
        dto.setJourneyId(entity.getId());
        dto.setOpen(false);
        dto.setCurrentStar(-1);
        dto.setMinToPassStar(entity.getMinToPassStar());
        dto.setMinToOpenStar(entity.getMinToOpenStar());
        dto.setTitle(entity.getTitle());
        return dto;
    }

    public static Collection<JourneyDTO> getJourneyDTOSByUser(Collection<Journey> journeys) {
        Collection<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeys.iterator();
        while (itr.hasNext()) {
            JourneyDTO dto = getJourneyDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

     */
    public static Collection<PlayHistoryDTO> getPlayHistoryDTOS(Collection<PlayHistory> histories) {
        Collection<PlayHistoryDTO> dtos = new ArrayList<PlayHistoryDTO>();
        Iterator<PlayHistory> itr = histories.iterator();
        while (itr.hasNext()) {
            PlayHistoryDTO dto = getPlayHistoryDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
    public static PlayHistoryDTO getPlayHistoryDTO(PlayHistory entity) {
        PlayHistoryDTO dto = new PlayHistoryDTO();
        PuzzleLevel pl = entity.getPuzzleLevel();
        AppMember player = entity.getPlayer();
        PuzzleGroup puzzleGroup = pl.getPuzzleGroup();

        dto.setId(entity.getId());
        dto.setPlayDuration(entity.getPlayDuration());
        dto.setStartPlayTime(entity.getStartPlayTime());
        dto.setEndPlayTime(entity.getEndPlayTime());
        dto.setPlayScore(entity.getPlayScore());
        dto.setStars(entity.getStars());
        dto.setPlayerId(player.getId());
        dto.setPlayerUsername(player.getUsername());
        if(entity.getGameStatus()!=null)
            dto.setGameStatus(entity.getGameStatus().name());
        else
            dto.setGameStatus(GameStatus.Unknown.name());
        dto.setPlId(pl.getId());
        dto.setPlTitle(pl.getTitle());
        dto.setPlCode(pl.getCode());
        dto.setPlMaxScore(pl.getMaxScore());
        dto.setPlFromAge(pl.getFromAge());
        dto.setPlToAge(pl.getToAge());

        dto.setPgTitle(puzzleGroup.getTitle());
        dto.setPgId(puzzleGroup.getId());

        return dto;
    }

    public static AppMemberWalletDTO getAppMemberWalletDTO(AppMember_WalletItem entity) {
        AppMemberWalletDTO dto = new AppMemberWalletDTO();
        WalletItem walletItem = entity.getWalletItem();
        dto.setId(entity.getId());
        dto.setWalletItemTitle(walletItem.getLabel());
        dto.setWalletItemId(walletItem.getId());
        dto.setAmount(entity.getAmount());
        dto.setAppMemberId(entity.getApplicationMember().getId());
        dto.setAppMemberUsername(entity.getApplicationMember().getUsername());
        dto.setIconId(entity.getWalletItem().getIcon().getId());
        dto.setWalletItemType(entity.getWalletItem().getWalletItemType().getValue());
        dto.setCurrency(entity.getWalletItem().getWalletItemType().getCurrency());
        return dto;
    }

    public static Collection<AppMemberWalletDTO> getAppMemberWalletDTOS(Collection<AppMember_WalletItem> appMember_walletItems) {
        Collection<AppMemberWalletDTO> dtos = new ArrayList<>();
        Iterator<AppMember_WalletItem> itr = appMember_walletItems.iterator();
        while(itr.hasNext()) {
            AppMember_WalletItem appMember_walletItem = itr.next();
            AppMemberWalletDTO dto = getAppMemberWalletDTO(appMember_walletItem);
            dtos.add(dto);
        }
        return dtos;
    }
        public static JourneyDTO getJourneyDTO(Journey entity) {
            JourneyDTO dto = new JourneyDTO();
            dto.setId(entity.getId());
            dto.setVersion(entity.getVersion());
            dto.setCreated(entity.getCreated());
            dto.setUpdated(entity.getUpdated());
            dto.setTitle(entity.getTitle());
            dto.setOrdering(entity.getOrdering());
            dto.setMinToOpenStar(entity.getMinToOpenStar());
            dto.setMinToPassStar(entity.getMinToPassStar());
            dto.setPicId(entity.getPic().getId());
            dto.setButtonCurrenIconId(entity.getButtonCurrenIcon().getId());
            dto.setButtonLockedIconId(entity.getButtonLockedIcon().getId());
            dto.setButtonPassedIconId(entity.getButtonPassedIcon().getId());
            dto.setCreatedBy(entity.getCreatedBy().getUsername());
            dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
            dto.setCreatedById(entity.getCreatedBy().getId());
            dto.setUpdatedById(entity.getUpdatedBy().getId());
         return dto;
    }

    public static Collection<LearningSkillDTO> getLearningSkillDTO(Collection<LearningSkill> skills) {
        Collection<LearningSkillDTO>  dtos = new ArrayList<LearningSkillDTO>();
        Iterator<LearningSkill> itr = skills.iterator();

        while(itr.hasNext()){
            LearningSkill skill = itr.next();
            LearningSkillDTO dto = new LearningSkillDTO();
            dto = DTOUtil.getLearningSkillDTO(skill);
            dtos.add(dto);
        }
        return dtos;
    }

    public static LearningSkillDTO getLearningSkillDTO(LearningSkill ls) {

        if(ls.getParentSkill() == null)
            return  new LearningSkillDTO(ls.getId(), ls.getTitle(), ls.getType().name(),0L,"",0L,ls.getIcon().getId(),"Root of Skill Tree");;
        LearningSkillDTO lsDTO = new LearningSkillDTO(ls.getId(), ls.getTitle(), ls.getType().name(),ls.getParentSkill().getId(),ls.getParentSkill().getTitle(),ls.getLevelUpSize(),ls.getIcon().getId(),ls.getDescription());
        return lsDTO;
    }

    public static LearningTopicDTO getLearningTopicDTO(LearningTopic lt) {

        LearningTopicDTO lsDTO =null;
        if(lt.getParentTopic()==null)
            lsDTO = new LearningTopicDTO(lt.getId(), lt.getTitle(), 0L,
                    lt.getVersion(), lt.getCreated(), lt.getUpdated(),lt.getCreatedBy().getUsername(),lt.getUpdatedBy().getUsername());
        else
            lsDTO = new LearningTopicDTO(lt.getId(), lt.getTitle(),lt.getParentTopic().getId(),
                    lt.getVersion(), lt.getCreated(), lt.getUpdated(),lt.getCreatedBy().getUsername(),lt.getUpdatedBy().getUsername());

        return lsDTO;
    }

    public static LearningContentDTO getLearningContentDTO(LearningContent lc) {
        LearningContentDTO dto =null;

        dto =  new LearningContentDTO(lc.getId() ,lc.getVersion(), lc.getCreated(), lc.getUpdated(),lc.getCreatedBy().getUsername(), lc.getUpdatedBy().getUsername(),
                lc.getDescText(), lc.getDescBrief(), lc.getBinaryContent().getFileName(),lc.getBinaryContent().getSize(),lc.getBinaryContent().getContentType().name(), lc.getBinaryContent().getId());
        return dto;
    }


    public static Collection<LearningContentDTO> getLearningContentDTOS(Collection<LearningContent> learningContents) {
        Collection<LearningContentDTO> dtos = new ArrayList<LearningContentDTO>();
        Iterator<LearningContent> itr = learningContents.iterator();
        while (itr.hasNext()) {
            LearningContentDTO dto = getLearningContentDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
    /*
    public static Collection<PLObjectiveDTO> getLearningTopicDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveDTO> dtos = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> objectives = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> itr = objectives.iterator();
        while (itr.hasNext()) {
            PLObjectiveDTO plObjectiveDTO = getPuzzleLevelObjectiveDTO(itr.next());
            dtos.add(plObjectiveDTO);
        }
        return dtos;
    }

     */

    public static WalletItemTypeDTO getWalletItemTypeDTO(WalletItemType wit) {
        WalletItemTypeDTO walletItemTypeDTO = new WalletItemTypeDTO(wit.getId(),wit.getValue(),wit.getLabel(),wit.getCurrency(),wit.getWalletItemCategory().name(),
                wit.getVersion(),wit.getCreated(),wit.getUpdated(),wit.getCreatedBy().getUsername(),wit.getUpdatedBy().getUsername() );
        return walletItemTypeDTO;
    }
    public static Collection<WalletItemTypeDTO> getWalletItemTypeDTOS(Collection<WalletItemType> walletItemTypes) {
        Collection<WalletItemTypeDTO> dtos = new ArrayList<WalletItemTypeDTO>();
        Iterator<WalletItemType> iterator = walletItemTypes.iterator();
        while (iterator.hasNext()) {
            WalletItemTypeDTO walletItemTypeDTO = getWalletItemTypeDTO(iterator.next());
            dtos.add(walletItemTypeDTO);
        }
        return dtos;
    }
    public static WalletItemDTO getWalletItemDTO(WalletItem wi)  {
        WalletItemTypeDTO walletItemTypeDTO = getWalletItemTypeDTO(wi.getWalletItemType());
         WalletItemDTO walletItemDTO = new WalletItemDTO(wi.getId(), wi.getLabel(), wi.getValue(),wi.getIcon().getId(),
                wi.getWalletItemType().getValue(),walletItemTypeDTO.getCurrency(),wi.getIcon().getThumbnail(), wi.getVersion(),wi.getCreated(),
                wi.getUpdated(),wi.getCreatedBy().getUsername(),wi.getUpdatedBy().getUsername());
        return walletItemDTO;
    }
    public static Collection<WalletItemDTO> getWalletItemDTOS(Collection<WalletItem> walletItems){
        Collection<WalletItemDTO> dtos = new ArrayList<WalletItemDTO>();
        Iterator<WalletItem> iterator = walletItems.iterator();
        while (iterator.hasNext()) {
            WalletItemDTO walletItemDTO = getWalletItemDTO(iterator.next());
            dtos.add(walletItemDTO);
        }
        return dtos;
    }


       public static PLGroundDTO getPLGroundDTO(PLGround plGround) throws IOException, ClassNotFoundException, JSONException {

        PLGroundDTO dto = new PLGroundDTO();
        dto.setId(plGround.getId());
        dto.setVersion(plGround.getVersion());
        dto.setCreated(plGround.getCreated());
        dto.setUpdated(plGround.getUpdated());
        dto.setCreatedBy(plGround.getCreatedBy().getUsername());
        dto.setUpdatedBy(plGround.getUpdatedBy().getUsername());
        dto.setNumRows(plGround.getNumRows());
        dto.setXposition(plGround.getxPosition());
        dto.setYposition(plGround.getyPosition());
        dto.setZposition(plGround.getzPosition());
        dto.setXrotation(plGround.getxRotation());
        dto.setYrotation(plGround.getyRotation());
        dto.setZrotation(plGround.getzRotation());
        dto.setZoom(plGround.getZoom());
        dto.setPan(plGround.getPan());
        dto.setRotation(plGround.getRotation());
      //  String s = new String(plGround.getBoardGraphic(), StandardCharsets.US_ASCII);
      //  JSONObject objJsonObject = new JSONObject(s);
        BoardGraphicDTO boardGraphicDTO = PLDTOUtil.getBoardGraphicJSON(plGround);
        dto.setBoardGraphicDTO(boardGraphicDTO);
        dto.setNumColumns(plGround.getNumColumns());
        dto.setPuzzleLevelId(plGround.getPuzzleLevel().getId());
       // dto.setPuzzleLevelTitle(plGround.getPuzzleLevel().getTitle());



        return  dto;
    }

    /*
    public static Collection<PLGroundDTO> getPLGroundDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLGroundDTO> dtos = new ArrayList<PLGroundDTO>();
        Collection<PLGround> plGrounds = puzzleLevel.getPlGrounds();
        Iterator<PLGround> itr = plGrounds.iterator();
        while(itr.hasNext()) {
            PLGround plGround = itr.next();
            PLGroundDTO plGroundDTO = getPLGroundDTO(plGround);
            dtos.add(plGroundDTO);
        }
        return dtos;
    }

     */
    public static Collection<PLDTO> getPlayedPuzzlesByAppMemberDTOS(Collection<PlayHistory> histories) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();
        Iterator<PlayHistory> itr = histories.iterator();

        while(itr.hasNext()){
            PlayHistory entity = itr.next();
            PLDTO dto = new PLDTO();
            dto.setId(entity.getPuzzleLevel().getId());
            dto.setCode(entity.getPuzzleLevel().getCode());
            dto.setApproveDate(entity.getPuzzleLevel().getApproveDate());
            dto.setTitle(entity.getPuzzleLevel().getTitle());
            dto.setToAge(entity.getPuzzleLevel().getToAge());
            dto.setFromAge(entity.getPuzzleLevel().getFromAge());
            dto.setOrdering(entity.getPuzzleLevel().getOrdering());
            dto.setMaxScore(entity.getPuzzleLevel().getMaxScore());
            dto.setFirstStarScore(entity.getPuzzleLevel().getFirstStarScore());
            dto.setSecondStarScore(entity.getPuzzleLevel().getSecondStarScore());
            dto.setThirdStartScore(entity.getPuzzleLevel().getThirdStartScore());
            dto.setVersion(entity.getVersion());
            dto.setCreated(entity.getCreated());
            dto.setUpdated(entity.getUpdated());
            dto.setCreatedBy(entity.getCreatedBy().getUsername());
            dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
            dto.setUpdatedById(entity.getUpdatedBy().getId());
            dto.setIconId(entity.getPuzzleLevel().getIcon().getId());
            dto.setPicId(entity.getPuzzleLevel().getPicture().getId());
            dtos.add(dto);

        }
        return dtos;
    }
    /*
    public static Collection<PLDTO> getNotPlayedPuzzlesByAppMemberDTOS(Collection<PlayHistory> histories) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();
        Iterator<PlayHistory> itr = histories.iterator();

        while(itr.hasNext()){
            PlayHistory entity = itr.next();
            PLDTO dto = new PLDTO();
            dto.setId(entity.getPuzzleLevel().getId());
            dto.setCode(entity.getPuzzleLevel().getCode());
            dto.setApproveDate(entity.getPuzzleLevel().getApproveDate());
            dto.setTitle(entity.getPuzzleLevel().getTitle());
            dto.setToAge(entity.getPuzzleLevel().getToAge());
            dto.setFromAge(entity.getPuzzleLevel().getFromAge());
            dto.setOrdering(entity.getPuzzleLevel().getOrdering());
            dto.setMaxScore(entity.getPuzzleLevel().getMaxScore());
            dto.setFirstStarScore(entity.getPuzzleLevel().getFirstStarScore());
            dto.setSecondStarScore(entity.getPuzzleLevel().getSecondStarScore());
            dto.setThirdStartScore(entity.getPuzzleLevel().getThirdStartScore());
            dto.setVersion(entity.getVersion());
            dto.setCreated(entity.getCreated());
            dto.setUpdated(entity.getUpdated());
            dto.setCreatedBy(entity.getCreatedBy().getUsername());
            dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
            dto.setUpdatedById(entity.getUpdatedBy().getId());
            dto.setIconId(entity.getPuzzleLevel().getIcon().getId());
            dto.setPicId(entity.getPuzzleLevel().getPicture().getId());
            dtos.add(dto);

        }
        return dtos;
    }

     */
/*
    public static Collection<PermitedPlayerDTO> getPermitedPlayerDTOS(PuzzleLevel puzzleLevel) {
        Collection<PermitedPlayerDTO> dtos = new ArrayList<PermitedPlayerDTO>();
        Collection<PermitedPlayer> permitedPlayers = puzzleLevel.getPermitedPlayerCollection();
        Iterator<PermitedPlayer> itr = permitedPlayers.iterator();

        while(itr.hasNext()){
            PermitedPlayer permitedPlayer = itr.next();
            PermitedPlayerDTO dto = new PermitedPlayerDTO();
            dto.setId(permitedPlayer.getId());
            dto.setPlId(permitedPlayer.getId());
            dto.setVersion(permitedPlayer.getVersion());
            dto.setPlayerUsername(permitedPlayer.getPlayer().getUsername());
            dto.setEmail(permitedPlayer.getPlayer().getEmail());
            dto.setUpdated(permitedPlayer.getUpdated());
            dto.setCreated(permitedPlayer.getCreated());
            dtos.add(dto);

        }
        return dtos;
    }

 */

    public static Collection<InstanceDTO>  getPuzzleLevelInstance(PuzzleLevel puzzleLevel){
        Collection<InstanceDTO> dtos = new ArrayList<InstanceDTO>();
        Collection<Instance> instances = puzzleLevel.getInstances();
        Iterator<Instance> itr = instances.iterator();

        while(itr.hasNext()){
            Instance instance = itr.next();
            InstanceDTO dto = new InstanceDTO();
            dto.setId(instance.getId());
            dto.setVersion(instance.getVersion());
            dto.setCol(instance.getCol());
            dto.setRow(instance.getRow());
            dto.setzOrder(instance.getzOrder());
            dto.setCreated(instance.getCreated());
            dto.setUpdated(instance.getUpdated());
            dto.setCreatedById(instance.getCreatedBy().getId());
            dto.setUpdatedByID(instance.getUpdatedBy().getId());
            dto.setUpdatedBy(instance.getUpdatedBy().getUsername());
            dto.setCreatedBy(instance.getCreatedBy().getUsername());
            dto.setPGObjectId(instance.getAlCityObjectInPG().getId());
            dto.setPGObjectCode(instance.getAlCityObjectInPG().getCode());
            dto.setPGObjectTitle(instance.getAlCityObjectInPG().getTitle());

            dtos.add(dto);
        }
        return dtos;
    }

    public static Collection<ClientTypeDTO> getClientTypeDTOS(Collection<ClientType> clientTypes){
        Iterator<ClientType> iterator = clientTypes.iterator();
        Collection<ClientTypeDTO> dtos = new ArrayList<ClientTypeDTO>();
        while(iterator.hasNext()) {
            ClientTypeDTO dto = getClientTypeDTO(iterator.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static ClientTypeDTO getClientTypeDTO(ClientType ctype){
      ClientTypeDTO dto = new ClientTypeDTO(ctype.getId(), ctype.getLabel(),ctype.getValue());
        return  dto;
   }

   public static MemberTypeDTO getMemberTypeDTO(MemberType mt){
        MemberTypeDTO dto = new MemberTypeDTO(mt.getId(), mt.getVersion(), mt.getLabel(), mt.getValue(),
                mt.getCreated(), mt.getUpdated(), mt.getCreatedBy().getUsername(),mt.getUpdatedBy().getUsername(),mt.getCreatedBy().getId(),mt.getUpdatedBy().getId());
        return dto;
    }
   public static Collection<MemberTypeDTO> getMemberTypeDTOS(Collection<MemberType> memberTypes){
       Iterator<MemberType> iterator = memberTypes.iterator();
       Collection<MemberTypeDTO> dtos = new ArrayList<MemberTypeDTO>();
       while(iterator.hasNext()) {
           MemberTypeDTO dto = getMemberTypeDTO(iterator.next());
           dtos.add(dto);
       }
       return dtos;
    }
    public static ObjectCategoryDTO getObjectCategoryDTO(ObjectCategory category){
        ObjectCategoryDTO objectCategoryDTO = new ObjectCategoryDTO(category.getId(),category.getLabel(),category.getValue(),
                category.getVersion(), category.getCreated(),
                category.getUpdated(),null);
        return objectCategoryDTO;
    }
    public static Collection<ObjectCategoryDTO> getObjectCategories(Collection<ObjectCategory> categories){
        Collection<ObjectCategoryDTO> dtos = new ArrayList<ObjectCategoryDTO>();
        Iterator<ObjectCategory> itr = categories.iterator();
        while(itr.hasNext()){
            ObjectCategoryDTO dto =getObjectCategoryDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
//    public static PLRulePostActionTypeDTO getPLRulePostActionTypeDTO(PLRulePostActionType entity) {
//        PLRulePostActionTypeDTO dto = new PLRulePostActionTypeDTO(entity.getId(),entity.getLabel(),entity.getValue(),1L,entity.getCreated()
//        ,entity.getUpdated());
//
//        return dto;
//    }
//        public static Collection<PLRulePostActionTypeDTO> getPLRulePostActionTypeDTOS(Collection<PLRulePostActionType> rulePostActionTypes){
//        Collection<PLRulePostActionTypeDTO> dtos = new ArrayList<PLRulePostActionTypeDTO>();
//        Iterator<PLRulePostActionType> iterator = rulePostActionTypes.iterator();
//        while(iterator.hasNext()) {
//            PLRulePostActionType rulePostActionType = iterator.next();
//            PLRulePostActionTypeDTO dto = new PLRulePostActionTypeDTO();
//            dto = getPLRulePostActionTypeDTO(rulePostActionType);
//            dtos.add(dto);
//        }
//        return dtos;
//    }


    public static RendererDTO getActionRendererDTO(Renderer actionRenderer){
           RendererDTO dto = new RendererDTO();
        dto.setId(actionRenderer.getId());
        dto.setCreated(actionRenderer.getCreated());
        dto.setUpdated(actionRenderer.getUpdated());
        dto.setVersion(actionRenderer.getVersion());
        dto.setUpdatedBy(actionRenderer.getUpdatedBy().getUsername());
        dto.setCreatedBy(actionRenderer.getCreatedBy().getUsername());
            ObjectActionType objectAction = actionRenderer.getObjectAction();
        dto.setObjectAction(objectAction.name());
            ClientType clientType = actionRenderer.getClientType();
        dto.setClientType(clientType.getValue());
        dto.setHandler(actionRenderer.getHandler());
           return dto;
   }
    public static PLRulePostActionDTO getPLRulePostActionDTO(PLRulePostAction postAction,AttributeService attributeService) {
        PLRulePostActionDTO dto = new PLRulePostActionDTO();
        dto.setId(postAction.getId());
        dto.setActionName(postAction.getActionName());
        dto.setAlertMessage(postAction.getAlertMessage());
        dto.setAlertType(postAction.getAlertType());
        dto.setPlRulePostActionType(postAction.getPlRulePostActionType().name());
        if(postAction.getSubAction()==null)
            dto.setSubAction("");
        else
            dto.setSubAction(postAction.getSubAction());
        dto.setObjectId(postAction.getObjectId());
        dto.setVariable(postAction.getVariable());
        dto.setValueExperssion(postAction.getValueExperssion());
        dto.setOrdering(postAction.getOrdering());
        dto.setOwnerId(postAction.getOwnerId());
        dto.setActionKey(postAction.getActionKey());
        dto.setOwnerType(postAction.getOwnerType().name());
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(postAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        dto.setParameters(parameters);
        return  dto;
    }

    public static Collection<PLRulePostActionDTO> getPLRulePostActionDTOS(Collection<PLRulePostAction> postActions,AttributeService attributeService) {
        Collection<PLRulePostActionDTO> dtos = new ArrayList<>();
        Comparator actionOrderingComparetor = new PLRulePostActionComparator();

        Iterator<PLRulePostAction> itr = postActions.iterator();
        while(itr.hasNext()){
            PLRulePostAction postAction = itr.next();
            PLRulePostActionDTO dto = getPLRulePostActionDTO(postAction,attributeService);
            dtos.add(dto);
        }
        List<PLRulePostActionDTO> sortedList =new ArrayList<PLRulePostActionDTO>();
        sortedList = dtos.stream().collect(toList());
        sortedList.sort(actionOrderingComparetor);

        return  sortedList;
    }
        public static PLRuleDTO getPLRuleDTO(PLRule plRule) {
        PLRuleDTO dto = new PLRuleDTO();
        dto.setId(plRule.getId());
        dto.setTitle(plRule.getTitle());
        dto.setOrdering(plRule.getOrdering());
        dto.setCondition(plRule.getCondition());
        dto.setIgnoreRemaining(plRule.getIgnoreRemaining());
        dto.setPuzzleLevelId(plRule.getPuzzleLevel().getId());
        dto.setPuzzleLeveTitle(plRule.getPuzzleLevel().getTitle());
        dto.setPLRuleEventId(plRule.getPlRuleEvent().getId());
        dto.setPLRuleEventName(plRule.getPlRuleEvent().getName());
        dto.setPLRuleSubEventName(plRule.getSubEvent());
        dto.setPlRuleEventTypeId(plRule.getPlRuleEvent().getPlRuleEventType().ordinal());
        dto.setPlRuleEventTypeTitle(plRule.getPlRuleEvent().getPlRuleEventType().name());
        return dto;
    }
    public static Collection<PLRuleDTO> getRulesForPuzzleLevel(PuzzleLevel pl){
        Collection<PLRuleDTO> dtos = new ArrayList<PLRuleDTO>();
        Collection<PLRule>  puzzleLevelRules = pl.getRules();
        Iterator<PLRule> iterator = puzzleLevelRules.iterator();
        while(iterator.hasNext()) {
            PLRule plRule = iterator.next();
            PLRuleDTO dto = new PLRuleDTO();
            dto = getPLRuleDTO(plRule);
            dtos.add(dto);
        }
        Comparator ruleOrderComparetor = new PLRuleComparator();
        List<PLRuleDTO> sortedList =new ArrayList<PLRuleDTO>();
        sortedList = dtos.stream().collect(toList());
        sortedList.sort(ruleOrderComparetor);
        return sortedList;
    }
    public static Collection<PLCellDTO> getPLCellDTOS(Collection<PLCell> cells) {
        Collection<PLCellDTO> dtos = new ArrayList<PLCellDTO>();
        Iterator<PLCell> iterator = cells.iterator();
        while(iterator.hasNext()) {
            PLCell cell = iterator.next();
            PLCellDTO dto = new PLCellDTO(cell.getId(), cell.getRow(), cell.getCol(), cell.getzOrder(),cell.getPlGround().getId());
            dtos.add(dto);
        }
        return dtos;
    }
    public static PLCellDTO getPLCellDTO(PLCell cell) {
            PLCellDTO dto = new PLCellDTO(cell.getId(), cell.getRow(), cell.getCol(), cell.getzOrder(),cell.getPlGround().getId());
        return dto;
    }

    public static Collection<PLCellData> getPLCellDTOS(Collection<PLCell> cells, AttributeService attributeService) {
        Collection<PLCellData> dtos = new ArrayList<PLCellData>();
        Iterator<PLCell> iterator = cells.iterator();
        while(iterator.hasNext()) {
            PLCell cell = iterator.next();
            PostionIntDTO position = new PostionIntDTO(cell.getRow(), cell.getCol(), cell.getzOrder());
            Collection<AttributeData>  cellProperties = DTOUtil.getAttributeForOwnerById(attributeService,cell.getId(),AttributeOwnerType.Puzzle_Level_Cell_Property);
            Collection<AttributeData>  cellVariables = DTOUtil.getAttributeForOwnerById(attributeService,cell.getId(),AttributeOwnerType.Puzzle_Level_Cell_Variable);
            PLCellData dto = new PLCellData(cell.getId(),position,cellProperties,cellVariables);
            dtos.add(dto);
        }
        return dtos;
    }

        public static Collection<RuleData> getPLRules(PuzzleLevel pl, AttributeService attributeService,AttributeValueService attributeValueService, PLRulePostActionService plRulePostActionService){
        Collection<RuleData> rulesData = new ArrayList<RuleData>();
        Collection<PLRule>  rules = pl.getRules();
        Iterator<PLRule> iterator = rules.iterator();
        while(iterator.hasNext()) {
            PLRule rule = iterator.next();
            RuleData ruleData = new RuleData();
            ruleData.setTitle(rule.getTitle());
            ruleData.setOrdering(rule.getOrdering());
            ruleData.setConditions(rule.getCondition());
            ruleData.setIgnoreRemaining(rule.getIgnoreRemaining());
            String event = rule.getPlRuleEvent().getName();
            String subEvent = rule.getSubEvent();
            if(subEvent==null || subEvent.isBlank()) subEvent="";
            if(!subEvent.equalsIgnoreCase(""))
                event = event + ":" + subEvent;
            ruleData.setEvent(event);
            Collection<PostActionTreeExport> actions = getActionsTrees(plRulePostActionService ,attributeService,attributeValueService ,rule);
            ruleData.setActions(actions);

//            Comparator ruleActionComparator = new RuleActionDataComparator();
//            List<PostActionTreeExport> sortedAction =new ArrayList<PostActionTreeExport>();
//            sortedAction = actions.stream().collect(toList());
//            sortedAction.sort(ruleActionComparator);
//            ruleData.setActionTreeExports(sortedAction);

            rulesData.add(ruleData);
        }

        Comparator ruleComparator = new RuleDataComparator();
        List<RuleData> sortedRules =new ArrayList<RuleData>();
        sortedRules = rulesData.stream().collect(toList());
        sortedRules.sort(ruleComparator);

        return sortedRules;
    }
    public static <PLRulePostActionImport> void preOrderTraversal(PLRulePostActionService plRulePostActionService,PostActionTreeImport<PLRulePostActionImport> node,Long ownerId) {
        if (node == null) return;

        System.out.println(node.postAction.getActionName() + " ");
        PLRulePostAction postAction = plRulePostActionService.importPostAction(node.postAction,ownerId);
        for (PostActionTreeImport<com.alcity.dto.plimpexport.rulemport.PLRulePostActionImport> child : node.children) {
            preOrderTraversal(plRulePostActionService,child,postAction.getId());
        }
    }
    public static <PLRulePostActionImport_New> void preOrderTraversal_New(PLRulePostActionService plRulePostActionService, PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> node, Long ownerId) {
        if (node == null) return;

        System.out.println(node.getActionName() + " ");
        node.setPostActionOwnerType(PLRulePostActionOwnerType.Puzzle_Level_Rule.name());
        PLRulePostAction postAction = plRulePostActionService.importPostAction_New(node,ownerId);
        PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> childs=node.getInnerActions();
        while (childs.getInnerActions() !=null) {
            PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> child = node.getInnerActions();
            child.setPostActionOwnerType(PLRulePostActionOwnerType.Inner_Rule_Post_Action.name());
            preOrderTraversal_New(plRulePostActionService,child,postAction.getId());
        }
    }

    public static Collection<PLRulePostAction> getPlRulePostActions(PLRulePostActionService plRulePostActionService, Long ownerId ){
        Collection<PLRulePostAction>  postActions = plRulePostActionService.findByOwnerId(ownerId) ;
        Iterator<PLRulePostAction> iterator = postActions.iterator();
        while(iterator.hasNext()) {
            PLRulePostAction postAction = iterator.next();
            inorder(plRulePostActionService , postAction);
        }
        return postActions;
    }

    public static void inorder(PLRulePostActionService plRulePostActionService,PLRulePostAction postAction)
    {
        ArrayList<PLRulePostAction>  postActions = plRulePostActionService.findByOwnerId(postAction.getId()) ;
        if (postAction == null || postActions.isEmpty())
            return;
        // Total children count
         int total = postActions.size();
        // All the children except the last
        for (int i = 0; i < total - 1; i++)
            inorder(plRulePostActionService,postActions.get(i));
        // Print the current node's data
        System.out.print("" + postAction.getActionName() + " ");

        // Last child
        inorder(plRulePostActionService,postActions.get(total - 1));
    }

    public static <PLRulePostActionImport> PostActionTreeExport preOrderTraversal(PostActionTreeExport treeExport , PLRulePostActionService plRulePostActionService,AttributeService attributeService,AttributeValueService attributeValueService ,PLRulePostAction root) {
        Collection<PLRulePostAction> children = plRulePostActionService.findByOwnerId(root.getId());
        Collection<Attribute> parameters = new ArrayList<Attribute>();
        if (root == null)  return treeExport;

        parameters = attributeService.findPostActionParameters(root.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        Collection<AttributeData> parametersData = DTOUtil.getAttributesForRuleAction(root.getId(),parameters,attributeValueService);


        treeExport.setFiedlds(root.getPlRulePostActionType().name()+":"+root.getSubAction(), root.getOrdering(), root.getObjectId(),root.getActionName(),root.getVariable(),root.getValueExperssion(),
                root.getAlertType(), root.getAlertMessage(), root.getActionKey(),parametersData);
        Iterator<PLRulePostAction> childIterator = children.iterator();
        while(childIterator.hasNext()){
            PLRulePostAction child = childIterator.next();
            PostActionTreeExport<PostActionTreeExport> subTree=treeExport.getChild(new PostActionTreeExport<>(child.getPlRulePostActionType().name()+":"+child.getSubAction(), child.getOrdering(), child.getObjectId(),child.getActionName(),
                    child.getVariable(),child.getValueExperssion(),child.getAlertType(), child.getAlertMessage(), child.getActionKey(),null,null));
            preOrderTraversal(subTree,plRulePostActionService,attributeService,attributeValueService,child) ;
        }
        return treeExport;
     }

    public static Collection<PostActionTreeExport> getActionsTrees(PLRulePostActionService plRulePostActionService, AttributeService attributeService,AttributeValueService attributeValueService , PLRule plRule){
        Collection<PostActionTreeExport> actionTrees = new ArrayList<PostActionTreeExport>();
        Collection<PLRulePostAction>  postActions = plRulePostActionService.findByOwnerId(plRule.getId()) ;

        Iterator<PLRulePostAction> iterator = postActions.iterator();
        while(iterator.hasNext()) {
            PLRulePostAction postAction =iterator.next();
            PostActionTreeExport tree = new PostActionTreeExport();
            tree =  preOrderTraversal(tree,plRulePostActionService,attributeService,attributeValueService ,postAction);
            actionTrees.add(tree);
        }

        return actionTrees;
    }
    public static Collection<AttributeData>  getActionParametersDTOS(Collection<Attribute>  attributes){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        Iterator<Attribute> iterator = attributes.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
              Iterator<AttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext() ) {
                AttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getDataValue(alCityAttributeValue);
                String type = getDataType(attribute);
                AttributeData record = new AttributeData(attribute.getId(), attribute.getName(),alCityAttributeValue.getId(),value,type,false,"");
                records.add(record);
            }
        }
        return records;
    }
    public static Collection<AttributeData>  getPropertiesDTOForPGObject(Collection<Attribute>  properties){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        Iterator<Attribute> iterator = properties.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Iterator<AttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext() ) {
                AttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getDataValue(alCityAttributeValue);
                String type = getDataType(attribute);
                AttributeData record = new AttributeData(attribute.getId(), attribute.getName(),alCityAttributeValue.getId(),value,type,false,"");
                records.add(record);
            }
        }
        return records;
    }
    public static Collection<AttributeData>  getAttributesForRuleAction(Long ownerId,Collection<Attribute>  properties,AttributeValueService attributeValueService){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        AttributeData record =null;
        Iterator<Attribute> iterator = properties.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Optional<AttributeValue> attributeValueOptional = attributeValueService.findByAttributeIdAndOwnerId(attribute, ownerId);
            if (attributeValueOptional.isPresent()) {
                String value = getDataValue(attributeValueOptional.get());
                String type = getDataType(attribute);
                record = new AttributeData(attribute.getId(), attribute.getName(), attributeValueOptional.get().getId(), value, type, false, "");
                records.add(record);
            }
        }
        return records;
    }

    public static Collection<AttributeData>  getVariablesDTOForPGObject(Collection<Attribute>  variables){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        Iterator<Attribute> iterator = variables.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Iterator<AttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext() ) {
                AttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getDataValue(alCityAttributeValue);
                String type = getDataType(attribute);
                AttributeData record = new AttributeData(attribute.getId(), attribute.getName(),alCityAttributeValue.getId(),value,type,false,"");
                records.add(record);
            }
        }
        return records;
    }
    public static Collection<AttributeData>  getAttributeForOwnerById(AttributeService attributeService , Long ownerId, AttributeOwnerType ownerType){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        Collection<Attribute>  attributes =attributeService.findByOwnerIdAndAttributeOwnerTypeNew(ownerId,ownerType);
        Iterator<Attribute> iterator = attributes.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            //filter values by owner id
            //attributeValues = attributeValues.stream().filter(value ->value.getOwnerId().equals(ownerId) ).collect(Collectors.toList());
            Iterator<AttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext() ) {
                AttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getDataValue(alCityAttributeValue);
                String type = getDataType(attribute);
                AttributeData record = new AttributeData(attribute.getId(), attribute.getName(),alCityAttributeValue.getId(),value,type,false,"");
                records.add(record);
            }
        }
        return records;
    }
    public static String getDataValue(AttributeValue value){
        if(value == null)  return "Attribute Value is Null";
        if (value.getExpressionValue()!=null )     return value.getExpressionValue();
        if (value.getBooleanValue()!=null )  return value.getBooleanValue().toString();

        if (value.getDoubleValue()!=null )
            return value.getDoubleValue().toString();

        if (value.getIntValue()!=null )      return value.getIntValue().toString();

        if (value.getLongValue()!=null )     return value.getLongValue().toString();

        if (value.getBinaryContentId()!=null )  return value.getBinaryContentId().toString();

        if (value.getStringValue()!=null )     return value.getStringValue();
        if (value.getBindedAttributeId()!=null )     return value.getBindedAttributeId().getId().toString();
        if (value.getObjectValue()!=null )     return value.getStringValue();

        return "Unknown Value";
    }
    public static String getDataType(Attribute attribute){
        if(attribute == null)  return "Attribute is Null";
        if(attribute.getDataType().name().equalsIgnoreCase("Float")) return "Decimal";
        else return attribute.getDataType().name();
     }
}