package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
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
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.ApplicationMember_WalletItemService;
import com.alcity.service.users.WalletItemService;
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


@Order(value=6)
@Component
public class ImportXOProblemData_3 implements CommandLineRunner {

    @Autowired
    private ApplicationMemberService applicationMemberService;

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
    ActionRendererService actionRendererService;


    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;

    @Autowired
    ALCityObjectInPGService puzzleGroup_PuzzleObjectService;

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
    private ApplicationMember_WalletItemService applicationMember_walletItemService;


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

        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        ApplicationMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        ApplicationMember Alireza_Zare = applicationMemberService.findByUsername("alireza");

        LearningTopic X_O_Topic = learningTopicService.findByTitle("X-O Game");

         LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill matching = learningSkillService.findByValue("matching");
        WalletItem alCoin100WalletItem = walletItemService.findByValue("al_coin_100");
        WalletItem alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

//        Journey journey_1 = journeyService.findByTitle("Journey_1");
//        Journey journey_2 = journeyService.findByTitle("Journey_2");



        byte[] puzzle_Ground_Image_X_O = ImageUtil.getImage("src/main/resources/images/X-O Problem/","x-o-ground.png");
        BinaryContent puzzle_ground_binary_content_1 = new BinaryContent("puzzle ground for X-O Game",puzzle_Ground_Image_X_O,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_ground_binary_content_1);

        byte[] puzzle_group_Icon_1 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_1.png");
        BinaryContent puzzle_group_binary_content_1 = new BinaryContent("image_puzzle_group_x-o",puzzle_group_Icon_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_binary_content_1);

        byte[] text_object_content = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_binary_content = new BinaryContent("text_object_x-o",text_object_content,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(textObject_binary_content);

        ObjectCategory objectCategory_TextObject = objectCategoryService.findByValue("TextObject");

        Optional<PuzzleGroup>  IQ_Puzzle_Group =puzzleGroupService.findByTitle("Hash Image - Puzzle Group 1");

        ALCityObject textObject = new ALCityObject("TextObject",objectCategory_TextObject,textObject_binary_content,textObject_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(textObject);

        ALCityObjectInPG puzzleGroup_puzzleObject = new ALCityObjectInPG("Image Hash Puzzle Group with Text Object","X_O_textObject",IQ_Puzzle_Group.get(),textObject,1L,now,now,admin_1,admin_1);
        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);


        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_x_o = new PuzzleLevel(now,1L,"X-O","4500",10,14,5f,IQ_Puzzle_Group.get(),PLDifficulty.Easy,PLStatus.Ongoing,privacy_1,puzzle_group_binary_content_1,puzzle_group_binary_content_1,3L,now,now,admin_1,admin_1);
        puzzleLevelService.save(puzzleLevel_x_o);


        BinaryContent puzzle_group_1_binary_content = binaryContentService.findByfileName("image_puzzle_group_matematic");

        Integer xPos=1;
        Integer xRotation=0;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);


        PLGround puzzleLevel_X_O_ground = new PLGround(3,3,puzzleLevel_x_o,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleLevel_X_O_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_X_O_ground);

        PermitedPlayer player_1_puzzleLevel_X_O = new PermitedPlayer(Alireza_Zare,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_X_O);

        LearningContent learningContent_Mathcing=new LearningContent("help to IQ","this content is about X-O Games",puzzle_group_1_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Mathcing);

