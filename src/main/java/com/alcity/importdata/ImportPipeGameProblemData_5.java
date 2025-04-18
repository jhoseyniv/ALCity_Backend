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
public class ImportPipeGameProblemData_5 implements CommandLineRunner {

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
        log.info("Start Application...Import Problem 3 X-O Probelm");
        System.out.println("...Import X-O Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        Optional<AppMember> admin_1Optional = applicationMemberService.findByUsername("admin");
        Optional<AppMember> jalalHoseyniOptional = applicationMemberService.findByUsername("jalal");
        Optional<AppMember> Alireza_ZareOptional = applicationMemberService.findByUsername("alireza");
        AppMember  admin_1=admin_1Optional.get();
        AppMember jalalHoseyni = jalalHoseyniOptional.get();
        AppMember Alireza_Zare = Alireza_ZareOptional.get();


        Optional<LearningTopic> pipe_Game_Topic = learningTopicService.findByTitle("Pipe_Game");

        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill memory_booster = learningSkillService.findByValue("memory_booster");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("walletItem_2");

        JsonReader reader = Json.createReader(new FileReader("src/main/resources/images/Pipe-Game/PipeGame-ground.json"));
        JsonObject jsonObject = reader.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGraphicDTO boardGraphic = objectMapper.readValue(jsonObject.toString(), BoardGraphicDTO.class);


        byte[] pl_Icon_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","MemGame_icon.png");
        BinaryContent pl_Icon_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Icon",pl_Icon_Memory_Game_bytes.length,pl_Icon_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Memory_Game_content);

        byte[] pl_pic_pipe_game_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","MemGame.png");
        BinaryContent pl_pic_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Picture",pl_pic_pipe_game_bytes.length,pl_pic_pipe_game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Memory_Game_content);

        byte[] pipe_game_bytes_bg = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","pipe_game_bg.png");
        BinaryContent pipe_game_bytes_bg_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"pipe_game_bg",pipe_game_bytes_bg.length,pipe_game_bytes_bg,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pipe_game_bytes_bg_content);



        Optional<PuzzleGroup>  puzzleGroup_Science =puzzleGroupService.findByTitle("Science Puzzle Group");

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("Public");

        PuzzleLevel pipe_Game= new PuzzleLevel(admin_1,now,1L,"pipe-Game","4548",8,15,5f,0f,2f,3f,puzzleGroup_Science.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,pl_Icon_Memory_Game_content,pl_Icon_Memory_Game_content,3L,now,now,admin_1,admin_1);
        pipe_Game.setIcon(pl_pic_Memory_Game_content);
        pipe_Game.setPicture(pl_pic_Memory_Game_content);
        puzzleLevelService.save(pipe_Game);


        byte[] pipe_image_LearningContent_Image_bytes = ImageUtil.getImage("src/main/resources/images/X-O Problem/","puzzle_group_X_O_Image.png");
        BinaryContent pg_pipe_Game_learning_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory-Game", pipe_image_LearningContent_Image_bytes.length, pipe_image_LearningContent_Image_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pg_pipe_Game_learning_content);

        Integer xPos=3;
        Integer yPos=3;
        Integer zPos=3;
        Integer xRotation=3;
        Boolean zoom=false;
        Boolean pan=false;
        Boolean rotation=false;

        byte[] boardGraphic2 = ImageUtil.convertObjectToBytes(boardGraphic);
        PLGround pl_Memory_Game_ground = new PLGround(4,5,xPos,yPos,zPos,xRotation,xRotation,xRotation,zoom,pan,rotation,pipe_Game, boardGraphic2,1L,now,now,admin_1,admin_1);
        puzzleLevelGroundService.save(pl_Memory_Game_ground);

        PermitedPlayer player_1_puzzleLevel_X_O = new PermitedPlayer(Alireza_Zare,pipe_Game,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_X_O);

        LearningContent learningContent_memory=new LearningContent("help to Memory","this content is about pipe Games",pg_pipe_Game_learning_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_memory);


        LearningTopicInPL pllLearningTopic_1 = new LearningTopicInPL(pipe_Game,pipe_Game_Topic.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(pllLearningTopic_1);

        Float playScore =1f;
        Integer stars = ToolBox.getPuzzleLevelStars(playScore,pipe_Game.getFirstStarScore(),pipe_Game.getSecondStarScore(),pipe_Game.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,pipe_Game,now,now,15L,playScore,stars,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PGLearningSkillContent puzzleSkillLearningContent_1 = new PGLearningSkillContent(memory_booster,puzzleGroup_Science.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,pipe_Game,now,now,GameStatus.Playing,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)<=20)");

        PLObjective pl_objective_1 = new PLObjective("Finished in max number of possible steps","Finished in max number of possible steps",
                10f,2f,condition_Objective_1,memory_booster,
                                                                 alCoin10WalletItem.get(),pipe_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)>20)");

        PLObjective pl_objective_2 = new PLObjective("Finished in more than max number of possible steps","Finished in morethan max number of possible steps",
                1f,2f,condition_Objective_2,memory_booster,
                alCoin10WalletItem.get(),pipe_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_2);


