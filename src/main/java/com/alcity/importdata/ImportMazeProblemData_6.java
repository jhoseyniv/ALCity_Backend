package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.Journey.JourneyStepService;
import com.alcity.service.alobject.*;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.AppMember_WalletItemService;
import com.alcity.service.appmember.WalletItemService;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.base.ClientTypeService;
import com.alcity.service.base.PLPrivacyService;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=6)
@Component
public class ImportMazeProblemData_6 implements CommandLineRunner {

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
    ActionService puzzleObjectActionService;

    @Autowired
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;

    @Autowired
    PLGameInstanceService plGameInstanceService;

    @Autowired
    ObjectCategoryService objectCategoryService;

    @Autowired
    BaseObjectService puzzleObjectService;

    @Autowired
    InstanceService pgObjectInstanceService;


    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    PLRuleEventService plRuleEventService;


     @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    RendererService actionRendererService;


    @Autowired
    ActionService puzzleObject_ObjectActionService;

    @Autowired
    PGObjectService alCityObjectInPGService;
    @Autowired
    ActionService actionService;

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
    BaseObjectService alCityObjectService;

    @Autowired
    PGLearningSkillService puzzleSkillLearningContentService;
    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {

/*
        log.info("Start Application...Import Problem 3 X-O Probelm");
        System.out.println("...Import Maze game Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        Optional<AppMember> admin_1Optional = applicationMemberService.findByUsername("admin");
        Optional<AppMember> jalalHoseyniOptional = applicationMemberService.findByUsername("jalal");
        Optional<AppMember> Alireza_ZareOptional = applicationMemberService.findByUsername("alireza");
        AppMember  admin_1=admin_1Optional.get();
        AppMember jalalHoseyni = jalalHoseyniOptional.get();
        AppMember Alireza_Zare = Alireza_ZareOptional.get();


        Optional<LearningTopic> Maze_Game_Topic = learningTopicService.findByTitle("Maze_Game");

        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill memory_booster = learningSkillService.findByValue("memory_booster");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("walletItem_1");


        JsonReader reader = Json.createReader(new FileReader("src/main/resources/images/Maze_Game/Maze_Game-ground.json"));
        JsonObject jsonObject = reader.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGraphicDTO boardGraphic = objectMapper.readValue(jsonObject.toString(), BoardGraphicDTO.class);


        byte[] pl_Icon_Maze_Game_bytes = ImageUtil.getImage("src/main/resources/images/Maze_Game/","MazeGame_icon.jpeg");
        BinaryContent pl_Icon_Maze_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Maze_Game_Icon",pl_Icon_Maze_Game_bytes.length,pl_Icon_Maze_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Maze_Game_content);

        byte[] pl_pic_Maze_game_bytes = ImageUtil.getImage("src/main/resources/images/Maze_Game/","MazeGame_icon.jpeg");
        BinaryContent pl_pic_Maze_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Maze_Game_Picture",pl_pic_Maze_game_bytes.length,pl_pic_Maze_game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Maze_Game_content);

        BinaryContent puzzle_group_1_binary_content_image = binaryContentService.findByfileName("image_puzzle_group_matematic");

        byte[] maze_Cell_start_bytes = ImageUtil.getImage("src/main/resources/images/Maze_Game/","cat.png");
        BinaryContent maze_Cell_start_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"cat",maze_Cell_start_bytes.length,maze_Cell_start_bytes,null,"cat","","",BinaryContentType.Image);
        binaryContentService.save(maze_Cell_start_content);

        Optional<PuzzleGroup>  puzzleGroup_Maze =puzzleGroupService.findByTitle("Maze Puzzle Group");

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("Public");

        PuzzleLevel maze_Game= new PuzzleLevel(admin_1,now,1L,"Maze-Game","4549",8,15,5f,0f,2f,3f,puzzleGroup_Maze.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,pl_Icon_Maze_Game_content,pl_pic_Maze_Game_content,3L,now,now,admin_1,admin_1);
        maze_Game.setIcon(pl_Icon_Maze_Game_content);
        maze_Game.setPicture(pl_pic_Maze_Game_content);
        puzzleLevelService.save(maze_Game);


        byte[] maze_image_LearningContent_Image_bytes = ImageUtil.getImage("src/main/resources/images/Maze_Game/","MazeGame_icon.jpeg");
        BinaryContent pg_maze_Game_learning_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory-Game", maze_image_LearningContent_Image_bytes.length, maze_image_LearningContent_Image_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pg_maze_Game_learning_content);

        Float xPos=15f;
        Float yPos=15f;
        Float zPos=-15f;
        Float xRotation=35f;
        Float yRotation=-45f;
        Float zRotation=0f;
        Boolean zoom=true;
        Boolean pan=true;
        Boolean rotation=true;

        byte[] boardGraphic2 = ImageUtil.convertObjectToBytes(boardGraphic);
        PLGround pl_Memory_Game_ground = new PLGround(21,21,xPos,yPos,zPos,xRotation,yRotation,zRotation,zoom,pan,rotation,maze_Game, boardGraphic2,1L,now,now,admin_1,admin_1);
        puzzleLevelGroundService.save(pl_Memory_Game_ground);

        PermitedPlayer player_1_puzzleLevel_Maze = new PermitedPlayer(Alireza_Zare,maze_Game,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_Maze);

        LearningContent learningContent_memory=new LearningContent("help to Memory","this content is about pipe Games",pg_maze_Game_learning_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_memory);


        LearningTopicInPL pllLearningTopic_1 = new LearningTopicInPL(maze_Game,Maze_Game_Topic.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(pllLearningTopic_1);

        Float playScore =1f;
        Integer stars = ToolBox.getPuzzleLevelStars(playScore,maze_Game.getFirstStarScore(),maze_Game.getSecondStarScore(),maze_Game.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,maze_Game,now,now,15L,playScore,stars,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PGLearningSkillContent puzzleSkillLearningContent_1 = new PGLearningSkillContent(memory_booster,puzzleGroup_Maze.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,maze_Game,now,now,GameStatus.Playing,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)<=100)");

        PLObjective pl_objective_1 = new PLObjective("Finished in max number of possible steps","Finished in max number of possible steps",
                10f,2f,condition_Objective_1,memory_booster,
                                                                 alCoin10WalletItem.get(),maze_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)>100)");

        PLObjective pl_objective_2 = new PLObjective("Finished in more than max number of possible steps","Finished in morethan max number of possible steps",
                1f,2f,condition_Objective_2,memory_booster,
                alCoin10WalletItem.get(),maze_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_2);


        Attribute pl_variable_finished =new Attribute("finished",maze_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);
        AttributeValue pl_variable_finished_value= new AttributeValue(false,null,null,null,null,null,null,null,false,null,pl_variable_finished,1L,now,now,admin_1,admin_1,maze_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_finished_value);

        Attribute pl_variable_steps =new Attribute("steps",maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_steps);
        AttributeValue pl_variable_steps_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_steps,1L,now,now,admin_1,admin_1,maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_steps_value);


        Attribute pl_variable_objX =new Attribute("objX",maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_objX);
        AttributeValue pl_variable_objX_value= new AttributeValue(null,20,null,null,null,null,null,null,false,null,pl_variable_objX,1L,now,now,admin_1,admin_1,maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_objX_value);

        Attribute pl_variable_objY =new Attribute("objY",maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_objY);
        AttributeValue pl_variable_objY_value= new AttributeValue(null,1,null,null,null,null,null,null,false,null,pl_variable_objY,1L,now,now,admin_1,admin_1,maze_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_objY_value);


        Optional<ALCityObject> imageObject01 =alCityObjectService.findByTitle("ImageObject01");


        ALCityObjectInPG mazeGame_ImageObject = new ALCityObjectInPG("Image maze Puzzle Group with Image Object","mazeGame_ImageObject",puzzleGroup_Maze.get(),imageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(mazeGame_ImageObject);

        DTOUtil.copyActionFromTo(imageObject01.get().getId(), mazeGame_ImageObject.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(mazeGame_ImageObject.getId(), POActionOwnerType.Puzzle_Group_Object);

        Iterator<ObjectAction> actionIterator = actions.iterator();
        while(actionIterator.hasNext()){
            ObjectAction objectAction = actionIterator.next();
            Renderer renderer = objectAction.getActionRenderer();

            if(renderer.getHandler().equalsIgnoreCase("ShowImage")){
                Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                Iterator<Attribute> attributeIterator = attributes.iterator();
                while(attributeIterator.hasNext()){
                    Attribute attribute = attributeIterator.next();
                    AttributeValue attributeValue = new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), bgImage)",true,null,attribute,1L,now,now,admin_1,admin_1,objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                    attributeValueService.save(attributeValue);
                }
            }
            if(renderer.getHandler().equalsIgnoreCase("MoveImage")){
                Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                Iterator<Attribute> attributeIterator = attributes.iterator();
                while(attributeIterator.hasNext()){
                    Attribute attribute = attributeIterator.next();
                    if(attribute.getName().equalsIgnoreCase("fromRow")) {
                        AttributeValue attributeValue = new AttributeValue(null, null, null, null, null, null, null, "BoardVar(objX)", true, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                    if(attribute.getName().equalsIgnoreCase("toRow")) {
                        AttributeValue attributeValue = new AttributeValue(null, 0, null, null, null, null, null, null, false, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                    if(attribute.getName().equalsIgnoreCase("fromCol")) {
                        AttributeValue attributeValue = new AttributeValue(null, 0, null, null, null, null, null, null, false, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                    if(attribute.getName().equalsIgnoreCase("toCol")) {
                        AttributeValue attributeValue = new AttributeValue(null, 0, null, null, null, null, null, null, false, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                }
            }

        }

        Attribute ImageObject01_property_bgImage =new Attribute("bgImage",mazeGame_ImageObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_bgImage);
        AttributeValue ImageObject01_property_bgImage_value = new AttributeValue(null,null,null,null,null,null,pg_maze_Game_learning_content.getId(),null,false,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,mazeGame_ImageObject.getId(), AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_property_bgImage_value);

//        Attribute attributeBGImage_Image_Object= null;
//        Collection<Attribute> attributes = attributeService.findPropertiesForALCityObject(imageObject01.get().getId(),AttributeOwnerType.Object_Property);
//        Iterator<Attribute> attributeIterator = attributes.iterator();
//        while(actionIterator.hasNext()){
//            Attribute attribute = attributeIterator.next();
//            if(attribute.getName().equalsIgnoreCase("bgImage")) {
//                attributeBGImage_Image_Object =attribute;
//
//            }
//        }



        Attribute ImageObject01_variable_canMove=new Attribute("canMove",mazeGame_ImageObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_canMove);
        AttributeValue  Image0object_variable_1_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_canMove,1L,now,now,admin_1,admin_1,mazeGame_ImageObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_1_value);

        Attribute ImageObject01_variable_isTarget =new Attribute("isTarget",mazeGame_ImageObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_isTarget);
        AttributeValue  Image0object_variable_2_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_isTarget,1L,now,now,admin_1,admin_1,mazeGame_ImageObject.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_2_value);

        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRuleEvent> Swipe_event = plRuleEventService.findByName("Swipe");
        Optional<PLRuleEvent> internalevent = plRuleEventService.findByName("InternalEvent");


        StringBuffer    click_rule_condition = new StringBuffer("equal(BoardVar(finished),false)");
        Boolean ignoreRemaining = false;
        PLRule rule_for_Click = new PLRule("Click",1
                ,click_rule_condition,ignoreRemaining,maze_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click);

        PLRulePostAction VariableAssignmentAction_1 = new PLRulePostAction(rule_for_Click.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1);


        StringBuffer   Click2Right_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(row), BoardVar(objX)) & (EventParam(col) > BoardVar(objY)) & (BoardVar(objY)<21) & (equal(InstByPos(BoardVar(objX), BoardVar(objY)+1), null) | equal(InstVar(InstByPos(BoardVar(objX), BoardVar(objY)+1), isTarget),true))");
        Boolean ignoreRemaining2 = false;
        PLRule rule_for_Click2Right = new PLRule("Click2Right",2
                ,Click2Right_rule_condition,ignoreRemaining2,maze_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click2Right);


        PLRulePostAction FireEventAction_Move_Click2Right = new PLRulePostAction(rule_for_Click2Right.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Move","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Move_Click2Right);

        Attribute FireEventAction_Move_Click2Right_param_1=new Attribute("row",FireEventAction_Move_Click2Right.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Right_param_1);
        AttributeValue  FireEventAction_Move_Click2Right_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)",Boolean.TRUE,null,FireEventAction_Move_Click2Right_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Right.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Right_param_1_value);

        Attribute FireEventAction_Move_Click2Right_param_2=new Attribute("col",FireEventAction_Move_Click2Right.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Right_param_2);
        AttributeValue  FireEventAction_Move_Click2Right_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)+1",Boolean.TRUE,null,FireEventAction_Move_Click2Right_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Right.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Right_param_2_value);

        StringBuffer   Click2Left_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(row), BoardVar(objX)) & (EventParam(col) < BoardVar(objY)) & (BoardVar(objY)>1) & equal(InstByPos(BoardVar(objX), BoardVar(objY)-1), null)");
        Boolean ignoreRemaining3 = false;
        PLRule rule_for_Click2Left = new PLRule("Click2Left",3
                ,Click2Left_rule_condition,ignoreRemaining3,maze_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click2Left);

        PLRulePostAction FireEventAction_Move_Click2Left = new PLRulePostAction(rule_for_Click2Left.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Move","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Move_Click2Left);

        Attribute FireEventAction_Move_Click2Left_param_1=new Attribute("row",FireEventAction_Move_Click2Left.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Left_param_1);
        AttributeValue  FireEventAction_Move_Click2Left_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)",Boolean.TRUE,null,FireEventAction_Move_Click2Left_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Left.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Left_param_1_value);

        Attribute FireEventAction_Move_Click2Left_param_2=new Attribute("col",FireEventAction_Move_Click2Left.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Left_param_2);
        AttributeValue  FireEventAction_Move_Click2Left_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)-1",Boolean.TRUE,null,FireEventAction_Move_Click2Left_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Left.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Left_param_2_value);

        StringBuffer   Click2Up_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(col), BoardVar(objY)) & (EventParam(row) < BoardVar(objX)) & (BoardVar(objX)>1) & equal(InstByPos(BoardVar(objX)-1, BoardVar(objY)), null)");
        Boolean ignoreRemaining_Click2Up = false;
        PLRule rule_for_Click2Up = new PLRule("Click2Up",4
                ,Click2Up_rule_condition,ignoreRemaining_Click2Up,maze_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click2Up);

        PLRulePostAction FireEventAction_Move_Click2Up = new PLRulePostAction(rule_for_Click2Up.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Move","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Move_Click2Up);

        Attribute FireEventAction_Move_Click2Up_param_1=new Attribute("row",FireEventAction_Move_Click2Up.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Up_param_1);
        AttributeValue  FireEventAction_Move_Click2Up_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)-1",Boolean.TRUE,null,FireEventAction_Move_Click2Up_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Up.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Up_param_1_value);

        Attribute FireEventAction_Move_Click2Up_param_2=new Attribute("col",FireEventAction_Move_Click2Up.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Up_param_2);
        AttributeValue  FireEventAction_Move_Click2Up_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)",Boolean.TRUE,null,FireEventAction_Move_Click2Up_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Up.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Up_param_2_value);

        StringBuffer   Click2Down_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(col), BoardVar(objY)) & (EventParam(row) > BoardVar(objX)) & (BoardVar(objX)<21) & equal(InstByPos(BoardVar(objX)+1, BoardVar(objY)), null)");
        Boolean ignoreRemaining_Click2Down = false;
        PLRule rule_for_Click2Down = new PLRule("Click2Down",5
                ,Click2Down_rule_condition,ignoreRemaining_Click2Down,maze_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click2Down);

        PLRulePostAction FireEventAction_Move_Click2Down = new PLRulePostAction(rule_for_Click2Down.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Move","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Move_Click2Down);

        Attribute FireEventAction_Move_Click2Down_param_1=new Attribute("row",FireEventAction_Move_Click2Down.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Down_param_1);
        AttributeValue  FireEventAction_Move_Click2Down_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)+1",Boolean.TRUE,null,FireEventAction_Move_Click2Down_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Down.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Down_param_1_value);

        Attribute FireEventAction_Move_Click2Down_param_2=new Attribute("col",FireEventAction_Move_Click2Down.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Move_Click2Down_param_2);
        AttributeValue  FireEventAction_Move_Click2Down_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)",Boolean.TRUE,null,FireEventAction_Move_Click2Down_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Move_Click2Down.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Move_Click2Down_param_2_value);

        StringBuffer   Swipe_rule_condition = new StringBuffer("equal(BoardVar(finished),false)");
        Boolean ignoreRemaining_Swipe = false;
        PLRule rule_for_Swipe = new PLRule("Swipe",6
                ,Swipe_rule_condition,ignoreRemaining_Swipe,maze_Game,Swipe_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe);

        PLRulePostAction VariableAssignmentAction_Swipe = new PLRulePostAction(rule_for_Swipe.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_Swipe);


        StringBuffer   Swipe2Right_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(dir), RIGHT) & (BoardVar(objY)<21) & (equal(InstByPos(BoardVar(objX), BoardVar(objY)+1), null) | equal(InstVar(InstByPos(BoardVar(objX), BoardVar(objY)+1), isTarget),true))");
        Boolean ignoreRemaining_Swipe2Right = false;
        PLRule rule_for_Swipe2Right = new PLRule("Swipe2Right",7
                ,Swipe2Right_rule_condition,ignoreRemaining_Swipe2Right,maze_Game,Swipe_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe2Right);

        PLRulePostAction FireEventAction_Swipe2Right_1 = new PLRulePostAction(rule_for_Swipe2Right.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Swipe","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Swipe2Right_1);

        Attribute FireEventAction_Swipe2Right_1_param_1=new Attribute("rowInc",FireEventAction_Swipe2Right_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Right_1_param_1);
        AttributeValue  FireEventAction_Swipe2Right_1_param_1_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Right_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Right_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Right_1_param_1_value);

        Attribute FireEventAction_Swipe2Right_1_param_2=new Attribute("colInc",FireEventAction_Swipe2Right_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Right_1_param_2);
        AttributeValue  FireEventAction_Swipe2Right_1_param_2_value= new AttributeValue(null,1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Right_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Right_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Right_1_param_2_value);


        StringBuffer   Swipe2Left_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(dir), LEFT) & (BoardVar(objY)>1) & equal(InstByPos(BoardVar(objX), BoardVar(objY)-1), null)");
        Boolean ignoreRemaining_Swipe2Left = false;
        PLRule rule_for_Swipe2Left = new PLRule("Swipe2Left",8
                ,Swipe2Left_rule_condition,ignoreRemaining_Swipe2Left,maze_Game,Swipe_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe2Left);

        PLRulePostAction FireEventAction_Swipe2Left_1 = new PLRulePostAction(rule_for_Swipe2Left.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Swipe","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Swipe2Left_1);

        Attribute FireEventAction_Swipe2Left_1_param_1=new Attribute("rowInc",FireEventAction_Swipe2Left_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Left_1_param_1);
        AttributeValue  FireEventAction_Swipe2Left_1_param_1_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Left_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Left_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Left_1_param_1_value);

        Attribute FireEventAction_Swipe2Left_1_param_2=new Attribute("colInc",FireEventAction_Swipe2Left_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Left_1_param_2);
        AttributeValue  FireEventAction_Swipe2Left_1_param_2_value= new AttributeValue(null,-1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Left_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Left_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Left_1_param_2_value);

        StringBuffer   Swipe2Up_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(dir), UP) & (BoardVar(objX)>1) & equal(InstByPos(BoardVar(objX)-1, BoardVar(objY)), null)");
        Boolean ignoreRemaining_Swipe2Up = false;
        PLRule rule_for_Swipe2Up = new PLRule("Swipe2Up",9
                ,Swipe2Up_rule_condition,ignoreRemaining_Swipe2Up,maze_Game,Swipe_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe2Up);

        PLRulePostAction FireEventAction_Swipe2Up_1 = new PLRulePostAction(rule_for_Swipe2Up.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Swipe","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Swipe2Up_1);

        Attribute FireEventAction_Swipe2Up_1_param_1=new Attribute("rowInc",FireEventAction_Swipe2Up_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Up_1_param_1);
        AttributeValue  FireEventAction_Swipe2Up_1_param_1_value= new AttributeValue(null,-1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Up_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Up_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Up_1_param_1_value);

        Attribute FireEventAction_Swipe2Up_1_param_2=new Attribute("colInc",FireEventAction_Swipe2Up_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Up_1_param_2);
        AttributeValue  FireEventAction_Swipe2Up_1_param_2_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Up_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Up_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Up_1_param_2_value);

        StringBuffer   Swipe2Dowm_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & equal(EventParam(dir), DOWN) & (BoardVar(objX)<21) & equal(InstByPos(BoardVar(objX)+1, BoardVar(objY)), null)");
        Boolean ignoreRemaining_Swipe2Dowm = false;
        PLRule rule_for_Swipe2Dowm = new PLRule("Swipe2Dowm",10
                ,Swipe2Dowm_rule_condition,ignoreRemaining_Swipe2Dowm,maze_Game,Swipe_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe2Dowm);

        PLRulePostAction FireEventAction_Swipe2Dowm_1 = new PLRulePostAction(rule_for_Swipe2Dowm.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Swipe","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Swipe2Dowm_1);

        Attribute FireEventAction_Swipe2Dowm_1_param_1=new Attribute("rowInc",FireEventAction_Swipe2Dowm_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Dowm_1_param_1);
        AttributeValue  FireEventAction_Swipe2Dowm_1_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Dowm_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Dowm_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Dowm_1_param_1_value);

        Attribute FireEventAction_Swipe2Dowm_1_param_2=new Attribute("colInc",FireEventAction_Swipe2Dowm_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2Dowm_1_param_2);
        AttributeValue  FireEventAction_Swipe2Dowm_1_param_2_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_Swipe2Dowm_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2Dowm_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2Dowm_1_param_2_value);

        StringBuffer   Swipe_2_rule_condition = new StringBuffer("(BoardVar(objX)+EventParam(rowInc)<22) & (BoardVar(objX)+EventParam(rowInc)>0) & (BoardVar(objY)+EventParam(colInc)<22) & (BoardVar(objY)+EventParam(colInc)>0) & equal(InstByPos(BoardVar(objX)+EventParam(rowInc), BoardVar(objY)+EventParam(colInc)), null)");
        Boolean ignoreRemaining_Swipe_2 = false;
        PLRule rule_for_Swipe_2 = new PLRule("Swipe",10
                ,Swipe_2_rule_condition,ignoreRemaining_Swipe_2,maze_Game,internalevent.get(),"swipe",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Swipe_2);

        PLRulePostAction FireEventAction_move2_Swipe_2_1 = new PLRulePostAction(rule_for_Swipe_2.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Move","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_move2_Swipe_2_1);

        Attribute FireEventAction_move2_Swipe_2_1_param_1=new Attribute("row",FireEventAction_move2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_move2_Swipe_2_1_param_1);
        AttributeValue  FireEventAction_move2_Swipe_2_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)+EventParam(rowInc)",Boolean.TRUE,null,FireEventAction_move2_Swipe_2_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_move2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_move2_Swipe_2_1_value);

        Attribute FireEventAction_move2_Swipe_2_1_param_2=new Attribute("col",FireEventAction_move2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_move2_Swipe_2_1_param_2);
        AttributeValue  FireEventAction_move2_Swipe_2_1_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)+EventParam(colInc)",Boolean.TRUE,null,FireEventAction_move2_Swipe_2_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_move2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_move2_Swipe_2_1_param_2_value);

        PLRulePostAction FireEventAction_Swipe2_Swipe_2_1 = new PLRulePostAction(rule_for_Swipe_2.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,1,"","",
                "",new StringBuffer(""),"Swipe","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_Swipe2_Swipe_2_1);

        Attribute FireEventAction_Swipe2_Swipe_2_1_param_1=new Attribute("rowInc",FireEventAction_Swipe2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2_Swipe_2_1_param_1);
        AttributeValue  FireEventAction_Swipe2_Swipe_2_1_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(rowInc)",Boolean.TRUE,null,FireEventAction_Swipe2_Swipe_2_1_param_1,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2_Swipe_2_1_param_1_value);

        Attribute FireEventAction_Swipe2_Swipe_2_1_param_2=new Attribute("colInc",FireEventAction_Swipe2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_Swipe2_Swipe_2_1_param_2);
        AttributeValue  FireEventAction_Swipe2_Swipe_2_1_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(colInc)",Boolean.TRUE,null,FireEventAction_Swipe2_Swipe_2_1_param_2,1L,now,now,admin_1,admin_1,FireEventAction_Swipe2_Swipe_2_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_Swipe2_Swipe_2_1_param_2_value);


        StringBuffer   rule_move_condition = new StringBuffer("equal(1,1)");
        Boolean ignoreRemaining_rule_move = false;
        PLRule rule_move = new PLRule("Move",10
                ,rule_move_condition,ignoreRemaining_rule_move,maze_Game,internalevent.get(),"move",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_move);

        PLRulePostAction rule_move_CallObjectAction_1 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,6,"move","InstProp(InstByPos(BoardVar(objX),BoardVar(objY)),objectId)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_CallObjectAction_1);

        Attribute rule_move_CallObjectAction_1_param_1=new Attribute("fromRow",rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rule_move_CallObjectAction_1_param_1);
        AttributeValue  rule_move_CallObjectAction_1_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objX)",Boolean.TRUE,null,rule_move_CallObjectAction_1_param_1,1L,now,now,admin_1,admin_1,rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(rule_move_CallObjectAction_1_param_1_value);

        Attribute rule_move_CallObjectAction_1_param_2=new Attribute("toRow",rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rule_move_CallObjectAction_1_param_2);
        AttributeValue  rule_move_CallObjectAction_1_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(row)",Boolean.TRUE,null,rule_move_CallObjectAction_1_param_2,1L,now,now,admin_1,admin_1,rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(rule_move_CallObjectAction_1_param_2_value);

        Attribute rule_move_CallObjectAction_1_param_3=new Attribute("fromCol",rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rule_move_CallObjectAction_1_param_3);
        AttributeValue  rule_move_CallObjectAction_1_param_3_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(objY)",Boolean.TRUE,null,rule_move_CallObjectAction_1_param_3,1L,now,now,admin_1,admin_1,rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(rule_move_CallObjectAction_1_param_3_value);

        Attribute rule_move_CallObjectAction_1_param_4=new Attribute("toCol",rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rule_move_CallObjectAction_1_param_4);
        AttributeValue  rule_move_CallObjectAction_1_param_4_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(col)",Boolean.TRUE,null,rule_move_CallObjectAction_1_param_4,1L,now,now,admin_1,admin_1,rule_move_CallObjectAction_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(rule_move_CallObjectAction_1_param_4_value);

        PLRulePostAction rule_move_VariableAssignmentAction_2 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,6,"","",
                "InstProp(InstByPos(BoardVar(objX),BoardVar(objY)),x)",new StringBuffer("EventParam(row)"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_VariableAssignmentAction_2);

        PLRulePostAction rule_move_VariableAssignmentAction_3 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,6,"","",
                "InstProp(InstByPos(BoardVar(objX),BoardVar(objY)),y)",new StringBuffer("EventParam(col)"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_VariableAssignmentAction_3);

        PLRulePostAction rule_move_VariableAssignmentAction_4 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,6,"","",
                "BoardVar(objX)",new StringBuffer("EventParam(row)"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_VariableAssignmentAction_4);

        PLRulePostAction rule_move_VariableAssignmentAction_5 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,6,"","",
                "BoardVar(objY)",new StringBuffer("EventParam(col)"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_VariableAssignmentAction_5);

        PLRulePostAction rule_move_FireEventAction_6 = new PLRulePostAction(rule_move.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,6,"","",
                "",new StringBuffer(""),"Check","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(rule_move_FireEventAction_6);

        StringBuffer    CheckCompletion_rule_condition = new StringBuffer("equal(BoardVar(objX),2) & equal(BoardVar(objY),21)");
        Boolean ignoreRemaining5 = false;
        PLRule rule_for_CheckCompletion = new PLRule("CheckCompletion",5
                ,CheckCompletion_rule_condition,ignoreRemaining5,maze_Game,internalevent.get(),"check",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_CheckCompletion);

        PLRulePostAction VariableAssignmentAction_1_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_CheckCompletion);



        PLRulePostAction UserAlertAction_2_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallSystemAction,2,"ShowMessage","",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(UserAlertAction_2_CheckCompletion);

        Attribute UserAlertAction_2_CheckCompletion_param_1=new Attribute("text",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_1);
        AttributeValue  UserAlertAction_2_CheckCompletion_param_1_value= new AttributeValue(null,null,null,"You Win",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_1,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(UserAlertAction_2_CheckCompletion_param_1_value);

        Attribute UserAlertAction_2_CheckCompletion_param_2=new Attribute("dialogType",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_2);
        AttributeValue  callobjectaction_2_startTimer_param_2_value= new AttributeValue(null,null,null,"DIALOG",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_2,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_2_value);

        Attribute UserAlertAction_2_CheckCompletion_param_3=new Attribute("messageType",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_3);
        AttributeValue  callobjectaction_2_startTimer_param_3_value= new AttributeValue(null,null,null,"INFO",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_3,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_3_value);






        ALCityInstanceInPL instance_img2001 = new ALCityInstanceInPL("instance_img2001",20,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2001);

        AttributeValue instance_img0_property_bgimage_1_value2 = new AttributeValue(null,null,null,null,null,null,maze_Cell_start_content.getId(),null,false,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img2001.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img0_property_bgimage_1_value2);

        AttributeValue  instance_variable_canMove_value2= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_canMove,1L,now,now,admin_1,admin_1,instance_img2001.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_variable_canMove_value2);

        ALCityInstanceInPL instance_img0101 = new ALCityInstanceInPL("instance_img0101",1,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0101);

        ALCityInstanceInPL instance_img0102 = new ALCityInstanceInPL("instance_img0102",1,2,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0102);

        ALCityInstanceInPL instance_img0103 = new ALCityInstanceInPL("instance_img0103",1,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0103);

        ALCityInstanceInPL instance_img0104 = new ALCityInstanceInPL("instance_img0104",1,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0104);

        ALCityInstanceInPL instance_img0105 = new ALCityInstanceInPL("instance_img0105",1,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0105);

        ALCityInstanceInPL instance_img0106 = new ALCityInstanceInPL("instance_img0106",1,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0106);

        ALCityInstanceInPL instance_img0107 = new ALCityInstanceInPL("instance_img0107",1,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0107);

        ALCityInstanceInPL instance_img0108 = new ALCityInstanceInPL("instance_img0108",1,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0108);

        ALCityInstanceInPL instance_img0109 = new ALCityInstanceInPL("instance_img0109",1,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0109);

        ALCityInstanceInPL instance_img0110 = new ALCityInstanceInPL("instance_img0110",1,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0110);

        ALCityInstanceInPL instance_img0111 = new ALCityInstanceInPL("instance_img0111",1,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0111);

        ALCityInstanceInPL instance_img0112 = new ALCityInstanceInPL("instance_img0112",1,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0112);

        ALCityInstanceInPL instance_img0113 = new ALCityInstanceInPL("instance_img0113",1,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0113);

        ALCityInstanceInPL instance_img0114 = new ALCityInstanceInPL("instance_img0114",1,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0114);

        ALCityInstanceInPL instance_img0115 = new ALCityInstanceInPL("instance_img0115",1,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0115);

        ALCityInstanceInPL instance_img0116 = new ALCityInstanceInPL("instance_img0116",1,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0116);

        ALCityInstanceInPL instance_img0117 = new ALCityInstanceInPL("instance_img0117",1,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0117);

        ALCityInstanceInPL instance_img0118 = new ALCityInstanceInPL("instance_img0118",1,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0118);

        ALCityInstanceInPL instance_img0119 = new ALCityInstanceInPL("instance_img0119",1,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0119);

        ALCityInstanceInPL instance_img0120 = new ALCityInstanceInPL("instance_img0120",1,20,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0120);

        ALCityInstanceInPL instance_img0121 = new ALCityInstanceInPL("instance_img0121",1,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0121);


        ALCityInstanceInPL instance_img0201 = new ALCityInstanceInPL("instance_img0201",2,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0201);

        ALCityInstanceInPL instance_img0211 = new ALCityInstanceInPL("instance_img0211",2,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0211);

        ALCityInstanceInPL instance_img0219 = new ALCityInstanceInPL("instance_img0219",2,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0219);


        ALCityInstanceInPL instance_img0301 = new ALCityInstanceInPL("instance_img0301",3,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0301);

        ALCityInstanceInPL instance_img0303 = new ALCityInstanceInPL("instance_img0303",3,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0303);

        ALCityInstanceInPL instance_img0304 = new ALCityInstanceInPL("instance_img0304",3,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0304);

        ALCityInstanceInPL instance_img0305 = new ALCityInstanceInPL("instance_img0305",3,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0305);

        ALCityInstanceInPL instance_img0306 = new ALCityInstanceInPL("instance_img0306",3,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0306);

        ALCityInstanceInPL instance_img0307 = new ALCityInstanceInPL("instance_img0307",3,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0307);

        ALCityInstanceInPL instance_img0308 = new ALCityInstanceInPL("instance_img0308",3,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0308);

        ALCityInstanceInPL instance_img0309 = new ALCityInstanceInPL("instance_img0309",3,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0309);

        ALCityInstanceInPL instance_img0311 = new ALCityInstanceInPL("instance_img0311",3,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0311);

        ALCityInstanceInPL instance_img0313 = new ALCityInstanceInPL("instance_img0313",3,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0313);

        ALCityInstanceInPL instance_img0314 = new ALCityInstanceInPL("instance_img0314",3,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0314);

        ALCityInstanceInPL instance_img0315 = new ALCityInstanceInPL("instance_img0315",3,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0315);

        ALCityInstanceInPL instance_img0317 = new ALCityInstanceInPL("instance_img0317",3,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0317);

        ALCityInstanceInPL instance_img0319 = new ALCityInstanceInPL("instance_img0319",3,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0319);

        ALCityInstanceInPL instance_img0321 = new ALCityInstanceInPL("instance_img0321",3,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0321);

        ALCityInstanceInPL instance_img0401 = new ALCityInstanceInPL("instance_img0401",4,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0401);

        ALCityInstanceInPL instance_img0403 = new ALCityInstanceInPL("instance_img0403",4,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0403);

        ALCityInstanceInPL instance_img0413 = new ALCityInstanceInPL("instance_img0413",4,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0413);

        ALCityInstanceInPL instance_img0417 = new ALCityInstanceInPL("instance_img0417",4,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0417);

        ALCityInstanceInPL instance_img0419 = new ALCityInstanceInPL("instance_img0419",4,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0419);

        ALCityInstanceInPL instance_img0421 = new ALCityInstanceInPL("instance_img0421",4,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0421);

        ALCityInstanceInPL instance_img0501 = new ALCityInstanceInPL("instance_img0501",5,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0501);

        ALCityInstanceInPL instance_img0503 = new ALCityInstanceInPL("instance_img0503",5,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0503);

        ALCityInstanceInPL instance_img0505 = new ALCityInstanceInPL("instance_img0505",5,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0505);

        ALCityInstanceInPL instance_img0506 = new ALCityInstanceInPL("instance_img0506",5,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0506);

        ALCityInstanceInPL instance_img0507 = new ALCityInstanceInPL("instance_img0507",5,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0507);

        ALCityInstanceInPL instance_img0508 = new ALCityInstanceInPL("instance_img0508",5,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0508);

        ALCityInstanceInPL instance_img0509 = new ALCityInstanceInPL("instance_img0509",5,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0509);

        ALCityInstanceInPL instance_img0510 = new ALCityInstanceInPL("instance_img0510",5,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0510);

        ALCityInstanceInPL instance_img0511 = new ALCityInstanceInPL("instance_img0511",5,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0511);

        ALCityInstanceInPL instance_img0512 = new ALCityInstanceInPL("instance_img0512",5,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0512);

        ALCityInstanceInPL instance_img0513 = new ALCityInstanceInPL("instance_img0513",5,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0513);

        ALCityInstanceInPL instance_img0515 = new ALCityInstanceInPL("instance_img0513",5,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0515);


        ALCityInstanceInPL instance_img0516 = new ALCityInstanceInPL("instance_img0516",5,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0516);

        ALCityInstanceInPL instance_img0517 = new ALCityInstanceInPL("instance_img0517",5,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0517);

        ALCityInstanceInPL instance_img0519 = new ALCityInstanceInPL("instance_img0519",5,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0519);

        ALCityInstanceInPL instance_img0521 = new ALCityInstanceInPL("instance_img0519",5,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0521);

        ALCityInstanceInPL instance_img0601 = new ALCityInstanceInPL("instance_img0601",6,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0601);

        ALCityInstanceInPL instance_img0603 = new ALCityInstanceInPL("instance_img0603",6,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0603);

        ALCityInstanceInPL instance_img0607 = new ALCityInstanceInPL("instance_img0607",6,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0607);

        ALCityInstanceInPL instance_img0613 = new ALCityInstanceInPL("instance_img0613",6,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0613);

        ALCityInstanceInPL instance_img0615 = new ALCityInstanceInPL("instance_img0615",6,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0615);

        ALCityInstanceInPL instance_img0617 = new ALCityInstanceInPL("instance_img0617",6,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0617);

        ALCityInstanceInPL instance_img0619 = new ALCityInstanceInPL("instance_img0619",6,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0619);

        ALCityInstanceInPL instance_img0621 = new ALCityInstanceInPL("instance_img0621",6,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0621);

        ALCityInstanceInPL instance_img0701 = new ALCityInstanceInPL("instance_img0701",7,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0701);

        ALCityInstanceInPL instance_img0703 = new ALCityInstanceInPL("instance_img0703",7,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0703);

        ALCityInstanceInPL instance_img0704 = new ALCityInstanceInPL("instance_img0704",7,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0704);

        ALCityInstanceInPL instance_img0705 = new ALCityInstanceInPL("instance_img0705",7,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0705);

        ALCityInstanceInPL instance_img0706 = new ALCityInstanceInPL("instance_img0706",7,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0706);

        ALCityInstanceInPL instance_img0707 = new ALCityInstanceInPL("instance_img0707",7,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0707);

        ALCityInstanceInPL instance_img0709 = new ALCityInstanceInPL("instance_img0709",7,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0709);

        ALCityInstanceInPL instance_img0710 = new ALCityInstanceInPL("instance_img0710",7,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0710);

        ALCityInstanceInPL instance_img0711 = new ALCityInstanceInPL("instance_img0711",7,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0711);

        ALCityInstanceInPL instance_img0713 = new ALCityInstanceInPL("instance_img0713",7,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0713);

        ALCityInstanceInPL instance_img0714 = new ALCityInstanceInPL("instance_img0714",7,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0714);

        ALCityInstanceInPL instance_img0715 = new ALCityInstanceInPL("instance_img0715",7,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0715);

        ALCityInstanceInPL instance_img0717 = new ALCityInstanceInPL("instance_img0717",7,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0717);

        ALCityInstanceInPL instance_img0718 = new ALCityInstanceInPL("instance_img0718",7,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0718);

        ALCityInstanceInPL instance_img0719 = new ALCityInstanceInPL("instance_img0719",7,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0719);

        ALCityInstanceInPL instance_img0721 = new ALCityInstanceInPL("instance_img0721",7,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0721);

        ALCityInstanceInPL instance_img0801 = new ALCityInstanceInPL("instance_img0801",8,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0801);

        ALCityInstanceInPL instance_img0809 = new ALCityInstanceInPL("instance_img0809",8,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0809);

        ALCityInstanceInPL instance_img0811 = new ALCityInstanceInPL("instance_img0811",8,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0811);

        ALCityInstanceInPL instance_img0819 = new ALCityInstanceInPL("instance_img0819",8,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0819);

        ALCityInstanceInPL instance_img0821 = new ALCityInstanceInPL("instance_img0821",8,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0821);

        ALCityInstanceInPL instance_img0901 = new ALCityInstanceInPL("instance_img0901",9,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0901);

        ALCityInstanceInPL instance_img0903 = new ALCityInstanceInPL("instance_img0903",9,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0903);

        ALCityInstanceInPL instance_img0904 = new ALCityInstanceInPL("instance_img0904",9,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0904);

        ALCityInstanceInPL instance_img0905 = new ALCityInstanceInPL("instance_img0905",9,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0905);

        ALCityInstanceInPL instance_img0906 = new ALCityInstanceInPL("instance_img0906",9,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0906);

        ALCityInstanceInPL instance_img0907 = new ALCityInstanceInPL("instance_img0907",9,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0907);

        ALCityInstanceInPL instance_img0909 = new ALCityInstanceInPL("instance_img0909",9,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0909);

        ALCityInstanceInPL instance_img0911 = new ALCityInstanceInPL("instance_img0911",9,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0911);

        ALCityInstanceInPL instance_img0912 = new ALCityInstanceInPL("instance_img0912",9,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0912);

        ALCityInstanceInPL instance_img0913 = new ALCityInstanceInPL("instance_img0913",9,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0913);

        ALCityInstanceInPL instance_img0914 = new ALCityInstanceInPL("instance_img0914",9,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0914);

        ALCityInstanceInPL instance_img0915 = new ALCityInstanceInPL("instance_img0915",9,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0915);

        ALCityInstanceInPL instance_img0916 = new ALCityInstanceInPL("instance_img0916",9,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0916);

        ALCityInstanceInPL instance_img0917 = new ALCityInstanceInPL("instance_img0917",9,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0917);

        ALCityInstanceInPL instance_img0918 = new ALCityInstanceInPL("instance_img0918",9,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0918);

        ALCityInstanceInPL instance_img0919 = new ALCityInstanceInPL("instance_img0919",9,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0919);

        ALCityInstanceInPL instance_img0921 = new ALCityInstanceInPL("instance_img0921",9,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0921);

        ALCityInstanceInPL instance_img1001 = new ALCityInstanceInPL("instance_img1001",10,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1001);

        ALCityInstanceInPL instance_img1003 = new ALCityInstanceInPL("instance_img1003",10,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1003);

        ALCityInstanceInPL instance_img1007 = new ALCityInstanceInPL("instance_img1007",10,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1007);

        ALCityInstanceInPL instance_img1011 = new ALCityInstanceInPL("instance_img1011",10,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1011);

        ALCityInstanceInPL instance_img1015 = new ALCityInstanceInPL("instance_img1015",10,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1015);

        ALCityInstanceInPL instance_img1021 = new ALCityInstanceInPL("instance_img1021",10,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1021);

        ALCityInstanceInPL instance_img1101 = new ALCityInstanceInPL("instance_img1101",11,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1101);

        ALCityInstanceInPL instance_img1103 = new ALCityInstanceInPL("instance_img1103",11,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1103);

        ALCityInstanceInPL instance_img1104 = new ALCityInstanceInPL("instance_img1104",11,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1104);

        ALCityInstanceInPL instance_img1105 = new ALCityInstanceInPL("instance_img1105",11,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1105);

        ALCityInstanceInPL instance_img1107 = new ALCityInstanceInPL("instance_img1107",11,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1107);

        ALCityInstanceInPL instance_img1108 = new ALCityInstanceInPL("instance_img1108",11,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1108);

        ALCityInstanceInPL instance_img1109 = new ALCityInstanceInPL("instance_img1109",11,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1109);

        ALCityInstanceInPL instance_img1111 = new ALCityInstanceInPL("instance_img1111",11,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1111);

        ALCityInstanceInPL instance_img1113 = new ALCityInstanceInPL("instance_img1113",11,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1113);

        ALCityInstanceInPL instance_img1115 = new ALCityInstanceInPL("instance_img1115",11,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1115);

        ALCityInstanceInPL instance_img1116 = new ALCityInstanceInPL("instance_img1116",11,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1116);

        ALCityInstanceInPL instance_img1117 = new ALCityInstanceInPL("instance_img1117",11,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1117);

        ALCityInstanceInPL instance_img1118 = new ALCityInstanceInPL("instance_img1118",11,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1118);

        ALCityInstanceInPL instance_img1119 = new ALCityInstanceInPL("instance_img1119",11,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1119);

        ALCityInstanceInPL instance_img1121 = new ALCityInstanceInPL("instance_img1121",11,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1121);

        ALCityInstanceInPL instance_img1201 = new ALCityInstanceInPL("instance_img1201",12,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1201);

        ALCityInstanceInPL instance_img1205 = new ALCityInstanceInPL("instance_img1205",12,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1205);

        ALCityInstanceInPL instance_img1207 = new ALCityInstanceInPL("instance_img1207",12,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1207);

        ALCityInstanceInPL instance_img1209 = new ALCityInstanceInPL("instance_img1209",12,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1209);

        ALCityInstanceInPL instance_img1213 = new ALCityInstanceInPL("instance_img1213",12,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1213);

        ALCityInstanceInPL instance_img1219 = new ALCityInstanceInPL("instance_img1219",12,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1219);

        ALCityInstanceInPL instance_img1221 = new ALCityInstanceInPL("instance_img1221",12,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1221);

        ALCityInstanceInPL instance_img1301 = new ALCityInstanceInPL("instance_img1301",13,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1301);

        ALCityInstanceInPL instance_img1302 = new ALCityInstanceInPL("instance_img1302",13,2,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1302);

        ALCityInstanceInPL instance_img1303 = new ALCityInstanceInPL("instance_img1303",13,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1303);

        ALCityInstanceInPL instance_img1305 = new ALCityInstanceInPL("instance_img1305",13,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1305);

        ALCityInstanceInPL instance_img1307 = new ALCityInstanceInPL("instance_img1307",13,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1307);

        ALCityInstanceInPL instance_img1309 = new ALCityInstanceInPL("instance_img1309",13,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1309);

        ALCityInstanceInPL instance_img1310 = new ALCityInstanceInPL("instance_img1310",13,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1310);

        ALCityInstanceInPL instance_img1311 = new ALCityInstanceInPL("instance_img1311",13,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1311);

        ALCityInstanceInPL instance_img1313 = new ALCityInstanceInPL("instance_img1313",13,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1313);

        ALCityInstanceInPL instance_img1314 = new ALCityInstanceInPL("instance_img1314",13,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1314);

        ALCityInstanceInPL instance_img1315 = new ALCityInstanceInPL("instance_img1315",13,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1315);

        ALCityInstanceInPL instance_img1316 = new ALCityInstanceInPL("instance_img1316",13,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1316);

        ALCityInstanceInPL instance_img1317 = new ALCityInstanceInPL("instance_img1317",13,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1317);

        ALCityInstanceInPL instance_img1318 = new ALCityInstanceInPL("instance_img1318",13,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1318);

        ALCityInstanceInPL instance_img1319 = new ALCityInstanceInPL("instance_img1319",13,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1319);

        ALCityInstanceInPL instance_img1321 = new ALCityInstanceInPL("instance_img1321",13,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1321);

        ALCityInstanceInPL instance_img1401 = new ALCityInstanceInPL("instance_img1401",14,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1401);

        ALCityInstanceInPL instance_img1405 = new ALCityInstanceInPL("instance_img1405",14,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1405);

        ALCityInstanceInPL instance_img1407 = new ALCityInstanceInPL("instance_img1407",14,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1407);

        ALCityInstanceInPL instance_img1419 = new ALCityInstanceInPL("instance_img1419",14,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1419);

        ALCityInstanceInPL instance_img1421 = new ALCityInstanceInPL("instance_img1421",14,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1421);

        ALCityInstanceInPL instance_img1501 = new ALCityInstanceInPL("instance_img1501",15,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1501);

        ALCityInstanceInPL instance_img1503 = new ALCityInstanceInPL("instance_img1503",15,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1503);

        ALCityInstanceInPL instance_img1504 = new ALCityInstanceInPL("instance_img1504",15,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1504);

        ALCityInstanceInPL instance_img1505 = new ALCityInstanceInPL("instance_img1505",15,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1505);

        ALCityInstanceInPL instance_img1507 = new ALCityInstanceInPL("instance_img1507",15,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1507);

        ALCityInstanceInPL instance_img1508 = new ALCityInstanceInPL("instance_img1508",15,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1508);

        ALCityInstanceInPL instance_img1509 = new ALCityInstanceInPL("instance_img1509",15,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1509);

        ALCityInstanceInPL instance_img1511 = new ALCityInstanceInPL("instance_img1511",15,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1511);

        ALCityInstanceInPL instance_img1512 = new ALCityInstanceInPL("instance_img1512",15,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1512);

        ALCityInstanceInPL instance_img1513 = new ALCityInstanceInPL("instance_img1513",15,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1513);

        ALCityInstanceInPL instance_img1514 = new ALCityInstanceInPL("instance_img1514",15,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1514);

        ALCityInstanceInPL instance_img1515 = new ALCityInstanceInPL("instance_img1515",15,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1515);

        ALCityInstanceInPL instance_img1517 = new ALCityInstanceInPL("instance_img1517",15,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1517);

        ALCityInstanceInPL instance_img1519 = new ALCityInstanceInPL("instance_img1519",15,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1519);

        ALCityInstanceInPL instance_img1521 = new ALCityInstanceInPL("instance_img1521",15,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1521);

        ALCityInstanceInPL instance_img1601 = new ALCityInstanceInPL("instance_img1601",16,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1601);

        ALCityInstanceInPL instance_img1603 = new ALCityInstanceInPL("instance_img1603",16,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1603);

        ALCityInstanceInPL instance_img1607 = new ALCityInstanceInPL("instance_img1607",16,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1607);

        ALCityInstanceInPL instance_img1609 = new ALCityInstanceInPL("instance_img1609",16,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1609);

        ALCityInstanceInPL instance_img1611 = new ALCityInstanceInPL("instance_img1611",16,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1611);

        ALCityInstanceInPL instance_img1617 = new ALCityInstanceInPL("instance_img1617",16,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1617);

        ALCityInstanceInPL instance_img1619 = new ALCityInstanceInPL("instance_img1619",16,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1619);

        ALCityInstanceInPL instance_img1621 = new ALCityInstanceInPL("instance_img1621",16,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1621);

        ALCityInstanceInPL instance_img1701 = new ALCityInstanceInPL("instance_img1701",17,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1701);

        ALCityInstanceInPL instance_img1703 = new ALCityInstanceInPL("instance_img1703",17,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1703);

        ALCityInstanceInPL instance_img1705 = new ALCityInstanceInPL("instance_img1705",17,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1705);

        ALCityInstanceInPL instance_img1707 = new ALCityInstanceInPL("instance_img1707",17,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1707);

        ALCityInstanceInPL instance_img1709 = new ALCityInstanceInPL("instance_img1709",17,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1709);

        ALCityInstanceInPL instance_img1710 = new ALCityInstanceInPL("instance_img1710",17,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1710);

        ALCityInstanceInPL instance_img1711 = new ALCityInstanceInPL("instance_img1711",17,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1711);

        ALCityInstanceInPL instance_img1713 = new ALCityInstanceInPL("instance_img1713",17,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1713);
        ALCityInstanceInPL instance_img1714 = new ALCityInstanceInPL("instance_img1714",17,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1714);

        ALCityInstanceInPL instance_img1715 = new ALCityInstanceInPL("instance_img1715",17,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1715);

        ALCityInstanceInPL instance_img1716 = new ALCityInstanceInPL("instance_img1716",17,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1716);

        ALCityInstanceInPL instance_img1717 = new ALCityInstanceInPL("instance_img1717",17,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1717);

        ALCityInstanceInPL instance_img1719 = new ALCityInstanceInPL("instance_img1719",17,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1719);

          ALCityInstanceInPL instance_img1721 = new ALCityInstanceInPL("instance_img1721",17,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1721);

        ALCityInstanceInPL instance_img1801 = new ALCityInstanceInPL("instance_img1801",18,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1801);

        ALCityInstanceInPL instance_img1803 = new ALCityInstanceInPL("instance_img1803",18,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1803);

        ALCityInstanceInPL instance_img1805 = new ALCityInstanceInPL("instance_img1805",18,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1805);

        ALCityInstanceInPL instance_img1813 = new ALCityInstanceInPL("instance_img1813",18,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1813);

        ALCityInstanceInPL instance_img1817 = new ALCityInstanceInPL("instance_img1817",18,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1817);

        ALCityInstanceInPL instance_img1821 = new ALCityInstanceInPL("instance_img1821",18,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1821);

        ALCityInstanceInPL instance_img1901 = new ALCityInstanceInPL("instance_img1901",19,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1901);

        ALCityInstanceInPL instance_img1903 = new ALCityInstanceInPL("instance_img1903",19,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1903);

        ALCityInstanceInPL instance_img1905 = new ALCityInstanceInPL("instance_img1905",19,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1905);

        ALCityInstanceInPL instance_img1907 = new ALCityInstanceInPL("instance_img1907",19,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1907);

        ALCityInstanceInPL instance_img1908 = new ALCityInstanceInPL("instance_img1908",19,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1908);

        ALCityInstanceInPL instance_img1909 = new ALCityInstanceInPL("instance_img1909",19,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1909);

        ALCityInstanceInPL instance_img1910 = new ALCityInstanceInPL("instance_img1910",19,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1910);

        ALCityInstanceInPL instance_img1911 = new ALCityInstanceInPL("instance_img1911",19,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1911);

        ALCityInstanceInPL instance_img1912 = new ALCityInstanceInPL("instance_img1912",19,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1912);

        ALCityInstanceInPL instance_img1913 = new ALCityInstanceInPL("instance_img1913",19,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1913);

        ALCityInstanceInPL instance_img1915 = new ALCityInstanceInPL("instance_img1915",19,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1915);

        ALCityInstanceInPL instance_img1917 = new ALCityInstanceInPL("instance_img1917",19,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1917);

        ALCityInstanceInPL instance_img1918 = new ALCityInstanceInPL("instance_img1918",19,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1918);

        ALCityInstanceInPL instance_img1919 = new ALCityInstanceInPL("instance_img1919",19,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1919);

        ALCityInstanceInPL instance_img1921 = new ALCityInstanceInPL("instance_img1921",19,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1921);

        ALCityInstanceInPL instance_img2003 = new ALCityInstanceInPL("instance_img2003",20,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2003);

        ALCityInstanceInPL instance_img2005 = new ALCityInstanceInPL("instance_img2005",20,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2005);

        ALCityInstanceInPL instance_img2015 = new ALCityInstanceInPL("instance_img2015",20,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2015);

        ALCityInstanceInPL instance_img2019 = new ALCityInstanceInPL("instance_img2019",20,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2019);

        ALCityInstanceInPL instance_img2021 = new ALCityInstanceInPL("instance_img2021",20,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2021);

        ALCityInstanceInPL instance_img2101 = new ALCityInstanceInPL("instance_img2101",21,1,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2101);

        ALCityInstanceInPL instance_img2102 = new ALCityInstanceInPL("instance_img2102",21,2,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2102);

        ALCityInstanceInPL instance_img2103 = new ALCityInstanceInPL("instance_img2103",21,3,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2103);

        ALCityInstanceInPL instance_img2104 = new ALCityInstanceInPL("instance_img2104",21,4,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2104);

        ALCityInstanceInPL instance_img2105 = new ALCityInstanceInPL("instance_img2105",21,5,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2105);

        ALCityInstanceInPL instance_img2106 = new ALCityInstanceInPL("instance_img2106",21,6,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2106);
        ALCityInstanceInPL instance_img2107 = new ALCityInstanceInPL("instance_img2107",21,7,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2107);

        ALCityInstanceInPL instance_img2108 = new ALCityInstanceInPL("instance_img2108",21,8,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2108);

        ALCityInstanceInPL instance_img2109 = new ALCityInstanceInPL("instance_img2109",21,9,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2109);

        ALCityInstanceInPL instance_img2110 = new ALCityInstanceInPL("instance_img2110",21,10,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2110);

        ALCityInstanceInPL instance_img2111 = new ALCityInstanceInPL("instance_img2111",21,11,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2111);

        ALCityInstanceInPL instance_img2112 = new ALCityInstanceInPL("instance_img2112",21,12,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2112);

        ALCityInstanceInPL instance_img2113 = new ALCityInstanceInPL("instance_img2113",21,13,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2113);

        ALCityInstanceInPL instance_img2114 = new ALCityInstanceInPL("instance_img2114",21,14,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2114);

        ALCityInstanceInPL instance_img2115 = new ALCityInstanceInPL("instance_img2115",21,15,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2115);

        ALCityInstanceInPL instance_img2116 = new ALCityInstanceInPL("instance_img2116",21,16,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2116);

        ALCityInstanceInPL instance_img2117 = new ALCityInstanceInPL("instance_img2117",21,17,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2117);

        ALCityInstanceInPL instance_img2118 = new ALCityInstanceInPL("instance_img2118",21,18,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2118);

        ALCityInstanceInPL instance_img2119 = new ALCityInstanceInPL("instance_img2119",21,19,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2119);

        ALCityInstanceInPL instance_img2120 = new ALCityInstanceInPL("instance_img2120",21,20,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2120);

        ALCityInstanceInPL instance_img2121 = new ALCityInstanceInPL("instance_img2121",21,21,0,mazeGame_ImageObject,maze_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2121);
*/

    }





}
