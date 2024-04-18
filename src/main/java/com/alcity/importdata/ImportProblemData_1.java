package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alenum.POActionOwnerType;
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
import com.alcity.entity.ruleengine.UserEvent;
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
import com.alcity.service.ruleengine.SystemEventService;
import com.alcity.service.ruleengine.UserEventService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.ApplicationMember_WalletItemService;
import com.alcity.service.users.WalletItemService;
import com.alcity.utility.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Order(value=2)
@Component
public class ImportProblemData_1 implements CommandLineRunner {

    @Autowired
    private ApplicationMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;

    @Autowired
    private BinaryContentTypeService binaryContentTypeService;

    @Autowired
    PuzzleLevelGroundService puzzleLevelGroundService;
    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PuzzleGroupService puzzleGroupService;

    @Autowired
    private PuzzleDifficultyService puzzleDifficultyService;

    @Autowired
    private PuzzleLevelStatusService puzzleLevelStatusService;
    @Autowired
    private GameStatusService gameStatusService;
    @Autowired
    private PuzzleLevelPrivacyService puzzleLevelPrivacyService;

    @Autowired
    PermitedPlayerRepository permitedPlayerRepository;

    @Autowired
    PuzzleLevelLearningTopicService puzzleLevelLearningTopicService;

    @Autowired
    PlayHistoryService playHistoryService;


    @Autowired
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;

    @Autowired
    PuzzleLevelGameInstanceService puzzleLevelGameInstanceService;

    @Autowired
    ObjectCategoryService objectCategoryService;

    @Autowired
    PuzzleObjectService puzzleObjectService;

    @Autowired
    PuzzleGroupObjectInstanceService puzzleGroupObjectInstanceService;


    @Autowired
    ALCityAttributeValueService alCityAttributeValueService;


    @Autowired
    ObjectActionService objectActionService;


    @Autowired
    private CameraSetupService cameraSetupService;


    @Autowired
    PuzzleLevelRuleEventTypeService puzzleLevelRuleEventTypeService;

    @Autowired
    UserEventService userEventService;

    @Autowired
    SystemEventService systemEventService;
    @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    ActionRendererService actionRendererService;


    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;
    @Autowired
    PuzzleLevelRulePostActionTypeService puzzleLevelRulePostActionTypeService;

    @Autowired
    PuzzleGroup_PuzzleObjectService puzzleGroup_PuzzleObjectService;

    @Autowired
    PuzzleLevelRuleService puzzleLevelRuleService;


    @Autowired
    PuzzleLevelRuleEventService puzzleLevelRuleEventService;

    @Autowired
    PuzzleLevelRulePostActionService puzzleLevelRulePostActionService;

    @Autowired
    PuzzleLevelObjectiveService puzzleLevelObjectiveService;

    @Autowired
    ALCityAttributeService alCityAttributeService;


