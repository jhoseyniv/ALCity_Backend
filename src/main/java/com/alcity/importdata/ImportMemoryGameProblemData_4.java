package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.Journey.JourneyStepService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.AppMember_WalletItemService;
import com.alcity.service.appmember.WalletItemService;
import com.alcity.utility.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Order(value=7)
@Component
public class ImportMemoryGameProblemData_4 implements CommandLineRunner {

    @Autowired
    private AppMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;


    @Autowired
    PLGroundService puzzleLevelGroundService;
    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PGService puzzleGroupService;


    @Autowired
    private PLPrivacyService puzzleLevelPrivacyService;

    @Autowired
    PermitedPlayerRepository permitedPlayerRepository;

    @Autowired
    PLLearningTopicService plLearningTopicService;

    @Autowired
    PlayHistoryService playHistoryService;


    @Autowired
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;

    @Autowired
    PLGameInstanceService plGameInstanceService;

    @Autowired
    ObjectCategoryService objectCategoryService;

    @Autowired
    ALCityObjectService puzzleObjectService;

    @Autowired
    ALCityInstanceInPLService pgObjectInstanceService;


    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    PLRuleEventService plRuleEventService;


    @Autowired
    private CameraSetupService cameraSetupService;


    @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    RendererService actionRendererService;


    @Autowired
    PuzzleObjectActionService puzzleObject_ObjectActionService;

    @Autowired
    ALCityObjectInPGService alCityObjectInPGService;

    @Autowired
    PLRuleService puzzleLevelRuleService;

    @Autowired
    PLRulePostActionService plRulePostActionService;


    @Autowired
    PLRuleEventService puzzleLevelRuleEventService;

    @Autowired
    PLRulePostActionService puzzleLevelRulePostActionService;

    @Autowired
    PLObjectiveService plObjectiveService;

    @Autowired
    AttributeService attributeService;


     @Autowired
    LearningSkillService learningSkillService;

    @Autowired
    WalletItemService walletItemService;
    @Autowired
    JourneyService journeyService;

    @Autowired
    JourneyStepService journeyStepService;

    @Autowired
    private AppMember_WalletItemService applicationMember_walletItemService;


   @Autowired
    PuzzleSkillLearningContentService puzzleSkillLearningContentService;
    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 3 X-O Probelm");
        System.out.println("...Import X-O Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        AppMember admin_1 = applicationMemberService.findByUsername("admin");
        AppMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        AppMember Alireza_Zare = applicationMemberService.findByUsername("alireza");

        Optional<LearningTopic> Memory_Game_Topic = learningTopicService.findByTitle("Memory_Game");

        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill memory_booster = learningSkillService.findByValue("memory_booster");
        Optional<WalletItem> alCoin100WalletItem = walletItemService.findByValue("al_coin_100");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

        Optional<Journey> journey_1 = journeyService.findByTitle("Journey_1");
        Optional<Journey> journey_2 = journeyService.findByTitle("Journey_2");

        byte[]  planyGround_Image_Memory_Game = ImageUtil.getImage("src/main/resources/images/Memory-Game/","MemGame.png");
        BinaryContent playGround_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"puzzle ground for Memory Game",planyGround_Image_Memory_Game.length,planyGround_Image_Memory_Game,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(playGround_Memory_Game_content);

        byte[] pl_Icon_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","MemGame_icon.png");
        BinaryContent pl_Icon_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Icon",pl_Icon_Memory_Game_bytes.length,pl_Icon_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Memory_Game_content);

        byte[] pl_pic_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","MemGame.png");
        BinaryContent pl_pic_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Picture",pl_pic_Memory_Game_bytes.length,pl_pic_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Memory_Game_content);

        //BinaryContent puzzle_group_1_binary_content_image = binaryContentService.findByfileName("image_puzzle_group_matematic");

        byte[] text_object_content = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",text_object_content.length,text_object_content,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(textObject_binary_content);

        ObjectCategory objectCategory_TextObject = objectCategoryService.findByValue("TextObject");

        Optional<PuzzleGroup>  IQ_Puzzle_Group =puzzleGroupService.findByTitle("Hash Image - Puzzle Group 1");

         Optional<ALCityObject> textObject = puzzleObjectService.findByTitle("TextObject");


        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel pl_Memory_Game= new PuzzleLevel(now,1L,"Memory-Game","4501",10,14,5f,0f,2f,3f,IQ_Puzzle_Group.get(),PLDifficulty.Easy,PLStatus.Ongoing,privacy_1,3L,now,now,admin_1,admin_1);
        pl_Memory_Game.setIcon(pl_pic_Memory_Game_content);
        pl_Memory_Game.setPicture(pl_pic_Memory_Game_content);
        puzzleLevelService.save(pl_Memory_Game);


        byte[] X_O_LearningContent_Image_bytes = ImageUtil.getImage("src/main/resources/images/X-O Problem/","puzzle_group_X_O_Image.png");
        BinaryContent pg_Memory_Game_learning_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory-Game", X_O_LearningContent_Image_bytes.length, X_O_LearningContent_Image_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pg_Memory_Game_learning_content);

        Integer xPos=1;
        Integer xRotation=0;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);

