package com.alcity.utility;

import com.alcity.comparetors.*;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.learning.LearningSkillTreeDTO;
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
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;
import com.alcity.dto.puzzle.*;
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
import com.alcity.entity.puzzle.*;
import com.alcity.repository.alobject.AttributeRepository;
import com.alcity.repository.alobject.AttributeValueRepository;
import com.alcity.service.alobject.ActionService;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import com.alcity.service.appmember.ObjectiveTransactionService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.puzzle.PLRulePostActionService;
import com.alcity.test.ruleimport_new.PostActionTreeImport_New;
import org.json.JSONException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class DTOUtil {


    public static PLTemplateDTO getPLTemplateDTO(PLTemplate template){
        return new PLTemplateDTO(template.getId(),template.getTitle(), template.getFromAge(), template.getToAge(), template.getPuzzleCategory().getId(),
                 template.getPuzzleGroupId(),template.getPuzzleLevelId(), template.getContent());
    }


    public static Collection<PLTemplateDTO> getPLTemplateDTOS(Collection<PLTemplate> templates){
        Collection<PLTemplateDTO> dtos = new ArrayList<>();
        for (PLTemplate template  : templates) {
            PLTemplateDTO dto = getPLTemplateDTO(template);
            dtos.add(dto);
        }
        return  dtos;
    }

    public static PLRuleEventDTO getPLRuleEventDTO(PLRuleEvent event){
        return new PLRuleEventDTO(event.getId(),event.getName(), event.getPlRuleEventType().name(), event.getEventId());
    }

    public static Collection<PLRuleEventDTO> getPLRuleEventDTOS(Collection<PLRuleEvent> events){
        Collection<PLRuleEventDTO> dtos = new ArrayList<>();
        for (PLRuleEvent event  : events) {
            PLRuleEventDTO dto = getPLRuleEventDTO(event);
            dtos.add(dto);
        }
        return  dtos;
    }

    public static PLGameInstanceDTO getGameInstanceDTO(PLGameInstance instance){
        return new PLGameInstanceDTO(instance.getId(),instance.getPlayer().getId()
                ,instance.getPuzzleLevel().getId(),instance.getPuzzleLevel().getTitle(),instance.getStartPlayTime(),
                instance.getEndPlayTime(),instance.getGameStatus().name(),instance.getAnalyticalData());
    }

    public static Collection<PLGameInstanceDTO> getPLGameInstanceDTOS(Collection<PLGameInstance> instances){
        Collection<PLGameInstanceDTO> dtos = new  ArrayList<>();
        for (PLGameInstance instance  : instances) {
            PLGameInstanceDTO dto = getGameInstanceDTO(instance);
            dtos.add(dto);
        }
        return  dtos;
    }
    public static PLDTO getPuzzleLevelDTO(PuzzleLevel pl) {
        Long plGroundId = 0L;
        if(pl.getPlGrounds().isEmpty())
            plGroundId = 0L;
         else
            plGroundId =pl.getPlGrounds().iterator().next().getId();

        return new PLDTO(pl.getId(), pl.getVersion(), pl.getCreated(),
                pl.getUpdated(), pl.getCreatedBy().getUsername(), pl.getUpdatedBy().getUsername(),
                pl.getApproveDate(), plGroundId, pl.getPuzzleGroup().getId(), pl.getPuzzleGroup().getTitle(),
                pl.getOrdering(), pl.getTitle(), pl.getCode(), pl.getFromAge(), pl.getToAge(), pl.getMaxScore(),
                pl.getFirstStarScore() , pl.getSecondStarScore(), pl.getThirdStartScore(),
                pl.getPuzzleLevelStatus().name(), pl.getPuzzleLevelPrivacy().getValue(), pl.getPuzzleDifficulty().name(),pl.getPuzzleGroup().getIcon().getId(),pl.getPuzzleGroup().getPic().getId());
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
    public static AttributeValue getAttributeValueFromPGVariableImport(AttributeData dto,Attribute attribute,AppMember createdBy){
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

    public static AttributeValue getAttributeValueFromPLVariableImport_New(AttributeData dto, Attribute attribute, AppMember createdBy, Long ownerId, AttributeOwnerType ownerType){
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

        if(dataType.equals(DataType.Binary) && ToolBox.isLong(dto.getValue())) {
            binaryContentId = Long.valueOf(dto.getValue());
            isExpressionValue = Boolean.FALSE;
        }
        else if(dataType.equals(DataType.Long) && ToolBox.isLong(dto.getValue())) {
            longValue = Long.valueOf(dto.getValue());
            isExpressionValue = Boolean.FALSE;
        }
        else if(dataType.equals(DataType.Integer) && ToolBox.isInteger(dto.getValue())) {
            intValue = Integer.valueOf(dto.getValue());
            isExpressionValue = Boolean.FALSE;
        }
        else if(dataType.equals(DataType.Float) && ToolBox.isFloat(dto.getValue())) {
            floatValue = Float.valueOf(dto.getValue());
            isExpressionValue = Boolean.FALSE;
        }
        else if(dataType.equals(DataType.String)) {
            stringValue = dto.getValue();
            isExpressionValue = Boolean.FALSE;
        }
        else {
            isExpressionValue=Boolean.TRUE;
            expressionValue = dto.getValue();
        }

        AttributeValue  attributeValue = new AttributeValue(booleanValue,intValue,longValue,stringValue,
                objectValue,floatValue,binaryContentId, expressionValue,isExpressionValue,bindedAttribute ,attribute,
                1L,DateUtils.getNow(),DateUtils.getNow(),createdBy,createdBy,ownerId,ownerType);
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
        for (AttributeValue attributeValue : input) {
            AttributeValueDTO dto = getAttributeValueDTO(attributeValue);
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

 public static Collection<PLBinaryContentDTO> getPLBinaryContentsDTOS(BinaryContentService binaryContentService,Set<Long> attributes) {
        Collection<PLBinaryContentDTO> contents = new ArrayList<>();
        Iterator<Long> itr = attributes.iterator();
        while(itr.hasNext()) {
            Optional<BinaryContent> binaryContentOptional = binaryContentService.findById(itr.next());
            if(binaryContentOptional.isPresent()) {
                String deviceType="";
                BinaryContent bc = binaryContentOptional.get();
                if(bc.getIs3dContent()){
                    if(bc.getAndriod3Dcontent()!=null && bc.getAndriod3Dcontent().length >0) {
                        deviceType=DeviceType.Android.name();
                    }else if(bc.getIos3Dcontent()!=null && bc.getIos3Dcontent().length>0) {
                        deviceType=DeviceType.IOS.name();
                    }else if(bc.getWeb3Dcontent()!=null && bc.getWeb3Dcontent().length>0) {
                        deviceType=DeviceType.Web.name();
                    }
                }
                PLBinaryContentDTO dto = new PLBinaryContentDTO(bc.getId(),bc.getFileName(),bc.getSize(),bc.getContentType().name(),bc.getIs3dContent(),deviceType);
                contents.add(dto);
            }
        }
        return contents;
    }

    public static JourneyStepDTO getJourneyStepsDTO(JourneyStep step) {
        return new JourneyStepDTO(step.getId(), step.getTitle(), step.getOrdering(), step.getXpos(), step.getYpos(),
                step.getJourney().getId(), step.getJourney().getTitle(), step.getJourney().getOrdering(),
                step.getJourney().getMinToOpenStar(), step.getJourney().getMinToPassStar(),
                step.getPuzzleGroup().getTitle(), step.getPuzzleGroup().getId(), step.getPuzzleGroup().getIcon().getId(), step.getJourney().getPic().getId(),
                step.getVersion(), step.getCreated(), step.getUpdated(), step.getCreatedBy().getUsername(), step.getUpdatedBy().getUsername());
    }

    public static Collection<JourneyStepDTO> getJourneyStepsDTOS(Collection<JourneyStep> input) {
        Collection<JourneyStepDTO> dtos = new ArrayList<JourneyStepDTO>();
        for (JourneyStep journeyStep : input) {
            JourneyStepDTO dto = getJourneyStepsDTO(journeyStep);
            dtos.add(dto);
        }
        return dtos;
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

    public static Collection<PLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> inputs) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();
        for (PuzzleLevel pl : inputs) {
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

    public static PGObjectDTO getPGObjectDTO(PGObject pgObject) {
        return new PGObjectDTO(pgObject.getId(), pgObject.getTitle(), pgObject.getCode(),
                pgObject.getPuzzleGroup().getTitle(), pgObject.getPuzzleGroup().getId(),
                pgObject.getAlCityObject().getId(), pgObject.getAlCityObject().getTitle());
    }

    public static Collection<PGObjectDTO> getPGObjectDTOS(Collection<PGObject> input) {
        Collection<PGObjectDTO> dtos = new ArrayList<PGObjectDTO>();
        for (PGObject alCityObjectInPG : input) {
            PGObjectDTO dto = getPGObjectDTO(alCityObjectInPG);
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
        for (PuzzleGroup input : inputs) {
            Collection<PLDTO> dtos = new ArrayList<PLDTO>();
            dtos = getPuzzleLevelsByPuzzleGroup(input);
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

    public static Collection<LearningSkillTreeDTO> getLearningSkillDTOSFromPL(PuzzleLevel input,LearningSkillService learningSkillService) {
        Collection<LearningSkillTreeDTO> dtos = new ArrayList<LearningSkillTreeDTO>();
        Collection<PLObjective> plObjectiveCollection = input.getPlObjectives();
        Iterator<PLObjective> itr = plObjectiveCollection.iterator();

        while (itr.hasNext()) {
            PLObjective objective = itr.next();
            LearningSkill learningSkill = objective.getLearningSkill();
            LearningSkillTreeDTO  skillTree = new LearningSkillTreeDTO();
            skillTree = DTOUtil.traverseSkillTree(skillTree,learningSkillService ,learningSkill);
            dtos.add(skillTree);
        }
        return dtos;
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
  public static PlLearningTopicDTO getPlLearningTopicDTO(PLLearningTopic entity){
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
    public static Collection<PlLearningTopicDTO> getPlLearningTopicDTOS(PuzzleLevel puzzleLevel) {
        Collection<PlLearningTopicDTO> dtos = new ArrayList<PlLearningTopicDTO>();
        Collection<PLLearningTopic> learningTopics = puzzleLevel.getLearningTopics();
        for (PLLearningTopic learningTopic : learningTopics) {
            PlLearningTopicDTO dto = getPlLearningTopicDTO(learningTopic);
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
        return new BinaryContentDTO(content.getId(),
                content.getFileName(), content.getSize(), null, content.getThumbnail(),content.getIos3Dcontent(),content.getAndriod3Dcontent(),content.getWeb3Dcontent(),
                content.getContentType().name(),content.getIs3dContent(),
                content.getTag1(), content.getTag2(), content.getTag3());
    }


    public static AppMemberDTO getAppMemberDTO(AppMember member){
        String userName="admin";
        if(member.getCreatedBy() == null || member.getCreatedBy() == null)
            userName="admin";
        if(member.getLanguage() ==null) member.setLanguage(Language.English);

        return new AppMemberDTO(member.getId(),member.getAge(),member.getLanguage().name(),
                member.getUsername(),member.getPassword(),member.getIcon().getId(), member.getNickname(),
                member.getMobile(), member.getEmail(),member.getGender().name(),member.getMemberType().getValue(),
                member.getVersion(), member.getCreated(), member.getUpdated(), userName, userName);
    }

    public static AdvertisementDTO getAdvertisementDTO(Advertisement ads){
        return new AdvertisementDTO(ads.getId(),ads.getAdText(), ads.getAdsType().name(),
                ads.getVersion(), ads.getCreated(), ads.getUpdated(), ads.getCreatedBy().getUsername(), ads.getUpdatedBy().getUsername());
    }

    public static AdvertisementDTO getTermsAndCondDTO(Advertisement ads){
        return new AdvertisementDTO(ads.getId(),ads.getAdText(), ads.getAdsType().name(),
                ads.getVersion(), ads.getCreated(), ads.getUpdated(), ads.getCreatedBy().getUsername(), ads.getUpdatedBy().getUsername());
    }

    public static LearningSkillRadarDTO getLearningSkillRadarDTO(AppMember_LearningSkill memberSkill) {
        return new LearningSkillRadarDTO(memberSkill.getLearningSkill().getId(),memberSkill.getApplicationMember().getId(),
                memberSkill.getLearningSkill().getTitle(), memberSkill.getLearningSkill().getType().name(),
                memberSkill.getLevel(),memberSkill.getAmount());
    }

    public static Collection<LearningSkillRadarDTO> getLearningSkillRadarDTOS(Collection<AppMember_LearningSkill> skillTransactions) {
        Collection<LearningSkillRadarDTO> dtos = new ArrayList<LearningSkillRadarDTO>();
        for (AppMember_LearningSkill skillTransaction : skillTransactions) {
            LearningSkillRadarDTO dto = getLearningSkillRadarDTO(skillTransaction);
            dtos.add(dto);
        }
        return dtos;
    }

    public static AppMemberSkillScoreDTO getXPForAAppMemberSkillDTO(AppMember appMember, LearningSkill learningSkill, Collection<ObjectiveTransaction> transactions, BinaryContentService binaryContentService){
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
        AppMemberSkillScoreDTO dto = new AppMemberSkillScoreDTO(learningSkill.getId(),learningSkill.getTitle(),
                learningSkill.getDescription(),level,learningSkill.getType().name(),
                reminder,learningSkill.getWeight(),appMember.getId(),icon.getId());

        return dto;
    }

    public static Collection<AppMemberXPDTO> getXPByWeek(AppMember member, ObjectiveTransactionService objectiveTransactionService) {
        Collection<AppMemberXPDTO> dtos = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();
        for (int index = 0; index < 7; index++){
            AppMemberXPDTO appMemberWeekXPDT_index = DTOUtil.getXPByDate(today.minusDays(index),member,objectiveTransactionService);
            dtos.add(appMemberWeekXPDT_index);

        }
        return dtos;
        /*
        AppMemberXPDTO appMemberWeekXPDT_1 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(1)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_1);

        AppMemberXPDTO appMemberWeekXPDT_2 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(2)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_2);

        AppMemberXPDTO appMemberWeekXPDT_3 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(3)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_3);

        AppMemberXPDTO appMemberWeekXPDT_4 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(4)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_4);

        AppMemberXPDTO appMemberWeekXPDT_5 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(5)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_5);

        AppMemberXPDTO appMemberWeekXPDT_6 = DTOUtil.getXPByDate(DateUtils.getDateByString(today.minusDays(6)),member,objectiveTransactionService);
        dtos.add(appMemberWeekXPDT_6);

         */

    }

    /*
    public static AppMemberXPDTO getXPByDate(Collection<ObjectiveTransaction> transactions, LocalDateTime date, Long memberId,ObjectiveTransactionService objectiveTransactionService) {
        Float xp=0f;
        for (ObjectiveTransaction transaction : transactions) {
            xp += transaction.getAmount();
        }
        return new AppMemberXPDTO( date.getDayOfWeek().getValue(), date.getDayOfWeek().name(), xp, memberId, DateUtils.getDateByString(date) );
    }
     */

    public static AppMemberXPDTO getXPByDate( LocalDateTime date,AppMember member, ObjectiveTransactionService objectiveTransactionService) {
        Float xp=0f;
        String dateString = DateUtils.getDateToString(date);
        Collection<ObjectiveTransaction> transactions = objectiveTransactionService.findByAppMemberAndTransactionDateContaining(member,dateString);

        for (ObjectiveTransaction transaction : transactions) {
            xp += transaction.getAmount();
        }
        return new AppMemberXPDTO( date.getDayOfWeek().getValue(), date.getDayOfWeek().name(), xp, member.getId(), dateString );
    }


    public static Collection<AppMemberDTO> getAppMemberDTOS(Collection<AppMember> appMemberCollection) {
        Collection<AppMemberDTO> dtos = new ArrayList<AppMemberDTO>();
        for (AppMember appMember : appMemberCollection) {
            AppMemberDTO dto = getAppMemberDTO(appMember);
            dtos.add(dto);
        }
        return dtos;
    }

    public static Collection<JourneyDTO> getJourneyDTOS(Collection<Journey> journeys) {
        Collection<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
        for (Journey journey : journeys) {
            JourneyDTO dto = getJourneyDTO(journey);
            dtos.add(dto);
        }
        return dtos;
    }

    public static RoadMapDTO getJourneyRoadMapDTO(RoadMap entity) {
        return new RoadMapDTO(entity.getId(), entity.getXpos(), entity.getYpos(), entity.getJourney().getId(), entity.getGraphic().getId());
    }

    public static Collection<RoadMapDTO> getJourneyRoadMapsDTOS(Collection<RoadMap> roadMaps) {
        Collection<RoadMapDTO> dtos = new ArrayList<RoadMapDTO>();
        for (RoadMap roadMap : roadMaps) {
            RoadMapDTO dto = getJourneyRoadMapDTO(roadMap);
            dtos.add(dto);
        }
        return dtos;
    }

    public static AppMemberWalletDTO getAppMemberWalletDTO(AppMember_WalletItem entity) {
        return new AppMemberWalletDTO
                (
                    entity.getId(),
                    entity.getApplicationMember().getId(),
                    entity.getApplicationMember().getUsername(),
                    entity.getWalletItem().getId(), entity.getWalletItem().getLabel(),
                    entity.getWalletItem().getWalletItemType().getValue(),entity.getWalletItem().getDescription(),
                    entity.getWalletItem().getWalletItemType().getCurrency(),
                    entity.getAmount(), entity.getWalletItem().getIcon().getId()
                );
    }

    public static Collection<AppMemberWalletDTO> getAppMemberWalletDTOS(Collection<AppMember_WalletItem> appMember_walletItems) {
        Collection<AppMemberWalletDTO> dtos = new ArrayList<>();
        for (AppMember_WalletItem appMember_walletItem : appMember_walletItems) {
            AppMemberWalletDTO dto = getAppMemberWalletDTO(appMember_walletItem);
            dtos.add(dto);
        }
        return dtos;
    }

    public static JourneyDTO getJourneyDTO(Journey journey) {
            return new JourneyDTO(journey.getId(), journey.getVersion(), journey.getCreated(), journey.getUpdated(),
                    journey.getCreatedBy().getUsername(), journey.getUpdatedBy().getUsername(),
                    journey.getTitle(),journey.getPic().getId(), journey.getButtonPassedIcon().getId(),
                    journey.getButtonCurrenIcon().getId(), journey.getButtonLockedIcon().getId(),
                    journey.getOrdering(), journey.getMinToPassStar(), journey.getMinToOpenStar());
    }

    public static Collection<LearningSkillDTO> getLearningSkillDTO(Collection<LearningSkill> skills) {
        Collection<LearningSkillDTO>  dtos = new ArrayList<LearningSkillDTO>();

        for (LearningSkill skill : skills) {
            LearningSkillDTO dto = DTOUtil.getLearningSkillDTO(skill);
            dtos.add(dto);
        }
        return dtos;
    }

    public static LearningSkillDTO getLearningSkillDTO(LearningSkill ls) {
        if(ls.getParentSkill() == null)
            return  new LearningSkillDTO(ls.getId(), ls.getTitle(), ls.getType().name(),0L,"",0L,ls.getIcon().getId(),1f,"Root of Skill Tree");;
        LearningSkillDTO lsDTO = new LearningSkillDTO(ls.getId(), ls.getTitle(), ls.getType().name(),ls.getParentSkill().getId(),ls.getParentSkill().getTitle(),ls.getLevelUpSize(),ls.getIcon().getId(), ls.getWeight(), ls.getDescription());
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
        return  new LearningContentDTO(lc.getId() ,lc.getVersion(), lc.getCreated(), lc.getUpdated(),lc.getCreatedBy().getUsername(), lc.getUpdatedBy().getUsername(),
                lc.getDescText(), lc.getDescBrief(), lc.getBinaryContent().getFileName(),lc.getBinaryContent().getSize(),lc.getBinaryContent().getContentType().name(), lc.getBinaryContent().getId());
    }


    public static Collection<LearningContentDTO> getLearningContentDTOS(Collection<LearningContent> learningContents) {
        Collection<LearningContentDTO> dtos = new ArrayList<LearningContentDTO>();
        for (LearningContent learningContent : learningContents) {
            LearningContentDTO dto = getLearningContentDTO(learningContent);
            dtos.add(dto);
        }
        return dtos;
    }

    public static WalletItemTypeDTO getWalletItemTypeDTO(WalletItemType wit) {
        return new WalletItemTypeDTO(wit.getId(),wit.getValue(),wit.getLabel(),wit.getCurrency(),
                wit.getVersion(),wit.getCreated(),wit.getUpdated(),wit.getCreatedBy().getUsername(),wit.getUpdatedBy().getUsername() );
    }

    public static Collection<WalletItemTypeDTO> getWalletItemTypeDTOS(Collection<WalletItemType> walletItemTypes) {
        Collection<WalletItemTypeDTO> dtos = new ArrayList<WalletItemTypeDTO>();
        for (WalletItemType walletItemType : walletItemTypes) {
            WalletItemTypeDTO walletItemTypeDTO = getWalletItemTypeDTO(walletItemType);
            dtos.add(walletItemTypeDTO);
        }
        return dtos;
    }

    public static WalletItemDTO getWalletItemDTO(WalletItem wi)  {
        WalletItemTypeDTO walletItemTypeDTO = getWalletItemTypeDTO(wi.getWalletItemType());
        return new WalletItemDTO(wi.getId(), wi.getLabel(), wi.getValue(),wi.getIcon().getId(),
               wi.getWalletItemType().getValue(),walletItemTypeDTO.getCurrency(),wi.isBaseCurrency(),wi.getIcon().getThumbnail(),wi.getValue());
    }

    public static Collection<WalletItemDTO> getWalletItemDTOS(Collection<WalletItem> walletItems){
        Collection<WalletItemDTO> dtos = new ArrayList<WalletItemDTO>();
        for (WalletItem walletItem : walletItems) {
            WalletItemDTO walletItemDTO = getWalletItemDTO(walletItem);
            dtos.add(walletItemDTO);
        }
        return dtos;
    }


   public static PLGroundDTO getPLGroundDTO(PLGround ground) throws IOException, ClassNotFoundException, JSONException {
       return new PLGroundDTO(ground.getId(), ground.getVersion(), ground.getCreated(), ground.getUpdated(),
               ground.getUpdatedBy().getUsername(), ground.getUpdatedBy().getUsername(),
               ground.getNumRows(), ground.getNumColumns(), ground.getxPosition(), ground.getyPosition(), ground.getzPosition(),
               ground.getxRotation(), ground.getyRotation(), ground.getzRotation(),
               ground.getPuzzleLevel().getId(), ground.getBoardCenterX(), ground.getBoardCenterY(), ground.getBoardCenterZ(),
               ground.getPanLimit(), ground.getInitValueZoomLimit(),ground.getInitValueZoom(),
               ground.getInitPanOffsetX(), ground.getInitPanOffsetY(), ground.getInitPanOffsetZ() );
    }

    public static PLDTO getPlayedPuzzleByAppMemberDTO(PLGameInstance instance) {
        return new PLDTO(instance.getPuzzleLevel().getId(), instance.getVersion(), instance.getCreated(),
                instance.getUpdated(), instance.getCreatedBy().getUsername(), instance.getUpdatedBy().getUsername(),
                instance.getPuzzleLevel().getApproveDate(), instance.getPuzzleLevel().getPlGrounds().iterator().next().getId(),
                instance.getPuzzleLevel().getPuzzleGroup().getId(), instance.getPuzzleLevel().getPuzzleGroup().getTitle(),
                instance.getPuzzleLevel().getOrdering(), instance.getPuzzleLevel().getTitle(), instance.getPuzzleLevel().getCode(),
                instance.getPuzzleLevel().getFromAge(), instance.getPuzzleLevel().getToAge(), instance.getPuzzleLevel().getMaxScore(),
                instance.getPuzzleLevel().getFirstStarScore() , instance.getPuzzleLevel().getSecondStarScore(), instance.getPuzzleLevel().getThirdStartScore(),
                instance.getPuzzleLevel().getPuzzleLevelStatus().name(), instance.getPuzzleLevel().getPuzzleLevelPrivacy().getValue(), instance.getPuzzleLevel().getPuzzleDifficulty().name(),
                instance.getPuzzleLevel().getPuzzleGroup().getIcon().getId(),instance.getPuzzleLevel().getPuzzleGroup().getPic().getId());
        }

    public static Collection<PLDTO> getPlayedPuzzlesByAppMemberDTOS(Collection<PLGameInstance> histories) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();
        for (PLGameInstance history : histories) {
            PLDTO dto = getPlayedPuzzleByAppMemberDTO(history);
            dtos.add(dto);
        }
        return dtos;
    }

    public static Collection<InstanceDTO>  getInstancesDTOS(PuzzleLevel puzzleLevel){
        Collection<InstanceDTO> dtos = new ArrayList<InstanceDTO>();
        Collection<Instance> instances = puzzleLevel.getInstances();
        for (Instance instance : instances) {
            InstanceDTO dto = getInstanceDTO(instance);
            dtos.add(dto);
        }
        return dtos;
    }

    public static InstanceDTO  getInstanceDTO(Instance instance){
        return new InstanceDTO(instance.getId(),instance.getName(),
                                instance.getRow(), instance.getCol(), instance.getZorder(),
                instance.getAlCityObjectInPG().getId(), instance.getAlCityObjectInPG().getTitle(), instance.getAlCityObjectInPG().getCode(),instance.getPuzzleLevel().getId(),
                instance.getVersion(), instance.getCreated(), instance.getUpdated(), instance.getCreatedBy().getUsername(), instance.getUpdatedBy().getUsername());
      }

    public static Collection<ClientTypeDTO> getClientTypeDTOS(Collection<ClientType> clientTypes){
        Collection<ClientTypeDTO> dtos = new ArrayList<ClientTypeDTO>();
        for (ClientType clientType : clientTypes) {
            ClientTypeDTO dto = getClientTypeDTO(clientType);
            dtos.add(dto);
        }
        return dtos;
    }

    public static ClientTypeDTO getClientTypeDTO(ClientType ctype){
        return new ClientTypeDTO(ctype.getId(), ctype.getLabel(),ctype.getValue());
   }

   public static MemberTypeDTO getMemberTypeDTO(MemberType mt){
       return new MemberTypeDTO(mt.getId(), mt.getVersion(), mt.getLabel(), mt.getValue(),
               mt.getCreated(), mt.getUpdated(), mt.getCreatedBy().getUsername(),mt.getUpdatedBy().getUsername(),mt.getCreatedBy().getId(),mt.getUpdatedBy().getId());
   }

   public static Collection<MemberTypeDTO> getMemberTypeDTOS(Collection<MemberType> memberTypes){
       Collection<MemberTypeDTO> dtos = new ArrayList<MemberTypeDTO>();
       for (MemberType memberType : memberTypes) {
           MemberTypeDTO dto = getMemberTypeDTO(memberType);
           dtos.add(dto);
       }
       return dtos;
    }

    public static ObjectCategoryDTO getObjectCategoryDTO(ObjectCategory category){
        return new ObjectCategoryDTO(category.getId(),category.getLabel(),category.getValue(),
                category.getVersion(), category.getCreated(),
                category.getUpdated(),null);
    }
    public static Collection<ObjectCategoryDTO> getObjectCategories(Collection<ObjectCategory> categories){
        Collection<ObjectCategoryDTO> dtos = new ArrayList<ObjectCategoryDTO>();
        for (ObjectCategory category : categories) {
            ObjectCategoryDTO dto = getObjectCategoryDTO(category);
            dtos.add(dto);
        }
        return dtos;
    }

    public static  Collection<RendererDTO> getRendererDTOS(Collection<Renderer> renderers) {
        Collection<RendererDTO> dtos = new ArrayList<RendererDTO>();
        for (Renderer renderer : renderers) {
            RendererDTO dto = DTOUtil.getRendererDTO(renderer);
            dtos.add(dto);
        }
        return dtos;
    }

    public static RendererDTO getRendererDTO(Renderer ar){
        return new RendererDTO(ar.getObjectAction().name(), ar.getHandler(), ar.getClientType().getValue(),
                ar.getId(), ar.getVersion(), ar.getCreated(), ar.getUpdated(), ar.getCreatedBy().getUsername(), ar.getUpdatedBy().getUsername());
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
      //  sortedList = dtos.stream().collect(toList());
      //  sortedList.sort(actionOrderingComparetor);

        return  dtos;
    }
    public static PLRuleDTO getPLRuleDTO(PLRule plRule) {
        return new PLRuleDTO(plRule.getId(), plRule.getTitle(), plRule.getOrdering(),
                plRule.getIgnoreRemaining(), plRule.getCondition(), plRule.getPuzzleLevel().getId(), plRule.getPuzzleLevel().getTitle(),
                plRule.getPlRuleEvent().getId(), plRule.getPlRuleEvent().getName(),plRule.getSubEvent(),
                plRule.getPlRuleEvent().getPlRuleEventType().ordinal(), plRule.getPlRuleEvent().getPlRuleEventType().name());
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
        for (PLCell cell : cells) {
            PLCellDTO dto = new PLCellDTO(cell.getId(), cell.getRow(), cell.getCol(), cell.getZorder(), cell.getPlGround().getId());
            dtos.add(dto);
        }
        return dtos;
    }

    public static PLCellDTO getPLCellDTO(PLCell cell) {
        return new PLCellDTO(cell.getId(), cell.getRow(), cell.getCol(), cell.getZorder(),cell.getPlGround().getId());
    }

    public static Collection<PLCellData> getPLCellDTOS(Collection<PLCell> cells, AttributeService attributeService) {
        Collection<PLCellData> dtos = new ArrayList<PLCellData>();
        Iterator<PLCell> iterator = cells.iterator();
        while(iterator.hasNext()) {
            PLCell cell = iterator.next();
            PostionIntDTO position = new PostionIntDTO(cell.getRow(), cell.getCol(), cell.getZorder());
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

    public static <PLRulePostActionImport_New> void preOrderTraversal_New(PLRulePostActionService plRulePostActionService, PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> node, Long ownerId,Integer ordering) {
        if (node == null) return;

        System.out.println(node.getActionName() + " ");
        node.setPostActionOwnerType(node.getPostActionOwnerType());
        PLRulePostAction postAction = plRulePostActionService.importPostAction_New(node,ownerId,ordering);
        if(node.innerActions==null){node.innerActions= new ArrayList<>();}
        if(node.elseActions==null){node.elseActions = new ArrayList<>();}
        for (PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> child : node.innerActions) {
            child.setPostActionOwnerType(PLRulePostActionOwnerType.Inner_Rule_Post_Action.name());
            preOrderTraversal_New(plRulePostActionService,child,postAction.getId(),++ordering);
        }
        for (PostActionTreeImport_New<com.alcity.test.ruleimport_new.PostActionTreeImport_New> child : node.elseActions) {
            child.setPostActionOwnerType(PLRulePostActionOwnerType.Else.name());
            preOrderTraversal_New(plRulePostActionService,child,postAction.getId(),++ordering);
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
        String actionName=root.getPlRulePostActionType().name();
        String subAction = root.getSubAction();
        if(subAction != null && !subAction.trim().isEmpty())  {
            AttributeData subActionData = new AttributeData(0L,"eventsubtype",0L,subAction,DataType.String.name(),false,null);
            parametersData.add(subActionData);
        }
        String actionKey = root.getActionKey();
        if(actionKey == null)
            actionKey = "0";
        treeExport.setFiedlds(actionName, root.getOrdering(), root.getObjectId(),root.getActionName(),root.getVariable(),root.getValueExperssion(),
                root.getAlertType(), root.getAlertMessage(), actionKey,parametersData);
        Iterator<PLRulePostAction> childIterator = children.iterator();
        Integer counter = 0;
        while(childIterator.hasNext()){
            if(counter == 3) {
                System.out.println("childIterator.next().getActionName()");
            }
            PLRulePostAction child = childIterator.next();
           PLRulePostActionOwnerType ownerType = child.getOwnerType();
            PostActionTreeExport<PostActionTreeExport> subTree=treeExport.getChild(new PostActionTreeExport<>(child.getPlRulePostActionType().name()+":"+child.getSubAction(), child.getOrdering(), child.getObjectId(),child.getActionName(),
                    child.getVariable(),child.getValueExperssion(),child.getAlertType(), child.getAlertMessage(), child.getActionKey(),null,null),ownerType);
            preOrderTraversal(subTree,plRulePostActionService,attributeService,attributeValueService,child) ;
            counter++;
        }
        return treeExport;
     }

    public static LearningSkillTreeDTO traverseSkillTree(LearningSkillTreeDTO skillTreeDTO , LearningSkillService learningSkillService ,LearningSkill root) {
        Collection<LearningSkill> children = learningSkillService.findByParentSkill(root);
        if (root == null)  return skillTreeDTO;
        String parentTitle="";
        Long parentId=0L;

        if(root.getParentSkill()==null) {
            parentTitle = "";
            parentId =0L;
        }
        else {
            parentTitle =root.getParentSkill().getTitle();
            parentId =root.getParentSkill().getId();
        }

        skillTreeDTO.setFields(root.getId(), root.getTitle(),root.getType().name() ,parentTitle,parentId
                ,root.getWeight(),root.getLevelUpSize(),
                root.getIcon().getId(), root.getDescription());
        skillTreeDTO.setChildren(new ArrayList<LearningSkillTreeDTO<LearningSkillTreeDTO>>());
        Iterator<LearningSkill> childIterator = children.iterator();
        while(childIterator.hasNext()){
            LearningSkill child = childIterator.next();
            LearningSkillTreeDTO<LearningSkillTreeDTO> subTree=skillTreeDTO.getChild(new LearningSkillTreeDTO<>(child.getId(), child.getTitle(),child.getType().name() ,child.getParentSkill().getTitle(),child.getParentSkill().getId()
                    ,child.getWeight(),child.getLevelUpSize(),
                    child.getIcon().getId(), child.getDescription()));
            traverseSkillTree(subTree,learningSkillService,child) ;
        }
        return skillTreeDTO;
    }


    public static Collection<PostActionTreeExport> getActionsTrees(PLRulePostActionService plRulePostActionService, AttributeService attributeService,AttributeValueService attributeValueService , PLRule plRule){
        Collection<PostActionTreeExport> actionTrees = new ArrayList<PostActionTreeExport>();
        Collection<PLRulePostAction>  postActions = plRulePostActionService.findByOwnerId(plRule.getId()) ;
        if(plRule.getTitle().equalsIgnoreCase("CheckQueue")){
            System.out.println(plRule.getTitle());

        }
        Iterator<PLRulePostAction> iterator = postActions.iterator();
        Integer counter = 0;
        while(iterator.hasNext()) {
            PLRulePostAction postAction =iterator.next();
            counter++;
            PostActionTreeExport tree = new PostActionTreeExport();
            if(counter==10){
                System.out.println(postAction.getPlRulePostActionType());
            }
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

    public static Collection<AttributeData>  getPropertiesDTOForPGOInstance(Collection<Attribute>  properties){
        Collection<AttributeData> records = new ArrayList<AttributeData>();
        Collection<Attribute> duplicated = properties.stream().collect(Collectors.groupingBy(Attribute::getName))
                .entrySet().stream().filter(e -> e.getValue().size() > 1)
                .flatMap(e -> e.getValue().stream()).toList();

        Iterator<Attribute> iteratorDuplicate = duplicated.iterator();
        while(iteratorDuplicate.hasNext()) {
            Attribute attribute = iteratorDuplicate.next();
            if(attribute.getAttributeOwnerType().equals(AttributeOwnerType.Puzzle_Group_Object_Property)) {
                properties.remove(attribute);
            }
        }

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