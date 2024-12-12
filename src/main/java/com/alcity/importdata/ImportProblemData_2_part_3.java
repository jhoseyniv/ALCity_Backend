package com.alcity.importdata;


import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.base.PLRulePostActionType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.alenum.DataType;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.WalletItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


@Order(value=5)
@Component
public class ImportProblemData_2_part_3 implements CommandLineRunner {

    @Autowired
    private AppMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;

     @Autowired
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;

    @Autowired
    PLLearningTopicService plLearningTopicService;
    @Autowired
    PlayHistoryService playHistoryService;
    @Autowired
    PLGameInstanceService plGameInstanceService;

    @Autowired
    LearningSkillService learningSkillService;
    @Autowired
    WalletItemService walletItemService;
    @Autowired
    JourneyService journeyService;
    @Autowired
    private PuzzleCategoryService puzzleCategoryService;
    @Autowired
    private PGService puzzleGroupService;
    @Autowired
    private PLPrivacyService puzzleLevelPrivacyService;
    @Autowired
    PuzzleLevelService puzzleLevelService;
    @Autowired
    private CameraSetupService cameraSetupService;
    @Autowired
    PLGroundService puzzleLevelGroundService;
    @Autowired
    PermitedPlayerRepository permitedPlayerRepository;
    @Autowired
    PuzzleSkillLearningContentService puzzleSkillLearningContentService;
    @Autowired
    ObjectCategoryService objectCategoryService;
    @Autowired
    ALCityObjectService puzzleObjectService;
    @Autowired
    ALCityObjectInPGService puzzleGroup_PuzzleObjectService;

    @Autowired
    PLObjectiveService plObjectiveService;
    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    ALCityInstanceInPLService pgObjectInstanceService;

    @Autowired
    AttributeService attributeService;
    @Autowired
    RendererService actionRendererService;
    @Autowired
    ClientTypeService clientTypeService;
    @Autowired
    PuzzleObjectActionService puzzleObject_ObjectActionService;
   @Autowired
   PLRuleService plRuleService;

   @Autowired
   PLRuleEventService plRuleEventService;
 @Autowired
 PLRulePostActionService plRulePostActionService;

