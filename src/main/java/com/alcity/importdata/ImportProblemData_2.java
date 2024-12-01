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


@Order(value=3)
@Component
public class ImportProblemData_2 implements CommandLineRunner {

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
        Journey journey_1 = journeyService.findByTitle("Journey_1");

        byte[] playGround_image_problem_2 = ImageUtil.getImage("src/main/resources/images/","playGround_2.png");
        BinaryContent play_ground_binary_content_2 = new BinaryContent(1L, now, now,admin_1 , admin_1,"puzzle ground for Maze Problem",playGround_image_problem_2.length,playGround_image_problem_2,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(play_ground_binary_content_2);

        byte[] puzzle_group_Pic_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_2.png");
        BinaryContent puzzle_group_2_binary_content_pic = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_Mazes",puzzle_group_Pic_2.length,puzzle_group_Pic_2,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_2_binary_content_pic);


        byte[] puzzle_group_Icon_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group2_icon.png");
        BinaryContent puzzle_group_2_binary_content_icon = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_Mazes",puzzle_group_Icon_2.length,puzzle_group_Icon_2,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_2_binary_content_icon);


        PuzzleCategory maze = puzzleCategoryService.findByValue("Maze");


        PuzzleGroup puzzleGroup_2 = new PuzzleGroup("Maze Image - Puzzle Group 2",maze,puzzle_group_2_binary_content_icon,puzzle_group_2_binary_content_pic,1L,now,now,admin_1,admin_1);
        puzzleGroupService.save(puzzleGroup_2);

        JourneyStep step_3_journey_1 = new JourneyStep("step_3_journey_1",1,30,30,journey_1,puzzleGroup_2,1L,now,now,admin_1,admin_1);

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_Maze = new PuzzleLevel(now,1L,"Find shortest path from start to end in maze","4546",10,14,5f,puzzleGroup_2, PLDifficulty.Medium, PLStatus.Not_Started,privacy_1,3L,now,now,admin_1,admin_1);
        puzzleLevel_Maze.setIcon(puzzle_group_2_binary_content_icon);
        puzzleLevel_Maze.setPicture(puzzle_group_2_binary_content_pic);
        puzzleLevelService.save(puzzleLevel_Maze);


        byte[] puzzle_group_Maze_pic = ImageUtil.getImage("src/main/resources/images/","MazeImage.png");
        BinaryContent puzzle_group_Maze_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Maze Image",puzzle_group_Maze_pic.length,puzzle_group_Maze_pic,null,"tag1","","",BinaryContentType.Image);
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

        LearningSubjectInPL puzzleLevelLearningTopic_1 = new LearningSubjectInPL(puzzleLevel_Maze,routing_in_the_table.get(),learningContent_routing,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_Maze,now,100,10f,1L,now,now,jalalHoseyni,jalalHoseyni);
        playHistoryService.save(playHistory_1);

        PlayHistory playHistory_2 = new PlayHistory(moslemBalavandi,puzzleLevel_Maze,now,100,10f,1L,now,now,moslemBalavandi,moslemBalavandi);
        playHistoryService.save(playHistory_2);

        PlayHistory playHistory_3 = new PlayHistory(alirezaZarei,puzzleLevel_Maze,now,100,10f,1L,now,now,alirezaZarei,alirezaZarei);
        playHistoryService.save(playHistory_3);

        LearningSkillContent puzzleSkillLearningContent_1 = new LearningSkillContent(routing,puzzleGroup_2,learningContent_routing,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_Maze, GameStatus.gameStatus_1,1L,now,now,jalalHoseyni,jalalHoseyni);
        plGameInstanceService.save(puzzleLevelGameInstance);

        ObjectCategory objectCategory_Image = objectCategoryService.findByValue("Image");

        BinaryContent img_Image_binary_content = binaryContentService.findByfileName("image_object");
        Optional<ALCityObject> ImageObject02 =puzzleObjectService.findByTitle("ImageObject02");


        ALCityObjectInPG puzzleGroup_puzzleObject = new ALCityObjectInPG("Maze Puzzle Group with Image Object","Maze_ImageObject",puzzleGroup_2,ImageObject02.get(),1L,now,now,admin_1,admin_1);
        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);

        StringBuffer  condition_Objective = new StringBuffer("(LastX == 19)&&(LastY == 18)&&(PathLen=40)" );
        Optional<WalletItem> carObjectWalletItem = walletItemService.findByValue("car_object");

        PLObjective puzzleLevelObjective = new PLObjective("find shortest path from start to end","find shortest path from start to end",10f,2f,condition_Objective,find_paths,carObjectWalletItem.get(),puzzleLevel_Maze
                ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective);