        Attribute pl_variable_finished =new Attribute("finished",pipe_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);
        AttributeValue pl_variable_finished_value= new AttributeValue(false,null,null,null,null,null,null,null,false,null,pl_variable_finished,1L,now,now,admin_1,admin_1,pipe_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_finished_value);

        Attribute pl_variable_steps =new Attribute("steps",pipe_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_steps);
        AttributeValue pl_variable_steps_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_steps,1L,now,now,admin_1,admin_1,pipe_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_steps_value);


        Attribute pl_variable_solved =new Attribute("solved",pipe_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_solved);
        AttributeValue pl_variable_solved_value= new AttributeValue(null,1,null,null,null,null,null,null,false,null,pl_variable_solved,1L,now,now,admin_1,admin_1,pipe_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_solved_value);

        Optional<ALCityObject> imageObject01 =alCityObjectService.findByTitle("ImageObject01");


        ALCityObjectInPG imageObject_in_pipegame = new ALCityObjectInPG("Image Pipe Puzzle Group with Image Object","PipeGame_ImageObject",puzzleGroup_Science.get(),imageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(imageObject_in_pipegame);

        DTOUtil.copyActionFromTo(imageObject01.get().getId(), imageObject_in_pipegame.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(imageObject_in_pipegame.getId(), POActionOwnerType.Puzzle_Group_Object);

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
        }

        Attribute ImageObject01_property_bgImage =new Attribute("bgImage",imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_bgImage);
        AttributeValue  Image0object_property_1_value= new AttributeValue(null,null,null,null,null,null,pipe_game_bytes_bg_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value);


        Attribute ImageObject01_variable_correctDir=new Attribute("correctDir",imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_correctDir);
        AttributeValue  Image0object_variable_1_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_1_value);

        Attribute ImageObject01_variable_currentDir =new Attribute("currentDir",imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_currentDir);
        AttributeValue  Image0object_variable_2_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_currentDir,1L,now,now,admin_1,admin_1,imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_2_value);


        Attribute ImageObject01_variable_possibleDirs =new Attribute("possibleDirs",imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_possibleDirs);
        AttributeValue  ImageObject01_variable_3_value= new AttributeValue(null,4,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_3_value);

        Attribute ImageObject01_variable_canRotate =new Attribute("canRotate",imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_canRotate);
        AttributeValue  ImageObject01_variable_ImageObject01_variable_canRotate_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,imageObject_in_pipegame.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_ImageObject01_variable_canRotate_value);


        ALCityInstanceInPL instance_img_1_1 = new ALCityInstanceInPL("instance_img_1_1",1,1,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_1);
        byte[] instance_img_1_1_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img1_1.png");
        BinaryContent instance_img_1_1_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img1_1",instance_img_1_1_bytes.length,instance_img_1_1_bytes,null,"instance_img10","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_1_1_bytes_content);

        AttributeValue  instance_img_1_1_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_1_1_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_1_1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_1_1_bgImage_value);


        ALCityInstanceInPL instance_img_1_2 = new ALCityInstanceInPL("instance_img_1_2",1,2,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_2);

        byte[] instance_img_1_2_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_1_2.png");
        BinaryContent instance_img_1_2_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_1_2",instance_img_1_2_bytes.length,instance_img_1_2_bytes,null,"instance_img_1_2","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_1_2_bytes_content);

        AttributeValue  instance_img_1_2_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_1_2_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_1_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_1_2_bgImage_value);

        AttributeValue instance_img_1_2_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_1_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_1_2_canRotate_value1);

        AttributeValue instance_img_1_2_correctDir_value1 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_1_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_1_2_correctDir_value1);



        ALCityInstanceInPL instance_img_1_3 = new ALCityInstanceInPL("instance_img_1_3",1,3,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_3);

        ALCityInstanceInPL instance_img_1_4 = new ALCityInstanceInPL("instance_img_1_4",1,4,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_4);

        ALCityInstanceInPL instance_img_1_5 = new ALCityInstanceInPL("instance_img1_5",1,5,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_1_5);

        ALCityInstanceInPL instance_img_2_1 = new ALCityInstanceInPL("instance_img_2_1",2,1,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_1);

        byte[] instance_img_2_2_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_2_2.png");
        BinaryContent instance_img_2_2_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_2_2",instance_img_2_2_bytes.length,instance_img_2_2_bytes,null,"instance_img_2_2","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_2_2_bytes_content);

        ALCityInstanceInPL instance_img_2_2 = new ALCityInstanceInPL("instance_img_2_2",2,2,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_2);

        AttributeValue instance_img_2_2_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_2_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_2_canRotate_value1);

        AttributeValue instance_img_2_2_correctDir_value1 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_2_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_2_correctDir_value1);

        AttributeValue instance_img_2_2_possibleDirs_value1 = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,instance_img_2_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_2_possibleDirs_value1);

        byte[] instance_img_2_3_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_2_3.png");
        BinaryContent instance_img_2_3_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_2_3",instance_img_2_3_bytes.length,instance_img_2_3_bytes,null,"instance_img_2_3","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_2_3_bytes_content);

        ALCityInstanceInPL instance_img_2_3 = new ALCityInstanceInPL("instance_img_2_3",2,3,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_3);

        AttributeValue instance_img_2_3_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_2_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_3_canRotate_value1);
        AttributeValue instance_img_2_3_correctDir_value1 = new AttributeValue(null,3,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_2_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_3_correctDir_value1);

        ALCityInstanceInPL instance_img_2_4 = new ALCityInstanceInPL("instance_img_2_4",2,4,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_4);

        byte[] instance_img_2_4_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_2_4.png");
        BinaryContent instance_img_2_4_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_2_4",instance_img_2_4_bytes.length,instance_img_2_4_bytes,null,"instance_img_2_4","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_2_4_bytes_content);
        AttributeValue instance_img_2_4_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_2_4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_4_canRotate_value1);
        AttributeValue instance_img_2_4_correctDir_value1 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_2_4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_4_correctDir_value1);

        AttributeValue instance_img_2_4_possibleDirs_value1 = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,instance_img_2_4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_4_possibleDirs_value1);

        ALCityInstanceInPL instance_img_2_5 = new ALCityInstanceInPL("instance_img_2_5",2,5,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_2_5);
        AttributeValue instance_img_2_5_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_2_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_5_canRotate_value1);

        AttributeValue instance_img_2_5_correctDir_value1 = new AttributeValue(null,0,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_2_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_2_5_correctDir_value1);

        ALCityInstanceInPL instance_img_3_1 = new ALCityInstanceInPL("instance_img_3_1",3,1,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_1);


        byte[] instance_img_3_2_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_3_2.png");
        BinaryContent instance_img_3_2_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_3_2",instance_img_3_2_bytes.length,instance_img_3_2_bytes,null,"instance_img_3_2","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_3_2_bytes_content);

        ALCityInstanceInPL instance_img_3_2 = new ALCityInstanceInPL("instance_img_3_2",3,2,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_2);

        AttributeValue  instance_img_3_2_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_3_2_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_3_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_3_2_bgImage_value);

        AttributeValue instance_img_3_2_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_3_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_2_canRotate_value1);

        AttributeValue instance_img_3_2_correctDir_value1 = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_3_2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_2_correctDir_value1);

        byte[] instance_img_3_3_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_3_3.png");
        BinaryContent instance_img_3_3_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_3_3",instance_img_3_3_bytes.length,instance_img_3_3_bytes,null,"instance_img_3_3","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_3_3_bytes_content);
        ALCityInstanceInPL instance_img_3_3 = new ALCityInstanceInPL("instance_img_3_3",3,3,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_3);

        AttributeValue  instance_img_3_3_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_3_3_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_3_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_3_3_bgImage_value);

        AttributeValue instance_img_3_3_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_3_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_3_canRotate_value1);
        AttributeValue instance_img_3_3_correctDir_value1 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_3_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_3_correctDir_value1);

        ALCityInstanceInPL instance_img_3_4 = new ALCityInstanceInPL("instance_img_3_4",3,4,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_4);

        byte[] instance_img_3_5_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_3_5.png");
        BinaryContent instance_img_3_5_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_3_5",instance_img_3_5_bytes.length,instance_img_3_5_bytes,null,"instance_img_3_5","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_3_5_bytes_content);

        ALCityInstanceInPL instance_img_3_5 = new ALCityInstanceInPL("instance_img_3_5",4,1,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_3_5);

        AttributeValue instance_img_3_5_canRotate_value1 = new AttributeValue(true,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img_3_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_5_canRotate_value1);

        AttributeValue  instance_img_3_5_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_3_5_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_3_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_3_5_bgImage_value);

        AttributeValue  instance_img_3_5_correctDir_value= new AttributeValue(null,1,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img_3_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_5_correctDir_value);

        AttributeValue instance_img_3_5_possibleDirs_value1 = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,instance_img_3_3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img_3_5_possibleDirs_value1);

        ALCityInstanceInPL instance_img_4_1 = new ALCityInstanceInPL("instance_img_4_1",4,1,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_1);

        ALCityInstanceInPL instance_img_4_2 = new ALCityInstanceInPL("instance_img_4_2",4,2,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_2);

        ALCityInstanceInPL instance_img_4_3 = new ALCityInstanceInPL("instance_img_4_3",4,3,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_3);

        ALCityInstanceInPL instance_img_4_4 = new ALCityInstanceInPL("instance_img_4_4",4,4,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_4);

        ALCityInstanceInPL instance_img_4_5 = new ALCityInstanceInPL("instance_img_4_5",4,5,0,imageObject_in_pipegame,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img_4_5);

        byte[] instance_img_4_5_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img_4_5.png");
        BinaryContent instance_img_4_5_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img_4_5",instance_img_4_5_bytes.length,instance_img_4_5_bytes,null,"instance_img_4_5","","",BinaryContentType.Image);
        binaryContentService.save(instance_img_4_5_bytes_content);

        AttributeValue  instance_img_4_5_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img_4_5_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img_4_5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img_4_5_bgImage_value);




    }





}
