package com.alcity.utility;

import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.dto.alobject.ObjectActionDTO;
import com.alcity.dto.alobject.PuzzleObjectActionOwnerTypeDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.dto.player.PermitedPlayerDTO;
import com.alcity.dto.puzzle.*;
import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PuzzleObjectActionOwnerType;
import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.ClientType;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
import com.alcity.utility.DateUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;

public class DTOUtil {


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

    public static Collection<PuzzleLevelDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> puzzleLevelCollection) {
        Collection<PuzzleLevelDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelDTO>();

        Iterator<PuzzleLevel> itrPuzzleLevel = puzzleLevelCollection.iterator();
        while (itrPuzzleLevel.hasNext()) {
            PuzzleLevel puzzleLevel = itrPuzzleLevel.next();
            PuzzleLevelDTO puzzleLevelDTO = new PuzzleLevelDTO();
            puzzleLevelDTO.setId(puzzleLevel.getId());
            puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
            puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
            puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
            puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));
            puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
            puzzleLevelDTO.setCode(puzzleLevel.getCode());
            puzzleLevelDTO.setTitle(puzzleLevel.getTitle());
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

    public static PuzzleGroupDTO getPuzzleGroupDTO(PuzzleGroup puzzleGroup) {
        PuzzleGroupDTO puzzleGroupDTO = new PuzzleGroupDTO();
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


    public static PuzzleLevelDTO getPuzzleLevelDTO(PuzzleLevel puzzleLevel) {
        PuzzleLevelDTO puzzleLevelDTO = new PuzzleLevelDTO();

        puzzleLevelDTO.setId(puzzleLevel.getId());
        puzzleLevelDTO.setVersion(puzzleLevel.getVersion());
        puzzleLevelDTO.setCode(puzzleLevel.getCode());
        puzzleLevelDTO.setApproveDate(DateUtils.getDatatimeFromLong(puzzleLevel.getApproveDate()));
        puzzleLevelDTO.setTitle(puzzleLevel.getTitle());
        puzzleLevelDTO.setToAge(puzzleLevel.getToAge());
        puzzleLevelDTO.setFromAge(puzzleLevel.getFromAge());
        puzzleLevelDTO.setOrdering(puzzleLevel.getOrdering());
        puzzleLevelDTO.setMaxScore(puzzleLevel.getMaxScore());
        puzzleLevelDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevel.getUpdated()));
        puzzleLevelDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevel.getCreated()));
        puzzleLevelDTO.setPuzzleLevelDifficulty(puzzleLevel.getPuzzleDifficulty().getLabel());
        puzzleLevelDTO.setPuzzleLevelPrivacy(puzzleLevel.getPuzzleLevelPrivacy().getLabel());
        puzzleLevelDTO.setPuzzleLevelStatus(puzzleLevel.getPuzzleLevelStatus().getLabel());
        return puzzleLevelDTO;
    }

    public static Collection<PuzzleLevelObjectiveDTO> getPuzzleLevelObjectiveDTOS(PuzzleLevel puzzleLevel) {
        Collection<PuzzleLevelObjectiveDTO> puzzleLevelObjectiveDTOCollection = new ArrayList<PuzzleLevelObjectiveDTO>();
        Collection<PuzzleLevelObjective> puzzleLevelObjectiveCollection = puzzleLevel.getPuzzleLevelObjectiveCollection();
        Iterator<PuzzleLevelObjective> itr_objectives = puzzleLevelObjectiveCollection.iterator();

        while (itr_objectives.hasNext()) {
            PuzzleLevelObjective puzzleLevelObjective = itr_objectives.next();
            PuzzleLevelObjectiveDTO puzzleLevelObjectiveDTO = new PuzzleLevelObjectiveDTO();
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

    public static Collection<PuzzleLevelGroundDTO> getPuzzleLevelGroundDTOS(PuzzleLevel puzzleLevel) {
        Collection<PuzzleLevelGroundDTO> puzzleLevelGroundDTOCollection = new ArrayList<PuzzleLevelGroundDTO>();
        Collection<PuzzleLevelGround> puzzleLevelGroundCollection = puzzleLevel.getPuzzleLevelGroundCollection();
        Iterator<PuzzleLevelGround> itr_Grounds = puzzleLevelGroundCollection.iterator();
        while(itr_Grounds.hasNext()) {
            PuzzleLevelGround puzzleLevelGround = itr_Grounds.next();
            PuzzleLevelGroundDTO puzzleLevelGroundDTO = new PuzzleLevelGroundDTO();
            puzzleLevelGroundDTO.setId(puzzleLevelGround.getId());
            puzzleLevelGroundDTO.setVersion(puzzleLevelGround.getVersion());
            puzzleLevelGroundDTO.setCreated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getCreated()));
            puzzleLevelGroundDTO.setUpdated(DateUtils.getDatatimeFromLong(puzzleLevelGround.getUpdated()));
            puzzleLevelGroundDTO.setNumRows(puzzleLevelGround.getNumRows());
            puzzleLevelGroundDTO.setNumColumns(puzzleLevelGround.getNumColumns());
            puzzleLevelGroundDTO.setCameraSetup(puzzleLevelGround.getCameraSetup());

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
        Collection<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceCollection = puzzleLevel.getPuzzleGroupObjectInstanceCollection();
        Iterator<PuzzleGroupObjectInstance> itr = puzzleGroupObjectInstanceCollection.iterator();

        while(itr.hasNext()){
            PuzzleGroupObjectInstance puzzleGroupObjectInstance = itr.next();
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
            puzzleObject_objectActionDTO.setCreated(DateUtils.getDatatimeFromLong(poa.getCreated()));
            puzzleObject_objectActionDTO.setUpdated(DateUtils.getDatatimeFromLong(poa.getUpdated()));
            ObjectAction objectAction = new ObjectAction();
            objectAction = poa.getObjectAction();
            ObjectActionDTO objectActionDTO = new ObjectActionDTO(objectAction.getId(), objectAction.getLabel(), objectAction.getValue(), objectAction.getVersion(),
                    DateUtils.getDatatimeFromLong(objectAction.getCreated()),DateUtils.getDatatimeFromLong(objectAction.getUpdated()));

            puzzleObject_objectActionDTO.setObjectActionDTO(objectActionDTO);

            PuzzleObjectActionOwnerType poaot = new PuzzleObjectActionOwnerType();

            PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO = new PuzzleObjectActionOwnerTypeDTO(poaot.getId(),poaot.getLabel(),poaot.getValue(),poaot.getVersion(),
                    DateUtils.getDatatimeFromLong(poaot.getCreated()),DateUtils.getDatatimeFromLong(poaot.getUpdated()));

            puzzleObject_objectActionDTO.setPuzzleObjectActionOwnerTypeDTO(puzzleObjectActionOwnerTypeDTO);

            ActionRenderer actionRenderer = poa.getActionRenderer();
//            ActionRendererDTO actionRendererDTO = new ActionRendererDTO();
//            actionRendererDTO.setId(actionRenderer.getId());
//            actionRendererDTO.setVersion(actionRenderer.getVersion());
//            actionRendererDTO.setCreated(DateUtils.getDatatimeFromLong(actionRenderer.getCreated()));
//            actionRendererDTO.setUpdated(DateUtils.getDatatimeFromLong(actionRenderer.getUpdated()));
//            actionRendererDTO.setHandler(actionRenderer.getHandler());
            ActionRendererDTO actionRendererDTO=DTOUtil.getActionRenderer(actionRenderer);
            puzzleObject_objectActionDTO.setActionRendererDTO(actionRendererDTO);
            puzzleObject_objectActionDTOCollection.add(puzzleObject_objectActionDTO);
        }

        return puzzleObject_objectActionDTOCollection;
    }

   public static ActionRendererDTO getActionRenderer(ActionRenderer actionRenderer){
           ActionRendererDTO actionRendererDTO = new ActionRendererDTO();
           actionRendererDTO.setId(actionRenderer.getId());
           actionRendererDTO.setCreated(DateUtils.getDatatimeFromLong(actionRenderer.getCreated()));
           actionRendererDTO.setUpdated(DateUtils.getDatatimeFromLong(actionRenderer.getUpdated()));
           actionRendererDTO.setVersion(actionRenderer.getVersion());
           ObjectAction objectAction = actionRenderer.getObjectAction();

           ObjectActionDTO objectActionDTO = new ObjectActionDTO(objectAction.getId(),objectAction.getLabel(),objectAction.getValue(),objectAction.getVersion(),
                   DateUtils.getDatatimeFromLong(objectAction.getCreated()),
                   DateUtils.getDatatimeFromLong(objectAction.getUpdated()));

           actionRendererDTO.setObjectActionDTO(objectActionDTO);

           ClientType clientType = actionRenderer.getClientType();
           ClientTypeDTO clientTypeDTO = new ClientTypeDTO(clientType.getId(),clientType.getLabel(),clientType.getValue(), clientType.getVersion(),
                   DateUtils.getDatatimeFromLong(clientType.getCreated()),
                   DateUtils.getDatatimeFromLong(clientType.getUpdated()));

           actionRendererDTO.setHandler(actionRenderer.getHandler());

       return actionRendererDTO;
   }

}