        Attribute attribute_variable_LastX =new Attribute("LastX",puzzleLevel_Maze.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        Attribute attribute_variable_LastY =new Attribute("LastY",puzzleLevel_Maze.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        Attribute attribute_variable_PathLen =new Attribute("PathLen",puzzleLevel_Maze.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_LastX);
        attributeService.save(attribute_variable_LastY);
        attributeService.save(attribute_variable_PathLen);

        AttributeValue attribute_variable_LastX_value= new AttributeValue(null,1,null,null,null,null,null,attribute_variable_LastX,attribute_variable_LastX,1L,now,now,admin_1,admin_1);
        AttributeValue attribute_variable_LastY_value= new AttributeValue(null,0,null,null,null,null,null,attribute_variable_LastY,attribute_variable_LastY,1L,now,now,admin_1,admin_1);
        AttributeValue attribute_variable_PathLen_value= new AttributeValue(null,0,null,null,null,null,null,attribute_variable_PathLen,attribute_variable_PathLen,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_LastX_value);
        attributeValueService.save(attribute_variable_LastY_value);
        attributeValueService.save(attribute_variable_PathLen_value);

        ClientType mobile = clientTypeService.findByValue("mobile");

        Optional<Renderer> showActionRenderer = actionRendererService.findByHandlerAndObjectAction("Show",ObjectAction.Show);;


        Renderer hideActionRenderer = new Renderer("Hide",mobile,ObjectAction.Hide,1L,now,now,admin_1,admin_1);
        actionRendererService.save(hideActionRenderer);

        PuzzleObjectAction imageObject01_ShowAction = new PuzzleObjectAction(POActionOwnerType.ALCity_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Show,showActionRenderer.get(),1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(imageObject01_ShowAction);


        Attribute attribute_show_action =new Attribute("actionId",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_action);

        AttributeValue showAction_parameter_action_id= new AttributeValue(null,null,attribute_show_action.getId(),null,null,null,null,attribute_show_action,attribute_show_action,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_action_id);

        Attribute attribute_show_aSync =new Attribute("aSync",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_aSync);
        AttributeValue showAction_parameter_aSync_Value= new AttributeValue(false,null,null,null,null,null,null,attribute_show_aSync,attribute_show_aSync,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_aSync_Value);

        Attribute attribute_show_graphic =new Attribute("graphic",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_graphic);

        AttributeValue showAction_parameter_graphic_value= new AttributeValue(null,null,null,null,null,null,-1L,attribute_show_graphic,attribute_show_graphic,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_graphic_value);

        Attribute attribute_show_ObjectId =new Attribute("ObjectId",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Object,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_show_ObjectId);
        AttributeValue showAction_parameter_ObjectId_value= new AttributeValue(null,null,null,null,"object",null,null,attribute_show_ObjectId,attribute_show_ObjectId,1L,now,now,admin_1,admin_1);
        attributeValueService.save(showAction_parameter_ObjectId_value);


        Attribute alCityAttribute_show_Row =new Attribute("row",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_show_Row);
        AttributeValue alCity_moveAction_parameter_row= new AttributeValue(null,0,null,null,null,null,null,alCityAttribute_show_Row,alCityAttribute_show_Row,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_moveAction_parameter_row);

        Attribute alCityAttribute_move_Col =new Attribute("col",imageObject01_ShowAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_move_Col);
        AttributeValue alCity_showAction_parameter_Col= new AttributeValue(null,0,null,null,null,null,null,alCityAttribute_move_Col,alCityAttribute_move_Col,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_showAction_parameter_Col);

        PuzzleObjectAction imageObject01_HideAction = new PuzzleObjectAction(POActionOwnerType.ALCity_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Hide,hideActionRenderer,1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(imageObject01_HideAction);
        Attribute attribute_hide_action_id =new Attribute("actionId",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_action_id);

        AttributeValue hideAction_parameter_action_id= new AttributeValue(null,null,imageObject01_HideAction.getId(),null,null,null,null,attribute_hide_action_id,attribute_hide_action_id,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_action_id);

        Attribute attribute_hide_aSync =new Attribute("aSync",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_aSync);
        AttributeValue hideAction_parameter_aSync_Value= new AttributeValue(false,null,null,null,null,null,null,attribute_hide_aSync,attribute_hide_aSync,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_aSync_Value);

        Attribute attribute_hide_graphic =new Attribute("graphic",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_graphic);
        AttributeValue hideAction_parameter_graphic_value= new AttributeValue(null,null,null,null,null,null,-1L,attribute_hide_graphic,attribute_hide_graphic,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_graphic_value);

        Attribute attribute_hide_ObjectId =new Attribute("ObjectId",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Object,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_hide_ObjectId);
        AttributeValue hideAction_parameter_ObjectId_value= new AttributeValue(null,null,null,null,"object",null,null,attribute_hide_ObjectId,attribute_hide_ObjectId,1L,now,now,admin_1,admin_1);
        attributeValueService.save(hideAction_parameter_ObjectId_value);


        Attribute alCityAttribute_hide_Row =new Attribute("row",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_hide_Row);
        AttributeValue alCity_hideAction_parameter_row= new AttributeValue(null,0,null,null,null,null,null,alCityAttribute_hide_Row,alCityAttribute_hide_Row,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_hideAction_parameter_row);

        Attribute alCityAttribute_hide_Col =new Attribute("col",imageObject01_HideAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_hide_Col);
        AttributeValue alCity_hideAction_parameter_Col= new AttributeValue(null,0,null,null,null,null,null,alCityAttribute_hide_Col,alCityAttribute_hide_Col,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCity_hideAction_parameter_Col);

        Attribute attribute_object_variable_Prex =new Attribute("PreX",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_object_variable_Prex);
        AttributeValue attribute_object_variable_Prex_value= new AttributeValue(null,0,null,null,null,null,null,attribute_object_variable_Prex,attribute_object_variable_Prex,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_object_variable_Prex_value);

        Attribute attribute_object_variable_PreY =new Attribute("PreY",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_object_variable_PreY);
        AttributeValue attribute_object_variable_PreY_value= new AttributeValue(null,0,null,null,null,null,null,attribute_object_variable_PreY,attribute_object_variable_PreY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_object_variable_PreY_value);

        Attribute attribute_object_variable_Selected =new Attribute("Selected",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_object_variable_Selected);
        AttributeValue attribute_object_variable_Selected_value= new AttributeValue(null,0,null,null,null,null,null,attribute_object_variable_Selected,attribute_object_variable_Selected,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_object_variable_Selected_value);

        Attribute attribute_object_variable_Locked =new Attribute("Locked",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_object_variable_Locked);
        AttributeValue attribute_object_variable_Locked_value= new AttributeValue(null,0,null,null,null,null,null,attribute_object_variable_Locked,attribute_object_variable_Locked,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_object_variable_Locked_value);

        byte[] image_0_maze_1 = ImageUtil.getImage("src/main/resources/images/Maze_problem/","white_space_image.png");
        byte[] image_0_maze_2 = ImageUtil.getImage("src/main/resources/images/Maze_problem/","black_space_image.png");
        byte[] image_0_maze_3 = ImageUtil.getImage("src/main/resources/images/Maze_problem/","yellow_space_image.png");

        BinaryContent white_space_image = new BinaryContent(1L, now, now,admin_1 , admin_1,"white_space_image", image_0_maze_1.length, image_0_maze_1,null,"tag1","","",BinaryContentType.Image);
        BinaryContent black_space_image = new BinaryContent(1L, now, now,admin_1 , admin_1,"black_space_image",image_0_maze_2.length,image_0_maze_2,null,"tag1","","",BinaryContentType.Image);
        BinaryContent yellow_space_image = new BinaryContent(1L, now, now,admin_1 , admin_1,"yellow_space_image",image_0_maze_3.length,image_0_maze_3,null,"tag1","","",BinaryContentType.Image);

        binaryContentService.save(white_space_image);
        binaryContentService.save(black_space_image);
        binaryContentService.save(yellow_space_image);


        ALCityInstanceInPL instance_img_1_1_0 = new ALCityInstanceInPL("instance_img_1_1_0",1,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_1_0);

        Attribute instance_img_1_1_0_bgImage_property =new Attribute("bgImage",instance_img_1_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_1_1_0_bgImage_property);
        AttributeValue instance_img_1_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_1_1_0_bgImage_property,instance_img_1_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_1_1_0_bgImage_property_value);

        Attribute instance_img_1_1_0_locked_variable =new Attribute("Locked",instance_img_1_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_1_1_0_locked_variable);
        AttributeValue instance_img_1_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_1_1_0_locked_variable,instance_img_1_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_1_1_0_locked_variable_value);



        ALCityInstanceInPL instance_img_2_1_0 = new ALCityInstanceInPL("instance_img_2_1_0",2,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_1_0);
        Attribute instance_img_2_1_0_bgImage_property =new Attribute("bgImage",instance_img_2_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_2_1_0_bgImage_property);
        AttributeValue instance_img_2_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_2_1_0_bgImage_property,instance_img_2_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_2_1_0_bgImage_property_value);

        Attribute instance_img_2_1_0_locked_variable =new Attribute("Locked",instance_img_2_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_2_1_0_locked_variable);
        AttributeValue instance_img_2_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_2_1_0_locked_variable,instance_img_2_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_2_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_3_1_0 = new ALCityInstanceInPL("instance_img_3_1_0",3,1,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_1_0);

        Attribute instance_img_3_1_0_bgImage_property =new Attribute("bgImage",instance_img_3_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_3_1_0_bgImage_property);
        AttributeValue instance_img_3_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_3_1_0_bgImage_property,instance_img_3_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_3_1_0_bgImage_property_value);
        Attribute instance_img_3_1_0_locked_variable =new Attribute("Locked",instance_img_3_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_3_1_0_locked_variable);
        AttributeValue instance_img_3_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_3_1_0_locked_variable,instance_img_3_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_3_1_0_locked_variable_value);




        ALCityInstanceInPL instance_img_4_1_0 = new ALCityInstanceInPL("instance_img_4_1_0",4,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_1_0);

        Attribute instance_img_4_1_0_bgImage_property =new Attribute("bgImage",instance_img_4_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_4_1_0_bgImage_property);
        AttributeValue instance_img_4_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_4_1_0_bgImage_property,instance_img_4_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_4_1_0_bgImage_property_value);

        Attribute instance_img_4_1_0_locked_variable =new Attribute("Locked",instance_img_4_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_4_1_0_locked_variable);
        AttributeValue instance_img_4_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_4_1_0_locked_variable,instance_img_4_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_4_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_5_1_0 = new ALCityInstanceInPL("instance_img_5_1_0",5,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_5_1_0);

        Attribute instance_img_5_1_0_bgImage_property =new Attribute("bgImage",instance_img_5_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_5_1_0_bgImage_property);
        AttributeValue instance_img_5_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_5_1_0_bgImage_property,instance_img_5_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_5_1_0_bgImage_property_value);
        Attribute instance_img_5_1_0_locked_variable =new Attribute("Locked",instance_img_5_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_5_1_0_locked_variable);
        AttributeValue instance_img_5_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_5_1_0_locked_variable,instance_img_5_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_5_1_0_locked_variable_value);



        ALCityInstanceInPL instance_img_6_1_0 = new ALCityInstanceInPL("instance_img_6_1_0",6,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_6_1_0);
        Attribute instance_img_6_1_0_bgImage_property =new Attribute("bgImage",instance_img_6_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_6_1_0_bgImage_property);
        AttributeValue instance_img_6_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_6_1_0_bgImage_property,instance_img_6_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_6_1_0_bgImage_property_value);
        Attribute instance_img_6_1_0_locked_variable =new Attribute("Locked",instance_img_6_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_6_1_0_locked_variable);
        AttributeValue instance_img_6_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_6_1_0_locked_variable,instance_img_6_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_6_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_7_1_0 = new ALCityInstanceInPL("instance_img_7_1_0",7,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_7_1_0);
        Attribute instance_img_7_1_0_bgImage_property =new Attribute("bgImage",instance_img_7_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_7_1_0_bgImage_property);
        AttributeValue instance_img_7_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_7_1_0_bgImage_property,instance_img_7_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_7_1_0_bgImage_property_value);
        Attribute instance_img_7_1_0_locked_variable =new Attribute("Locked",instance_img_7_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_7_1_0_locked_variable);
        AttributeValue instance_img_7_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_7_1_0_locked_variable,instance_img_7_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_7_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_8_1_0 = new ALCityInstanceInPL("instance_img_8_1_0",8,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_8_1_0);
        Attribute instance_img_8_1_0_bgImage_property =new Attribute("bgImage",instance_img_8_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_8_1_0_bgImage_property);
        AttributeValue instance_img_8_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_8_1_0_bgImage_property,instance_img_8_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_8_1_0_bgImage_property_value);
        Attribute instance_img_8_1_0_locked_variable =new Attribute("Locked",instance_img_8_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_8_1_0_locked_variable);
        AttributeValue instance_img_8_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_8_1_0_locked_variable,instance_img_8_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_8_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_9_1_0 = new ALCityInstanceInPL("instance_img_9_1_0",9,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_9_1_0);
        Attribute instance_img_9_1_0_bgImage_property =new Attribute("bgImage",instance_img_9_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_9_1_0_bgImage_property);
        AttributeValue instance_img_9_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_9_1_0_bgImage_property,instance_img_9_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_9_1_0_bgImage_property_value);
        Attribute instance_img_9_1_0_locked_variable =new Attribute("Locked",instance_img_9_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_9_1_0_locked_variable);
        AttributeValue instance_img_9_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_9_1_0_locked_variable,instance_img_9_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_9_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_10_1_0 = new ALCityInstanceInPL("instance_img_10_1_0",10,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_10_1_0);
        Attribute instance_img_10_1_0_bgImage_property =new Attribute("bgImage",instance_img_10_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_10_1_0_bgImage_property);
        AttributeValue instance_img_10_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_10_1_0_bgImage_property,instance_img_10_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_10_1_0_bgImage_property_value);
        Attribute instance_img_10_1_0_locked_variable =new Attribute("Locked",instance_img_10_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_10_1_0_locked_variable);
        AttributeValue instance_img_10_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_10_1_0_locked_variable,instance_img_10_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_10_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_11_1_0 = new ALCityInstanceInPL("instance_img_11_1_0",11,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_11_1_0);
        Attribute instance_img_11_1_0_bgImage_property =new Attribute("bgImage",instance_img_11_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_11_1_0_bgImage_property);
        AttributeValue instance_img_11_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_11_1_0_bgImage_property,instance_img_11_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_11_1_0_bgImage_property_value);
        Attribute instance_img_11_1_0_locked_variable =new Attribute("Locked",instance_img_11_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_11_1_0_locked_variable);
        AttributeValue instance_img_11_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_11_1_0_locked_variable,instance_img_11_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_11_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_12_1_0 = new ALCityInstanceInPL("instance_img_12_1_0",12,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_12_1_0);
        Attribute instance_img_12_1_0_bgImage_property =new Attribute("bgImage",instance_img_12_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_12_1_0_bgImage_property);
        AttributeValue instance_img_12_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_12_1_0_bgImage_property,instance_img_12_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_12_1_0_bgImage_property_value);
        Attribute instance_img_12_1_0_locked_variable =new Attribute("Locked",instance_img_12_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_12_1_0_locked_variable);
        AttributeValue instance_img_12_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_12_1_0_locked_variable,instance_img_12_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_12_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_13_1_0 = new ALCityInstanceInPL("instance_img_13_1_0",13,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_13_1_0);
        Attribute instance_img_13_1_0_bgImage_property =new Attribute("bgImage",instance_img_13_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_13_1_0_bgImage_property);
        AttributeValue instance_img_13_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_13_1_0_bgImage_property,instance_img_13_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_13_1_0_bgImage_property_value);
        Attribute instance_img_13_1_0_locked_variable =new Attribute("Locked",instance_img_13_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_13_1_0_locked_variable);
        AttributeValue instance_img_13_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_13_1_0_locked_variable,instance_img_13_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_13_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_14_1_0 = new ALCityInstanceInPL("instance_img_14_1_0",14,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_14_1_0);
        Attribute instance_img_14_1_0_bgImage_property =new Attribute("bgImage",instance_img_14_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_14_1_0_bgImage_property);
        AttributeValue instance_img_14_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_14_1_0_bgImage_property,instance_img_14_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_14_1_0_bgImage_property_value);
        Attribute instance_img_14_1_0_locked_variable =new Attribute("Locked",instance_img_14_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_14_1_0_locked_variable);
        AttributeValue instance_img_14_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_14_1_0_locked_variable,instance_img_14_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_14_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_15_1_0 = new ALCityInstanceInPL("instance_img_15_1_0",15,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_15_1_0);
        Attribute instance_img_15_1_0_bgImage_property =new Attribute("bgImage",instance_img_15_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_15_1_0_bgImage_property);
        AttributeValue instance_img_15_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_15_1_0_bgImage_property,instance_img_15_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_15_1_0_bgImage_property_value);
        Attribute instance_img_15_1_0_locked_variable =new Attribute("Locked",instance_img_15_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_15_1_0_locked_variable);
        AttributeValue instance_img_15_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_15_1_0_locked_variable,instance_img_15_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_15_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_16_1_0 = new ALCityInstanceInPL("instance_img_16_1_0",16,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_16_1_0);
        Attribute instance_img_16_1_0_bgImage_property =new Attribute("bgImage",instance_img_16_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_16_1_0_bgImage_property);
        AttributeValue instance_img_16_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_16_1_0_bgImage_property,instance_img_16_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_16_1_0_bgImage_property_value);
        Attribute instance_img_16_1_0_locked_variable =new Attribute("Locked",instance_img_16_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_16_1_0_locked_variable);
        AttributeValue instance_img_16_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_16_1_0_locked_variable,instance_img_16_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_16_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_17_1_0 = new ALCityInstanceInPL("instance_img_17_1_0",17,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_17_1_0);
        Attribute instance_img_17_1_0_bgImage_property =new Attribute("bgImage",instance_img_17_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_17_1_0_bgImage_property);
        AttributeValue instance_img_17_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_17_1_0_bgImage_property,instance_img_17_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_17_1_0_bgImage_property_value);
        Attribute instance_img_17_1_0_locked_variable =new Attribute("Locked",instance_img_17_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_17_1_0_locked_variable);
        AttributeValue instance_img_17_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_17_1_0_locked_variable,instance_img_17_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_17_1_0_locked_variable_value);



        ALCityInstanceInPL instance_img_18_1_0 = new ALCityInstanceInPL("instance_img_18_1_0",18,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_18_1_0);
        Attribute instance_img_18_1_0_bgImage_property =new Attribute("bgImage",instance_img_18_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_18_1_0_bgImage_property);
        AttributeValue instance_img_18_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_18_1_0_bgImage_property,instance_img_18_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_18_1_0_bgImage_property_value);
        Attribute instance_img_18_1_0_locked_variable =new Attribute("Locked",instance_img_18_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_18_1_0_locked_variable);
        AttributeValue instance_img_18_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_18_1_0_locked_variable,instance_img_18_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_18_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_19_1_0 = new ALCityInstanceInPL("instance_img_19_1_0",19,1,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_19_1_0);
        Attribute instance_img_19_1_0_bgImage_property =new Attribute("bgImage",instance_img_19_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_19_1_0_bgImage_property);
        AttributeValue instance_img_19_1_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,black_space_image.getId(),instance_img_19_1_0_bgImage_property,instance_img_19_1_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_19_1_0_bgImage_property_value);
        Attribute instance_img_19_1_0_locked_variable =new Attribute("Locked",instance_img_19_1_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_19_1_0_locked_variable);
        AttributeValue instance_img_19_1_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,instance_img_19_1_0_locked_variable,instance_img_19_1_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_19_1_0_locked_variable_value);


        ALCityInstanceInPL instance_img_1_2_1 = new ALCityInstanceInPL("instance_img_1_2_1",1,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_2_1);
        Attribute instance_img_1_2_1_bgImage_property =new Attribute("bgImage",instance_img_1_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_1_2_1_bgImage_property);
        AttributeValue instance_img_1_2_1_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,white_space_image.getId(),instance_img_1_2_1_bgImage_property,instance_img_1_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_1_2_1_bgImage_property_value);


        ALCityInstanceInPL instance_img_1_2_0 = new ALCityInstanceInPL("instance_img_1_2_0",1,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_2_0);
        Attribute instance_img_1_2_0_bgImage_property =new Attribute("bgImage",instance_img_1_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(instance_img_1_2_0_bgImage_property);
        AttributeValue instance_img_1_2_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,yellow_space_image.getId(),instance_img_1_2_0_bgImage_property,instance_img_1_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(instance_img_1_2_0_bgImage_property_value);


        ALCityInstanceInPL img_2_2_1 = new ALCityInstanceInPL("img_2_2_1",2,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_2_1);
        Attribute img_2_2_1_bgImage_property =new Attribute("bgImage",img_2_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_2_1_bgImage_property);
        AttributeValue img_2_2_1_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,white_space_image.getId(),img_2_2_1_bgImage_property,img_2_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_2_1_bgImage_property_value);


        ALCityInstanceInPL img_2_2_0 = new ALCityInstanceInPL("img_2_2_0",2,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_2_0);
        Attribute img_2_2_0_bgImage_property =new Attribute("bgImage",img_2_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_2_0_bgImage_property);
        AttributeValue img_2_2_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,yellow_space_image.getId(),img_2_2_0_bgImage_property,img_2_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_2_0_bgImage_property_value);


        ALCityInstanceInPL img_3_2_1 = new ALCityInstanceInPL("img_3_2_1",3,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_2_1);
        Attribute img_3_2_1_bgImage_property =new Attribute("bgImage",img_3_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_2_1_bgImage_property);
        AttributeValue img_3_2_1_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,white_space_image.getId(),img_3_2_1_bgImage_property,img_3_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_2_1_bgImage_property_value);


        ALCityInstanceInPL img_3_2_0 = new ALCityInstanceInPL("img_3_2_0",3,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_2_0);
        Attribute img_3_2_0_bgImage_property =new Attribute("bgImage",img_3_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_2_0_bgImage_property);
        AttributeValue img_3_2_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,yellow_space_image.getId(),img_3_2_0_bgImage_property,img_3_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_2_0_bgImage_property_value);


        ALCityInstanceInPL img_4_2_1 = new ALCityInstanceInPL("img_4_2_1",4,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_2_1);
        Attribute img_4_2_1_bgImage_property =new Attribute("bgImage",img_4_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_2_1_bgImage_property);
        AttributeValue img_4_2_1_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,white_space_image.getId(),img_4_2_1_bgImage_property,img_4_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_2_1_bgImage_property_value);


        ALCityInstanceInPL img_4_2_0 = new ALCityInstanceInPL("img_4_2_0",4,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_2_0);
        Attribute img_4_2_0_bgImage_property =new Attribute("bgImage",img_4_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_2_0_bgImage_property);
        AttributeValue img_4_2_0_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,yellow_space_image.getId(),img_4_2_0_bgImage_property,img_4_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_2_0_bgImage_property_value);

        ALCityInstanceInPL img_5_2_1 = new ALCityInstanceInPL("img_5_2_1",5,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_2_1);
        Attribute img_5_2_1_bgImage_property =new Attribute("bgImage",img_5_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_2_1_bgImage_property);
        AttributeValue img_5_2_1_bgImage_property_value= new AttributeValue(null,null,null,null,null,null,white_space_image.getId(),img_5_2_1_bgImage_property,img_5_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_2_1_bgImage_property_value);

        ALCityInstanceInPL img_5_2_0 = new ALCityInstanceInPL("img_5_2_0",5,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_2_0);
        Attribute img_5_2_0_bgImage_property =new Attribute("bgImage",img_5_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_2_0_bgImage_property);
        AttributeValue img_5_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_5_2_0_bgImage_property,img_5_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_2_0_bgImage_property_value);


        ALCityInstanceInPL img_6_2_1 = new ALCityInstanceInPL("img_6_2_1",6,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_2_1);
        Attribute img_6_2_1_bgImage_property =new Attribute("bgImage",img_6_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_2_1_bgImage_property);
        AttributeValue img_6_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_2_1_bgImage_property,img_6_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_2_1_bgImage_property_value);


        ALCityInstanceInPL img_6_2_0 = new ALCityInstanceInPL("img_6_2_0",6,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_2_0);
        Attribute img_6_2_0_bgImage_property =new Attribute("bgImage",img_6_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_2_0_bgImage_property);
        AttributeValue img_6_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_2_0_bgImage_property,img_6_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_2_0_bgImage_property_value);



        ALCityInstanceInPL img_7_2_1 = new ALCityInstanceInPL("img_7_2_1",7,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_2_1);
        Attribute img_7_2_1_bgImage_property =new Attribute("bgImage",img_7_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_2_1_bgImage_property);
        AttributeValue img_7_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_7_2_1_bgImage_property,img_7_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_2_1_bgImage_property_value);


        ALCityInstanceInPL img_7_2_0 = new ALCityInstanceInPL("img_7_2_0",7,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_2_0);
        Attribute img_7_2_0_bgImage_property =new Attribute("bgImage",img_7_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_2_0_bgImage_property);
        AttributeValue img_7_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_7_2_0_bgImage_property,img_7_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_2_0_bgImage_property_value);


        ALCityInstanceInPL img_8_2_1 = new ALCityInstanceInPL("img_8_2_1",8,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_2_1);
        Attribute img_8_2_1_bgImage_property =new Attribute("bgImage",img_8_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_2_1_bgImage_property);
        AttributeValue img_8_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_2_1_bgImage_property,img_8_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_2_1_bgImage_property_value);


        ALCityInstanceInPL img_8_2_0 = new ALCityInstanceInPL("img_8_2_0",8,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_2_0);
        Attribute img_8_2_0_bgImage_property =new Attribute("bgImage",img_8_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_2_0_bgImage_property);
        AttributeValue img_8_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_2_0_bgImage_property,img_8_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_2_0_bgImage_property_value);


        ALCityInstanceInPL img_9_2_0 = new ALCityInstanceInPL("img_9_2_0",9,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_2_0);
        Attribute img_9_2_0_bgImage_property =new Attribute("bgImage",img_8_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_2_0_bgImage_property);
        AttributeValue img_9_2_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_2_0_bgImage_property,img_9_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_2_0_bgImage_property_value);


        ALCityInstanceInPL img_10_2_1 = new ALCityInstanceInPL("img_10_2_1",10,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_2_1);
        Attribute img_10_2_1_bgImage_property =new Attribute("bgImage",img_10_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_2_1_bgImage_property);
        AttributeValue img_10_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_2_1_bgImage_property,img_10_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_2_1_bgImage_property_value);



        ALCityInstanceInPL img_10_2_0 = new ALCityInstanceInPL("img_10_2_0",10,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_2_0);
        Attribute img_10_2_0_bgImage_property =new Attribute("bgImage",img_10_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_2_0_bgImage_property);
        AttributeValue img_10_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_2_0_bgImage_property,img_10_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_2_0_bgImage_property_value);


        ALCityInstanceInPL img_11_2_0 = new ALCityInstanceInPL("img_11_2_0",11,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_2_0);
        Attribute img_11_2_0_bgImage_property =new Attribute("bgImage",img_11_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_2_0_bgImage_property);
        AttributeValue img_11_2_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_11_2_0_bgImage_property,img_11_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_2_0_bgImage_property_value);
        Attribute img_11_2_0_locked_variable =new Attribute("Locked",img_11_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_2_0_locked_variable);
        AttributeValue img_11_2_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_11_2_0_locked_variable,img_11_2_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_2_0_locked_variable_value);



        ALCityInstanceInPL img_12_2_1 = new ALCityInstanceInPL("img_12_2_1",12,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_2_1);
        Attribute img_12_2_1_bgImage_property =new Attribute("bgImage",img_12_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_2_1_bgImage_property);
        AttributeValue img_12_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_2_1_bgImage_property,img_12_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_2_1_bgImage_property_value);


        ALCityInstanceInPL img_12_2_0 = new ALCityInstanceInPL("img_12_2_0",12,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_2_0);
        Attribute img_12_2_0_bgImage_property =new Attribute("bgImage",img_12_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_2_0_bgImage_property);
        AttributeValue img_12_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_2_0_bgImage_property,img_12_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_2_0_bgImage_property_value);


        ALCityInstanceInPL img_13_2_1 = new ALCityInstanceInPL("img_13_2_1",13,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_2_1);
        Attribute img_13_2_1_bgImage_property =new Attribute("bgImage",img_13_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_2_1_bgImage_property);
        AttributeValue img_13_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_13_2_1_bgImage_property,img_13_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_2_1_bgImage_property_value);


        ALCityInstanceInPL img_13_2_0 = new ALCityInstanceInPL("img_13_2_0",13,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_2_0);
        Attribute img_13_2_0_bgImage_property =new Attribute("bgImage",img_13_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_2_0_bgImage_property);
        AttributeValue img_13_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_13_2_0_bgImage_property,img_13_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_2_0_bgImage_property_value);


        ALCityInstanceInPL img_14_2_1 = new ALCityInstanceInPL("img_14_2_1",14,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_2_1);
        Attribute img_14_2_1_bgImage_property =new Attribute("bgImage",img_14_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_2_1_bgImage_property);
        AttributeValue img_14_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_2_1_bgImage_property,img_14_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_2_1_bgImage_property_value);


        ALCityInstanceInPL img_14_2_0 = new ALCityInstanceInPL("img_14_2_0",14,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_2_0);
        Attribute img_14_2_0_bgImage_property =new Attribute("bgImage",img_14_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_2_0_bgImage_property);
        AttributeValue img_14_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_2_0_bgImage_property,img_14_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_2_0_bgImage_property_value);


        ALCityInstanceInPL img_15_2_0 = new ALCityInstanceInPL("img_15_2_0",15,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_2_0);
        Attribute img_15_2_0_bgImage_property =new Attribute("bgImage",img_15_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_2_0_bgImage_property);
        AttributeValue img_15_2_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_2_0_bgImage_property,img_15_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_2_0_bgImage_property_value);
        Attribute img_15_2_0_locked_variable =new Attribute("Locked",img_15_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_2_0_locked_variable);
        AttributeValue img_15_2_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_2_0_locked_variable,img_15_2_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_2_0_locked_variable_value);




        ALCityInstanceInPL img_16_2_1 = new ALCityInstanceInPL("img_16_2_1",16,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_2_1);
        Attribute img_16_2_1_bgImage_property =new Attribute("bgImage",img_16_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_2_1_bgImage_property);
        AttributeValue img_16_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_2_1_bgImage_property,img_16_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_2_1_bgImage_property_value);


        ALCityInstanceInPL img_16_2_0 = new ALCityInstanceInPL("img_16_2_0",16,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_2_0);
        Attribute img_16_2_0_bgImage_property =new Attribute("bgImage",img_16_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_2_0_bgImage_property);
        AttributeValue img_16_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_16_2_0_bgImage_property,img_16_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_2_0_bgImage_property_value);


        ALCityInstanceInPL img_17_2_1 = new ALCityInstanceInPL("img_17_2_1",17,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_2_1);
        Attribute img_17_2_1_bgImage_property =new Attribute("bgImage",img_17_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_2_1_bgImage_property);
        AttributeValue img_17_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_17_2_1_bgImage_property,img_17_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_2_1_bgImage_property_value);



        ALCityInstanceInPL img_17_2_0 = new ALCityInstanceInPL("img_17_2_0",17,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_2_0);
        Attribute img_17_2_0_bgImage_property =new Attribute("bgImage",img_17_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_2_0_bgImage_property);
        AttributeValue img_17_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_17_2_0_bgImage_property,img_17_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_2_0_bgImage_property_value);


        ALCityInstanceInPL img_18_2_1 = new ALCityInstanceInPL("img_18_2_1",18,2,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_2_1);
        Attribute img_18_2_1_bgImage_property =new Attribute("bgImage",img_18_2_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_2_1_bgImage_property);
        AttributeValue img_18_2_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_2_1_bgImage_property,img_18_2_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_2_1_bgImage_property_value);



        ALCityInstanceInPL img_18_2_0 = new ALCityInstanceInPL("img_18_2_0",18,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_2_0);
        Attribute img_18_2_0_bgImage_property =new Attribute("bgImage",img_18_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_2_0_bgImage_property);
        AttributeValue img_18_2_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_2_0_bgImage_property,img_18_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_2_0_bgImage_property_value);


        ALCityInstanceInPL img_19_2_0 = new ALCityInstanceInPL("img_19_2_0",19,2,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_2_0);
        Attribute img_19_2_0_bgImage_property =new Attribute("bgImage",img_19_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_2_0_bgImage_property);
        AttributeValue img_19_2_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_2_0_bgImage_property,img_19_2_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_2_0_bgImage_property_value);
        Attribute img_19_2_0_locked_variable =new Attribute("Locked",img_19_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_2_0_locked_variable);
        AttributeValue img_19_2_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_2_0_locked_variable,img_19_2_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_2_0_locked_variable_value);


        ALCityInstanceInPL img_1_3_0 = new ALCityInstanceInPL("img_1_3_0",1,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_3_0);
        Attribute img_1_3_0_bgImage_property =new Attribute("bgImage",img_1_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_3_0_bgImage_property);
        AttributeValue img_1_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_3_0_bgImage_property,img_1_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_3_0_bgImage_property_value);
        Attribute img_1_3_0_locked_variable =new Attribute("Locked",img_19_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_3_0_locked_variable);
        AttributeValue img_1_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_3_0_locked_variable,img_1_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_3_0_locked_variable_value);


        ALCityInstanceInPL img_2_3_0 = new ALCityInstanceInPL("img_2_3_0",2,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_3_0);
        Attribute img_2_3_0_bgImage_property =new Attribute("bgImage",img_2_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_3_0_bgImage_property);
        AttributeValue img_2_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_2_3_0_bgImage_property,img_2_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_3_0_bgImage_property_value);
        Attribute img_2_3_0_locked_variable =new Attribute("Locked",img_2_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_3_0_locked_variable);
        AttributeValue img_2_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_2_3_0_locked_variable,img_2_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_3_0_locked_variable_value);



        ALCityInstanceInPL img_3_3_0 = new ALCityInstanceInPL("img_3_3_0",3,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_3_0);
        Attribute img_3_3_0_bgImage_property =new Attribute("bgImage",img_3_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_3_0_bgImage_property);
        AttributeValue img_3_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_3_3_0_bgImage_property,img_3_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_3_0_bgImage_property_value);
        Attribute img_3_3_0_locked_variable =new Attribute("Locked",img_19_2_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_3_0_locked_variable);
        AttributeValue img_3_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_3_3_0_locked_variable,img_3_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_3_0_locked_variable_value);


        ALCityInstanceInPL img_4_3_1 = new ALCityInstanceInPL("img_4_3_1",4,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_3_1);
        Attribute img_4_3_1_bgImage_property =new Attribute("bgImage",img_4_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_3_1_bgImage_property);
        AttributeValue img_4_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_3_1_bgImage_property,img_4_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_3_1_bgImage_property_value);


        ALCityInstanceInPL img_4_3_0 = new ALCityInstanceInPL("img_4_3_0",4,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_3_0);
        Attribute img_4_3_0_bgImage_property =new Attribute("bgImage",img_4_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_3_0_bgImage_property);
        AttributeValue img_4_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_3_0_bgImage_property,img_4_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_3_0_bgImage_property_value);


        ALCityInstanceInPL img_5_3_0 = new ALCityInstanceInPL("img_5_3_0",5,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_3_0);
        Attribute img_5_3_0_bgImage_property =new Attribute("bgImage",img_5_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_3_0_bgImage_property);
        AttributeValue img_5_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_3_0_bgImage_property,img_5_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_3_0_bgImage_property_value);
        Attribute img_5_3_0_locked_variable =new Attribute("Locked",img_5_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_3_0_locked_variable);
        AttributeValue img_5_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_3_0_locked_variable,img_5_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_3_0_locked_variable_value);


        ALCityInstanceInPL img_6_3_0 = new ALCityInstanceInPL("img_6_3_0",6,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_3_0);
        Attribute img_6_3_0_bgImage_property =new Attribute("bgImage",img_6_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_3_0_bgImage_property);
        AttributeValue img_6_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_6_3_0_bgImage_property,img_6_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_3_0_bgImage_property_value);
        Attribute img_6_3_0_locked_variable =new Attribute("Locked",img_6_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_3_0_locked_variable);
        AttributeValue img_6_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_6_3_0_locked_variable,img_6_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_3_0_locked_variable_value);


        ALCityInstanceInPL img_7_3_0 = new ALCityInstanceInPL("img_7_3_0",7,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_3_0);
        Attribute img_7_3_0_bgImage_property =new Attribute("bgImage",img_7_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_3_0_bgImage_property);
        AttributeValue img_7_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_3_0_bgImage_property,img_7_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_3_0_bgImage_property_value);
        Attribute img_7_3_0_locked_variable =new Attribute("Locked",img_7_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_3_0_locked_variable);
        AttributeValue img_7_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_3_0_locked_variable,img_7_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_3_0_locked_variable_value);


        ALCityInstanceInPL img_8_3_1 = new ALCityInstanceInPL("img_8_3_1",8,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_3_1);
        Attribute img_8_3_1_bgImage_property =new Attribute("bgImage",img_8_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_3_1_bgImage_property);
        AttributeValue img_8_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_3_1_bgImage_property,img_8_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_3_1_bgImage_property_value);


        ALCityInstanceInPL img_8_3_0 = new ALCityInstanceInPL("img_8_3_0",8,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_3_0);
        Attribute img_8_3_0_bgImage_property =new Attribute("bgImage",img_8_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_3_0_bgImage_property);
        AttributeValue img_8_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_3_0_bgImage_property,img_8_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_3_0_bgImage_property_value);


        ALCityInstanceInPL img_9_3_0 = new ALCityInstanceInPL("img_9_3_0",9,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_3_0);
        Attribute img_9_3_0_bgImage_property =new Attribute("bgImage",img_9_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_3_0_bgImage_property);
        AttributeValue img_9_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_3_0_bgImage_property,img_9_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_3_0_bgImage_property_value);
        Attribute img_9_3_0_locked_variable =new Attribute("Locked",img_9_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_3_0_locked_variable);
        AttributeValue img_9_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_3_0_locked_variable,img_9_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_3_0_locked_variable_value);


        ALCityInstanceInPL img_10_3_1 = new ALCityInstanceInPL("img_10_3_1",10,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_3_1);
        Attribute img_10_3_1_bgImage_property =new Attribute("bgImage",img_10_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_3_1_bgImage_property);
        AttributeValue img_10_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_3_1_bgImage_property,img_10_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_3_1_bgImage_property_value);


        ALCityInstanceInPL img_10_3_0 = new ALCityInstanceInPL("img_10_3_0",10,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_3_0);
        Attribute img_10_3_0_bgImage_property =new Attribute("bgImage",img_10_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_3_0_bgImage_property);
        AttributeValue img_10_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_3_0_bgImage_property,img_10_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_3_0_bgImage_property_value);


        ALCityInstanceInPL img_11_3_0 = new ALCityInstanceInPL("img_11_3_0",11,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_3_0);
        Attribute img_11_3_0_bgImage_property =new Attribute("bgImage",img_11_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_3_0_bgImage_property);
        AttributeValue img_11_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_11_3_0_bgImage_property,img_11_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_3_0_bgImage_property_value);
        Attribute img_11_3_0_locked_variable =new Attribute("Locked",img_11_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_3_0_locked_variable);
        AttributeValue img_11_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_11_3_0_locked_variable,img_11_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_3_0_locked_variable_value);



        ALCityInstanceInPL img_12_3_1 = new ALCityInstanceInPL("img_12_3_1",12,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_3_1);
        Attribute img_12_3_1_bgImage_property =new Attribute("bgImage",img_12_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_3_1_bgImage_property);
        AttributeValue img_12_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_3_1_bgImage_property,img_12_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_3_1_bgImage_property_value);


        ALCityInstanceInPL img_12_3_0 = new ALCityInstanceInPL("img_12_3_0",12,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_3_0);
        Attribute img_12_3_0_bgImage_property =new Attribute("bgImage",img_12_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_3_0_bgImage_property);
        AttributeValue img_12_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_3_0_bgImage_property,img_12_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_3_0_bgImage_property_value);


        ALCityInstanceInPL img_13_3_0 = new ALCityInstanceInPL("img_13_3_0",13,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_3_0);
        Attribute img_13_3_0_bgImage_property =new Attribute("bgImage",img_13_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_3_0_bgImage_property);
        AttributeValue img_13_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_3_0_bgImage_property,img_13_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_3_0_bgImage_property_value);
        Attribute img_13_3_0_locked_variable =new Attribute("Locked",img_13_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_3_0_locked_variable);
        AttributeValue img_13_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_3_0_locked_variable,img_13_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_3_0_locked_variable_value);


        ALCityInstanceInPL img_14_3_1 = new ALCityInstanceInPL("img_14_3_1",14,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_3_1);
        Attribute img_14_3_1_bgImage_property =new Attribute("bgImage",img_14_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_3_1_bgImage_property);
        AttributeValue img_14_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_3_1_bgImage_property,img_14_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_3_1_bgImage_property_value);


        ALCityInstanceInPL img_14_3_0 = new ALCityInstanceInPL("img_14_3_0",14,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_3_0);
        Attribute img_14_3_0_bgImage_property =new Attribute("bgImage",img_14_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_3_0_bgImage_property);
        AttributeValue img_14_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_3_0_bgImage_property,img_14_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_3_0_bgImage_property_value);


        ALCityInstanceInPL img_15_3_0 = new ALCityInstanceInPL("img_15_3_0",15,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_3_0);
        Attribute img_15_3_0_bgImage_property =new Attribute("bgImage",img_15_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_3_0_bgImage_property);
        AttributeValue img_15_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_3_0_bgImage_property,img_15_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_3_0_bgImage_property_value);
        Attribute img_15_3_0_locked_variable =new Attribute("Locked",img_15_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_3_0_locked_variable);
        AttributeValue img_15_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_3_0_locked_variable,img_15_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_3_0_locked_variable_value);


        ALCityInstanceInPL img_16_3_1 = new ALCityInstanceInPL("img_16_3_1",16,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_3_1);
        Attribute img_16_3_1_bgImage_property =new Attribute("bgImage",img_16_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_3_1_bgImage_property);
        AttributeValue img_16_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_3_1_bgImage_property,img_16_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_3_1_bgImage_property_value);


        ALCityInstanceInPL img_16_3_0 = new ALCityInstanceInPL("img_16_3_0",16,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_3_0);
        Attribute img_16_3_0_bgImage_property =new Attribute("bgImage",img_16_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_3_0_bgImage_property);
        AttributeValue img_16_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_16_3_0_bgImage_property,img_16_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_3_0_bgImage_property_value);


        ALCityInstanceInPL img_17_3_0 = new ALCityInstanceInPL("img_17_3_0",17,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_3_0);
        Attribute img_17_3_0_bgImage_property =new Attribute("bgImage",img_17_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_3_0_bgImage_property);
        AttributeValue img_17_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_17_3_0_bgImage_property,img_17_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_3_0_bgImage_property_value);
        Attribute img_17_3_0_locked_variable =new Attribute("Locked",img_17_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_3_0_locked_variable);
        AttributeValue img_17_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_17_3_0_locked_variable,img_17_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_3_0_locked_variable_value);


        ALCityInstanceInPL img_18_3_1 = new ALCityInstanceInPL("img_18_3_1",18,3,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_3_1);
        Attribute img_18_3_1_bgImage_property =new Attribute("bgImage",img_18_3_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_3_1_bgImage_property);
        AttributeValue img_18_3_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_3_1_bgImage_property,img_18_3_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_3_1_bgImage_property_value);


        ALCityInstanceInPL img_18_3_0 = new ALCityInstanceInPL("img_18_3_0",18,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_3_0);
        Attribute img_18_3_0_bgImage_property =new Attribute("bgImage",img_18_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_3_0_bgImage_property);
        AttributeValue img_18_3_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_3_0_bgImage_property,img_18_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_3_0_bgImage_property_value);

        ALCityInstanceInPL img_19_3_0 = new ALCityInstanceInPL("img_19_3_0",19,3,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_3_0);
        Attribute img_19_3_0_bgImage_property =new Attribute("bgImage",img_19_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_3_0_bgImage_property);
        AttributeValue img_19_3_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_3_0_bgImage_property,img_19_3_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_3_0_bgImage_property_value);
        Attribute img_19_3_0_locked_variable =new Attribute("Locked",img_19_3_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_3_0_locked_variable);
        AttributeValue img_19_3_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_3_0_locked_variable,img_19_3_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_3_0_locked_variable_value);



        ALCityInstanceInPL img_1_4_0 = new ALCityInstanceInPL("img_1_4_0",1,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_4_0);
        Attribute img_1_4_0_bgImage_property =new Attribute("bgImage",img_1_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_4_0_bgImage_property);
        AttributeValue img_1_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_4_0_bgImage_property,img_1_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_4_0_bgImage_property_value);
        Attribute img_1_4_0_locked_variable =new Attribute("Locked",img_1_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_4_0_locked_variable);
        AttributeValue img_1_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_4_0_locked_variable,img_1_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_4_0_locked_variable_value);



        ALCityInstanceInPL img_2_4_1 = new ALCityInstanceInPL("img_2_4_1",2,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_4_1);
        Attribute img_2_4_1_bgImage_property =new Attribute("img_2_4_1",img_2_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_4_1_bgImage_property);
        AttributeValue img_2_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_4_1_bgImage_property,img_2_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_4_1_bgImage_property_value);



        ALCityInstanceInPL img_2_4_0 = new ALCityInstanceInPL("img_2_4_0",2,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_4_0);
        Attribute img_2_4_0_bgImage_property =new Attribute("img_2_4_0",img_2_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_4_0_bgImage_property);
        AttributeValue img_2_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_4_0_bgImage_property,img_2_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_4_0_bgImage_property_value);


        ALCityInstanceInPL img_3_4_1 = new ALCityInstanceInPL("img_3_4_1",3,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_4_1);
        Attribute img_3_4_1_bgImage_property =new Attribute("img_3_4_1",img_3_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_4_1_bgImage_property);
        AttributeValue img_3_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_3_4_1_bgImage_property,img_3_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_4_1_bgImage_property_value);


        ALCityInstanceInPL img_3_4_0 = new ALCityInstanceInPL("img_3_4_0",3,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_4_0);
        Attribute img_3_4_0_bgImage_property =new Attribute("img_3_4_0",img_3_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_4_0_bgImage_property);
        AttributeValue img_3_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_3_4_0_bgImage_property,img_3_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_4_0_bgImage_property_value);

        ALCityInstanceInPL img_4_4_1 = new ALCityInstanceInPL("img_4_4_1",4,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_4_1);
        Attribute img_4_4_1_bgImage_property =new Attribute("img_4_4_1",img_4_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_4_1_bgImage_property);
        AttributeValue img_4_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_4_1_bgImage_property,img_4_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_4_1_bgImage_property_value);


        ALCityInstanceInPL img_4_4_0 = new ALCityInstanceInPL("img_4_4_0",4,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_4_0);
        Attribute img_4_4_0_bgImage_property =new Attribute("img_4_4_0",img_4_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_4_0_bgImage_property);
        AttributeValue img_4_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_4_0_bgImage_property,img_4_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_4_0_bgImage_property_value);


        ALCityInstanceInPL img_5_4_0 = new ALCityInstanceInPL("img_5_4_0",5,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_4_0);
        Attribute img_5_4_0_bgImage_property =new Attribute("bgImage",img_5_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_4_0_bgImage_property);
        AttributeValue img_5_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_4_0_bgImage_property,img_5_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_4_0_bgImage_property_value);
        Attribute img_5_4_0_locked_variable =new Attribute("Locked",img_5_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_4_0_locked_variable);
        AttributeValue img_5_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_4_0_locked_variable,img_5_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_4_0_locked_variable_value);


        ALCityInstanceInPL img_6_4_1 = new ALCityInstanceInPL("img_6_4_1",6,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_4_1);
        Attribute img_6_4_1_bgImage_property =new Attribute("bgImage",img_6_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_4_1_bgImage_property);
        AttributeValue img_6_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_4_1_bgImage_property,img_6_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_4_1_bgImage_property_value);



        ALCityInstanceInPL img_6_4_0 = new ALCityInstanceInPL("img_6_4_0",6,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_4_0);
        Attribute img_6_4_0_bgImage_property =new Attribute("bgImage",img_6_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_4_0_bgImage_property);
        AttributeValue img_6_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_4_0_bgImage_property,img_6_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_4_0_bgImage_property_value);


        ALCityInstanceInPL img_7_4_0 = new ALCityInstanceInPL("img_7_4_0",7,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_4_0);
        Attribute img_7_4_0_bgImage_property =new Attribute("bgImage",img_7_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_4_0_bgImage_property);
        AttributeValue img_7_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_4_0_bgImage_property,img_7_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_4_0_bgImage_property_value);
        Attribute img_7_4_0_locked_variable =new Attribute("Locked",img_7_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_4_0_locked_variable);
        AttributeValue img_7_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_4_0_locked_variable,img_7_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_4_0_locked_variable_value);


        ALCityInstanceInPL img_8_4_1 = new ALCityInstanceInPL("img_8_4_1",8,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_4_1);
        Attribute img_8_4_1_bgImage_property =new Attribute("bgImage",img_8_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_4_1_bgImage_property);
        AttributeValue img_8_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_4_1_bgImage_property,img_8_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_4_1_bgImage_property_value);


        ALCityInstanceInPL img_8_4_0 = new ALCityInstanceInPL("img_8_4_0",8,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_4_0);
        Attribute img_8_4_0_bgImage_property =new Attribute("bgImage",img_8_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_4_0_bgImage_property);
        AttributeValue img_8_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_4_0_bgImage_property,img_8_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_4_0_bgImage_property_value);


        ALCityInstanceInPL img_9_4_1 = new ALCityInstanceInPL("img_9_4_1",9,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_4_1);
        Attribute img_9_4_1_bgImage_property =new Attribute("bgImage",img_9_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_4_1_bgImage_property);
        AttributeValue img_9_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_9_4_1_bgImage_property,img_9_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_4_1_bgImage_property_value);


        ALCityInstanceInPL img_9_4_0 = new ALCityInstanceInPL("img_9_4_0",9,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_4_0);
        Attribute img_9_4_0_bgImage_property =new Attribute("bgImage",img_9_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_4_0_bgImage_property);
        AttributeValue img_9_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_9_4_0_bgImage_property,img_9_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_4_0_bgImage_property_value);

        ALCityInstanceInPL img_10_4_1 = new ALCityInstanceInPL("img_10_4_1",10,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_4_1);
        Attribute img_10_4_1_bgImage_property =new Attribute("bgImage",img_10_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_4_1_bgImage_property);
        AttributeValue img_10_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_4_1_bgImage_property,img_10_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_4_1_bgImage_property_value);


        ALCityInstanceInPL img_10_4_0 = new ALCityInstanceInPL("img_10_4_0",10,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_4_0);
        Attribute img_10_4_0_bgImage_property =new Attribute("bgImage",img_10_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_4_0_bgImage_property);
        AttributeValue img_10_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_4_0_bgImage_property,img_10_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_4_0_bgImage_property_value);


        ALCityInstanceInPL img_11_4_1 = new ALCityInstanceInPL("img_11_4_1",11,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_4_1);
        Attribute img_11_4_1_bgImage_property =new Attribute("bgImage",img_11_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_4_1_bgImage_property);
        AttributeValue img_11_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_11_4_1_bgImage_property,img_11_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_4_1_bgImage_property_value);


        ALCityInstanceInPL img_11_4_0 = new ALCityInstanceInPL("img_11_4_0",11,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_4_0);
        Attribute img_11_4_0_bgImage_property =new Attribute("bgImage",img_11_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_4_0_bgImage_property);
        AttributeValue img_11_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_11_4_0_bgImage_property,img_11_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_4_0_bgImage_property_value);


        ALCityInstanceInPL img_12_4_1 = new ALCityInstanceInPL("img_12_4_1",12,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_4_1);
        Attribute img_12_4_1_bgImage_property =new Attribute("bgImage",img_11_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_4_1_bgImage_property);
        AttributeValue img_12_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_4_1_bgImage_property,img_12_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_4_1_bgImage_property_value);


        ALCityInstanceInPL img_12_4_0 = new ALCityInstanceInPL("img_12_4_0",12,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_4_0);
        Attribute img_12_4_0_bgImage_property =new Attribute("bgImage",img_12_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_4_0_bgImage_property);
        AttributeValue img_12_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_4_0_bgImage_property,img_12_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_4_0_bgImage_property_value);


        ALCityInstanceInPL img_13_4_0 = new ALCityInstanceInPL("img_13_4_0",13,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_4_0);
        Attribute img_13_4_0_bgImage_property =new Attribute("bgImage",img_13_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_4_0_bgImage_property);
        AttributeValue img_13_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_4_0_bgImage_property,img_13_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_4_0_bgImage_property_value);
        Attribute img_13_4_0_locked_variable =new Attribute("Locked",img_13_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_4_0_locked_variable);
        AttributeValue img_13_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_4_0_locked_variable,img_13_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_4_0_locked_variable_value);



        ALCityInstanceInPL img_14_4_1 = new ALCityInstanceInPL("img_14_4_1",14,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_4_1);
        Attribute img_14_4_1_bgImage_property =new Attribute("bgImage",img_14_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_4_1_bgImage_property);
        AttributeValue img_14_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_4_1_bgImage_property,img_14_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_4_1_bgImage_property_value);


