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
import com.alcity.entity.users.AppMember;
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


@Order(value=2)
@Component
public class ImportHashImageProblemData_1 implements CommandLineRunner {

    @Autowired
    private ApplicationMemberService applicationMemberService;

    @Autowired
    private BinaryContentService binaryContentService;

    @Autowired
    ALCityObjectService alCityObjectService;

    @Autowired
    PLGroundService puzzleLevelGroundService;
    @Autowired
    PuzzleLevelService puzzleLevelService;

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    private PGService puzzleGroupService;


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
    ALCityInstanceInPLService pgObjectInstanceService;


    @Autowired
    AttributeValueService attributeValueService;



    @Autowired
    private CameraSetupService cameraSetupService;


    @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    RendererService actionRendererService;


    @Autowired
    PuzzleObjectActionService puzzleObject_ObjectActionService;

    @Autowired
    ALCityObjectInPGService alCityObjectInPGService;

    @Autowired
    PLRuleService puzzleLevelRuleService;

    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    PLRuleEventService plRuleEventService;

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
        log.info("Start Application...Import Problem 1");
        System.out.println("...Import Problem 1");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);
        // byte[] avatar = getImage("src/main/resources/images/","avatar.png");

        AppMember admin_1 = applicationMemberService.findByUsername("admin");
        AppMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        Optional<LearningTopic> hashImage_Topic = learningTopicService.findByTitle("Hash Image");

         LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill division = learningSkillService.findByValue("division");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

        Journey journey_1 = journeyService.findByTitle("Journey_1");
        Journey journey_2 = journeyService.findByTitle("Journey_2");



        byte[] puzzle_Ground_Image_1 = ImageUtil.getImage("src/main/resources/images/","playGround.png");
        BinaryContent puzzle_ground_binary_content_1 = new BinaryContent("puzzle ground for hash image",puzzle_Ground_Image_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_ground_binary_content_1);

        byte[] hashImage_icon_byte = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","hashImage_icon.png");
        BinaryContent hashImage_icon = new BinaryContent("hash image icon",puzzle_Ground_Image_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(hashImage_icon);

        byte[] hashImage_pic_byte = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","hashImage_pic.png");
        BinaryContent hashImage_pic = new BinaryContent("hash image picture",puzzle_Ground_Image_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(hashImage_pic);

        PuzzleCategory mathematic = puzzleCategoryService.findByValue("mathematic");

        Optional<PuzzleGroup> puzzleGroup_HashImage = puzzleGroupService.findByTitle("Hash Image - Puzzle Group 1");
        JourneyStep step_1_journey_1 = new JourneyStep("step1_journey_1",1,30,30,journey_1,puzzleGroup_HashImage.get(),1L,now,now,admin_1,admin_1);
        JourneyStep step_2_journey_1 = new JourneyStep("step2_journey_1",1,30,30,journey_1,puzzleGroup_HashImage.get(),1L,now,now,admin_1,admin_1);
        JourneyStep step_1_journey_2 = new JourneyStep("step1_journey_2",1,30,30,journey_2,puzzleGroup_HashImage.get(),1L,now,now,admin_1,admin_1);
        journeyStepService.save(step_1_journey_1);
        journeyStepService.save(step_2_journey_1);
        journeyStepService.save(step_1_journey_2);


        PLPrivacy privacy_1 = plPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_hashimage = new PuzzleLevel(now,1L,"hashed image with a empty cell","4545",10,14,5f,puzzleGroup_HashImage.get(),PLDifficulty.Easy,PLStatus.Ongoing,privacy_1,3L,now,now,admin_1,admin_1);
        puzzleLevel_hashimage.setIcon(hashImage_icon);
        puzzleLevel_hashimage.setPicture(hashImage_pic);
        puzzleLevelService.save(puzzleLevel_hashimage);


        byte[] puzzle_group_Hash_Image = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle","hashImage_pic.png");
        BinaryContent puzzle_group_Hash_Image_binary_content = new BinaryContent("hashImage",puzzle_group_Hash_Image,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_Hash_Image_binary_content);

        Integer xPos=3;
        Integer xRotation=3;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);


        PLGround puzzleLevel_hashImage_ground = new PLGround(3,3,puzzleLevel_hashimage,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleLevel_hashImage_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_hashImage_ground);

        PermitedPlayer player_1_puzzleLevel_hashimage = new PermitedPlayer(jalalHoseyni,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_hashimage);

        LearningContent learningContent_Division=new LearningContent("help to math","this content is about hash images",puzzle_group_Hash_Image_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Division);

        LearningSubjectInPL puzzleLevelLearningTopic_1 = new LearningSubjectInPL(puzzleLevel_hashimage,hashImage_Topic.get(),learningContent_Division,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_hashimage,now,100,10f,1L,now,now,jalalHoseyni,jalalHoseyni);
        playHistoryService.save(playHistory_1);

        LearningSkillContent puzzleSkillLearningContent_1 = new LearningSkillContent(division,puzzleGroup_HashImage.get(),learningContent_Division,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_hashimage,GameStatus.gameStatus_1,1L,now,now,jalalHoseyni,jalalHoseyni);
        plGameInstanceService.save(puzzleLevelGameInstance);

        Optional<ALCityObject> ImageObject01 =alCityObjectService.findByTitle("ImageObject01");

        ALCityObjectInPG ImageObject01_in_haseImage_puzzleGroup = new ALCityObjectInPG("Image Hash Puzzle Group with Image Object","Hash_ImageObject",puzzleGroup_HashImage.get(),ImageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(ImageObject01_in_haseImage_puzzleGroup);

        //change actions for ImageObject01_in_haseImage_puzzleGroup
       // alCityObjectInPGService.copyActionTo(ImageObject01_in_haseImage_puzzleGroup);

        StringBuffer  condition_Objective = new StringBuffer("(img1.x == img1.targetX)&&(img1.y == img1.targetY)" +","
                + "(img2.x == img2.targetX)&&(img2.y == img2.targetY)" + ","
                + "(img3.x == img3.targetX)&&(img3.y == img3.targetY)" + ","
                + "(img4.x == img4.targetX)&&(img4.y == img4.targetY)" + ","
                + "(img5.x == img5.targetX)&&(img5.y == img5.targetY)" +  ","
                + "(img6.x == img6.targetX)&&(img6.y == img6.targetY)" +	","
                + "(img7.x == img7.targetX)&&(img7.y == img7.targetY)"+","
                + "(img8.x == img8.targetX)&&(img8.y == img8.targetY)");

        PLObjective puzzleLevelObjective = new PLObjective("arrange image pieces","arrange image pieces as correct image",10f,2f,condition_Objective,timeManagement,alCoin10WalletItem.get(),puzzleLevel_hashimage
                ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective);


        Attribute alCityAttribute_variable_X =new Attribute("X",puzzleLevel_hashimage.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        Attribute alCityAttribute_variable_Y =new Attribute("Y",puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_variable_X);
        attributeService.save(alCityAttribute_variable_Y);
        AttributeValue alCityAttribute_variable_X_value= new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_variable_X,alCityAttribute_variable_X,1L,now,now,admin_1,admin_1);
        AttributeValue alCityAttribute_variable_Y_value= new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_variable_Y,alCityAttribute_variable_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttribute_variable_X_value);
        attributeValueService.save(alCityAttribute_variable_Y_value);

        ALCityInstanceInPL instance_img0 = new ALCityInstanceInPL("instance_img0",1,1,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img1 = new ALCityInstanceInPL("instance_img1",2,3,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img2 = new ALCityInstanceInPL("instance_img2",1,2,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img3 = new ALCityInstanceInPL("instance_img3",2,1,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img4 = new ALCityInstanceInPL("instance_img4",3,2,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img5 = new ALCityInstanceInPL("instance_img5",1,3,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img6 = new ALCityInstanceInPL("instance_img6",2,2,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img7 = new ALCityInstanceInPL("instance_img7",3,3,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        ALCityInstanceInPL instance_img8 = new ALCityInstanceInPL("instance_img8",3,1,1,ImageObject01_in_haseImage_puzzleGroup,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);

        pgObjectInstanceService.save(instance_img0);
        pgObjectInstanceService.save(instance_img1);
        pgObjectInstanceService.save(instance_img2);
        pgObjectInstanceService.save(instance_img3);
        pgObjectInstanceService.save(instance_img4);
        pgObjectInstanceService.save(instance_img5);
        pgObjectInstanceService.save(instance_img6);
        pgObjectInstanceService.save(instance_img7);
        pgObjectInstanceService.save(instance_img8);


        byte[] image_0_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","0.png");
        byte[] image_1_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","1.png");
        byte[] image_2_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","2.png");
        byte[] image_3_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","3.png");
        byte[] image_4_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","4.png");
        byte[] image_5_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","5.png");
        byte[] image_6_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","6.png");
        byte[] image_7_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","7.png");
        byte[] image_8_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","8.png");


        BinaryContent image_0_Instance_content = new BinaryContent("img0",image_0_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_1_Instance_content = new BinaryContent("img1",image_1_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_2_Instance_content = new BinaryContent("img2",image_2_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_3_Instance_content = new BinaryContent("img3",image_3_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_4_Instance_content = new BinaryContent("img4",image_4_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_5_Instance_content = new BinaryContent("img5",image_5_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_6_Instance_content = new BinaryContent("img6",image_6_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_7_Instance_content = new BinaryContent("img7",image_7_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        BinaryContent image_8_Instance_content = new BinaryContent("img8",image_8_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);

        binaryContentService.save(image_0_Instance_content);
        binaryContentService.save(image_1_Instance_content);
        binaryContentService.save(image_2_Instance_content);
        binaryContentService.save(image_3_Instance_content);
        binaryContentService.save(image_4_Instance_content);
        binaryContentService.save(image_5_Instance_content);
        binaryContentService.save(image_6_Instance_content);
        binaryContentService.save(image_7_Instance_content);
        binaryContentService.save(image_8_Instance_content);

        Attribute alCityAttribute_bgImage_0 =new Attribute("bgImage",instance_img0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_0);

        AttributeValue alCityAttributeValue_binary_0= new AttributeValue(null,null,image_0_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_0,alCityAttribute_bgImage_0,1L,now,now,admin_1,admin_1);

        Attribute alCityAttribute_bgImage_1 =new Attribute("bgImage",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_1);

        AttributeValue alCityAttributeValue_binary_1= new AttributeValue(null,null,image_1_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_1,alCityAttribute_bgImage_1,1L,now,now,admin_1,admin_1);


        Attribute alCityAttribute_bgImage_2 =new Attribute("bgImage",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_2);

        AttributeValue alCityAttributeValue_binary_2= new AttributeValue(null,null,image_2_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_2,alCityAttribute_bgImage_2,1L,now,now,admin_1,admin_1);

        Attribute alCityAttribute_bgImage_3 =new Attribute("bgImage",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_3);

        AttributeValue alCityAttributeValue_binary_3= new AttributeValue(null,null,image_3_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_3,alCityAttribute_bgImage_3,1L,now,now,admin_1,admin_1);

        Attribute alCityAttribute_bgImage_4 =new Attribute("bgImage",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_4);

        AttributeValue alCityAttributeValue_binary_4= new AttributeValue(null,null,image_4_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_4,alCityAttribute_bgImage_4,1L,now,now,admin_1,admin_1);


        Attribute alCityAttribute_bgImage_5 =new Attribute("bgImage",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_5);

        AttributeValue alCityAttributeValue_binary_5= new AttributeValue(null,null,image_5_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_5,alCityAttribute_bgImage_5,1L,now,now,admin_1,admin_1);


        Attribute alCityAttribute_bgImage_6 =new Attribute("bgImage",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_6);
        AttributeValue alCityAttributeValue_binary_6= new AttributeValue(null,null,image_6_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_6,alCityAttribute_bgImage_6,1L,now,now,admin_1,admin_1);


        Attribute alCityAttribute_bgImage_7 =new Attribute("bgImage",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_7);
        AttributeValue alCityAttributeValue_binary_7= new AttributeValue(null,null,image_7_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_7,alCityAttribute_bgImage_7,1L,now,now,admin_1,admin_1);

        Attribute alCityAttribute_bgImage_8 =new Attribute("bgImage",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_8);

        AttributeValue alCityAttributeValue_binary_8= new AttributeValue(null,null,image_8_Instance_content.getId(),null,null,null,null,alCityAttribute_bgImage_8,alCityAttribute_bgImage_8,1L,now,now,admin_1,admin_1);


        attributeValueService.save(alCityAttributeValue_binary_0);
        attributeValueService.save(alCityAttributeValue_binary_1);
        attributeValueService.save(alCityAttributeValue_binary_2);
        attributeValueService.save(alCityAttributeValue_binary_3);
        attributeValueService.save(alCityAttributeValue_binary_4);
        attributeValueService.save(alCityAttributeValue_binary_5);
        attributeValueService.save(alCityAttributeValue_binary_6);
        attributeValueService.save(alCityAttributeValue_binary_7);
        attributeValueService.save(alCityAttributeValue_binary_8);












        Attribute alCityAttribute_instance_1_targetX= new Attribute("targetX",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_1_targetX);
        AttributeValue alCityAttributeValue_instance_1_targetX = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_1_targetX,alCityAttribute_instance_1_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_1_targetX);

        Attribute alCityAttribute_instance_1_targetY= new Attribute("targetY",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_1_targetY);
        AttributeValue alCityAttributeValue_instance_1_targetY = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_1_targetY,alCityAttribute_instance_1_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_1_targetY);

        Attribute alCityAttribute_instance_1_X= new Attribute("X",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_1_X);
        AttributeValue alCityAttributeValue_instance_1_X = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_1_X,alCityAttribute_instance_1_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_1_X);

        Attribute alCityAttribute_instance_1_Y= new Attribute("Y",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_1_Y);
        AttributeValue alCityAttributeValue_instance_1_Y = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_1_Y,alCityAttribute_instance_1_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_1_Y);

        Attribute alCityAttribute_instance_2_targetX= new Attribute("targetX",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_2_targetX);
        AttributeValue alCityAttributeValue_instance_2_targetX = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_2_targetX,alCityAttribute_instance_2_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_2_targetX);

        Attribute alCityAttribute_instance_2_targetY= new Attribute("targetY",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_2_targetY);
        AttributeValue alCityAttributeValue_instance_2_targetY = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_2_targetY,alCityAttribute_instance_2_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_2_targetY);

        Attribute alCityAttribute_instance_2_X= new Attribute("X",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_2_X);
        AttributeValue alCityAttributeValue_instance_2_X = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_2_X,alCityAttribute_instance_2_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_2_X);

        Attribute alCityAttribute_instance_2_Y= new Attribute("Y",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_2_Y);
        AttributeValue alCityAttributeValue_instance_2_Y = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_2_Y,alCityAttribute_instance_2_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_2_Y);


        Attribute alCityAttribute_instance_3_targetX= new Attribute("targetX",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_3_targetX);
        AttributeValue alCityAttributeValue_instance_3_targetX = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_3_targetX,alCityAttribute_instance_3_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_3_targetX);

        Attribute alCityAttribute_instance_3_targetY= new Attribute("targetY",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_3_targetY);
        AttributeValue alCityAttributeValue_instance_3_targetY = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_3_targetY,alCityAttribute_instance_3_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_3_targetY);

        Attribute alCityAttribute_instance_3_X= new Attribute("X",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_3_X);
        AttributeValue alCityAttributeValue_instance_3_X = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_3_X,alCityAttribute_instance_3_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_3_X);

        Attribute alCityAttribute_instance_3_Y= new Attribute("Y",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_3_Y);
        AttributeValue alCityAttributeValue_instance_3_Y = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_3_Y,alCityAttribute_instance_3_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_3_Y);


        Attribute alCityAttribute_instance_4_targetX= new Attribute("targetX",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_4_targetX);
        AttributeValue alCityAttributeValue_instance_4_targetX = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_4_targetX,alCityAttribute_instance_4_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_4_targetX);

        Attribute alCityAttribute_instance_4_targetY= new Attribute("targetY",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_4_targetY);
        AttributeValue alCityAttributeValue_instance_4_targetY = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_4_targetY,alCityAttribute_instance_4_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_4_targetY);

        Attribute alCityAttribute_instance_4_X= new Attribute("X",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_4_X);
        AttributeValue alCityAttributeValue_instance_4_X = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_4_X,alCityAttribute_instance_4_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_4_X);

        Attribute alCityAttribute_instance_4_Y= new Attribute("Y",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_4_Y);
        AttributeValue alCityAttributeValue_instance_4_Y = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_4_Y,alCityAttribute_instance_4_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_4_Y);

        Attribute alCityAttribute_instance_5_targetX= new Attribute("targetX",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_5_targetX);
        AttributeValue alCityAttributeValue_instance_5_targetX = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_5_targetX,alCityAttribute_instance_5_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_5_targetX);

        Attribute alCityAttribute_instance_5_targetY= new Attribute("targetY",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_5_targetY);
        AttributeValue alCityAttributeValue_instance_5_targetY = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_5_targetY,alCityAttribute_instance_5_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_5_targetY);

        Attribute alCityAttribute_instance_5_X= new Attribute("X",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_5_X);
        AttributeValue alCityAttributeValue_instance_5_X = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_5_X,alCityAttribute_instance_5_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_5_X);

        Attribute alCityAttribute_instance_5_Y= new Attribute("Y",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_5_Y);
        AttributeValue alCityAttributeValue_instance_5_Y = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_5_Y,alCityAttribute_instance_5_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_5_Y);

        Attribute alCityAttribute_instance_6_targetX= new Attribute("targetX",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_6_targetX);
        AttributeValue alCityAttributeValue_instance_6_targetX = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_6_targetX,alCityAttribute_instance_6_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_6_targetX);

        Attribute alCityAttribute_instance_6_targetY= new Attribute("targetY",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_6_targetY);
        AttributeValue alCityAttributeValue_instance_6_targetY = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_6_targetY,alCityAttribute_instance_6_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_6_targetY);

        Attribute alCityAttribute_instance_6_X= new Attribute("X",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_6_X);
        AttributeValue alCityAttributeValue_instance_6_X = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_6_X,alCityAttribute_instance_6_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_6_X);

        Attribute alCityAttribute_instance_6_Y= new Attribute("Y",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_6_Y);
        AttributeValue alCityAttributeValue_instance_6_Y = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_6_Y,alCityAttribute_instance_6_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_6_Y);

        Attribute alCityAttribute_instance_7_targetX= new Attribute("targetX",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_7_targetX);
        AttributeValue alCityAttributeValue_instance_7_targetX = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_7_targetX,alCityAttribute_instance_7_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_7_targetX);

        Attribute alCityAttribute_instance_7_targetY= new Attribute("targetY",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_7_targetY);
        AttributeValue alCityAttributeValue_instance_7_targetY = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_7_targetY,alCityAttribute_instance_7_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_7_targetY);

        Attribute alCityAttribute_instance_7_X= new Attribute("X",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_7_X);
        AttributeValue alCityAttributeValue_instance_7_X = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_7_X,alCityAttribute_instance_7_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_7_X);

        Attribute alCityAttribute_instance_7_Y= new Attribute("Y",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_7_Y);
        AttributeValue alCityAttributeValue_instance_7_Y = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_7_Y,alCityAttribute_instance_7_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_7_Y);

        Attribute alCityAttribute_instance_8_targetX= new Attribute("targetX",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_8_targetX);
        AttributeValue alCityAttributeValue_instance_8_targetX = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_8_targetX,alCityAttribute_instance_8_targetX,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_8_targetX);

        Attribute alCityAttribute_instance_8_targetY= new Attribute("targetY",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_8_targetY);
        AttributeValue alCityAttributeValue_instance_8_targetY = new AttributeValue(null,2,null,null,null,null,null,alCityAttribute_instance_8_targetY,alCityAttribute_instance_8_targetY,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_8_targetY);

        Attribute alCityAttribute_instance_8_X= new Attribute("X",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_8_X);
        AttributeValue alCityAttributeValue_instance_8_X = new AttributeValue(null,3,null,null,null,null,null,alCityAttribute_instance_8_X,alCityAttribute_instance_8_X,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_8_X);

        Attribute alCityAttribute_instance_8_Y= new Attribute("Y",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_instance_8_Y);
        AttributeValue alCityAttributeValue_instance_8_Y = new AttributeValue(null,1,null,null,null,null,null,alCityAttribute_instance_8_Y,alCityAttribute_instance_8_Y,1L,now,now,admin_1,admin_1);
        attributeValueService.save(alCityAttributeValue_instance_8_Y);

        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");


        StringBuffer    move_rule_condition = new StringBuffer("((e.x==X)&&((e.y==Y-1)||(e.y==Y+1)))  ||  ((e.y==Y)&&((e.x==X-1)||(e.x==X+1)))");
        PLRule rule_for_move = new PLRule("Move object by click around empty object",1
                ,move_rule_condition,puzzleLevel_hashimage,click_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_move);


        PLRulePostAction move_rule_post_Action_1 = new PLRulePostAction(rule_for_move,PLRulePostActionType.Call_Object_Action,1,"move","objects[e.x][e.y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_1);

        Attribute rulePostAction_1_move_param_1 =new Attribute("actionId",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_1);

        Long actionId_in_Unity=0L;
        AttributeValue rulePostAction_1_move_param_1_value= new AttributeValue(null,null,actionId_in_Unity,null,null,null,null,rulePostAction_1_move_param_1,rulePostAction_1_move_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_1_value);

        Attribute rulePostAction_1_move_param_2 =new Attribute("aSync",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_2);

        AttributeValue rulePostAction_1_move_param_2_value= new AttributeValue(false,null,null,null,null,null,null,rulePostAction_1_move_param_2,rulePostAction_1_move_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_2_value);

        Attribute rulePostAction_1_move_param_3 =new Attribute("FormRow",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_3);

        AttributeValue rulePostAction_1_move_param_3_value= new AttributeValue(null,null,null,"e.x",null,null,null,rulePostAction_1_move_param_3,rulePostAction_1_move_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_3_value);


        Attribute rulePostAction_1_move_param_4 =new Attribute("FromCol",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_4);

        AttributeValue rulePostAction_1_move_param_4_value= new AttributeValue(null,null,null,"e.y",null,null,null,rulePostAction_1_move_param_4,rulePostAction_1_move_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_4_value);

        Attribute rulePostAction_1_move_param_5 =new Attribute("toRow",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_5);

        AttributeValue rulePostAction_1_move_param_5_value= new AttributeValue(null,null,null,"X",null,null,null,rulePostAction_1_move_param_5,rulePostAction_1_move_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_5_value);

        Attribute rulePostAction_1_move_param_6 =new Attribute("toCol",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_6);

        AttributeValue rulePostAction_1_move_param_6_value= new AttributeValue(null,null,null,"Y",null,null,null,rulePostAction_1_move_param_6,rulePostAction_1_move_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_6_value);

        Attribute rulePostAction_1_move_param_7 =new Attribute("ObjectId",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_7);

        AttributeValue rulePostAction_1_move_param_7_value= new AttributeValue(null,null,null,"objects[e.x][e.y]",null,null,null,rulePostAction_1_move_param_7,rulePostAction_1_move_param_7,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_7_value);

        Attribute rulePostAction_1_move_param_8 =new Attribute("moveType",move_rule_post_Action_1.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_1_move_param_8);

        AttributeValue rulePostAction_1_move_param_8_value= new AttributeValue(null,null,null,"Jump",null,null,null,rulePostAction_1_move_param_8,rulePostAction_1_move_param_8,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_1_move_param_8_value);

        PLRulePostAction move_rule_post_Action_2 =  new PLRulePostAction(rule_for_move,PLRulePostActionType.Call_Object_Action,2,"move","objects[X][Y]",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(move_rule_post_Action_2);

        Attribute rulePostAction_2_move_param_1 =new Attribute("actionId",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_1);

        actionId_in_Unity=0L;
        AttributeValue rulePostAction_2_move_param_1_value= new AttributeValue(null,null,actionId_in_Unity,null,null,null,null,rulePostAction_2_move_param_1,rulePostAction_2_move_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_1_value);

        Attribute rulePostAction_2_move_param_2 =new Attribute("aSync",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_2);

        AttributeValue rulePostAction_2_move_param_2_value= new AttributeValue(false,null,null,null,null,null,null,rulePostAction_2_move_param_2,rulePostAction_2_move_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_2_value);

        Attribute rulePostAction_2_move_param_3 =new Attribute("FormRow",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_3);

        AttributeValue rulePostAction_2_move_param_3_value= new AttributeValue(null,null,null,"x",null,null,null,rulePostAction_2_move_param_3,rulePostAction_2_move_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_3_value);

        Attribute rulePostAction_2_move_param_4 =new Attribute("FromCol",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_4);

        AttributeValue rulePostAction_2_move_param_4_value= new AttributeValue(null,null,null,"y",null,null,null,rulePostAction_2_move_param_4,rulePostAction_2_move_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_4_value);

        Attribute rulePostAction_2_move_param_5 =new Attribute("toRow",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_5);

        AttributeValue rulePostAction_2_move_param_5_value= new AttributeValue(null,null,null,"e.x",null,null,null,rulePostAction_2_move_param_5,rulePostAction_2_move_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_5_value);

        Attribute rulePostAction_2_move_param_6 =new Attribute("toCol",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_6);

        AttributeValue rulePostAction_2_move_param_6_value= new AttributeValue(null,null,null,"e.y",null,null,null,rulePostAction_2_move_param_6,rulePostAction_2_move_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_6_value);

        Attribute rulePostAction_2_move_param_7 =new Attribute("ObjectId",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_7);

        AttributeValue rulePostAction_2_move_param_7_value= new AttributeValue(null,null,null,"objects[X][Y]",null,null,null,rulePostAction_2_move_param_7,rulePostAction_2_move_param_7,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_7_value);

        Attribute rulePostAction_2_move_param_8 =new Attribute("moveType",move_rule_post_Action_2.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_2_move_param_8);

        AttributeValue rulePostAction_2_move_param_8_value= new AttributeValue(null,null,null,"Jump",null,null,null,rulePostAction_2_move_param_8,rulePostAction_2_move_param_8,1L,now,now,admin_1,admin_1);
        attributeValueService.save(rulePostAction_2_move_param_8_value);

        PLRulePostAction rulePostAction_3_assignment = new PLRulePostAction(rule_for_move,PLRulePostActionType.Variable_Assignment_Action,3,"","",
                "objects[X][Y].x",new StringBuffer("X"),"","",1L ,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_3_assignment);


        PLRulePostAction rulePostAction_4_assignment = new PLRulePostAction(rule_for_move,PLRulePostActionType.Variable_Assignment_Action,4,"","",
                "objects[X][Y].y",new StringBuffer("Y"),"","",1L ,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_4_assignment);

        PLRulePostAction rulePostAction_5_assignment = new PLRulePostAction(rule_for_move,PLRulePostActionType.Variable_Assignment_Action,5,"","",
                "X",new StringBuffer("e.x"),"","",1L ,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_5_assignment);

        PLRulePostAction rulePostAction_6_assignment =  new PLRulePostAction(rule_for_move,PLRulePostActionType.Variable_Assignment_Action,6,"","",
                "Y",new StringBuffer("e.y"),"","",1L ,now,now,admin_1,admin_1);
        puzzleLevelRulePostActionService.save(rulePostAction_6_assignment);


    }





}
