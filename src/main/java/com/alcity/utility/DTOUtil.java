package com.alcity.utility;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.Interpreter.PLObjectiveData;
import com.alcity.dto.Interpreter.object.RecordData;
import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.alenum.EnumDTO;
import com.alcity.dto.alobject.*;
import com.alcity.dto.appmember.*;
import com.alcity.dto.base.*;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alobject.*;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.journey.RoadMap;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.service.alobject.AttributeService;
import com.alcity.service.alobject.AttributeValueService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class DTOUtil {



    public static PLDTO getPuzzleLevelDTO(PuzzleLevel entity) {
        PLDTO dto = new PLDTO();

        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setCode(entity.getCode());
        dto.setApproveDate(entity.getApproveDate());
        dto.setTitle(entity.getTitle());
        dto.setToAge(entity.getToAge());
        dto.setFromAge(entity.getFromAge());
        dto.setOrdering(entity.getOrdering());
        dto.setMaxScore(entity.getMaxScore());
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
        AttributeValueDTO valueDTO= new AttributeValueDTO(value.getId(),value.getBooleanValue(), value.getLongValue(), value.getDoubleValue(),
                        value.getIntValue(), value.getBinaryContentId() ,value.getStringValue(),value.getObjectValue(),value.getAttributeId().getId() );
        return valueDTO;
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
        Collection<AttributeValueDTO> output = new ArrayList<AttributeValueDTO>();
        Iterator<AttributeValue> itr = input.iterator();
        while (itr.hasNext()) {
            AttributeValueDTO valueDTO = getAttributeValueDTO(itr.next());
            output.add(valueDTO);
        }
        return  output;
    }
        public static AttributeDTO getAttributeDTO(Attribute att){
            Collection<AttributeValueDTO> valueDTOS = getAttributesValueDTOS(att.getAttributeValues()) ;
            AttributeDTO attributeDTO = new AttributeDTO(att.getId(), att.getName(), att.getOwnerId(), att.getAttributeOwnerType().name(), att.getDataType().name(),valueDTOS ,
                    att.getVersion(), att.getCreated(), att.getCreated(), att.getCreatedBy().getUsername(), att.getUpdatedBy().getUsername());
           return attributeDTO;
        }

    public static Collection<AttributeDTO> getAttributesDTOS(Collection<Attribute> input) {
        Collection<AttributeDTO> output = new ArrayList<AttributeDTO>();
        Iterator<Attribute> itr = input.iterator();
        while (itr.hasNext()) {
           AttributeDTO attributeDTO = getAttributeDTO(itr.next());
            output.add(attributeDTO);
        }
        return output;
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
    public static Collection<PLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> input) {
        Collection<PLDTO> output = new ArrayList<PLDTO>();

        Iterator<PuzzleLevel> itr = input.iterator();
        while (itr.hasNext()) {
            PuzzleLevel pl = itr.next();
            PLDTO dto = new PLDTO();
            dto.setId(pl.getId());
            dto.setVersion(pl.getVersion());
            dto.setOrdering(pl.getOrdering());
            dto.setApproveDate(pl.getApproveDate());
            dto.setCreated(pl.getCreated());
            dto.setUpdated(pl.getUpdated());
            dto.setCode(pl.getCode());
            dto.setTitle(pl.getTitle());
            dto.setFromAge(pl.getFromAge());
            dto.setToAge(pl.getToAge());
            dto.setMaxScore(pl.getMaxScore());
            dto.setPuzzleGroupId(pl.getPuzzleGroup().getId());
            dto.setPuzzleGroupTitle(pl.getPuzzleGroup().getTitle());
            output.add(dto);
        }
        return output;
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

    public static Collection<LearningSkillLContentDTO> getLearningSkillContentDTOS(Collection<LearningSkillContent> input) {
        Collection<LearningSkillLContentDTO> output = new ArrayList<LearningSkillLContentDTO>();
        Iterator<LearningSkillContent> itr = input.iterator();
        while (itr.hasNext()) {
            LearningSkillLContentDTO dto = new LearningSkillLContentDTO();
            LearningSkillContent lsc = itr.next();

            dto.setId(lsc.getId());
            dto.setVersion(lsc.getVersion());
            dto.setCreated(lsc.getCreated());
            dto.setCreatedById(lsc.getCreatedBy().getId());
            dto.setUpdated(lsc.getUpdated());
            dto.setUpdatedById(lsc.getUpdatedBy().getId());
            dto.setCreatedBy(lsc.getCreatedBy().getUsername());
            dto.setUpdatedBy(lsc.getUpdatedBy().getUsername());
            dto.setLearningSkillId(lsc.getLearningSkill().getId());
            dto.setLearningSkillTitle(lsc.getLearningSkill().getValue());
            dto.setLearningContentId(lsc.getLearningContent().getId());
            dto.setPuzzleGroupId(lsc.getPuzzleGroup().getId());
            dto.setPuzzleGroupTitle(lsc.getPuzzleGroup().getTitle());
            output.add(dto);
        }
        return output;
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
    public static Collection<ALCityObjectInPGDTO> getALCityObjectInPGDTOS(Collection<ALCityObjectInPG> input) {
        Collection<ALCityObjectInPGDTO> dtos = new ArrayList<ALCityObjectInPGDTO>();
        Iterator<ALCityObjectInPG> itr = input.iterator();
        while (itr.hasNext()) {
            ALCityObjectInPG alCityObjectInPG = itr.next();
            ALCityObjectInPGDTO dto = getALCityObjectInPGDTO(alCityObjectInPG);
            dtos.add(dto);
        }
        return dtos;

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
        plObjectiveDTO.setSkillLable(plObjective.getLearningSkill().getLabel());
        plObjectiveDTO.setSkillValue(plObjective.getLearningSkill().getValue());
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
    public static Collection<PLObjectiveData> getPuzzleLevelObjectiveData(PuzzleLevel input) {
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
                content.getFileName(), content.getSize(), null, content.getThumbnail(), content.getContentType().name(),
                content.getTag1(), content.getTag2(), content.getTag3());
        return binaryContentDTO;
    }
    public static BinaryContentDTO getBinaryContentDTO(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(), content.getCreatedBy().getUsername(), content.getUpdatedBy().getUsername(),
                content.getFileName(), content.getSize(), content.getContent(), content.getThumbnail(), content.getContentType().name(),
                content.getTag1(), content.getTag2(), content.getTag3());
        return binaryContentDTO;
    }

    public static AppMemberDTO getAppMemberDTO(AppMember member){
        String userName="admin";
        if(member.getCreatedBy() == null || member.getCreatedBy() == null)
            userName="admin";

        AppMemberDTO memberDTO = new AppMemberDTO(member.getId(),member.getAge(),
                member.getUsername(),member.getPassword(),member.getIcon().getThumbnail(),member.getIcon().getId(), member.getNickname(),
                member.getMobile(), member.getEmail(),member.getGender().name(),member.getMemberType().getValue(),
                member.getVersion(), member.getCreated(), member.getUpdated(), userName, userName);

        return memberDTO;
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
    public static Collection<AppMemberDTO> getAppMemberDTOS(Collection<AppMember> appMemberCollection) {
        Collection<AppMemberDTO> dtos = new ArrayList<AppMemberDTO>();
        Iterator<AppMember> iterator = appMemberCollection.iterator();
        while (iterator.hasNext()) {
            AppMemberDTO dto = getAppMemberDTO(iterator.next());
            dtos.add(dto);
        }
        return dtos;
    }
    public static Collection<JourneyDTO> getJourneyDTOS(Collection<Journey> journeyCollection) {
        Collection<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeyCollection.iterator();
        while (itr.hasNext()) {
            JourneyDTO dto = getJourneyDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }

    public static RoadMapDTO getRoadMapDTO(RoadMap entity) {
        RoadMapDTO dto = new RoadMapDTO();
        dto.setId(entity.getId());
        dto.setXpos(entity.getXpos());
        dto.setYpos(entity.getYpos());
        dto.setGraphicId(entity.getGraphic().getId());
        return dto;
    }
    /*
    public static AppMemberJourneyDetailDTO getAppMemberJourneyDetailDTO(Collection<PlayHistoryDTO> playHistoryDTOS, Collection<JourneyStepDTO> journeyStepDTOS){
        Collection<AppMemberJourneyStepDTO> appMemberJourneyStepDTOS = new ArrayList<>();
        AppMemberJourneyDetailDTO dto = new AppMemberJourneyDetailDTO();
        Collection<RoadMapDTO> roadMapDTOS = new ArrayList<>();
        Iterator<JourneyStepDTO> itr = journeyStepDTOS.iterator();
        while(itr.hasNext()) {
            JourneyStepDTO journeyStepDTO = itr.next();
            Optional<PlayHistoryDTO> playHistoryDTOOptional = playHistoryDTOS.stream().filter(PlayHistoryDTO -> PlayHistoryDTO.getPgId() == journeyStepDTO.getPuzzleGroupId()).findFirst();
            if (playHistoryDTOOptional.isPresent()){
                PlayHistoryDTO playHistoryDTO = playHistoryDTOOptional.get();
                AppMemberJourneyStepDTO appMemberJourneyStepDTO = new AppMemberJourneyStepDTO(journeyStepDTO.getStepId(), playHistoryDTO.getPlId(),journeyStepDTO.getStepXpos(),journeyStepDTO.getStepYpos(),playHistoryDTO.getStars(),true);
                appMemberJourneyStepDTOS.add(appMemberJourneyStepDTO);
            }else {
                AppMemberJourneyStepDTO appMemberJourneyStepDTO = new AppMemberJourneyStepDTO(journeyStepDTO.getStepId(), -1L,journeyStepDTO.getStepXpos(),journeyStepDTO.getStepYpos(),0,false);
                appMemberJourneyStepDTOS.add(appMemberJourneyStepDTO);

            }
        }
        dto.setRoadMapDTOS(roadMapDTOS);
        dto.setJourneyStepsDTO(appMemberJourneyStepDTOS);
        return dto;
    }

     */
    public static Collection<RoadMapDTO> getJourneyRoadMapsDTOS(Collection<RoadMap> roadMaps) {
        Collection<RoadMapDTO> dtos = new ArrayList<RoadMapDTO>();
        Iterator<RoadMap> itr = roadMaps.iterator();
        while (itr.hasNext()) {
            RoadMapDTO dto = getRoadMapDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
    public static AppMemberJourneyDTO getAppmemberJourneyDTO(AppMember member ,Journey entity) {
        AppMemberJourneyDTO dto = new AppMemberJourneyDTO();
        dto.setJourneyId(entity.getId());
        dto.setOpen(false);
        dto.setCurrentStar(-1);
        dto.setMinStar(entity.getMinStar());
        dto.setMaxStar(entity.getMaxStar());
        dto.setTitle(entity.getTitle());
        dto.setIconId(entity.getGraphic().getId());
        return dto;
    }

    public static Collection<JourneyDTO> getJourneyDTOSByUser(Collection<Journey> journeyCollection) {
        Collection<JourneyDTO> dtos = new ArrayList<JourneyDTO>();
        Iterator<Journey> itr = journeyCollection.iterator();
        while (itr.hasNext()) {
            JourneyDTO dto = getJourneyDTO(itr.next());
            dtos.add(dto);
        }
        return dtos;
    }
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
        dto.setPlayTime(entity.getPlayTime());
        dto.setPlayScore(entity.getPlayScore());
        dto.setStars(entity.getStars());
        dto.setPlayerId(player.getId());
        dto.setPlayerUsername(player.getUsername());

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
        dto.setThumbnail(entity.getWalletItem().getIcon().getThumbnail());
        dto.setWalletItemType(entity.getWalletItem().getWalletItemType().getValue());
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
            dto.setMinScore(entity.getMinStar());
            dto.setMaxScore(entity.getMaxStar());
            dto.setCreatedBy(entity.getCreatedBy().getUsername());
            dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
            dto.setCreatedById(entity.getCreatedBy().getId());
            dto.setUpdatedById(entity.getUpdatedBy().getId());
            dto.setIconId(entity.getGraphic().getId());
            dto.setThumbnail(entity.getGraphic().getThumbnail());
         return dto;
    }

    public static LearningSkillDTO getLearningSkillDTO(LearningSkill ls) {
        LearningSkillDTO lsDTO = new LearningSkillDTO(ls.getId(), ls.getLabel(), ls.getValue(),
                ls.getVersion(), ls.getCreated(), ls.getUpdated(),ls.getCreatedBy().getUsername(),ls.getUpdatedBy().getUsername());
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
    // LearningContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String descText, String descBrief, String fileName, Integer size, String contentType,Long binaryContentId) {
    }


    public static Collection<LearningContentDTO> getLearningContentDTOS(Collection<LearningContent> learningContents) {
        Collection<LearningContentDTO> learningContentDTOCollection = new ArrayList<LearningContentDTO>();
        Iterator<LearningContent> iterator = learningContents.iterator();
        while (iterator.hasNext()) {
            LearningContentDTO learningContentDTO = getLearningContentDTO(iterator.next());
            learningContentDTOCollection.add(learningContentDTO);
        }
        return learningContentDTOCollection;
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
                wi.getWalletItemType().getValue(),wi.getIcon().getThumbnail(), wi.getVersion(),wi.getCreated(),
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
 /*   public static Collection<PLDTO> getPublicPuzzleLevelsDTOS(Collection<PuzzleLevel> puzzleLevels, Integer age) {
        Collection<PLDTO> dtos = new ArrayList<PLDTO>();
        Collection<PuzzleLevel> filterdByAge = puzzleLevels.stream().filter(PuzzleLevel -> PuzzleLevel.getFromAge() <=age  && age <= PuzzleLevel.getToAge()).collect(Collectors.toList());
        Iterator<PuzzleLevel> itr = filterdByAge.iterator();

        while(itr.hasNext()){
            PuzzleLevel entity = itr.next();
            PLPrivacy privacy = entity.getPuzzleLevelPrivacy();
            if(privacy.getValue().equalsIgnoreCase("public")) {
                PLDTO dto = new PLDTO();
                dto.setId(entity.getId());
                dto.setCode(entity.getCode());
                dto.setApproveDate(entity.getApproveDate());
                dto.setTitle(entity.getTitle());
                dto.setToAge(entity.getToAge());
                dto.setFromAge(entity.getFromAge());
                dto.setOrdering(entity.getOrdering());
                dto.setMaxScore(entity.getMaxScore());
                dto.setFirstStarScore(entity.getFirstStarScore());
                dto.setSecondStarScore(entity.getSecondStarScore());
                dto.setThirdStartScore(entity.getThirdStartScore());
                dto.setVersion(entity.getVersion());
                dto.setCreated(entity.getCreated());
                dto.setUpdated(entity.getUpdated());
                dto.setCreatedBy(entity.getCreatedBy().getUsername());
                dto.setUpdatedBy(entity.getUpdatedBy().getUsername());
                dto.setUpdatedById(entity.getUpdatedBy().getId());
                dto.setIconId(entity.getIcon().getId());
                dto.setPicId(entity.getPicture().getId());
                dtos.add(dto);
            }
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

    public static Collection<PermitedPlayerDTO> getPermitedPlayerDTOS(PuzzleLevel puzzleLevel) {
        Collection<PermitedPlayerDTO> permitedPlayerDTOCollection = new ArrayList<PermitedPlayerDTO>();
        Collection<PermitedPlayer> permitedPlayerCollection = puzzleLevel.getPermitedPlayerCollection();
        Iterator<PermitedPlayer> itr_permitedPlayers = permitedPlayerCollection.iterator();

        while(itr_permitedPlayers.hasNext()){
            PermitedPlayer permitedPlayer = itr_permitedPlayers.next();
            PermitedPlayerDTO permitedPlayerDTO = new PermitedPlayerDTO();
            permitedPlayerDTO.setId(permitedPlayer.getId());
            permitedPlayerDTO.setPlId(permitedPlayer.getId());
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
            alCityObjectDTO.setPictureId(co.getPic().getId());
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
    public static  Collection<PuzzleObjectActionDTO> getPuzzleObjectActionDTOS(Collection<PuzzleObjectAction> input) {
        Collection<PuzzleObjectActionDTO> output = new ArrayList<PuzzleObjectActionDTO>();
        Iterator<PuzzleObjectAction> itr = input.iterator();
        while (itr.hasNext()) {
            PuzzleObjectActionDTO dto = new PuzzleObjectActionDTO();
            PuzzleObjectAction poa = itr.next();
            dto = getPuzzleObjectActionDTO(poa);
            output.add(dto);
        }

        return output;
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
    public static ObjectCategoryDTO getObjectCategoryDTO(ObjectCategory oc){
        ObjectCategoryDTO objectCategoryDTO = new ObjectCategoryDTO(oc.getId(),oc.getLabel(),oc.getValue(), oc.getVersion(), oc.getCreated(),
                oc.getUpdated(),null);
        return objectCategoryDTO;
    }
    public static Collection<ObjectCategoryDTO> getObjectCategories(Collection<ObjectCategory> input){
        Collection<ObjectCategoryDTO> output = new ArrayList<ObjectCategoryDTO>();
        Iterator<ObjectCategory> itr = input.iterator();
        while(itr.hasNext()){
            ObjectCategoryDTO dto =getObjectCategoryDTO(itr.next());
            output.add(dto);
        }
        return output;
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
    public static PLRuleDTO getPLRuleDTO(PLRule plRule) {
        PLRuleDTO dto = new PLRuleDTO();
        dto.setId(plRule.getId());
        dto.setTitle(plRule.getTitle());
        dto.setOrdering(plRule.getOrdering());
        dto.setCondition(plRule.getCondition());
        dto.setPuzzleLevelId(plRule.getPuzzleLevel().getId());
        dto.setPuzzleLeveTitle(plRule.getPuzzleLevel().getTitle());
        dto.setPLRuleEventId(plRule.getPlRuleEvent().getId());
        dto.setPLRuleEventName(plRule.getPlRuleEvent().getName());
        dto.setPlRuleEventTypeId(plRule.getPlRuleEvent().getPlRuleEventType().ordinal());
        dto.setPlRuleEventTypeTitle(plRule.getPlRuleEvent().getPlRuleEventType().name());
        return dto;
    }
    public static Collection<PLRuleDTO> getRulesForPuzzleLevel(PuzzleLevel pl){
        Collection<PLRuleDTO> dtos = new ArrayList<PLRuleDTO>();
        Collection<PLRule>  puzzleLevelRules = pl.getPuzzleLevelRuleCollection();
        Iterator<PLRule> iterator = puzzleLevelRules.iterator();
        while(iterator.hasNext()) {
            PLRule plRule = iterator.next();
            PLRuleDTO dto = new PLRuleDTO();
            dto = getPLRuleDTO(plRule);
            dtos.add(dto);
        }
        return dtos;
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
            Collection<RecordData> parameters = new ArrayList<RecordData>();

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

    public static Collection<RecordData>  getAttributeForOwnerById(AttributeService attributeService , Long ownerId, AttributeOwnerType ownerType){
        Collection<RecordData> variables = new ArrayList<RecordData>();
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
                RecordData variable = new RecordData(attribute.getId(), attribute.getName(),alCityAttributeValue.getId(),value,type);
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