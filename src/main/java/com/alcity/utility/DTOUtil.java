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
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
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
        puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
        puzzleLevelDTO.setTitle(puzzleLevel.getName());
        puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
        puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
        puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
        puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
        puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
        puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));
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
            alCityAttributeDTO.setCreated(DateUtils.getDatatimeFromLong(alCityAttribute.getCreated()));
            alCityAttributeDTO.setUpdated(DateUtils.getDatatimeFromLong(alCityAttribute.getUpdated()));

            DataTypeِDTO dataTypeِDTO =  getDataTypeDTO(alCityAttribute.getDataType());
            alCityAttributeDTO.setDataTypeِDTO(dataTypeِDTO);

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
            alCityAttributeDTO.setCreated(DateUtils.getDatatimeFromLong(alCityAttribute.getCreated()));
            alCityAttributeDTO.setUpdated(DateUtils.getDatatimeFromLong(alCityAttribute.getUpdated()));

           // AttributeOwnerTypeDTO attributeOwnerTypDTOe = getAttributeOwnerTypeDTO(alCityAttribute.getAttributeOwnerType());
           // alCityAttributeDTO.setAttributeOwnerTypeDTO(attributeOwnerTypDTOe);

            DataTypeِDTO dataTypeِDTO = getDataTypeDTO(alCityAttribute.getDataType());
            alCityAttributeDTO.setDataTypeِDTO(dataTypeِDTO);

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
//            attributeOwnerTypeDTO.setCreated(DateUtils.getDatatimeFromLong(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(DateUtils.getDatatimeFromLong(attributeOwnerType.getUpdated()));
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
//            attributeOwnerTypeDTO.setCreated(DateUtils.getDatatimeFromLong(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(DateUtils.getDatatimeFromLong(attributeOwnerType.getUpdated()));
//        }else attributeOwnerTypeDTO = null;
//        return attributeOwnerTypeDTO;
//    }
    public static DataTypeِDTO getDataTypeDTO(DataType dataType) {
        DataTypeِDTO dataTypeِDTO = new DataTypeِDTO();
        if (dataType != null ) {
            dataTypeِDTO.setId(dataType.getId());
            dataTypeِDTO.setVersion(dataType.getVersion());
            dataTypeِDTO.setLabel(dataType.getLabel());
            dataTypeِDTO.setValue(dataType.getValue());
            dataTypeِDTO.setCreated(DateUtils.getDatatimeFromLong(dataType.getCreated()));
            dataTypeِDTO.setUpdated(DateUtils.getDatatimeFromLong(dataType.getUpdated()));
        }else dataTypeِDTO = null;
        return dataTypeِDTO;
    }



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
//            attributeOwnerTypeDTO.setCreated(DateUtils.getDatatimeFromLong(attributeOwnerType.getCreated()));
//            attributeOwnerTypeDTO.setUpdated(DateUtils.getDatatimeFromLong(attributeOwnerType.getUpdated()));
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
            journeyStepDTO.setCreated(DateUtils.getDatatimeFromLong(journeyStep.getCreated()));
            journeyStepDTO.setUpdated(DateUtils.getDatatimeFromLong(journeyStep.getUpdated()));
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
            puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
            puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));
            puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
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
            puzzleSkillLearningContentDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleSkillLearningContent.getCreated()));
            puzzleSkillLearningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleSkillLearningContent.getUpdated()));

            LearningSkill learningSkill = puzzleSkillLearningContent.getLearningSkill();
            LearningSkillDTO learningSkillDTO = new LearningSkillDTO(learningSkill.getId(), learningSkill.getLabel(), learningSkill.getValue(), learningSkill.getVersion(),
                    DateUtils.getDatatimeFromLong(learningSkill.getCreated()), DateUtils.getDatatimeFromLong(learningSkill.getUpdated()));
            puzzleSkillLearningContentDTO.setLearningSkillDTO(learningSkillDTO);

            LearningContent learningContent = puzzleSkillLearningContent.getLearningContent();
            BinaryContent binaryContent = learningContent.getBinaryContent();
            BinaryContentDTO binaryContentDTO = new BinaryContentDTO(binaryContent.getFileName(), binaryContent.getSize(), binaryContent.getContent(), binaryContent.getId(), binaryContent.getVersion(),
                    DateUtils.getDatatimeFromLong(binaryContent.getCreated()), DateUtils.getDatatimeFromLong(binaryContent.getUpdated()));

            LearningContentDTO learningContentDTO = new LearningContentDTO();
            learningContentDTO.setId(learningContent.getId());
            learningContentDTO.setVersion(learningContent.getVersion());
            learningContentDTO.setCreated(DateUtils.getDatatimeFromLong(learningContent.getCreated()));
            learningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(learningContent.getUpdated()));
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
            puzzleGroup_puzzleObjectDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getCreated()));
            puzzleGroup_puzzleObjectDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getUpdated()));

            PuzzleObject puzzleObject = puzzleGroup_puzzleObject.getPuzzleObject();
            PuzzleObjectDTO puzzleObjectDTO = new PuzzleObjectDTO();
            puzzleObjectDTO.setId(puzzleObject.getId());
            puzzleObjectDTO.setVersion(puzzleObject.getVersion());
            puzzleObjectDTO.setObjectCategory(puzzleObject.getObjectCategory().getLabel());
            puzzleObjectDTO.setTitle(puzzleObjectDTO.getTitle());
            puzzleObjectDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleObject.getCreated()));
            puzzleObjectDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleObject.getUpdated()));

            BinaryContent picture = puzzleObject.getPicture();
            BinaryContent icon = puzzleObject.getPicture();
            BinaryContentDTO pictureDTO = new BinaryContentDTO(picture.getFileName(), picture.getSize(), picture.getContent(), picture.getId(), picture.getVersion(),
                    DateUtils.getDatatimeFromLong(picture.getCreated()), DateUtils.getDatatimeFromLong(picture.getUpdated()));

            BinaryContentDTO iconDTO = new BinaryContentDTO(icon.getFileName(), icon.getSize(), icon.getContent(), icon.getId(), icon.getVersion(),
                    DateUtils.getDatatimeFromLong(icon.getCreated()), DateUtils.getDatatimeFromLong(icon.getUpdated()));

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
        puzzleGroupDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroup.getCreated()));
        puzzleGroupDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroup.getUpdated()));
        puzzleGroupDTO.setTitle(puzzleGroup.getTitle());
        BinaryContent binaryContent_icon = puzzleGroup.getIcon();
        BinaryContent binaryContent_pic = puzzleGroup.getPic();

        BinaryContentDTO binaryContentDTO_icon = new BinaryContentDTO(binaryContent_icon.getFileName(), binaryContent_icon.getSize(), binaryContent_icon.getContent(), binaryContent_icon.getId(), binaryContent_icon.getVersion()
                , DateUtils.getDatatimeFromLong(binaryContent_icon.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_icon.getUpdated()));
        BinaryContentDTO binaryContentDTO_pic = new BinaryContentDTO(binaryContent_pic.getFileName(), binaryContent_pic.getSize(), binaryContent_pic.getContent(), binaryContent_pic.getId(), binaryContent_pic.getVersion()
                , DateUtils.getDatatimeFromLong(binaryContent_pic.getCreated()), DateUtils.getDatatimeFromLong(binaryContent_pic.getUpdated()));
        puzzleGroupDTO.setIcon(binaryContentDTO_icon);
        puzzleGroupDTO.setPic(binaryContentDTO_pic);

        return puzzleGroupDTO;
    }
    public static PuzzleCategoryDTO getPuzzleCategoryDTO(PuzzleCategory puzzleCategory) {
        PuzzleCategoryDTO puzzleCategoryDTO = new PuzzleCategoryDTO();
        Collection<PuzzleGroup> puzzleGroupCollection = puzzleCategory.getPuzzleGroupCollection();
        puzzleCategoryDTO.setId(puzzleCategoryDTO.getId());
        puzzleCategoryDTO.setVersion(puzzleCategory.getVersion());
        puzzleCategoryDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleCategory.getCreated()));
        puzzleCategoryDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleCategory.getUpdated()));
        puzzleCategoryDTO.setLabel(puzzleCategory.getLabel());
        puzzleCategoryDTO.setValue(puzzleCategory.getValue());
        Collection<PGDTO> puzzleGroupDTOCollection = new ArrayList<PGDTO>();

        Iterator<PuzzleGroup> iterator = puzzleGroupCollection.iterator();
        while(iterator.hasNext()){
            PGDTO puzzleGroupDTO = new PGDTO();
            PuzzleGroup puzzleGroup = iterator.next();
            puzzleGroupDTO = getPuzzleGroupDTO(puzzleGroup);
            puzzleGroupDTOCollection.add(puzzleGroupDTO);
        }
        puzzleCategoryDTO.setPuzzleGroupDTOCollection(puzzleGroupDTOCollection);

        return puzzleCategoryDTO;
    }


    public static Collection<PLObjectiveDTO> getPuzzleLevelObjectiveDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLObjectiveDTO> puzzleLevelObjectiveDTOCollection = new ArrayList<PLObjectiveDTO>();
        Collection<PLObjective> puzzleLevelObjectiveCollection = puzzleLevel.getPlObjectives();
        Iterator<PLObjective> itr_objectives = puzzleLevelObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PLObjective puzzleLevelObjective = itr_objectives.next();
            PLObjectiveDTO puzzleLevelObjectiveDTO = new PLObjectiveDTO();
            puzzleLevelObjectiveDTO.setId(puzzleLevelObjective.getId());
            puzzleLevelObjectiveDTO.setVersion(puzzleLevelObjective.getVersion());
            puzzleLevelObjectiveDTO.setTitle(puzzleLevelObjective.getTitle());
            puzzleLevelObjectiveDTO.setCondition(puzzleLevelObjective.getCondition());
            puzzleLevelObjectiveDTO.setRewardAmount(puzzleLevelObjective.getRewardAmount());
            puzzleLevelObjectiveDTO.setSkillAmount(puzzleLevelObjective.getSkillAmount());
            puzzleLevelObjectiveDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevelObjective.getUpdated()));
            puzzleLevelObjectiveDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevelObjective.getCreated()));

            puzzleLevelObjectiveDTOCollection.add(puzzleLevelObjectiveDTO);
        }
        return puzzleLevelObjectiveDTOCollection;
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
        Collection<PuzzleLevel_LearningTopicDTO> puzzleLevel_learningTopicDTOCollection = new ArrayList<PuzzleLevel_LearningTopicDTO>();
        Collection<PuzzleLevel_LearningTopic> puzzleLevel_learningTopicCollection = puzzleLevel.getPuzzleLevel_learningTopics();
        Iterator<PuzzleLevel_LearningTopic> itr_learningTopics = puzzleLevel_learningTopicCollection.iterator();
        while(itr_learningTopics.hasNext()) {
            PuzzleLevel_LearningTopic puzzleLevel_learningTopic = itr_learningTopics.next();
            PuzzleLevel_LearningTopicDTO puzzleLevel_learningTopicDTO = new PuzzleLevel_LearningTopicDTO();
            LearningTopicDTO learningTopicDTO = new LearningTopicDTO();
            LearningContentDTO learningContentDTO = new LearningContentDTO();

            puzzleLevel_learningTopicDTO.setId(puzzleLevel_learningTopic.getId());
            puzzleLevel_learningTopicDTO.setVersion(puzzleLevel_learningTopic.getVersion());
            puzzleLevel_learningTopicDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getUpdated()));
            puzzleLevel_learningTopicDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getCreated()));

            learningTopicDTO.setId(puzzleLevel_learningTopic.getLearningTopic().getId());
            learningTopicDTO.setTitle(puzzleLevel_learningTopic.getLearningTopic().getTitle());
            learningTopicDTO.setVersion(puzzleLevel_learningTopic.getLearningTopic().getVersion());
            learningTopicDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningTopic().getCreated()));
            learningTopicDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningTopic().getUpdated()));
            puzzleLevel_learningTopicDTO.setLearningTopicDTO(learningTopicDTO);

            learningContentDTO.setId(puzzleLevel_learningTopic.getLearningContent().getId());
            learningContentDTO.setVersion(puzzleLevel_learningTopic.getLearningContent().getVersion());
            learningContentDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningContent().getCreated()));
            learningContentDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel_learningTopic.getLearningContent().getUpdated()));

            learningContentDTO.setDescText(puzzleLevel_learningTopic.getLearningContent().getDescText());
            learningContentDTO.setDescBrief(puzzleLevel_learningTopic.getLearningContent().getDescBrief());

            puzzleLevel_learningTopicDTO.setLearningContentDTO(learningContentDTO);


            puzzleLevel_learningTopicDTOCollection.add(puzzleLevel_learningTopicDTO);
        }

        return puzzleLevel_learningTopicDTOCollection;
    }
    public static CameraSetupDTO getCameraSetupDTO(CameraSetup cameraSetup){
        CameraSetupDTO cameraSetupDTO = new CameraSetupDTO();
        cameraSetupDTO.setId(cameraSetup.getId());
        cameraSetupDTO.setVersion(cameraSetup.getVersion());
        cameraSetupDTO.setCreated(DateUtils.getDatatimeFromLong(cameraSetup.getCreated()));
        cameraSetupDTO.setUpdated(DateUtils.getDatatimeFromLong(cameraSetup.getUpdated()));
        cameraSetupDTO.setxPosition(cameraSetup.getxPosition());
        cameraSetupDTO.setyPosition(cameraSetup.getyPosition());
        cameraSetupDTO.setzPosition(cameraSetup.getzPosition());
        cameraSetupDTO.setxRotation(cameraSetup.getyRotation());
        cameraSetupDTO.setyRotation(cameraSetup.getyRotation());
        cameraSetupDTO.setzRotation(cameraSetup.getzRotation());
        return cameraSetupDTO;
    }
    public static Collection<PLGroundDTO> getPuzzleLevelGroundDTOS(PuzzleLevel puzzleLevel) {
        Collection<PLGroundDTO> puzzleLevelGroundDTOCollection = new ArrayList<PLGroundDTO>();
        Collection<PLGround> puzzleLevelGroundCollection = puzzleLevel.getPlGrounds();
        Iterator<PLGround> itr_Grounds = puzzleLevelGroundCollection.iterator();
        while(itr_Grounds.hasNext()) {
            PLGround puzzleLevelGround = itr_Grounds.next();
            PLGroundDTO puzzleLevelGroundDTO = new PLGroundDTO();
            puzzleLevelGroundDTO.setId(puzzleLevelGround.getId());
            puzzleLevelGroundDTO.setVersion(puzzleLevelGround.getVersion());
            puzzleLevelGroundDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getCreated()));
            puzzleLevelGroundDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getUpdated()));
            puzzleLevelGroundDTO.setNumRows(puzzleLevelGround.getNumRows());
            puzzleLevelGroundDTO.setNumColumns(puzzleLevelGround.getNumColumns());
            CameraSetupDTO cameraSetupDTO = getCameraSetupDTO(puzzleLevelGround.getCameraSetup());
            puzzleLevelGroundDTO.setCameraSetup(cameraSetupDTO);

            BinaryContent boardGraphic = puzzleLevelGround.getBoardGraphic();
            BinaryContentDTO backGroundDTO= new BinaryContentDTO();
            backGroundDTO.setId(boardGraphic.getId());
            backGroundDTO.setFileName(boardGraphic.getFileName());
            backGroundDTO.setVersion(boardGraphic.getVersion());
            backGroundDTO.setSize(boardGraphic.getSize());
            backGroundDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getUpdated()));
            backGroundDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getCreated()));


            puzzleLevelGroundDTO.setBoardGraphic(backGroundDTO);
            puzzleLevelGroundDTOCollection.add(puzzleLevelGroundDTO);
        }


        return puzzleLevelGroundDTOCollection;
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
            permitedPlayerDTO.setUpdated(DateUtils.getDatatimeFromLong(permitedPlayer.getUpdated()));
            permitedPlayerDTO.setCreated(DateUtils.getDatatimeFromLong(permitedPlayer.getCreated()));
            permitedPlayerDTOCollection.add(permitedPlayerDTO);

        }
        return permitedPlayerDTOCollection;
    }

    public static Collection<PuzzleGroupObjectInstanceDTO>  getPuzzleGroupObjectInstance(PuzzleLevel puzzleLevel){
        Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = new ArrayList<PuzzleGroupObjectInstanceDTO>();
        Collection<PGObjectInstance> puzzleGroupObjectInstanceCollection = puzzleLevel.getPuzzleGroupObjectInstanceCollection();
        Iterator<PGObjectInstance> itr = puzzleGroupObjectInstanceCollection.iterator();

        while(itr.hasNext()){
            PGObjectInstance puzzleGroupObjectInstance = itr.next();
            PuzzleGroupObjectInstanceDTO puzzleGroupObjectInstanceDTO = new PuzzleGroupObjectInstanceDTO();
            puzzleGroupObjectInstanceDTO.setId(puzzleGroupObjectInstance.getId());
            puzzleGroupObjectInstanceDTO.setVersion(puzzleGroupObjectInstance.getVersion());
            puzzleGroupObjectInstanceDTO.setCol(puzzleGroupObjectInstance.getCol());
            puzzleGroupObjectInstanceDTO.setRow(puzzleGroupObjectInstance.getRow());
            puzzleGroupObjectInstanceDTO.setzOrder(puzzleGroupObjectInstance.getzOrder());
            puzzleGroupObjectInstanceDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroupObjectInstance.getCreated()));
            puzzleGroupObjectInstanceDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroupObjectInstance.getUpdated()));

            PuzzleGroup_PuzzleObject puzzleGroup_puzzleObject = puzzleGroupObjectInstance.getPuzzleGroup_PuzzleObject();
            PuzzleGroup_PuzzleObjectDTO  puzzleGroup_puzzleObjectDTO= new PuzzleGroup_PuzzleObjectDTO();
            puzzleGroup_puzzleObjectDTO.setId(puzzleGroup_puzzleObject.getId());
            puzzleGroup_puzzleObjectDTO.setVersion(puzzleGroup_puzzleObject.getVersion());
            puzzleGroup_puzzleObjectDTO.setCode(puzzleGroup_puzzleObject.getCode());
            puzzleGroup_puzzleObjectDTO.setTitle(puzzleGroup_puzzleObject.getTitle());
            puzzleGroup_puzzleObjectDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getCreated()));
            puzzleGroup_puzzleObjectDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleGroup_puzzleObject.getUpdated()));

            puzzleGroupObjectInstanceDTO.setPuzzleGroup_puzzleObjectDTO(puzzleGroup_puzzleObjectDTO);

            puzzleGroupObjectInstanceDTOCollection.add(puzzleGroupObjectInstanceDTO);
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
            BinaryContent picture = po.getPicture();
            BinaryContentDTO pictureDTO = new BinaryContentDTO(picture.getFileName(),picture.getSize(),picture.getContent(),picture.getId(),picture.getVersion(),
                    DateUtils.getDatatimeFromLong(picture.getCreated()),
                    DateUtils.getDatatimeFromLong(picture.getUpdated()));

            BinaryContent icon = po.getIcon();
            BinaryContentDTO iconDTO = new BinaryContentDTO(icon.getFileName(),icon.getSize(),icon.getContent(),icon.getId(),icon.getVersion(),
                    DateUtils.getDatatimeFromLong(icon.getCreated()),
                    DateUtils.getDatatimeFromLong(icon.getUpdated()));
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
            BinaryContent picture = po.getPicture();
            BinaryContentDTO pictureDTO = new BinaryContentDTO(picture.getFileName(),picture.getSize(),picture.getContent(),picture.getId(),picture.getVersion(),
                    DateUtils.getDatatimeFromLong(picture.getCreated()),
                    DateUtils.getDatatimeFromLong(picture.getUpdated()));

            BinaryContent icon = po.getIcon();
            BinaryContentDTO iconDTO = new BinaryContentDTO(icon.getFileName(),icon.getSize(),icon.getContent(),icon.getId(),icon.getVersion(),
                    DateUtils.getDatatimeFromLong(icon.getCreated()),
                    DateUtils.getDatatimeFromLong(icon.getUpdated()));
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
            puzzleObject_objectActionDTO.setCreated(DateUtils.getDatatimeFromLong(poa.getCreated()));
            puzzleObject_objectActionDTO.setUpdated(DateUtils.getDatatimeFromLong(poa.getUpdated()));
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
            puzzleObject_objectActionDTO.setCreated(DateUtils.getDatatimeFromLong(poa.getCreated()));
            puzzleObject_objectActionDTO.setUpdated(DateUtils.getDatatimeFromLong(poa.getUpdated()));
            ObjectAction objectAction = poa.getObjectAction();

            puzzleObject_objectActionDTO.setObjectAction(objectAction);

