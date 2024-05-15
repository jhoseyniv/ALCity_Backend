package com.alcity.utility;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.Interpreter.PLObjectiveData;
import com.alcity.dto.Interpreter.object.RecordrData;
import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
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
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
import com.alcity.service.alobject.AttributeService;

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
        plDTO.setTitle(pl.getName());
        plDTO.setToAge(pl.getToAge());
        plDTO.setFromAge(pl.getFromAge());
        plDTO.setOrdering(pl.getOrdering());
        plDTO.setMaxScore(pl.getMaxScore());
        plDTO.setUpdated(pl.getUpdated());
        plDTO.setCreated(pl.getCreated());
        plDTO.setCreatedBy(pl.getCreatedBy().getUsername());
        plDTO.setUpdatedBy(pl.getUpdatedBy().getUsername());
        plDTO.setCreatedById(pl.getCreatedBy().getId());
        plDTO.setUpdatedById(pl.getUpdatedBy().getId());

        plDTO.setPuzzleLevelDifficulty(pl.getPuzzleDifficulty().toString());
        plDTO.setPuzzleLevelPrivacy(pl.getPuzzleLevelPrivacy().getLabel());
        plDTO.setPuzzleLevelStatus(pl.getPuzzleLevelStatus().toString());

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
            Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = DTOUtil.getPuzzleGroupObjectInstance(puzzleLevel);

            puzzleLevelDTO.setPuzzleLevelGroundDTOCollection(puzzleLevelGroundDTOCollection);
            puzzleLevelDTO.setPuzzleLevelObjectiveDTOCollection(puzzleLevelObjectiveDTOCollection);
            puzzleLevelDTO.setPuzzleLevel_learningTopicDTOCollection(puzzleLevel_learningTopicDTOCollection);
            puzzleLevelDTO.setPermitedPlayerDTOCollection(permitedPlayerDTOCollection);
            puzzleLevelDTO.setPuzzleGroupObjectInstanceDTOCollection(puzzleGroupObjectInstanceDTOCollection);
        } else puzzleLevelDTO = null;

        return puzzleLevelDTO;
    }

        public static AttributeDTO getAttributeDTO(Attribute attribute){
            AttributeDTO attributeDTO = new AttributeDTO(attribute.getId(), attribute.getVersion(), attribute.getCreated(),
                    attribute.getUpdated(),attribute.getCreatedBy().getUsername(),attribute.getUpdatedBy().getUsername(),
                    attribute.getName(),attribute.getOwnerId(),attribute.getAttributeOwnerType().toString(),attribute.getDataType().toString());
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
            //.....
            journeyStepDTOCollection.add(journeyStepDTO);
        }
        return journeyStepDTOCollection;
    }

    public static Collection<PuzzleLevelLDTO> getPuzzleLevelDTOS(Collection<PuzzleLevel> puzzleLevelCollection) {
        Collection<PuzzleLevelLDTO> puzzleLevelDTOCollection = new ArrayList<PuzzleLevelLDTO>();

        Iterator<PuzzleLevel> itrPuzzleLevel = puzzleLevelCollection.iterator();
        while (itrPuzzleLevel.hasNext()) {
            PuzzleLevel puzzleLevel = itrPuzzleLevel.next();
            PuzzleLevelLDTO puzzleLevelDTO = new PuzzleLevelLDTO();
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
                    learningSkill.getCreated(), learningSkill.getUpdated(),learningSkill.getCreatedBy().getUsername(),
                    learningSkill.getUpdatedBy().getUsername());
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

    public static Collection<ALCityObjectInPuzzleGroupDTO> getPuzzleGroup_PuzzleObjectDTOS(Collection<ALCityObjectInPG> puzzleGroup_puzzleObjectCollection) {
        Collection<ALCityObjectInPuzzleGroupDTO> alCityObjectInPuzzleGroupDTOS = new ArrayList<ALCityObjectInPuzzleGroupDTO>();
        Iterator<ALCityObjectInPG> itrPuzzleGroup_puzzleObject = puzzleGroup_puzzleObjectCollection.iterator();
        while (itrPuzzleGroup_puzzleObject.hasNext()) {
            ALCityObjectInPuzzleGroupDTO alCityObjectInPuzzleGroupDTO = new ALCityObjectInPuzzleGroupDTO();
            ALCityObjectInPG alCityObjectInPG = itrPuzzleGroup_puzzleObject.next();
            alCityObjectInPuzzleGroupDTO.setId(alCityObjectInPG.getId());
            alCityObjectInPuzzleGroupDTO.setCode(alCityObjectInPG.getCode());
            alCityObjectInPuzzleGroupDTO.setTitle(alCityObjectInPG.getTitle());
            alCityObjectInPuzzleGroupDTO.setVersion(alCityObjectInPG.getVersion());
            alCityObjectInPuzzleGroupDTO.setCreated(alCityObjectInPG.getCreated());
            alCityObjectInPuzzleGroupDTO.setUpdated(alCityObjectInPG.getUpdated());

            ALCityObject alCityObject = alCityObjectInPG.getAlCityObject();
            ALCityObjectDTO alCityObjectDTO = new ALCityObjectDTO();
            alCityObjectDTO.setId(alCityObject.getId());
            alCityObjectDTO.setVersion(alCityObject.getVersion());
            alCityObjectDTO.setObjectCategory(alCityObject.getObjectCategory().getLabel());
            alCityObjectDTO.setTitle(alCityObject.getTitle());
            alCityObjectDTO.setCreated(alCityObject.getCreated());
            alCityObjectDTO.setUpdated(alCityObject.getUpdated());
            alCityObjectDTO.setPictureId(alCityObject.getPicture().getId());
            alCityObjectDTO.setIconId(alCityObject.getIcon().getId());

            alCityObjectInPuzzleGroupDTO.setPuzzleObjectDTO(alCityObjectDTO);
            alCityObjectInPuzzleGroupDTOS.add(alCityObjectInPuzzleGroupDTO);
        }
        return alCityObjectInPuzzleGroupDTOS;

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

    public static PLObjectiveDTO getPuzzleLevelObjectiveDTO(PLObjective plObjective){
        PLObjectiveDTO plObjectiveDTO = new PLObjectiveDTO();
        plObjectiveDTO.setId(plObjective.getId());
        plObjectiveDTO.setVersion(plObjective.getVersion());
        plObjectiveDTO.setTitle(plObjective.getTitle());
        plObjectiveDTO.setCondition(plObjective.getCondition());
        plObjectiveDTO.setDescription(plObjective.getDescription());
        plObjectiveDTO.setRewardAmount(plObjective.getRewardAmount());
        plObjectiveDTO.setSkillAmount(plObjective.getSkillAmount());
        plObjectiveDTO.setUpdated(plObjective.getUpdated());
        plObjectiveDTO.setCreated(plObjective.getCreated());
        plObjectiveDTO.setCreatedBy(plObjective.getCreatedBy().getUsername());
        plObjectiveDTO.setUpdatedBy(plObjective.getUpdatedBy().getUsername());
        plObjectiveDTO.setUpdatedById(plObjective.getUpdatedBy().getId());
        plObjectiveDTO.setCreatedById(plObjective.getCreatedBy().getId());
        plObjectiveDTO.setLearningSkillDTO(getLearningSkillDTO(plObjective.getLearningSkill()));
        plObjectiveDTO.setWalletItemDTO(getWalletItemDTO(plObjective.getWalletItem()));
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
                    ls.getVersion(), ls.getCreated(), ls.getUpdated(),ls.getCreatedBy().getUsername(),ls.getUpdatedBy().getUsername());
         return lsDTO;
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

    public static Collection<PuzzleGroupObjectInstanceDTO>  getPuzzleGroupObjectInstance(PuzzleLevel puzzleLevel){
        Collection<PuzzleGroupObjectInstanceDTO> puzzleGroupObjectInstanceDTOCollection = new ArrayList<PuzzleGroupObjectInstanceDTO>();
        Collection<ALCityInstanceInPL> puzzleGroupObjectInstanceCollection = puzzleLevel.getPuzzleGroupObjectInstanceCollection();
        Iterator<ALCityInstanceInPL> itr = puzzleGroupObjectInstanceCollection.iterator();

        while(itr.hasNext()){
            ALCityInstanceInPL pgObjectInstance = itr.next();
            PuzzleGroupObjectInstanceDTO pgObjectInstanceDTO = new PuzzleGroupObjectInstanceDTO();
            pgObjectInstanceDTO.setId(pgObjectInstance.getId());
            pgObjectInstanceDTO.setVersion(pgObjectInstance.getVersion());
            pgObjectInstanceDTO.setCol(pgObjectInstance.getCol());
            pgObjectInstanceDTO.setRow(pgObjectInstance.getRow());
            pgObjectInstanceDTO.setzOrder(pgObjectInstance.getzOrder());
            pgObjectInstanceDTO.setCreated(pgObjectInstance.getCreated());
            pgObjectInstanceDTO.setUpdated(pgObjectInstance.getUpdated());

            ALCityObjectInPG alCityObject_puzzleGroup = pgObjectInstance.getAlCityObjectInPG();
            ALCityObjectInPuzzleGroupDTO puzzleGroup_puzzleObjectDTO= new ALCityObjectInPuzzleGroupDTO();
            puzzleGroup_puzzleObjectDTO.setId(alCityObject_puzzleGroup.getId());
            puzzleGroup_puzzleObjectDTO.setVersion(alCityObject_puzzleGroup.getVersion());
            puzzleGroup_puzzleObjectDTO.setCode(alCityObject_puzzleGroup.getCode());
            puzzleGroup_puzzleObjectDTO.setTitle(alCityObject_puzzleGroup.getTitle());
            puzzleGroup_puzzleObjectDTO.setCreated(alCityObject_puzzleGroup.getCreated());
            puzzleGroup_puzzleObjectDTO.setUpdated(alCityObject_puzzleGroup.getUpdated());

            pgObjectInstanceDTO.setPuzzleGroup_puzzleObjectDTO(puzzleGroup_puzzleObjectDTO);

            puzzleGroupObjectInstanceDTOCollection.add(pgObjectInstanceDTO);
        }
        return puzzleGroupObjectInstanceDTOCollection;
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
    public static  Collection<PuzzleObjectActionDTO> getPuzzleObjectActionDTOS(Collection<PuzzleObjectAction> puzzleObject_objectActionCollection) {
        Collection<PuzzleObjectActionDTO> puzzleObjectActionDTOS = new ArrayList<PuzzleObjectActionDTO>();
        Iterator<PuzzleObjectAction> iterator = puzzleObject_objectActionCollection.iterator();
        while (iterator.hasNext()) {
            PuzzleObjectActionDTO puzzleObjectActionDTO = new PuzzleObjectActionDTO();
            PuzzleObjectAction poa = iterator.next();
            puzzleObjectActionDTO = getPuzzleObjectActionDTO(poa);
            puzzleObjectActionDTOS.add(puzzleObjectActionDTO);
        }

        return puzzleObjectActionDTOS;
    }

    public static PuzzleObjectActionDTO getPuzzleObjectActionDTO(PuzzleObjectAction poa) {
        PuzzleObjectActionDTO puzzleObjectActionDTO = new PuzzleObjectActionDTO();

        puzzleObjectActionDTO.setId(poa.getId());
        puzzleObjectActionDTO.setVersion(poa.getVersion());
        puzzleObjectActionDTO.setOwnerObjectid(poa.getOwnerObjectid());
        puzzleObjectActionDTO.setCreated(poa.getCreated());
        puzzleObjectActionDTO.setUpdated(poa.getUpdated());

        ObjectAction objectAction = poa.getObjectAction();
        puzzleObjectActionDTO.setObjectAction(objectAction);

        ActionRenderer actionRenderer = poa.getActionRenderer();
        ActionRendererDTO actionRendererDTO=DTOUtil.getActionRendererDTO(actionRenderer);
        puzzleObjectActionDTO.setActionRendererDTO(actionRendererDTO);

        return puzzleObjectActionDTO;
    }
  public static ClientTypeDTO getClientTypeDTO(ClientType ctype){
      ClientTypeDTO clientTypeDTO = new ClientTypeDTO(ctype.getId(), ctype.getLabel(), ctype.getValue(), ctype.getVersion(),
              ctype.getCreated(),ctype.getUpdated());
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
    public static ActionRendererDTO getActionRendererDTO(ActionRenderer actionRenderer){
           ActionRendererDTO aRendererDTO = new ActionRendererDTO();
            aRendererDTO.setId(actionRenderer.getId());
            aRendererDTO.setCreated(actionRenderer.getCreated());
            aRendererDTO.setUpdated(actionRenderer.getUpdated());
            aRendererDTO.setVersion(actionRenderer.getVersion());
            aRendererDTO.setUpdatedBy(actionRenderer.getUpdatedBy().getUsername());
            aRendererDTO.setCreatedBy(actionRenderer.getCreatedBy().getUsername());
            ObjectAction objectAction = actionRenderer.getObjectAction();
            aRendererDTO.setObjectAction(objectAction);

             ClientType clientType = actionRenderer.getClientType();
             ClientTypeDTO clientTypeDTO = new ClientTypeDTO(clientType.getId(),clientType.getLabel(),
                     clientType.getValue(), clientType.getVersion(),
                   clientType.getCreated(), clientType.getUpdated());
            aRendererDTO.setClientTypeDTO(clientTypeDTO);
            aRendererDTO.setHandler(actionRenderer.getHandler());

       return aRendererDTO;
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