    @Autowired
    DataTypeService dataTypeService;

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
        log.info("Start Application...Import Problem 1");
        System.out.println("...Import Problem 1");

        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);
        // byte[] avatar = getImage("src/main/resources/images/","avatar.png");

        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        ApplicationMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        GameStatus gameStatus_1 = gameStatusService.findByValue("gameStatus_1");
        LearningTopic hashImage_Topic = learningTopicService.findByTitle("Hash Image");

        DataType alcity_Int = dataTypeService.findByValue("Integer");
        DataType alcity_Binary = dataTypeService.findByValue("Binary");
        DataType alcity_Long = dataTypeService.findByValue("Long");
        DataType alcity_Boolean = dataTypeService.findByValue("Boolean");
        DataType alcity_String =  dataTypeService.findByValue("String");

        BinaryContentType imageType= binaryContentTypeService.findByValue("image");

      //  LearningSkill constraint = learningSkillService.findByValue("Constraint");
      //  LearningSkill loop = learningSkillService.findByValue("loop");
        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill division = learningSkillService.findByValue("division");
        WalletItem alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

       // Optional<ApplicationMember_WalletItem> jalalHoseyni_alcoin_10 = applicationMember_walletItemService.findByApplicationMemberAndWalletItem(jalalHoseyni,alCoin10WalletItem);

        Journey journey_1 = journeyService.findByTitle("Journey_1");
        Journey journey_2 = journeyService.findByTitle("Journey_2");


        ZonedDateTime  createdDate= ZonedDateTime.now();
        Long now = createdDate.toEpochSecond();

        //CameraSetupDTO cameraSetupDTO = new CameraSetupDTO(5,5,5,5,5,5);

        byte[] puzzle_Ground_Image_1 = Util.getImage("src/main/resources/images/","playGround.png");
        BinaryContent puzzle_ground_binary_content_1 = new BinaryContent("puzzle ground for hash image",puzzle_Ground_Image_1,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_ground_binary_content_1);

        byte[] puzzle_group_Icon_1 = Util.getImage("src/main/resources/images/","puzzle_group_1.png");
        BinaryContent puzzle_group_binary_content_1 = new BinaryContent("image_puzzle_group_matematic",puzzle_group_Icon_1,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_binary_content_1);

        PuzzleCategory mathematic = puzzleCategoryService.findByValue("mathematic");

        PuzzleGroup puzzleGroup_1 = new PuzzleGroup("Hash Image - Puzzle Group 1",mathematic,puzzle_group_binary_content_1,puzzle_group_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleGroupService.save(puzzleGroup_1);

        JourneyStep journey_1_Step_1 = new JourneyStep("step1_journey_1",1,30,30,journey_1,puzzleGroup_1,1L,now,now,admin_1,admin_1);
        JourneyStep journey_1_Step_2 = new JourneyStep("step2_journey_1",1,30,30,journey_1,puzzleGroup_1,1L,now,now,admin_1,admin_1);
        JourneyStep journey_2_Step_1 = new JourneyStep("step1_journey_2",1,30,30,journey_2,puzzleGroup_1,1L,now,now,admin_1,admin_1);
        journeyStepService.save(journey_1_Step_1);
        journeyStepService.save(journey_1_Step_2);
        journeyStepService.save(journey_2_Step_1);


        PuzzleLevelDifficulty easy = puzzleDifficultyService.findByValue("Easy");
        PuzzleLevelStatus ongoing = puzzleLevelStatusService.findByValue("ongoing");
        PuzzleLevelPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_hashimage = new PuzzleLevel(now,1L,"arrange hash image","HASH_IMAGe",10,14,5f,puzzleGroup_1,easy,ongoing,privacy_1,puzzle_group_binary_content_1,puzzle_group_binary_content_1,3L,now,now,admin_1,admin_1);
        puzzleLevelService.save(puzzleLevel_hashimage);


        byte[] puzzle_group_Hash_Image = Util.getImage("src/main/resources/images/","hashImage.png");
        BinaryContent puzzle_group_Hash_Image_binary_content = new BinaryContent("hashImage",puzzle_group_Hash_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_Hash_Image_binary_content);

        Integer xPos=3;
        Integer xRotation=3;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);


        PuzzleLevelGround puzzleLevel_hashImage_ground = new PuzzleLevelGround(3,3,puzzleLevel_hashimage,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleLevel_hashImage_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_hashImage_ground);

        PermitedPlayer player_1_puzzleLevel_hashimage = new PermitedPlayer(jalalHoseyni,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_hashimage);

        LearningContent learningContent_Division=new LearningContent("help to math","this content is about hash images",puzzle_group_Hash_Image_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Division);

        PuzzleLevel_LearningTopic puzzleLevelLearningTopic_1 = new PuzzleLevel_LearningTopic(puzzleLevel_hashimage,hashImage_Topic,learningContent_Division,1L,now,now,admin_1,admin_1);
        puzzleLevelLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_hashimage,now,100,10f,1L,now,now,jalalHoseyni,jalalHoseyni);
        playHistoryService.save(playHistory_1);

        PuzzleSkillLearningContent puzzleSkillLearningContent_1 = new PuzzleSkillLearningContent(division,puzzleGroup_1,learningContent_Division,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PuzzleLevelGameInstance puzzleLevelGameInstance= new PuzzleLevelGameInstance(jalalHoseyni,puzzleLevel_hashimage,gameStatus_1,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleLevelGameInstanceService.save(puzzleLevelGameInstance);


        ObjectCategory objectCategory_bird = objectCategoryService.findByValue("Bird");
        ObjectCategory objectCategory_Mamals = objectCategoryService.findByValue("Mamals");
        ObjectCategory objectCategory_cereal = objectCategoryService.findByValue("cereal");
        ObjectCategory objectCategory_Image = objectCategoryService.findByValue("Image");

        BinaryContent eagle_Image_binary_content = binaryContentService.findByfileName("eagle image");
        BinaryContent goose_Image_binary_content = binaryContentService.findByfileName("goose image");
        BinaryContent fox_Image_binary_content = binaryContentService.findByfileName("fox image");
        BinaryContent wheat_Image_binary_content = binaryContentService.findByfileName("wheat image");

        PuzzleObject eagle = new PuzzleObject("eagle",objectCategory_bird,eagle_Image_binary_content,eagle_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(eagle);

        PuzzleObject goose = new PuzzleObject("Goose",objectCategory_bird,goose_Image_binary_content,goose_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(goose);

        PuzzleObject fox = new PuzzleObject("Fox",objectCategory_Mamals,fox_Image_binary_content,fox_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(fox);

        PuzzleObject wheat = new PuzzleObject("Wheat",objectCategory_cereal,wheat_Image_binary_content,wheat_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(wheat);

        PuzzleObject ImageObject01 = new PuzzleObject("ImageObject01",objectCategory_Image,wheat_Image_binary_content,wheat_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(ImageObject01);

        PuzzleGroup_PuzzleObject  puzzleGroup_puzzleObject = new PuzzleGroup_PuzzleObject ("Image Hash Puzzle Group with Image Object","Hash_ImageObject",puzzleGroup_1,ImageObject01,1L,now,now,admin_1,admin_1);
        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);


        StringBuffer  condition_hashImage = new StringBuffer("(img1.x == img1.targetX)&&(img1.y == img1.targetY)" +","
                + "(img2.x == img2.targetX)&&(img2.y == img2.targetY)" + ","
                + "(img3.x == img3.targetX)&&(img3.y == img3.targetY)" + ","
                + "(img4.x == img4.targetX)&&(img4.y == img4.targetY)" + ","
                + "(img5.x == img5.targetX)&&(img5.y == img5.targetY)" +  ","
                + "(img6.x == img6.targetX)&&(img6.y == img6.targetY)" +	","
                + "(img7.x == img7.targetX)&&(img7.y == img7.targetY)"+","
                + "(img8.x == img8.targetX)&&(img8.y == img8.targetY)");

        PuzzleLevelObjective puzzleLevelObjective = new PuzzleLevelObjective("arrange image pieces","arrange image pieces as correct image",10f,2f,condition_hashImage,timeManagement,alCoin10WalletItem,puzzleLevel_hashimage
                ,1L,now,now,admin_1,admin_1);
        puzzleLevelObjectiveService.save(puzzleLevelObjective);


        ALCityAttribute alCityAttribute_variable_X =new ALCityAttribute("X",puzzleLevel_hashimage.getId(), AttributeOwnerType.Puzzle_Level_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        ALCityAttribute alCityAttribute_variable_Y =new ALCityAttribute("Y",puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_variable_X);
        alCityAttributeService.save(alCityAttribute_variable_Y);
        ALCityAttributeValue alCityAttribute_variable_X_value= new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_variable_X,alCityAttribute_variable_X,1L,now,now,admin_1,admin_1);
        ALCityAttributeValue alCityAttribute_variable_Y_value= new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_variable_Y,alCityAttribute_variable_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttribute_variable_X_value);
        alCityAttributeValueService.save(alCityAttribute_variable_Y_value);

        PuzzleGroupObjectInstance instance_img0 = new PuzzleGroupObjectInstance("instance_img0",1,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img1 = new PuzzleGroupObjectInstance("instance_img1",2,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img2 = new PuzzleGroupObjectInstance("instance_img2",1,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img3 = new PuzzleGroupObjectInstance("instance_img3",2,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img4 = new PuzzleGroupObjectInstance("instance_img4",3,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img5 = new PuzzleGroupObjectInstance("instance_img5",1,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img6 = new PuzzleGroupObjectInstance("instance_img6",2,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img7 = new PuzzleGroupObjectInstance("instance_img7",3,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        PuzzleGroupObjectInstance instance_img8 = new PuzzleGroupObjectInstance("instance_img8",3,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);

        puzzleGroupObjectInstanceService.save(instance_img0);
        puzzleGroupObjectInstanceService.save(instance_img1);
        puzzleGroupObjectInstanceService.save(instance_img2);
        puzzleGroupObjectInstanceService.save(instance_img3);
        puzzleGroupObjectInstanceService.save(instance_img4);
        puzzleGroupObjectInstanceService.save(instance_img5);
        puzzleGroupObjectInstanceService.save(instance_img6);
        puzzleGroupObjectInstanceService.save(instance_img7);
        puzzleGroupObjectInstanceService.save(instance_img8);


        byte[] image_0_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","0.png");
        byte[] image_1_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","1.png");
        byte[] image_2_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","2.png");
        byte[] image_3_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","3.png");
        byte[] image_4_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","4.png");
        byte[] image_5_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","5.png");
        byte[] image_6_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","6.png");
        byte[] image_7_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","7.png");
        byte[] image_8_hash = Util.getImage("src/main/resources/images/hashImage_Puzzle/","8.png");


        BinaryContent image_0_Instance_content = new BinaryContent("img0",image_0_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_1_Instance_content = new BinaryContent("img1",image_1_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_2_Instance_content = new BinaryContent("img2",image_2_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_3_Instance_content = new BinaryContent("img3",image_3_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_4_Instance_content = new BinaryContent("img4",image_4_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_5_Instance_content = new BinaryContent("img5",image_5_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_6_Instance_content = new BinaryContent("img6",image_6_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_7_Instance_content = new BinaryContent("img7",image_7_hash,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_8_Instance_content = new BinaryContent("img8",image_8_hash,imageType,1L,now,now,admin_1,admin_1);

        binaryContentService.save(image_0_Instance_content);
        binaryContentService.save(image_1_Instance_content);
        binaryContentService.save(image_2_Instance_content);
        binaryContentService.save(image_3_Instance_content);
        binaryContentService.save(image_4_Instance_content);
        binaryContentService.save(image_5_Instance_content);
        binaryContentService.save(image_6_Instance_content);
        binaryContentService.save(image_7_Instance_content);
        binaryContentService.save(image_8_Instance_content);

        ALCityAttribute alCityAttribute_bgImage_0 =new ALCityAttribute("bgImage",instance_img0.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_0);

        ALCityAttributeValue alCityAttributeValue_binary_0= new ALCityAttributeValue(null,null,null,null,null,image_0_Instance_content,alCityAttribute_bgImage_0,alCityAttribute_bgImage_0,1L,now,now,admin_1,admin_1);

        ALCityAttribute alCityAttribute_bgImage_1 =new ALCityAttribute("bgImage",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_1);

        ALCityAttributeValue alCityAttributeValue_binary_1= new ALCityAttributeValue(null,null,null,null,null,image_1_Instance_content,alCityAttribute_bgImage_1,alCityAttribute_bgImage_1,1L,now,now,admin_1,admin_1);


        ALCityAttribute alCityAttribute_bgImage_2 =new ALCityAttribute("bgImage",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_2);

        ALCityAttributeValue alCityAttributeValue_binary_2= new ALCityAttributeValue(null,null,null,null,null,image_2_Instance_content,alCityAttribute_bgImage_2,alCityAttribute_bgImage_2,1L,now,now,admin_1,admin_1);

        ALCityAttribute alCityAttribute_bgImage_3 =new ALCityAttribute("bgImage",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_3);

        ALCityAttributeValue alCityAttributeValue_binary_3= new ALCityAttributeValue(null,null,null,null,null,image_3_Instance_content,alCityAttribute_bgImage_3,alCityAttribute_bgImage_3,1L,now,now,admin_1,admin_1);

        ALCityAttribute alCityAttribute_bgImage_4 =new ALCityAttribute("bgImage",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_4);

        ALCityAttributeValue alCityAttributeValue_binary_4= new ALCityAttributeValue(null,null,null,null,null,image_4_Instance_content,alCityAttribute_bgImage_4,alCityAttribute_bgImage_4,1L,now,now,admin_1,admin_1);


        ALCityAttribute alCityAttribute_bgImage_5 =new ALCityAttribute("bgImage",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_5);

        ALCityAttributeValue alCityAttributeValue_binary_5= new ALCityAttributeValue(null,null,null,null,null,image_5_Instance_content,alCityAttribute_bgImage_5,alCityAttribute_bgImage_5,1L,now,now,admin_1,admin_1);


        ALCityAttribute alCityAttribute_bgImage_6 =new ALCityAttribute("bgImage",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_6);
        ALCityAttributeValue alCityAttributeValue_binary_6= new ALCityAttributeValue(null,null,null,null,null,image_6_Instance_content,alCityAttribute_bgImage_6,alCityAttribute_bgImage_6,1L,now,now,admin_1,admin_1);


        ALCityAttribute alCityAttribute_bgImage_7 =new ALCityAttribute("bgImage",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_7);
        ALCityAttributeValue alCityAttributeValue_binary_7= new ALCityAttributeValue(null,null,null,null,null,image_7_Instance_content,alCityAttribute_bgImage_7,alCityAttribute_bgImage_7,1L,now,now,admin_1,admin_1);

        ALCityAttribute alCityAttribute_bgImage_8 =new ALCityAttribute("bgImage",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_bgImage_8);

        ALCityAttributeValue alCityAttributeValue_binary_8= new ALCityAttributeValue(null,null,null,null,null,image_8_Instance_content,alCityAttribute_bgImage_8,alCityAttribute_bgImage_8,1L,now,now,admin_1,admin_1);


        alCityAttributeValueService.save(alCityAttributeValue_binary_0);
        alCityAttributeValueService.save(alCityAttributeValue_binary_1);
        alCityAttributeValueService.save(alCityAttributeValue_binary_2);
        alCityAttributeValueService.save(alCityAttributeValue_binary_3);
        alCityAttributeValueService.save(alCityAttributeValue_binary_4);
        alCityAttributeValueService.save(alCityAttributeValue_binary_5);
        alCityAttributeValueService.save(alCityAttributeValue_binary_6);
        alCityAttributeValueService.save(alCityAttributeValue_binary_7);
        alCityAttributeValueService.save(alCityAttributeValue_binary_8);




        ClientType mobile = clientTypeService.findByValue("mobile");
        ObjectAction moveAction = objectActionService.findByValue("Move");

        //POActionOwnerType puzzleObjectIsOwner = puzzleObjectActionOwnerTypeService.findByValue("Puzzle Object");

        ActionRenderer moveActionRenderer = new ActionRenderer("Move",mobile,moveAction,1L,now,now,admin_1,admin_1);
        actionRendererService.save(moveActionRenderer);

        PuzzleObject_ObjectAction  ImageObject01_MoveAction = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),moveAction,moveActionRenderer,1L,now,now,admin_1,admin_1);
        puzzleObject_ObjectActionService.save(ImageObject01_MoveAction);

        ALCityAttribute alCityAttribute_move_action =new ALCityAttribute("actionId",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Long,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_action);
        ALCityAttributeValue alCity_moveAction_parameter_action_id= new ALCityAttributeValue(null,null,alCityAttribute_move_action.getId(),null,null,null,alCityAttribute_move_action,alCityAttribute_move_action,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_action_id);

        ALCityAttribute alCityAttribute_move_aSync =new ALCityAttribute("aSync",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Boolean,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_aSync);
        ALCityAttributeValue alCity_moveAction_parameter_aSync= new ALCityAttributeValue(false,null,null,null,null,null,alCityAttribute_move_aSync,alCityAttribute_move_aSync,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_aSync);

        ALCityAttribute alCityAttribute_move_formRow =new ALCityAttribute("formRow",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_formRow);
        ALCityAttributeValue alCity_moveAction_parameter_fromRow= new ALCityAttributeValue(null,0,null,null,null,null,alCityAttribute_move_formRow,alCityAttribute_move_formRow,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_fromRow);

        ALCityAttribute alCityAttribute_move_toRow =new ALCityAttribute("toRow",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_toRow);
        ALCityAttributeValue alCity_moveAction_parameter_toRow= new ALCityAttributeValue(null,0,null,null,null,null,alCityAttribute_move_toRow,alCityAttribute_move_toRow,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_toRow);

        ALCityAttribute alCityAttribute_move_fromCol =new ALCityAttribute("FromCol",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_fromCol);
        ALCityAttributeValue alCity_moveAction_parameter_fromCol= new ALCityAttributeValue(null,0,null,null,null,null,alCityAttribute_move_fromCol,alCityAttribute_move_fromCol,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_fromCol);

        ALCityAttribute alCityAttribute_move_toCol =new ALCityAttribute("toCol",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_toCol);
        ALCityAttributeValue alCity_moveAction_parameter_toCol= new ALCityAttributeValue(null,0,null,null,null,null,alCityAttribute_move_toCol,alCityAttribute_move_toCol,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_toCol);

        //this type must be clear object type
        ALCityAttribute alCityAttribute_move_ObjectId =new ALCityAttribute("ObjectId",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_Long,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_ObjectId);
        ALCityAttributeValue alCity_moveAction_parameter_ObjectId= new ALCityAttributeValue(null,null,null,null,null,null,alCityAttribute_move_ObjectId,alCityAttribute_move_ObjectId,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_ObjectId);

        //this type must be clear ...enum value for move type
        ALCityAttribute alCityAttribute_move_moveType =new ALCityAttribute("moveType",puzzleGroup_puzzleObject.getId(),AttributeOwnerType.PuzzleObject_Parameter,alcity_String,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_move_moveType);
        ALCityAttributeValue alCity_moveAction_parameter_moveType= new ALCityAttributeValue(null,null,null,"jump",null,null,alCityAttribute_move_moveType,alCityAttribute_move_moveType,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCity_moveAction_parameter_moveType);



        ALCityAttribute alCityAttribute_instance_1_targetX= new ALCityAttribute("targetX",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_1_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_1_targetX = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_1_targetX,alCityAttribute_instance_1_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_1_targetX);

        ALCityAttribute alCityAttribute_instance_1_targetY= new ALCityAttribute("targetY",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_1_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_1_targetY = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_1_targetY,alCityAttribute_instance_1_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_1_targetY);

        ALCityAttribute alCityAttribute_instance_1_X= new ALCityAttribute("X",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_1_X);
        ALCityAttributeValue alCityAttributeValue_instance_1_X = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_1_X,alCityAttribute_instance_1_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_1_X);

        ALCityAttribute alCityAttribute_instance_1_Y= new ALCityAttribute("Y",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_1_Y);
        ALCityAttributeValue alCityAttributeValue_instance_1_Y = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_1_Y,alCityAttribute_instance_1_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_1_Y);

        ALCityAttribute alCityAttribute_instance_2_targetX= new ALCityAttribute("targetX",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_2_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_2_targetX = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_2_targetX,alCityAttribute_instance_2_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_2_targetX);

        ALCityAttribute alCityAttribute_instance_2_targetY= new ALCityAttribute("targetY",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_2_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_2_targetY = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_2_targetY,alCityAttribute_instance_2_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_2_targetY);

        ALCityAttribute alCityAttribute_instance_2_X= new ALCityAttribute("X",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_2_X);
        ALCityAttributeValue alCityAttributeValue_instance_2_X = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_2_X,alCityAttribute_instance_2_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_2_X);

        ALCityAttribute alCityAttribute_instance_2_Y= new ALCityAttribute("Y",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_2_Y);
        ALCityAttributeValue alCityAttributeValue_instance_2_Y = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_2_Y,alCityAttribute_instance_2_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_2_Y);


        ALCityAttribute alCityAttribute_instance_3_targetX= new ALCityAttribute("targetX",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_3_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_3_targetX = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_3_targetX,alCityAttribute_instance_3_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_3_targetX);

        ALCityAttribute alCityAttribute_instance_3_targetY= new ALCityAttribute("targetY",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_3_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_3_targetY = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_3_targetY,alCityAttribute_instance_3_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_3_targetY);

        ALCityAttribute alCityAttribute_instance_3_X= new ALCityAttribute("X",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_3_X);
        ALCityAttributeValue alCityAttributeValue_instance_3_X = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_3_X,alCityAttribute_instance_3_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_3_X);

        ALCityAttribute alCityAttribute_instance_3_Y= new ALCityAttribute("Y",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_3_Y);
        ALCityAttributeValue alCityAttributeValue_instance_3_Y = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_3_Y,alCityAttribute_instance_3_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_3_Y);


        ALCityAttribute alCityAttribute_instance_4_targetX= new ALCityAttribute("targetX",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_4_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_4_targetX = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_4_targetX,alCityAttribute_instance_4_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_4_targetX);

        ALCityAttribute alCityAttribute_instance_4_targetY= new ALCityAttribute("targetY",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_4_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_4_targetY = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_4_targetY,alCityAttribute_instance_4_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_4_targetY);

        ALCityAttribute alCityAttribute_instance_4_X= new ALCityAttribute("X",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_4_X);
        ALCityAttributeValue alCityAttributeValue_instance_4_X = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_4_X,alCityAttribute_instance_4_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_4_X);

        ALCityAttribute alCityAttribute_instance_4_Y= new ALCityAttribute("Y",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_4_Y);
        ALCityAttributeValue alCityAttributeValue_instance_4_Y = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_4_Y,alCityAttribute_instance_4_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_4_Y);

        ALCityAttribute alCityAttribute_instance_5_targetX= new ALCityAttribute("targetX",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_5_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_5_targetX = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_5_targetX,alCityAttribute_instance_5_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_5_targetX);

        ALCityAttribute alCityAttribute_instance_5_targetY= new ALCityAttribute("targetY",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_5_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_5_targetY = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_5_targetY,alCityAttribute_instance_5_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_5_targetY);

        ALCityAttribute alCityAttribute_instance_5_X= new ALCityAttribute("X",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_5_X);
        ALCityAttributeValue alCityAttributeValue_instance_5_X = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_5_X,alCityAttribute_instance_5_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_5_X);

        ALCityAttribute alCityAttribute_instance_5_Y= new ALCityAttribute("Y",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_5_Y);
        ALCityAttributeValue alCityAttributeValue_instance_5_Y = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_5_Y,alCityAttribute_instance_5_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_5_Y);

        ALCityAttribute alCityAttribute_instance_6_targetX= new ALCityAttribute("targetX",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_6_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_6_targetX = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_targetX,alCityAttribute_instance_6_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_6_targetX);

        ALCityAttribute alCityAttribute_instance_6_targetY= new ALCityAttribute("targetY",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_6_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_6_targetY = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_6_targetY,alCityAttribute_instance_6_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_6_targetY);

        ALCityAttribute alCityAttribute_instance_6_X= new ALCityAttribute("X",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_6_X);
        ALCityAttributeValue alCityAttributeValue_instance_6_X = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_X,alCityAttribute_instance_6_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_6_X);

        ALCityAttribute alCityAttribute_instance_6_Y= new ALCityAttribute("Y",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_6_Y);
        ALCityAttributeValue alCityAttributeValue_instance_6_Y = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_Y,alCityAttribute_instance_6_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_6_Y);

        ALCityAttribute alCityAttribute_instance_7_targetX= new ALCityAttribute("targetX",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_7_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_7_targetX = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_targetX,alCityAttribute_instance_7_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_7_targetX);

        ALCityAttribute alCityAttribute_instance_7_targetY= new ALCityAttribute("targetY",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_7_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_7_targetY = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_7_targetY,alCityAttribute_instance_7_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_7_targetY);

        ALCityAttribute alCityAttribute_instance_7_X= new ALCityAttribute("X",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_7_X);
        ALCityAttributeValue alCityAttributeValue_instance_7_X = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_X,alCityAttribute_instance_7_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_7_X);

        ALCityAttribute alCityAttribute_instance_7_Y= new ALCityAttribute("Y",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_7_Y);
        ALCityAttributeValue alCityAttributeValue_instance_7_Y = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_Y,alCityAttribute_instance_7_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_7_Y);

        ALCityAttribute alCityAttribute_instance_8_targetX= new ALCityAttribute("targetX",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_8_targetX);
        ALCityAttributeValue alCityAttributeValue_instance_8_targetX = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_8_targetX,alCityAttribute_instance_8_targetX,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_8_targetX);

        ALCityAttribute alCityAttribute_instance_8_targetY= new ALCityAttribute("targetY",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_8_targetY);
        ALCityAttributeValue alCityAttributeValue_instance_8_targetY = new ALCityAttributeValue(null,2,null,null,null,null,alCityAttribute_instance_8_targetY,alCityAttribute_instance_8_targetY,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_8_targetY);

        ALCityAttribute alCityAttribute_instance_8_X= new ALCityAttribute("X",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_8_X);
        ALCityAttributeValue alCityAttributeValue_instance_8_X = new ALCityAttributeValue(null,3,null,null,null,null,alCityAttribute_instance_8_X,alCityAttribute_instance_8_X,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_8_X);

        ALCityAttribute alCityAttribute_instance_8_Y= new ALCityAttribute("Y",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_ObjectInstance_Variable,alcity_Int,1L,now,now,admin_1,admin_1);
        alCityAttributeService.save(alCityAttribute_instance_8_Y);
        ALCityAttributeValue alCityAttributeValue_instance_8_Y = new ALCityAttributeValue(null,1,null,null,null,null,alCityAttribute_instance_8_Y,alCityAttribute_instance_8_Y,1L,now,now,admin_1,admin_1);
        alCityAttributeValueService.save(alCityAttributeValue_instance_8_Y);


        PuzzleLevelRuleEventType userEvent = puzzleLevelRuleEventTypeService.findByValue("User Event");
        UserEvent clickEvent = userEventService.findByValue("Click");

        PuzzleLevelRule rule_for_move_objects_in_hash_image = new PuzzleLevelRule("Move object by click around empty object",1
                ,"((e.x==X)&&((e.y==Y-1)||(e.y==Y+1)))  ||  ((e.y==Y)&&((e.x==X-1)||(e.x==X+1)))",puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_move_objects_in_hash_image);

        PuzzleLevelRuleEvent puzzleLevelRuleEvent_click = new PuzzleLevelRuleEvent("Click",userEvent,clickEvent.getId(),rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
        puzzleLevelRuleEventService.save(puzzleLevelRuleEvent_click);
        StringBuffer moveActionExpression=new StringBuffer(" objects[e.x][e.y] , move , parameters:[" +
                "{name:'actionId  IN UNITY' , value:'move action id'}," +
                "{name:'aSync' , value:'false'}," +
                "{name:'formRow' , value:'e.x'}," +
                "{name:'fromCol' , value:'e.y'}," +
                "{name:'toRow' , value:'x'}," +
                "{name:'toCol' , value:'y'}," +
                "{name:'ObjectId' , value:'objects[e.x][e.y]'}," +
                "{name:'moveType' , value:'jump'}] ");

        PuzzleLevelRulePostActionType callObjectAction=puzzleLevelRulePostActionTypeService.findByValue("CallObjectAction");

        PuzzleLevelRulePostAction rulePostAction_1_move = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,1,moveActionExpression,callObjectAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_1_move);

        StringBuffer moveActionExpression_2=new StringBuffer(" objects[e.x][e.y] , move , parameters:[" +
                "{name:'actionId' , value:'move action id'}," +
                "{name:'aSync' , value:'false'}," +
                "{name:'formRow' , value:'e.x'}," +
                "{name:'fromCol' , value:'e.y'}," +
                "{name:'toRow' , value:'x'}," +
                "{name:'toCol' , value:'y'}," +
                "{name:'ObjectId' , value:'objects[e.x][e.y]'}," +
                "{name:'moveType' , value:'jump'}] ");


        PuzzleLevelRulePostAction rulePostAction_2_move = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,2,moveActionExpression_2,callObjectAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_2_move);

        StringBuffer assignActionExpression_3=new StringBuffer(" parameters:[" +
                "{variable:'objects[X][Y].x'}," +
                "{valueExperssion:'x'}" +
                "]") ;

        PuzzleLevelRulePostActionType variableAssignmentAction=puzzleLevelRulePostActionTypeService.findByValue("VariableAssignmentAction");
        PuzzleLevelRulePostAction rulePostAction_3_assignment = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,3,assignActionExpression_3,variableAssignmentAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_3_assignment);

        StringBuffer assignActionExpression_4=new StringBuffer(" parameters:[" +
                "{variable:'objects[X][Y].x'}," +
                "{valueExperssion:'Y'}" +
                "]") ;

        PuzzleLevelRulePostAction rulePostAction_4_assignment = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,4,assignActionExpression_4,variableAssignmentAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_4_assignment);

        StringBuffer assignActionExpression_5=new StringBuffer(" parameters:[" +
                "{variable:'X'}," +
                "{valueExperssion:'e.x'}" +
                "]") ;

        PuzzleLevelRulePostAction rulePostAction_5_assignment = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,5,assignActionExpression_5,variableAssignmentAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_5_assignment);

        StringBuffer assignActionExpression_6=new StringBuffer(" parameters:[" +
                "{variable:'Y'}," +
                "{valueExperssion:'e.y'}" +
                "]") ;

        PuzzleLevelRulePostAction rulePostAction_6_assignment = new PuzzleLevelRulePostAction(rule_for_move_objects_in_hash_image,6,assignActionExpression_6,variableAssignmentAction,1L,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_6_assignment);

    }





}
