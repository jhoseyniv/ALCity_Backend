package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
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


@Order(value=6)
@Component
public class ImportXOProblemData_3 implements CommandLineRunner {

    @Autowired
    private ApplicationMemberService applicationMemberService;

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
    LearningTopicService learningTopicService;

    @Autowired
    LearningContentService learningContentService;

    @Autowired
    PLGameInstanceService plGameInstanceService;

    @Autowired
    ObjectCategoryService objectCategoryService;

    @Autowired
    PuzzleObjectService puzzleObjectService;

    @Autowired
    PGObjectInstanceService pgObjectInstanceService;


    @Autowired
    AttributeValueService attributeValueService;



    @Autowired
    private CameraSetupService cameraSetupService;


    @Autowired
    ClientTypeService clientTypeService;

    @Autowired
    ActionRendererService actionRendererService;


    @Autowired
    PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;

    @Autowired
    PuzzleGroup_PuzzleObjectService puzzleGroup_PuzzleObjectService;

    @Autowired
    PLRuleService puzzleLevelRuleService;


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
    private ApplicationMember_WalletItemService applicationMember_walletItemService;


   @Autowired
    PuzzleSkillLearningContentService puzzleSkillLearningContentService;
    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 3 X-O Probelm");
        System.out.println("...Import X-O Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        ApplicationMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        ApplicationMember Alireza_Zare = applicationMemberService.findByUsername("alireza");

        LearningTopic X_O_Topic = learningTopicService.findByTitle("X-O Game");

         LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill matching = learningSkillService.findByValue("matching");
        WalletItem alCoin100WalletItem = walletItemService.findByValue("al_coin_100");
        WalletItem alCoin10WalletItem = walletItemService.findByValue("al_coin_10");

        Journey journey_1 = journeyService.findByTitle("Journey_1");
        Journey journey_2 = journeyService.findByTitle("Journey_2");



        byte[] puzzle_Ground_Image_X_O = ImageUtil.getImage("src/main/resources/images/X-O Problem/","x-o-ground.png");
        BinaryContent puzzle_ground_binary_content_1 = new BinaryContent("puzzle ground for X-O Game",puzzle_Ground_Image_X_O,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_ground_binary_content_1);

        byte[] puzzle_group_Icon_1 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_1.png");
        BinaryContent puzzle_group_binary_content_1 = new BinaryContent("image_puzzle_group_x-o",puzzle_group_Icon_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_binary_content_1);

        byte[] text_object_content = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_binary_content = new BinaryContent("text_object_x-o",text_object_content,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(textObject_binary_content);

        ObjectCategory objectCategory_TextObject = objectCategoryService.findByValue("TextObject");

        Optional<PuzzleGroup>  IQ_Puzzle_Group =puzzleGroupService.findByTitle("Hash Image - Puzzle Group 1");

        PuzzleObject textObject = new PuzzleObject("TextObject",objectCategory_TextObject,textObject_binary_content,textObject_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
        puzzleObjectService.save(textObject);

        PuzzleGroup_PuzzleObject  puzzleGroup_puzzleObject = new PuzzleGroup_PuzzleObject ("Image Hash Puzzle Group with Text Object","X_O_textObject",IQ_Puzzle_Group.get(),textObject,1L,now,now,admin_1,admin_1);
        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);


        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_x_o = new PuzzleLevel(now,1L,"X-O","4500",10,14,5f,IQ_Puzzle_Group.get(),PLDifficulty.Easy,PLStatus.Ongoing,privacy_1,puzzle_group_binary_content_1,puzzle_group_binary_content_1,3L,now,now,admin_1,admin_1);
        puzzleLevelService.save(puzzleLevel_x_o);


        byte[] puzzle_group_X_O_Image = ImageUtil.getImage("src/main/resources/images/X-O Problem/","puzzle_group_X_O_Image.png");
        BinaryContent puzzle_group_X_O_binary_content = new BinaryContent("X-O",puzzle_group_X_O_Image,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_X_O_binary_content);

        Integer xPos=1;
        Integer xRotation=0;
        CameraSetup cameraSetup = new CameraSetup(1L,now,now,admin_1,admin_1,xPos,xPos,xPos,xRotation,xRotation,xRotation);
        cameraSetupService.save(cameraSetup);


        PLGround puzzleLevel_X_O_ground = new PLGround(3,3,puzzleLevel_x_o,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
        puzzleLevel_X_O_ground.setCameraSetup(cameraSetup);
        puzzleLevelGroundService.save(puzzleLevel_X_O_ground);

        PermitedPlayer player_1_puzzleLevel_X_O = new PermitedPlayer(Alireza_Zare,puzzleLevel_x_o,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_X_O);

        LearningContent learningContent_Mathcing=new LearningContent("help to IQ","this content is about X-O Games",puzzle_group_X_O_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Mathcing);

        PuzzleLevel_LearningTopic puzzleLevelLearningTopic_1 = new PuzzleLevel_LearningTopic(puzzleLevel_x_o,X_O_Topic,learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(puzzleLevelLearningTopic_1);

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,puzzleLevel_x_o,now,100,10f,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PuzzleSkillLearningContent puzzleSkillLearningContent_1 = new PuzzleSkillLearningContent(matching,IQ_Puzzle_Group.get(),learningContent_Mathcing,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,puzzleLevel_x_o,GameStatus.gameStatus_1,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("(equal(InstProp(InstByPos(2, 2),text),X) & " +
                "( (equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(2, 3),text),X)) |"+
                        "(equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(3, 2),text),X)) |   " +
                        " (equal(InstProp(InstByPos(1, 1),text),X)&equal(InstProp(InstByPos(3, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(1, 3),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))| "+
                        "(equal(InstProp(InstByPos(1, 1),text),X) & ((equal(InstProp(InstByPos(1, 2),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(2, 1),text),X)&equal(InstProp(InstByPos(3, 1),text),X))))|(equal(InstProp(InstByPos(3, 3),text),X) & " +
                "((equal(InstProp(InstByPos(2, 3),text),X)&equal(InstProp(InstByPos(1, 3),text),X)) | " +
                        "(equal(InstProp(InstByPos(3, 1),text),X)&equal(InstProp(InstByPos(3, 2),text),X))))");

        PLObjective puzzleLevelObjective_1 = new PLObjective("X won","X won the game",1.5f,2f,condition_Objective_1,matching,
                                                                 alCoin10WalletItem,puzzleLevel_x_o ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("(equal(InstProp(InstByPos(2, 2),text),O) & " +
                "( (equal(InstProp(InstByPos(2, 1),text),O)& equal(InstProp(InstByPos(2, 3),text),O)) | " +
                " (equal(InstProp(InstByPos(1, 2),text),O)& equal(InstProp(InstByPos(3, 2),text),O)) " +
                "| (equal(InstProp(InstByPos(1, 1),text),O)& equal(InstProp(InstByPos(3, 3),text),O))" +
                " |(equal(InstProp(InstByPos(1, 3),text),O)& equal(InstProp(InstByPos(3, 1),text),O))))|" +
                " (equal(InstProp(InstByPos(1, 1),text),O) & ((equal(InstProp(InstByPos(1, 2),text),O) &" +
                " equal(InstProp(InstByPos(1, 3),text),O))" +
                " |(equal(InstProp(InstByPos(2, 1),text),O)& equal(InstProp(InstByPos(3, 1),text),O))))|" +
                "(equal(InstProp(InstByPos(3, 3),text),O) & ((equal(InstProp(InstByPos(2, 3),text),O) & " +
                "equal(InstProp(InstByPos(1, 3),text),O)) " +
                "|(equal(InstProp(InstByPos(3, 1),text),O)& equal(InstProp(InstByPos(3, 2),text),O))))");

        PLObjective puzzleLevelObjective_2 = new PLObjective("O won","O won the game",5.5f,5f,condition_Objective_2,timeManagement,
                alCoin10WalletItem,puzzleLevel_x_o ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(puzzleLevelObjective_2);


        Attribute attribute_variable_Turn =new Attribute("Turn",puzzleLevel_x_o.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_Turn);
        AttributeValue attribute_variable_Turn_value= new AttributeValue(null,null,null,"X",null,null,attribute_variable_Turn,attribute_variable_Turn,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_Turn_value);

        Attribute attribute_variable_Finished =new Attribute("Finished",puzzleLevel_x_o.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(attribute_variable_Finished);

        AttributeValue attribute_variable_Finished_value= new AttributeValue(false,null,null,null,null,null,attribute_variable_Finished,attribute_variable_Finished,1L,now,now,admin_1,admin_1);
        attributeValueService.save(attribute_variable_Finished_value);



//        BinaryContent img_Image_binary_content = binaryContentService.findByfileName("image_object");

//        PuzzleObject ImageObject02 = new PuzzleObject("ImageObject02",objectCategory_Image,img_Image_binary_content,img_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(ImageObject02);
//        PuzzleObject eagle = new PuzzleObject("eagle",objectCategory_bird,eagle_Image_binary_content,eagle_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(eagle);
//
//        PuzzleObject goose = new PuzzleObject("Goose",objectCategory_bird,goose_Image_binary_content,goose_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(goose);
//
//        PuzzleObject fox = new PuzzleObject("Fox",objectCategory_Mamals,fox_Image_binary_content,fox_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(fox);
//
//        PuzzleObject wheat = new PuzzleObject("Wheat",objectCategory_cereal,wheat_Image_binary_content,wheat_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(wheat);
//
//        PuzzleObject ImageObject01 = new PuzzleObject("ImageObject01",objectCategory_Image,wheat_Image_binary_content,wheat_Image_binary_content,1L,now,now,jalalHoseyni,jalalHoseyni);
//        puzzleObjectService.save(ImageObject01);
//
//        PuzzleGroup_PuzzleObject  puzzleGroup_puzzleObject = new PuzzleGroup_PuzzleObject ("Image Hash Puzzle Group with Image Object","Hash_ImageObject",puzzleGroup_1,ImageObject01,1L,now,now,admin_1,admin_1);
//        puzzleGroup_PuzzleObjectService.save(puzzleGroup_puzzleObject);
//
//
//
//

//        PGObjectInstance instance_img0 = new PGObjectInstance("instance_img0",1,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img1 = new PGObjectInstance("instance_img1",2,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img2 = new PGObjectInstance("instance_img2",1,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img3 = new PGObjectInstance("instance_img3",2,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img4 = new PGObjectInstance("instance_img4",3,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img5 = new PGObjectInstance("instance_img5",1,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img6 = new PGObjectInstance("instance_img6",2,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img7 = new PGObjectInstance("instance_img7",3,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        PGObjectInstance instance_img8 = new PGObjectInstance("instance_img8",3,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//
//        pgObjectInstanceService.save(instance_img0);
//        pgObjectInstanceService.save(instance_img1);
//        pgObjectInstanceService.save(instance_img2);
//        pgObjectInstanceService.save(instance_img3);
//        pgObjectInstanceService.save(instance_img4);
//        pgObjectInstanceService.save(instance_img5);
//        pgObjectInstanceService.save(instance_img6);
//        pgObjectInstanceService.save(instance_img7);
//        pgObjectInstanceService.save(instance_img8);
//
//
//        byte[] image_0_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","0.png");
//        byte[] image_1_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","1.png");
//        byte[] image_2_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","2.png");
//        byte[] image_3_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","3.png");
//        byte[] image_4_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","4.png");
//        byte[] image_5_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","5.png");
//        byte[] image_6_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","6.png");
//        byte[] image_7_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","7.png");
//        byte[] image_8_hash = ImageUtil.getImage("src/main/resources/images/hashImage_Puzzle/","8.png");
//
//
//        BinaryContent image_0_Instance_content = new BinaryContent("img0",image_0_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_1_Instance_content = new BinaryContent("img1",image_1_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_2_Instance_content = new BinaryContent("img2",image_2_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_3_Instance_content = new BinaryContent("img3",image_3_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_4_Instance_content = new BinaryContent("img4",image_4_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_5_Instance_content = new BinaryContent("img5",image_5_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_6_Instance_content = new BinaryContent("img6",image_6_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_7_Instance_content = new BinaryContent("img7",image_7_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//        BinaryContent image_8_Instance_content = new BinaryContent("img8",image_8_hash,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
//
//        binaryContentService.save(image_0_Instance_content);
//        binaryContentService.save(image_1_Instance_content);
//        binaryContentService.save(image_2_Instance_content);
//        binaryContentService.save(image_3_Instance_content);
//        binaryContentService.save(image_4_Instance_content);
//        binaryContentService.save(image_5_Instance_content);
//        binaryContentService.save(image_6_Instance_content);
//        binaryContentService.save(image_7_Instance_content);
//        binaryContentService.save(image_8_Instance_content);
//
//        Attribute alCityAttribute_bgImage_0 =new Attribute("bgImage",instance_img0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_0);
//
//        AttributeValue alCityAttributeValue_binary_0= new AttributeValue(null,null,image_0_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_0,alCityAttribute_bgImage_0,1L,now,now,admin_1,admin_1);
//
//        Attribute alCityAttribute_bgImage_1 =new Attribute("bgImage",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_1);
//
//        AttributeValue alCityAttributeValue_binary_1= new AttributeValue(null,null,image_1_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_1,alCityAttribute_bgImage_1,1L,now,now,admin_1,admin_1);
//
//
//        Attribute alCityAttribute_bgImage_2 =new Attribute("bgImage",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_2);
//
//        AttributeValue alCityAttributeValue_binary_2= new AttributeValue(null,null,image_2_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_2,alCityAttribute_bgImage_2,1L,now,now,admin_1,admin_1);
//
//        Attribute alCityAttribute_bgImage_3 =new Attribute("bgImage",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_3);
//
//        AttributeValue alCityAttributeValue_binary_3= new AttributeValue(null,null,image_3_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_3,alCityAttribute_bgImage_3,1L,now,now,admin_1,admin_1);
//
//        Attribute alCityAttribute_bgImage_4 =new Attribute("bgImage",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_4);
//
//        AttributeValue alCityAttributeValue_binary_4= new AttributeValue(null,null,image_4_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_4,alCityAttribute_bgImage_4,1L,now,now,admin_1,admin_1);
//
//
//        Attribute alCityAttribute_bgImage_5 =new Attribute("bgImage",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_5);
//
//        AttributeValue alCityAttributeValue_binary_5= new AttributeValue(null,null,image_5_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_5,alCityAttribute_bgImage_5,1L,now,now,admin_1,admin_1);
//
//
//        Attribute alCityAttribute_bgImage_6 =new Attribute("bgImage",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_6);
//        AttributeValue alCityAttributeValue_binary_6= new AttributeValue(null,null,image_6_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_6,alCityAttribute_bgImage_6,1L,now,now,admin_1,admin_1);
//
//
//        Attribute alCityAttribute_bgImage_7 =new Attribute("bgImage",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_7);
//        AttributeValue alCityAttributeValue_binary_7= new AttributeValue(null,null,image_7_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_7,alCityAttribute_bgImage_7,1L,now,now,admin_1,admin_1);
//
//        Attribute alCityAttribute_bgImage_8 =new Attribute("bgImage",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_bgImage_8);
//
//        AttributeValue alCityAttributeValue_binary_8= new AttributeValue(null,null,image_8_Instance_content.getId(),null,null,null,alCityAttribute_bgImage_8,alCityAttribute_bgImage_8,1L,now,now,admin_1,admin_1);
//
//
//        attributeValueService.save(alCityAttributeValue_binary_0);
//        attributeValueService.save(alCityAttributeValue_binary_1);
//        attributeValueService.save(alCityAttributeValue_binary_2);
//        attributeValueService.save(alCityAttributeValue_binary_3);
//        attributeValueService.save(alCityAttributeValue_binary_4);
//        attributeValueService.save(alCityAttributeValue_binary_5);
//        attributeValueService.save(alCityAttributeValue_binary_6);
//        attributeValueService.save(alCityAttributeValue_binary_7);
//        attributeValueService.save(alCityAttributeValue_binary_8);
//
//
//
//
//        ClientType mobile = clientTypeService.findByValue("mobile");
//
//
//        ActionRenderer moveActionRenderer = new ActionRenderer("Move",mobile,ObjectAction.Move,1L,now,now,admin_1,admin_1);
//        actionRendererService.save(moveActionRenderer);
//
//        PuzzleObject_ObjectAction  imageObject01_MoveAction = new PuzzleObject_ObjectAction(POActionOwnerType.Puzzle_Object,puzzleGroup_puzzleObject.getId(),ObjectAction.Move,moveActionRenderer,1L,now,now,admin_1,admin_1);
//        puzzleObject_ObjectActionService.save(imageObject01_MoveAction);
//
//        Attribute alCityAttribute_move_action =new Attribute("actionId",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_action);
//        AttributeValue alCity_moveAction_parameter_action_id= new AttributeValue(null,null,alCityAttribute_move_action.getId(),null,null,null,alCityAttribute_move_action,alCityAttribute_move_action,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_action_id);
//
//        Attribute alCityAttribute_move_aSync =new Attribute("aSync",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Boolean,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_aSync);
//        AttributeValue alCity_moveAction_parameter_aSync= new AttributeValue(false,null,null,null,null,null,alCityAttribute_move_aSync,alCityAttribute_move_aSync,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_aSync);
//
//        Attribute alCityAttribute_move_formRow =new Attribute("formRow",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_formRow);
//        AttributeValue alCity_moveAction_parameter_fromRow= new AttributeValue(null,0,null,null,null,null,alCityAttribute_move_formRow,alCityAttribute_move_formRow,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_fromRow);
//
//        Attribute alCityAttribute_move_toRow =new Attribute("toRow",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_toRow);
//        AttributeValue alCity_moveAction_parameter_toRow= new AttributeValue(null,0,null,null,null,null,alCityAttribute_move_toRow,alCityAttribute_move_toRow,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_toRow);
//
//        Attribute alCityAttribute_move_fromCol =new Attribute("FromCol",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_fromCol);
//        AttributeValue alCity_moveAction_parameter_fromCol= new AttributeValue(null,0,null,null,null,null,alCityAttribute_move_fromCol,alCityAttribute_move_fromCol,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_fromCol);
//
//        Attribute alCityAttribute_move_toCol =new Attribute("toCol",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_toCol);
//        AttributeValue alCity_moveAction_parameter_toCol= new AttributeValue(null,0,null,null,null,null,alCityAttribute_move_toCol,alCityAttribute_move_toCol,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_toCol);
//
//        //this type must be clear object type
//        Attribute alCityAttribute_move_ObjectId =new Attribute("ObjectId",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_ObjectId);
//        AttributeValue alCity_moveAction_parameter_ObjectId= new AttributeValue(null,null,null,null,null,null,alCityAttribute_move_ObjectId,alCityAttribute_move_ObjectId,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_ObjectId);
//
//        //this type must be clear ...enum value for move type
//        Attribute alCityAttribute_move_moveType =new Attribute("moveType",imageObject01_MoveAction.getId(),AttributeOwnerType.Puzzle_Object_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_move_moveType);
//        AttributeValue alCity_moveAction_parameter_moveType= new AttributeValue(null,null,null,"jump",null,null,alCityAttribute_move_moveType,alCityAttribute_move_moveType,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCity_moveAction_parameter_moveType);
//
//
//
//        Attribute alCityAttribute_instance_1_targetX= new Attribute("targetX",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_targetX);
//        AttributeValue alCityAttributeValue_instance_1_targetX = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_1_targetX,alCityAttribute_instance_1_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_1_targetX);
//
//        Attribute alCityAttribute_instance_1_targetY= new Attribute("targetY",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_targetY);
//        AttributeValue alCityAttributeValue_instance_1_targetY = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_1_targetY,alCityAttribute_instance_1_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_1_targetY);
//
//        Attribute alCityAttribute_instance_1_X= new Attribute("X",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_X);
//        AttributeValue alCityAttributeValue_instance_1_X = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_1_X,alCityAttribute_instance_1_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_1_X);
//
//        Attribute alCityAttribute_instance_1_Y= new Attribute("Y",instance_img1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_1_Y);
//        AttributeValue alCityAttributeValue_instance_1_Y = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_1_Y,alCityAttribute_instance_1_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_1_Y);
//
//        Attribute alCityAttribute_instance_2_targetX= new Attribute("targetX",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_targetX);
//        AttributeValue alCityAttributeValue_instance_2_targetX = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_2_targetX,alCityAttribute_instance_2_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_2_targetX);
//
//        Attribute alCityAttribute_instance_2_targetY= new Attribute("targetY",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_targetY);
//        AttributeValue alCityAttributeValue_instance_2_targetY = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_2_targetY,alCityAttribute_instance_2_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_2_targetY);
//
//        Attribute alCityAttribute_instance_2_X= new Attribute("X",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_X);
//        AttributeValue alCityAttributeValue_instance_2_X = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_2_X,alCityAttribute_instance_2_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_2_X);
//
//        Attribute alCityAttribute_instance_2_Y= new Attribute("Y",instance_img2.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_2_Y);
//        AttributeValue alCityAttributeValue_instance_2_Y = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_2_Y,alCityAttribute_instance_2_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_2_Y);
//
//
//        Attribute alCityAttribute_instance_3_targetX= new Attribute("targetX",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_targetX);
//        AttributeValue alCityAttributeValue_instance_3_targetX = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_3_targetX,alCityAttribute_instance_3_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_3_targetX);
//
//        Attribute alCityAttribute_instance_3_targetY= new Attribute("targetY",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_targetY);
//        AttributeValue alCityAttributeValue_instance_3_targetY = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_3_targetY,alCityAttribute_instance_3_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_3_targetY);
//
//        Attribute alCityAttribute_instance_3_X= new Attribute("X",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_X);
//        AttributeValue alCityAttributeValue_instance_3_X = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_3_X,alCityAttribute_instance_3_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_3_X);
//
//        Attribute alCityAttribute_instance_3_Y= new Attribute("Y",instance_img3.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_3_Y);
//        AttributeValue alCityAttributeValue_instance_3_Y = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_3_Y,alCityAttribute_instance_3_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_3_Y);
//
//
//        Attribute alCityAttribute_instance_4_targetX= new Attribute("targetX",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_targetX);
//        AttributeValue alCityAttributeValue_instance_4_targetX = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_4_targetX,alCityAttribute_instance_4_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_4_targetX);
//
//        Attribute alCityAttribute_instance_4_targetY= new Attribute("targetY",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_targetY);
//        AttributeValue alCityAttributeValue_instance_4_targetY = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_4_targetY,alCityAttribute_instance_4_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_4_targetY);
//
//        Attribute alCityAttribute_instance_4_X= new Attribute("X",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_X);
//        AttributeValue alCityAttributeValue_instance_4_X = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_4_X,alCityAttribute_instance_4_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_4_X);
//
//        Attribute alCityAttribute_instance_4_Y= new Attribute("Y",instance_img4.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_4_Y);
//        AttributeValue alCityAttributeValue_instance_4_Y = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_4_Y,alCityAttribute_instance_4_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_4_Y);
//
//        Attribute alCityAttribute_instance_5_targetX= new Attribute("targetX",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_targetX);
//        AttributeValue alCityAttributeValue_instance_5_targetX = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_5_targetX,alCityAttribute_instance_5_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_5_targetX);
//
//        Attribute alCityAttribute_instance_5_targetY= new Attribute("targetY",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_targetY);
//        AttributeValue alCityAttributeValue_instance_5_targetY = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_5_targetY,alCityAttribute_instance_5_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_5_targetY);
//
//        Attribute alCityAttribute_instance_5_X= new Attribute("X",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_X);
//        AttributeValue alCityAttributeValue_instance_5_X = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_5_X,alCityAttribute_instance_5_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_5_X);
//
//        Attribute alCityAttribute_instance_5_Y= new Attribute("Y",instance_img5.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_5_Y);
//        AttributeValue alCityAttributeValue_instance_5_Y = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_5_Y,alCityAttribute_instance_5_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_5_Y);
//
//        Attribute alCityAttribute_instance_6_targetX= new Attribute("targetX",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_targetX);
//        AttributeValue alCityAttributeValue_instance_6_targetX = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_targetX,alCityAttribute_instance_6_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_6_targetX);
//
//        Attribute alCityAttribute_instance_6_targetY= new Attribute("targetY",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_targetY);
//        AttributeValue alCityAttributeValue_instance_6_targetY = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_6_targetY,alCityAttribute_instance_6_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_6_targetY);
//
//        Attribute alCityAttribute_instance_6_X= new Attribute("X",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_X);
//        AttributeValue alCityAttributeValue_instance_6_X = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_X,alCityAttribute_instance_6_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_6_X);
//
//        Attribute alCityAttribute_instance_6_Y= new Attribute("Y",instance_img6.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_6_Y);
//        AttributeValue alCityAttributeValue_instance_6_Y = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_6_Y,alCityAttribute_instance_6_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_6_Y);
//
//        Attribute alCityAttribute_instance_7_targetX= new Attribute("targetX",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_targetX);
//        AttributeValue alCityAttributeValue_instance_7_targetX = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_targetX,alCityAttribute_instance_7_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_7_targetX);
//
//        Attribute alCityAttribute_instance_7_targetY= new Attribute("targetY",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_targetY);
//        AttributeValue alCityAttributeValue_instance_7_targetY = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_7_targetY,alCityAttribute_instance_7_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_7_targetY);
//
//        Attribute alCityAttribute_instance_7_X= new Attribute("X",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_X);
//        AttributeValue alCityAttributeValue_instance_7_X = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_X,alCityAttribute_instance_7_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_7_X);
//
//        Attribute alCityAttribute_instance_7_Y= new Attribute("Y",instance_img7.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_7_Y);
//        AttributeValue alCityAttributeValue_instance_7_Y = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_7_Y,alCityAttribute_instance_7_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_7_Y);
//
//        Attribute alCityAttribute_instance_8_targetX= new Attribute("targetX",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_targetX);
//        AttributeValue alCityAttributeValue_instance_8_targetX = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_8_targetX,alCityAttribute_instance_8_targetX,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_8_targetX);
//
//        Attribute alCityAttribute_instance_8_targetY= new Attribute("targetY",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_targetY);
//        AttributeValue alCityAttributeValue_instance_8_targetY = new AttributeValue(null,2,null,null,null,null,alCityAttribute_instance_8_targetY,alCityAttribute_instance_8_targetY,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_8_targetY);
//
//        Attribute alCityAttribute_instance_8_X= new Attribute("X",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_X);
//        AttributeValue alCityAttributeValue_instance_8_X = new AttributeValue(null,3,null,null,null,null,alCityAttribute_instance_8_X,alCityAttribute_instance_8_X,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_8_X);
//
//        Attribute alCityAttribute_instance_8_Y= new Attribute("Y",instance_img8.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
//        attributeService.save(alCityAttribute_instance_8_Y);
//        AttributeValue alCityAttributeValue_instance_8_Y = new AttributeValue(null,1,null,null,null,null,alCityAttribute_instance_8_Y,alCityAttribute_instance_8_Y,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(alCityAttributeValue_instance_8_Y);
//
//
//        StringBuffer    puzzle_Rule_Condition = new StringBuffer("((e.x==X)&&((e.y==Y-1)||(e.y==Y+1)))  ||  ((e.y==Y)&&((e.x==X-1)||(e.x==X+1)))");
//        PLRule rule_for_move_objects_in_hash_image = new PLRule("Move object by click around empty object",1
//                ,puzzle_Rule_Condition,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleService.save(rule_for_move_objects_in_hash_image);
//
//
//        PLRuleEvent puzzleLevelRuleEvent_click = new PLRuleEvent("Click",PLRuleEventType.User_Event,UserEvent.Click.ordinal(),rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRuleEventService.save(puzzleLevelRuleEvent_click);
//
//
//        String objectId="objects[e.x][e.y]";
//        Integer ordering =1;
//        String actionName="move";
//        StringBuffer moveActionExpression_1=new StringBuffer(" parameters:[" +
//                "{name:'formRow' , value:'e.x'}," +
//                "{name:'fromCol' , value:'e.y'}," +
//                "{name:'moveType' , value:'jump'}] ");
//
//        PLRulePostAction rulePostAction_1_move = new PLRulePostAction(moveActionExpression_1,ordering,objectId,actionName,PLRulePostActionType.Call_Object_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_1_move);
//
//        Attribute rulePostAction_1_move_param_1 =new Attribute("actionId",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Long,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_1);
//
//        Long actionId_in_Unity=0L;
//        AttributeValue rulePostAction_1_move_param_1_value= new AttributeValue(null,null,actionId_in_Unity,null,null,null,rulePostAction_1_move_param_1,rulePostAction_1_move_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_1_value);
//
//        Attribute rulePostAction_1_move_param_2 =new Attribute("aSync",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_2);
//
//        AttributeValue rulePostAction_1_move_param_2_value= new AttributeValue(false,null,null,null,null,null,rulePostAction_1_move_param_2,rulePostAction_1_move_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_2_value);
//
//        Attribute rulePostAction_1_move_param_3 =new Attribute("FormRow",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_3);
//
//        AttributeValue rulePostAction_1_move_param_3_value= new AttributeValue(null,null,null,"e.x",null,null,rulePostAction_1_move_param_3,rulePostAction_1_move_param_3,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_3_value);
//
//
//        Attribute rulePostAction_1_move_param_4 =new Attribute("FromCol",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_4);
//
//        AttributeValue rulePostAction_1_move_param_4_value= new AttributeValue(null,null,null,"e.y",null,null,rulePostAction_1_move_param_4,rulePostAction_1_move_param_4,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_4_value);
//
//        Attribute rulePostAction_1_move_param_5 =new Attribute("toRow",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_5);
//
//        AttributeValue rulePostAction_1_move_param_5_value= new AttributeValue(null,null,null,"X",null,null,rulePostAction_1_move_param_5,rulePostAction_1_move_param_5,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_5_value);
//
//        Attribute rulePostAction_1_move_param_6 =new Attribute("toCol",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_6);
//
//        AttributeValue rulePostAction_1_move_param_6_value= new AttributeValue(null,null,null,"Y",null,null,rulePostAction_1_move_param_6,rulePostAction_1_move_param_6,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_6_value);
//
//        Attribute rulePostAction_1_move_param_7 =new Attribute("ObjectId",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_7);
//
//        AttributeValue rulePostAction_1_move_param_7_value= new AttributeValue(null,null,null,"objects[e.x][e.y]",null,null,rulePostAction_1_move_param_7,rulePostAction_1_move_param_7,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_7_value);
//
//        Attribute rulePostAction_1_move_param_8 =new Attribute("moveType",rulePostAction_1_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_1_move_param_8);
//
//        AttributeValue rulePostAction_1_move_param_8_value= new AttributeValue(null,null,null,"Jump",null,null,rulePostAction_1_move_param_8,rulePostAction_1_move_param_8,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_1_move_param_8_value);
//
//
//
//
//        objectId="objects[X][Y]";
//        ordering =2;
//        actionName="move";
//        StringBuffer moveActionExpression_2=new StringBuffer(" parameters:[" +
//                "{name:'actionId' , value:'move action id'}," +
//                "{name:'aSync' , value:'false'}," +
//                "{name:'formRow' , value:'e.x'}," +
//                "{name:'fromCol' , value:'e.y'}," +
//                "{name:'toRow' , value:'x'}," +
//                "{name:'toCol' , value:'y'}," +
//                "{name:'ObjectId' , value:'objects[e.x][e.y]'}," +
//                "{name:'moveType' , value:'jump'}] ");
//
//
//        PLRulePostAction rulePostAction_2_move = new PLRulePostAction(moveActionExpression_2,ordering,objectId,
//                actionName,PLRulePostActionType.Call_Object_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_2_move);
//
//
//        Attribute rulePostAction_2_move_param_1 =new Attribute("actionId",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Long,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_1);
//
//        actionId_in_Unity=0L;
//        AttributeValue rulePostAction_2_move_param_1_value= new AttributeValue(null,null,actionId_in_Unity,null,null,null,rulePostAction_2_move_param_1,rulePostAction_2_move_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_1_value);
//
//        Attribute rulePostAction_2_move_param_2 =new Attribute("aSync",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Boolean,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_2);
//
//        AttributeValue rulePostAction_2_move_param_2_value= new AttributeValue(false,null,null,null,null,null,rulePostAction_2_move_param_2,rulePostAction_2_move_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_2_value);
//
//        Attribute rulePostAction_2_move_param_3 =new Attribute("FormRow",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_3);
//
//        AttributeValue rulePostAction_2_move_param_3_value= new AttributeValue(null,null,null,"e.x",null,null,rulePostAction_2_move_param_3,rulePostAction_2_move_param_3,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_3_value);
//
//        Attribute rulePostAction_2_move_param_4 =new Attribute("FromCol",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_4);
//
//        AttributeValue rulePostAction_2_move_param_4_value= new AttributeValue(null,null,null,"e.y",null,null,rulePostAction_2_move_param_4,rulePostAction_2_move_param_4,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_4_value);
//
//        Attribute rulePostAction_2_move_param_5 =new Attribute("toRow",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_5);
//
//        AttributeValue rulePostAction_2_move_param_5_value= new AttributeValue(null,null,null,"X",null,null,rulePostAction_2_move_param_5,rulePostAction_2_move_param_5,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_5_value);
//
//        Attribute rulePostAction_2_move_param_6 =new Attribute("toCol",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_6);
//
//        AttributeValue rulePostAction_2_move_param_6_value= new AttributeValue(null,null,null,"Y",null,null,rulePostAction_2_move_param_6,rulePostAction_2_move_param_6,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_6_value);
//
//        Attribute rulePostAction_2_move_param_7 =new Attribute("ObjectId",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_7);
//
//        AttributeValue rulePostAction_2_move_param_7_value= new AttributeValue(null,null,null,"objects[e.x][e.y]",null,null,rulePostAction_2_move_param_7,rulePostAction_2_move_param_7,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_7_value);
//
//
//        Attribute rulePostAction_2_move_param_8 =new Attribute("moveType",rulePostAction_2_move.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_2_move_param_8);
//
//        AttributeValue rulePostAction_2_move_param_8_value= new AttributeValue(null,null,null,"Jump",null,null,rulePostAction_2_move_param_8,rulePostAction_2_move_param_8,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_2_move_param_8_value);
//
//
//
//
//        objectId=null;
//        ordering =3;
//        actionName="assignment";
//        StringBuffer assignActionExpression_3=new StringBuffer(" parameters:[" +
//                "{variable:'objects[X][Y].x'}," +
//                "{valueExperssion:'x'}" +
//                "]") ;
//
//
//
//
//        PLRulePostAction rulePostAction_3_assignment = new PLRulePostAction(assignActionExpression_3,ordering,objectId,actionName,PLRulePostActionType.Variable_Assignment_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_3_assignment);
//
//        Attribute rulePostAction_3_assignment_param_1 =new Attribute("variable",rulePostAction_3_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_3_assignment_param_1);
//
//        AttributeValue rulePostAction_3_assignment_param_1_value= new AttributeValue(null,null,null,"objects[X][Y].x",null,null,rulePostAction_3_assignment_param_1,rulePostAction_3_assignment_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_3_assignment_param_1_value);
//
//
//        Attribute rulePostAction_3_assignment_param_2 =new Attribute("valueExperssion",rulePostAction_3_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_3_assignment_param_2);
//
//        AttributeValue rulePostAction_3_assignment_param_2_value= new AttributeValue(null,null,null,"X",null,null,rulePostAction_3_assignment_param_2,rulePostAction_3_assignment_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_3_assignment_param_2_value);
//
//
//
//
//
//        objectId=null;
//        ordering =4;
//        actionName="assignment";
//
//        StringBuffer assignActionExpression_4=new StringBuffer(" parameters:[" +
//                "{variable:'objects[X][Y].x'}," +
//                "{valueExperssion:'Y'}" +
//                "]") ;
//
//        PLRulePostAction rulePostAction_4_assignment = new PLRulePostAction(assignActionExpression_4,ordering,objectId,actionName,
//                PLRulePostActionType.Variable_Assignment_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_4_assignment);
//
//        Attribute rulePostAction_4_assignment_param_1 =new Attribute("variable",rulePostAction_4_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_4_assignment_param_1);
//
//        AttributeValue rulePostAction_4_assignment_param_1_value= new AttributeValue(null,null,null,"objects[X][Y].x",null,null,rulePostAction_4_assignment_param_1,rulePostAction_4_assignment_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_4_assignment_param_1_value);
//
//
//        Attribute rulePostAction_4_assignment_param_2 =new Attribute("valueExperssion",rulePostAction_4_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_4_assignment_param_2);
//
//        AttributeValue rulePostAction_4_assignment_param_2_value= new AttributeValue(null,null,null,"Y",null,null,rulePostAction_4_assignment_param_2,rulePostAction_4_assignment_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_4_assignment_param_2_value);
//
//
//
//
//
//
//        objectId=null;
//        ordering =5;
//        actionName="assignment";
//
//        StringBuffer assignActionExpression_5=new StringBuffer(" parameters:[" +
//                "{variable:'X'}," +
//                "{valueExperssion:'e.x'}" +
//                "]") ;
//
//        PLRulePostAction rulePostAction_5_assignment = new PLRulePostAction(assignActionExpression_5,ordering,objectId,actionName
//                ,PLRulePostActionType.Variable_Assignment_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_5_assignment);
//
//
//        Attribute rulePostAction_5_assignment_param_1 =new Attribute("variable",rulePostAction_5_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_5_assignment_param_1);
//
//        AttributeValue rulePostAction_5_assignment_param_1_value= new AttributeValue(null,null,null,"X",null,null,rulePostAction_5_assignment_param_1,rulePostAction_5_assignment_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_5_assignment_param_1_value);
//
//
//        Attribute rulePostAction_5_assignment_param_2 =new Attribute("valueExperssion",rulePostAction_5_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_5_assignment_param_2);
//
//        AttributeValue rulePostAction_5_assignment_param_2_value= new AttributeValue(null,null,null,"e.x",null,null,rulePostAction_5_assignment_param_2,rulePostAction_5_assignment_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_5_assignment_param_2_value);
//
//
//
//
//
//
//        objectId=null;
//        ordering =6;
//        actionName="assignment";
//        StringBuffer assignActionExpression_6=new StringBuffer(" parameters:[" +
//                "{variable:'Y'}," +
//                "{valueExperssion:'e.y'}" +
//                "]") ;
//
//        PLRulePostAction rulePostAction_6_assignment = new PLRulePostAction(assignActionExpression_6,ordering,objectId,actionName,
//                PLRulePostActionType.Variable_Assignment_Action,rule_for_move_objects_in_hash_image,1L,now,now,admin_1,admin_1);
//        puzzleLevelRulePostActionService.save(rulePostAction_6_assignment);
//
//        Attribute rulePostAction_6_assignment_param_1 =new Attribute("variable",rulePostAction_6_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_6_assignment_param_1);
//
//        AttributeValue rulePostAction_6_assignment_param_1_value= new AttributeValue(null,null,null,"Y",null,null,rulePostAction_6_assignment_param_1,rulePostAction_6_assignment_param_1,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_6_assignment_param_1_value);
//
//
//        Attribute rulePostAction_6_assignment_param_2 =new Attribute("valueExperssion",rulePostAction_6_assignment.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(rulePostAction_6_assignment_param_2);
//
//        AttributeValue rulePostAction_6_assignment_param_2_value= new AttributeValue(null,null,null,"e.y",null,null,rulePostAction_6_assignment_param_2,rulePostAction_6_assignment_param_2,1L,now,now,admin_1,admin_1);
//        attributeValueService.save(rulePostAction_6_assignment_param_2_value);



    }





}
