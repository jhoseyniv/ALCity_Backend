package com.alcity.utility;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.Interpreter.PLObjectiveData;
import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.alobject.*;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.base.LearningSkillDTO;
import com.alcity.dto.base.WalletItemTypeDTO;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.dto.user.ApplicationMemberDTO;
import com.alcity.dto.user.MemberTypeDTO;
import com.alcity.dto.user.WalletItemDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class DTOUtil {

    public static ObjectCategoryDTO getObjectCategoryDTO(ObjectCategory oc){
        ObjectCategoryDTO objectCategoryDTO = new ObjectCategoryDTO(oc.getId(),oc.getLabel(),oc.getValue(), oc.getVersion(), oc.getCreated(),
                oc.getUpdated(),null);
            return objectCategoryDTO;
    }

    public static PuzzleLevelLDTO getPuzzleLevelDTO(PuzzleLevel pl) {
        PuzzleLevelLDTO plDTO = new PuzzleLevelLDTO();

        plDTO.setId(pl.getId());
        plDTO.setVersion(pl.getVersion());
        plDTO.setCode(pl.getCode());
        plDTO.setApproveDate(pl.getApproveDate());
        plDTO.setTitle(pl.getTitle());
        plDTO.setToAge(pl.getToAge());
        plDTO.setFromAge(pl.getFromAge());
        plDTO.setOrdering(pl.getOrdering());
        plDTO.setMaxScore(pl.getMaxScore());
        plDTO.setPuzzleGroupId(pl.getPuzzleGroup().getId());
        plDTO.setUpdated(pl.getUpdated());
        plDTO.setCreated(pl.getCreated());
        plDTO.setCreatedBy(pl.getCreatedBy().getUsername());
        plDTO.setUpdatedBy(pl.getUpdatedBy().getUsername());
        plDTO.setCreatedById(pl.getCreatedBy().getId());
        plDTO.setUpdatedById(pl.getUpdatedBy().getId());

        plDTO.setPuzzleLevelDifficulty(pl.getPuzzleDifficulty().toString());
        plDTO.setPuzzleLevelPrivacy(pl.getPuzzleLevelPrivacy().getLabel());
        plDTO.setPuzzleLevelStatus(pl.getPuzzleLevelStatus().toString());
        plDTO.setIconId(pl.getIcon().getId());
        plDTO.setPicId(pl.getPicture().getId());

        return plDTO;
    }

    public static PuzzleLevelLDTO getPuzzleLevelDTO(Optional<PuzzleLevel> puzzleLevelOptional) {
        PuzzleLevel puzzleLevel = new PuzzleLevel();
        PuzzleLevelLDTO puzzleLevelDTO= new PuzzleLevelLDTO();

        if(puzzleLevelOptional.isPresent()){
            puzzleLevel = puzzleLevelOptional.get();
            puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevel);

            Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevel);
            Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection = DTOUtil.getPuzzleLevel_LearningTopicDTOS(puzzleLevel);
            Collection<PLGroundDTO> puzzleLevelGroundDTOCollection = DTOUtil.getPuzzleLevelGroundDTOS(puzzleLevel);
            Collection<PermitedPlayerDTO> permitedPlayerDTOCollection = DTOUtil.getPermitedPlayerDTOS(puzzleLevel);
            Collection<PLInstanceDTO> plInstanceDTOS = DTOUtil.getPuzzleLevelInstance(puzzleLevel);

            puzzleLevelDTO.setPuzzleLevelGroundDTOCollection(puzzleLevelGroundDTOCollection);
            puzzleLevelDTO.setPuzzleLevelObjectiveDTOCollection(puzzleLevelObjectiveDTOCollection);
            puzzleLevelDTO.setPuzzleLevel_learningTopicDTOCollection(puzzleLevel_learningTopicDTOCollection);
            puzzleLevelDTO.setPermitedPlayerDTOCollection(permitedPlayerDTOCollection);
            puzzleLevelDTO.setPlInstanceDTOS(plInstanceDTOS);
        } else puzzleLevelDTO = null;

        return puzzleLevelDTO;
    }
    public static AttributeValueDTO getAttributeValueDTO(AttributeValue value){
        AttributeValueDTO valueDTO= new AttributeValueDTO(value.getId(), value.getLongValue(), value.getDoubleValue(),
                        value.getIntValue(), value.getBinaryContentId() ,value.getStringValue(),value.getObjectValue() );
        return valueDTO;
    }
    public static Collection<AttributeValueDTO> getAttributesValueDTOS(Collection<AttributeValue> values) {
        Collection<AttributeValueDTO> dtos = new ArrayList<AttributeValueDTO>();
        Iterator<AttributeValue> itr = values.iterator();
        while (itr.hasNext()) {
            AttributeValueDTO valueDTO = getAttributeValueDTO(itr.next());
            dtos.add(valueDTO);
        }
        return  dtos;
    }
        public static AttributeDTO getAttributeDTO(Attribute att){
            Collection<AttributeValueDTO> valueDTOS = getAttributesValueDTOS(att.getAttributeValues()) ;
            AttributeDTO attributeDTO = new AttributeDTO(att.getId(), att.getName(), att.getOwnerId(), att.getAttributeOwnerType().name(), att.getDataType().name(),valueDTOS ,
                    att.getVersion(), att.getCreated(), att.getCreated(), att.getCreatedBy().getUsername(), att.getUpdatedBy().getUsername());
           return attributeDTO;
        }

    public static Collection<AttributeDTO> getAttributesDTOS(Collection<Attribute> attributeCollection) {
        Collection<AttributeDTO> attributeDTOCollection = new ArrayList<AttributeDTO>();
        Iterator<Attribute> itr = attributeCollection.iterator();
        while (itr.hasNext()) {
           AttributeDTO attributeDTO = getAttributeDTO(itr.next());
            attributeDTOCollection.add(attributeDTO);
        }
        return attributeDTOCollection;
    }



    public static Collection<JourneyStepDTO> getJorenyStepsDTOS(Collection<JourneyStep> journeyStepCollection) {
        Collection<JourneyStepDTO> journeyStepDTOCollection = new ArrayList<JourneyStepDTO>();
        Iterator<JourneyStep> itr = journeyStepCollection.iterator();
        while (itr.hasNext()) {
            JourneyStep journeyStep = itr.next();
            JourneyStepDTO journeyStepDTO = new JourneyStepDTO();
            journeyStepDTO.setId(journeyStep.getId());
            journeyStepDTO.setVersion(journeyStep.getVersion());
            journeyStepDTO.setOrdering(journeyStep.getOrdering());
            journeyStepDTO.setXpos(journeyStep.getXpos());
            journeyStepDTO.setYpos(journeyStep.getYpos());
            journeyStepDTO.setTitle(journeyStep.getTitle());
            journeyStepDTO.setCreated(journeyStep.getCreated());
            journeyStepDTO.setUpdated(journeyStep.getUpdated());
            journeyStepDTOCollection.add(journeyStepDTO);
        }
        return journeyStepDTOCollection;
    }
    public static void copyAttributesActionFromTo(Long fromOwnerId,Long toOwnerId, AttributeOwnerType from , AttributeOwnerType to,
                                 AttributeService attributeService, AttributeValueService attributeValueService){
        //ActionRenderer actionRenderer = action.getActionRenderer();
        Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerType(fromOwnerId, from);
        Iterator<Attribute> itr = parameters.iterator();
        while(itr.hasNext()){
            Attribute att = new Attribute();
            att = itr.next();
            Attribute newRecord = new Attribute(att.getName(),toOwnerId,to,att.getDataType(),
                    att.getVersion(),att.getCreated(),att.getUpdated(),att.getCreatedBy(),att.getUpdatedBy());
            attributeService.save(newRecord);

            attributeValues = att.getAttributeValues();
            Iterator<AttributeValue> itrAttributeValuesIterator = attributeValues.iterator();
            while(itrAttributeValuesIterator.hasNext()) {
                AttributeValue attValue = new AttributeValue();
                attValue = itrAttributeValuesIterator.next();
                AttributeValue newAttributeValue = new AttributeValue(attValue.getBooleanValue(),attValue.getIntValue(),attValue.getLongValue(),attValue.getStringValue(),
                        attValue.getObjectValue(),attValue.getDoubleValue(),attValue.getBinaryContentId(),newRecord,newRecord,
                        attValue.getVersion(),attValue.getCreated(),attValue.getUpdated(),attValue.getCreatedBy(),attValue.getUpdatedBy());

                //for log info
                if(att.getName().equalsIgnoreCase("CODE"))   newAttributeValue.setStringValue("NEW CODE FOR ALCITY Object");
                if(att.getName().equalsIgnoreCase("aSync"))     newAttributeValue.setStringValue("True");

                attributeValueService.save(newAttributeValue);
            }


        }

    }
    public static void copyPropertiesFromTo(Long fromOwnerId,Long toOwnerId, AttributeOwnerType from , AttributeOwnerType to,
                                                  AttributeService attributeService, AttributeValueService attributeValueService){
        //ActionRenderer actionRenderer = action.getActionRenderer();
        Collection<AttributeValue> attributeValues = new ArrayList<AttributeValue>();
        Collection<Attribute> parameters = attributeService.findByOwnerIdAndAttributeOwnerType(fromOwnerId, from);
        Iterator<Attribute> itr = parameters.iterator();
        while(itr.hasNext()){
            Attribute att = new Attribute();
            att = itr.next();
            Attribute newRecord = new Attribute(att.getName(),toOwnerId,to,att.getDataType(),
                    att.getVersion(),att.getCreated(),att.getUpdated(),att.getCreatedBy(),att.getUpdatedBy());
            attributeService.save(newRecord);

            attributeValues = att.getAttributeValues();
            Iterator<AttributeValue> itrAttributeValuesIterator = attributeValues.iterator();
            while(itrAttributeValuesIterator.hasNext()) {
                AttributeValue attValue = new AttributeValue();
                attValue = itrAttributeValuesIterator.next();
                AttributeValue newAttributeValue = new AttributeValue(attValue.getBooleanValue(),attValue.getIntValue(),attValue.getLongValue(),attValue.getStringValue(),
                        attValue.getObjectValue(),attValue.getDoubleValue(),attValue.getBinaryContentId(),newRecord,newRecord,
                        attValue.getVersion(),attValue.getCreated(),attValue.getUpdated(),attValue.getCreatedBy(),attValue.getUpdatedBy());

                //for log info
                if(att.getName().equalsIgnoreCase("CODE"))   newAttributeValue.setStringValue("NEW CODE FOR ALCITY Object");
                if(att.getName().equalsIgnoreCase("aSync"))     newAttributeValue.setStringValue("True");

                attributeValueService.save(newAttributeValue);
            }


        }

    }
    public static Collection<PuzzleLevelLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> puzzleLevelCollection) {
        Collection<PuzzleLevelLDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelLDTO>();

        Iterator<PuzzleLevel> itrPuzzleLevel = puzzleLevelCollection.iterator();
        while (itrPuzzleLevel.hasNext()) {
            PuzzleLevel puzzleLevel = itrPuzzleLevel.next();
            PuzzleLevelLDTO dtoObject = new PuzzleLevelLDTO();
            dtoObject.setId(puzzleLevel.getId());
            dtoObject.setVersion(puzzleLevel.getVersion());
            dtoObject.setOrdering(puzzleLevel.getOrdering());
            dtoObject.setApproveDate(puzzleLevel.getApproveDate());
            dtoObject.setCreated(puzzleLevel.getCreated());
            dtoObject.setUpdated(puzzleLevel.getUpdated());
            dtoObject.setCode(puzzleLevel.getCode());
            dtoObject.setTitle(puzzleLevel.getTitle());
            dtoObject.setFromAge(puzzleLevel.getFromAge());
            dtoObject.setToAge(puzzleLevel.getToAge());
            dtoObject.setMaxScore(puzzleLevel.getMaxScore());
            dtoObject.setPuzzleGroupId(puzzleLevel.getPuzzleGroup().getId());
            dtoObject.setPuzzleLevelStatus(puzzleLevel.getPuzzleLevelStatus().name());
            dtoObject.setPuzzleLevelDifficulty(puzzleLevel.getPuzzleDifficulty().name());
            dtoObject.setPuzzleLevelPrivacy(puzzleLevel.getPuzzleLevelPrivacy().getValue());

            puzzleLevelDTOCollection.add(dtoObject);
        }
        return puzzleLevelDTOCollection;
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

    public static Collection<LearningSkillLContentDTO> getLearningSkillContentDTOS(Collection<LearningSkillContent> learningSkillContents) {
        Collection<LearningSkillLContentDTO> learningSkillLContentDTOS = new ArrayList<LearningSkillLContentDTO>();
        Iterator<LearningSkillContent> itrPuzzleSkills = learningSkillContents.iterator();
        while (itrPuzzleSkills.hasNext()) {
            LearningSkillLContentDTO dtoObject = new LearningSkillLContentDTO();
            LearningSkillContent learningSkillContent = itrPuzzleSkills.next();

            dtoObject.setId(learningSkillContent.getId());
            dtoObject.setVersion(learningSkillContent.getVersion());
            dtoObject.setCreated(learningSkillContent.getCreated());
            dtoObject.setCreatedById(learningSkillContent.getCreatedBy().getId());
            dtoObject.setUpdated(learningSkillContent.getUpdated());
            dtoObject.setUpdatedById(learningSkillContent.getUpdatedBy().getId());
            dtoObject.setCreatedBy(learningSkillContent.getCreatedBy().getUsername());
            dtoObject.setUpdatedBy(learningSkillContent.getUpdatedBy().getUsername());
            dtoObject.setLearningSkillId(learningSkillContent.getLearningSkill().getId());
            dtoObject.setLearningSkillTitle(learningSkillContent.getLearningSkill().getValue());
            dtoObject.setLearningContentId(learningSkillContent.getLearningContent().getId());
            dtoObject.setPuzzleGroupId(learningSkillContent.getPuzzleGroup().getId());
            dtoObject.setPuzzleGroupTitle(learningSkillContent.getPuzzleGroup().getTitle());
            learningSkillLContentDTOS.add(dtoObject);
        }
        return learningSkillLContentDTOS;
    }
    public static ALCityObjectInPGDTO getALCityObjectInPGDTO(ALCityObjectInPG alCityObjectInPG) {
        ALCityObjectInPGDTO dto = new ALCityObjectInPGDTO();
        dto.setId(alCityObjectInPG.getId());
        dto.setCode(alCityObjectInPG.getCode());
        dto.setTitle(alCityObjectInPG.getTitle());

        dto.setPuzzleGroup(alCityObjectInPG.getPuzzleGroup().getTitle());
        dto.setPuzzleGroupId(alCityObjectInPG.getPuzzleGroup().getId());
        dto.setAlCityObject(alCityObjectInPG.getAlCityObject().getTitle());
        dto.setAlCityObjectId(alCityObjectInPG.getAlCityObject().getId());
        dto.setVersion(alCityObjectInPG.getVersion());
        dto.setCreated(alCityObjectInPG.getCreated());
        dto.setUpdated(alCityObjectInPG.getUpdated());
        dto.setCreatedBy(alCityObjectInPG.getCreatedBy().getUsername());
        dto.setUpdatedBy(alCityObjectInPG.getUpdatedBy().getUsername());

        return  dto;
    }
    public static Collection<ALCityObjectInPGDTO> getALCityObjectInPGDTOS(Collection<ALCityObjectInPG> alCityObjectInPGCollection) {
        Collection<ALCityObjectInPGDTO> dtoObjects = new ArrayList<ALCityObjectInPGDTO>();
        Iterator<ALCityObjectInPG> iterator = alCityObjectInPGCollection.iterator();
        while (iterator.hasNext()) {
            ALCityObjectInPG alCityObjectInPG = iterator.next();
            ALCityObjectInPGDTO dtoObject = getALCityObjectInPGDTO(alCityObjectInPG);
            dtoObjects.add(dtoObject);
        }
        return dtoObjects;

    }

    public static Collection<PGDTO> getPuzzleGroupDTOS(Collection<PuzzleGroup> puzzleGroups) {
        Collection<PGDTO> pgdtos = new ArrayList<PGDTO>();
        Iterator<PuzzleGroup> itr = puzzleGroups.iterator();
        while (itr.hasNext()) {
            PGDTO pgdto = getPuzzleGroupDTO(itr.next());
            pgdtos.add(pgdto);
        }
        return pgdtos;
    }

    public static PGDTO getPuzzleGroupDTO(PuzzleGroup puzzleGroup) {
        PGDTO puzzleGroupDTO = new PGDTO();
        puzzleGroupDTO.setId(puzzleGroup.getId());
        puzzleGroupDTO.setVersion(puzzleGroup.getVersion());
        puzzleGroupDTO.setCreated(puzzleGroup.getCreated());
        puzzleGroupDTO.setUpdated(puzzleGroup.getUpdated());
        puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
        puzzleGroupDTO.setPuzzleCategoryId(puzzleGroup.getPuzzleCategory().getId());
        puzzleGroupDTO.setIconId(puzzleGroup.getIcon().getId());
        puzzleGroupDTO.setPicId(puzzleGroup.getPic().getId());
        puzzleGroupDTO.setCreated(puzzleGroup.getCreated());
        puzzleGroupDTO.setUpdated(puzzleGroup.getUpdated());
        puzzleGroupDTO.setCreatedBy(puzzleGroup.getCreatedBy().getUsername());
        puzzleGroupDTO.setUpdatedBy(puzzleGroup.getUpdatedBy().getUsername());

        return puzzleGroupDTO;
    }

    public static Collection<PuzzleCategoryDTO> getPuzzleCategoryDTOS(Collection<PuzzleCategory> puzzleCategories) {
        Collection<PuzzleCategoryDTO> puzzleCategoryDTOCollection = new ArrayList<PuzzleCategoryDTO>();
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        Iterator<PuzzleCategory> itr = puzzleCategories.iterator();
        while(itr.hasNext()) {
            PuzzleCategory puzzleCategory = itr.next();
            puzzleCategoryDTO = DTOUtil.getPuzzleCategoryDTO(puzzleCategory);
            puzzleCategoryDTOCollection.add(puzzleCategoryDTO);
        }
        return puzzleCategoryDTOCollection;
    }
    public static PuzzleCategoryDTO getPuzzleCategoryDTO(PuzzleCategory pc) {
        PuzzleCategoryDTO pcDTO = new PuzzleCategoryDTO();
        Collection<PuzzleGroup> puzzleGroupCollection = pc.getPuzzleGroupCollection();
        pcDTO.setId(pc.getId());
        pcDTO.setLabel(pc.getLabel());
        pcDTO.setValue(pc.getValue());
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
        plObjectiveDTO.setPuzzleLevelId(plObjective.getPuzzleLevel().getId());
        plObjectiveDTO.setUpdated(plObjective.getUpdated());
        plObjectiveDTO.setCreated(plObjective.getCreated());
        plObjectiveDTO.setCreatedBy(plObjective.getCreatedBy().getUsername());
        plObjectiveDTO.setUpdatedBy(plObjective.getUpdatedBy().getUsername());
        plObjectiveDTO.setUpdatedById(plObjective.getUpdatedBy().getId());
        plObjectiveDTO.setCreatedById(plObjective.getCreatedBy().getId());
        plObjectiveDTO.setSkillId(plObjective.getLearningSkill().getId());
        plObjectiveDTO.setSkillTitle(plObjective.getLearningSkill().getValue());
        plObjectiveDTO.setWalletItemId(plObjective.getWalletItem().getId());
        plObjectiveDTO.setWalletItemTitle(plObjective.getWalletItem().getValue());
        return  plObjectiveDTO;
    }

    public static Collection<PLObjectiveDTO> getPuzzleLevelObjectiveDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> plObjectiveCollection = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> itr_objectives = plObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjectiveDTO plObjectiveDTO = getPuzzleLevelObjectiveDTO(itr_objectives.next());
            plObjectiveDTOCollection.add(plObjectiveDTO);
        }
        return plObjectiveDTOCollection;
    }


    //this method used for create Interpreter json
    public static Collection<PLObjectiveData> getPuzzleLevelObjectiveData(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveData> puzzleLevelObjectiveDataCollection = new ArrayList<PLObjectiveData>();
        Collection<PLObjective> puzzleLevelObjectiveCollection = puzzleLevel.getPlObjectives();

        Iterator<PLObjective> itr_objectives = puzzleLevelObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjective puzzleLevelObjective = itr_objectives.next();
            PLObjectiveData puzzleLevelObjectiveData = new PLObjectiveData();
            puzzleLevelObjectiveData.setId(puzzleLevelObjective.getId());
            puzzleLevelObjectiveData.setTitle(puzzleLevelObjective.getTitle());
            puzzleLevelObjectiveData.setDescription(puzzleLevelObjective.getDescription());
            puzzleLevelObjectiveData.setCondition(puzzleLevelObjective.getCondition());
            puzzleLevelObjectiveData.setRewardAmount(puzzleLevelObjective.getRewardAmount());
            puzzleLevelObjectiveData.setRewardId(puzzleLevelObjective.getWalletItem().getId());
            puzzleLevelObjectiveData.setSkillAmount(puzzleLevelObjective.getSkillAmount());

            puzzleLevelObjectiveDataCollection.add(puzzleLevelObjectiveData);
        }
        return puzzleLevelObjectiveDataCollection;
    }


    public static Collection<PuzzleLevel_LearningTopicDTO> getPuzzleLevel_LearningTopicDTOS(PuzzleLevel puzzleLevel) {
        Collection<PuzzleLevel_LearningTopicDTO> pl_ltDTOCollection = new ArrayList<PuzzleLevel_LearningTopicDTO>();
        Collection<LearningSubjectInPL> pl_learningTopicCollection = puzzleLevel.getLearningSubjectInPLCollection();
        Iterator<LearningSubjectInPL> itr_learningTopics = pl_learningTopicCollection.iterator();
        while(itr_learningTopics.hasNext()) {
            LearningSubjectInPL pl_learningTopic = itr_learningTopics.next();
            PuzzleLevel_LearningTopicDTO pl_learningTopicDTO = new PuzzleLevel_LearningTopicDTO();
            LearningTopicDTO learningTopicDTO = new LearningTopicDTO();
            LearningContentDTO learningContentDTO = new LearningContentDTO();

            pl_learningTopicDTO.setId(pl_learningTopic.getId());
            pl_learningTopicDTO.setVersion(pl_learningTopic.getVersion());
            pl_learningTopicDTO.setUpdated(pl_learningTopic.getUpdated());
            pl_learningTopicDTO.setCreated(pl_learningTopic.getCreated());

            learningTopicDTO.setId(pl_learningTopic.getLearningTopic().getId());
            learningTopicDTO.setTitle(pl_learningTopic.getLearningTopic().getTitle());
            learningTopicDTO.setVersion(pl_learningTopic.getLearningTopic().getVersion());
            learningTopicDTO.setCreated(pl_learningTopic.getLearningTopic().getCreated());
            learningTopicDTO.setUpdated(pl_learningTopic.getLearningTopic().getUpdated());
            pl_learningTopicDTO.setLearningTopicDTO(learningTopicDTO);

            learningContentDTO.setId(pl_learningTopic.getLearningContent().getId());
            learningContentDTO.setVersion(pl_learningTopic.getLearningContent().getVersion());
            learningContentDTO.setCreated(pl_learningTopic.getLearningContent().getCreated());
            learningContentDTO.setUpdated(pl_learningTopic.getLearningContent().getUpdated());

            learningContentDTO.setDescText(pl_learningTopic.getLearningContent().getDescText());
            learningContentDTO.setDescBrief(pl_learningTopic.getLearningContent().getDescBrief());

            pl_learningTopicDTO.setLearningContentDTO(learningContentDTO);


            pl_ltDTOCollection.add(pl_learningTopicDTO);
        }

        return pl_ltDTOCollection;
    }
    public static BinaryContentDTO getBinaryContentDTOWithoutContent(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(),
                content.getCreatedBy().getUsername(),content.getUpdatedBy().getUsername(),
                 content.getFileName(), content.getSize(), null,content.getContentType().name());
        return binaryContentDTO;
    }
    public static BinaryContentDTO getBinaryContentDTO(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(),
                content.getCreatedBy().getUsername(),content.getUpdatedBy().getUsername(),content.getFileName(), content.getSize(), content.getContent(),content.getContentType().name());
        return binaryContentDTO;
    }

    public static ApplicationMemberDTO getApplicationMemberDTO(ApplicationMember member){
        ApplicationMemberDTO memberDTO = new ApplicationMemberDTO(member.getId(),member.getAge(),
                member.getUsername(),member.getPassword(), member.getNickname(), member.getMobile(), member.getEmail(),
                member.getAvatar(), member.getCreatedBy().getId(), member.getUpdatedBy().getId());

        return memberDTO;
    }
    public static JourneyDTO getJourneyDTO(Journey journey) {
         JourneyDTO journeyDTO = new JourneyDTO();
         journeyDTO.setId(journey.getId());
         journeyDTO.setVersion(journey.getVersion());
         journeyDTO.setCreated(journey.getCreated());
         journeyDTO.setUpdated(journey.getUpdated());
         journeyDTO.setTitle(journey.getTitle());
         journeyDTO.setCreatedBy(journey.getCreatedBy().getUsername());
         journeyDTO.setUpdatedBy(journey.getUpdatedBy().getUsername());
         journeyDTO.setCreatedById(journey.getCreatedBy().getId());
         journeyDTO.setUpdatedById(journey.getUpdatedBy().getId());
         journeyDTO.setGraphic(getBinaryContentDTOWithoutContent(journey.getGraphic()));
         return journeyDTO;
    }

    public static LearningSkillDTO getLearningSkillDTO(LearningSkill ls) {
        LearningSkillDTO lsDTO = new LearningSkillDTO(ls.getId(), ls.getLabel(), ls.getValue(),
                ls.getVersion(), ls.getCreated(), ls.getUpdated(),ls.getCreatedBy().getUsername(),ls.getUpdatedBy().getUsername());
        return lsDTO;
    }
    public static LearningTopicDTO getLearningTopicDTO(LearningTopic lt) {

        LearningTopicDTO lsDTO =null;
        if(lt.getParentTopic()==null)
            lsDTO = new LearningTopicDTO(lt.getId(), lt.getTitle(), "ROOT",0L,
                    lt.getVersion(), lt.getCreated(), lt.getUpdated(),lt.getCreatedBy().getUsername(),lt.getUpdatedBy().getUsername());
        else
            lsDTO = new LearningTopicDTO(lt.getId(), lt.getTitle(), lt.getParentTopic().getTitle(),lt.getParentTopic().getId(),
                lt.getVersion(), lt.getCreated(), lt.getUpdated(),lt.getCreatedBy().getUsername(),lt.getUpdatedBy().getUsername());

        return lsDTO;
    }
    public static Collection<PLObjectiveDTO> getLearningTopicDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> plObjectiveCollection = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> itr_objectives = plObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjectiveDTO plObjectiveDTO = getPuzzleLevelObjectiveDTO(itr_objectives.next());
            plObjectiveDTOCollection.add(plObjectiveDTO);
        }
        return plObjectiveDTOCollection;
    }

    public static WalletItemTypeDTO getWalletItemTypeDTO(WalletItemType wit) {
        WalletItemTypeDTO walletItemTypeDTO = new WalletItemTypeDTO(wit.getId(), wit.getLabel(), wit.getValue(), wit.getVersion(),
                wit.getCreated(), wit.getUpdated(),wit.getCurrency(),wit.getWalletItemCategory().toString() );
        return walletItemTypeDTO;
    }
    public static WalletItemDTO getWalletItemDTO(WalletItem wi) {
        WalletItemTypeDTO walletItemTypeDTO = getWalletItemTypeDTO(wi.getWalletItemType());
        WalletItemDTO walletItemDTO = new WalletItemDTO(wi.getId(), wi.getLabel(), wi.getValue(), wi.getVersion(),
                wi.getCreated(), wi.getUpdated(),wi.getCreatedBy().getUsername(),wi.getUpdatedBy().getUsername(),wi.getCreatedBy().getId(),
                wi.getUpdatedBy().getId(), walletItemTypeDTO);
        return walletItemDTO;
    }
    public static CameraSetupDTO getCameraSetupDTO(CameraSetup cs){
        CameraSetupDTO cameraSetupDTO = new CameraSetupDTO(cs.getId(), cs.getVersion(), cs.getCreated(), cs.getUpdated(),
                cs.getCreatedBy().getUsername(),cs.getUpdatedBy().getUsername(),
                cs.getxPosition(),cs.getyPosition(),cs.getzPosition(),cs.getxRotation(),cs.getyRotation(),cs.getzRotation());
            return cameraSetupDTO;
    }
    public static Collection<PLGroundDTO> getPuzzleLevelGroundDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLGroundDTO> plGroundDTOCollection = new ArrayList<PLGroundDTO>();
        Collection<PLGround> plGroundCollection = puzzleLevel.getPlGrounds();
        Iterator<PLGround> itr_Grounds = plGroundCollection.iterator();
        while(itr_Grounds.hasNext()) {
            PLGround puzzleLevelGround = itr_Grounds.next();
            PLGroundDTO plGroundDTO = new PLGroundDTO();
            plGroundDTO.setId(puzzleLevelGround.getId());
            plGroundDTO.setVersion(puzzleLevelGround.getVersion());
            plGroundDTO.setCreated(puzzleLevelGround.getCreated());
            plGroundDTO.setUpdated(puzzleLevelGround.getUpdated());
            plGroundDTO.setNumRows(puzzleLevelGround.getNumRows());
            plGroundDTO.setNumColumns(puzzleLevelGround.getNumColumns());
            CameraSetupDTO cameraSetupDTO = getCameraSetupDTO(puzzleLevelGround.getCameraSetup());
            plGroundDTO.setCameraSetup(cameraSetupDTO);

            BinaryContent boardGraphic = puzzleLevelGround.getBoardGraphic();
            BinaryContentDTO backGroundDTO= new BinaryContentDTO();
            backGroundDTO.setId(boardGraphic.getId());
            backGroundDTO.setFileName(boardGraphic.getFileName());
            backGroundDTO.setVersion(boardGraphic.getVersion());
            backGroundDTO.setSize(boardGraphic.getSize());
            backGroundDTO.setUpdated(puzzleLevelGround.getUpdated());
            backGroundDTO.setCreated(puzzleLevelGround.getCreated());


            plGroundDTO.setBoardGraphic(backGroundDTO);
            plGroundDTOCollection.add(plGroundDTO);
        }


        return plGroundDTOCollection;
    }

    public static Collection<PermitedPlayerDTO> getPermitedPlayerDTOS(PuzzleLevel puzzleLevel) {
        Collection<PermitedPlayerDTO> permitedPlayerDTOCollection = new ArrayList<PermitedPlayerDTO>();
        Collection<PermitedPlayer> permitedPlayerCollection = puzzleLevel.getPermitedPlayerCollection();
        Iterator<PermitedPlayer> itr_permitedPlayers = permitedPlayerCollection.iterator();

        while(itr_permitedPlayers.hasNext()){
            PermitedPlayer permitedPlayer = itr_permitedPlayers.next();
            PermitedPlayerDTO permitedPlayerDTO = new PermitedPlayerDTO();
            permitedPlayerDTO.setId(permitedPlayer.getId());
            permitedPlayerDTO.setVersion(permitedPlayer.getVersion());
            permitedPlayerDTO.setPlayerUsername(permitedPlayer.getPlayer().getUsername());
            permitedPlayerDTO.setEmail(permitedPlayer.getPlayer().getEmail());
            permitedPlayerDTO.setUpdated(permitedPlayer.getUpdated());
            permitedPlayerDTO.setCreated(permitedPlayer.getCreated());
            permitedPlayerDTOCollection.add(permitedPlayerDTO);

        }
        return permitedPlayerDTOCollection;
    }

    public static Collection<PLInstanceDTO>  getPuzzleLevelInstance(PuzzleLevel puzzleLevel){
        Collection<PLInstanceDTO> plInstanceDTOS = new ArrayList<PLInstanceDTO>();
        Collection<ALCityInstanceInPL> alCityInstanceInPLCollection = puzzleLevel.getPuzzleGroupObjectInstanceCollection();
        Iterator<ALCityInstanceInPL> itr = alCityInstanceInPLCollection.iterator();

        while(itr.hasNext()){
            ALCityInstanceInPL pgObjectInstance = itr.next();
            PLInstanceDTO plInstanceDTO = new PLInstanceDTO();
            plInstanceDTO.setId(pgObjectInstance.getId());
            plInstanceDTO.setVersion(pgObjectInstance.getVersion());
            plInstanceDTO.setCol(pgObjectInstance.getCol());
            plInstanceDTO.setRow(pgObjectInstance.getRow());
            plInstanceDTO.setzOrder(pgObjectInstance.getzOrder());
            plInstanceDTO.setCreated(pgObjectInstance.getCreated());
            plInstanceDTO.setUpdated(pgObjectInstance.getUpdated());
            plInstanceDTO.setCreatedById(pgObjectInstance.getCreatedBy().getId());
            plInstanceDTO.setUpdatedByID(pgObjectInstance.getUpdatedBy().getId());
            plInstanceDTO.setUpdatedBy(pgObjectInstance.getUpdatedBy().getUsername());
            plInstanceDTO.setCreatedBy(pgObjectInstance.getCreatedBy().getUsername());
            plInstanceDTO.setAlCityObjectinPGId(pgObjectInstance.getAlCityObjectInPG().getId());
            plInstanceDTO.setAlCityObjectinPGCode(pgObjectInstance.getAlCityObjectInPG().getCode());
            plInstanceDTO.setAlCityObjectinPGTitle(pgObjectInstance.getAlCityObjectInPG().getTitle());

            plInstanceDTOS.add(plInstanceDTO);
        }
        return plInstanceDTOS;
    }

    public static ALCityObjectDTO getALCityObjectDTO(ALCityObject co){
        ALCityObjectDTO alCityObjectDTO= new ALCityObjectDTO();
            alCityObjectDTO.setId(co.getId());
            alCityObjectDTO.setObjectCategory(co.getObjectCategory().getLabel());
            alCityObjectDTO.setTitle(co.getTitle());
            alCityObjectDTO.setVersion(co.getVersion());
            alCityObjectDTO.setPictureId(co.getPicture().getId());
            alCityObjectDTO.setIconId(co.getIcon().getId());
            alCityObjectDTO.setCreated(co.getCreated());
            alCityObjectDTO.setUpdated(co.getUpdated());
            alCityObjectDTO.setUpdatedBy(co.getUpdatedBy().getUsername());
            alCityObjectDTO.setCreatedBy(co.getCreatedBy().getUsername());
        return alCityObjectDTO;
    }
    public static  Collection<ALCityObjectDTO> getALCityObjectsDTOS(Collection<ALCityObject> puzzleObjectCollection){
        Collection<ALCityObjectDTO> alCityObjectDTOSl = new ArrayList<ALCityObjectDTO>();
        Iterator<ALCityObject> iterator = puzzleObjectCollection.iterator();
        while (iterator.hasNext()) {
            ALCityObjectDTO alCityObjectDTO = new ALCityObjectDTO();
            ALCityObject alCityObject = iterator.next();
            alCityObjectDTO = getALCityObjectDTO(alCityObject);
            alCityObjectDTOSl.add(alCityObjectDTO);
        }

        return alCityObjectDTOSl;
    }
    public static  Collection<PuzzleObjectActionDTO> getPuzzleObjectActionDTOS(Collection<PuzzleObjectAction> puzzleObjectActions) {
        Collection<PuzzleObjectActionDTO> puzzleObjectActionDTOS = new ArrayList<PuzzleObjectActionDTO>();
        Iterator<PuzzleObjectAction> iterator = puzzleObjectActions.iterator();
        while (iterator.hasNext()) {
            PuzzleObjectActionDTO puzzleObjectActionDTO = new PuzzleObjectActionDTO();
            PuzzleObjectAction poa = iterator.next();
            puzzleObjectActionDTO = getPuzzleObjectActionDTO(poa);
            puzzleObjectActionDTOS.add(puzzleObjectActionDTO);
        }

        return puzzleObjectActionDTOS;
    }

    public static PuzzleObjectActionDTO getPuzzleObjectActionDTO(PuzzleObjectAction poa) {
        PuzzleObjectActionDTO poaDTO = new PuzzleObjectActionDTO();

        poaDTO.setId(poa.getId());
        poaDTO.setVersion(poa.getVersion());
        poaDTO.setOwnerObjectid(poa.getOwnerObjectid());
        poaDTO.setUpdated(poa.getUpdated());
        poaDTO.setCreated(poa.getCreated());
        poaDTO.setUpdatedBy(poa.getUpdatedBy().getUsername());
        poaDTO.setCreatedBy(poa.getUpdatedBy().getUsername());

        poaDTO.setObjectAction(poa.getObjectAction().name());
        poaDTO.setObjectActionId( Long.valueOf(poa.getObjectAction().ordinal()));

        poaDTO.setActionRender(poa.getActionRenderer().getHandler());
        poaDTO.setActionRenderId(poa.getActionRenderer().getId());

        poaDTO.setOwnerType(poa.getPoActionOwnerType().name());
        poaDTO.setOwnerTypeId( Long.valueOf(poa.getPoActionOwnerType().ordinal()));

        return poaDTO;
    }
  public static ClientTypeDTO getClientTypeDTO(ClientType ctype){
      ClientTypeDTO clientTypeDTO = new ClientTypeDTO(ctype.getId(), ctype.getLabel(),
              ctype.getValue(),  ctype.getVersion(), ctype.getCreated(),ctype.getUpdated());
        return  clientTypeDTO;
   }
   public static MemberTypeDTO getMemberTypeDTO(MemberType mt){
        MemberTypeDTO memberTypeDTO = new MemberTypeDTO(mt.getId(), mt.getVersion(), mt.getLabel(), mt.getValue(),
                mt.getCreated(), mt.getUpdated(), mt.getCreatedBy().getUsername(),mt.getUpdatedBy().getUsername(),mt.getCreatedBy().getId(),mt.getUpdatedBy().getId());
        return memberTypeDTO;
    }
   public static Collection<MemberTypeDTO> getMemberTypeDTOS(Collection<MemberType> memberTypes){
       Iterator<MemberType> iterator = memberTypes.iterator();
       Collection<MemberTypeDTO> memberTypeDTOCollection = new ArrayList<MemberTypeDTO>();
       while(iterator.hasNext()) {
           MemberTypeDTO memberTypeDTO = getMemberTypeDTO(iterator.next());
           memberTypeDTOCollection.add(memberTypeDTO);
       }

       return memberTypeDTOCollection;
    }
    public static RendererDTO getActionRendererDTO(Renderer actionRenderer){
           RendererDTO rendererDTO = new RendererDTO();
             rendererDTO.setId(actionRenderer.getId());
             rendererDTO.setCreated(actionRenderer.getCreated());
             rendererDTO.setUpdated(actionRenderer.getUpdated());
             rendererDTO.setVersion(actionRenderer.getVersion());
             rendererDTO.setUpdatedBy(actionRenderer.getUpdatedBy().getUsername());
             rendererDTO.setCreatedBy(actionRenderer.getCreatedBy().getUsername());
            ObjectAction objectAction = actionRenderer.getObjectAction();
            rendererDTO.setObjectAction(objectAction.name());
            ClientType clientType = actionRenderer.getClientType();
            rendererDTO.setClientType(clientType.getValue());
            rendererDTO.setHandler(actionRenderer.getHandler());
           return rendererDTO;
   }
    public static Collection<PLRuleDTO> getRulesForPuzzleLevel(PuzzleLevel pl){
        Collection<PLRuleDTO> rules = new ArrayList<PLRuleDTO>();
        Collection<PLRule>  puzzleLevelRules = pl.getPuzzleLevelRuleCollection();
        Iterator<PLRule> iterator = puzzleLevelRules.iterator();
        while(iterator.hasNext()) {
            PLRule puzzleLevelRule = iterator.next();
            PLRuleDTO rule = new PLRuleDTO();
            rule.setTitle(puzzleLevelRule.getTitle());
            rule.setOrdering(puzzleLevelRule.getOrdering());
            rule.setConditions(puzzleLevelRule.getCondition());
            rules.add(rule);
        }
        return rules;
    }
    public static Collection<RuleData> getRulesForPuzzleLevel(PuzzleLevel pl, AttributeService alCityAttributeService){
        Collection<RuleData> rules = new ArrayList<RuleData>();
        Collection<PLRule>  puzzleLevelRules = pl.getPuzzleLevelRuleCollection();
        Iterator<PLRule> iterator = puzzleLevelRules.iterator();
        while(iterator.hasNext()) {
            PLRule puzzleLevelRule = iterator.next();
            RuleData rule = new RuleData();
            rule.setTitle(puzzleLevelRule.getTitle());
            rule.setOrdering(puzzleLevelRule.getOrdering());
            rule.setConditions(puzzleLevelRule.getCondition());
            rule.setEvent(puzzleLevelRule.getPlRuleEvent().getName());
            Collection<RuleActionData> actions = getRuleActionData(alCityAttributeService, puzzleLevelRule);
            rule.setActions(actions);

            rules.add(rule);
        }

        return rules;
    }

    public static Collection<RuleActionData> getRuleActionData(AttributeService alCityAttributeService , PLRule plRule){
        Collection<RuleActionData> actions = new ArrayList<RuleActionData>();
        Collection<PLRulePostAction> plRulePostActions = plRule.getPlRulePostActions();
        Iterator<PLRulePostAction> iterator = plRulePostActions.iterator();
        while(iterator.hasNext()) {
            Collection<RecordrData> parameters = new ArrayList<RecordrData>();

            PLRulePostAction plRulePostAction =iterator.next();
            RuleActionData ruleActionData = new RuleActionData();
            ruleActionData.setOrdering(plRulePostAction.getOrdering());
            ruleActionData.setActionName(plRulePostAction.getActionName());
            ruleActionData.setObjectId(plRulePostAction.getObjectId());
            ruleActionData.setVariable(plRulePostAction.getVariable());
            ruleActionData.setValueExperssion(plRulePostAction.getValueExperssion());
            ruleActionData.setActionType(plRulePostAction.getPlRulePostActionType().toString());
            ruleActionData.setAlertMessage(plRulePostAction.getAlertMessage());
            ruleActionData.setAlertType(plRulePostAction.getAlertType());

            parameters = getAttributeForOwnerById(alCityAttributeService , plRulePostAction.getId(), AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
            ruleActionData.setParameters(parameters);

            actions.add(ruleActionData);
        }


        return actions;
    }

    public static Collection<RecordrData>  getAttributeForOwnerById(AttributeService attributeService , Long ownerId, AttributeOwnerType ownerType){
        Collection<RecordrData> variables = new ArrayList<RecordrData>();
        Collection<Attribute>  attributes =attributeService.findByOwnerIdAndAttributeOwnerType(ownerId,ownerType);
        Iterator<Attribute> iterator = attributes.iterator();
        while(iterator.hasNext()) {
            Attribute attribute = iterator.next();
            Collection<AttributeValue> attributeValues = attribute.getAttributeValues();
            Iterator<AttributeValue> iteratorValues = attributeValues.iterator();
            while(iteratorValues.hasNext()) {
                AttributeValue alCityAttributeValue = iteratorValues.next();
                String value = getDataValue(alCityAttributeValue);
                String type = attribute.getDataType().toString();
                RecordrData variable = new RecordrData(attribute.getName(),value,type);
                variables.add(variable);
            }

        }

        return variables;
    }
    public static String getDataValue(AttributeValue alCityAttributeValue){
        if (alCityAttributeValue.getBooleanValue()!=null )
            return alCityAttributeValue.getBooleanValue().toString();

        if (alCityAttributeValue.getDoubleValue()!=null )
            return alCityAttributeValue.getDoubleValue().toString();

        if (alCityAttributeValue.getIntValue()!=null )
            return alCityAttributeValue.getIntValue().toString();

        if (alCityAttributeValue.getLongValue()!=null )
            return alCityAttributeValue.getLongValue().toString();

        if (alCityAttributeValue.getBinaryContentId()!=null )
            return alCityAttributeValue.getBinaryContentId().toString();

        if (alCityAttributeValue.getStringValue()!=null )
            return alCityAttributeValue.getStringValue();
        if (alCityAttributeValue.getObjectValue()!=null )
            return alCityAttributeValue.getStringValue();

        return "Unknown Value";
    }

}