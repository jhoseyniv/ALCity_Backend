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
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import com.alcity.utility.ToolBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;


@Order(value=6)
@Component
public class ImportXOProblemData_3 implements CommandLineRunner {

    @Autowired
    private AppMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;


    @Autowired
    PLGroundService plGroundService;
    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    private PGService pgService;


    @Autowired
    private PLPrivacyService plPrivacyService;

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
    InstanceInPLService objectInstanceService;

    @Autowired
    PLRulePostActionTypeService plRulePostActionTypeService;

    @Autowired
    ActionService actionService;


    @Autowired
    PLRuleEventService plRuleEventService;


    @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    RendererService actionRendererService;

    @Autowired
    ObjectInPGService objectInPGService;

    @Autowired
    AttributeService attributeService;

    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    ObjectService objectService;

    @Autowired
    PLRuleService puzzleLevelRuleService;

    @Autowired
    PLRulePostActionService plRulePostActionService;


      @Autowired
    PLObjectiveService plObjectiveService;


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

        Optional<AppMember> admin_1Optional = applicationMemberService.findByUsername("admin");
        Optional<AppMember> jalalHoseyniOptional = applicationMemberService.findByUsername("jalal");
        Optional<AppMember> Alireza_ZareOptional = applicationMemberService.findByUsername("alireza");
        Optional<LearningTopic> hashImage_Topic = learningTopicService.findByTitle("Hash Image");
        AppMember  admin_1=admin_1Optional.get();
        AppMember jalalHoseyni = jalalHoseyniOptional.get();
        AppMember Alireza_Zare = Alireza_ZareOptional.get();


        Optional<LearningTopic> X_O_Topic = learningTopicService.findByTitle("X-O Game");

         LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill matching = learningSkillService.findByValue("matching");
        Optional<WalletItem> alCoin100WalletItem = walletItemService.findByValue("al_coin_100");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

        byte[] plGroundImage = ImageUtil.getImage("src/main/resources/images/X-O Problem/","x-o-ground.json");
        byte[] pgIcon = ImageUtil.getImage("src/main/resources/images/","puzzle_group_1.png");
        BinaryContent pgIcon_bc = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_x-o ",pgIcon.length,pgIcon,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pgIcon_bc);

        Optional<PuzzleGroup>  IQ_Puzzle_Group =pgService.findByTitle("IQ Puzzle Group");

        Optional<ALCityObject>  textObject = objectService.findByTitle("TextObject");

        ALCityObjectInPG textObject_In_IQ_Puzzle_Group = new ALCityObjectInPG(" Text Image in hash puzzle group","TextObject",IQ_Puzzle_Group.get(),textObject.get(),1L,now,now,admin_1,admin_1);
        objectInPGService.save(textObject_In_IQ_Puzzle_Group);

        DTOUtil.copyActionFromTo(textObject.get().getId(), textObject_In_IQ_Puzzle_Group.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);


        PLPrivacy privacy_1 = plPrivacyService.findByValue("Public");

        PuzzleLevel puzzleLevel = new PuzzleLevel(admin_1,now,1L,"X-O","4500",8,15,10f,3f,5f,8f,IQ_Puzzle_Group.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,3L,now,now,admin_1,admin_1);
        puzzleLevel.setIcon(pgIcon_bc);
        puzzleLevel.setPicture(pgIcon_bc);
        puzzleLevelService.save(puzzleLevel);

        BinaryContent puzzle_group_1_binary_content = binaryContentService.findByfileName("image_puzzle_group_matematic");

        Integer xPos=1;
        Integer xRotation=0;

        PLGround plGround = new PLGround(3,3,xPos,xPos,xPos,xRotation,xRotation,xRotation,puzzleLevel, plGroundImage,1L,now,now,admin_1,admin_1);
        plGroundService.save(plGround);

        PermitedPlayer player_1 = new PermitedPlayer(Alireza_Zare,puzzleLevel,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1);

        LearningContent learningContent_Mathcing=new LearningContent("help to IQ","this content is about X-O Games",puzzle_group_1_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Mathcing);