        LearningSubjectInPL puzzleLevelLearningTopic_1 = new LearningSubjectInPL(puzzleLevel_x_o,X_O_Topic,learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,puzzleLevel_x_o,now,100,10f,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PuzzleSkillLearningContent puzzleSkillLearningContent_1 = new PuzzleSkillLearningContent(matching,IQ_Puzzle_Group.get(),learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_x_o,GameStatus.gameStatus_1,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("(equal(InstProp(InstByPos(2, 2),text),X) & " +
                "( (equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(2, 3),text),X)) |"+
                        "(equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(3, 2),text),X)) |   " +
                        " (equal(InstProp(InstByPos(1, 1),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(1, 3),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))| "+
                        "(equal(InstProp(InstByPos(1, 1),text),X) & ((equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))|(equal(InstProp(InstByPos(3, 3),text),X) & " +
                "((equal(InstProp(InstByPos(2, 3),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(3, 1),text),X)&equal(InstProp(InstByPos(3, 2),text),X))))");

        PLObjective puzzleLevelObjective_1 = new PLObjective("X won","X won the game",1.5f,2f,condition_Objective_1,matching,
                                                                 alCoin10WalletItem,puzzleLevel_x_o ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("(equal(InstProp(InstByPos(2, 2),text),O) & " +
                "( (equal(InstProp(InstByPos(2, 1),text),O)& equal(InstProp(InstByPos(2, 3),text),O)) | " +
                " (equal(InstProp(InstByPos(1, 2),text),O)& equal(InstProp(InstByPos(3, 2),text),O)) " +
                "| (equal(InstProp(InstByPos(1, 1),text),O)& equal(InstProp(InstByPos(3, 3),text),O))" +
                " |(equal(InstProp(InstByPos(1, 3),text),O)& equal(InstProp(InstByPos(3, 1),text),O))))|" +
                " (equal(InstProp(InstByPos(1, 1),text),O) & ((equal(InstProp(InstByPos(1, 2),text),O) &" +
                " equal(InstProp(InstByPos(1, 3),text),O))" +
                " |(equal(InstProp(InstByPos(2, 1),text),O)& equal(InstProp(InstByPos(3, 1),text),O))))|" +
                "(equal(InstProp(InstByPos(3, 3),text),O) & ((equal(InstProp(InstByPos(2, 3),text),O) & " +
                "equal(InstProp(InstByPos(1, 3),text),O)) " +
                "|(equal(InstProp(InstByPos(3, 1),text),O)& equal(InstProp(InstByPos(3, 2),text),O))))");

        PLObjective puzzleLevelObjective_2 = new PLObjective("O won","O won the game",5.5f,5f,condition_Objective_2,timeManagement,
                alCoin10WalletItem,puzzleLevel_x_o ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_2);


        Attribute attribute_variable_Turn =new Attribute("Turn",puzzleLevel_x_o.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_Turn);
        AttributeValue attribute_variable_Turn_value= new AttributeValue(null,null,null,"X",null,null,null,attribute_variable_Turn,attribute_variable_Turn,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_Turn_value);

        Attribute attribute_variable_Finished =new Attribute("Finished",puzzleLevel_x_o.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_Finished);

        AttributeValue attribute_variable_Finished_value= new AttributeValue(false,null,null,null,null,null,null,attribute_variable_Finished,attribute_variable_Finished,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_Finished_value);

        ALCityInstanceInPL obj_1_1 = new ALCityInstanceInPL("obj_1_1",1,1,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_1_2 = new ALCityInstanceInPL("obj_1_2",1,2,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_1_3 = new ALCityInstanceInPL("obj_1_3",1,3,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_1 = new ALCityInstanceInPL("obj_2_1",2,1,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_2 = new ALCityInstanceInPL("obj_2_2",2,2,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_2_3 = new ALCityInstanceInPL("obj_2_3",2,3,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_1 = new ALCityInstanceInPL("obj_3_1",3,1,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_2 = new ALCityInstanceInPL("obj_3_2",3,2,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL obj_3_3 = new ALCityInstanceInPL("obj_3_3",3,3,0,puzzleGroup_puzzleObject,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);

        pgObjectInstanceService.save(obj_1_1);
        pgObjectInstanceService.save(obj_1_2);
        pgObjectInstanceService.save(obj_1_3);
        pgObjectInstanceService.save(obj_2_1);
        pgObjectInstanceService.save(obj_2_2);
        pgObjectInstanceService.save(obj_2_3);
        pgObjectInstanceService.save(obj_3_1);
        pgObjectInstanceService.save(obj_3_2);
        pgObjectInstanceService.save(obj_3_3);

        Optional<ActionRenderer> show = actionRendererService.findByHandler("Show");
        PuzzleObject_ObjectAction  textObject_Create = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Create,show.get(),1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(textObject_Create);

        Attribute create_action_param_1 =new Attribute("text",textObject_Create.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(create_action_param_1);
        AttributeValue  create_action_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,null,create_action_param_1,create_action_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(create_action_param_1_value);

        Attribute create_action_param_2 =new Attribute("ObjectType",textObject_Create.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(create_action_param_2);
        AttributeValue create_action_param_2_value= new AttributeValue(null,null,null,"TextObject",null,null,null,create_action_param_2,create_action_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(create_action_param_2_value);

        PuzzleObject_ObjectAction  textObject_Show = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Show,show.get(),1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(textObject_Show);
        Attribute show_action_param_1 =new Attribute("text",textObject_Show.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(show_action_param_1);
        AttributeValue  show_action_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,null,show_action_param_1,show_action_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(show_action_param_1_value);

        Attribute object_property_1 =new Attribute("text",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(object_property_1);
        AttributeValue  object_property_1_value= new AttributeValue(null,null,null,"",null,null,null,object_property_1,object_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(object_property_1_value);


        Attribute obj_1_1_property_1 =new Attribute("text",obj_1_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_1_1_property_1);
        AttributeValue  obj_1_1_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_1_1_property_1,obj_1_1_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_1_1_property_1_value);

        Attribute obj_1_2_property_1 =new Attribute("text",obj_1_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_1_2_property_1);
        AttributeValue  obj_1_2_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_1_2_property_1,obj_1_2_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_1_2_property_1_value);

        Attribute obj_1_3_property_1 =new Attribute("text",obj_1_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_1_3_property_1);
        AttributeValue  obj_1_3_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_1_3_property_1,obj_1_3_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_1_3_property_1_value);

        Attribute obj_2_1_property_1 =new Attribute("text",obj_2_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_2_1_property_1);
        AttributeValue  obj_2_1_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_2_1_property_1,obj_2_1_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_2_1_property_1_value);

        Attribute obj_2_2_property_1 =new Attribute("text",obj_2_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_2_2_property_1);
        AttributeValue  obj_2_2_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_2_2_property_1,obj_2_2_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_2_2_property_1_value);

        Attribute obj_2_3_property_1 =new Attribute("text",obj_2_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_2_3_property_1);
        AttributeValue  obj_2_3_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_2_3_property_1,obj_2_3_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_2_3_property_1_value);

        Attribute obj_3_1_property_1 =new Attribute("text",obj_3_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_3_1_property_1);
        AttributeValue  obj_3_1_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_3_1_property_1,obj_3_1_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_3_1_property_1_value);

        Attribute obj_3_2_property_1 =new Attribute("text",obj_3_2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_3_2_property_1);
        AttributeValue  obj_3_2_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_3_2_property_1,obj_3_2_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_3_2_property_1_value);

        Attribute obj_3_3_property_1 =new Attribute("text",obj_3_3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(obj_3_3_property_1);
        AttributeValue  obj_3_3_property_1_value= new AttributeValue(null,null,null,"",null,null,null,obj_3_3_property_1,obj_3_3_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(obj_3_3_property_1_value);

        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRuleEvent> Internal_Event = plRuleEventService.findByName("Internal_Event");


        StringBuffer    Click_NON_EMPTY_Cell_condition = new StringBuffer("unequal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule Click_NON_EMPTY_Cell_rule = new PLRule("Click NON_EMPTY Cell",1 ,Click_NON_EMPTY_Cell_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Click_NON_EMPTY_Cell_rule);

        PLRulePostAction Click_NON_EMPTY_Cell_rule_showMessage = new PLRulePostAction(Click_NON_EMPTY_Cell_rule,PLRulePostActionType.User_Alert,0,"","",
                "",new StringBuffer(""),"error","Cell is not empty!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(Click_NON_EMPTY_Cell_rule_showMessage);

        StringBuffer    X_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),X)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule X_Turn_rule = new PLRule("X Turn",1 ,X_Turn_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(X_Turn_rule);

        PLRulePostAction  X_Turn_post_Action_1 = new PLRulePostAction(X_Turn_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "InstProp(InstByPos(EventParam(row), EventParam(col)), text)",new StringBuffer("X"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_1);

        PLRulePostAction X_Turn_post_Action_2 = new PLRulePostAction(X_Turn_rule,PLRulePostActionType.Call_Object_Action,0,"show","InstByPos(EventParam(row), EventParam(col))",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_2);

        PLRulePostAction X_Turn_post_Action_3 = new PLRulePostAction(X_Turn_rule,PLRulePostActionType.Fire_Event,0,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_3);

        PLRulePostAction X_Turn_post_Action_4 = new PLRulePostAction(X_Turn_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "BoardVar(turn)",new StringBuffer("O"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_4);



        StringBuffer    O_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),O)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule O_Turn_rule = new PLRule("O Turn",1 ,O_Turn_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(O_Turn_rule);

        PLRulePostAction  O_Turn_post_Action_1 = new PLRulePostAction(O_Turn_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "InstProp(InstByPos(EventParam(row), EventParam(col)), text)",new StringBuffer("O"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_1);

        PLRulePostAction O_Turn_post_Action_2 = new PLRulePostAction(O_Turn_rule,PLRulePostActionType.Call_Object_Action,0,"show","InstByPos(EventParam(row), EventParam(col))",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_2);

        PLRulePostAction O_Turn_post_Action_3 = new PLRulePostAction(O_Turn_rule,PLRulePostActionType.Fire_Event,0,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_3);

        PLRulePostAction O_Turn_post_Action_4 = new PLRulePostAction(O_Turn_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "BoardVar(turn)",new StringBuffer("X"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_4);


        StringBuffer    Game_Finished_Error_condition = new StringBuffer("equal(BoardVar(finished),true)");
        PLRule Game_Finished_Error_rule = new PLRule("Game Finished Error",1 ,Game_Finished_Error_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Game_Finished_Error_rule);

        PLRulePostAction Game_Finished_Post_Action_1 = new PLRulePostAction(Game_Finished_Error_rule,PLRulePostActionType.User_Alert,0,"","",
                "",new StringBuffer(""),"info","Game has finished!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(Game_Finished_Post_Action_1);


        StringBuffer    X_Win_Rule_condition = new StringBuffer("equal(BoardVar(finished),true)");
        PLRule X_Win_Rule_rule = new PLRule("X Win Rule",1 ,X_Win_Rule_condition,puzzleLevel_x_o,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(X_Win_Rule_rule);

        PLRulePostAction X_Win_Rule_rule_post_Action_1 = new PLRulePostAction(X_Win_Rule_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Win_Rule_rule_post_Action_1);


        PLRulePostAction X_Win_Rule_rule_post_Action_2 = new PLRulePostAction(X_Win_Rule_rule,PLRulePostActionType.User_Alert,0,"","",
                "",new StringBuffer(""),"info","X won!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Win_Rule_rule_post_Action_2);


        StringBuffer    O_Win_Rule_condition = new StringBuffer("equal(BoardVar(finished),true)");
        PLRule O_Win_Rule_rule = new PLRule("O Win Rule",1 ,O_Win_Rule_condition,puzzleLevel_x_o,Internal_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(O_Win_Rule_rule);

        PLRulePostAction O_Win_Rule_rule_post_Action_1 = new PLRulePostAction(O_Win_Rule_rule,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Win_Rule_rule_post_Action_1);


        PLRulePostAction O_Win_Rule_rule_post_Action_2 = new PLRulePostAction(O_Win_Rule_rule,PLRulePostActionType.User_Alert,0,"","",
                "",new StringBuffer(""),"info","O Won!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Win_Rule_rule_post_Action_2);



    }





}