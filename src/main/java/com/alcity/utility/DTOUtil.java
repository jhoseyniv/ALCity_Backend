package com.alcity.utility;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.Interpreter.PLObjectiveData;
import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.alobject.*;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.base.DataTypeِDTO;
import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.dto.user.ApplicationMemberDTO;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.service.alobject.AttributeService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class DTOUtil {


    public static PLDTO getPuzzleLevelDTO(PuzzleLevel puzzleLevel) {
        PLDTO puzzleLevelDTO = new PLDTO();

        puzzleLevelDTO.setId(puzzleLevel.getId());
        puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
        puzzleLevelDTO.setCode(puzzleLevel.getCode());
        puzzleLevelDTO.setApproveDate(puzzleLevel.getApproveDate());
        puzzleLevelDTO.setTitle(puzzleLevel.getName());
        puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
        puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
        puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
        puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
        puzzleLevelDTO.setUpdated(puzzleLevel.getUpdated());
        puzzleLevelDTO.setCreated(puzzleLevel.getCreated());
        puzzleLevelDTO.setPuzzleLevelDifficulty(puzzleLevel.getPuzzleDifficulty().toString());
        puzzleLevelDTO.setPuzzleLevelPrivacy(puzzleLevel.getPuzzleLevelPrivacy().getLabel());
        puzzleLevelDTO.setPuzzleLevelStatus(puzzleLevel.getPuzzleLevelStatus().toString());

        return puzzleLevelDTO;
    }

    public static PLDTO getPuzzleLevelDTO(Optional<PuzzleLevel> puzzleLevelOptional) {
        PuzzleLevel puzzleLevel = new PuzzleLevel();
        PLDTO puzzleLevelDTO= new PLDTO();

        if(puzzleLevelOptional.isPresent()){
            puzzleLevel = puzzleLevelOptional.get();
            puzzleLevelDTO = DTOUtil.getPuzzleLevelDTO(puzzleLevel);

            Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection = DTOUtil.getPuzzleLevelObjectiveDTOS(puzzleLevel);
            Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection = DTOUtil.getPuzzleLevel_LearningTopicDTOS(puzzleLevel);
            Collection<PLGroundDTO> puzzleLevelGroundDTOCollection = DTOUtil.getPuzzleLevelGroundDTOS(puzzleLevel);
            Collection<PermitedPlayerDTO> permitedPlayerDTOCollection = DTOUtil.getPermitedPlayerDTOS(puzzleLevel);
            Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = DTOUtil.getPuzzleGroupObjectInstance(puzzleLevel);

            puzzleLevelDTO.setPuzzleLevelGroundDTOCollection(puzzleLevelGroundDTOCollection);
            puzzleLevelDTO.setPuzzleLevelObjectiveDTOCollection(puzzleLevelObjectiveDTOCollection);
            puzzleLevelDTO.setPuzzleLevel_learningTopicDTOCollection(puzzleLevel_learningTopicDTOCollection);
            puzzleLevelDTO.setPermitedPlayerDTOCollection(permitedPlayerDTOCollection);
            puzzleLevelDTO.setPuzzleGroupObjectInstanceDTOCollection(puzzleGroupObjectInstanceDTOCollection);
        } else puzzleLevelDTO = null;

        return puzzleLevelDTO;
    }

        public static AttributeDTO getALCityAttributeDTO(Optional<Attribute> alCityAttributeOptional){
        AttributeDTO alCityAttributeDTO = new AttributeDTO();
        if (alCityAttributeOptional.isPresent()) {
            Attribute alCityAttribute = alCityAttributeOptional.get();
            alCityAttributeDTO.setId(alCityAttribute.getId());
            alCityAttributeDTO.setVersion(alCityAttribute.getVersion());
            alCityAttributeDTO.setName(alCityAttribute.getName());
            alCityAttributeDTO.setOwnerId(alCityAttribute.getOwnerId());
            alCityAttributeDTO.setCreated(alCityAttribute.getCreated());
            alCityAttributeDTO.setUpdated(alCityAttribute.getUpdated());

            //DataTypeِDTO dataTypeِDTO =  getDataTypeDTO(alCityAttribute.getDataType());
            alCityAttributeDTO.setDataType(alCityAttribute.getDataType().toString());

            //AttributeOwnerTypeDTO attributeOwnerTypeDTO= getAttributeOwnerTypeDTO(alCityAttribute.getAttributeOwnerType());
          //  alCityAttributeDTO.setAttributeOwnerTypeDTO(attributeOwnerTypeDTO);

        }else alCityAttributeDTO = null;
        return alCityAttributeDTO;

    }

    public static Collection<AttributeDTO> getALCityAttributes(Collection<Attribute> alCityAttributeCollection) {
        Collection<AttributeDTO> alCityAttributeDTOCollection = new ArrayList<AttributeDTO>();
        Iterator<Attribute> itr = alCityAttributeCollection.iterator();
        while (itr.hasNext()) {
            Attribute alCityAttribute = itr.next();
            AttributeDTO alCityAttributeDTO = new AttributeDTO();
            alCityAttributeDTO.setId(alCityAttribute.getId());
            alCityAttributeDTO.setVersion(alCityAttribute.getVersion());
            alCityAttributeDTO.setName(alCityAttribute.getName());
            alCityAttributeDTO.setOwnerId(alCityAttribute.getOwnerId());
            alCityAttributeDTO.setCreated(alCityAttribute.getCreated());
            alCityAttributeDTO.setUpdated(alCityAttribute.getUpdated());

           // AttributeOwnerTypeDTO attributeOwnerTypDTOe = getAttributeOwnerTypeDTO(alCityAttribute.getAttributeOwnerType());
           // alCityAttributeDTO.setAttributeOwnerTypeDTO(attributeOwnerTypDTOe);

           // DataTypeِDTO dataTypeِDTO = getDataTypeDTO(alCityAttribute.getDataType().);
            alCityAttributeDTO.setDataType(alCityAttribute.getDataType().toString());

            alCityAttributeDTOCollection.add(alCityAttributeDTO);
        }
        return alCityAttributeDTOCollection;
    }


//    public static AttributeOwnerTypeDTO getAttributeOwnerType(Optional<AttributeOwnerType> attributeOwnerTypeOptional) {
//        AttributeOwnerTypeDTO attributeOwnerTypeDTO = new AttributeOwnerTypeDTO();
//        if (attributeOwnerTypeOptional.isPresent()) {
//            AttributeOwnerType attributeOwnerType = attributeOwnerTypeOptional.get();
//            attributeOwnerTypeDTO.setId(attributeOwnerType.getId());
//            attributeOwnerTypeDTO.setVersion(attributeOwnerType.getVersion());
//            attributeOwnerTypeDTO.setLabel(attributeOwnerType.getLabel());
//            attributeOwnerTypeDTO.setValue(attributeOwnerType.getValue());
//            attributeOwnerTypeDTO.setCreated(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(attributeOwnerType.getUpdated()));
//        }else attributeOwnerTypeDTO = null;
//        return attributeOwnerTypeDTO;
//    }
//    public static AttributeOwnerTypeDTO getAttributeOwnerTypeDTO(AttributeOwnerType attributeOwnerType) {
//        AttributeOwnerTypeDTO attributeOwnerTypeDTO = new AttributeOwnerTypeDTO();
//        if (attributeOwnerType != null ) {
//            attributeOwnerTypeDTO.setId(attributeOwnerType.getId());
//            attributeOwnerTypeDTO.setVersion(attributeOwnerType.getVersion());
//            attributeOwnerTypeDTO.setLabel(attributeOwnerType.getLabel());
//            attributeOwnerTypeDTO.setValue(attributeOwnerType.getValue());
//            attributeOwnerTypeDTO.setCreated(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(attributeOwnerType.getUpdated()));
//        }else attributeOwnerTypeDTO = null;
//        return attributeOwnerTypeDTO;
//    }
//    public static DataTypeِDTO getDataTypeDTO(DataType dataType) {
//        DataTypeِDTO dataTypeِDTO = new DataTypeِDTO();
//        if (dataType != null ) {
//            dataTypeِDTO.setId(dataType.getId());
//            dataTypeِDTO.setVersion(dataType.getVersion());
//            dataTypeِDTO.setLabel(dataType.getLabel());
//            dataTypeِDTO.setValue(dataType.getValue());
//            dataTypeِDTO.setCreated(dataType.getCreated());
//            dataTypeِDTO.setUpdated(dataType.getUpdated());
//        }else dataTypeِDTO = null;
//        return dataTypeِDTO;
//    }



//    public static Collection<AttributeOwnerTypeDTO> getAttributeOwnerTypes(Collection<AttributeOwnerType> attributeOwnerTypeCollection) {
//        Collection<AttributeOwnerTypeDTO> attributeOwnerTypeDTOCollection = new ArrayList<AttributeOwnerTypeDTO>();
//        Iterator<AttributeOwnerType> itr = attributeOwnerTypeCollection.iterator();
//        while (itr.hasNext()) {
//            AttributeOwnerType attributeOwnerType = itr.next();
//            AttributeOwnerTypeDTO attributeOwnerTypeDTO = new AttributeOwnerTypeDTO();
//            attributeOwnerTypeDTO.setId(attributeOwnerType.getId());
//            attributeOwnerTypeDTO.setVersion(attributeOwnerType.getVersion());
//            attributeOwnerTypeDTO.setLabel(attributeOwnerType.getLabel());
//            attributeOwnerTypeDTO.setValue(attributeOwnerType.getValue());
//            attributeOwnerTypeDTO.setCreated(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(attributeOwnerType.getUpdated()));
//            attributeOwnerTypeDTOCollection.add(attributeOwnerTypeDTO);
//        }
//        return attributeOwnerTypeDTOCollection;
//    }


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
            //.....
            journeyStepDTOCollection.add(journeyStepDTO);
        }
        return journeyStepDTOCollection;
    }

    public static Collection<PLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> puzzleLevelCollection) {
        Collection<PLDTO> puzzleLevelDTOCollection = new ArrayList<PLDTO>();

        Iterator<PuzzleLevel> itrPuzzleLevel = puzzleLevelCollection.iterator();
        while (itrPuzzleLevel.hasNext()) {
            PuzzleLevel puzzleLevel = itrPuzzleLevel.next();
            PLDTO puzzleLevelDTO = new PLDTO();
            puzzleLevelDTO.setId(puzzleLevel.getId());
            puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
            puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
            puzzleLevelDTO.setApproveDate(puzzleLevel.getApproveDate());
            puzzleLevelDTO.setCreated(puzzleLevel.getCreated());
            puzzleLevelDTO.setUpdated(puzzleLevel.getUpdated());
            puzzleLevelDTO.setCode(puzzleLevel.getCode());
            puzzleLevelDTO.setTitle(puzzleLevel.getName());
            puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
            puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
            puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
            puzzleLevelDTOCollection.add(puzzleLevelDTO);
        }
        return puzzleLevelDTOCollection;
    }

    public static Collection<PuzzleSkillLearningContentDTO> getPuzzleSkillLearningContentDTOS(Collection<PuzzleSkillLearningContent> puzzleSkillLearningContentCollection) {
        Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection = new ArrayList<PuzzleSkillLearningContentDTO>();
        Iterator<PuzzleSkillLearningContent> itrPuzzleSkills = puzzleSkillLearningContentCollection.iterator();
        while (itrPuzzleSkills.hasNext()) {
            PuzzleSkillLearningContentDTO puzzleSkillLearningContentDTO = new PuzzleSkillLearningContentDTO();
            PuzzleSkillLearningContent puzzleSkillLearningContent = itrPuzzleSkills.next();

            puzzleSkillLearningContentDTO.setId(puzzleSkillLearningContent.getId());
            puzzleSkillLearningContentDTO.setVersion(puzzleSkillLearningContent.getVersion());
            puzzleSkillLearningContentDTO.setCreated(puzzleSkillLearningContent.getCreated());
            puzzleSkillLearningContentDTO.setUpdated(puzzleSkillLearningContent.getUpdated());

            LearningSkill learningSkill = puzzleSkillLearningContent.getLearningSkill();
            LearningSkillDTO learningSkillDTO = new LearningSkillDTO(learningSkill.getId(), learningSkill.getLabel(), learningSkill.getValue(), learningSkill.getVersion(),
                    learningSkill.getCreated(), learningSkill.getUpdated());
            puzzleSkillLearningContentDTO.setLearningSkillDTO(learningSkillDTO);

            LearningContent learningContent = puzzleSkillLearningContent.getLearningContent();
            BinaryContent binaryContent = learningContent.getBinaryContent();
            BinaryContentDTO binaryContentDTO = getBinaryContentDTO(binaryContent);

            LearningContentDTO learningContentDTO = new LearningContentDTO();
            learningContentDTO.setId(learningContent.getId());
            learningContentDTO.setVersion(learningContent.getVersion());
            learningContentDTO.setCreated(learningContent.getCreated());
            learningContentDTO.setUpdated(learningContent.getUpdated());
            learningContentDTO.setBinaryContentDTO(binaryContentDTO);
            learningContentDTO.setDescBrief(learningContent.getDescBrief());
            learningContentDTO.setDescText(learningContent.getDescText());
            puzzleSkillLearningContentDTO.setLearningContentDTO(learningContentDTO);

            puzzleSkillLearningContentDTOCollection.add(puzzleSkillLearningContentDTO);
        }

        return puzzleSkillLearningContentDTOCollection;
    }

    public static Collection<PuzzleGroup_PuzzleObjectDTO> getPuzzleGroup_PuzzleObjectDTOS(Collection<PuzzleGroup_PuzzleObject> puzzleGroup_puzzleObjectCollection) {
        Collection<PuzzleGroup_PuzzleObjectDTO> puzzleGroup_puzzleObjectDTOCollection = new ArrayList<PuzzleGroup_PuzzleObjectDTO>();
        Iterator<PuzzleGroup_PuzzleObject> itrPuzzleGroup_puzzleObject = puzzleGroup_puzzleObjectCollection.iterator();
        while (itrPuzzleGroup_puzzleObject.hasNext()) {
            PuzzleGroup_PuzzleObjectDTO puzzleGroup_puzzleObjectDTO = new PuzzleGroup_PuzzleObjectDTO();
            PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = itrPuzzleGroup_puzzleObject.next();
            puzzleGroup_puzzleObjectDTO.setId(puzzleGroup_puzzleObject.getId());
            puzzleGroup_puzzleObjectDTO.setCode(puzzleGroup_puzzleObject.getCode());
            puzzleGroup_puzzleObjectDTO.setTitle(puzzleGroup_puzzleObject.getTitle());
            puzzleGroup_puzzleObjectDTO.setVersion(puzzleGroup_puzzleObject.getVersion());
            puzzleGroup_puzzleObjectDTO.setCreated(puzzleGroup_puzzleObject.getCreated());
            puzzleGroup_puzzleObjectDTO.setUpdated(puzzleGroup_puzzleObject.getUpdated());

            PuzzleObject puzzleObject = puzzleGroup_puzzleObject.getPuzzleObject();
            PuzzleObjectDTO puzzleObjectDTO = new PuzzleObjectDTO();
            puzzleObjectDTO.setId(puzzleObject.getId());
            puzzleObjectDTO.setVersion(puzzleObject.getVersion());
            puzzleObjectDTO.setObjectCategory(puzzleObject.getObjectCategory().getLabel());
            puzzleObjectDTO.setTitle(puzzleObjectDTO.getTitle());
            puzzleObjectDTO.setCreated(puzzleObject.getCreated());
            puzzleObjectDTO.setUpdated(puzzleObject.getUpdated());

            BinaryContent pic = puzzleObject.getPicture();
            BinaryContent icon = puzzleObject.getPicture();
            BinaryContentDTO pictureDTO = getBinaryContentDTO(pic);
            BinaryContentDTO iconDTO = getBinaryContentDTO(icon);

            puzzleObjectDTO.setPicture(pictureDTO);
            puzzleObjectDTO.setIcon(iconDTO);

            puzzleGroup_puzzleObjectDTO.setPuzzleObjectDTO(puzzleObjectDTO);
            puzzleGroup_puzzleObjectDTOCollection.add(puzzleGroup_puzzleObjectDTO);
        }
        return puzzleGroup_puzzleObjectDTOCollection;

    }


    public static PGDTO getPuzzleGroupDTO(PuzzleGroup puzzleGroup) {
        PGDTO puzzleGroupDTO = new PGDTO();
        puzzleGroupDTO.setId(puzzleGroup.getId());
        puzzleGroupDTO.setVersion(puzzleGroup.getVersion());
        puzzleGroupDTO.setCreated(puzzleGroup.getCreated());
        puzzleGroupDTO.setUpdated(puzzleGroup.getUpdated());
        puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
        BinaryContent icon = puzzleGroup.getIcon();
        BinaryContent pic = puzzleGroup.getPic();

        BinaryContentDTO binaryContentDTO_icon = getBinaryContentDTO(icon);
        BinaryContentDTO binaryContentDTO_pic = getBinaryContentDTO(pic);
        puzzleGroupDTO.setIcon(binaryContentDTO_icon);
        puzzleGroupDTO.setPic(binaryContentDTO_pic);

        return puzzleGroupDTO;
    }
    public static PuzzleCategoryDTO getPuzzleCategoryDTO(PuzzleCategory pc) {
        PuzzleCategoryDTO pcDTO = new PuzzleCategoryDTO();
        Collection<PuzzleGroup> puzzleGroupCollection = pc.getPuzzleGroupCollection();
        pcDTO.setId(pc.getId());
        pcDTO.setVersion(pc.getVersion());
        pcDTO.setCreated(pc.getCreated());
        pcDTO.setUpdated(pc.getUpdated());
        pcDTO.setLabel(pc.getLabel());
        pcDTO.setValue(pc.getValue());
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();

        Iterator<PuzzleGroup> iterator = puzzleGroupCollection.iterator();
        while(iterator.hasNext()){
            PGDTO puzzleGroupDTO = new PGDTO();
            PuzzleGroup puzzleGroup = iterator.next();
            puzzleGroupDTO = getPuzzleGroupDTO(puzzleGroup);
            puzzleGroupDTOCollection.add(puzzleGroupDTO);
        }
        pcDTO.setPuzzleGroupDTOCollection(puzzleGroupDTOCollection);

        return pcDTO;
    }


    public static Collection<PLObjectiveDTO> getPuzzleLevelObjectiveDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveDTO> plObjectiveDTOCollection = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> plObjectiveCollection = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> itr_objectives = plObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjective puzzleLevelObjective = itr_objectives.next();
            PLObjectiveDTO plObjectiveDTO = new PLObjectiveDTO();
            plObjectiveDTO.setId(puzzleLevelObjective.getId());
            plObjectiveDTO.setVersion(puzzleLevelObjective.getVersion());
            plObjectiveDTO.setTitle(puzzleLevelObjective.getTitle());
            plObjectiveDTO.setCondition(puzzleLevelObjective.getCondition());
            plObjectiveDTO.setRewardAmount(puzzleLevelObjective.getRewardAmount());
            plObjectiveDTO.setSkillAmount(puzzleLevelObjective.getSkillAmount());
            plObjectiveDTO.setUpdated(puzzleLevelObjective.getUpdated());
            plObjectiveDTO.setCreated(puzzleLevelObjective.getCreated());

            plObjectiveDTOCollection.add(plObjectiveDTO);
        }
        return plObjectiveDTOCollection;
    }
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
        Collection<PuzzleLevel_LearningTopic> puzzleLevel_learningTopicCollection = puzzleLevel.getPuzzleLevel_learningTopics();
        Iterator<PuzzleLevel_LearningTopic> itr_learningTopics = puzzleLevel_learningTopicCollection.iterator();
        while(itr_learningTopics.hasNext()) {
            PuzzleLevel_LearningTopic puzzleLevel_learningTopic = itr_learningTopics.next();
            PuzzleLevel_LearningTopicDTO puzzleLevel_learningTopicDTO = new PuzzleLevel_LearningTopicDTO();
            LearningTopicDTO learningTopicDTO = new LearningTopicDTO();
            LearningContentDTO learningContentDTO = new LearningContentDTO();

            puzzleLevel_learningTopicDTO.setId(puzzleLevel_learningTopic.getId());
            puzzleLevel_learningTopicDTO.setVersion(puzzleLevel_learningTopic.getVersion());
            puzzleLevel_learningTopicDTO.setUpdated(puzzleLevel_learningTopic.getUpdated());
            puzzleLevel_learningTopicDTO.setCreated(puzzleLevel_learningTopic.getCreated());

            learningTopicDTO.setId(puzzleLevel_learningTopic.getLearningTopic().getId());
            learningTopicDTO.setTitle(puzzleLevel_learningTopic.getLearningTopic().getTitle());
            learningTopicDTO.setVersion(puzzleLevel_learningTopic.getLearningTopic().getVersion());
            learningTopicDTO.setCreated(puzzleLevel_learningTopic.getLearningTopic().getCreated());
            learningTopicDTO.setUpdated(puzzleLevel_learningTopic.getLearningTopic().getUpdated());
            puzzleLevel_learningTopicDTO.setLearningTopicDTO(learningTopicDTO);

            learningContentDTO.setId(puzzleLevel_learningTopic.getLearningContent().getId());
            learningContentDTO.setVersion(puzzleLevel_learningTopic.getLearningContent().getVersion());
            learningContentDTO.setCreated(puzzleLevel_learningTopic.getLearningContent().getCreated());
            learningContentDTO.setUpdated(puzzleLevel_learningTopic.getLearningContent().getUpdated());

            learningContentDTO.setDescText(puzzleLevel_learningTopic.getLearningContent().getDescText());
            learningContentDTO.setDescBrief(puzzleLevel_learningTopic.getLearningContent().getDescBrief());

            puzzleLevel_learningTopicDTO.setLearningContentDTO(learningContentDTO);


            pl_ltDTOCollection.add(puzzleLevel_learningTopicDTO);
        }

        return pl_ltDTOCollection;
    }
    public static BinaryContentDTO getBinaryContentDTOWithoutContent(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(),
                content.getCreatedBy().getUsername(),content.getUpdatedBy().getUsername(),
                 content.getFileName(), content.getSize(), null);
        return binaryContentDTO;
    }
    public static BinaryContentDTO getBinaryContentDTO(BinaryContent content){
        BinaryContentDTO binaryContentDTO = new BinaryContentDTO(content.getId(), content.getVersion(), content.getCreated(), content.getUpdated(),
                content.getCreatedBy().getUsername(),content.getUpdatedBy().getUsername(),content.getFileName(), content.getSize(), content.getContent());
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
                ls.getVersion(), ls.getCreated(), ls.getUpdated());
        return lsDTO;
    }

        public static CameraSetupDTO getCameraSetupDTO(CameraSetup cameraSetup){
        CameraSetupDTO cameraSetupDTO = new CameraSetupDTO();
        cameraSetupDTO.setId(cameraSetup.getId());
        cameraSetupDTO.setVersion(cameraSetup.getVersion());
        cameraSetupDTO.setCreated(cameraSetup.getCreated());
        cameraSetupDTO.setUpdated(cameraSetup.getUpdated());
        cameraSetupDTO.setxPosition(cameraSetup.getxPosition());
        cameraSetupDTO.setyPosition(cameraSetup.getyPosition());
        cameraSetupDTO.setzPosition(cameraSetup.getzPosition());
        cameraSetupDTO.setxRotation(cameraSetup.getyRotation());
        cameraSetupDTO.setyRotation(cameraSetup.getyRotation());
        cameraSetupDTO.setzRotation(cameraSetup.getzRotation());
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

    public static Collection<PuzzleGroupObjectInstanceDTO>  getPuzzleGroupObjectInstance(PuzzleLevel puzzleLevel){
        Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = new ArrayList<PuzzleGroupObjectInstanceDTO>();
        Collection<PGObjectInstance> puzzleGroupObjectInstanceCollection = puzzleLevel.getPuzzleGroupObjectInstanceCollection();
        Iterator<PGObjectInstance> itr = puzzleGroupObjectInstanceCollection.iterator();

        while(itr.hasNext()){
            PGObjectInstance pgObjectInstance = itr.next();
            PuzzleGroupObjectInstanceDTO pgObjectInstanceDTO = new PuzzleGroupObjectInstanceDTO();
            pgObjectInstanceDTO.setId(pgObjectInstance.getId());
            pgObjectInstanceDTO.setVersion(pgObjectInstance.getVersion());
            pgObjectInstanceDTO.setCol(pgObjectInstance.getCol());
            pgObjectInstanceDTO.setRow(pgObjectInstance.getRow());
            pgObjectInstanceDTO.setzOrder(pgObjectInstance.getzOrder());
            pgObjectInstanceDTO.setCreated(pgObjectInstance.getCreated());
            pgObjectInstanceDTO.setUpdated(pgObjectInstance.getUpdated());

            PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = pgObjectInstance.getPuzzleGroup_PuzzleObject();
            PuzzleGroup_PuzzleObjectDTO  puzzleGroup_puzzleObjectDTO= new PuzzleGroup_PuzzleObjectDTO();
            puzzleGroup_puzzleObjectDTO.setId(puzzleGroup_puzzleObject.getId());
            puzzleGroup_puzzleObjectDTO.setVersion(puzzleGroup_puzzleObject.getVersion());
            puzzleGroup_puzzleObjectDTO.setCode(puzzleGroup_puzzleObject.getCode());
            puzzleGroup_puzzleObjectDTO.setTitle(puzzleGroup_puzzleObject.getTitle());
            puzzleGroup_puzzleObjectDTO.setCreated(puzzleGroup_puzzleObject.getCreated());
            puzzleGroup_puzzleObjectDTO.setUpdated(puzzleGroup_puzzleObject.getUpdated());

            pgObjectInstanceDTO.setPuzzleGroup_puzzleObjectDTO(puzzleGroup_puzzleObjectDTO);

            puzzleGroupObjectInstanceDTOCollection.add(pgObjectInstanceDTO);
        }
        return puzzleGroupObjectInstanceDTOCollection;
    }

    public static PuzzleObjectDTO getPuzzleObjectById(Optional<PuzzleObject> puzzleObject){
        PuzzleObjectDTO puzzleObjectDTO= new PuzzleObjectDTO();
        PuzzleObject po = new PuzzleObject();
        if(puzzleObject.isPresent()) {
            po= puzzleObject.get();
            puzzleObjectDTO.setId(po.getId());
            puzzleObjectDTO.setObjectCategory(po.getObjectCategory().getLabel());
            puzzleObjectDTO.setTitle(po.getTitle());
            puzzleObjectDTO.setVersion(po.getVersion());
            BinaryContent pic = po.getPicture();
            BinaryContentDTO pictureDTO = getBinaryContentDTO(pic);
            BinaryContent icon = po.getIcon();
            BinaryContentDTO iconDTO = getBinaryContentDTO(icon);
            puzzleObjectDTO.setPicture(pictureDTO);
            puzzleObjectDTO.setIcon(iconDTO);

        } else puzzleObjectDTO=null;

        return puzzleObjectDTO;
    }
    public static  Collection<PuzzleObjectDTO> getPuzzleObjects(Collection<PuzzleObject> puzzleObjectCollection){
        Collection<PuzzleObjectDTO> puzzleObjectDTOCollection = new ArrayList<PuzzleObjectDTO>();
        Iterator<PuzzleObject> iterator = puzzleObjectCollection.iterator();
        while (iterator.hasNext()) {
            PuzzleObjectDTO puzzleObjectDTO = new PuzzleObjectDTO();
            PuzzleObject po = iterator.next();
            puzzleObjectDTO.setId(po.getId());
            puzzleObjectDTO.setObjectCategory(po.getObjectCategory().getLabel());
            puzzleObjectDTO.setTitle(po.getTitle());
            puzzleObjectDTO.setVersion(po.getVersion());
            BinaryContent pic = po.getPicture();
            BinaryContentDTO pictureDTO = getBinaryContentDTO(pic);

            BinaryContent icon = po.getIcon();
            BinaryContentDTO iconDTO =getBinaryContentDTO(icon);
            puzzleObjectDTO.setPicture(pictureDTO);
            puzzleObjectDTO.setIcon(iconDTO);
            puzzleObjectDTOCollection.add(puzzleObjectDTO);
        }

        return puzzleObjectDTOCollection;
    }
    public static  Collection<PuzzleObject_ObjectActionDTO> getPuzzleObjectActions(Collection<PuzzleObject_ObjectAction> puzzleObject_objectActionCollection) {
        Collection<PuzzleObject_ObjectActionDTO> puzzleObject_objectActionDTOCollection = new ArrayList<PuzzleObject_ObjectActionDTO>();
        Iterator<PuzzleObject_ObjectAction> iterator = puzzleObject_objectActionCollection.iterator();
        while (iterator.hasNext()) {
            PuzzleObject_ObjectActionDTO puzzleObject_objectActionDTO = new PuzzleObject_ObjectActionDTO();
            PuzzleObject_ObjectAction poa = iterator.next();

            puzzleObject_objectActionDTO.setId(poa.getId());
            puzzleObject_objectActionDTO.setVersion(poa.getVersion());
            puzzleObject_objectActionDTO.setOwnerObjectid(poa.getOwnerObjectid());
            puzzleObject_objectActionDTO.setCreated(poa.getCreated());
            puzzleObject_objectActionDTO.setUpdated(poa.getUpdated());
            ObjectAction objectAction = poa.getObjectAction();

            puzzleObject_objectActionDTO.setObjectAction(objectAction);

            ActionRenderer actionRenderer = poa.getActionRenderer();

            ActionRendererDTO actionRendererDTO=DTOUtil.getActionRenderer(actionRenderer);
            puzzleObject_objectActionDTO.setActionRendererDTO(actionRendererDTO);
            puzzleObject_objectActionDTOCollection.add(puzzleObject_objectActionDTO);
        }

        return puzzleObject_objectActionDTOCollection;
    }

    public static  PuzzleObject_ObjectActionDTO getPuzzleObjectAction(Optional<PuzzleObject_ObjectAction> puzzleObject_objectActionOptional) {
        PuzzleObject_ObjectActionDTO puzzleObject_objectActionDTO = new PuzzleObject_ObjectActionDTO();
          if(puzzleObject_objectActionOptional.isPresent()) {
            PuzzleObject_ObjectAction poa= puzzleObject_objectActionOptional.get();
            puzzleObject_objectActionDTO.setId(poa.getId());
            puzzleObject_objectActionDTO.setVersion(poa.getVersion());
            puzzleObject_objectActionDTO.setOwnerObjectid(poa.getOwnerObjectid());
            puzzleObject_objectActionDTO.setCreated(poa.getCreated());
            puzzleObject_objectActionDTO.setUpdated(poa.getUpdated());
            ObjectAction objectAction = poa.getObjectAction();

            puzzleObject_objectActionDTO.setObjectAction(objectAction);

//            POActionOwnerType poaot = new POActionOwnerType();
//            poaot = poa.getPuzzleObjectActionOwnerType();
//            PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO = new PuzzleObjectActionOwnerTypeDTO(poaot.getId(),poaot.getLabel(),poaot.getValue(),poaot.getVersion(),
//                    poaot.getCreated()),poaot.getUpdated()));

//            puzzleObject_objectActionDTO.setPuzzleObjectActionOwnerTypeDTO(puzzleObjectActionOwnerTypeDTO);

            ActionRenderer actionRenderer = poa.getActionRenderer();

            ActionRendererDTO actionRendererDTO=DTOUtil.getActionRenderer(actionRenderer);
            puzzleObject_objectActionDTO.setActionRendererDTO(actionRendererDTO);
        } else puzzleObject_objectActionDTO=null;

        return puzzleObject_objectActionDTO;
    }

    public static ActionRendererDTO getActionRenderer(ActionRenderer actionRenderer){
           ActionRendererDTO actionRendererDTO = new ActionRendererDTO();
           actionRendererDTO.setId(actionRenderer.getId());
           actionRendererDTO.setCreated(actionRenderer.getCreated());
           actionRendererDTO.setUpdated(actionRenderer.getUpdated());
           actionRendererDTO.setVersion(actionRenderer.getVersion());
           ObjectAction objectAction = actionRenderer.getObjectAction();


           actionRendererDTO.setObjectAction(objectAction);

           ClientType clientType = actionRenderer.getClientType();
           ClientTypeDTO clientTypeDTO = new ClientTypeDTO(clientType.getId(),clientType.getLabel(),clientType.getValue(), clientType.getVersion(),
                   clientType.getCreated(),
                   clientType.getUpdated());
           actionRendererDTO.setClientTypeDTO(clientTypeDTO);
           actionRendererDTO.setHandler(actionRenderer.getHandler());

       return actionRendererDTO;
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
            ruleActionData.setActionType(plRulePostAction.getPlRulePostActionType().toString());
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
            Collection<AttributeValue> attributeValues = attribute.getAttributeValueSet();
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

        if (alCityAttributeValue.getBinaryContent()!=null )
            return alCityAttributeValue.getBinaryContent().toString();

        if (alCityAttributeValue.getStringValue()!=null )
            return alCityAttributeValue.getStringValue();

        return "Unknown Value";
    }

}