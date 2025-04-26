package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
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
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import com.alcity.utility.ToolBox;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;


@Order(value=8)
@Component
public class ImportMultiplyProblemData_7 implements CommandLineRunner {

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
    ObjectService puzzleObjectService;

    @Autowired
    InstanceInPLService pgObjectInstanceService;


    @Autowired
    AttributeValueService attributeValueService;

    @Autowired
    PLRuleEventService plRuleEventService;
    @Autowired
    PLRulePostActionTypeService plRulePostActionTypeService;


     @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    RendererService actionRendererService;


    @Autowired
    ActionService puzzleObject_ObjectActionService;

    @Autowired
    ObjectInPGService alCityObjectInPGService;
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
    ObjectService alCityObjectService;

   @Autowired
   PGSkillLearningContentService puzzleSkillLearningContentService;
    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 7 Multiply Game ");
        System.out.println("...Import Problem 7 Multiply Game Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        Optional<AppMember> admin_1Optional = applicationMemberService.findByUsername("admin");
        Optional<AppMember> jalalHoseyniOptional = applicationMemberService.findByUsername("jalal");
        Optional<AppMember> Alireza_ZareOptional = applicationMemberService.findByUsername("alireza");
        AppMember  admin_1=admin_1Optional.get();
        AppMember jalalHoseyni = jalalHoseyniOptional.get();
        AppMember Alireza_Zare = Alireza_ZareOptional.get();


        Optional<LearningTopic> Multiply_Game_Topic = learningTopicService.findByTitle("Multiply_Game");

        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill memory_booster = learningSkillService.findByValue("memory_booster");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("walletItem_1");


        JsonReader reader = Json.createReader(new FileReader("src/main/resources/images/Multiply_Game/Multiply_Game.json"));
        JsonObject jsonObject = reader.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGraphicDTO boardGraphic = objectMapper.readValue(jsonObject.toString(), BoardGraphicDTO.class);


        byte[] pl_Icon_Multiply_Game_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","Multiply_Game.png");
        BinaryContent pl_Icon_Multiply_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Multiply_Game",pl_Icon_Multiply_Game_bytes.length,pl_Icon_Multiply_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Multiply_Game_content);

        byte[] pl_pic_Multiply_Game_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","Multiply_Game_icon.png");
        BinaryContent pl_pic_Multiply_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Multiply_Game_Picture",pl_pic_Multiply_Game_bytes.length,pl_pic_Multiply_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Multiply_Game_content);

        BinaryContent puzzle_group_1_binary_content_image = binaryContentService.findByfileName("image_puzzle_group_matematic");


        Optional<PuzzleGroup>  puzzleGroup_Multiply =puzzleGroupService.findByTitle("Multiply Puzzle Group");

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("Public");

        PuzzleLevel Multiply_Game= new PuzzleLevel(admin_1,now,1L,"Multiply game","4950",8,15,5f,0f,2f,3f,puzzleGroup_Multiply.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,pl_Icon_Multiply_Game_content,pl_Icon_Multiply_Game_content,3L,now,now,admin_1,admin_1);
        Multiply_Game.setIcon(pl_Icon_Multiply_Game_content);
        Multiply_Game.setPicture(pl_pic_Multiply_Game_content);
        puzzleLevelService.save(Multiply_Game);


        byte[] maze_image_LearningContent_Image_bytes = ImageUtil.getImage("src/main/resources/images/Maze_Game/","MazeGame_icon.jpeg");
        BinaryContent pg_maze_Game_learning_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory-Game", maze_image_LearningContent_Image_bytes.length, maze_image_LearningContent_Image_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pg_maze_Game_learning_content);

        Float xPos=0f;
        Float yPos=15f;
        Float zPos=0f;
        Float xRotation=90f;
        Float yRotation=0f;
        Float zRotation=0f;
        Boolean zoom=true;
        Boolean pan=true;
        Boolean rotation=false;

        byte[] boardGraphic2 = ImageUtil.convertObjectToBytes(boardGraphic);
        PLGround pl_Memory_Game_ground = new PLGround(10,10,xPos,yPos,zPos,xRotation,yRotation,zRotation,zoom,pan,rotation,Multiply_Game, boardGraphic2,1L,now,now,admin_1,admin_1);
        puzzleLevelGroundService.save(pl_Memory_Game_ground);

        PermitedPlayer player_1_puzzleLevel_Maze = new PermitedPlayer(Alireza_Zare,Multiply_Game,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_Maze);

        LearningContent learningContent_memory=new LearningContent("help to Memory","this content is about pipe Games",pg_maze_Game_learning_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_memory);


        LearningTopicInPL pllLearningTopic_1 = new LearningTopicInPL(Multiply_Game,Multiply_Game_Topic.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(pllLearningTopic_1);

        Float playScore =1f;
        Integer stars = ToolBox.getPuzzleLevelStars(playScore,Multiply_Game.getFirstStarScore(),Multiply_Game.getSecondStarScore(),Multiply_Game.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,Multiply_Game,now,now,15L,playScore,stars,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PGLearningSkillContent puzzleSkillLearningContent_1 = new PGLearningSkillContent(memory_booster,puzzleGroup_Multiply.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,Multiply_Game,now,now,GameStatus.Playing,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(score)>=90)");

        PLObjective pl_objective_1 = new PLObjective("Finished in max number of possible steps","Finished in max number of possible steps",
                10f,2f,condition_Objective_1,memory_booster,
                                                                 alCoin10WalletItem.get(),Multiply_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("equal(BoardVar(finished),true)& (BoardVar(score)<90) & (BoardVar(score)>=70)");

        PLObjective pl_objective_2 = new PLObjective("Finished in more than max number of possible steps","Finished in morethan max number of possible steps",
                1f,2f,condition_Objective_2,memory_booster,
                alCoin10WalletItem.get(),Multiply_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_2);

        StringBuffer  condition_Objective_3 = new StringBuffer("equal(BoardVar(finished),true)& (BoardVar(score)<70)");
        PLObjective pl_objective_3 = new PLObjective("Finished with no score","Finished without getting any score",
                1f,2f,condition_Objective_3,memory_booster,
                alCoin10WalletItem.get(),Multiply_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_3);


        Attribute pl_variable_finished =new Attribute("finished",Multiply_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);
        AttributeValue pl_variable_finished_value= new AttributeValue(false,null,null,null,null,null,null,null,false,null,pl_variable_finished,1L,now,now,admin_1,admin_1,Multiply_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_finished_value);

        Attribute pl_variable_progress =new Attribute("progress",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_progress);
        AttributeValue pl_variable_progress_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_progress,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_progress_value);


        Attribute pl_variable_score =new Attribute("score",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_score);
        AttributeValue pl_variable_score_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_score,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_score_value);

        Attribute pl_variable_firstValue =new Attribute("firstValue",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_firstValue);
        AttributeValue pl_variable_firstValue_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_firstValue,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_firstValue_value);

        Attribute pl_variable_secondValue =new Attribute("secondValue",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_secondValue);
        AttributeValue pl_variable_secondValue_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_secondValue,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_secondValue_value);

