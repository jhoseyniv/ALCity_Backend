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
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("al_coin_10");


      //  byte[]  planyGround_Image_Memory_Game = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","PipeGame-ground.json");
        JsonReader reader = Json.createReader(new FileReader("src/main/resources/images/X-O Problem/x-o-ground.json"));
        JsonObject jsonObject = reader.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGraphicDTO boardGraphic = objectMapper.readValue(jsonObject.toString(), BoardGraphicDTO.class);


        byte[] pl_Icon_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","MemGame_icon.png");
        BinaryContent pl_Icon_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Icon",pl_Icon_Memory_Game_bytes.length,pl_Icon_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Memory_Game_content);

        byte[] pl_pic_pipe_game_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","MemGame.png");
        BinaryContent pl_pic_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Picture",pl_pic_pipe_game_bytes.length,pl_pic_pipe_game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Memory_Game_content);

        BinaryContent puzzle_group_1_binary_content_image = binaryContentService.findByfileName("image_puzzle_group_matematic");



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

        byte[] boardGraphic2 = ImageUtil.convertObjectToBytes(boardGraphic);
        PLGround pl_Memory_Game_ground = new PLGround(4,5,xPos,yPos,zPos,xRotation,xRotation,xRotation,pipe_Game, boardGraphic2,1L,now,now,admin_1,admin_1);
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
        AttributeValue pl_variable_solved_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_solved,1L,now,now,admin_1,admin_1,pipe_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_solved_value);

        Optional<ALCityObject> imageObject01 =alCityObjectService.findByTitle("ImageObject01");


        ALCityObjectInPG imageObject01_in_pipeGame_puzzleGroup = new ALCityObjectInPG("Image Pipe Puzzle Group with Image Object","PipeGame_ImageObject",puzzleGroup_Science.get(),imageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(imageObject01_in_pipeGame_puzzleGroup);

        DTOUtil.copyActionFromTo(imageObject01.get().getId(), imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(imageObject01_in_pipeGame_puzzleGroup.getId(), POActionOwnerType.Puzzle_Group_Object);

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

        Attribute ImageObject01_property_bgImage =new Attribute("bgImage",imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_bgImage);
        AttributeValue  Image0object_property_1_value= new AttributeValue(null,null,null,null,null,null,puzzle_group_1_binary_content_image.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value);


        Attribute ImageObject01_variable_correctDir=new Attribute("correctDir",imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_correctDir);
        AttributeValue  Image0object_variable_1_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_1_value);

        Attribute ImageObject01_variable_currentDir =new Attribute("currentDir",imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_currentDir);
        AttributeValue  Image0object_variable_2_value= new AttributeValue(null,0,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_currentDir,1L,now,now,admin_1,admin_1,imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(Image0object_variable_2_value);


        Attribute ImageObject01_variable_possibleDirs =new Attribute("possibleDirs",imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_possibleDirs);
        AttributeValue  ImageObject01_variable_3_value= new AttributeValue(null,4,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_3_value);

        Attribute ImageObject01_variable_canRotate =new Attribute("canRotate",imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_canRotate);
        AttributeValue  ImageObject01_variable_ImageObject01_variable_canRotate_value= new AttributeValue(true,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,imageObject01_in_pipeGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_ImageObject01_variable_canRotate_value);


        ALCityInstanceInPL instance_img0 = new ALCityInstanceInPL("instance_img0",1,1,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0);

        AttributeValue ImageObject01_canRotate_value1 = new AttributeValue(false,null,null,null,null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_canRotate_value1);

        byte[] instance_img0_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img0.png");
        BinaryContent instance_img0_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img0",instance_img0_bytes.length,instance_img0_bytes,null,"instance_img0","","",BinaryContentType.Image);
        binaryContentService.save(instance_img0_bytes_content);

        AttributeValue  Image0object_bgImage_1_value1= new AttributeValue(null,null,null,null,null,null,instance_img0_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_bgImage_1_value1);



        ALCityInstanceInPL instance_img1 = new ALCityInstanceInPL("instance_img1",1,2,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1);

        AttributeValue instance_img1_variable_1_value1 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img1_variable_1_value1);

        byte[] instance_img1_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img1.png");
        BinaryContent instance_img1_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img1",instance_img1_bytes.length,instance_img1_bytes,null,"instance_img1","","",BinaryContentType.Image);
        binaryContentService.save(instance_img1_bytes_content);
        AttributeValue  Image0object_property_1_value1= new AttributeValue(null,null,null,null,null,null,instance_img1_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value1);


        byte[] instance_img2_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img2.png");
        BinaryContent instance_img2_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img2",instance_img2_bytes.length,instance_img2_bytes,null,"instance_img2","","",BinaryContentType.Image);
        binaryContentService.save(instance_img2_bytes_content);

        ALCityInstanceInPL instance_img2 = new ALCityInstanceInPL("instance_img2",1,3,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2);

        AttributeValue instance_img2_correctDir_value = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img2_correctDir_value);

        AttributeValue instance_img2_possibleDirs_value = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img2_possibleDirs_value);

        AttributeValue  instance_img2_property_bgimage_value= new AttributeValue(null,null,null,null,null,null,instance_img2_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img2_property_bgimage_value);

        byte[] instance_img3_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img3.png");
        BinaryContent instance_img3_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img3",instance_img3_bytes.length,instance_img3_bytes,null,"instance_img3","","",BinaryContentType.Image);
        binaryContentService.save(instance_img3_bytes_content);

        ALCityInstanceInPL instance_img3 = new ALCityInstanceInPL("instance_img3",1,4,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img3);

        AttributeValue ImageObject01_variable_correctDir_value = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_correctDir_value);

        AttributeValue  Image0object_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img3_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_bgImage_value);


        byte[] instance_img4_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img4.png");
        BinaryContent instance_img4_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img4",instance_img4_bytes.length,instance_img4_bytes,null,"instance_img4","","",BinaryContentType.Image);
        binaryContentService.save(instance_img4_bytes_content);

        ALCityInstanceInPL instance_img4 = new ALCityInstanceInPL("instance_img4",1,5,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img4);
        AttributeValue instance_img4_variable_2_value2 = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img4_variable_2_value2);

        AttributeValue  instance_img4_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img4_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img4_property_bgImage_value);



        ALCityInstanceInPL instance_img5 = new ALCityInstanceInPL("instance_img5",2,1,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img5);
        AttributeValue instance_img5_variable_2_value2 = new AttributeValue(null,3,null,"C",null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img5_variable_2_value2);

        AttributeValue  instance_img5_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img4_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img5_property_bgImage_value);


        byte[] instance_img6_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img6.png");
        BinaryContent instance_img6_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img4",instance_img6_bytes.length,instance_img6_bytes,null,"instance_img6","","",BinaryContentType.Image);
        binaryContentService.save(instance_img6_bytes_content);
        ALCityInstanceInPL instance_img6 = new ALCityInstanceInPL("instance_img6",2,1,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img6);

        AttributeValue instance_img6_variable_correctDir_value = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img6_variable_correctDir_value);

        AttributeValue instance_img6_variable_possibleDirs_value = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img6_variable_possibleDirs_value);

        AttributeValue  instance_img6_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img6_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img6_property_bgImage_value);


        byte[] instance_img7_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img7.png");
        BinaryContent instance_img7_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img7",instance_img7_bytes.length,instance_img7_bytes,null,"instance_img7","","",BinaryContentType.Image);
        binaryContentService.save(instance_img7_bytes_content);

        ALCityInstanceInPL instance_img7 = new ALCityInstanceInPL("instance_img7",2,3,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img7);
        AttributeValue instance_img7_variable_correctDir_value = new AttributeValue(null,0,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img7_variable_correctDir_value);

        AttributeValue  instance_img7_property_bgimage_value2= new AttributeValue(null,null,null,null,null,null,instance_img7_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img7_property_bgimage_value2);


        byte[] instance_img8_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img8.png");
        BinaryContent instance_img8_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img8",instance_img8_bytes.length,instance_img8_bytes,null,"instance_img8","","",BinaryContentType.Image);
        binaryContentService.save(instance_img8_bytes_content);
        ALCityInstanceInPL instance_img8 = new ALCityInstanceInPL("instance_img8",2,4,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img8);
        AttributeValue instance_img8_variable_correctDir_value = new AttributeValue(null,1,null,null,null,null,null,null,false,null,ImageObject01_variable_correctDir,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img8_variable_correctDir_value);

        AttributeValue instance_img8_variable_possibleDirs_value = new AttributeValue(null,2,null,null,null,null,null,null,false,null,ImageObject01_variable_possibleDirs,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img8_variable_possibleDirs_value);

        AttributeValue  instance_img8_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img8_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img8_property_bgImage_value);


        byte[] instance_img9_bytes = ImageUtil.getImage("src/main/resources/images/Pipe-Game/","instance_img9.png");
        BinaryContent instance_img9_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"instance_img9",instance_img9_bytes.length,instance_img9_bytes,null,"instance_img9","","",BinaryContentType.Image);
        binaryContentService.save(instance_img9_bytes_content);

        ALCityInstanceInPL instance_img9 = new ALCityInstanceInPL("instance_img9",2,5,0,imageObject01_in_pipeGame_puzzleGroup,pipe_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img9);
        AttributeValue instance_img9_canRotate_value = new AttributeValue(null,null,null,"E",null,null,null,null,false,null,ImageObject01_variable_canRotate,1L,now,now,admin_1,admin_1,instance_img9.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img9_canRotate_value);

        AttributeValue  instance_img9_property_bgImage_value= new AttributeValue(null,null,null,null,null,null,instance_img9_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_bgImage,1L,now,now,admin_1,admin_1,instance_img9.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img9_property_bgImage_value);


        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRuleEvent> internalEvent = plRuleEventService.findByName("internalevent");

        Optional<PLRulePostActionType> CallObjectAction = plRulePostActionTypeService.findByValue("CallObjectAction");
        Optional<PLRulePostActionType> UserAlertAction = plRulePostActionTypeService.findByValue("UserAlertAction");
        Optional<PLRulePostActionType> VariableAssignmentAction = plRulePostActionTypeService.findByValue("VariableAssignmentAction");
        Optional<PLRulePostActionType> FireEventAction = plRulePostActionTypeService.findByValue("FireEventAction");

        StringBuffer    click_rule_condition = new StringBuffer("equal(BoardVar(finished),false) & unequal(InstByPos(EventParam(row), EventParam(col)), null) & equal(InstVar(InstByPos(EventParam(row), EventParam(col)), canRotate),true)");
        Boolean ignoreRemaining = false;
        PLRule rule_for_Click = new PLRule("Click",1
                ,click_rule_condition,ignoreRemaining,pipe_Game,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_Click);

        PLRulePostAction VariableAssignmentAction_1 = new PLRulePostAction(rule_for_Click,VariableAssignmentAction.get(),1,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1);

        PLRulePostAction VariableAssignmentAction_2 = new PLRulePostAction(rule_for_Click,VariableAssignmentAction.get(),2,"","",
                "BoardVar(solved)",new StringBuffer("BoardVar(solved)-cond(equal(InstVar(InstByPos(EventParam(row), EventParam(col)), currentDir),InstVar(InstByPos(EventParam(row), EventParam(col)), correctDir)),1,0)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2);

        PLRulePostAction VariableAssignmentAction_3 = new PLRulePostAction(rule_for_Click,VariableAssignmentAction.get(),3,"","",
                "InstVar(InstByPos(EventParam(row), EventParam(col)), currentDir)",new StringBuffer("(InstVar(InstByPos(EventParam(row), EventParam(col)), currentDir)+1) % InstVar(InstByPos(EventParam(row), EventParam(col)), possibleDirs)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_3);

        PLRulePostAction VariableAssignmentAction_4 = new PLRulePostAction(rule_for_Click,VariableAssignmentAction.get(),4,"","",
                "BoardVar(solved)",new StringBuffer("BoardVar(solved)+cond(equal(InstVar(InstByPos(EventParam(row), EventParam(col)), currentDir),InstVar(InstByPos(EventParam(row), EventParam(col)), correctDir)),1,0)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_4);

        PLRulePostAction CallObjectAction_4_Click = new PLRulePostAction(rule_for_Click,CallObjectAction.get(),5,"Rotate","InstProp(InstByPos(EventParam(row),EventParam(col)),objectId)",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_4_Click);

        PLRulePostAction FireEventAction_5_Click = new PLRulePostAction(rule_for_Click,FireEventAction.get(),6,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_5_Click);



        StringBuffer   CheckCompletion_rule_condition = new StringBuffer("equal(BoardVar(solved),8)");
        Boolean ignoreRemaining2 = false;
        PLRule rule_for_CheckCompletion = new PLRule("CheckCompletion",2
                ,CheckCompletion_rule_condition,ignoreRemaining2,pipe_Game,internalEvent.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_CheckCompletion);


        PLRulePostAction VariableAssignmentAction_1_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion,VariableAssignmentAction.get(),1,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_CheckCompletion);


        PLRulePostAction UserAlertAction_2_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion,UserAlertAction.get(),2,"","",
                "",new StringBuffer(""),"info","mission completed!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(UserAlertAction_2_CheckCompletion);


    }





}