        LearningTopicInPL puzzleLevelLearningTopic_1 = new LearningTopicInPL(puzzleLevel,X_O_Topic.get(),learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

       Float playScore =10f;
       Integer stars = ToolBox.getPuzzleLevelStars(playScore,puzzleLevel.getFirstStarScore(),puzzleLevel.getSecondStarScore(),puzzleLevel.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,puzzleLevel,now,now,85L,playScore,stars,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        LearningSkillContent puzzleSkillLearningContent_1 = new LearningSkillContent(matching,IQ_Puzzle_Group.get(),learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel,now,now,GameStatus.Playing,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("(equal(InstProp(InstByPos(1, 1),text),X) & ( (equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) | (equal(InstProp(InstByPos(2, 1),text),X) & ( (equal(InstProp(InstByPos(2, 2),text),X)&equal(InstProp(InstByPos(2, 3),text),X)) |  (equal(InstProp(InstByPos(3, 1),text),X) & ( (equal(InstProp(InstByPos(3, 2),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) |  (equal(InstProp(InstByPos(1, 1),text),X) & ( (equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(3, 1),text),X)) |  (equal(InstProp(InstByPos(1, 2),text),X) & ( (equal(InstProp(InstByPos(2, 2),text),X)&equal(InstProp(InstByPos(3, 2),text),X)) |  (equal(InstProp(InstByPos(1, 3),text),X) & ( (equal(InstProp(InstByPos(2, 3),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) |  (equal(InstProp(InstByPos(1, 1),text),X) & ( (equal(InstProp(InstByPos(2, 2),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) |  (equal(InstProp(InstByPos(1, 3),text),X) & ( (equal(InstProp(InstByPos(2, 2),text),X)&equal(InstProp(InstByPos(3, 1),text),X))");

        PLObjective puzzleLevelObjective_1 = new PLObjective("X won","X won the game",1.5f,2f,condition_Objective_1,matching,
                                                                 alCoin10WalletItem.get(),puzzleLevel ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("(equal(InstProp(InstByPos(1, 1),text),O) & ( (equal(InstProp(InstByPos(1, 2),text),O)&equal(InstProp(InstByPos(1, 3),text),O)) | (equal(InstProp(InstByPos(2, 1),text),O) & ( (equal(InstProp(InstByPos(2, 2),text),O)&equal(InstProp(InstByPos(2, 3),text),O)) |  (equal(InstProp(InstByPos(3, 1),text),O) & ( (equal(InstProp(InstByPos(3, 2),text),O)&equal(InstProp(InstByPos(3, 3),text),O)) |  (equal(InstProp(InstByPos(1, 1),text),O) & ( (equal(InstProp(InstByPos(2, 1),text),O)&equal(InstProp(InstByPos(3, 1),text),O)) |  (equal(InstProp(InstByPos(1, 2),text),O) & ( (equal(InstProp(InstByPos(2, 2),text),O)&equal(InstProp(InstByPos(3, 2),text),O)) |  (equal(InstProp(InstByPos(1, 3),text),O) & ( (equal(InstProp(InstByPos(2, 3),text),O)&equal(InstProp(InstByPos(3, 3),text),O)) |  (equal(InstProp(InstByPos(1, 1),text),O) & ( (equal(InstProp(InstByPos(2, 2),text),O)&equal(InstProp(InstByPos(3, 3),text),O)) |  (equal(InstProp(InstByPos(1, 3),text),O) & ( (equal(InstProp(InstByPos(2, 2),text),O)&equal(InstProp(InstByPos(3, 1),text),O))");

        PLObjective puzzleLevelObjective_2 = new PLObjective("O won","O won the game",5.5f,5f,condition_Objective_2,timeManagement,
                alCoin10WalletItem.get(),puzzleLevel ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_2);

        Attribute attribute_variable_turn =new Attribute("turn",puzzleLevel.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_turn);
        AttributeValue attribute_variable_turn_value= new AttributeValue(null,null,null,"X",null,null,null,null,Boolean.FALSE,null,attribute_variable_turn,1L,now,now,admin_1,admin_1,puzzleLevel.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(attribute_variable_turn_value);

        Attribute attribute_variable_Finished =new Attribute("finished",puzzleLevel.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_Finished);

        AttributeValue attribute_variable_Finished_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,attribute_variable_Finished,1L,now,now,admin_1,admin_1,puzzleLevel.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(attribute_variable_Finished_value);


        ALCityInstanceInPL obj_1_1 = new ALCityInstanceInPL("obj_1_1",1,1,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_1_1);

        ALCityInstanceInPL obj_1_2 = new ALCityInstanceInPL("obj_1_2",1,2,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_1_2);

        ALCityInstanceInPL obj_1_3 = new ALCityInstanceInPL("obj_1_3",1,3,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_1_3);

        ALCityInstanceInPL obj_2_1 = new ALCityInstanceInPL("obj_2_1",2,1,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_2_1);

        ALCityInstanceInPL obj_2_2 = new ALCityInstanceInPL("obj_2_2",2,2,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_2_2);

        ALCityInstanceInPL obj_2_3 = new ALCityInstanceInPL("obj_2_3",2,3,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_2_3);

        ALCityInstanceInPL obj_3_1 = new ALCityInstanceInPL("obj_3_1",3,1,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_3_1);

        ALCityInstanceInPL obj_3_2 = new ALCityInstanceInPL("obj_3_2",3,2,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_3_2);

        ALCityInstanceInPL obj_3_3 = new ALCityInstanceInPL("obj_3_3",3,3,0,textObject_In_IQ_Puzzle_Group,puzzleLevel,1L,now,now,admin_1,admin_1);
        objectInstanceService.save(obj_3_3);


        Attribute alCity_object_property_1 =new Attribute("text",textObject_In_IQ_Puzzle_Group.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(alCity_object_property_1);
        AttributeValue  object_property_1_value= new AttributeValue(null,null,null,"",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,textObject_In_IQ_Puzzle_Group.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(object_property_1_value);

//        AttributeValue  obj_1_1_property_1_value= new AttributeValue(null,null,null,"text in obj_1_1",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_1_1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_1_1_property_1_value);
//
//         AttributeValue  obj_1_2_property_1_value= new AttributeValue(null,null,null,"text in obj_1_2",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_1_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_1_2_property_1_value);
//
//         AttributeValue  obj_1_3_property_1_value= new AttributeValue(null,null,null,"text in obj_1_3",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_1_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_1_3_property_1_value);
//
//         AttributeValue  obj_2_1_property_1_value= new AttributeValue(null,null,null,"text in obj_2_1",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_2_1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_2_1_property_1_value);
//
//        AttributeValue  obj_2_2_property_1_value= new AttributeValue(null,null,null,"text in obj_2_2",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_2_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_2_2_property_1_value);
//
//         AttributeValue  obj_2_3_property_1_value= new AttributeValue(null,null,null,"text in obj_2_3",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_2_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_2_3_property_1_value);
//
//         AttributeValue  obj_3_1_property_1_value= new AttributeValue(null,null,null,"text in obj_3_1",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_3_1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_3_1_property_1_value);
//
//        AttributeValue  obj_3_2_property_1_value= new AttributeValue(null,null,null,"text in obj_3_2",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_3_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_3_2_property_1_value);
//
//        AttributeValue  obj_3_3_property_1_value= new AttributeValue(null,null,null,"text in obj_3_3",null,null,null,null,Boolean.FALSE,null,alCity_object_property_1,1L,now,now,admin_1,admin_1,obj_3_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(obj_3_3_property_1_value);


        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRuleEvent> Internal_Event = plRuleEventService.findByName("internalevent");

        Optional<PLRulePostActionType> CallObjectAction = plRulePostActionTypeService.findByValue("CallObjectAction");
        Optional<PLRulePostActionType> UserAlertAction = plRulePostActionTypeService.findByValue("UserAlertAction");
        Optional<PLRulePostActionType> VariableAssignmentAction = plRulePostActionTypeService.findByValue("VariableAssignmentAction");
        Optional<PLRulePostActionType> FireEventAction = plRulePostActionTypeService.findByValue("FireEventAction");

        Boolean ignoreRemaining = true;
        StringBuffer    Click_NON_EMPTY_Cell_condition = new StringBuffer("unequal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule Click_NON_EMPTY_Cell_rule = new PLRule("Click NON_EMPTY Cell",1 ,Click_NON_EMPTY_Cell_condition,ignoreRemaining,puzzleLevel,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Click_NON_EMPTY_Cell_rule);

        PLRulePostAction Click_NON_EMPTY_Cell_rule_showMessage = new PLRulePostAction(Click_NON_EMPTY_Cell_rule,UserAlertAction.get(),0,"","",
                "",new StringBuffer(""),"error","Cell is not empty!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(Click_NON_EMPTY_Cell_rule_showMessage);

        StringBuffer    X_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),X)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule X_Turn_rule = new PLRule("X Turn",1 ,X_Turn_condition,ignoreRemaining,puzzleLevel,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(X_Turn_rule);

        PLRulePostAction  X_Turn_post_Action_1 = new PLRulePostAction(X_Turn_rule,VariableAssignmentAction.get(),0,"","",
                "InstProp(InstByPos(EventParam(row), EventParam(col)), text)",new StringBuffer("X"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_1);

        PLRulePostAction X_Turn_post_Action_2 = new PLRulePostAction(X_Turn_rule,CallObjectAction.get(),0,"show","InstByPos(EventParam(row), EventParam(col))",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_2);

        PLRulePostAction X_Turn_post_Action_3 = new PLRulePostAction(X_Turn_rule,FireEventAction.get(),0,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_3);

        PLRulePostAction X_Turn_post_Action_4 = new PLRulePostAction(X_Turn_rule,VariableAssignmentAction.get(),0,"","",
                "BoardVar(turn)",new StringBuffer("O"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Turn_post_Action_4);


        StringBuffer    O_Turn_condition = new StringBuffer("unequal(BoardVar(finished),true) & equal(BoardVar(turn),O)&equal(InstProp(InstByPos(EventParam(row), EventParam(col)), text),null)");
        PLRule O_Turn_rule = new PLRule("O Turn",1 ,O_Turn_condition,ignoreRemaining,puzzleLevel,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(O_Turn_rule);

        PLRulePostAction  O_Turn_post_Action_1 = new PLRulePostAction(O_Turn_rule,VariableAssignmentAction.get(),0,"","",
                "InstProp(InstByPos(EventParam(row), EventParam(col)), text)",new StringBuffer("O"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_1);

        PLRulePostAction O_Turn_post_Action_2 = new PLRulePostAction(O_Turn_rule,CallObjectAction.get(),0,"show","InstByPos(EventParam(row), EventParam(col))",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_2);

        PLRulePostAction O_Turn_post_Action_3 = new PLRulePostAction(O_Turn_rule,FireEventAction.get(),0,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_3);

        PLRulePostAction O_Turn_post_Action_4 = new PLRulePostAction(O_Turn_rule,VariableAssignmentAction.get(),0,"","",
                "BoardVar(turn)",new StringBuffer("X"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Turn_post_Action_4);


        StringBuffer    Game_Finished_Error_condition = new StringBuffer("equal(BoardVar(finished),true)");
        PLRule Game_Finished_Error_rule = new PLRule("Game Finished Error",1 ,Game_Finished_Error_condition,ignoreRemaining,puzzleLevel,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Game_Finished_Error_rule);

        PLRulePostAction Game_Finished_Post_Action_1 = new PLRulePostAction(Game_Finished_Error_rule,UserAlertAction.get(),0,"","",
                "",new StringBuffer(""),"info","Game has finished!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(Game_Finished_Post_Action_1);


        StringBuffer    X_Win_Rule_condition = new StringBuffer("(equal(InstProp(InstByPos(2, 2),text),X) & ( (equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(2, 3),text),X)) |  (equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(3, 2),text),X)) |      (equal(InstProp(InstByPos(1, 1),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) |(equal(InstProp(InstByPos(1, 3),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))|(equal(InstProp(InstByPos(1, 1),text),X) & ((equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) |(equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))|(equal(InstProp(InstByPos(3, 3),text),X) & ((equal(InstProp(InstByPos(2, 3),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) |(equal(InstProp(InstByPos(3, 1),text),X)&equal(InstProp(InstByPos(3, 2),text),X))))");
        PLRule X_Win_Rule_rule = new PLRule("X Win Rule",1 ,X_Win_Rule_condition,ignoreRemaining,puzzleLevel,Internal_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(X_Win_Rule_rule);

        PLRulePostAction X_Win_Rule_rule_post_Action_1 = new PLRulePostAction(X_Win_Rule_rule,VariableAssignmentAction.get(),0,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Win_Rule_rule_post_Action_1);

        PLRulePostAction X_Win_Rule_rule_post_Action_2 = new PLRulePostAction(X_Win_Rule_rule,UserAlertAction.get(),0,"","",
                "",new StringBuffer(""),"info","X won!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(X_Win_Rule_rule_post_Action_2);

        StringBuffer    O_Win_Rule_condition = new StringBuffer("condition\": \"(equal(InstProp(InstByPos(2, 2),text),O) & ( (equal(InstProp(InstByPos(2, 1),text),O)&equal(InstProp(InstByPos(2, 3),text),O)) |  (equal(InstProp(InstByPos(1, 2),text),O)&equal(InstProp(InstByPos(3, 2),text),O)) |      (equal(InstProp(InstByPos(1, 1),text),O)&equal(InstProp(InstByPos(3, 3),text),O)) |(equal(InstProp(InstByPos(1, 3),text),O)&equal(InstProp(InstByPos(3, 1),text),O))))|(equal(InstProp(InstByPos(1, 1),text),O) & ((equal(InstProp(InstByPos(1, 2),text),O)&equal(InstProp(InstByPos(1, 3),text),O)) |(equal(InstProp(InstByPos(2, 1),text),O)&equal(InstProp(InstByPos(3, 1),text),O))))|(equal(InstProp(InstByPos(3, 3),text),O) & ((equal(InstProp(InstByPos(2, 3),text),O)&equal(InstProp(InstByPos(1, 3),text),O)) |(equal(InstProp(InstByPos(3, 1),text),O)&equal(InstProp(InstByPos(3, 2),text),O))))");
        PLRule O_Win_Rule_rule = new PLRule("O Win Rule",1 ,O_Win_Rule_condition,ignoreRemaining,puzzleLevel,Internal_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(O_Win_Rule_rule);

        PLRulePostAction O_Win_Rule_rule_post_Action_1 = new PLRulePostAction(O_Win_Rule_rule,VariableAssignmentAction.get(),0,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Win_Rule_rule_post_Action_1);


        PLRulePostAction O_Win_Rule_rule_post_Action_2 = new PLRulePostAction(O_Win_Rule_rule,UserAlertAction.get(),0,"","",
                "",new StringBuffer(""),"info","O Won!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(O_Win_Rule_rule_post_Action_2);



    }





}