        PLGround pl_Memory_Game_ground = new PLGround(4,3,pl_Memory_Game,playGround_Memory_Game_content,1L,now,now,admin_1,admin_1);
        pl_Memory_Game_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(pl_Memory_Game_ground);

        PermitedPlayer player_1_puzzleLevel_X_O = new PermitedPlayer(Alireza_Zare,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_X_O);

        LearningContent learningContent_memory=new LearningContent("help to Memory","this content is about Memory Games",pg_Memory_Game_learning_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_memory);

        LearningSubjectInPL pllLearningTopic_1 = new LearningSubjectInPL(pl_Memory_Game,Memory_Game_Topic.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(pllLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,pl_Memory_Game,now,100,10f,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        LearningSkillContent puzzleSkillLearningContent_1 = new LearningSkillContent(memory_booster,IQ_Puzzle_Group.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,pl_Memory_Game,GameStatus.gameStatus_1,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("BoardVar(matched)=6");

        PLObjective pl_objective_1 = new PLObjective("finished","mission completed",1.5f,2f,condition_Objective_1,memory_booster,
                                                                 alCoin10WalletItem.get(),pl_Memory_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_1);


        Attribute pl_variable_matched =new Attribute("matched",pl_Memory_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_matched);
        AttributeValue pl_variable_matched_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_matched,pl_variable_matched,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_matched_value);

        Attribute pl_variable_turn =new Attribute("turn",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_turn);

        AttributeValue pl_variable_turn_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_turn,pl_variable_turn,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_turn_value);

        Attribute pl_variable_firstFlippedX =new Attribute("firstFlippedX",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_firstFlippedX);

        AttributeValue pl_variable_firstFlippedX_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_firstFlippedX,pl_variable_firstFlippedX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_firstFlippedX_value);

        Attribute pl_variable_firstFlippedY =new Attribute("firstFlippedY",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_firstFlippedY);