        Attribute pl_variable_userFirstValue =new Attribute("userFirstValue",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_userFirstValue);
        AttributeValue pl_variable_userFirstValue_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_userFirstValue,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_userFirstValue_value);

        Attribute pl_variable_userSecondValue =new Attribute("userSecondValue",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_userSecondValue);
        AttributeValue pl_variable_userSecondValue_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_userSecondValue,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_userSecondValue_value);

        Attribute pl_variable_ready =new Attribute("ready",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_ready);
        AttributeValue pl_variable_ready_value= new AttributeValue(true,null,null,null,null,null,null,null,false,null,pl_variable_ready,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_ready_value);

        Attribute pl_variable_started =new Attribute("started",Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_started);
        AttributeValue pl_variable_ready_started= new AttributeValue(false,null,null,null,null,null,null,null,false,null,pl_variable_started,1L,now,now,admin_1,admin_1,Multiply_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_ready_started);



        Optional<ALCityObject> textObject =alCityObjectService.findByTitle("TextObject");
        Optional<ALCityObject> imageObject =alCityObjectService.findByTitle("ImageObject01");


        ALCityObjectInPG textObject_in_Multiply = new ALCityObjectInPG("text object in Multiply puzzlegroup Object","Multiply_Game_TextObject",puzzleGroup_Multiply.get(),textObject.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(textObject_in_Multiply);

        ALCityObjectInPG imageobject_in_Multiply = new ALCityObjectInPG("Image object in Multiply puzzlegroup Object","Multiply_Game_ImageObject",puzzleGroup_Multiply.get(),imageObject.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(imageobject_in_Multiply);



        DTOUtil.copyActionFromTo(textObject.get().getId(), textObject_in_Multiply.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(textObject_in_Multiply.getId(), POActionOwnerType.Puzzle_Group_Object);


        DTOUtil.copyActionFromTo(imageObject.get().getId(), imageobject_in_Multiply.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions_imageObject = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(imageobject_in_Multiply.getId(), POActionOwnerType.Puzzle_Group_Object);


        Iterator<ObjectAction> actionIterator = actions.iterator();
        while(actionIterator.hasNext()){
            ObjectAction objectAction = actionIterator.next();
            Renderer renderer = objectAction.getActionRenderer();


            if(renderer.getObjectAction().name().equalsIgnoreCase("Show")){
                Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                Iterator<Attribute> attributeIterator = attributes.iterator();
                while(attributeIterator.hasNext()){
                    Attribute attribute = attributeIterator.next();
                    if(attribute.getName().equalsIgnoreCase("enabled")) {
                        AttributeValue attributeValue = new AttributeValue(false, null, null, null, null, null, null, null, false, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                    if(attribute.getName().equalsIgnoreCase("bgColor")) {
                        AttributeValue attributeValue = new AttributeValue(null, null, null, "white", null, null, null, null, false, null, attribute, 1L, now, now, admin_1, admin_1, objectAction.getId(), AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                        attributeValueService.save(attributeValue);
                    }
                 }
            }
        }
        Attribute textObject_property_text =new Attribute("text",textObject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(textObject_property_text);
        AttributeValue  textObject_property_text_value= new AttributeValue(null,null,null,"",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,textObject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(textObject_property_text_value);

        Attribute textObject_property_enabled =new Attribute("enabled",textObject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(textObject_property_enabled);
        AttributeValue  textObject_property_enabled_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,textObject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(textObject_property_enabled_value);

        ALCityInstanceInPL ins_100202 = new ALCityInstanceInPL("100202",2,2,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100202);
        AttributeValue  ins_100202_property_text_value= new AttributeValue(null,null,null,"Progress",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100202.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100202_property_text_value);

        AttributeValue  ins_100202_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100202.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100202_property_enabled_value);

        ALCityInstanceInPL ins_100203 = new ALCityInstanceInPL("100203",2,3,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100203);
        AttributeValue  ins_100203_property_text_value= new AttributeValue(null,null,null,":",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100203.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100203_property_text_value);

        AttributeValue  ins_100203_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100203.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100203_property_enabled_value);

        ALCityInstanceInPL ins_100204 = new ALCityInstanceInPL("100204",2,4,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100204);
        AttributeValue  ins_100204_property_text_value= new AttributeValue(null,null,null,"0%",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100204.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100204_property_text_value);

        AttributeValue  ins_100204_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100204.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100204_property_enabled_value);

        ALCityInstanceInPL ins_100302 = new ALCityInstanceInPL("100302",3,2,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100302);
        AttributeValue  ins_100302_property_text_value= new AttributeValue(null,null,null,"Score",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100302.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100302_property_text_value);

        AttributeValue  ins_100302_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100302.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100302_property_enabled_value);

        ALCityInstanceInPL ins_100303 = new ALCityInstanceInPL("100303",3,3,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100303);
        AttributeValue  ins_100303_property_text_value= new AttributeValue(null,null,null,":",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100303.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100303_property_text_value);

        AttributeValue  ins_100303_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100303.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100303_property_enabled_value);

        ALCityInstanceInPL ins_100304 = new ALCityInstanceInPL("100304",3,4,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100304);
        AttributeValue  ins_100304_property_text_value= new AttributeValue(null,null,null,"0",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100304.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100304_property_text_value);

        AttributeValue  ins_100304_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100304.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100304_property_enabled_value);

        ALCityInstanceInPL ins_100502 = new ALCityInstanceInPL("100502",5,2,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100502);
        AttributeValue  ins_100502_property_text_value= new AttributeValue(null,null,null,"0",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100502.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100502_property_text_value);

        AttributeValue  ins_100502_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100502.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100502_property_enabled_value);

        ALCityInstanceInPL ins_100504 = new ALCityInstanceInPL("100504",5,4,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100504);
        AttributeValue  ins_100504_property_text_value= new AttributeValue(null,null,null,"0",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100504.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100504_property_text_value);

        AttributeValue  ins_100504_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100504.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100504_property_enabled_value);

        ALCityInstanceInPL ins_100702 = new ALCityInstanceInPL("100702",7,2,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100702);
        AttributeValue  ins_100702_property_text_value= new AttributeValue(null,null,null,"-",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100702.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100702_property_text_value);

        AttributeValue  ins_100702_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100702.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100702_property_enabled_value);

        ALCityInstanceInPL ins_100703 = new ALCityInstanceInPL("100703",7,3,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100703);
        AttributeValue  ins_100703_property_text_value= new AttributeValue(null,null,null,"-",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100703.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100703_property_text_value);

        AttributeValue  ins_100703_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100703.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100703_property_enabled_value);

        ALCityInstanceInPL ins_100704 = new ALCityInstanceInPL("100704",7,4,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100704);
        AttributeValue  ins_100704_property_text_value= new AttributeValue(null,null,null,"-",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100704.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100704_property_text_value);

        AttributeValue  ins_100704_property_enabled_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100704.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100704_property_enabled_value);

        ALCityInstanceInPL ins_100107 = new ALCityInstanceInPL("100107",1,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100107);
        AttributeValue  ins_100107_property_text_value= new AttributeValue(null,null,null,"0",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100107.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100107_property_text_value);

        AttributeValue  ins_100107_property_enabled_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,textObject_property_enabled,1L,now,now,admin_1,admin_1,ins_100107.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100107_property_enabled_value);

        ALCityInstanceInPL ins_100207 = new ALCityInstanceInPL("100207",2,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100207);
        AttributeValue  ins_100207_property_text_value= new AttributeValue(null,null,null,"1",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100207.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100207_property_text_value);

        ALCityInstanceInPL ins_100307 = new ALCityInstanceInPL("100307",3,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100307);
        AttributeValue  ins_100307_property_text_value= new AttributeValue(null,null,null,"2",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100307.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100307_property_text_value);

        ALCityInstanceInPL ins_100407 = new ALCityInstanceInPL("100407",4,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100407);
        AttributeValue  ins_100407_property_text_value= new AttributeValue(null,null,null,"3",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100407.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100407_property_text_value);

        ALCityInstanceInPL ins_100507 = new ALCityInstanceInPL("100507",5,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100507);
        AttributeValue  ins_100507_property_text_value= new AttributeValue(null,null,null,"4",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100507.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100507_property_text_value);

        ALCityInstanceInPL ins_100607 = new ALCityInstanceInPL("100607",6,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100607);
        AttributeValue  ins_100607_property_text_value= new AttributeValue(null,null,null,"5",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100607.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100607_property_text_value);

        ALCityInstanceInPL ins_100707 = new ALCityInstanceInPL("100707",7,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100707);
        AttributeValue  ins_100707_property_text_value= new AttributeValue(null,null,null,"6",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100707.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100707_property_text_value);

        ALCityInstanceInPL ins_100807 = new ALCityInstanceInPL("100807",8,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100807);
        AttributeValue  ins_100807_property_text_value= new AttributeValue(null,null,null,"7",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100807.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100807_property_text_value);

        ALCityInstanceInPL ins_100907 = new ALCityInstanceInPL("100907",9,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100907);
        AttributeValue  ins_100907_property_text_value= new AttributeValue(null,null,null,"8",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100907.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100907_property_text_value);

        ALCityInstanceInPL ins_101007 = new ALCityInstanceInPL("101007",10,7,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_101007);
        AttributeValue  ins_101007_property_text_value= new AttributeValue(null,null,null,"9",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_101007.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_101007_property_text_value);

        ALCityInstanceInPL ins_100109 = new ALCityInstanceInPL("100109",1,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100109);
        AttributeValue  ins_100109_property_text_value= new AttributeValue(null,null,null,"0",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100109.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100109_property_text_value);

        ALCityInstanceInPL ins_100209 = new ALCityInstanceInPL("100209",2,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100209);
        AttributeValue  ins_100209_property_text_value= new AttributeValue(null,null,null,"1",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100209.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100209_property_text_value);

        ALCityInstanceInPL ins_100309 = new ALCityInstanceInPL("100309",3,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100309);
        AttributeValue  ins_100309_property_text_value= new AttributeValue(null,null,null,"2",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100309.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100309_property_text_value);

        ALCityInstanceInPL ins_100409 = new ALCityInstanceInPL("100409",4,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100409);
        AttributeValue  ins_100409_property_text_value= new AttributeValue(null,null,null,"3",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100409.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100409_property_text_value);

        ALCityInstanceInPL ins_100509 = new ALCityInstanceInPL("100509",5,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100509);
        AttributeValue  ins_100509_property_text_value= new AttributeValue(null,null,null,"4",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100509.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100509_property_text_value);

        ALCityInstanceInPL ins_100609 = new ALCityInstanceInPL("100609",6,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100609);
        AttributeValue  ins_100609_property_text_value= new AttributeValue(null,null,null,"5",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100609.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100609_property_text_value);

        ALCityInstanceInPL ins_100709 = new ALCityInstanceInPL("100709",7,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100709);
        AttributeValue  ins_100709_property_text_value= new AttributeValue(null,null,null,"6",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100709.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100709_property_text_value);

        ALCityInstanceInPL ins_100809 = new ALCityInstanceInPL("100809",8,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100809);
        AttributeValue  ins_100809_property_text_value= new AttributeValue(null,null,null,"7",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100809.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100809_property_text_value);

        ALCityInstanceInPL ins_100909 = new ALCityInstanceInPL("100909",9,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_100909);
        AttributeValue  ins_100909_property_text_value= new AttributeValue(null,null,null,"8",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_100909.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_100909_property_text_value);

        ALCityInstanceInPL ins_101009 = new ALCityInstanceInPL("101009",10,9,0,textObject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(ins_101009);
        AttributeValue  ins_101009_property_text_value= new AttributeValue(null,null,null,"9",null,null,null,null,Boolean.FALSE,null,textObject_property_text,1L,now,now,admin_1,admin_1,ins_101009.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(ins_101009_property_text_value);



        byte[] Multiply_imageobject_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","multyply_bg.png");
        BinaryContent Multiply_imageobject_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"multyply_bg",Multiply_imageobject_bytes.length,Multiply_imageobject_bytes,null,"multyply_bg","","",BinaryContentType.Image);
        binaryContentService.save(Multiply_imageobject_bytes_content);

        Attribute ImageObject01_property_bgImage =new Attribute("bgImage",imageobject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_bgImage);
        AttributeValue  Image0object_variable_2_value= new AttributeValue(false,null,null,null,null,null,Multiply_imageobject_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,imageobject_in_Multiply.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_variable_2_value);


        byte[] instance_img0_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","instance_img0.png");
        BinaryContent instance_img0_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img0",instance_img0_bytes.length,instance_img0_bytes,null,"instance_img0","","",BinaryContentType.Image);
        binaryContentService.save(instance_img0_bytes_content);

        ALCityInstanceInPL instance_img0 = new ALCityInstanceInPL("instance_img0",5,3,0,imageobject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0);
        AttributeValue  instance_img0_property_text_value= new AttributeValue(null,null,null,null,null,null,instance_img0_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img0_property_text_value);

        byte[] instance_img0101_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","instance_img0101.png");
        BinaryContent instance_img0101_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img0101",instance_img0101_bytes.length,instance_img0101_bytes,null,"instance_img0101","","",BinaryContentType.Image);
        binaryContentService.save(instance_img0101_bytes_content);

        ALCityInstanceInPL instance_img0101 = new ALCityInstanceInPL("instance_img0101",9,2,0,imageobject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0101);
        AttributeValue  instance_img0101_property_text_value= new AttributeValue(null,null,null,null,null,null,instance_img0101_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img0101.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img0101_property_text_value);

        byte[] instance_img0102_bytes = ImageUtil.getImage("src/main/resources/images/Multiply_Game/","instance_img0102.png");
        BinaryContent instance_img0102_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img0102",instance_img0102_bytes.length,instance_img0102_bytes,null,"instance_img0101","","",BinaryContentType.Image);
        binaryContentService.save(instance_img0102_bytes_content);

        ALCityInstanceInPL instance_img0102 = new ALCityInstanceInPL("instance_img0102",9,4,0,imageobject_in_Multiply,Multiply_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0102);
        AttributeValue  instance_img0102_property_text_value= new AttributeValue(null,null,null,null,null,null,instance_img0102_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img0102.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img0102_property_text_value);


        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRulePostActionType> FireEventAction_resetoutput = plRulePostActionTypeService.findByValue("FireEventAction:resetoutput");
        Optional<PLRulePostActionType> FireEventAction_update = plRulePostActionTypeService.findByValue("FireEventAction:update");
        Optional<PLRulePostActionType> FireEventAction_startTimer = plRulePostActionTypeService.findByValue("FireEventAction:startTimer");
        Optional<PLRulePostActionType> FireEventAction_check = plRulePostActionTypeService.findByValue("FireEventAction:check");
        Optional<PLRulePostActionType> CallObjectAction = plRulePostActionTypeService.findByValue("CallObjectAction");
        Optional<PLRulePostActionType> Callsystemaction = plRulePostActionTypeService.findByValue("callsystemaction");
        Optional<PLRuleEvent> internalevent_startTimer = plRuleEventService.findByName("internalevent:startTimer");
        Optional<PLRuleEvent> ActionComplete_Event = plRuleEventService.findByName("ActionComplete");
        Optional<PLRuleEvent> Internal_Event_resetoutput = plRuleEventService.findByName("internalevent:resetoutput");
        Optional<PLRuleEvent> internalevent_update = plRuleEventService.findByName("internalevent:update");
        Optional<PLRuleEvent> internalevent_check = plRuleEventService.findByName("internalevent:check");
        Optional<PLRulePostActionType> VariableAssignmentAction = plRulePostActionTypeService.findByValue("VariableAssignmentAction");

        StringBuffer    click_rule_condition = new StringBuffer("equal(BoardVar(ready),true) & equal(EventParam(row),9) & equal(EventParam(col),2)");
        Boolean ignoreRemaining = false;
        PLRule rule_for_ClickOnNext = new PLRule("ClickOnNext",1
                ,click_rule_condition,ignoreRemaining,Multiply_Game,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_ClickOnNext);

        PLRulePostAction FireEventAction_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,FireEventAction_resetoutput.get(),1,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_ClickOnNext);


        Attribute FireEventAction_ResetOutput_ClickOnNext_param_1=new Attribute("row",FireEventAction_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_ResetOutput_ClickOnNext_param_1);
        AttributeValue  FireEventAction_ResetOutput_ClickOnNext_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_ResetOutput_ClickOnNext_param_1,1L,now,now,admin_1,admin_1,FireEventAction_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_ResetOutput_ClickOnNext_param_1_value);

        Attribute FireEventAction_ResetOutput_ClickOnNext_param_2=new Attribute("col",FireEventAction_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_ResetOutput_ClickOnNext_param_2);
        AttributeValue  FireEventAction_ResetOutput_ClickOnNext_param_2_value= new AttributeValue(null,7,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_ResetOutput_ClickOnNext_param_2,1L,now,now,admin_1,admin_1,FireEventAction_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_ResetOutput_ClickOnNext_param_2_value);

        PLRulePostAction fireEventAction_ResetOutput_2_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,FireEventAction_resetoutput.get(),2,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(fireEventAction_ResetOutput_2_ClickOnNext);

        Attribute FireEventAction_2_ResetOutput_ClickOnNext_param_1=new Attribute("row",fireEventAction_ResetOutput_2_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_2_ResetOutput_ClickOnNext_param_1);
        AttributeValue  FireEventAction_2_ResetOutput_ClickOnNext_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_2_ResetOutput_ClickOnNext_param_1,1L,now,now,admin_1,admin_1,fireEventAction_ResetOutput_2_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_2_ResetOutput_ClickOnNext_param_1_value);

        Attribute FireEventAction_2_ResetOutput_ClickOnNext_param_2=new Attribute("col",fireEventAction_ResetOutput_2_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_2_ResetOutput_ClickOnNext_param_2);
        AttributeValue  FireEventAction_2_ResetOutput_ClickOnNext_param_2_value= new AttributeValue(null,9,null,null,null,null,null,null,Boolean.FALSE,null,FireEventAction_2_ResetOutput_ClickOnNext_param_2,1L,now,now,admin_1,admin_1,fireEventAction_ResetOutput_2_ClickOnNext.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_2_ResetOutput_ClickOnNext_param_2_value);


        PLRulePostAction VariableAssignmentAction_1_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),3,"","",
                "BoardVar(firstValue)",new StringBuffer("Random(1)"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_2_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),4,"","",
                "InstProp(InstByPos(5, 2), text)",new StringBuffer("BoardVar(firstValue)"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2_ClickOnNext);

        PLRulePostAction callObjectAction_1_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,CallObjectAction.get(),5,"show","InstProp(InstByPos(5, 2), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callObjectAction_1_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_3_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),6,"","",
                "BoardVar(secondValue)",new StringBuffer("Random(1)"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_3_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_4_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),7,"","",
                "InstProp(InstByPos(5, 4), text)",new StringBuffer("BoardVar(secondValue)"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_4_ClickOnNext);

        PLRulePostAction callObjectAction_2_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,CallObjectAction.get(),8,"show","InstProp(InstByPos(5, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callObjectAction_2_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_5_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),9,"","",
                "BoardVar(started)",new StringBuffer("true"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_5_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_6_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),10,"","",
                "BoardVar(ready)",new StringBuffer("false"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_6_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_7_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),11,"","",
                "BoardVar(userFirstValue)",new StringBuffer("0"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_7_ClickOnNext);

        PLRulePostAction VariableAssignmentAction_8_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,VariableAssignmentAction.get(),12,"","",
                "BoardVar(userSecondValue)",new StringBuffer("0"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_8_ClickOnNext);

        PLRulePostAction fireEvent_Action_ClickOnNext = new PLRulePostAction(rule_for_ClickOnNext,FireEventAction_startTimer.get(),13,"","",
                "",new StringBuffer("0"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(fireEvent_Action_ClickOnNext);




        StringBuffer    reset_user_values_condition = new StringBuffer("EventParam(row)<11");
        Boolean ignoreRemaining2 = false;
        PLRule reset_user_rule = new PLRule("reset user values",2
                ,reset_user_values_condition,ignoreRemaining2,Multiply_Game,Internal_Event_resetoutput.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(reset_user_rule);

        PLRulePostAction callobjectaction_reset_user_rule = new PLRulePostAction(reset_user_rule,CallObjectAction.get(),1,"disable","InstProp(InstByPos(EventParam(row), EventParam(col)), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_reset_user_rule);

        PLRulePostAction FireEventAction_resetoutput_reset_user_rule = new PLRulePostAction(reset_user_rule,FireEventAction_resetoutput.get(),2,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_resetoutput_reset_user_rule);

        Attribute FireEventAction_resetoutput_reset_user_rule_param_1=new Attribute("row",FireEventAction_resetoutput_reset_user_rule.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_resetoutput_reset_user_rule_param_1);
        AttributeValue  FireEventAction_resetoutput_reset_user_rule_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(row)+1",Boolean.TRUE,null,FireEventAction_resetoutput_reset_user_rule_param_1,1L,now,now,admin_1,admin_1,FireEventAction_resetoutput_reset_user_rule.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_resetoutput_reset_user_rule_param_1_value);

        Attribute FireEventAction_resetoutput_reset_user_rule_param_2=new Attribute("col",FireEventAction_resetoutput_reset_user_rule.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_resetoutput_reset_user_rule_param_2);
        AttributeValue  FireEventAction_resetoutput_reset_user_rule_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(col)",Boolean.TRUE,null,FireEventAction_resetoutput_reset_user_rule_param_2,1L,now,now,admin_1,admin_1,FireEventAction_resetoutput_reset_user_rule.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_resetoutput_reset_user_rule_param_2_value);





        StringBuffer    startTimer_condition = new StringBuffer("1 = 1");
        Boolean ignoreRemaining3 = false;
        PLRule startTimer_rule = new PLRule("startTimer",3
                ,startTimer_condition,ignoreRemaining3,Multiply_Game,internalevent_startTimer.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(startTimer_rule);

        PLRulePostAction callobjectaction_1_startTimer = new PLRulePostAction(startTimer_rule,CallObjectAction.get(),1,"show","InstProp(InstByPos(7, 2), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_startTimer);

        Attribute callobjectaction_1_startTimer_param_1=new Attribute("bgColor",callobjectaction_1_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_1_startTimer_param_1);
        AttributeValue  callobjectaction_1_startTimer_param_1_value= new AttributeValue(null,null,null,"green",null,null,null,null,Boolean.FALSE,null,callobjectaction_1_startTimer_param_1,1L,now,now,admin_1,admin_1,callobjectaction_1_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_1_startTimer_param_1_value);

        PLRulePostAction callobjectaction_2_startTimer = new PLRulePostAction(startTimer_rule,CallObjectAction.get(),2,"show","InstProp(InstByPos(7, 3), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_startTimer);

        Attribute callobjectaction_2_startTimer_param_1=new Attribute("bgColor",callobjectaction_2_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_2_startTimer_param_1);
        AttributeValue  callobjectaction_2_startTimer_param_1_value= new AttributeValue(null,null,null,"green",null,null,null,null,Boolean.FALSE,null,callobjectaction_2_startTimer_param_1,1L,now,now,admin_1,admin_1,callobjectaction_2_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_1_value);

        PLRulePostAction callobjectaction_3_startTimer = new PLRulePostAction(startTimer_rule,CallObjectAction.get(),3,"show","InstProp(InstByPos(7, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_3_startTimer);

        Attribute callobjectaction_3_startTimer_param_1=new Attribute("bgColor",callobjectaction_3_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_3_startTimer_param_1);
        AttributeValue  callobjectaction_3_startTimer_param_1_value= new AttributeValue(null,null,null,"green",null,null,null,null,Boolean.FALSE,null,callobjectaction_3_startTimer_param_1,1L,now,now,admin_1,admin_1,callobjectaction_3_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_3_startTimer_param_1_value);

        PLRulePostAction callsystemaction_startTimer = new PLRulePostAction(startTimer_rule,Callsystemaction.get(),4,"StartTimer","",
                "",new StringBuffer(""),"","",30L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callsystemaction_startTimer);

        Attribute callsystemaction_startTimer_param_1=new Attribute("duration",callsystemaction_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(callsystemaction_startTimer_param_1);
        AttributeValue  callsystemaction_startTimer_param_1_value= new AttributeValue(null,1000,null,null,null,null,null,null,Boolean.FALSE,null,callsystemaction_startTimer_param_1,1L,now,now,admin_1,admin_1,callsystemaction_startTimer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callsystemaction_startTimer_param_1_value);


        StringBuffer    Remained_2_Seconds_condition = new StringBuffer("equal(BoardVar(started), true) & equal(EventParam(actionId),30)");
        Boolean ignoreRemaining4 = false;
        PLRule Remained_2_Seconds_rule = new PLRule("Remained 2 Seconds",4
                ,Remained_2_Seconds_condition,ignoreRemaining4,Multiply_Game,ActionComplete_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Remained_2_Seconds_rule);

        PLRulePostAction callobjectaction_1_Remained_2_Seconds = new PLRulePostAction(Remained_2_Seconds_rule,CallObjectAction.get(),1,"show","InstProp(InstByPos(7, 2), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_Remained_2_Seconds);

        Attribute callobjectaction_1_Remained_2_Seconds_param_1=new Attribute("bgColor",callobjectaction_1_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_1_Remained_2_Seconds_param_1);
        AttributeValue  callobjectaction_1_Remained_2_Seconds_param_1_value= new AttributeValue(null,null,null,"white",null,null,null,null,Boolean.FALSE,null,callobjectaction_1_Remained_2_Seconds_param_1,1L,now,now,admin_1,admin_1,callobjectaction_1_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_1_Remained_2_Seconds_param_1_value);

        PLRulePostAction callobjectaction_2_Remained_2_Seconds = new PLRulePostAction(Remained_2_Seconds_rule,CallObjectAction.get(),2,"show","InstProp(InstByPos(7, 3), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_Remained_2_Seconds);

        Attribute callobjectaction_2_Remained_2_Seconds_param_1=new Attribute("bgColor",callobjectaction_2_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_2_Remained_2_Seconds_param_1);
        AttributeValue  callobjectaction_2_Remained_2_Seconds_param_1_value= new AttributeValue(null,null,null,"orange",null,null,null,null,Boolean.FALSE,null,callobjectaction_2_Remained_2_Seconds_param_1,1L,now,now,admin_1,admin_1,callobjectaction_2_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_Remained_2_Seconds_param_1_value);

        PLRulePostAction callobjectaction_3_Remained_2_Seconds = new PLRulePostAction(Remained_2_Seconds_rule,CallObjectAction.get(),2,"show","InstProp(InstByPos(7, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_3_Remained_2_Seconds);

        Attribute callobjectaction_3_Remained_2_Seconds_param_1=new Attribute("bgColor",callobjectaction_3_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_3_Remained_2_Seconds_param_1);
        AttributeValue  callobjectaction_3_Remained_2_Seconds_param_1_value= new AttributeValue(null,null,null,"orange",null,null,null,null,Boolean.FALSE,null,callobjectaction_3_Remained_2_Seconds_param_1,1L,now,now,admin_1,admin_1,callobjectaction_3_Remained_2_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_3_Remained_2_Seconds_param_1_value);

        PLRulePostAction callsystemaction_startTimer_2 = new PLRulePostAction(Remained_2_Seconds_rule,Callsystemaction.get(),4,"StartTimer","",
                "",new StringBuffer(""),"","",20L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callsystemaction_startTimer_2);

        Attribute callsystemaction_startTimer_2_param_1=new Attribute("duration",callsystemaction_startTimer_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(callsystemaction_startTimer_2_param_1);
        AttributeValue  callsystemaction_startTimer_2_param_1_value= new AttributeValue(null,1000,null,null,null,null,null,null,Boolean.FALSE,null,callsystemaction_startTimer_2_param_1,1L,now,now,admin_1,admin_1,callsystemaction_startTimer_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callsystemaction_startTimer_2_param_1_value);




        StringBuffer    Remained_1_Seconds_condition = new StringBuffer("equal(BoardVar(started), true) & equal(EventParam(actionId),20)");
        Boolean ignoreRemaining5 = false;
        PLRule Remained_1_Seconds_rule = new PLRule("Remained 1 Seconds",5
                ,Remained_1_Seconds_condition,ignoreRemaining5,Multiply_Game,ActionComplete_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Remained_1_Seconds_rule);

        PLRulePostAction callobjectaction_1_Remained_1_Seconds = new PLRulePostAction(Remained_1_Seconds_rule,CallObjectAction.get(),1,"show","InstProp(InstByPos(7, 3), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_Remained_1_Seconds);

        Attribute callobjectaction_1_Remained_1_Seconds_param_1=new Attribute("bgColor",callobjectaction_1_Remained_1_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_1_Remained_1_Seconds_param_1);
        AttributeValue  callobjectaction_1_Remained_1_Seconds_param_1_value= new AttributeValue(null,null,null,"white",null,null,null,null,Boolean.FALSE,null,callobjectaction_1_Remained_1_Seconds_param_1,1L,now,now,admin_1,admin_1,callobjectaction_1_Remained_1_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_1_Remained_1_Seconds_param_1_value);

        PLRulePostAction callobjectaction_2_Remained_1_Seconds = new PLRulePostAction(Remained_1_Seconds_rule,CallObjectAction.get(),2,"show","InstProp(InstByPos(7, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_Remained_1_Seconds);

        Attribute callobjectaction_2_Remained_1_Seconds_param_1=new Attribute("bgColor",callobjectaction_2_Remained_1_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_2_Remained_1_Seconds_param_1);
        AttributeValue  callobjectaction_2_Remained_1_Seconds_param_1_value= new AttributeValue(null,null,null,"red",null,null,null,null,Boolean.FALSE,null,callobjectaction_2_Remained_1_Seconds_param_1,1L,now,now,admin_1,admin_1,callobjectaction_2_Remained_1_Seconds.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_Remained_1_Seconds_param_1_value);

        PLRulePostAction callsystemaction_startTimer_3 = new PLRulePostAction(Remained_1_Seconds_rule,Callsystemaction.get(),4,"StartTimer","",
                "",new StringBuffer(""),"","",10L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callsystemaction_startTimer_3);

        Attribute callsystemaction_startTimer_3_param_1=new Attribute("duration",callsystemaction_startTimer_3.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(callsystemaction_startTimer_3_param_1);
        AttributeValue  callsystemaction_startTimer_3_param_1_value= new AttributeValue(null,1000,null,null,null,null,null,null,Boolean.FALSE,null,callsystemaction_startTimer_3_param_1,1L,now,now,admin_1,admin_1,callsystemaction_startTimer_3.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callsystemaction_startTimer_3_param_1_value);



        StringBuffer    Finished_Timer_condition = new StringBuffer("equal(BoardVar(started), true) & equal(EventParam(actionId),10)");
        Boolean ignoreRemaining6 = false;
        PLRule Finished_Timer_rule = new PLRule("Finished Timer",6
                ,Finished_Timer_condition,ignoreRemaining6,Multiply_Game,ActionComplete_Event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Finished_Timer_rule);

        PLRulePostAction callobjectaction_1_Finished_Timer = new PLRulePostAction(Finished_Timer_rule,CallObjectAction.get(),1,"show","InstProp(InstByPos(7, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_Finished_Timer);

        Attribute callobjectaction_1_Finished_Timer_param_1=new Attribute("bgColor",callobjectaction_1_Finished_Timer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_1_Finished_Timer_param_1);
        AttributeValue  callobjectaction_1_Finished_Timer_param_1_value= new AttributeValue(null,null,null,"white",null,null,null,null,Boolean.FALSE,null,callobjectaction_1_Finished_Timer_param_1,1L,now,now,admin_1,admin_1,callobjectaction_1_Finished_Timer.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_1_Finished_Timer_param_1_value);

        PLRulePostAction fireEventAction_update = new PLRulePostAction(Finished_Timer_rule,FireEventAction_update.get(),2,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(fireEventAction_update);
        Attribute fireEventAction_update_param_1=new Attribute("correct",fireEventAction_update.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(fireEventAction_update_param_1);
        AttributeValue  fireEventAction_update_param_1_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,fireEventAction_update_param_1,1L,now,now,admin_1,admin_1,fireEventAction_update.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(fireEventAction_update_param_1_value);


        StringBuffer    Finished_Step_condition = new StringBuffer("1=1");
        Boolean ignoreRemaining7 = false;
        PLRule Finished_Step_rule = new PLRule("Finished Step",7
                ,Finished_Step_condition,ignoreRemaining7,Multiply_Game,internalevent_update.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(Finished_Step_rule);


        PLRulePostAction VariableAssignmentAction_1_Finished_Step = new PLRulePostAction(Finished_Step_rule,VariableAssignmentAction.get(),1,"","",
                "BoardVar(progress)",new StringBuffer("BoardVar(progress) + 10"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_Finished_Step);


        PLRulePostAction callobjectaction_1_Finished_Step = new PLRulePostAction(Finished_Step_rule,CallObjectAction.get(),2,"show","InstProp(InstByPos(2, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_Finished_Step);

        Attribute callobjectaction_1_Finished_Step_param_1=new Attribute("text",callobjectaction_1_Finished_Step.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_1_Finished_Step_param_1);
        AttributeValue  callobjectaction_1_Finished_Step_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"concat(BoardVar(progress),'percent')",Boolean.TRUE,null,callobjectaction_1_Finished_Step_param_1,1L,now,now,admin_1,admin_1,callobjectaction_1_Finished_Step.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_1_Finished_Step_param_1_value);

        PLRulePostAction VariableAssignmentAction_2_Finished_Step = new PLRulePostAction(Finished_Step_rule,VariableAssignmentAction.get(),3,"","",
                "BoardVar(score)",new StringBuffer("BoardVar(score) + cond(equal(EventParam(correct),1),10,0)"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2_Finished_Step);

        PLRulePostAction callobjectaction_2_Finished_Step = new PLRulePostAction(Finished_Step_rule,CallObjectAction.get(),4,"show","InstProp(InstByPos(3, 4), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_Finished_Step);

        Attribute callobjectaction_2_Finished_Step_param_1=new Attribute("text",callobjectaction_2_Finished_Step.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(callobjectaction_2_Finished_Step_param_1);
        AttributeValue  callobjectaction_2_Finished_Step_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"BoardVar(score)",Boolean.TRUE,null,callobjectaction_2_Finished_Step_param_1,1L,now,now,admin_1,admin_1,callobjectaction_2_Finished_Step.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_Finished_Step_param_1_value);

        PLRulePostAction VariableAssignmentAction_3_Finished_Step = new PLRulePostAction(Finished_Step_rule,VariableAssignmentAction.get(),5,"","",
                "BoardVar(started)",new StringBuffer("false"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_3_Finished_Step);

        PLRulePostAction VariableAssignmentAction_4_Finished_Step = new PLRulePostAction(Finished_Step_rule,VariableAssignmentAction.get(),6,"","",
                "BoardVar(ready)",new StringBuffer("true"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_4_Finished_Step);

        PLRulePostAction FireEventAction_check_Finished_Step = new PLRulePostAction(Finished_Step_rule,FireEventAction_check.get(),7,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_check_Finished_Step);


        StringBuffer    ClickOnCheck_condition = new StringBuffer("equal(BoardVar(started),true) & equal(EventParam(row),9) & equal(EventParam(col),4)");
        Boolean ignoreRemaining8 = false;
        PLRule ClickOnCheck_rule = new PLRule("ClickOnCheck",8
                ,ClickOnCheck_condition,ignoreRemaining8,Multiply_Game,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(ClickOnCheck_rule);

        PLRulePostAction FireEventAction_update_ClickOnCheck = new PLRulePostAction(ClickOnCheck_rule,FireEventAction_update.get(),1,"","",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_update_ClickOnCheck);

        Attribute FireEventAction_update_ClickOnCheck_param_1=new Attribute("correct",FireEventAction_update_ClickOnCheck.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(FireEventAction_update_ClickOnCheck_param_1);
        AttributeValue  FireEventAction_update_ClickOnCheck_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"equal(BoardVar(firstValue)*BoardVar(secondValue),BoardVar(userFirstValue)*10+BoardVar(userSecondValue))",Boolean.TRUE,null,FireEventAction_update_ClickOnCheck_param_1,1L,now,now,admin_1,admin_1,FireEventAction_update_ClickOnCheck.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(FireEventAction_update_ClickOnCheck_param_1_value);

        StringBuffer    ClickOnFirstColumn_condition = new StringBuffer("equal(BoardVar(started),true) & equal(EventParam(col),7)");
        Boolean ignoreRemaining9 = false;
        PLRule ClickOnFirstColumn_rule = new PLRule("ClickOnFirstColumn",9
                ,ClickOnFirstColumn_condition,ignoreRemaining9,Multiply_Game,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(ClickOnFirstColumn_rule);

        PLRulePostAction callobjectaction_1_ClickOnFirstColumn = new PLRulePostAction(ClickOnFirstColumn_rule,CallObjectAction.get(),1,"disable","InstProp(InstByPos(BoardVar(userFirstValue)+1, 7), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_ClickOnFirstColumn);

        PLRulePostAction VariableAssignmentAction_1_ClickOnFirstColumn = new PLRulePostAction(ClickOnFirstColumn_rule,VariableAssignmentAction.get(),2,"","",
                "BoardVar(userFirstValue)",new StringBuffer("EventParam(row)-1"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_ClickOnFirstColumn);

        PLRulePostAction callobjectaction_2_ClickOnFirstColumn = new PLRulePostAction(ClickOnFirstColumn_rule,CallObjectAction.get(),3,"enable","InstProp(InstByPos(EventParam(row), 7), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_ClickOnFirstColumn);



        StringBuffer    ClickOnSecondColumn_condition = new StringBuffer("equal(BoardVar(started),true) & equal(EventParam(col),9)");
        Boolean ignoreRemaining10 = false;
        PLRule ClickOnSecondColumn_rule = new PLRule("ClickOnSecondColumn",10
                ,ClickOnSecondColumn_condition,ignoreRemaining10,Multiply_Game,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(ClickOnSecondColumn_rule);

        PLRulePostAction callobjectaction_1_ClickOnSecondColumn = new PLRulePostAction(ClickOnSecondColumn_rule,CallObjectAction.get(),3,"disable","InstProp(InstByPos(BoardVar(userSecondValue)+1, 9), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_1_ClickOnSecondColumn);

        PLRulePostAction VariableAssignmentAction_1_ClickOnSecondColumn = new PLRulePostAction(ClickOnSecondColumn_rule,VariableAssignmentAction.get(),2,"","",
                "BoardVar(userSecondValue)",new StringBuffer("EventParam(row)-1"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_ClickOnSecondColumn);

        PLRulePostAction callobjectaction_2_ClickOnSecondColumn = new PLRulePostAction(ClickOnSecondColumn_rule,CallObjectAction.get(),3,"enable","InstProp(InstByPos(EventParam(row), 9), objectId)",
                "",new StringBuffer(""),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(callobjectaction_2_ClickOnSecondColumn);

        StringBuffer    ChekcCompletion_condition = new StringBuffer("equal(BoardVar(progress), 100)");
        Boolean ignoreRemaining11 = false;
        PLRule ChekcCompletion_rule = new PLRule("CheckCompletion",11
                ,ChekcCompletion_condition,ignoreRemaining11,Multiply_Game,internalevent_check.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(ChekcCompletion_rule);

        PLRulePostAction VariableAssignmentAction_1_ChekcCompletion = new PLRulePostAction(ChekcCompletion_rule,VariableAssignmentAction.get(),1,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_ChekcCompletion);


    }





}
