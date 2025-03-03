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
import java.util.Optional;


@Order(value=2)
@Component
public class ImportHashImageProblemData_1 implements CommandLineRunner {

    @Autowired
    private AppMemberService applicationMemberService;

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
    ActionService puzzleObject_ObjectActionService;

    @Autowired
    ALCityObjectInPGService alCityObjectInPGService;

    @Autowired
    PLRuleService puzzleLevelRuleService;

    @Autowired
    PLRulePostActionService plRulePostActionService;

    @Autowired
    PLRuleEventService plRuleEventService;
    @Autowired
    PLRulePostActionTypeService plRulePostActionTypeService;

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

        Optional<Journey> journey_1 = journeyService.findByTitle("Journey_1");
        Optional<Journey> journey_2 = journeyService.findByTitle("Journey_2");

        byte[] puzzle_Ground_Image_1 = ImageUtil.getImage("src/main/resources/images/X-O Problem/","x-o-ground.json");

        BinaryContent puzzle_ground_binary_content_1 = new BinaryContent(1L, now, now,admin_1 , admin_1,"puzzle ground for hash image",puzzle_Ground_Image_1.length,puzzle_Ground_Image_1,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_ground_binary_content_1);

        byte[] hashImage_icon_byte = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","hashImage_icon.png");
        BinaryContent hashImage_icon = new BinaryContent(1L, now, now,admin_1 , admin_1,"hash image icon",puzzle_Ground_Image_1.length,puzzle_Ground_Image_1,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(hashImage_icon);

        byte[] hashImage_pic_byte = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","hashImage_pic.png");
        BinaryContent hashImage_pic = new BinaryContent(1L, now, now,admin_1 , admin_1,"hash image picture",puzzle_Ground_Image_1.length,hashImage_pic_byte,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(hashImage_pic);

        PuzzleCategory mathematic = puzzleCategoryService.findByValue("mathematic");

        Optional<PuzzleGroup> puzzleGroup_HashImage = puzzleGroupService.findByTitle("Hash Image - Puzzle Group 1");
        Optional<PuzzleGroup> puzzleGroup_IQ = puzzleGroupService.findByTitle("IQ Puzzle Group");

        JourneyStep step_1_journey_1 = new JourneyStep("step1_journey_1",1,20,20,journey_1.get(),puzzleGroup_HashImage.get(),1L,now,now,admin_1,admin_1);
        JourneyStep step_2_journey_1 = new JourneyStep("step2_journey_1",2,100,100,journey_1.get(),puzzleGroup_IQ.get(),1L,now,now,admin_1,admin_1);
        JourneyStep step_1_journey_2 = new JourneyStep("step1_journey_2",3,200,200,journey_2.get(),puzzleGroup_IQ.get(),1L,now,now,admin_1,admin_1);
        journeyStepService.save(step_1_journey_1);
        journeyStepService.save(step_2_journey_1);
        journeyStepService.save(step_1_journey_2);

        PLPrivacy privacy_1 = plPrivacyService.findByValue("Public");

        PuzzleLevel puzzleLevel_hashimage = new PuzzleLevel(admin_1,now,1L,"hashed image with a empty cell","4545",8,15,15f,4f,8f,13f,puzzleGroup_HashImage.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,3L,now,now,admin_1,admin_1);
        puzzleLevel_hashimage.setIcon(hashImage_icon);
        puzzleLevel_hashimage.setPicture(hashImage_pic);
        puzzleLevelService.save(puzzleLevel_hashimage);


        byte[] puzzle_group_Hash_Image = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle","hashImage_pic.png");
        BinaryContent puzzle_group_Hash_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"hashImage",puzzle_group_Hash_Image.length,puzzle_group_Hash_Image,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_Hash_Image_binary_content);

        Integer xPos=3;
        Integer xRotation=3;
        CameraSetup cameraSetup = new CameraSetup("setup 5",xPos,xPos,xPos,xRotation,xRotation,xRotation,1L,now,now,admin_1,admin_1);
        cameraSetupService.save(cameraSetup);


        PLGround puzzleLevel_hashImage_ground = new PLGround(3,3,puzzleLevel_hashimage,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleLevel_hashImage_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_hashImage_ground);

        PermitedPlayer player_1_puzzleLevel_hashimage = new PermitedPlayer(jalalHoseyni,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_hashimage);

        LearningContent learningContent_Division=new LearningContent("help to math","this content is about hash images",puzzle_group_Hash_Image_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Division);

        LearningTopicInPL puzzleLevelLearningTopic_1 = new LearningTopicInPL(puzzleLevel_hashimage,hashImage_Topic.get(),learningContent_Division,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);
        Float playScore =14f;
        Integer stars = ToolBox.getPuzzleLevelStars(playScore,puzzleLevel_hashimage.getFirstStarScore(),puzzleLevel_hashimage.getSecondStarScore(),puzzleLevel_hashimage.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_hashimage,now,now,80L,playScore,stars,1L,now,now,jalalHoseyni,jalalHoseyni);
        playHistoryService.save(playHistory_1);

        LearningSkillContent puzzleSkillLearningContent_1 = new LearningSkillContent(division,puzzleGroup_HashImage.get(),learningContent_Division,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_hashimage,now,now,GameStatus.Playing,1L,now,now,jalalHoseyni,jalalHoseyni);
        plGameInstanceService.save(puzzleLevelGameInstance);

        Optional<ALCityObject> ImageObject01 =alCityObjectService.findByTitle("ImageObject01");

        ALCityObjectInPG ImageObject01_in_haseImage_puzzleGroup = new ALCityObjectInPG("Image Hash Puzzle Group with Image Object","Hash_ImageObject",puzzleGroup_HashImage.get(),ImageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(ImageObject01_in_haseImage_puzzleGroup);

        //change actions for ImageObject01_in_haseImage_puzzleGroup
        alCityObjectInPGService.copyActionFromALCityObjectToPuzzleGroupObject(ImageObject01_in_haseImage_puzzleGroup);

        StringBuffer  condition_Objective = new StringBuffer("equal(BoardVar(finished),true)");
              //  + "(img2.x == img2.targetX)&&(img2.y == img2.targetY)" + ","
               // + "(img3.x == img3.targetX)&&(img3.y == img3.targetY)" + ","
              //  + "(img4.x == img4.targetX)&&(img4.y == img4.targetY)" + ","
              //  + "(img5.x == img5.targetX)&&(img5.y == img5.targetY)" +  ","
              //  + "(img6.x == img6.targetX)&&(img6.y == img6.targetY)" +	","
              //  + "(img7.x == img7.targetX)&&(img7.y == img7.targetY)"+","
              //  + "(img8.x == img8.targetX)&&(img8.y == img8.targetY)");

        PLObjective puzzleLevelObjective = new PLObjective("arrange image pieces","arrange image pieces as correct image",10f,2f,condition_Objective,timeManagement,alCoin10WalletItem.get(),puzzleLevel_hashimage
                ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective);


        Attribute pl_variable_finished =new Attribute("finished",puzzleLevel_hashimage.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);

        AttributeValue pl_variable_finished_value= new AttributeValue(null,1,null,null,null,null,null,null,pl_variable_finished,pl_variable_finished,1L,now,now,admin_1,admin_1,puzzleLevel_hashimage.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_finished_value);

        Attribute pl_variable_dragTargetObject =new Attribute("dragTargetObject",puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_dragTargetObject);
        AttributeValue pl_variable_dragTargetObject_value= new AttributeValue(null,1,null,null,null,null,null,null,pl_variable_dragTargetObject,pl_variable_dragTargetObject,1L,now,now,admin_1,admin_1,puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_dragTargetObject_value);


        Attribute pl_variable_machedCount =new Attribute("machedCount",puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_machedCount);
        AttributeValue pl_variable_machedCount_value= new AttributeValue(null,1,null,null,null,null,null,null,pl_variable_machedCount,pl_variable_machedCount,1L,now,now,admin_1,admin_1,puzzleLevel_hashimage.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_machedCount_value);


        Attribute ImageObject01_property_1 =new Attribute("image",ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_1);
        AttributeValue  Image0object_property_1_value= new AttributeValue(null,null,null,null,null,null,puzzle_group_Hash_Image_binary_content.getId(),null,ImageObject01_property_1,ImageObject01_property_1,1L,now,now,admin_1,admin_1,ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value);

        Attribute ImageObject01_variable_1 =new Attribute("targetX",ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_1);
        AttributeValue  ImageObject01_variable_1_value= new AttributeValue(null,3,null,null,null,null,null,null,ImageObject01_variable_1,ImageObject01_variable_1,1L,now,now,admin_1,admin_1,ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_1_value);

        Attribute ImageObject01_variable_2 =new Attribute("targetY",ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_2);
        AttributeValue  ImageObject01_variable_2_value= new AttributeValue(null,3,null,null,null,null,null,null,ImageObject01_variable_2,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,ImageObject01_in_haseImage_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value);



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

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img0.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);
        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img1.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);
        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img2.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);
        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img3.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img4.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img5.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img6.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img7.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);

        DTOUtil.copyPropertiesFromTo(ImageObject01_in_haseImage_puzzleGroup.getId(),instance_img8.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,AttributeOwnerType.Puzzle_Level_Instance_Variable,
                attributeService,attributeValueService);


        byte[] image_0_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","0.png");
        byte[] image_1_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","1.png");
        byte[] image_2_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","2.png");
        byte[] image_3_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","3.png");
        byte[] image_4_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","4.png");
        byte[] image_5_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","5.png");
        byte[] image_6_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","6.png");
        byte[] image_7_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","7.png");
        byte[] image_8_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","8.png");


        BinaryContent image_0_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img0",image_0_hash.length,image_0_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_1_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img1",image_1_hash.length,image_1_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_2_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img2",image_2_hash.length,image_2_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_3_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img3",image_3_hash.length,image_3_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_4_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img4",image_4_hash.length,image_4_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_5_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img5",image_5_hash.length,image_5_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_6_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img6",image_6_hash.length,image_6_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_7_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img7",image_7_hash.length,image_7_hash,null,"tag1","","",BinaryContentType.Image);
        BinaryContent image_8_Instance_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"img8",image_8_hash.length,image_8_hash,null,"tag1","","",BinaryContentType.Image);

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

        AttributeValue alCityAttributeValue_binary_0= new AttributeValue(null,null,null,null,null,null,image_0_Instance_content.getId(),null,alCityAttribute_bgImage_0,alCityAttribute_bgImage_0,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);

        Attribute alCityAttribute_bgImage_1 =new Attribute("bgImage",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_1);

        AttributeValue alCityAttributeValue_binary_1= new AttributeValue(null,null,null,null,null,null,image_1_Instance_content.getId(),null,alCityAttribute_bgImage_1,alCityAttribute_bgImage_1,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);


        Attribute alCityAttribute_bgImage_2 =new Attribute("bgImage",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_2);

        AttributeValue alCityAttributeValue_binary_2= new AttributeValue(null,null,null,null,null,null,image_2_Instance_content.getId(),null,alCityAttribute_bgImage_2,alCityAttribute_bgImage_2,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);

        Attribute alCityAttribute_bgImage_3 =new Attribute("bgImage",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_3);

        AttributeValue alCityAttributeValue_binary_3= new AttributeValue(null,null,null,null,null,null,image_3_Instance_content.getId(),null,alCityAttribute_bgImage_3,alCityAttribute_bgImage_3,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);

        Attribute alCityAttribute_bgImage_4 =new Attribute("bgImage",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_4);

        AttributeValue alCityAttributeValue_binary_4= new AttributeValue(null,null,null,null,null,null,image_4_Instance_content.getId(),null,alCityAttribute_bgImage_4,alCityAttribute_bgImage_4,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);


        Attribute alCityAttribute_bgImage_5 =new Attribute("bgImage",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_5);

        AttributeValue alCityAttributeValue_binary_5= new AttributeValue(null,null,null,null,null,null,image_5_Instance_content.getId(),null,alCityAttribute_bgImage_5,alCityAttribute_bgImage_5,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);


        Attribute alCityAttribute_bgImage_6 =new Attribute("bgImage",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_6);
        AttributeValue alCityAttributeValue_binary_6= new AttributeValue(null,null,null,null,null,null,image_6_Instance_content.getId(),null,alCityAttribute_bgImage_6,alCityAttribute_bgImage_6,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);


        Attribute alCityAttribute_bgImage_7 =new Attribute("bgImage",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_7);
        AttributeValue alCityAttributeValue_binary_7= new AttributeValue(null,null,null,null,null,null,image_7_Instance_content.getId(),null,alCityAttribute_bgImage_7,alCityAttribute_bgImage_7,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);

        Attribute alCityAttribute_bgImage_8 =new Attribute("bgImage",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(alCityAttribute_bgImage_8);

        AttributeValue alCityAttributeValue_binary_8= new AttributeValue(null,null,null,null,null,null,image_8_Instance_content.getId(),null,alCityAttribute_bgImage_8,alCityAttribute_bgImage_8,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Property);


        attributeValueService.save(alCityAttributeValue_binary_0);
        attributeValueService.save(alCityAttributeValue_binary_1);
        attributeValueService.save(alCityAttributeValue_binary_2);
        attributeValueService.save(alCityAttributeValue_binary_3);
        attributeValueService.save(alCityAttributeValue_binary_4);
        attributeValueService.save(alCityAttributeValue_binary_5);
        attributeValueService.save(alCityAttributeValue_binary_6);
        attributeValueService.save(alCityAttributeValue_binary_7);
        attributeValueService.save(alCityAttributeValue_binary_8);



//        Attribute alCityAttribute_instance_1_targetX= new Attribute("targetX",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_targetX);
//        AttributeValue alCityAttributeValue_instance_1_targetX = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_1_targetX,alCityAttribute_instance_1_targetX,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_1_targetX);
//
//        Attribute alCityAttribute_instance_1_targetY= new Attribute("targetY",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_targetY);
//        AttributeValue alCityAttributeValue_instance_1_targetY = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_1_targetY,alCityAttribute_instance_1_targetY,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_1_targetY);
//
//        Attribute alCityAttribute_instance_1_X= new Attribute("X",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_X);
//        AttributeValue alCityAttributeValue_instance_1_X = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_1_X,alCityAttribute_instance_1_X,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_1_X);
//
//        Attribute alCityAttribute_instance_1_Y= new Attribute("Y",instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_Y);
//        AttributeValue alCityAttributeValue_instance_1_Y = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_1_Y,alCityAttribute_instance_1_Y,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_1_Y);
//
//        Attribute alCityAttribute_instance_2_targetX= new Attribute("targetX",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_targetX);
//        AttributeValue alCityAttributeValue_instance_2_targetX = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_2_targetX,alCityAttribute_instance_2_targetX,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_2_targetX);
//
//        Attribute alCityAttribute_instance_2_targetY= new Attribute("targetY",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_targetY);
//        AttributeValue alCityAttributeValue_instance_2_targetY = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_2_targetY,alCityAttribute_instance_2_targetY,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_2_targetY);
//
//        Attribute alCityAttribute_instance_2_X= new Attribute("X",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_X);
//        AttributeValue alCityAttributeValue_instance_2_X = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_2_X,alCityAttribute_instance_2_X,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_2_X);
//
//        Attribute alCityAttribute_instance_2_Y= new Attribute("Y",instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_Y);
//        AttributeValue alCityAttributeValue_instance_2_Y = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_2_Y,alCityAttribute_instance_2_Y,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_2_Y);
//
//
//        Attribute alCityAttribute_instance_3_targetX= new Attribute("targetX",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_targetX);
//        AttributeValue alCityAttributeValue_instance_3_targetX = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_3_targetX,alCityAttribute_instance_3_targetX,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_3_targetX);
//
//        Attribute alCityAttribute_instance_3_targetY= new Attribute("targetY",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_targetY);
//        AttributeValue alCityAttributeValue_instance_3_targetY = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_3_targetY,alCityAttribute_instance_3_targetY,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_3_targetY);
//
//        Attribute alCityAttribute_instance_3_X= new Attribute("X",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_X);
//        AttributeValue alCityAttributeValue_instance_3_X = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_3_X,alCityAttribute_instance_3_X,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_3_X);
//
//        Attribute alCityAttribute_instance_3_Y= new Attribute("Y",instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_Y);
//        AttributeValue alCityAttributeValue_instance_3_Y = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_3_Y,alCityAttribute_instance_3_Y,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_3_Y);
//
//
//        Attribute alCityAttribute_instance_4_targetX= new Attribute("targetX",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_targetX);
//        AttributeValue alCityAttributeValue_instance_4_targetX = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_4_targetX,alCityAttribute_instance_4_targetX,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_4_targetX);
//
//        Attribute alCityAttribute_instance_4_targetY= new Attribute("targetY",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_targetY);
//        AttributeValue alCityAttributeValue_instance_4_targetY = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_4_targetY,alCityAttribute_instance_4_targetY,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_4_targetY);
//
//        Attribute alCityAttribute_instance_4_X= new Attribute("X",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_X);
//        AttributeValue alCityAttributeValue_instance_4_X = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_4_X,alCityAttribute_instance_4_X,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_4_X);
//
//        Attribute alCityAttribute_instance_4_Y= new Attribute("Y",instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_Y);
//        AttributeValue alCityAttributeValue_instance_4_Y = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_4_Y,alCityAttribute_instance_4_Y,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_4_Y);
//
//        Attribute alCityAttribute_instance_5_targetX= new Attribute("targetX",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_targetX);
//        AttributeValue alCityAttributeValue_instance_5_targetX = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_5_targetX,alCityAttribute_instance_5_targetX,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_5_targetX);
//
//        Attribute alCityAttribute_instance_5_targetY= new Attribute("targetY",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_targetY);
//        AttributeValue alCityAttributeValue_instance_5_targetY = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_5_targetY,alCityAttribute_instance_5_targetY,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_5_targetY);
//
//        Attribute alCityAttribute_instance_5_X= new Attribute("X",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_X);
//        AttributeValue alCityAttributeValue_instance_5_X = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_5_X,alCityAttribute_instance_5_X,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_5_X);
//
//        Attribute alCityAttribute_instance_5_Y= new Attribute("Y",instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_Y);
//        AttributeValue alCityAttributeValue_instance_5_Y = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_5_Y,alCityAttribute_instance_5_Y,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_5_Y);
//
//        Attribute alCityAttribute_instance_6_targetX= new Attribute("targetX",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_targetX);
//        AttributeValue alCityAttributeValue_instance_6_targetX = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_6_targetX,alCityAttribute_instance_6_targetX,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_6_targetX);
//
//        Attribute alCityAttribute_instance_6_targetY= new Attribute("targetY",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_targetY);
//        AttributeValue alCityAttributeValue_instance_6_targetY = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_6_targetY,alCityAttribute_instance_6_targetY,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_6_targetY);
//
//        Attribute alCityAttribute_instance_6_X= new Attribute("X",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_X);
//        AttributeValue alCityAttributeValue_instance_6_X = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_6_X,alCityAttribute_instance_6_X,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_6_X);
//
//        Attribute alCityAttribute_instance_6_Y= new Attribute("Y",instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_Y);
//        AttributeValue alCityAttributeValue_instance_6_Y = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_6_Y,alCityAttribute_instance_6_Y,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_6_Y);
//
//        Attribute alCityAttribute_instance_7_targetX= new Attribute("targetX",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_targetX);
//        AttributeValue alCityAttributeValue_instance_7_targetX = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_7_targetX,alCityAttribute_instance_7_targetX,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_7_targetX);
//
//        Attribute alCityAttribute_instance_7_targetY= new Attribute("targetY",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_targetY);
//        AttributeValue alCityAttributeValue_instance_7_targetY = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_7_targetY,alCityAttribute_instance_7_targetY,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_7_targetY);
//
//        Attribute alCityAttribute_instance_7_X= new Attribute("X",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_X);
//        AttributeValue alCityAttributeValue_instance_7_X = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_7_X,alCityAttribute_instance_7_X,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_7_X);
//
//        Attribute alCityAttribute_instance_7_Y= new Attribute("Y",instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_Y);
//        AttributeValue alCityAttributeValue_instance_7_Y = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_7_Y,alCityAttribute_instance_7_Y,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_7_Y);
//
//        Attribute alCityAttribute_instance_8_targetX= new Attribute("targetX",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_targetX);
//        AttributeValue alCityAttributeValue_instance_8_targetX = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_8_targetX,alCityAttribute_instance_8_targetX,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_8_targetX);
//
//        Attribute alCityAttribute_instance_8_targetY= new Attribute("targetY",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_targetY);
//        AttributeValue alCityAttributeValue_instance_8_targetY = new AttributeValue(null,2,null,null,null,null,null,null,alCityAttribute_instance_8_targetY,alCityAttribute_instance_8_targetY,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_8_targetY);
//
//        Attribute alCityAttribute_instance_8_X= new Attribute("X",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_X);
//        AttributeValue alCityAttributeValue_instance_8_X = new AttributeValue(null,3,null,null,null,null,null,null,alCityAttribute_instance_8_X,alCityAttribute_instance_8_X,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_8_X);
//
//        Attribute alCityAttribute_instance_8_Y= new Attribute("Y",instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_Y);
//        AttributeValue alCityAttributeValue_instance_8_Y = new AttributeValue(null,1,null,null,null,null,null,null,alCityAttribute_instance_8_Y,alCityAttribute_instance_8_Y,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Puzzle_Level_Instance_Variable);
//        attributeValueService.save(alCityAttributeValue_instance_8_Y);

        Optional<PLRuleEvent> drag_event = plRuleEventService.findByName("Drag");
        Optional<PLRuleEvent> internalevent_checkcell = plRuleEventService.findByName("internalevent:checkcell");
        Optional<PLRuleEvent> internalevent_check = plRuleEventService.findByName("internalevent:check");

        Optional<PLRulePostActionType> CallObjectAction = plRulePostActionTypeService.findByValue("CallObjectAction");
        Optional<PLRulePostActionType> UserAlertAction = plRulePostActionTypeService.findByValue("UserAlertAction");
        Optional<PLRulePostActionType> VariableAssignmentAction = plRulePostActionTypeService.findByValue("VariableAssignmentAction");
        Optional<PLRulePostActionType> FireEventAction = plRulePostActionTypeService.findByValue("FireEventAction");
        Optional<PLRulePostActionType> FireEventAction_checkcell = plRulePostActionTypeService.findByValue("FireEventAction:checkcell");
        Optional<PLRulePostActionType> FireEventAction_check = plRulePostActionTypeService.findByValue("FireEventAction:check");
        Optional<PLRulePostActionType> internalevent_checkcel = plRulePostActionTypeService.findByValue("internalevent:checkcell");

        StringBuffer    move_rule_condition = new StringBuffer("equal(1,1)");
        PLRule rule_for_move = new PLRule("Move object by Drag",1
                ,move_rule_condition,puzzleLevel_hashimage,drag_event.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_move);


        PLRulePostAction move_rule_post_Action_1 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),1,"","",
                "BoardVar(dragTargetObject)",new StringBuffer("InstProp(InstByPos(EventParam(toRow),EventParam(toCol)),objectId)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_1);

        PLRulePostAction move_rule_post_Action_2 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),2,"","",
                "InstProp(InstById(BoardVar(dragTargetObject)), x)",new StringBuffer("EventParam(fromRow)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_2);

        PLRulePostAction move_rule_post_Action_3 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),3,"","",
                "InstProp(InstById(BoardVar(dragTargetObject)), y)",new StringBuffer("EventParam(fromCol)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_3);


        PLRulePostAction move_rule_post_Action_4 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),4,"","",
                "InstProp(InstByPos(EventParam(fromRow),EventParam(fromCol)), x)",new StringBuffer("EventParam(toRow)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_4);

        PLRulePostAction move_rule_post_Action_5 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),5,"","",
                "InstProp(InstByPos(EventParam(fromRow),EventParam(fromCol)), y)",new StringBuffer("EventParam(toCol)"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_5);


        PLRulePostAction move_rule_post_Action_6 = new PLRulePostAction(rule_for_move,CallObjectAction.get(),6,"move","InstByPos(EventParam(fromRow),EventParam(fromCol))",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_6);

        Attribute rulePostAction_6_move_param_1 =new Attribute("fromRow",move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_6_move_param_1);
        AttributeValue rulePostAction_6_move_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(fromRow)",rulePostAction_6_move_param_1,rulePostAction_6_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_6_move_param_1_value);

        Attribute rulePostAction_6_move_param_2 =new Attribute("toRow",move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_6_move_param_2);
        AttributeValue rulePostAction_6_move_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(toRow)",rulePostAction_6_move_param_2,rulePostAction_6_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_6_move_param_2_value);

        Attribute rulePostAction_6_move_param_3 =new Attribute("fromCol",move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_6_move_param_3);
        AttributeValue rulePostAction_6_move_param_3_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(fromCol)",rulePostAction_6_move_param_3,rulePostAction_6_move_param_3,1L,now,now,admin_1,admin_1,move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_6_move_param_3_value);

        Attribute rulePostAction_6_move_param_4 =new Attribute("toCol",move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_6_move_param_4);
        AttributeValue rulePostAction_6_move_param_4_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(toCol)",rulePostAction_6_move_param_4,rulePostAction_6_move_param_4,1L,now,now,admin_1,admin_1,move_rule_post_Action_6.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_6_move_param_4_value);


        PLRulePostAction move_rule_post_Action_7 = new PLRulePostAction(rule_for_move,CallObjectAction.get(),7,"move","BoardVar(dragTargetObject)",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_7);

        Attribute rulePostAction_7_move_param_1 =new Attribute("fromRow",move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_move_param_1);
        AttributeValue rulePostAction_7_move_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(toRow)",rulePostAction_7_move_param_1,rulePostAction_7_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_7_move_param_1_value);

        Attribute rulePostAction_7_move_param_2 =new Attribute("toRow",move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_move_param_2);
        AttributeValue rulePostAction_7_move_param_2_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(fromRow)",rulePostAction_7_move_param_2,rulePostAction_7_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_7_move_param_2_value);

        Attribute rulePostAction_7_move_param_3 =new Attribute("fromCol",move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_move_param_3);
        AttributeValue rulePostAction_7_move_param_3_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(toCol)",rulePostAction_7_move_param_3,rulePostAction_7_move_param_3,1L,now,now,admin_1,admin_1,move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_7_move_param_3_value);

        Attribute rulePostAction_7_move_param_4 =new Attribute("toCol",move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Experssion,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_7_move_param_4);
        AttributeValue rulePostAction_7_move_param_4_value= new AttributeValue(null,null,null,null,null,null,null,"EventParam(fromCol)",rulePostAction_7_move_param_4,rulePostAction_7_move_param_4,1L,now,now,admin_1,admin_1,move_rule_post_Action_7.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_7_move_param_4_value);

        PLRulePostAction move_rule_post_Action_8 = new PLRulePostAction(rule_for_move,VariableAssignmentAction.get(),8,"","",
                "BoardVar(machedCount)",new StringBuffer("0"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_8);

        PLRulePostAction move_rule_post_Action_9 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),9,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_9);

        Attribute rulePostAction_9_move_param_1 =new Attribute("row",move_rule_post_Action_9.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_9_move_param_1);
        AttributeValue rulePostAction_9_move_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_9_move_param_1,rulePostAction_9_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_9.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_9_move_param_1_value);

        Attribute rulePostAction_9_move_param_2 =new Attribute("col",move_rule_post_Action_9.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_9_move_param_2);
        AttributeValue rulePostAction_9_move_param_2_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_9_move_param_2,rulePostAction_9_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_9.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_9_move_param_2_value);

        PLRulePostAction move_rule_post_Action_10 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),10,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_10);

        Attribute rulePostAction_10_move_param_1 =new Attribute("row",move_rule_post_Action_10.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_10_move_param_1);
        AttributeValue rulePostAction_10_move_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_10_move_param_1,rulePostAction_10_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_10.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_10_move_param_1_value);

        Attribute rulePostAction_10_move_param_2 =new Attribute("col",move_rule_post_Action_10.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_10_move_param_2);
        AttributeValue rulePostAction_10_move_param_2_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_10_move_param_2,rulePostAction_10_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_10.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_10_move_param_2_value);

        PLRulePostAction move_rule_post_Action_11 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),11,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_11);

        Attribute rulePostAction_11_move_param_1 =new Attribute("row",move_rule_post_Action_11.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_11_move_param_1);
        AttributeValue rulePostAction_11_move_param_1_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_11_move_param_1,rulePostAction_11_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_11.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_11_move_param_1_value);

        Attribute rulePostAction_11_move_param_2 =new Attribute("col",move_rule_post_Action_11.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_11_move_param_2);
        AttributeValue rulePostAction_11_move_param_2_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_11_move_param_2,rulePostAction_11_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_11.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_11_move_param_2_value);

        PLRulePostAction move_rule_post_Action_12 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),12,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_12);

        Attribute rulePostAction_12_move_param_1 =new Attribute("row",move_rule_post_Action_12.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_12_move_param_1);
        AttributeValue rulePostAction_12_move_param_1_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_12_move_param_1,rulePostAction_12_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_12.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_12_move_param_1_value);

        Attribute rulePostAction_12_move_param_2 =new Attribute("col",move_rule_post_Action_12.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_12_move_param_2);
        AttributeValue rulePostAction_12_move_param_2_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_12_move_param_2,rulePostAction_12_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_12.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_12_move_param_2_value);

        PLRulePostAction move_rule_post_Action_13 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),13,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_13);

        Attribute rulePostAction_13_move_param_1 =new Attribute("row",move_rule_post_Action_13.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_13_move_param_1);
        AttributeValue rulePostAction_13_move_param_1_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_13_move_param_1,rulePostAction_13_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_13.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_13_move_param_1_value);

        Attribute rulePostAction_13_move_param_2 =new Attribute("col",move_rule_post_Action_13.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_13_move_param_2);
        AttributeValue rulePostAction_13_move_param_2_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_13_move_param_2,rulePostAction_13_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_13.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_13_move_param_2_value);

        PLRulePostAction move_rule_post_Action_14 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),14,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_14);

        Attribute rulePostAction_14_move_param_1 =new Attribute("row",move_rule_post_Action_14.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_14_move_param_1);
        AttributeValue rulePostAction_14_move_param_1_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_14_move_param_1,rulePostAction_14_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_14.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_14_move_param_1_value);

        Attribute rulePostAction_14_move_param_2 =new Attribute("col",move_rule_post_Action_14.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_14_move_param_2);
        AttributeValue rulePostAction_14_move_param_2_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_14_move_param_2,rulePostAction_14_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_14.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_14_move_param_2_value);

        PLRulePostAction move_rule_post_Action_15 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),15,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_15);

        Attribute rulePostAction_15_move_param_1 =new Attribute("row",move_rule_post_Action_15.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_15_move_param_1);
        AttributeValue rulePostAction_15_move_param_1_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_15_move_param_1,rulePostAction_15_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_15.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_15_move_param_1_value);

        Attribute rulePostAction_15_move_param_2 =new Attribute("col",move_rule_post_Action_15.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_15_move_param_2);
        AttributeValue rulePostAction_15_move_param_2_value= new AttributeValue(null,1,null,null,null,null,null,null,rulePostAction_15_move_param_2,rulePostAction_15_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_15.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_15_move_param_2_value);

        PLRulePostAction move_rule_post_Action_16 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),16,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_16);

        Attribute rulePostAction_16_move_param_1 =new Attribute("row",move_rule_post_Action_16.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_16_move_param_1);
        AttributeValue rulePostAction_16_move_param_1_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_16_move_param_1,rulePostAction_16_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_16.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_16_move_param_1_value);

        Attribute rulePostAction_16_move_param_2 =new Attribute("col",move_rule_post_Action_16.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_16_move_param_2);
        AttributeValue rulePostAction_16_move_param_2_value= new AttributeValue(null,2,null,null,null,null,null,null,rulePostAction_16_move_param_2,rulePostAction_16_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_16.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_16_move_param_2_value);

        PLRulePostAction move_rule_post_Action_17 = new PLRulePostAction(rule_for_move,FireEventAction_checkcell.get(),17,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_17);

        Attribute rulePostAction_17_move_param_1 =new Attribute("row",move_rule_post_Action_17.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_17_move_param_1);
        AttributeValue rulePostAction_17_move_param_1_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_17_move_param_1,rulePostAction_17_move_param_1,1L,now,now,admin_1,admin_1,move_rule_post_Action_17.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_17_move_param_1_value);

        Attribute rulePostAction_17_move_param_2 =new Attribute("col",move_rule_post_Action_17.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(rulePostAction_17_move_param_2);
        AttributeValue rulePostAction_17_move_param_2_value= new AttributeValue(null,3,null,null,null,null,null,null,rulePostAction_17_move_param_2,rulePostAction_17_move_param_2,1L,now,now,admin_1,admin_1,move_rule_post_Action_17.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(rulePostAction_17_move_param_2_value);

        PLRulePostAction move_rule_post_Action_18 = new PLRulePostAction(rule_for_move,FireEventAction_check.get(),18,"","",
                "",new StringBuffer(""),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(move_rule_post_Action_18);


        StringBuffer    x_win_rule_condition = new StringBuffer("equal(InstProp(InstByPos(EventParam(row), EventParam(col)),x),InstVar(InstByPos(EventParam(row), EventParam(col)),targetX)) & equal(InstProp(InstByPos(EventParam(row), EventParam(col)),y),InstVar(InstByPos(EventParam(row), EventParam(col)),targetY))");
        PLRule x_win_rule = new PLRule("X Win Rule",2
                ,x_win_rule_condition,puzzleLevel_hashimage,internalevent_checkcell.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(x_win_rule);

        PLRulePostAction x_win_rule_post_Action_1 = new PLRulePostAction(x_win_rule,VariableAssignmentAction.get(),1,"","",
                "BoardVar(machedCount)",new StringBuffer("BoardVar(machedCount) + 1"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(x_win_rule_post_Action_1);

        StringBuffer    x_win_rule_condition_2 = new StringBuffer("equal(BoardVar(machedCount),9)");
        PLRule x_win_rule_2 = new PLRule("X Win Rule",2
                ,x_win_rule_condition_2,puzzleLevel_hashimage,internalevent_check.get(),1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(x_win_rule_2);

        PLRulePostAction x_win_rule_2_post_Action_1 = new PLRulePostAction(x_win_rule_2,VariableAssignmentAction.get(),1,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(x_win_rule_2_post_Action_1);

        PLRulePostAction x_win_rule_2_post_Action_2 = new PLRulePostAction(x_win_rule_2,UserAlertAction.get(),2,"","",
                "BoardVar(finished)",new StringBuffer("true"),"info","you won!",1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(x_win_rule_2_post_Action_2);



    }





}