//        PGObjectInstance img_13_4_1 = new PGObjectInstance("img_13_4_1",13,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
//        pgObjectInstanceService.save(img_13_4_1);
//        Attribute img_13_4_1_bgImage_property =new Attribute("bgImage",img_13_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(img_13_4_1_bgImage_property);
//        AttributeValue img_13_4_1_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_13_4_1_bgImage_property,img_13_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(img_13_4_1_bgImage_property_value);


        ALCityInstanceInPL img_14_4_0 = new ALCityInstanceInPL("img_14_4_0",14,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_4_0);
        Attribute img_14_4_0_bgImage_property =new Attribute("bgImage",img_14_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_4_0_bgImage_property);
        AttributeValue img_14_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_4_0_bgImage_property,img_14_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_4_0_bgImage_property_value);


        ALCityInstanceInPL img_15_4_0 = new ALCityInstanceInPL("img_15_4_0",15,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_4_0);
        Attribute img_15_4_0_bgImage_property =new Attribute("bgImage",img_15_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_4_0_bgImage_property);
        AttributeValue img_15_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_4_0_bgImage_property,img_15_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_4_0_bgImage_property_value);
        Attribute img_15_4_0_locked_variable =new Attribute("Locked",img_15_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_4_0_locked_variable);
        AttributeValue img_15_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_4_0_locked_variable,img_15_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_4_0_locked_variable_value);


        ALCityInstanceInPL img_16_4_1 = new ALCityInstanceInPL("img_16_4_1",16,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_4_1);
        Attribute img_16_4_1_bgImage_property =new Attribute("bgImage",img_16_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_4_1_bgImage_property);
        AttributeValue img_16_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_4_1_bgImage_property,img_16_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_4_1_bgImage_property_value);



        ALCityInstanceInPL img_16_4_0 = new ALCityInstanceInPL("img_16_4_0",16,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_4_0);
        Attribute img_16_4_0_bgImage_property =new Attribute("bgImage",img_16_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_4_0_bgImage_property);
        AttributeValue img_16_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_16_4_0_bgImage_property,img_16_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_4_0_bgImage_property_value);


        ALCityInstanceInPL img_17_4_0 = new ALCityInstanceInPL("img_17_4_0",17,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_4_0);
        Attribute img_17_4_0_bgImage_property =new Attribute("bgImage",img_17_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_4_0_bgImage_property);
        AttributeValue img_17_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_17_4_0_bgImage_property,img_17_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_4_0_bgImage_property_value);
        Attribute img_17_4_0_locked_variable =new Attribute("Locked",img_17_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_4_0_locked_variable);
        AttributeValue img_17_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_17_4_0_locked_variable,img_17_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_4_0_locked_variable_value);



        ALCityInstanceInPL img_18_4_1 = new ALCityInstanceInPL("img_18_4_1",18,4,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_4_1);
        Attribute img_18_4_1_bgImage_property =new Attribute("bgImage",img_18_4_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_4_1_bgImage_property);
        AttributeValue img_18_4_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_4_1_bgImage_property,img_18_4_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_4_1_bgImage_property_value);


        ALCityInstanceInPL img_18_4_0 = new ALCityInstanceInPL("img_18_4_0",18,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_4_0);
        Attribute img_18_4_0_bgImage_property =new Attribute("bgImage",img_18_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_4_0_bgImage_property);
        AttributeValue img_18_4_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_4_0_bgImage_property,img_18_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_4_0_bgImage_property_value);

        ALCityInstanceInPL img_19_4_0 = new ALCityInstanceInPL("img_19_4_0",19,4,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_4_0);
        Attribute img_19_4_0_bgImage_property =new Attribute("bgImage",img_19_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_4_0_bgImage_property);
        AttributeValue img_19_4_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_4_0_bgImage_property,img_19_4_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_4_0_bgImage_property_value);
        Attribute img_19_4_0_locked_variable =new Attribute("Locked",img_19_4_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_4_0_locked_variable);
        AttributeValue img_19_4_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_4_0_locked_variable,img_19_4_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_4_0_locked_variable_value);



        ALCityInstanceInPL img_1_5_0 = new ALCityInstanceInPL("img_1_5_0",1,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_5_0);
        Attribute img_1_5_0_bgImage_property =new Attribute("bgImage",img_1_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_5_0_bgImage_property);
        AttributeValue img_1_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_5_0_bgImage_property,img_1_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_5_0_bgImage_property_value);
        Attribute img_1_5_0_locked_variable =new Attribute("Locked",img_1_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_5_0_locked_variable);
        AttributeValue img_1_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_5_0_locked_variable,img_1_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_5_0_locked_variable_value);


        ALCityInstanceInPL img_2_5_1 = new ALCityInstanceInPL("img_2_5_1",2,5,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_5_1);
        Attribute img_2_5_1_bgImage_property =new Attribute("bgImage",img_2_5_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_5_1_bgImage_property);
        AttributeValue img_2_5_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_5_1_bgImage_property,img_2_5_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_5_1_bgImage_property_value);


        ALCityInstanceInPL img_2_5_0 = new ALCityInstanceInPL("img_2_5_0",2,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_5_0);
        Attribute img_2_5_0_bgImage_property =new Attribute("bgImage",img_2_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_5_0_bgImage_property);
        AttributeValue img_2_5_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_5_0_bgImage_property,img_2_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_5_0_bgImage_property_value);


        ALCityInstanceInPL img_3_5_0 = new ALCityInstanceInPL("img_3_5_0",3,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_5_0);
        Attribute img_3_5_0_bgImage_property =new Attribute("bgImage",img_3_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_5_0_bgImage_property);
        AttributeValue img_3_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_3_5_0_bgImage_property,img_3_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_5_0_bgImage_property_value);
        Attribute img_3_5_0_locked_variable =new Attribute("Locked",img_3_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_5_0_locked_variable);
        AttributeValue img_3_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_3_5_0_locked_variable,img_3_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_5_0_locked_variable_value);


        ALCityInstanceInPL img_4_5_0 = new ALCityInstanceInPL("img_4_5_0",4,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_5_0);
        Attribute img_4_5_0_bgImage_property =new Attribute("bgImage",img_4_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_5_0_bgImage_property);
        AttributeValue img_4_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_4_5_0_bgImage_property,img_4_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_5_0_bgImage_property_value);
        Attribute img_4_5_0_locked_variable =new Attribute("Locked",img_4_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_5_0_locked_variable);
        AttributeValue img_4_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_4_5_0_locked_variable,img_4_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_5_0_locked_variable_value);


        ALCityInstanceInPL img_5_5_0 = new ALCityInstanceInPL("img_5_5_0",5,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_5_0);
        Attribute img_5_5_0_bgImage_property =new Attribute("bgImage",img_5_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_5_0_bgImage_property);
        AttributeValue img_5_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_5_0_bgImage_property,img_5_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_5_0_bgImage_property_value);
        Attribute img_5_5_0_locked_variable =new Attribute("Locked",img_5_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_5_0_locked_variable);
        AttributeValue img_5_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_5_0_locked_variable,img_5_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_5_0_locked_variable_value);



        ALCityInstanceInPL img_6_5_1 = new ALCityInstanceInPL("img_6_5_1",6,5,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_5_1);
        Attribute img_6_5_1_bgImage_property =new Attribute("bgImage",img_6_5_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_5_1_bgImage_property);
        AttributeValue img_6_5_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_5_1_bgImage_property,img_6_5_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_5_1_bgImage_property_value);


        ALCityInstanceInPL img_6_5_0 = new ALCityInstanceInPL("img_6_5_0",6,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_5_0);
        Attribute img_6_5_0_bgImage_property =new Attribute("bgImage",img_6_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_5_0_bgImage_property);
        AttributeValue img_6_5_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_5_0_bgImage_property,img_6_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_5_0_bgImage_property_value);


        ALCityInstanceInPL img_7_5_0 = new ALCityInstanceInPL("img_7_5_0",7,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_5_0);
        Attribute img_7_5_0_bgImage_property =new Attribute("bgImage",img_7_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_5_0_bgImage_property);
        AttributeValue img_7_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_5_0_bgImage_property,img_7_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_5_0_bgImage_property_value);
        Attribute img_7_5_0_locked_variable =new Attribute("Locked",img_7_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_5_0_locked_variable);
        AttributeValue img_7_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_5_0_locked_variable,img_7_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_5_0_locked_variable_value);


        ALCityInstanceInPL img_8_5_0 = new ALCityInstanceInPL("img_8_5_0",8,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_5_0);
        Attribute img_8_5_0_bgImage_property =new Attribute("bgImage",img_8_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_5_0_bgImage_property);
        AttributeValue img_8_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_8_5_0_bgImage_property,img_8_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_5_0_bgImage_property_value);
        Attribute img_8_5_0_locked_variable =new Attribute("Locked",img_8_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_5_0_locked_variable);
        AttributeValue img_8_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_8_5_0_locked_variable,img_8_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_5_0_locked_variable_value);


        ALCityInstanceInPL img_9_5_0 = new ALCityInstanceInPL("img_9_5_0",9,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_5_0);
        Attribute img_9_5_0_bgImage_property =new Attribute("bgImage",img_9_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_5_0_bgImage_property);
        AttributeValue img_9_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_5_0_bgImage_property,img_9_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_5_0_bgImage_property_value);
        Attribute img_9_5_0_locked_variable =new Attribute("Locked",img_9_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_5_0_locked_variable);
        AttributeValue img_9_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_5_0_locked_variable,img_9_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_5_0_locked_variable_value);


        ALCityInstanceInPL img_10_5_1 = new ALCityInstanceInPL("img_10_5_1",10,5,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_5_1);
        Attribute img_10_5_1_bgImage_property =new Attribute("bgImage",img_10_5_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_5_1_bgImage_property);
        AttributeValue img_10_5_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_5_1_bgImage_property,img_10_5_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_5_1_bgImage_property_value);

        ALCityInstanceInPL img_10_5_0 = new ALCityInstanceInPL("img_10_5_0",10,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_5_0);
        Attribute img_10_5_0_bgImage_property =new Attribute("bgImage",img_10_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_5_0_bgImage_property);
        AttributeValue img_10_5_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_5_0_bgImage_property,img_10_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_5_0_bgImage_property_value);

        ALCityInstanceInPL img_11_5_0 = new ALCityInstanceInPL("img_11_5_0",11,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_5_0);
        Attribute img_11_5_0_bgImage_property =new Attribute("bgImage",img_11_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_5_0_bgImage_property);
        AttributeValue img_11_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_11_5_0_bgImage_property,img_11_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_5_0_bgImage_property_value);
        Attribute img_11_5_0_locked_variable =new Attribute("Locked",img_11_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_5_0_locked_variable);
        AttributeValue img_11_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_11_5_0_locked_variable,img_11_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_5_0_locked_variable_value);


        ALCityInstanceInPL img_12_5_0 = new ALCityInstanceInPL("img_12_5_0",12,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_5_0);
        Attribute img_12_5_0_bgImage_property =new Attribute("bgImage",img_12_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_5_0_bgImage_property);
        AttributeValue img_12_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_12_5_0_bgImage_property,img_12_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_5_0_bgImage_property_value);
        Attribute img_12_5_0_locked_variable =new Attribute("Locked",img_12_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_5_0_locked_variable);
        AttributeValue img_12_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_12_5_0_locked_variable,img_12_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_5_0_locked_variable_value);


        ALCityInstanceInPL img_13_5_0 = new ALCityInstanceInPL("img_13_5_0",13,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_5_0);
        Attribute img_13_5_0_bgImage_property =new Attribute("bgImage",img_13_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_5_0_bgImage_property);
        AttributeValue img_13_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_5_0_bgImage_property,img_13_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_5_0_bgImage_property_value);
        Attribute img_13_5_0_locked_variable =new Attribute("Locked",img_13_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_5_0_locked_variable);
        AttributeValue img_13_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_5_0_locked_variable,img_13_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_5_0_locked_variable_value);


        ALCityInstanceInPL img_14_5_1 = new ALCityInstanceInPL("img_14_5_1",14,5,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_5_1);
        Attribute img_14_5_1_bgImage_property =new Attribute("bgImage",img_14_5_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_5_1_bgImage_property);
        AttributeValue img_14_5_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_5_1_bgImage_property,img_14_5_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_5_1_bgImage_property_value);


        ALCityInstanceInPL img_14_5_0 = new ALCityInstanceInPL("img_14_5_0",14,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_5_0);
        Attribute img_14_5_0_bgImage_property =new Attribute("bgImage",img_14_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_5_0_bgImage_property);
        AttributeValue img_14_5_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_5_0_bgImage_property,img_14_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_5_0_bgImage_property_value);


        ALCityInstanceInPL img_15_5_0 = new ALCityInstanceInPL("img_15_5_0",15,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_5_0);
        Attribute img_15_5_0_bgImage_property =new Attribute("bgImage",img_15_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_5_0_bgImage_property);
        AttributeValue img_15_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_5_0_bgImage_property,img_15_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_5_0_bgImage_property_value);
        Attribute img_15_5_0_locked_variable =new Attribute("Locked",img_15_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_5_0_locked_variable);
        AttributeValue img_15_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_5_0_locked_variable,img_15_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_5_0_locked_variable_value);


        ALCityInstanceInPL img_16_5_0 = new ALCityInstanceInPL("img_16_5_0",16,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_5_0);
        Attribute img_16_5_0_bgImage_property =new Attribute("bgImage",img_16_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_5_0_bgImage_property);
        AttributeValue img_16_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_16_5_0_bgImage_property,img_16_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_5_0_bgImage_property_value);
        Attribute img_16_5_0_locked_variable =new Attribute("Locked",img_16_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_5_0_locked_variable);
        AttributeValue img_16_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_16_5_0_locked_variable,img_16_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_5_0_locked_variable_value);


        ALCityInstanceInPL img_17_5_0 = new ALCityInstanceInPL("img_17_5_0",17,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_5_0);
        Attribute img_17_5_0_bgImage_property =new Attribute("bgImage",img_17_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_5_0_bgImage_property);
        AttributeValue img_17_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_17_5_0_bgImage_property,img_17_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_5_0_bgImage_property_value);
        Attribute img_17_5_0_locked_variable =new Attribute("Locked",img_17_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_5_0_locked_variable);
        AttributeValue img_17_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_17_5_0_locked_variable,img_17_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_5_0_locked_variable_value);

        ALCityInstanceInPL img_18_5_1 = new ALCityInstanceInPL("img_18_5_1",18,5,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_5_1);
        Attribute img_18_5_1_bgImage_property =new Attribute("bgImage",img_18_5_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_5_1_bgImage_property);
        AttributeValue img_18_5_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_5_1_bgImage_property,img_18_5_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_5_1_bgImage_property_value);


        ALCityInstanceInPL img_18_5_0 = new ALCityInstanceInPL("img_18_5_0",18,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_5_0);
        Attribute img_18_5_0_bgImage_property =new Attribute("bgImage",img_18_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_5_0_bgImage_property);
        AttributeValue img_18_5_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_5_0_bgImage_property,img_18_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_5_0_bgImage_property_value);


        ALCityInstanceInPL img_19_5_0 = new ALCityInstanceInPL("img_19_5_0",19,5,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_5_0);
        Attribute img_19_5_0_bgImage_property =new Attribute("bgImage",img_19_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_5_0_bgImage_property);
        AttributeValue img_19_5_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_5_0_bgImage_property,img_19_5_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_5_0_bgImage_property_value);
        Attribute img_19_5_0_locked_variable =new Attribute("Locked",img_19_5_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_5_0_locked_variable);
        AttributeValue img_19_5_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_5_0_locked_variable,img_19_5_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_5_0_locked_variable_value);


        ALCityInstanceInPL img_1_6_0 = new ALCityInstanceInPL("img_1_6_0",1,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_6_0);
        Attribute img_1_6_0_bgImage_property =new Attribute("bgImage",img_1_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_6_0_bgImage_property);
        AttributeValue img_1_6_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_6_0_bgImage_property,img_1_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_6_0_bgImage_property_value);
        Attribute img_1_6_0_locked_variable =new Attribute("Locked",img_1_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_6_0_locked_variable);
        AttributeValue img_1_6_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_6_0_locked_variable,img_1_6_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_6_0_locked_variable_value);


        ALCityInstanceInPL img_2_6_1 = new ALCityInstanceInPL("img_2_6_1",2,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_6_1);
        Attribute img_2_6_1_bgImage_property =new Attribute("bgImage",img_2_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_6_1_bgImage_property);
        AttributeValue img_2_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_6_1_bgImage_property,img_2_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_6_1_bgImage_property_value);


        ALCityInstanceInPL img_2_6_0 = new ALCityInstanceInPL("img_2_6_0",2,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_6_0);
        Attribute img_2_6_0_bgImage_property =new Attribute("bgImage",img_2_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_6_0_bgImage_property);
        AttributeValue img_2_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_6_0_bgImage_property,img_2_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_6_0_bgImage_property_value);


        ALCityInstanceInPL img_3_6_0 = new ALCityInstanceInPL("img_3_6_0",3,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_6_0);
        Attribute img_3_6_0_bgImage_property =new Attribute("bgImage",img_3_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_6_0_bgImage_property);
        AttributeValue img_3_6_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_3_6_0_bgImage_property,img_3_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_6_0_bgImage_property_value);
        Attribute img_3_6_0_locked_variable =new Attribute("Locked",img_3_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_6_0_locked_variable);
        AttributeValue img_3_6_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_3_6_0_locked_variable,img_3_6_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_6_0_locked_variable_value);


        ALCityInstanceInPL img_4_6_1 = new ALCityInstanceInPL("img_4_6_1",4,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_6_1);
        Attribute img_4_6_1_bgImage_property =new Attribute("bgImage",img_4_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_6_1_bgImage_property);
        AttributeValue img_4_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_6_1_bgImage_property,img_4_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_6_1_bgImage_property_value);


        ALCityInstanceInPL img_4_6_0 = new ALCityInstanceInPL("img_4_6_0",4,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_6_0);
        Attribute img_4_6_0_bgImage_property =new Attribute("bgImage",img_4_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_6_0_bgImage_property);
        AttributeValue img_4_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_6_0_bgImage_property,img_4_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_6_0_bgImage_property_value);



        ALCityInstanceInPL img_5_6_1 = new ALCityInstanceInPL("img_5_6_1",5,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_6_1);
        Attribute img_5_6_1_bgImage_property =new Attribute("bgImage",img_5_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_6_1_bgImage_property);
        AttributeValue img_5_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_5_6_1_bgImage_property,img_5_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_6_1_bgImage_property_value);


        ALCityInstanceInPL img_5_6_0 = new ALCityInstanceInPL("img_5_6_0",5,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_6_0);
        Attribute img_5_6_0_bgImage_property =new Attribute("bgImage",img_5_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_6_0_bgImage_property);
        AttributeValue img_5_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_5_6_0_bgImage_property,img_5_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_6_0_bgImage_property_value);


        ALCityInstanceInPL img_6_6_1 = new ALCityInstanceInPL("img_6_6_1",6,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_6_1);
        Attribute img_6_6_1_bgImage_property =new Attribute("bgImage",img_6_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_6_1_bgImage_property);
        AttributeValue img_6_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_6_1_bgImage_property,img_6_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_6_1_bgImage_property_value);


        ALCityInstanceInPL img_6_6_0 = new ALCityInstanceInPL("img_6_6_0",6,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_6_0);
        Attribute img_6_6_0_bgImage_property =new Attribute("bgImage",img_6_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_6_0_bgImage_property);
        AttributeValue img_6_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_6_0_bgImage_property,img_6_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_6_0_bgImage_property_value);



        ALCityInstanceInPL img_7_6_0 = new ALCityInstanceInPL("img_7_6_0",7,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_6_0);
        Attribute img_7_6_0_bgImage_property =new Attribute("bgImage",img_7_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_6_0_bgImage_property);
        AttributeValue img_7_6_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_6_0_bgImage_property,img_7_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_6_0_bgImage_property_value);
        Attribute img_7_6_0_locked_variable =new Attribute("Locked",img_7_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_6_0_locked_variable);
        AttributeValue img_7_6_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_6_0_locked_variable,img_7_6_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_6_0_locked_variable_value);


        ALCityInstanceInPL img_8_6_1 = new ALCityInstanceInPL("img_8_6_1",8,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_6_1);
        Attribute img_8_6_1_bgImage_property =new Attribute("bgImage",img_8_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_6_1_bgImage_property);
        AttributeValue img_8_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_6_1_bgImage_property,img_8_6_1_bgImage_property,1L,now,now,admin_1,admin_1);

        attributeValueService.save(img_8_6_1_bgImage_property_value);



        ALCityInstanceInPL img_8_6_0 = new ALCityInstanceInPL("img_8_6_0",8,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_6_0);
        Attribute img_8_6_0_bgImage_property =new Attribute("bgImage",img_8_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_6_0_bgImage_property);
        AttributeValue img_8_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_6_0_bgImage_property,img_8_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_6_0_bgImage_property_value);


        ALCityInstanceInPL img_9_6_1 = new ALCityInstanceInPL("img_9_6_1",9,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_6_1);
        Attribute img_9_6_1_bgImage_property =new Attribute("bgImage",img_9_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_6_1_bgImage_property);
        AttributeValue img_9_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_9_6_1_bgImage_property,img_9_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_6_1_bgImage_property_value);

        ALCityInstanceInPL img_9_6_0 = new ALCityInstanceInPL("img_9_6_0",9,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_6_0);
        Attribute img_9_6_0_bgImage_property =new Attribute("bgImage",img_9_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_6_0_bgImage_property);
        AttributeValue img_9_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_9_6_0_bgImage_property,img_9_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_6_0_bgImage_property_value);

        ALCityInstanceInPL img_10_6_1 = new ALCityInstanceInPL("img_10_6_1",10,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_6_1);
        Attribute img_10_6_1_bgImage_property =new Attribute("bgImage",img_10_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_6_1_bgImage_property);
        AttributeValue img_10_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_6_1_bgImage_property,img_10_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_6_1_bgImage_property_value);

        ALCityInstanceInPL img_10_6_0 = new ALCityInstanceInPL("img_10_6_0",10,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_6_0);
        Attribute img_10_6_0_bgImage_property =new Attribute("bgImage",img_10_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_6_0_bgImage_property);
        AttributeValue img_10_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_6_0_bgImage_property,img_10_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_6_0_bgImage_property_value);


        ALCityInstanceInPL img_11_6_1 = new ALCityInstanceInPL("img_11_6_1",11,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_6_1);
        Attribute img_11_6_1_bgImage_property =new Attribute("bgImage",img_11_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_6_1_bgImage_property);
        AttributeValue img_11_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_11_6_1_bgImage_property,img_11_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_6_1_bgImage_property_value);


        ALCityInstanceInPL img_11_6_0 = new ALCityInstanceInPL("img_11_6_0",11,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_6_0);
        Attribute img_11_6_0_bgImage_property =new Attribute("bgImage",img_11_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_6_0_bgImage_property);
        AttributeValue img_11_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_11_6_0_bgImage_property,img_11_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_6_0_bgImage_property_value);


        ALCityInstanceInPL img_12_6_1 = new ALCityInstanceInPL("img_12_6_1",12,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_6_1);
        Attribute img_12_6_1_bgImage_property =new Attribute("bgImage",img_12_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_6_1_bgImage_property);
        AttributeValue img_12_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_6_1_bgImage_property,img_12_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_6_1_bgImage_property_value);


        ALCityInstanceInPL img_12_6_0 = new ALCityInstanceInPL("img_12_6_0",12,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_6_0);
        Attribute img_12_6_0_bgImage_property =new Attribute("bgImage",img_12_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_6_0_bgImage_property);
        AttributeValue img_12_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_6_0_bgImage_property,img_12_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_6_0_bgImage_property_value);


        ALCityInstanceInPL img_13_6_0 = new ALCityInstanceInPL("img_13_6_0",13,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_6_0);
        Attribute img_13_6_0_bgImage_property =new Attribute("bgImage",img_13_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_6_0_bgImage_property);
        AttributeValue img_13_6_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_6_0_bgImage_property,img_13_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_6_0_bgImage_property_value);
        Attribute img_13_6_0_locked_variable =new Attribute("Locked",img_13_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_6_0_locked_variable);
        AttributeValue img_13_6_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_6_0_locked_variable,img_13_6_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_6_0_locked_variable_value);

        ALCityInstanceInPL img_14_6_1 = new ALCityInstanceInPL("img_14_6_1",14,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_6_1);
        Attribute img_14_6_1_bgImage_property =new Attribute("bgImage",img_14_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_6_1_bgImage_property);
        AttributeValue img_14_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_6_1_bgImage_property,img_14_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_6_1_bgImage_property_value);

        ALCityInstanceInPL img_14_6_0 = new ALCityInstanceInPL("img_14_6_0",14,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_6_0);
        Attribute img_14_6_0_bgImage_property =new Attribute("bgImage",img_14_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_6_0_bgImage_property);
        AttributeValue img_14_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_6_0_bgImage_property,img_14_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_6_0_bgImage_property_value);

        ALCityInstanceInPL img_15_6_1 = new ALCityInstanceInPL("img_15_6_1",15,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_6_1);
        Attribute img_15_6_1_bgImage_property =new Attribute("bgImage",img_15_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_6_1_bgImage_property);
        AttributeValue img_15_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_15_6_1_bgImage_property,img_15_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_6_1_bgImage_property_value);


        ALCityInstanceInPL img_15_6_0 = new ALCityInstanceInPL("img_15_6_0",15,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_6_0);
        Attribute img_15_6_0_bgImage_property =new Attribute("bgImage",img_15_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_6_0_bgImage_property);
        AttributeValue img_15_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_15_6_0_bgImage_property,img_15_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_6_0_bgImage_property_value);


        ALCityInstanceInPL img_16_6_1 = new ALCityInstanceInPL("img_16_6_1",16,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_6_1);
        Attribute img_16_6_1_bgImage_property =new Attribute("bgImage",img_16_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_6_1_bgImage_property);
        AttributeValue img_16_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_6_1_bgImage_property,img_16_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_6_1_bgImage_property_value);


        ALCityInstanceInPL img_16_6_0 = new ALCityInstanceInPL("img_16_6_0",16,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_6_0);
        Attribute img_16_6_0_bgImage_property =new Attribute("bgImage",img_16_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_6_0_bgImage_property);
        AttributeValue img_16_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_16_6_0_bgImage_property,img_16_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_6_0_bgImage_property_value);


        ALCityInstanceInPL img_17_6_1 = new ALCityInstanceInPL("img_17_6_1",17,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_6_1);
        Attribute img_17_6_1_bgImage_property =new Attribute("bgImage",img_17_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_6_1_bgImage_property);
        AttributeValue img_17_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_17_6_1_bgImage_property,img_17_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_6_1_bgImage_property_value);


        ALCityInstanceInPL img_17_6_0 = new ALCityInstanceInPL("img_17_6_0",17,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_6_0);
        Attribute img_17_6_0_bgImage_property =new Attribute("bgImage",img_17_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_6_0_bgImage_property);
        AttributeValue img_17_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_17_6_0_bgImage_property,img_17_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_6_0_bgImage_property_value);


        ALCityInstanceInPL img_18_6_1 = new ALCityInstanceInPL("img_18_6_1",18,6,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_6_1);
        Attribute img_18_6_1_bgImage_property =new Attribute("bgImage",img_18_6_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_6_1_bgImage_property);
        AttributeValue img_18_6_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_6_1_bgImage_property,img_18_6_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_6_1_bgImage_property_value);

        ALCityInstanceInPL img_18_6_0 = new ALCityInstanceInPL("img_18_6_0",18,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_6_0);
        Attribute img_18_6_0_bgImage_property =new Attribute("bgImage",img_18_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_6_0_bgImage_property);
        AttributeValue img_18_6_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_6_0_bgImage_property,img_18_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_6_0_bgImage_property_value);


        ALCityInstanceInPL img_19_6_0 = new ALCityInstanceInPL("img_19_6_0",19,6,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_6_0);
        Attribute img_19_6_0_bgImage_property =new Attribute("bgImage",img_19_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_6_0_bgImage_property);
        AttributeValue img_19_6_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_6_0_bgImage_property,img_19_6_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_6_0_bgImage_property_value);
        Attribute img_19_6_0_locked_variable =new Attribute("Locked",img_19_6_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_6_0_locked_variable);
        AttributeValue img_19_6_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_6_0_locked_variable,img_19_6_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_6_0_locked_variable_value);


        ALCityInstanceInPL img_1_7_0 = new ALCityInstanceInPL("img_1_7_0",1,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_7_0);
        Attribute img_1_7_0_bgImage_property =new Attribute("bgImage",img_1_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_7_0_bgImage_property);
        AttributeValue img_1_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_7_0_bgImage_property,img_1_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_7_0_bgImage_property_value);
        Attribute img_1_7_0_locked_variable =new Attribute("Locked",img_1_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_7_0_locked_variable);
        AttributeValue img_1_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_7_0_locked_variable,img_1_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_7_0_locked_variable_value);

        ALCityInstanceInPL img_2_7_1 = new ALCityInstanceInPL("img_2_7_1",2,7,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_7_1);
        Attribute img_2_7_1_bgImage_property =new Attribute("bgImage",img_2_7_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_7_1_bgImage_property);
        AttributeValue img_2_7_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_7_1_bgImage_property,img_2_7_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_7_1_bgImage_property_value);



        ALCityInstanceInPL img_2_7_0 = new ALCityInstanceInPL("img_2_7_0",2,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_7_0);
        Attribute img_2_7_0_bgImage_property =new Attribute("bgImage",img_2_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_7_0_bgImage_property);
        AttributeValue img_2_7_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_7_0_bgImage_property,img_2_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_7_0_bgImage_property_value);


        ALCityInstanceInPL img_3_7_0 = new ALCityInstanceInPL("img_3_7_0",3,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_7_0);
        Attribute img_3_7_0_bgImage_property =new Attribute("bgImage",img_3_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_7_0_bgImage_property);
        AttributeValue img_3_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_3_7_0_bgImage_property,img_3_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_7_0_bgImage_property_value);
        Attribute img_3_7_0_locked_variable =new Attribute("Locked",img_3_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_7_0_locked_variable);
        AttributeValue img_3_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_3_7_0_locked_variable,img_3_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_7_0_locked_variable_value);


        ALCityInstanceInPL img_4_7_1 = new ALCityInstanceInPL("img_4_7_1",4,7,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_7_1);
        Attribute img_4_7_1_bgImage_property =new Attribute("bgImage",img_4_7_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_7_1_bgImage_property);
        AttributeValue img_4_7_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_7_1_bgImage_property,img_4_7_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_7_1_bgImage_property_value);


        ALCityInstanceInPL img_4_7_0 = new ALCityInstanceInPL("img_4_7_0",4,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_7_0);
        Attribute img_4_7_0_bgImage_property =new Attribute("bgImage",img_4_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_7_0_bgImage_property);
        AttributeValue img_4_7_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_7_0_bgImage_property,img_4_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_7_0_bgImage_property_value);


        ALCityInstanceInPL img_5_7_0 = new ALCityInstanceInPL("img_5_7_0",5,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_7_0);
        Attribute img_5_7_0_bgImage_property =new Attribute("bgImage",img_5_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_7_0_bgImage_property);
        AttributeValue img_5_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_7_0_bgImage_property,img_5_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_7_0_bgImage_property_value);
        Attribute img_5_7_0_locked_variable =new Attribute("Locked",img_5_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_7_0_locked_variable);
        AttributeValue img_5_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_7_0_locked_variable,img_5_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_7_0_locked_variable_value);


        ALCityInstanceInPL img_6_7_0 = new ALCityInstanceInPL("img_6_7_0",6,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_7_0);
        Attribute img_6_7_0_bgImage_property =new Attribute("bgImage",img_6_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_7_0_bgImage_property);
        AttributeValue img_6_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_6_7_0_bgImage_property,img_6_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_7_0_bgImage_property_value);
        Attribute img_6_7_0_locked_variable =new Attribute("Locked",img_6_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_7_0_locked_variable);
        AttributeValue img_6_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_6_7_0_locked_variable,img_6_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_7_0_locked_variable_value);

        ALCityInstanceInPL img_7_7_0 = new ALCityInstanceInPL("img_7_7_0",7,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_7_0);
        Attribute img_7_7_0_bgImage_property =new Attribute("bgImage",img_7_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_7_0_bgImage_property);
        AttributeValue img_7_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_7_0_bgImage_property,img_7_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_7_0_bgImage_property_value);
        Attribute img_7_7_0_locked_variable =new Attribute("Locked",img_7_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_7_0_locked_variable);
        AttributeValue img_7_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_7_0_locked_variable,img_7_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_7_0_locked_variable_value);

        ALCityInstanceInPL img_8_7_1 = new ALCityInstanceInPL("img_8_7_1",8,7,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_7_1);
        Attribute img_8_7_1_bgImage_property =new Attribute("bgImage",img_8_7_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_7_1_bgImage_property);
        AttributeValue img_8_7_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_7_1_bgImage_property,img_8_7_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_7_1_bgImage_property_value);

        ALCityInstanceInPL img_8_7_0 = new ALCityInstanceInPL("img_8_7_0",8,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_7_0);
        Attribute img_8_7_0_bgImage_property =new Attribute("bgImage",img_8_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_7_0_bgImage_property);
        AttributeValue img_8_7_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_7_0_bgImage_property,img_8_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_7_0_bgImage_property_value);


        ALCityInstanceInPL img_9_7_0 = new ALCityInstanceInPL("img_9_7_0",9,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_7_0);
        Attribute img_9_7_0_bgImage_property =new Attribute("bgImage",img_9_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_7_0_bgImage_property);
        AttributeValue img_9_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_7_0_bgImage_property,img_9_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_7_0_bgImage_property_value);
        Attribute img_9_7_0_locked_variable =new Attribute("Locked",img_9_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_7_0_locked_variable);
        AttributeValue img_9_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_7_0_locked_variable,img_9_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_7_0_locked_variable_value);


        ALCityInstanceInPL img_10_7_0 = new ALCityInstanceInPL("img_10_7_0",10,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_7_0);
        Attribute img_10_7_0_bgImage_property =new Attribute("bgImage",img_10_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_7_0_bgImage_property);
        AttributeValue img_10_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_10_7_0_bgImage_property,img_10_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_7_0_bgImage_property_value);
        Attribute img_10_7_0_locked_variable =new Attribute("Locked",img_10_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_7_0_locked_variable);
        AttributeValue img_10_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_10_7_0_locked_variable,img_10_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_7_0_locked_variable_value);


        ALCityInstanceInPL img_11_7_0 = new ALCityInstanceInPL("img_11_7_0",11,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_7_0);
        Attribute img_11_7_0_bgImage_property =new Attribute("bgImage",img_11_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_7_0_bgImage_property);
        AttributeValue img_11_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_11_7_0_bgImage_property,img_11_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_7_0_bgImage_property_value);
        Attribute img_11_7_0_locked_variable =new Attribute("Locked",img_11_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_7_0_locked_variable);
        AttributeValue img_11_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_11_7_0_locked_variable,img_11_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_7_0_locked_variable_value);


        ALCityInstanceInPL img_12_7_1 = new ALCityInstanceInPL("img_12_7_1",12,7,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_7_1);
        Attribute img_12_7_1_bgImage_property =new Attribute("bgImage",img_12_7_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_7_1_bgImage_property);
        AttributeValue img_12_7_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_7_1_bgImage_property,img_12_7_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_7_1_bgImage_property_value);


        ALCityInstanceInPL img_12_7_0 = new ALCityInstanceInPL("img_12_7_0",12,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_7_0);
        Attribute img_12_7_0_bgImage_property =new Attribute("bgImage",img_12_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_7_0_bgImage_property);
        AttributeValue img_12_7_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_7_0_bgImage_property,img_12_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_7_0_bgImage_property_value);


        ALCityInstanceInPL img_13_7_0 = new ALCityInstanceInPL("img_13_7_0",13,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_7_0);
        Attribute img_13_7_0_bgImage_property =new Attribute("bgImage",img_13_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_7_0_bgImage_property);
        AttributeValue img_13_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_7_0_bgImage_property,img_13_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_7_0_bgImage_property_value);
        Attribute img_13_7_0_locked_variable =new Attribute("Locked",img_13_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_7_0_locked_variable);
        AttributeValue img_13_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_7_0_locked_variable,img_13_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_7_0_locked_variable_value);


        ALCityInstanceInPL img_14_7_1 = new ALCityInstanceInPL("img_14_7_1",14,7,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_7_1);
        Attribute img_14_7_1_bgImage_property =new Attribute("bgImage",img_14_7_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_7_1_bgImage_property);
        AttributeValue img_14_7_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_7_1_bgImage_property,img_14_7_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_7_1_bgImage_property_value);


        ALCityInstanceInPL img_14_7_0 = new ALCityInstanceInPL("img_14_7_0",14,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_7_0);
        Attribute img_14_7_0_bgImage_property =new Attribute("bgImage",img_14_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_7_0_bgImage_property);
        AttributeValue img_14_7_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_7_0_bgImage_property,img_14_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_7_0_bgImage_property_value);


        ALCityInstanceInPL img_15_7_0 = new ALCityInstanceInPL("img_15_7_0",15,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_7_0);
        Attribute img_15_7_0_bgImage_property =new Attribute("bgImage",img_15_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_7_0_bgImage_property);
        AttributeValue img_15_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_7_0_bgImage_property,img_15_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_7_0_bgImage_property_value);
        Attribute img_15_7_0_locked_variable =new Attribute("Locked",img_15_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_7_0_locked_variable);
        AttributeValue img_15_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_7_0_locked_variable,img_15_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_7_0_locked_variable_value);


        ALCityInstanceInPL img_16_7_0 = new ALCityInstanceInPL("img_16_7_0",16,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_7_0);
        Attribute img_16_7_0_bgImage_property =new Attribute("bgImage",img_16_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_7_0_bgImage_property);
        AttributeValue img_16_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_16_7_0_bgImage_property,img_16_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_7_0_bgImage_property_value);
        Attribute img_16_7_0_locked_variable =new Attribute("Locked",img_16_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_7_0_locked_variable);
        AttributeValue img_16_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_16_7_0_locked_variable,img_16_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_7_0_locked_variable_value);


        ALCityInstanceInPL img_17_7_0 = new ALCityInstanceInPL("img_17_7_0",17,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_7_0);
        Attribute img_17_7_0_bgImage_property =new Attribute("bgImage",img_17_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_7_0_bgImage_property);
        AttributeValue img_17_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_17_7_0_bgImage_property,img_17_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_7_0_bgImage_property_value);
        Attribute img_17_7_0_locked_variable =new Attribute("Locked",img_17_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_7_0_locked_variable);
        AttributeValue img_17_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_17_7_0_locked_variable,img_17_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_7_0_locked_variable_value);


        ALCityInstanceInPL img_18_7_0 = new ALCityInstanceInPL("img_18_7_0",18,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_7_0);
        Attribute img_18_7_0_bgImage_property =new Attribute("bgImage",img_18_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_7_0_bgImage_property);
        AttributeValue img_18_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_18_7_0_bgImage_property,img_18_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_7_0_bgImage_property_value);
        Attribute img_18_7_0_locked_variable =new Attribute("Locked",img_18_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_7_0_locked_variable);
        AttributeValue img_18_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_18_7_0_locked_variable,img_18_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_7_0_locked_variable_value);


        ALCityInstanceInPL img_19_7_0 = new ALCityInstanceInPL("img_19_7_0",19,7,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_7_0);
        Attribute img_19_7_0_bgImage_property =new Attribute("bgImage",img_19_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_7_0_bgImage_property);
        AttributeValue img_19_7_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_7_0_bgImage_property,img_19_7_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_7_0_bgImage_property_value);
        Attribute img_19_7_0_locked_variable =new Attribute("Locked",img_19_7_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_7_0_locked_variable);
        AttributeValue img_19_7_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_7_0_locked_variable,img_19_7_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_7_0_locked_variable_value);


        ALCityInstanceInPL img_1_8_0 = new ALCityInstanceInPL("img_1_8_0",1,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_8_0);
        Attribute img_1_8_0_bgImage_property =new Attribute("bgImage",img_1_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_8_0_bgImage_property);
        AttributeValue img_1_8_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_8_0_bgImage_property,img_1_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_8_0_bgImage_property_value);
        Attribute img_1_8_0_locked_variable =new Attribute("Locked",img_1_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_8_0_locked_variable);
        AttributeValue img_1_8_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_8_0_locked_variable,img_1_8_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_8_0_locked_variable_value);


        ALCityInstanceInPL img_2_8_1 = new ALCityInstanceInPL("img_2_8_1",2,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_8_1);
        Attribute img_2_8_1_bgImage_property =new Attribute("bgImage",img_2_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_8_1_bgImage_property);
        AttributeValue img_2_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_8_1_bgImage_property,img_2_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_8_1_bgImage_property_value);


        ALCityInstanceInPL img_2_8_0 = new ALCityInstanceInPL("img_2_8_0",2,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_8_0);
        Attribute img_2_8_0_bgImage_property =new Attribute("bgImage",img_2_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_8_0_bgImage_property);
        AttributeValue img_2_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_8_0_bgImage_property,img_2_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_8_0_bgImage_property_value);


        ALCityInstanceInPL img_3_8_1 = new ALCityInstanceInPL("img_3_8_1",3,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_8_1);
        Attribute img_3_8_1_bgImage_property =new Attribute("bgImage",img_3_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_8_1_bgImage_property);
        AttributeValue img_3_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_3_8_1_bgImage_property,img_3_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_8_1_bgImage_property_value);


        ALCityInstanceInPL img_3_8_0 = new ALCityInstanceInPL("img_3_8_0",3,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_8_0);
        Attribute img_3_8_0_bgImage_property =new Attribute("bgImage",img_3_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_8_0_bgImage_property);
        AttributeValue img_3_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_3_8_0_bgImage_property,img_3_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_8_0_bgImage_property_value);


        ALCityInstanceInPL img_4_8_1 = new ALCityInstanceInPL("img_4_8_1",4,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_8_1);
        Attribute img_4_8_1_bgImage_property =new Attribute("bgImage",img_4_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_8_1_bgImage_property);
        AttributeValue img_4_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_8_1_bgImage_property,img_4_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_8_1_bgImage_property_value);


        ALCityInstanceInPL img_4_8_0 = new ALCityInstanceInPL("img_4_8_0",4,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_8_0);
        Attribute img_4_8_0_bgImage_property =new Attribute("bgImage",img_4_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_8_0_bgImage_property);
        AttributeValue img_4_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_8_0_bgImage_property,img_4_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_8_0_bgImage_property_value);


        ALCityInstanceInPL img_5_8_0 = new ALCityInstanceInPL("img_5_8_0",5,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_8_0);
        Attribute img_5_8_0_bgImage_property =new Attribute("bgImage",img_5_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_8_0_bgImage_property);
        AttributeValue img_5_8_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_8_0_bgImage_property,img_5_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_8_0_bgImage_property_value);
        Attribute img_5_8_0_locked_variable =new Attribute("Locked",img_5_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_8_0_locked_variable);
        AttributeValue img_5_8_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_8_0_locked_variable,img_5_8_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_8_0_locked_variable_value);


        ALCityInstanceInPL img_6_8_1 = new ALCityInstanceInPL("img_6_8_1",6,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_8_1);
        Attribute img_6_8_1_bgImage_property =new Attribute("bgImage",img_6_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_8_1_bgImage_property);
        AttributeValue img_6_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_8_1_bgImage_property,img_6_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_8_1_bgImage_property_value);


        ALCityInstanceInPL img_6_8_0 = new ALCityInstanceInPL("img_6_8_0",6,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_8_0);
        Attribute img_6_8_0_bgImage_property =new Attribute("bgImage",img_6_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_8_0_bgImage_property);
        AttributeValue img_6_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_8_0_bgImage_property,img_6_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_8_0_bgImage_property_value);


        ALCityInstanceInPL img_7_8_1 = new ALCityInstanceInPL("img_7_8_1",7,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_8_1);
        Attribute img_7_8_1_bgImage_property =new Attribute("bgImage",img_7_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_8_1_bgImage_property);
        AttributeValue img_7_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_7_8_1_bgImage_property,img_7_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_8_1_bgImage_property_value);



        ALCityInstanceInPL img_7_8_0 = new ALCityInstanceInPL("img_7_8_0",7,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_8_0);
        Attribute img_7_8_0_bgImage_property =new Attribute("bgImage",img_7_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_8_0_bgImage_property);
        AttributeValue img_7_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_7_8_0_bgImage_property,img_7_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_8_0_bgImage_property_value);

        ALCityInstanceInPL img_8_8_1 = new ALCityInstanceInPL("img_8_8_1",8,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_8_1);
        Attribute img_8_8_1_bgImage_property =new Attribute("bgImage",img_8_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_8_1_bgImage_property);
        AttributeValue img_8_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_8_1_bgImage_property,img_8_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_8_1_bgImage_property_value);


        ALCityInstanceInPL img_8_8_0 = new ALCityInstanceInPL("img_8_8_0",8,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_8_0);
        Attribute img_8_8_0_bgImage_property =new Attribute("bgImage",img_8_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_8_0_bgImage_property);
        AttributeValue img_8_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_8_0_bgImage_property,img_8_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_8_0_bgImage_property_value);


        ALCityInstanceInPL img_9_8_0 = new ALCityInstanceInPL("img_9_8_0",9,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_8_0);
        Attribute img_9_8_0_bgImage_property =new Attribute("bgImage",img_9_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_8_0_bgImage_property);
        AttributeValue img_9_8_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_8_0_bgImage_property,img_9_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_8_0_bgImage_property_value);
        Attribute img_9_8_0_locked_variable =new Attribute("Locked",img_9_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_8_0_locked_variable);
        AttributeValue img_9_8_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_8_0_locked_variable,img_9_8_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_8_0_locked_variable_value);


        ALCityInstanceInPL img_10_8_1 = new ALCityInstanceInPL("img_10_8_1",10,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_8_1);
        Attribute img_10_8_1_bgImage_property =new Attribute("bgImage",img_10_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_8_1_bgImage_property);
        AttributeValue img_10_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_8_1_bgImage_property,img_10_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_8_1_bgImage_property_value);


        ALCityInstanceInPL img_10_8_0 = new ALCityInstanceInPL("img_10_8_0",10,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_8_0);
        Attribute img_10_8_0_bgImage_property =new Attribute("bgImage",img_10_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_8_0_bgImage_property);
        AttributeValue img_10_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_8_0_bgImage_property,img_10_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_8_0_bgImage_property_value);


        ALCityInstanceInPL img_11_8_1 = new ALCityInstanceInPL("img_11_8_1",11,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_8_1);
        Attribute img_11_8_1_bgImage_property =new Attribute("bgImage",img_11_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_8_1_bgImage_property);
        AttributeValue img_11_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_11_8_1_bgImage_property,img_11_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_8_1_bgImage_property_value);


        ALCityInstanceInPL img_11_8_0 = new ALCityInstanceInPL("img_11_8_0",11,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_8_0);
        Attribute img_11_8_0_bgImage_property =new Attribute("bgImage",img_11_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_8_0_bgImage_property);
        AttributeValue img_11_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_11_8_0_bgImage_property,img_11_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_8_0_bgImage_property_value);


        ALCityInstanceInPL img_12_8_1 = new ALCityInstanceInPL("img_12_8_1",12,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_8_1);
        Attribute img_12_8_1_bgImage_property =new Attribute("bgImage",img_12_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_8_1_bgImage_property);
        AttributeValue img_12_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_8_1_bgImage_property,img_12_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_8_1_bgImage_property_value);


        ALCityInstanceInPL img_12_8_0 = new ALCityInstanceInPL("img_12_8_0",12,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_8_0);
        Attribute img_12_8_0_bgImage_property =new Attribute("bgImage",img_12_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_8_0_bgImage_property);
        AttributeValue img_12_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_8_0_bgImage_property,img_12_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_8_0_bgImage_property_value);


        ALCityInstanceInPL img_13_8_0 = new ALCityInstanceInPL("img_13_8_0",13,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_8_0);
        Attribute img_13_8_0_bgImage_property =new Attribute("bgImage",img_13_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_8_0_bgImage_property);
        AttributeValue img_13_8_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_8_0_bgImage_property,img_13_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_8_0_bgImage_property_value);
        Attribute img_13_8_0_locked_variable =new Attribute("Locked",img_13_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_8_0_locked_variable);
        AttributeValue img_13_8_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_8_0_locked_variable,img_13_8_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_8_0_locked_variable_value);


        ALCityInstanceInPL img_14_8_1 = new ALCityInstanceInPL("img_14_8_1",14,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_8_1);
        Attribute img_14_8_1_bgImage_property =new Attribute("bgImage",img_14_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_8_1_bgImage_property);
        AttributeValue img_14_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_8_1_bgImage_property,img_14_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_8_1_bgImage_property_value);


        ALCityInstanceInPL img_14_8_0 = new ALCityInstanceInPL("img_14_8_0",14,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_8_0);
        Attribute img_14_8_0_bgImage_property =new Attribute("bgImage",img_14_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_8_0_bgImage_property);
        AttributeValue img_14_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_8_0_bgImage_property,img_14_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_8_0_bgImage_property_value);



        ALCityInstanceInPL img_15_8_1 = new ALCityInstanceInPL("img_15_8_1",15,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_8_1);
        Attribute img_15_8_1_bgImage_property =new Attribute("bgImage",img_15_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_8_1_bgImage_property);
        AttributeValue img_15_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_15_8_1_bgImage_property,img_15_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_8_1_bgImage_property_value);


        ALCityInstanceInPL img_15_8_0 = new ALCityInstanceInPL("img_15_8_0",15,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_8_0);
        Attribute img_15_8_0_bgImage_property =new Attribute("bgImage",img_15_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_8_0_bgImage_property);
        AttributeValue img_15_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_15_8_0_bgImage_property,img_15_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_8_0_bgImage_property_value);


        ALCityInstanceInPL img_16_8_1 = new ALCityInstanceInPL("img_16_8_1",16,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_8_1);
        Attribute img_16_8_1_bgImage_property =new Attribute("bgImage",img_16_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_8_1_bgImage_property);
        AttributeValue img_16_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_8_1_bgImage_property,img_16_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_8_1_bgImage_property_value);


        ALCityInstanceInPL img_16_8_0 = new ALCityInstanceInPL("img_16_8_0",16,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_8_0);
        Attribute img_16_8_0_bgImage_property =new Attribute("bgImage",img_16_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_8_0_bgImage_property);
        AttributeValue img_16_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_16_8_0_bgImage_property,img_16_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_8_0_bgImage_property_value);


        ALCityInstanceInPL img_17_8_1 = new ALCityInstanceInPL("img_17_8_1",17,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_8_1);
        Attribute img_17_8_1_bgImage_property =new Attribute("bgImage",img_17_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_8_1_bgImage_property);
        AttributeValue img_17_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_17_8_1_bgImage_property,img_17_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_8_1_bgImage_property_value);


        ALCityInstanceInPL img_17_8_0 = new ALCityInstanceInPL("img_17_8_0",17,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_8_0);
        Attribute img_17_8_0_bgImage_property =new Attribute("bgImage",img_17_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_8_0_bgImage_property);
        AttributeValue img_17_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_17_8_0_bgImage_property,img_17_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_8_0_bgImage_property_value);



        ALCityInstanceInPL img_18_8_1 = new ALCityInstanceInPL("img_18_8_1",18,8,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_8_1);
        Attribute img_18_8_1_bgImage_property =new Attribute("bgImage",img_18_8_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_8_1_bgImage_property);
        AttributeValue img_18_8_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_8_1_bgImage_property,img_18_8_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_8_1_bgImage_property_value);


        ALCityInstanceInPL img_18_8_0 = new ALCityInstanceInPL("img_18_8_0",18,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_8_0);
        Attribute img_18_8_0_bgImage_property =new Attribute("bgImage",img_18_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_8_0_bgImage_property);
        AttributeValue img_18_8_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_8_0_bgImage_property,img_18_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_8_0_bgImage_property_value);



        ALCityInstanceInPL img_19_8_0 = new ALCityInstanceInPL("img_19_8_0",19,8,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_8_0);
        Attribute img_19_8_0_bgImage_property =new Attribute("bgImage",img_19_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_8_0_bgImage_property);
        AttributeValue img_19_8_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_8_0_bgImage_property,img_19_8_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_8_0_bgImage_property_value);
        Attribute img_19_8_0_locked_variable =new Attribute("Locked",img_19_8_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_8_0_locked_variable);
        AttributeValue img_19_8_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_8_0_locked_variable,img_19_8_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_8_0_locked_variable_value);


        ALCityInstanceInPL img_1_9_0 = new ALCityInstanceInPL("img_1_9_0",1,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_9_0);
        Attribute img_1_9_0_bgImage_property =new Attribute("bgImage",img_1_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_9_0_bgImage_property);
        AttributeValue img_1_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_9_0_bgImage_property,img_1_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_9_0_bgImage_property_value);
        Attribute img_1_9_0_locked_variable =new Attribute("Locked",img_1_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_9_0_locked_variable);
        AttributeValue img_1_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_9_0_locked_variable,img_1_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_9_0_locked_variable_value);


        ALCityInstanceInPL img_2_9_1 = new ALCityInstanceInPL("img_2_9_1",2,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_9_1);
        Attribute img_2_9_1_bgImage_property =new Attribute("bgImage",img_2_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_9_1_bgImage_property);
        AttributeValue img_2_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_9_1_bgImage_property,img_2_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_9_1_bgImage_property_value);


        ALCityInstanceInPL img_2_9_0 = new ALCityInstanceInPL("img_2_9_0",2,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_9_0);
        Attribute img_2_9_0_bgImage_property =new Attribute("bgImage",img_2_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_9_0_bgImage_property);
        AttributeValue img_2_9_0_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_9_0_bgImage_property,img_2_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_9_0_bgImage_property_value);


        ALCityInstanceInPL img_3_9_0 = new ALCityInstanceInPL("img_3_9_0",3,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_9_0);
        Attribute img_3_9_0_bgImage_property =new Attribute("bgImage",img_3_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_9_0_bgImage_property);
        AttributeValue img_3_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_3_9_0_bgImage_property,img_3_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_9_0_bgImage_property_value);
        Attribute img_3_9_0_locked_variable =new Attribute("Locked",img_3_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_9_0_locked_variable);
        AttributeValue img_3_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_3_9_0_locked_variable,img_3_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_9_0_locked_variable_value);



        ALCityInstanceInPL img_4_9_0 = new ALCityInstanceInPL("img_4_9_0",4,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_9_0);
        Attribute img_4_9_0_bgImage_property =new Attribute("bgImage",img_4_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_9_0_bgImage_property);
        AttributeValue img_4_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_4_9_0_bgImage_property,img_4_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_9_0_bgImage_property_value);
        Attribute img_4_9_0_locked_variable =new Attribute("Locked",img_4_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_9_0_locked_variable);
        AttributeValue img_4_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_4_9_0_locked_variable,img_4_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_9_0_locked_variable_value);

        ALCityInstanceInPL img_5_9_0 = new ALCityInstanceInPL("img_5_9_0",5,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_9_0);
        Attribute img_5_9_0_bgImage_property =new Attribute("bgImage",img_5_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_9_0_bgImage_property);
        AttributeValue img_5_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_9_0_bgImage_property,img_5_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_9_0_bgImage_property_value);
        Attribute img_5_9_0_locked_variable =new Attribute("Locked",img_5_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_9_0_locked_variable);
        AttributeValue img_5_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_9_0_locked_variable,img_5_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_9_0_locked_variable_value);



        ALCityInstanceInPL img_6_9_1 = new ALCityInstanceInPL("img_6_9_1",6,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_9_1);
        Attribute img_6_9_1_bgImage_property =new Attribute("bgImage",img_6_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_9_1_bgImage_property);
        AttributeValue img_6_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_9_1_bgImage_property,img_6_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_9_1_bgImage_property_value);



        ALCityInstanceInPL img_6_9_0 = new ALCityInstanceInPL("img_6_9_0",6,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_9_0);
        Attribute img_6_9_0_bgImage_property =new Attribute("bgImage",img_6_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_9_0_bgImage_property);
        AttributeValue img_6_9_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_9_0_bgImage_property,img_6_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_9_0_bgImage_property_value);



        ALCityInstanceInPL img_7_9_0 = new ALCityInstanceInPL("img_7_9_0",7,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_9_0);
        Attribute img_7_9_0_bgImage_property =new Attribute("bgImage",img_7_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_9_0_bgImage_property);
        AttributeValue img_7_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_9_0_bgImage_property,img_7_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_9_0_bgImage_property_value);
        Attribute img_7_9_0_locked_variable =new Attribute("Locked",img_7_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_9_0_locked_variable);
        AttributeValue img_7_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_9_0_locked_variable,img_7_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_9_0_locked_variable_value);



        ALCityInstanceInPL img_8_9_1 = new ALCityInstanceInPL("img_8_9_1",8,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_9_1);
        Attribute img_8_9_1_bgImage_property =new Attribute("bgImage",img_8_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_9_1_bgImage_property);
        AttributeValue img_8_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_9_1_bgImage_property,img_6_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_9_1_bgImage_property_value);


        ALCityInstanceInPL img_8_9_0 = new ALCityInstanceInPL("img_8_9_0",8,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_9_0);
        Attribute img_8_9_0_bgImage_property =new Attribute("bgImage",img_8_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_9_0_bgImage_property);
        AttributeValue img_8_9_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_9_0_bgImage_property,img_6_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_9_0_bgImage_property_value);



        ALCityInstanceInPL img_9_9_0 = new ALCityInstanceInPL("img_9_9_0",9,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_9_0);
        Attribute img_9_9_0_bgImage_property =new Attribute("bgImage",img_9_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_9_0_bgImage_property);
        AttributeValue img_9_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_9_0_bgImage_property,img_9_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_9_0_bgImage_property_value);
        Attribute img_9_9_0_locked_variable =new Attribute("Locked",img_9_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_9_0_locked_variable);
        AttributeValue img_9_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_9_0_locked_variable,img_9_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_9_0_locked_variable_value);



        ALCityInstanceInPL img_10_9_1 = new ALCityInstanceInPL("img_10_9_1",10,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_9_1);
        Attribute img_10_9_1_bgImage_property =new Attribute("bgImage",img_10_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_9_1_bgImage_property);
        AttributeValue img_10_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_9_1_bgImage_property,img_10_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_9_1_bgImage_property_value);




        ALCityInstanceInPL img_10_9_0 = new ALCityInstanceInPL("img_10_9_0",10,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_9_0);
        Attribute img_10_9_0_bgImage_property =new Attribute("bgImage",img_10_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_9_0_bgImage_property);
        AttributeValue img_10_9_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_9_0_bgImage_property,img_10_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_9_0_bgImage_property_value);


        ALCityInstanceInPL img_11_9_0 = new ALCityInstanceInPL("img_11_9_0",11,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_9_0);
        Attribute img_11_9_0_bgImage_property =new Attribute("bgImage",img_11_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_9_0_bgImage_property);
        AttributeValue img_11_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_11_9_0_bgImage_property,img_11_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_9_0_bgImage_property_value);
        Attribute img_11_9_0_locked_variable =new Attribute("Locked",img_11_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_9_0_locked_variable);
        AttributeValue img_11_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_11_9_0_locked_variable,img_11_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_9_0_locked_variable_value);


        ALCityInstanceInPL img_12_9_0 = new ALCityInstanceInPL("img_12_9_0",12,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_9_0);
        Attribute img_12_9_0_bgImage_property =new Attribute("bgImage",img_12_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_9_0_bgImage_property);
        AttributeValue img_12_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_12_9_0_bgImage_property,img_12_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_9_0_bgImage_property_value);
        Attribute img_12_9_0_locked_variable =new Attribute("Locked",img_12_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_9_0_locked_variable);
        AttributeValue img_12_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_12_9_0_locked_variable,img_12_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_9_0_locked_variable_value);

        ALCityInstanceInPL img_13_9_0 = new ALCityInstanceInPL("img_13_9_0",13,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_9_0);
        Attribute img_13_9_0_bgImage_property =new Attribute("bgImage",img_13_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_9_0_bgImage_property);
        AttributeValue img_13_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_13_9_0_bgImage_property,img_13_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_9_0_bgImage_property_value);
        Attribute img_13_9_0_locked_variable =new Attribute("Locked",img_13_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_9_0_locked_variable);
        AttributeValue img_13_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_13_9_0_locked_variable,img_13_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_9_0_locked_variable_value);



        ALCityInstanceInPL img_14_9_1 = new ALCityInstanceInPL("img_14_9_1",14,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_9_1);
        Attribute img_14_9_1_bgImage_property =new Attribute("bgImage",img_14_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_9_1_bgImage_property);
        AttributeValue img_14_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_9_1_bgImage_property,img_14_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_9_1_bgImage_property_value);


        ALCityInstanceInPL img_14_9_0 = new ALCityInstanceInPL("img_14_9_0",14,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
         pgObjectInstanceService.save(img_14_9_0);
        Attribute img_14_9_0_bgImage_property =new Attribute("bgImage",img_14_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_9_0_bgImage_property);
        AttributeValue img_14_9_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_9_0_bgImage_property,img_14_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_9_0_bgImage_property_value);



        ALCityInstanceInPL img_15_9_0 = new ALCityInstanceInPL("img_15_9_0",15,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_9_0);
        Attribute img_15_9_0_bgImage_property =new Attribute("bgImage",img_15_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_9_0_bgImage_property);
        AttributeValue img_15_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_15_9_0_bgImage_property,img_15_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_9_0_bgImage_property_value);
        Attribute img_15_9_0_locked_variable =new Attribute("Locked",img_15_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_9_0_locked_variable);
        AttributeValue img_15_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_15_9_0_locked_variable,img_15_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_9_0_locked_variable_value);




        ALCityInstanceInPL img_16_9_0 = new ALCityInstanceInPL("img_16_9_0",16,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_9_0);
        Attribute img_16_9_0_bgImage_property =new Attribute("bgImage",img_16_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_9_0_bgImage_property);
        AttributeValue img_16_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_16_9_0_bgImage_property,img_16_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_9_0_bgImage_property_value);
        Attribute img_16_9_0_locked_variable =new Attribute("Locked",img_16_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_9_0_locked_variable);
        AttributeValue img_16_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_16_9_0_locked_variable,img_16_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_9_0_locked_variable_value);



        ALCityInstanceInPL img_17_9_0 = new ALCityInstanceInPL("img_17_9_0",17,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_9_0);
        Attribute img_17_9_0_bgImage_property =new Attribute("bgImage",img_17_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_9_0_bgImage_property);
        AttributeValue img_17_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_17_9_0_bgImage_property,img_17_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_9_0_bgImage_property_value);
        Attribute img_17_9_0_locked_variable =new Attribute("Locked",img_17_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_9_0_locked_variable);
        AttributeValue img_17_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_17_9_0_locked_variable,img_17_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_9_0_locked_variable_value);



        ALCityInstanceInPL img_18_9_1 = new ALCityInstanceInPL("img_18_9_1",18,9,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_9_1);
        Attribute img_18_9_1_bgImage_property =new Attribute("bgImage",img_18_9_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_9_1_bgImage_property);
        AttributeValue img_18_9_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_18_9_1_bgImage_property,img_18_9_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_9_1_bgImage_property_value);


        ALCityInstanceInPL img_18_9_0 = new ALCityInstanceInPL("img_18_9_0",18,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_9_0);
        Attribute img_18_9_0_bgImage_property =new Attribute("bgImage",img_18_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_9_0_bgImage_property);
        AttributeValue img_18_9_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_18_9_0_bgImage_property,img_18_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_9_0_bgImage_property_value);



        ALCityInstanceInPL img_19_9_0 = new ALCityInstanceInPL("img_19_9_0",19,9,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_9_0);
        Attribute img_19_9_0_bgImage_property =new Attribute("bgImage",img_19_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_9_0_bgImage_property);
        AttributeValue img_19_9_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_19_9_0_bgImage_property,img_19_9_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_9_0_bgImage_property_value);
        Attribute img_19_9_0_locked_variable =new Attribute("Locked",img_19_9_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_9_0_locked_variable);
        AttributeValue img_19_9_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_19_9_0_locked_variable,img_19_9_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_9_0_locked_variable_value);




        ALCityInstanceInPL img_1_10_0 = new ALCityInstanceInPL("img_1_10_0",1,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_10_0);
        Attribute img_1_10_0_bgImage_property =new Attribute("bgImage",img_1_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_10_0_bgImage_property);
        AttributeValue img_1_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_1_10_0_bgImage_property,img_1_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_10_0_bgImage_property_value);
        Attribute img_1_10_0_locked_variable =new Attribute("Locked",img_1_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_10_0_locked_variable);
        AttributeValue img_1_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_1_10_0_locked_variable,img_1_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_10_0_locked_variable_value);



        ALCityInstanceInPL img_2_10_1 = new ALCityInstanceInPL("img_2_10_1",2,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_10_1);
        Attribute img_2_10_1_bgImage_property =new Attribute("bgImage",img_2_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_10_1_bgImage_property);
        AttributeValue img_2_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_2_10_1_bgImage_property,img_2_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_10_1_bgImage_property_value);


        ALCityInstanceInPL img_2_10_0 = new ALCityInstanceInPL("img_2_10_0",2,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_10_0);
        Attribute img_2_10_0_bgImage_property =new Attribute("bgImage",img_2_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_10_0_bgImage_property);
        AttributeValue img_2_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_2_10_0_bgImage_property,img_2_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_10_0_bgImage_property_value);


        ALCityInstanceInPL img_3_10_1 = new ALCityInstanceInPL("img_3_10_1",3,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_10_1);
        Attribute img_3_10_1_bgImage_property =new Attribute("bgImage",img_3_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_10_1_bgImage_property);
        AttributeValue img_3_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_3_10_1_bgImage_property,img_3_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_10_1_bgImage_property_value);


        ALCityInstanceInPL img_3_10_0 = new ALCityInstanceInPL("img_3_10_0",3,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_10_0);
        Attribute img_3_10_0_bgImage_property =new Attribute("bgImage",img_3_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_10_0_bgImage_property);
        AttributeValue img_3_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_3_10_0_bgImage_property,img_3_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_10_0_bgImage_property_value);



        ALCityInstanceInPL img_4_10_1 = new ALCityInstanceInPL("img_4_10_1",4,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_10_1);
        Attribute img_4_10_1_bgImage_property =new Attribute("bgImage",img_4_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_10_1_bgImage_property);
        AttributeValue img_4_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_4_10_1_bgImage_property,img_4_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_10_1_bgImage_property_value);



        ALCityInstanceInPL img_4_10_0 = new ALCityInstanceInPL("img_4_10_0",4,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_10_0);
        Attribute img_4_10_0_bgImage_property =new Attribute("bgImage",img_4_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_10_0_bgImage_property);
        AttributeValue img_4_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_4_10_0_bgImage_property,img_4_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_10_0_bgImage_property_value);



        ALCityInstanceInPL img_5_10_0 = new ALCityInstanceInPL("img_5_10_0",5,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_10_0);
        Attribute img_5_10_0_bgImage_property =new Attribute("bgImage",img_5_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_10_0_bgImage_property);
        AttributeValue img_5_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_5_10_0_bgImage_property,img_5_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_10_0_bgImage_property_value);
        Attribute img_5_10_0_locked_variable =new Attribute("Locked",img_5_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_10_0_locked_variable);
        AttributeValue img_5_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_5_10_0_locked_variable,img_5_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_10_0_locked_variable_value);



        ALCityInstanceInPL img_6_10_1 = new ALCityInstanceInPL("img_6_10_1",6,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_10_1);
        Attribute img_6_10_1_bgImage_property =new Attribute("bgImage",img_6_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_10_1_bgImage_property);
        AttributeValue img_6_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_6_10_1_bgImage_property,img_6_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_10_1_bgImage_property_value);


        ALCityInstanceInPL img_6_10_0 = new ALCityInstanceInPL("img_6_10_0",6,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_10_0);
        Attribute img_6_10_0_bgImage_property =new Attribute("bgImage",img_6_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_10_0_bgImage_property);
        AttributeValue img_6_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_6_10_0_bgImage_property,img_6_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_10_0_bgImage_property_value);



        ALCityInstanceInPL img_7_10_0 = new ALCityInstanceInPL("img_7_10_0",7,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_10_0);
        Attribute img_7_10_0_bgImage_property =new Attribute("bgImage",img_7_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_10_0_bgImage_property);
        AttributeValue img_7_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_7_10_0_bgImage_property,img_7_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_10_0_bgImage_property_value);
        Attribute img_7_10_0_locked_variable =new Attribute("Locked",img_7_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_10_0_locked_variable);
        AttributeValue img_7_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_7_10_0_locked_variable,img_7_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_10_0_locked_variable_value);



        ALCityInstanceInPL img_8_10_1 = new ALCityInstanceInPL("img_8_10_1",8,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_10_1);
        Attribute img_8_10_1_bgImage_property =new Attribute("bgImage",img_8_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_10_1_bgImage_property);
        AttributeValue img_8_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_8_10_1_bgImage_property,img_8_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_10_1_bgImage_property_value);


        ALCityInstanceInPL img_8_10_0 = new ALCityInstanceInPL("img_8_10_0",8,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_10_0);
        Attribute img_8_10_0_bgImage_property =new Attribute("bgImage",img_8_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_10_0_bgImage_property);
        AttributeValue img_8_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_8_10_0_bgImage_property,img_8_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_10_0_bgImage_property_value);


        ALCityInstanceInPL img_9_10_0 = new ALCityInstanceInPL("img_9_10_0",9,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_10_0);
        Attribute img_9_10_0_bgImage_property =new Attribute("bgImage",img_9_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_10_0_bgImage_property);
        AttributeValue img_9_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,null,img_9_10_0_bgImage_property,img_9_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_10_0_bgImage_property_value);
        Attribute img_9_10_0_locked_variable =new Attribute("Locked",img_9_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_10_0_locked_variable);
        AttributeValue img_9_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,null,img_9_10_0_locked_variable,img_9_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_10_0_locked_variable_value);



        ALCityInstanceInPL img_10_10_1 = new ALCityInstanceInPL("img_10_10_1",10,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_10_1);
        Attribute img_10_10_1_bgImage_property =new Attribute("bgImage",img_10_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_10_1_bgImage_property);
        AttributeValue img_10_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_10_10_1_bgImage_property,img_10_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_10_1_bgImage_property_value);

        ALCityInstanceInPL img_10_10_0 = new ALCityInstanceInPL("img_10_10_0",10,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_10_0);
        Attribute img_10_10_0_bgImage_property =new Attribute("bgImage",img_10_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_10_0_bgImage_property);
        AttributeValue img_10_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_10_10_0_bgImage_property,img_10_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_10_0_bgImage_property_value);


        ALCityInstanceInPL img_11_10_1 = new ALCityInstanceInPL("img_11_10_1",11,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_10_1);

        Attribute img_11_10_1_bgImage_property =new Attribute("bgImage",img_11_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_10_1_bgImage_property);
        AttributeValue img_11_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_11_10_1_bgImage_property,img_11_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_10_1_bgImage_property_value);


        ALCityInstanceInPL img_11_10_0 = new ALCityInstanceInPL("img_11_10_0",11,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_10_0);
        Attribute img_11_10_0_bgImage_property =new Attribute("bgImage",img_11_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_10_0_bgImage_property);
        AttributeValue img_11_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_11_10_0_bgImage_property,img_11_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_10_0_bgImage_property_value);



        ALCityInstanceInPL img_12_10_1 = new ALCityInstanceInPL("img_12_10_1",10,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_10_1);
        Attribute img_12_10_1_bgImage_property =new Attribute("bgImage",img_12_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_10_1_bgImage_property);
        AttributeValue img_12_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_12_10_1_bgImage_property,img_12_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_10_1_bgImage_property_value);


        ALCityInstanceInPL img_12_10_0 = new ALCityInstanceInPL("img_12_10_0",12,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_10_0);
        Attribute img_12_10_0_bgImage_property =new Attribute("bgImage",img_12_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_10_0_bgImage_property);
        AttributeValue img_12_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_12_10_0_bgImage_property,img_12_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_10_0_bgImage_property_value);


        ALCityInstanceInPL img_13_10_1 = new ALCityInstanceInPL("img_13_10_1",13,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_10_1);
        Attribute img_13_10_1_bgImage_property =new Attribute("bgImage",img_13_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_10_1_bgImage_property);
        AttributeValue img_13_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_13_10_1_bgImage_property,img_13_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_10_1_bgImage_property_value);



        ALCityInstanceInPL img_13_10_0 = new ALCityInstanceInPL("img_13_10_0",13,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_10_0);
        Attribute img_13_10_0_bgImage_property =new Attribute("bgImage",img_13_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_10_0_bgImage_property);
        AttributeValue img_13_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_13_10_0_bgImage_property,img_13_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_10_0_bgImage_property_value);


        ALCityInstanceInPL img_14_10_1 = new ALCityInstanceInPL("img_14_10_1",14,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_10_1);
        Attribute img_14_10_1_bgImage_property =new Attribute("bgImage",img_14_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_10_1_bgImage_property);
        AttributeValue img_14_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_14_10_1_bgImage_property,img_14_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_10_1_bgImage_property_value);



        ALCityInstanceInPL img_14_10_0 = new ALCityInstanceInPL("img_14_10_0",14,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_10_0);
        Attribute img_14_10_0_bgImage_property =new Attribute("bgImage",img_14_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_10_0_bgImage_property);
        AttributeValue img_14_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_14_10_0_bgImage_property,img_14_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_10_0_bgImage_property_value);


        ALCityInstanceInPL img_15_10_1 = new ALCityInstanceInPL("img_15_10_1",15,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_10_1);
        Attribute img_15_10_1_bgImage_property =new Attribute("bgImage",img_15_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_10_1_bgImage_property);
        AttributeValue img_15_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_15_10_1_bgImage_property,img_15_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_10_1_bgImage_property_value);



        ALCityInstanceInPL img_15_10_0 = new ALCityInstanceInPL("img_15_10_0",15,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_10_0);
        Attribute img_15_10_0_bgImage_property =new Attribute("bgImage",img_15_10_0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_10_0_bgImage_property);
        AttributeValue img_15_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,null,img_15_10_0_bgImage_property,img_15_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_10_0_bgImage_property_value);



        ALCityInstanceInPL img_16_10_1 = new ALCityInstanceInPL("img_16_10_1",16,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_10_1);
        Attribute img_16_10_1_bgImage_property =new Attribute("bgImage",img_16_10_1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_10_1_bgImage_property);
        AttributeValue img_16_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,null,img_16_10_1_bgImage_property,img_16_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_10_1_bgImage_property_value);









    }

}