//            POActionOwnerType poaot = new POActionOwnerType();
//            poaot = poa.getPuzzleObjectActionOwnerType();
//            PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO = new PuzzleObjectActionOwnerTypeDTO(poaot.getId(),poaot.getLabel(),poaot.getValue(),poaot.getVersion(),
//                    DateUtils.getDatatimeFromLong(poaot.getCreated()),DateUtils.getDatatimeFromLong(poaot.getUpdated()));

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
           actionRendererDTO.setCreated(DateUtils.getDatatimeFromLong(actionRenderer.getCreated()));
           actionRendererDTO.setUpdated(DateUtils.getDatatimeFromLong(actionRenderer.getUpdated()));
           actionRendererDTO.setVersion(actionRenderer.getVersion());
           ObjectAction objectAction = actionRenderer.getObjectAction();


           actionRendererDTO.setObjectAction(objectAction);

           ClientType clientType = actionRenderer.getClientType();
           ClientTypeDTO clientTypeDTO = new ClientTypeDTO(clientType.getId(),clientType.getLabel(),clientType.getValue(), clientType.getVersion(),
                   DateUtils.getDatatimeFromLong(clientType.getCreated()),
                   DateUtils.getDatatimeFromLong(clientType.getUpdated()));
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
                String type = attribute.getDataType().getValue();
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