 private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 2");
        System.out.println("...Import Problem 2");

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);


        AppMember admin_1 = applicationMemberService.findByUsername("admin");
        AppMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        AppMember moslemBalavandi = applicationMemberService.findByUsername("moslem");
        AppMember alirezaZarei = applicationMemberService.findByUsername("alireza");
        Optional<LearningTopic> routing_in_the_table = learningTopicService.findByTitle("Routing in the Table");


         LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill routing = learningSkillService.findByValue("routing");
        LearningSkill find_paths = learningSkillService.findByValue("Find Paths");

        Optional<WalletItem> alCoin_100_WalletItem = walletItemService.findByValue("al_coin_100");
        Optional<Journey> journey_1 = journeyService.findByTitle("Journey_1");

        PuzzleCategory maze = puzzleCategoryService.findByValue("Maze");

        ObjectCategory objectCategory_Image = objectCategoryService.findByValue("Image");

        BinaryContent img_Image_binary_content = binaryContentService.findByfileName("image_object");
        BinaryContent yellow_space_image  = binaryContentService.findByfileName("yellow_space_image");
        BinaryContent black_space_image  = binaryContentService.findByfileName("black_space_image");
        BinaryContent white_space_image  = binaryContentService.findByfileName("white_space_image");
      // ALCityObjectInPG puzzleGroup_puzzleObject =  puzzleGroup_PuzzleObjectService.findByCode("Maze_ImageObject");

        PuzzleLevel   puzzleLevel_Maze = puzzleLevelService.findByCode("4546");

        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");


     StringBuffer    pr_select_object_Condition = new StringBuffer("(Object[e.x, e.y].Locked == false)&&(Object[e.x, e.y].Selected == false)&&(((e.x==LastX)&&(e.y==LastY+1))||((e.x==LastX)&&(e.y==LastY-1))||((e.x==LastX+1)&&(e.y==LastY))||((e.x==LastX-1)&&(e.y==LastY)))");
     PLRule rule_Select_object   = new PLRule("Select object",1 ,pr_select_object_Condition,puzzleLevel_Maze,click_event.get(),1L,now,now,admin_1,admin_1);
     plRuleService.save(rule_Select_object);

     StringBuffer assignmentActionExpression=new StringBuffer(" " );

     PLRulePostAction rulePostAction_1_assignment = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
              "Object[e.x, e.y].PreX",new StringBuffer("LastX"),"","",1L ,now,now,admin_1,admin_1);
     plRulePostActionService.save(rulePostAction_1_assignment);



     PLRulePostAction rulePostAction_2_assignment = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
             "Object[e.x, e.y].PreY",new StringBuffer("LastY"),"","",1L ,now,now,admin_1,admin_1);
     plRulePostActionService.save(rulePostAction_2_assignment);


     PLRulePostAction rulePostAction_3_assignment = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
             "Object[e.x, e.y].Selected",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
     plRulePostActionService.save(rulePostAction_3_assignment);



        PLRulePostAction rulePostAction_4_assignment = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "LastX",new StringBuffer("e.x"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rulePostAction_4_assignment);


        PLRulePostAction rulePostAction_5_assignment = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "LastY",new StringBuffer("e.y"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rulePostAction_5_assignment);


        PLRulePostAction rulePostAction_6_assignment =new PLRulePostAction(rule_Select_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "PathLen",new StringBuffer("PathLen+1"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rulePostAction_6_assignment);


        PLRulePostAction rulePostAction_7_hide = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Call_Object_Action,0,"hide","objects[e.x][e.y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rulePostAction_7_hide);

        Attribute rulePostAction_7_hide_param_1 =new Attribute("actionId",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_1);

        AttributeValue rulePostAction_7_hide_param_1_value= new AttributeValue(null,null,null,"show action id",null,null,null,rulePostAction_7_hide_param_1,rulePostAction_7_hide_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_1_value);

        Attribute rulePostAction_7_hide_param_2 =new Attribute("aSync",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_2);

        AttributeValue rulePostAction_7_hide_param_2_value= new AttributeValue(false,null,null,null,null,null,null,rulePostAction_7_hide_param_2,rulePostAction_7_hide_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_2_value);

        Attribute rulePostAction_7_hide_param_3 =new Attribute("graphic",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_3);

        AttributeValue rulePostAction_7_hide_param_3_value= new AttributeValue(null,null,null,null,null,null,null,rulePostAction_7_hide_param_3,rulePostAction_7_hide_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_3_value);

        Attribute rulePostAction_7_hide_param_4 =new Attribute("ObjectId",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_4);

        AttributeValue rulePostAction_7_hide_param_4_value= new AttributeValue(null,null,null,"objects[e.x][e.y][WhiteObject]",null,null,null,rulePostAction_7_hide_param_4,rulePostAction_7_hide_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_4_value);

        Attribute rulePostAction_7_hide_param_5 =new Attribute("row",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_5);

        AttributeValue rulePostAction_7_hide_param_5_value= new AttributeValue(null,null,null,"e.x",null,null,null,rulePostAction_7_hide_param_5,rulePostAction_7_hide_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_5_value);

        Attribute rulePostAction_7_hide_param_6 =new Attribute("col",rulePostAction_7_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_hide_param_6);

        AttributeValue rulePostAction_7_hide_param_6_value= new AttributeValue(null,null,null,"e.y",null,null,null,rulePostAction_7_hide_param_6,rulePostAction_7_hide_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_7_hide_param_6_value);


        PLRulePostAction rulePostAction_8_show = new PLRulePostAction(rule_Select_object,PLRulePostActionType.Call_Object_Action,0,"show","objects[e.x][e.y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rulePostAction_8_show);

        Attribute rulePostAction_8_show_param_1 =new Attribute("actionId",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_1);

        AttributeValue rulePostAction_8_show_param_1_value= new AttributeValue(null,null,null,"show action id",null,null,null,rulePostAction_8_show_param_1,rulePostAction_8_show_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_1_value);

        Attribute rulePostAction_8_show_param_2 =new Attribute("aSync",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_2);

        AttributeValue rulePostAction_8_show_param_2_value= new AttributeValue(false,null,null,null,null,null,null,rulePostAction_8_show_param_2,rulePostAction_8_show_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_2_value);

        Attribute rulePostAction_8_show_param_3 =new Attribute("graphic",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_3);
        AttributeValue rulePostAction_8_show_param_3_value= new AttributeValue(null,null,null,null,null,null,null,rulePostAction_8_show_param_3,rulePostAction_8_show_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_3_value);

        Attribute rulePostAction_8_show_param_4 =new Attribute("ObjectId",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_4);

        AttributeValue rulePostAction_8_show_param_4_value= new AttributeValue(null,null,null,"objects[e.x][e.y][WhiteObject]",null,null,null,rulePostAction_8_show_param_4,rulePostAction_8_show_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_4_value);

        Attribute rulePostAction_8_show_param_5 =new Attribute("row",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_5);

        AttributeValue rulePostAction_8_show_param_5_value= new AttributeValue(null,null,null,"e.x",null,null,null,rulePostAction_8_show_param_5,rulePostAction_8_show_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_5_value);

        Attribute rulePostAction_8_show_param_6 =new Attribute("col",rulePostAction_8_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_8_show_param_6);

        AttributeValue rulePostAction_8_show_param_6_value= new AttributeValue(null,null,null,"e.y",null,null,null,rulePostAction_8_show_param_6,rulePostAction_8_show_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_8_show_param_6_value);


        StringBuffer    pr_DeSelect_object_Condition = new StringBuffer("(Object[e.x, e.y].Locked == false)&&(Object[e.x, e.y].Selected == false)&&(((e.x==LastX)&&(e.y==LastY+1))||((e.x==LastX)&&(e.y==LastY-1))||((e.x==LastX+1)&&(e.y==LastY))||((e.x==LastX-1)&&(e.y==LastY)))");
        PLRule rule_DeSelect_object   = new PLRule("De Select object",2 ,pr_DeSelect_object_Condition,puzzleLevel_Maze,click_event.get(),1L,now,now,admin_1,admin_1);
        plRuleService.save(rule_DeSelect_object);

        PLRulePostAction deSelect_PostAction_1_assignment = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "LastX",new StringBuffer("Object[e.x, e.y].PreX"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_1_assignment);

        PLRulePostAction deSelect_PostAction_2_assignment = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "LastY",new StringBuffer("Object[e.x, e.y].PreY"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_2_assignment);

        PLRulePostAction deSelect_PostAction_3_assignment = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "Object[e.x, e.y].Selected",new StringBuffer("false"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_3_assignment);


        PLRulePostAction deSelect_PostAction_4_assignment = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Variable_Assignment_Action,0,"","",
                "PathLen",new StringBuffer("PathLen-1"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_4_assignment);

        PLRulePostAction deSelect_PostAction_5_hide = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Call_Object_Action,0,"hide","objects[e.x][e.y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_5_hide);

        Attribute deSelect_PostAction_5_param_1 =new Attribute("actionId",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_1);

        AttributeValue deSelect_PostAction_5_param_1_value= new AttributeValue(null,null,null,"show action id",null,null,null,deSelect_PostAction_5_param_1,deSelect_PostAction_5_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_1_value);

        Attribute deSelect_PostAction_5_param_2 =new Attribute("aSync",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_2);

        AttributeValue deSelect_PostAction_5_param_2_value= new AttributeValue(false,null,null,null,null,null,null,deSelect_PostAction_5_param_2,deSelect_PostAction_5_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_2_value);

        Attribute deSelect_PostAction_5_param_3 =new Attribute("graphic",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_3);

        AttributeValue deSelect_PostAction_5_param_3_value= new AttributeValue(null,null,null,null,null,null,null,deSelect_PostAction_5_param_3,deSelect_PostAction_5_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_3_value);

        Attribute deSelect_PostAction_5_param_4 =new Attribute("ObjectId",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_4);

        AttributeValue deSelect_PostAction_5_param_4_value= new AttributeValue(null,null,null,"objects[e.x][e.y][YellowObject]",null,null,null,deSelect_PostAction_5_param_4,deSelect_PostAction_5_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_4_value);

        Attribute deSelect_PostAction_5_param_5 =new Attribute("row",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_5);

        AttributeValue deSelect_PostAction_5_param_5_value= new AttributeValue(null,null,null,"e.x",null,null,null,deSelect_PostAction_5_param_5,deSelect_PostAction_5_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_5_value);

        Attribute deSelect_PostAction_5_param_6 =new Attribute("col",deSelect_PostAction_5_hide.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_5_param_6);

        AttributeValue deSelect_PostAction_5_param_6_value= new AttributeValue(null,null,null,"e.y",null,null,null,deSelect_PostAction_5_param_6,deSelect_PostAction_5_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_5_param_6_value);


        PLRulePostAction deSelect_PostAction_6_show = new PLRulePostAction(rule_DeSelect_object,PLRulePostActionType.Call_Object_Action,0,"show","objects[e.x][e.y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(deSelect_PostAction_6_show);

        Attribute deSelect_PostAction_6_param_1 =new Attribute("actionId",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_1);

        AttributeValue deSelect_PostAction_6_param_1_value= new AttributeValue(null,null,null,"show action id",null,null,null,deSelect_PostAction_6_param_1,deSelect_PostAction_6_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_1_value);

        Attribute deSelect_PostAction_6_param_2 =new Attribute("aSync",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_2);

        AttributeValue deSelect_PostAction_6_param_2_value= new AttributeValue(false,null,null,null,null,null,null,deSelect_PostAction_6_param_2,deSelect_PostAction_6_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_2_value);

        Attribute deSelect_PostAction_6_param_3 =new Attribute("graphic",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_3);

        AttributeValue deSelect_PostAction_6_param_3_value= new AttributeValue(null,null,null,null,null,null,null,deSelect_PostAction_6_param_3,deSelect_PostAction_6_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_3_value);

        Attribute deSelect_PostAction_6_param_4 =new Attribute("ObjectId",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_4);

        AttributeValue deSelect_PostAction_6_param_4_value= new AttributeValue(null,null,null,"objects[e.x][e.y][WhiteObject]",null,null,null,deSelect_PostAction_6_param_4,deSelect_PostAction_6_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_4_value);

        Attribute deSelect_PostAction_6_param_5 =new Attribute("row",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_5);

        AttributeValue deSelect_PostAction_6_param_5_value= new AttributeValue(null,null,null,"e.x",null,null,null,deSelect_PostAction_6_param_5,deSelect_PostAction_6_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_5_value);

        Attribute deSelect_PostAction_6_param_6 =new Attribute("col",deSelect_PostAction_6_show.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(deSelect_PostAction_6_param_6);

        AttributeValue deSelect_PostAction_6_param_6_value= new AttributeValue(null,null,null,"e.y",null,null,null,deSelect_PostAction_6_param_6,deSelect_PostAction_6_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(deSelect_PostAction_6_param_6_value);



        StringBuffer    win_rule_object_Condition = new StringBuffer("(Object[e.x, e.y].Selected == true)&&(e.x==LastX)&&(e.y==LastY)");
        PLRule rule_win_object   = new PLRule("Win Rule",3,win_rule_object_Condition,puzzleLevel_Maze,click_event.get(),1L,now,now,admin_1,admin_1);
        plRuleService.save(rule_win_object);

        PLRulePostAction win_rule_PostAction_1_showMessage = new PLRulePostAction(rule_win_object,PLRulePostActionType.Show_Message,0,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(win_rule_PostAction_1_showMessage);

        Attribute win_rule_PostAction_1_param_1 =new Attribute("actionId",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(win_rule_PostAction_1_param_1);

        AttributeValue win_rule_PostAction_1_param_1_value= new AttributeValue(null,null,null,"show message action id",null,null,null,win_rule_PostAction_1_param_1,win_rule_PostAction_1_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(win_rule_PostAction_1_param_1_value);

        Attribute win_rule_PostAction_1_param_2 =new Attribute("aSync",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(win_rule_PostAction_1_param_2);

        AttributeValue win_rule_PostAction_1_param_2_value= new AttributeValue(false,null,null,null,null,null,null,win_rule_PostAction_1_param_2,win_rule_PostAction_1_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(win_rule_PostAction_1_param_2_value);

        Attribute win_rule_PostAction_1_param_3 =new Attribute("MessageType",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(win_rule_PostAction_1_param_3);

        AttributeValue win_rule_PostAction_1_param_3_value= new AttributeValue(null,null,null,"info",null,null,null,win_rule_PostAction_1_param_3,win_rule_PostAction_1_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(win_rule_PostAction_1_param_3_value);

        Attribute win_rule_PostAction_1_param_4 =new Attribute("DialougeType",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(win_rule_PostAction_1_param_4);

        AttributeValue win_rule_PostAction_1_param_4_value= new AttributeValue(null,null,null,"toast",null,null,null,win_rule_PostAction_1_param_4,win_rule_PostAction_1_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(win_rule_PostAction_1_param_4_value);

        Attribute win_rule_PostAction_1_param_5 =new Attribute("text",win_rule_PostAction_1_showMessage.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(win_rule_PostAction_1_param_5);

        AttributeValue win_rule_PostAction_1_param_5_value= new AttributeValue(null,null,null,"win",null,null,null,win_rule_PostAction_1_param_5,win_rule_PostAction_1_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(win_rule_PostAction_1_param_5_value);

    }

}