        AttributeValue pl_variable_firstFlippedY_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_firstFlippedY,pl_variable_firstFlippedY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_firstFlippedY_value);

        Attribute pl_variable_secondFlippedX =new Attribute("secondFlippedX",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_secondFlippedX);

        AttributeValue pl_variable_secondFlippedX_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_secondFlippedX,pl_variable_secondFlippedX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_secondFlippedX_value);

        Attribute pl_variable_secondFlippedY =new Attribute("secondFlippedY",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_secondFlippedY);

        AttributeValue pl_variable_secondFlippedY_value= new AttributeValue(null,0,null,null,null,null,null,pl_variable_secondFlippedY,pl_variable_secondFlippedY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_secondFlippedY_value);

        Attribute pl_variable_finished =new Attribute("finished",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);

        AttributeValue pl_variable_finished_value= new AttributeValue(false,null,null,null,null,null,null,pl_variable_finished,pl_variable_finished,1L,now,now,admin_1,admin_1);
        attributeValueService.save(pl_variable_finished_value);






        Optional<ALCityObjectInPG> textObject_in_PG = alCityObjectInPGService.findByCodeAndTitle("textObject"," Text Image in hash puzzle group");


        ALCityInstanceInPL obj_1_1 = new ALCityInstanceInPL("obj_1_1",1,1,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_1_2 = new ALCityInstanceInPL("obj_1_2",1,2,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_1_3 = new ALCityInstanceInPL("obj_1_3",1,3,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_1 = new ALCityInstanceInPL("obj_2_1",2,1,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_2 = new ALCityInstanceInPL("obj_2_2",2,2,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_3 = new ALCityInstanceInPL("obj_2_3",2,3,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_1 = new ALCityInstanceInPL("obj_3_1",3,1,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_2 = new ALCityInstanceInPL("obj_3_2",3,2,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_3 = new ALCityInstanceInPL("obj_3_3",3,3,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_4_1 = new ALCityInstanceInPL("obj_4_1",4,1,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_4_2 = new ALCityInstanceInPL("obj_4_2",4,2,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_4_3 = new ALCityInstanceInPL("obj_4_3",4,3,0,textObject_in_PG.get(),pl_Memory_Game,1L,now,now,admin_1,admin_1);

        pgObjectInstanceService.save(obj_1_1);
        pgObjectInstanceService.save(obj_1_2);
        pgObjectInstanceService.save(obj_1_3);
        pgObjectInstanceService.save(obj_2_1);
        pgObjectInstanceService.save(obj_2_2);
        pgObjectInstanceService.save(obj_2_3);
        pgObjectInstanceService.save(obj_3_1);
        pgObjectInstanceService.save(obj_3_2);
        pgObjectInstanceService.save(obj_3_3);
        pgObjectInstanceService.save(obj_4_1);
        pgObjectInstanceService.save(obj_4_2);
        pgObjectInstanceService.save(obj_4_3);



        //Optional<ActionRenderer> show = actionRendererService.findByHandler("Show");
//        PuzzleObject_ObjectAction  textObject_Create = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Create,show.get(),1L,now,now,admin_1,admin_1);
//        puzzleObject_ObjectActionService.save(textObject_Create);
//
//        Attribute create_action_param_1 =new Attribute("text",textObject_Create.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(create_action_param_1);
//        AttributeValue  create_action_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,create_action_param_1,create_action_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(create_action_param_1_value);
//
//        Attribute create_action_param_2 =new Attribute("ObjectType",textObject_Create.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(create_action_param_2);
//        AttributeValue create_action_param_2_value= new AttributeValue(null,null,null,"TextObject",null,null,create_action_param_2,create_action_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(create_action_param_2_value);
//
//        PuzzleObject_ObjectAction  textObject_Show = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Show,show.get(),1L,now,now,admin_1,admin_1);
//        puzzleObject_ObjectActionService.save(textObject_Show);
//        Attribute show_action_param_1 =new Attribute("text",textObject_Show.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(show_action_param_1);
//        AttributeValue  show_action_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,show_action_param_1,show_action_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(show_action_param_1_value);
//
//        Attribute object_property_1 =new Attribute("text",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(object_property_1);
//        AttributeValue  object_property_1_value= new AttributeValue(null,null,null,"",null,null,object_property_1,object_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(object_property_1_value);
//
//
//        Attribute obj_1_1_property_1 =new Attribute("text",obj_1_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_1_1_property_1);
//        AttributeValue  obj_1_1_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_1_1_property_1,obj_1_1_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_1_1_property_1_value);
//
//        Attribute obj_1_2_property_1 =new Attribute("text",obj_1_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_1_2_property_1);
//        AttributeValue  obj_1_2_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_1_2_property_1,obj_1_2_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_1_2_property_1_value);
//
//        Attribute obj_1_3_property_1 =new Attribute("text",obj_1_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_1_3_property_1);
//        AttributeValue  obj_1_3_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_1_3_property_1,obj_1_3_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_1_3_property_1_value);
//
//        Attribute obj_2_1_property_1 =new Attribute("text",obj_2_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_2_1_property_1);
//        AttributeValue  obj_2_1_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_2_1_property_1,obj_2_1_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_2_1_property_1_value);
//
//        Attribute obj_2_2_property_1 =new Attribute("text",obj_2_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_2_2_property_1);
//        AttributeValue  obj_2_2_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_2_2_property_1,obj_2_2_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_2_2_property_1_value);
//
//        Attribute obj_2_3_property_1 =new Attribute("text",obj_2_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_2_3_property_1);
//        AttributeValue  obj_2_3_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_2_3_property_1,obj_2_3_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_2_3_property_1_value);
//
//        Attribute obj_3_1_property_1 =new Attribute("text",obj_3_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_3_1_property_1);
//        AttributeValue  obj_3_1_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_3_1_property_1,obj_3_1_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_3_1_property_1_value);
//
//        Attribute obj_3_2_property_1 =new Attribute("text",obj_3_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_3_2_property_1);
//        AttributeValue  obj_3_2_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_3_2_property_1,obj_3_2_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_3_2_property_1_value);
//
//        Attribute obj_3_3_property_1 =new Attribute("text",obj_3_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(obj_3_3_property_1);
//        AttributeValue  obj_3_3_property_1_value= new AttributeValue(null,null,null,"",null,null,obj_3_3_property_1,obj_3_3_property_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(obj_3_3_property_1_value);
//
//        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
//
//
//        StringBuffer    Click_NON_EMPTY_Cell_condition = new StringBuffer("unequal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
//        PLRule Click_NON_EMPTY_Cell_rule = new PLRule("Click NON_EMPTY Cell",1 ,Click_NON_EMPTY_Cell_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(Click_NON_EMPTY_Cell_rule);
//
//        PLRulePostAction Click_NON_EMPTY_Cell_rule_showMessage = new PLRulePostAction(new StringBuffer("Show Message"),1,"","",PLRulePostActionType.User_Alert,Click_NON_EMPTY_Cell_rule,1L,now,now,admin_1,admin_1);
//        plRulePostActionService.save(Click_NON_EMPTY_Cell_rule_showMessage);
//
////        Attribute win_rule_PostAction_1_param_1 =new Attribute("actionId",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
////        attributeService.save(win_rule_PostAction_1_param_1);
////
////        AttributeValue win_rule_PostAction_1_param_1_value= new AttributeValue(null,null,null,"show message action id",null,null,win_rule_PostAction_1_param_1,win_rule_PostAction_1_param_1,1L,now,now,admin_1,admin_1);
////        attributeValueService.save(win_rule_PostAction_1_param_1_value);
//
//
//
//        StringBuffer    X_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),X)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
//        PLRule X_Turn_rule = new PLRule("X Turn",1 ,X_Turn_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(X_Turn_rule);
//
//        StringBuffer    O_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),O)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
//        PLRule O_Turn_rule = new PLRule("O Turn",1 ,O_Turn_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(O_Turn_rule);
//
//        StringBuffer    Game_Finished_Error_condition = new StringBuffer("equal(BoardVar(finished),true)");
//        PLRule Game_Finished_Error_rule = new PLRule("Game Finished Error",1 ,Game_Finished_Error_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(Game_Finished_Error_rule);
//
//        StringBuffer    X_Win_Rule_condition = new StringBuffer("equal(BoardVar(finished),true)");
//        PLRule X_Win_Rule_rule = new PLRule("X Win Rule",1 ,X_Win_Rule_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(X_Win_Rule_rule);


    }





}
