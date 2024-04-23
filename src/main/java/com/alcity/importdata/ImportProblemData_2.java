package com.alcity.importdata;


import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
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
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.WalletItemService;
import com.alcity.utility.ImageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Order(value=3)
@Component
public class ImportProblemData_2 implements CommandLineRunner {

    @Autowired
    private ApplicationMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;

    @Autowired
    private BinaryContentTypeService binaryContentTypeService;
    @Autowired
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;
    @Autowired
    DataTypeService dataTypeService;

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
    private PuzzleGroupService puzzleGroupService;
    @Autowired
    private PuzzleLevelPrivacyService puzzleLevelPrivacyService;
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
    PuzzleObjectService puzzleObjectService;
    @Autowired
    PuzzleGroup_PuzzleObjectService puzzleGroup_PuzzleObjectService;

    @Autowired
    PLObjectiveService plObjectiveService;
    @Autowired
    AttributeValueService attributeValueService;
    @Autowired
    AttributeService attributeService;
    @Autowired
    ActionRendererService actionRendererService;
    @Autowired
    ClientTypeService clientTypeService;
    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;

    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 2");
        System.out.println("...Import Problem 2");

        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);
        ZonedDateTime  createdDate= ZonedDateTime.now();
        Long now = createdDate.toEpochSecond();


        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        BinaryContentType imageType= binaryContentTypeService.findByValue("image");
        ApplicationMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        ApplicationMember moslemBalavandi = applicationMemberService.findByUsername("moslem");
        ApplicationMember alirezaZarei = applicationMemberService.findByUsername("alireza");
        LearningTopic routing_in_the_table = learningTopicService.findByTitle("Routing in the Table");


        DataType alcity_Int = dataTypeService.findByValue("Integer");
        DataType alcity_Binary = dataTypeService.findByValue("Binary");
        DataType alcity_Long = dataTypeService.findByValue("Long");
        DataType alcity_Boolean = dataTypeService.findByValue("Boolean");
        DataType alcity_String =  dataTypeService.findByValue("String");
        DataType alcity_Object =  dataTypeService.findByValue("Object");


        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill routing = learningSkillService.findByValue("routing");
        LearningSkill find_paths = learningSkillService.findByValue("Find Paths");

        WalletItem alCoin_100_WalletItem = walletItemService.findByValue("al_coin_100");
        Journey journey_1 = journeyService.findByTitle("Journey_1");

        byte[] playGround_image_problem_2 = ImageUtil.getImage("src/main/resources/images/","playGround_2.png");
        BinaryContent play_ground_binary_content_2 = new BinaryContent("puzzle ground for Maze Problem",playGround_image_problem_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(play_ground_binary_content_2);

        byte[] puzzle_group_Pic_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_2.png");
        BinaryContent puzzle_group_2_binary_content_pic = new BinaryContent("image_puzzle_group_Mazes",puzzle_group_Pic_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_2_binary_content_pic);


        byte[] puzzle_group_Icon_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group2_icon.png");
        BinaryContent puzzle_group_2_binary_content_icon = new BinaryContent("image_puzzle_group_Mazes",puzzle_group_Icon_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_2_binary_content_icon);


        PuzzleCategory maze = puzzleCategoryService.findByValue("Maze");


        PuzzleGroup puzzleGroup_2 = new PuzzleGroup("Maze Image - Puzzle Group 2",maze,puzzle_group_2_binary_content_icon,puzzle_group_2_binary_content_pic,1L,now,now,admin_1,admin_1);
        puzzleGroupService.save(puzzleGroup_2);

        JourneyStep step_3_journey_1 = new JourneyStep("step_3_journey_1",1,30,30,journey_1,puzzleGroup_2,1L,now,now,admin_1,admin_1);

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_Maze = new PuzzleLevel(now,1L,"Find shortest path from start to end in maze","4546",10,14,5f,puzzleGroup_2, PLDifficulty.Medium, PLStatus.Not_Started,privacy_1,puzzle_group_2_binary_content_pic,puzzle_group_2_binary_content_icon,3L,now,now,admin_1,admin_1);
        puzzleLevelService.save(puzzleLevel_Maze);


        byte[] puzzle_group_Maze_pic = ImageUtil.getImage("src/main/resources/images/","MazeImage.png");
        BinaryContent puzzle_group_Maze_binary_content = new BinaryContent("Maze Image",puzzle_group_Maze_pic,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_Maze_binary_content);

        Integer xPos=5;
        Integer xRotation=5;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);

        PLGround puzzleLevel_Maze_ground = new PLGround(19,19,puzzleLevel_Maze,play_ground_binary_content_2,1L,now,now,admin_1,admin_1);
        puzzleLevel_Maze_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_Maze_ground);

        PermitedPlayer player_1_puzzleLevel_Maze = new PermitedPlayer(jalalHoseyni,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_Maze);

        PermitedPlayer player_2_puzzleLevel_Maze = new PermitedPlayer(moslemBalavandi,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_2_puzzleLevel_Maze);

        LearningContent learningContent_routing=new LearningContent("help to math","this content is about Mazes",puzzle_group_2_binary_content_pic,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_routing);

        PuzzleLevel_LearningTopic puzzleLevelLearningTopic_1 = new PuzzleLevel_LearningTopic(puzzleLevel_Maze,routing_in_the_table,learningContent_routing,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_Maze,now,100,10f,1L,now,now,jalalHoseyni,jalalHoseyni);
        playHistoryService.save(playHistory_1);

        PlayHistory playHistory_2 = new PlayHistory(moslemBalavandi,puzzleLevel_Maze,now,100,10f,1L,now,now,moslemBalavandi,moslemBalavandi);
        playHistoryService.save(playHistory_2);

        PlayHistory playHistory_3 = new PlayHistory(alirezaZarei,puzzleLevel_Maze,now,100,10f,1L,now,now,alirezaZarei,alirezaZarei);
        playHistoryService.save(playHistory_3);

        PuzzleSkillLearningContent puzzleSkillLearningContent_1 = new PuzzleSkillLearningContent(routing,puzzleGroup_2,learningContent_routing,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_Maze, GameStatus.gameStatus_1,1L,now,now,jalalHoseyni,jalalHoseyni);
        plGameInstanceService.save(puzzleLevelGameInstance);

        ObjectCategory objectCategory_Image = objectCategoryService.findByValue("Image");

        BinaryContent img_Image_binary_content = binaryContentService.findByfileName("image_object");

       PuzzleObject ImageObject02 = new PuzzleObject("ImageObject02",objectCategory_Image,img_Image_binary_content,img_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(ImageObject02);

        PuzzleGroup_PuzzleObject  puzzleGroup_puzzleObject = new PuzzleGroup_PuzzleObject ("Maze Puzzle Group with Image Object","Maze_ImageObject",puzzleGroup_2,ImageObject02,1L,now,now,admin_1,admin_1);
        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);

        StringBuffer  condition_Objective = new StringBuffer("(LastX == 19)&&(LastY == 18)&&(PathLen=40)" );
        WalletItem carObjectWalletItem = walletItemService.findByValue("car_object");

        PLObjective puzzleLevelObjective = new PLObjective("find shortest path from start to end","find shortest path from start to end",10f,2f,condition_Objective,find_paths,carObjectWalletItem,puzzleLevel_Maze
                ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective);

        Attribute attribute_variable_LastX =new Attribute("LastX",puzzleLevel_Maze.getId(), AttributeOwnerType.Puzzle_Level_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        Attribute attribute_variable_LastY =new Attribute("LastY",puzzleLevel_Maze.getId(),AttributeOwnerType.Puzzle_Level_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        Attribute attribute_variable_PathLen =new Attribute("PathLen",puzzleLevel_Maze.getId(),AttributeOwnerType.Puzzle_Level_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_LastX);
        attributeService.save(attribute_variable_LastY);
        attributeService.save(attribute_variable_PathLen);

        AttributeValue attribute_variable_LastX_value= new AttributeValue(null,1,null,null,null,null,attribute_variable_LastX,attribute_variable_LastX,1L,now,now,admin_1,admin_1);
        AttributeValue attribute_variable_LastY_value= new AttributeValue(null,0,null,null,null,null,attribute_variable_LastY,attribute_variable_LastY,1L,now,now,admin_1,admin_1);
        AttributeValue attribute_variable_PathLen_value= new AttributeValue(null,0,null,null,null,null,attribute_variable_PathLen,attribute_variable_PathLen,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_LastX_value);
        attributeValueService.save(attribute_variable_LastY_value);
        attributeValueService.save(attribute_variable_PathLen_value);
        ClientType mobile = clientTypeService.findByValue("mobile");

        ActionRenderer showActionRenderer = new ActionRenderer("Show",mobile,ObjectAction.Show,1L,now,now,admin_1,admin_1);
        actionRendererService.save(showActionRenderer);

        ActionRenderer hideActionRenderer = new ActionRenderer("Hide",mobile,ObjectAction.Hide,1L,now,now,admin_1,admin_1);
        actionRendererService.save(hideActionRenderer);

        PuzzleObject_ObjectAction  imageObject01_ShowAction = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Show,showActionRenderer,1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(imageObject01_ShowAction);


        Attribute attribute_show_action =new Attribute("actionId",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Long,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_action);

        AttributeValue showAction_parameter_action_id= new AttributeValue(null,null,attribute_show_action.getId(),null,null,null,attribute_show_action,attribute_show_action,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_action_id);

        Attribute attribute_show_aSync =new Attribute("aSync",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_aSync);
        AttributeValue showAction_parameter_aSync_Value= new AttributeValue(false,null,null,null,null,null,attribute_show_aSync,attribute_show_aSync,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_aSync_Value);

        Attribute attribute_show_graphic =new Attribute("graphic",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_graphic);
        AttributeValue showAction_parameter_graphic_value= new AttributeValue(null,null,null,null,null,null,attribute_show_graphic,attribute_show_graphic,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_graphic_value);


        Attribute alCityAttribute_show_Row =new Attribute("row",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_show_Row);
        AttributeValue alCity_moveAction_parameter_row= new AttributeValue(null,0,null,null,null,null,alCityAttribute_show_Row,alCityAttribute_show_Row,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_moveAction_parameter_row);

        Attribute alCityAttribute_move_Col =new Attribute("col",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_move_Col);
        AttributeValue alCity_showAction_parameter_Col= new AttributeValue(null,0,null,null,null,null,alCityAttribute_move_Col,alCityAttribute_move_Col,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_showAction_parameter_Col);


        PuzzleObject_ObjectAction  imageObject01_HideAction = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Hide,hideActionRenderer,1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(imageObject01_HideAction);
        Attribute attribute_hide_action_id =new Attribute("actionId",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Long,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_action_id);

        AttributeValue hideAction_parameter_action_id= new AttributeValue(null,null,imageObject01_HideAction.getId(),null,null,null,attribute_hide_action_id,attribute_hide_action_id,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_action_id);

        Attribute attribute_hide_aSync =new Attribute("aSync",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_aSync);
        AttributeValue hideAction_parameter_aSync_Value= new AttributeValue(false,null,null,null,null,null,attribute_hide_aSync,attribute_hide_aSync,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_aSync_Value);

        Attribute attribute_hide_graphic =new Attribute("graphic",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_graphic);
        AttributeValue hideAction_parameter_graphic_value= new AttributeValue(null,null,null,null,null,null,attribute_hide_graphic,attribute_hide_graphic,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_graphic_value);


        Attribute alCityAttribute_hide_Row =new Attribute("row",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_hide_Row);
        AttributeValue alCity_hideAction_parameter_row= new AttributeValue(null,0,null,null,null,null,alCityAttribute_hide_Row,alCityAttribute_hide_Row,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_hideAction_parameter_row);

        Attribute alCityAttribute_hide_Col =new Attribute("col",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_hide_Col);
        AttributeValue alCity_hideAction_parameter_Col= new AttributeValue(null,0,null,null,null,null,alCityAttribute_hide_Col,alCityAttribute_hide_Col,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_hideAction_parameter_Col);

    }

}
