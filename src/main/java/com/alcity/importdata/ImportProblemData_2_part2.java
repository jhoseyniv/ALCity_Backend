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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Iterator;


@Order(value=4)
@Component
public class ImportProblemData_2_part2 implements CommandLineRunner {

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
    PGObjectInstanceService pgObjectInstanceService;

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

        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);


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



        PuzzleCategory maze = puzzleCategoryService.findByValue("Maze");


        ObjectCategory objectCategory_Image = objectCategoryService.findByValue("Image");

        BinaryContent img_Image_binary_content = binaryContentService.findByfileName("image_object");
        BinaryContent yellow_space_image  = binaryContentService.findByfileName("yellow_space_image");
        BinaryContent black_space_image  = binaryContentService.findByfileName("black_space_image");
        BinaryContent white_space_image  = binaryContentService.findByfileName("white_space_image");
       PuzzleGroup_PuzzleObject  puzzleGroup_puzzleObject =  puzzleGroup_PuzzleObjectService.findByCode("Maze_ImageObject");

        PuzzleLevel   puzzleLevel_Maze = puzzleLevelService.findByCode("4546");

        PGObjectInstance img_16_10_0 = new PGObjectInstance("img_16_10_0",16,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_10_0);
        Attribute img_16_10_0_bgImage_property =new Attribute("bgImage",img_16_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_10_0_bgImage_property);
        AttributeValue img_16_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_10_0_bgImage_property,img_16_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_10_0_bgImage_property_value);



        PGObjectInstance img_17_10_0 = new PGObjectInstance("img_17_10_0",17,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_10_0);
        Attribute img_17_10_0_bgImage_property =new Attribute("bgImage",img_17_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_10_0_bgImage_property);
        AttributeValue img_17_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_10_0_bgImage_property,img_17_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_10_0_bgImage_property_value);
        Attribute img_17_10_0_locked_variable =new Attribute("Locked",img_17_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_10_0_locked_variable);
        AttributeValue img_17_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_10_0_locked_variable,img_17_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_10_0_locked_variable_value);



        PGObjectInstance img_18_10_1 = new PGObjectInstance("img_18_10_1",18,10,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_10_1);
        Attribute img_18_10_1_bgImage_property =new Attribute("bgImage",img_18_10_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_10_1_bgImage_property);
        AttributeValue img_18_10_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_10_1_bgImage_property,img_18_10_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_10_1_bgImage_property_value);



        PGObjectInstance img_18_10_0 = new PGObjectInstance("img_18_10_0",18,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_10_0);
        Attribute img_18_10_0_bgImage_property =new Attribute("bgImage",img_18_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_10_0_bgImage_property);
        AttributeValue img_18_10_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_10_0_bgImage_property,img_18_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_10_0_bgImage_property_value);



        PGObjectInstance img_19_10_0 = new PGObjectInstance("img_19_10_0",19,10,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_10_0);
        Attribute img_19_10_0_bgImage_property =new Attribute("bgImage",img_19_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_10_0_bgImage_property);
        AttributeValue img_19_10_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_10_0_bgImage_property,img_19_10_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_10_0_bgImage_property_value);
        Attribute img_19_10_0_locked_variable =new Attribute("Locked",img_19_10_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_10_0_locked_variable);
        AttributeValue img_19_10_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_10_0_locked_variable,img_19_10_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_10_0_locked_variable_value);


        PGObjectInstance img_1_11_0 = new PGObjectInstance("img_1_11_0",1,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_11_0);
        Attribute img_1_11_0_bgImage_property =new Attribute("bgImage",img_1_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_11_0_bgImage_property);
        AttributeValue img_1_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_11_0_bgImage_property,img_1_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_11_0_bgImage_property_value);
        Attribute img_1_11_0_locked_variable =new Attribute("Locked",img_1_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_11_0_locked_variable);
        AttributeValue img_1_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_11_0_locked_variable,img_1_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_11_0_locked_variable_value);



        PGObjectInstance img_2_11_1 = new PGObjectInstance("img_2_11_1",2,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_11_1);
        Attribute img_2_11_1_bgImage_property =new Attribute("bgImage",img_2_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_11_1_bgImage_property);
        AttributeValue img_2_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_11_1_bgImage_property,img_2_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_11_1_bgImage_property_value);


        PGObjectInstance img_2_11_0 = new PGObjectInstance("img_2_11_0",2,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_11_0);
        Attribute img_2_11_0_bgImage_property =new Attribute("bgImage",img_2_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_11_0_bgImage_property);
        AttributeValue img_2_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_11_0_bgImage_property,img_2_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_11_0_bgImage_property_value);


        PGObjectInstance img_3_11_0 = new PGObjectInstance("img_3_11_0",3,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_11_0);
        Attribute img_3_11_0_bgImage_property =new Attribute("bgImage",img_3_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_11_0_bgImage_property);
        AttributeValue img_3_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_11_0_bgImage_property,img_3_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_11_0_bgImage_property_value);
        Attribute img_3_11_0_locked_variable =new Attribute("Locked",img_3_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_11_0_locked_variable);
        AttributeValue img_3_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_11_0_locked_variable,img_3_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_11_0_locked_variable_value);

        PGObjectInstance img_4_11_1 = new PGObjectInstance("img_4_11_1",4,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_11_1);
        Attribute img_4_11_1_bgImage_property =new Attribute("bgImage",img_4_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_11_1_bgImage_property);
        AttributeValue img_4_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_11_1_bgImage_property,img_4_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_11_1_bgImage_property_value);

        PGObjectInstance img_4_11_0 = new PGObjectInstance("img_4_11_0",4,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_11_0);
        Attribute img_4_11_0_bgImage_property =new Attribute("bgImage",img_4_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_11_0_bgImage_property);
        AttributeValue img_4_11_0_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_11_0_bgImage_property,img_4_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_11_0_bgImage_property_value);


        PGObjectInstance img_5_11_1 = new PGObjectInstance("img_5_11_1",5,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_11_1);
        Attribute img_5_11_1_bgImage_property =new Attribute("bgImage",img_5_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_11_1_bgImage_property);
        AttributeValue img_5_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_5_11_1_bgImage_property,img_5_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_11_1_bgImage_property_value);


        PGObjectInstance img_5_11_0 = new PGObjectInstance("img_5_11_0",5,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_11_0);
        Attribute img_5_11_0_bgImage_property =new Attribute("bgImage",img_5_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_11_0_bgImage_property);
        AttributeValue img_5_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_5_11_0_bgImage_property,img_5_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_11_0_bgImage_property_value);



        PGObjectInstance img_6_11_1 = new PGObjectInstance("img_6_11_1",6,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_11_1);
        Attribute img_6_11_1_bgImage_property =new Attribute("bgImage",img_6_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_11_1_bgImage_property);
        AttributeValue img_6_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_11_1_bgImage_property,img_6_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_11_1_bgImage_property_value);



        PGObjectInstance img_6_11_0 = new PGObjectInstance("img_6_11_0",6,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_11_0);
        Attribute img_6_11_0_bgImage_property =new Attribute("bgImage",img_6_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_11_0_bgImage_property);
        AttributeValue img_6_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_11_0_bgImage_property,img_6_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_11_0_bgImage_property_value);




        PGObjectInstance img_7_11_0 = new PGObjectInstance("img_7_11_0",7,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_11_0);
        Attribute img_7_11_0_bgImage_property =new Attribute("bgImage",img_7_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_11_0_bgImage_property);
        AttributeValue img_7_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_11_0_bgImage_property,img_7_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_11_0_bgImage_property_value);
        Attribute img_7_11_0_locked_variable =new Attribute("Locked",img_7_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_11_0_locked_variable);
        AttributeValue img_7_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_11_0_locked_variable,img_7_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_11_0_locked_variable_value);


        PGObjectInstance img_8_11_1 = new PGObjectInstance("img_8_11_1",8,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_11_1);
        Attribute img_8_11_1_bgImage_property =new Attribute("bgImage",img_8_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_11_1_bgImage_property);
        AttributeValue img_8_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_11_1_bgImage_property,img_8_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_11_1_bgImage_property_value);



        PGObjectInstance img_8_11_0 = new PGObjectInstance("img_8_11_0",8,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_11_0);
        Attribute img_8_11_0_bgImage_property =new Attribute("bgImage",img_8_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_11_0_bgImage_property);
        AttributeValue img_8_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_11_0_bgImage_property,img_8_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_11_0_bgImage_property_value);



        PGObjectInstance img_9_11_0 = new PGObjectInstance("img_9_11_0",9,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_11_0);
        Attribute img_9_11_0_bgImage_property =new Attribute("bgImage",img_9_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_11_0_bgImage_property);
        AttributeValue img_9_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_9_11_0_bgImage_property,img_9_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_11_0_bgImage_property_value);
        Attribute img_9_11_0_locked_variable =new Attribute("Locked",img_9_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_11_0_locked_variable);
        AttributeValue img_9_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_9_11_0_locked_variable,img_9_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_11_0_locked_variable_value);



        PGObjectInstance img_10_11_0 = new PGObjectInstance("img_10_11_0",10,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_11_0);
        Attribute img_10_11_0_bgImage_property =new Attribute("bgImage",img_10_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_11_0_bgImage_property);
        AttributeValue img_10_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_10_11_0_bgImage_property,img_10_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_11_0_bgImage_property_value);
        Attribute img_10_11_0_locked_variable =new Attribute("Locked",img_10_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_11_0_locked_variable);
        AttributeValue img_10_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_10_11_0_locked_variable,img_10_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_11_0_locked_variable_value);



        PGObjectInstance img_11_11_0 = new PGObjectInstance("img_11_11_0",11,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_11_0);
        Attribute img_11_11_0_bgImage_property =new Attribute("bgImage",img_11_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_11_0_bgImage_property);
        AttributeValue img_11_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_11_11_0_bgImage_property,img_11_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_11_0_bgImage_property_value);
        Attribute img_11_11_0_locked_variable =new Attribute("Locked",img_11_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_11_0_locked_variable);
        AttributeValue img_11_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_11_11_0_locked_variable,img_11_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_11_0_locked_variable_value);



        PGObjectInstance img_12_11_0 = new PGObjectInstance("img_12_11_0",12,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_11_0);
        Attribute img_12_11_0_bgImage_property =new Attribute("bgImage",img_12_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_11_0_bgImage_property);
        AttributeValue img_12_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_12_11_0_bgImage_property,img_12_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_11_0_bgImage_property_value);
        Attribute img_12_11_0_locked_variable =new Attribute("Locked",img_12_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_11_0_locked_variable);
        AttributeValue img_12_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_12_11_0_locked_variable,img_12_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_11_0_locked_variable_value);




        PGObjectInstance img_13_11_0 = new PGObjectInstance("img_13_11_0",13,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_11_0);
        Attribute img_13_11_0_bgImage_property =new Attribute("bgImage",img_13_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_11_0_bgImage_property);
        AttributeValue img_13_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_11_0_bgImage_property,img_13_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_11_0_bgImage_property_value);
        Attribute img_13_11_0_locked_variable =new Attribute("Locked",img_13_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_11_0_locked_variable);
        AttributeValue img_13_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_11_0_locked_variable,img_13_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_11_0_locked_variable_value);



        PGObjectInstance img_14_11_0 = new PGObjectInstance("img_14_11_0",14,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_11_0);
        Attribute img_14_11_0_bgImage_property =new Attribute("bgImage",img_14_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_11_0_bgImage_property);
        AttributeValue img_14_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_14_11_0_bgImage_property,img_14_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_11_0_bgImage_property_value);
        Attribute img_14_11_0_locked_variable =new Attribute("Locked",img_14_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_11_0_locked_variable);
        AttributeValue img_14_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_14_11_0_locked_variable,img_14_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_11_0_locked_variable_value);



        PGObjectInstance img_15_11_0 = new PGObjectInstance("img_15_11_0",15,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_11_0);
        Attribute img_15_11_0_bgImage_property =new Attribute("bgImage",img_15_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_11_0_bgImage_property);
        AttributeValue img_15_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_11_0_bgImage_property,img_15_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_11_0_bgImage_property_value);
        Attribute img_15_11_0_locked_variable =new Attribute("Locked",img_15_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_11_0_locked_variable);
        AttributeValue img_15_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_11_0_locked_variable,img_15_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_11_0_locked_variable_value);




        PGObjectInstance img_16_11_1 = new PGObjectInstance("img_16_11_1",16,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_11_1);
        Attribute img_16_11_1_bgImage_property =new Attribute("bgImage",img_16_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_11_1_bgImage_property);
        AttributeValue img_16_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_11_1_bgImage_property,img_16_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_11_1_bgImage_property_value);


        PGObjectInstance img_16_11_0 = new PGObjectInstance("img_16_11_0",16,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_11_0);
        Attribute img_16_11_0_bgImage_property =new Attribute("bgImage",img_16_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_11_0_bgImage_property);
        AttributeValue img_16_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_11_0_bgImage_property,img_16_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_11_0_bgImage_property_value);



        PGObjectInstance img_17_11_0 = new PGObjectInstance("img_17_11_0",17,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_11_0);
        Attribute img_17_11_0_bgImage_property =new Attribute("bgImage",img_17_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_11_0_bgImage_property);
        AttributeValue img_17_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_11_0_bgImage_property,img_17_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_11_0_bgImage_property_value);
        Attribute img_17_11_0_locked_variable =new Attribute("Locked",img_17_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_11_0_locked_variable);
        AttributeValue img_17_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_11_0_locked_variable,img_17_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_11_0_locked_variable_value);


        PGObjectInstance img_18_11_1 = new PGObjectInstance("img_18_11_1",18,11,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_11_1);
        Attribute img_18_11_1_bgImage_property =new Attribute("bgImage",img_18_11_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_11_1_bgImage_property);
        AttributeValue img_18_11_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_11_1_bgImage_property,img_18_11_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_11_1_bgImage_property_value);


        PGObjectInstance img_18_11_0 = new PGObjectInstance("img_18_11_0",18,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_11_0);
        Attribute img_18_11_0_bgImage_property =new Attribute("bgImage",img_18_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_11_0_bgImage_property);
        AttributeValue img_18_11_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_11_0_bgImage_property,img_18_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_11_0_bgImage_property_value);



        PGObjectInstance img_19_11_0 = new PGObjectInstance("img_19_11_0",19,11,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_11_0);
        Attribute img_19_11_0_bgImage_property =new Attribute("bgImage",img_19_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_11_0_bgImage_property);
        AttributeValue img_19_11_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_11_0_bgImage_property,img_19_11_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_11_0_bgImage_property_value);
        Attribute img_19_11_0_locked_variable =new Attribute("Locked",img_19_11_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_11_0_locked_variable);
        AttributeValue img_19_11_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_11_0_locked_variable,img_19_11_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_11_0_locked_variable_value);



        PGObjectInstance img_1_12_0 = new PGObjectInstance("img_1_12_0",1,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_12_0);
        Attribute img_1_12_0_bgImage_property =new Attribute("bgImage",img_1_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_12_0_bgImage_property);
        AttributeValue img_1_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_12_0_bgImage_property,img_1_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_12_0_bgImage_property_value);
        Attribute img_1_12_0_locked_variable =new Attribute("Locked",img_1_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_12_0_locked_variable);
        AttributeValue img_1_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_12_0_locked_variable,img_1_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_12_0_locked_variable_value);




        PGObjectInstance img_2_12_1 = new PGObjectInstance("img_2_12_1",2,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_12_1);
        Attribute img_2_12_1_bgImage_property =new Attribute("bgImage",img_2_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_12_1_bgImage_property);
        AttributeValue img_2_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_12_1_bgImage_property,img_2_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_12_1_bgImage_property_value);



        PGObjectInstance img_2_12_0 = new PGObjectInstance("img_2_12_0",2,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_12_0);
        Attribute img_2_12_0_bgImage_property =new Attribute("bgImage",img_2_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_12_0_bgImage_property);
        AttributeValue img_2_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_12_0_bgImage_property,img_2_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_12_0_bgImage_property_value);


        PGObjectInstance img_3_12_0 = new PGObjectInstance("img_3_12_0",3,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_12_0);
        Attribute img_3_12_0_bgImage_property =new Attribute("bgImage",img_3_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_12_0_bgImage_property);
        AttributeValue img_3_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_12_0_bgImage_property,img_3_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_12_0_bgImage_property_value);
        Attribute img_3_12_0_locked_variable =new Attribute("Locked",img_3_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_12_0_locked_variable);
        AttributeValue img_3_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_12_0_locked_variable,img_3_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_12_0_locked_variable_value);



        PGObjectInstance img_4_12_1 = new PGObjectInstance("img_4_12_1",4,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_12_1);
        Attribute img_4_12_1_bgImage_property =new Attribute("bgImage",img_4_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_12_1_bgImage_property);
        AttributeValue img_4_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_12_1_bgImage_property,img_4_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_12_1_bgImage_property_value);




        PGObjectInstance img_4_12_0 = new PGObjectInstance("img_4_12_0",4,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_12_0);
        Attribute img_4_12_0_bgImage_property =new Attribute("bgImage",img_4_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_12_0_bgImage_property);
        AttributeValue img_4_12_0_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_12_0_bgImage_property,img_4_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_12_0_bgImage_property_value);


        PGObjectInstance img_5_12_0 = new PGObjectInstance("img_5_12_0",5,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_12_0);
        Attribute img_5_12_0_bgImage_property =new Attribute("bgImage",img_5_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_12_0_bgImage_property);
        AttributeValue img_5_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_12_0_bgImage_property,img_5_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_12_0_bgImage_property_value);
        Attribute img_5_12_0_locked_variable =new Attribute("Locked",img_5_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_12_0_locked_variable);
        AttributeValue img_5_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_12_0_locked_variable,img_5_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_12_0_locked_variable_value);


        PGObjectInstance img_6_12_1 = new PGObjectInstance("img_6_12_1",6,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_12_1);
        Attribute img_6_12_1_bgImage_property =new Attribute("bgImage",img_6_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_12_1_bgImage_property);
        AttributeValue img_6_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_12_1_bgImage_property,img_6_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_12_1_bgImage_property_value);


        PGObjectInstance img_6_12_0 = new PGObjectInstance("img_6_12_0",6,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_12_0);
        Attribute img_6_12_0_bgImage_property =new Attribute("bgImage",img_6_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_12_0_bgImage_property);
        AttributeValue img_6_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_12_0_bgImage_property,img_6_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_12_0_bgImage_property_value);


        PGObjectInstance img_7_12_0 = new PGObjectInstance("img_7_12_0",7,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_12_0);
        Attribute img_7_12_0_bgImage_property =new Attribute("bgImage",img_7_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_12_0_bgImage_property);
        AttributeValue img_7_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_12_0_bgImage_property,img_7_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_12_0_bgImage_property_value);
        Attribute img_7_12_0_locked_variable =new Attribute("Locked",img_7_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_12_0_locked_variable);
        AttributeValue img_7_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_12_0_locked_variable,img_7_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_12_0_locked_variable_value);



        PGObjectInstance img_8_12_1 = new PGObjectInstance("img_8_12_1",8,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_12_1);
        Attribute img_8_12_1_bgImage_property =new Attribute("bgImage",img_8_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_12_1_bgImage_property);
        AttributeValue img_8_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_12_1_bgImage_property,img_8_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_12_1_bgImage_property_value);


        PGObjectInstance img_8_12_0 = new PGObjectInstance("img_8_12_0",8,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_12_0);
        Attribute img_8_12_0_bgImage_property =new Attribute("bgImage",img_8_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_12_0_bgImage_property);
        AttributeValue img_8_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_12_0_bgImage_property,img_8_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_12_0_bgImage_property_value);

        PGObjectInstance img_9_12_1 = new PGObjectInstance("img_9_12_1",9,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_12_1);
        Attribute img_9_12_1_bgImage_property =new Attribute("bgImage",img_9_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_12_1_bgImage_property);
        AttributeValue img_9_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_9_12_1_bgImage_property,img_9_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_12_1_bgImage_property_value);

        PGObjectInstance img_9_12_0 = new PGObjectInstance("img_9_12_0",9,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_12_0);
        Attribute img_9_12_0_bgImage_property =new Attribute("bgImage",img_9_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_12_0_bgImage_property);
        AttributeValue img_9_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_9_12_0_bgImage_property,img_9_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_12_0_bgImage_property_value);

        PGObjectInstance img_10_12_1 = new PGObjectInstance("img_10_12_1",10,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_12_1);
        Attribute img_10_12_1_bgImage_property =new Attribute("bgImage",img_10_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_12_1_bgImage_property);
        AttributeValue img_10_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_10_12_1_bgImage_property,img_10_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_12_1_bgImage_property_value);

        PGObjectInstance img_10_12_0 = new PGObjectInstance("img_10_12_0",10,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_12_0);
        Attribute img_10_12_0_bgImage_property =new Attribute("bgImage",img_10_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_12_0_bgImage_property);
        AttributeValue img_10_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_10_12_0_bgImage_property,img_10_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_12_0_bgImage_property_value);



        PGObjectInstance img_11_12_1 = new PGObjectInstance("img_11_12_1",11,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_12_1);
        Attribute img_11_12_1_bgImage_property =new Attribute("bgImage",img_11_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_12_1_bgImage_property);
        AttributeValue img_11_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_11_12_1_bgImage_property,img_11_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_12_1_bgImage_property_value);


        PGObjectInstance img_11_12_0 = new PGObjectInstance("img_11_12_0",11,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_12_0);
        Attribute img_11_12_0_bgImage_property =new Attribute("bgImage",img_11_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_12_0_bgImage_property);
        AttributeValue img_11_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_11_12_0_bgImage_property,img_11_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_12_0_bgImage_property_value);


        PGObjectInstance img_12_12_1 = new PGObjectInstance("img_12_12_1",12,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_12_1);
        Attribute img_12_12_1_bgImage_property =new Attribute("bgImage",img_12_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_12_1_bgImage_property);
        AttributeValue img_12_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_12_12_1_bgImage_property,img_12_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_12_1_bgImage_property_value);

        PGObjectInstance img_12_12_0 = new PGObjectInstance("img_12_12_0",12,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_12_0);
        Attribute img_12_12_0_bgImage_property =new Attribute("bgImage",img_12_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_12_0_bgImage_property);
        AttributeValue img_12_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_12_12_0_bgImage_property,img_12_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_12_0_bgImage_property_value);



        PGObjectInstance img_13_12_0 = new PGObjectInstance("img_13_12_0",13,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_12_0);
        Attribute img_13_12_0_bgImage_property =new Attribute("bgImage",img_13_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_12_0_bgImage_property);
        AttributeValue img_13_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_12_0_bgImage_property,img_13_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_12_0_bgImage_property_value);
        Attribute img_13_12_0_locked_variable =new Attribute("Locked",img_13_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_12_0_locked_variable);
        AttributeValue img_13_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_12_0_locked_variable,img_13_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_12_0_locked_variable_value);


        PGObjectInstance img_14_12_1 = new PGObjectInstance("img_14_12_1",14,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_12_1);
        Attribute img_14_12_1_bgImage_property =new Attribute("bgImage",img_14_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_12_1_bgImage_property);
        AttributeValue img_14_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_12_1_bgImage_property,img_14_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_12_1_bgImage_property_value);


        PGObjectInstance img_14_12_0 = new PGObjectInstance("img_14_12_0",14,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_12_0);
        Attribute img_14_12_0_bgImage_property =new Attribute("bgImage",img_14_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_12_0_bgImage_property);
        AttributeValue img_14_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_12_0_bgImage_property,img_14_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_12_0_bgImage_property_value);

        PGObjectInstance img_15_12_1 = new PGObjectInstance("img_15_12_1",15,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_12_1);
        Attribute img_15_12_1_bgImage_property =new Attribute("bgImage",img_15_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_12_1_bgImage_property);
        AttributeValue img_15_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_15_12_1_bgImage_property,img_15_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_12_1_bgImage_property_value);

        PGObjectInstance img_15_12_0 = new PGObjectInstance("img_15_12_0",15,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_12_0);
        Attribute img_15_12_0_bgImage_property =new Attribute("bgImage",img_15_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_12_0_bgImage_property);
        AttributeValue img_15_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_15_12_0_bgImage_property,img_15_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_12_0_bgImage_property_value);

        PGObjectInstance img_16_12_1 = new PGObjectInstance("img_16_12_1",16,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_12_1);
        Attribute img_16_12_1_bgImage_property =new Attribute("bgImage",img_16_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_12_1_bgImage_property);
        AttributeValue img_16_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_12_1_bgImage_property,img_16_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_12_1_bgImage_property_value);

        PGObjectInstance img_16_12_0 = new PGObjectInstance("img_16_12_0",16,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_12_0);
        Attribute img_16_12_0_bgImage_property =new Attribute("bgImage",img_16_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_12_0_bgImage_property);
        AttributeValue img_16_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_12_0_bgImage_property,img_16_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_12_0_bgImage_property_value);


        PGObjectInstance img_17_12_0 = new PGObjectInstance("img_17_12_0",17,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_12_0);
        Attribute img_17_12_0_bgImage_property =new Attribute("bgImage",img_17_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_12_0_bgImage_property);
        AttributeValue img_17_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_12_0_bgImage_property,img_17_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_12_0_bgImage_property_value);
        Attribute img_17_12_0_locked_variable =new Attribute("Locked",img_17_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_12_0_locked_variable);
        AttributeValue img_17_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_12_0_locked_variable,img_17_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_12_0_locked_variable_value);


        PGObjectInstance img_18_12_1 = new PGObjectInstance("img_18_12_1",18,12,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_12_1);
        Attribute img_18_12_1_bgImage_property =new Attribute("bgImage",img_18_12_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_12_1_bgImage_property);
        AttributeValue img_18_12_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_12_1_bgImage_property,img_18_12_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_12_1_bgImage_property_value);


        PGObjectInstance img_18_12_0 = new PGObjectInstance("img_18_12_0",18,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_12_0);
        Attribute img_18_12_0_bgImage_property =new Attribute("bgImage",img_18_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_12_0_bgImage_property);
        AttributeValue img_18_12_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_12_0_bgImage_property,img_18_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_12_0_bgImage_property_value);



        PGObjectInstance img_19_12_0 = new PGObjectInstance("img_19_12_0",19,12,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_12_0);
        Attribute img_19_12_0_bgImage_property =new Attribute("bgImage",img_19_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_12_0_bgImage_property);
        AttributeValue img_19_12_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_12_0_bgImage_property,img_19_12_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_12_0_bgImage_property_value);
        Attribute img_19_12_0_locked_variable =new Attribute("Locked",img_19_12_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_12_0_locked_variable);
        AttributeValue img_19_12_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_12_0_locked_variable,img_19_12_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_12_0_locked_variable_value);



        PGObjectInstance img_1_13_0 = new PGObjectInstance("img_1_13_0",1,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_13_0);
        Attribute img_1_13_0_bgImage_property =new Attribute("bgImage",img_1_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_13_0_bgImage_property);
        AttributeValue img_1_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_13_0_bgImage_property,img_1_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_13_0_bgImage_property_value);
        Attribute img_1_13_0_locked_variable =new Attribute("Locked",img_1_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_13_0_locked_variable);
        AttributeValue img_1_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_13_0_locked_variable,img_1_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_13_0_locked_variable_value);


        PGObjectInstance img_2_13_1 = new PGObjectInstance("img_2_13_1",2,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_13_1);
        Attribute img_2_13_1_bgImage_property =new Attribute("bgImage",img_2_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_13_1_bgImage_property);
        AttributeValue img_2_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_13_1_bgImage_property,img_2_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_13_1_bgImage_property_value);


        PGObjectInstance img_2_13_0 = new PGObjectInstance("img_2_13_0",2,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_13_0);
        Attribute img_2_13_0_bgImage_property =new Attribute("bgImage",img_2_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_13_0_bgImage_property);
        AttributeValue img_2_13_0_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_13_0_bgImage_property,img_2_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_13_0_bgImage_property_value);

        PGObjectInstance img_3_13_0 = new PGObjectInstance("img_3_13_0",3,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_13_0);
        Attribute img_3_13_0_bgImage_property =new Attribute("bgImage",img_3_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_13_0_bgImage_property);
        AttributeValue img_3_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_13_0_bgImage_property,img_3_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_13_0_bgImage_property_value);
        Attribute img_3_13_0_locked_variable =new Attribute("Locked",img_3_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_13_0_locked_variable);
        AttributeValue img_3_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_13_0_locked_variable,img_3_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_13_0_locked_variable_value);



        PGObjectInstance img_4_13_1 = new PGObjectInstance("img_4_13_1",4,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_13_1);
        Attribute img_4_13_1_bgImage_property =new Attribute("bgImage",img_4_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_13_1_bgImage_property);
        AttributeValue img_4_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_13_1_bgImage_property,img_4_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_13_1_bgImage_property_value);


        PGObjectInstance img_4_13_0 = new PGObjectInstance("img_4_13_0",4,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_13_0);
        Attribute img_4_13_0_bgImage_property =new Attribute("bgImage",img_4_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_13_0_bgImage_property);
        AttributeValue img_4_13_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_13_0_bgImage_property,img_4_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_13_0_bgImage_property_value);


        PGObjectInstance img_5_13_0 = new PGObjectInstance("img_5_13_0",5,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_13_0);
        Attribute img_5_13_0_bgImage_property =new Attribute("bgImage",img_5_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_13_0_bgImage_property);
        AttributeValue img_5_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_13_0_bgImage_property,img_5_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_13_0_bgImage_property_value);
        Attribute img_5_13_0_locked_variable =new Attribute("Locked",img_5_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_13_0_locked_variable);
        AttributeValue img_5_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_13_0_locked_variable,img_5_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_13_0_locked_variable_value);


        PGObjectInstance img_6_13_1 = new PGObjectInstance("img_6_13_1",16,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_13_1);
        Attribute img_6_13_1_bgImage_property =new Attribute("bgImage",img_6_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_13_1_bgImage_property);
        AttributeValue img_6_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_13_1_bgImage_property,img_6_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_13_1_bgImage_property_value);


        PGObjectInstance img_6_13_0 = new PGObjectInstance("img_6_13_0",16,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_13_0);
        Attribute img_6_13_0_bgImage_property =new Attribute("bgImage",img_6_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_13_0_bgImage_property);
        AttributeValue img_6_13_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_13_0_bgImage_property,img_6_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_13_0_bgImage_property_value);


        PGObjectInstance img_7_13_0 = new PGObjectInstance("img_7_13_0",7,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_13_0);
        Attribute img_7_13_0_bgImage_property =new Attribute("bgImage",img_7_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_13_0_bgImage_property);
        AttributeValue img_7_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_13_0_bgImage_property,img_7_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_13_0_bgImage_property_value);
        Attribute img_7_13_0_locked_variable =new Attribute("Locked",img_7_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_13_0_locked_variable);
        AttributeValue img_7_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_13_0_locked_variable,img_7_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_13_0_locked_variable_value);


        PGObjectInstance img_8_13_0 = new PGObjectInstance("img_8_13_0",8,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_13_0);
        Attribute img_8_13_0_bgImage_property =new Attribute("bgImage",img_8_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_13_0_bgImage_property);
        AttributeValue img_8_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_8_13_0_bgImage_property,img_8_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_13_0_bgImage_property_value);
        Attribute img_8_13_0_locked_variable =new Attribute("Locked",img_8_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_13_0_locked_variable);
        AttributeValue img_8_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_8_13_0_locked_variable,img_8_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_13_0_locked_variable_value);


        PGObjectInstance img_9_13_1 = new PGObjectInstance("img_9_13_1",9,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_13_1);
        Attribute img_9_13_1_bgImage_property =new Attribute("bgImage",img_9_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_13_1_bgImage_property);
        AttributeValue img_9_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_9_13_1_bgImage_property,img_9_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_13_1_bgImage_property_value);

        PGObjectInstance img_9_13_0 = new PGObjectInstance("img_9_13_0",9,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_13_0);
        Attribute img_9_13_0_bgImage_property =new Attribute("bgImage",img_9_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_13_0_bgImage_property);
        AttributeValue img_9_13_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_9_13_0_bgImage_property,img_9_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_13_0_bgImage_property_value);


        PGObjectInstance img_10_13_0 = new PGObjectInstance("img_10_13_0",10,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_13_0);
        Attribute img_10_13_0_bgImage_property =new Attribute("bgImage",img_10_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_13_0_bgImage_property);
        AttributeValue img_10_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_10_13_0_bgImage_property,img_10_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_13_0_bgImage_property_value);
        Attribute img_10_13_0_locked_variable =new Attribute("Locked",img_10_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_13_0_locked_variable);
        AttributeValue img_10_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_10_13_0_locked_variable,img_10_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_13_0_locked_variable_value);


        PGObjectInstance img_11_13_0 = new PGObjectInstance("img_11_13_0",11,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_13_0);
        Attribute img_11_13_0_bgImage_property =new Attribute("bgImage",img_11_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_13_0_bgImage_property);
        AttributeValue img_11_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_11_13_0_bgImage_property,img_11_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_13_0_bgImage_property_value);
        Attribute img_11_13_0_locked_variable =new Attribute("Locked",img_11_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_13_0_locked_variable);
        AttributeValue img_11_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_11_13_0_locked_variable,img_11_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_13_0_locked_variable_value);

        PGObjectInstance img_12_13_0 = new PGObjectInstance("img_12_13_0",12,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_13_0);
        Attribute img_12_13_0_bgImage_property =new Attribute("bgImage",img_12_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_13_0_bgImage_property);
        AttributeValue img_12_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_12_13_0_bgImage_property,img_12_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_13_0_bgImage_property_value);
        Attribute img_12_13_0_locked_variable =new Attribute("Locked",img_12_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_13_0_locked_variable);
        AttributeValue img_12_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_12_13_0_locked_variable,img_12_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_13_0_locked_variable_value);

        PGObjectInstance img_13_13_0 = new PGObjectInstance("img_13_13_0",13,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_13_0);
        Attribute img_13_13_0_bgImage_property =new Attribute("bgImage",img_13_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_13_0_bgImage_property);
        AttributeValue img_13_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_13_0_bgImage_property,img_13_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_13_0_bgImage_property_value);
        Attribute img_13_13_0_locked_variable =new Attribute("Locked",img_13_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_13_0_locked_variable);
        AttributeValue img_13_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_13_0_locked_variable,img_13_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_13_0_locked_variable_value);

        PGObjectInstance img_14_13_1 = new PGObjectInstance("img_14_13_1",14,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_13_1);
        Attribute img_14_13_1_bgImage_property =new Attribute("bgImage",img_14_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_13_1_bgImage_property);
        AttributeValue img_14_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_13_1_bgImage_property,img_14_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_13_1_bgImage_property_value);

        PGObjectInstance img_14_13_0 = new PGObjectInstance("img_14_13_0",14,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_13_0);
        Attribute img_14_13_0_bgImage_property =new Attribute("bgImage",img_14_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_13_0_bgImage_property);
        AttributeValue img_14_13_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_13_0_bgImage_property,img_14_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_13_0_bgImage_property_value);


        PGObjectInstance img_15_13_0 = new PGObjectInstance("img_15_13_0",15,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_13_0);
        Attribute img_15_13_0_bgImage_property =new Attribute("bgImage",img_15_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_13_0_bgImage_property);
        AttributeValue img_15_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_13_0_bgImage_property,img_15_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_13_0_bgImage_property_value);
        Attribute img_15_13_0_locked_variable =new Attribute("Locked",img_15_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_13_0_locked_variable);
        AttributeValue img_15_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_13_0_locked_variable,img_15_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_13_0_locked_variable_value);

        PGObjectInstance img_16_13_0 = new PGObjectInstance("img_16_13_0",16,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_13_0);
        Attribute img_16_13_0_bgImage_property =new Attribute("bgImage",img_16_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_13_0_bgImage_property);
        AttributeValue img_16_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_16_13_0_bgImage_property,img_16_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_13_0_bgImage_property_value);
        Attribute img_16_13_0_locked_variable =new Attribute("Locked",img_16_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_13_0_locked_variable);
        AttributeValue img_16_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_16_13_0_locked_variable,img_16_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_13_0_locked_variable_value);


        PGObjectInstance img_17_13_0 = new PGObjectInstance("img_17_13_0",17,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_13_0);
        Attribute img_17_13_0_bgImage_property =new Attribute("bgImage",img_17_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_13_0_bgImage_property);
        AttributeValue img_17_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_13_0_bgImage_property,img_17_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_13_0_bgImage_property_value);
        Attribute img_17_13_0_locked_variable =new Attribute("Locked",img_17_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_13_0_locked_variable);
        AttributeValue img_17_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_13_0_locked_variable,img_17_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_13_0_locked_variable_value);


        PGObjectInstance img_18_13_1 = new PGObjectInstance("img_18_13_1",18,13,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_13_1);
        Attribute img_18_13_1_bgImage_property =new Attribute("bgImage",img_18_13_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_13_1_bgImage_property);
        AttributeValue img_18_13_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_13_1_bgImage_property,img_18_13_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_13_1_bgImage_property_value);

        PGObjectInstance img_18_13_0 = new PGObjectInstance("img_18_13_0",18,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_13_0);
        Attribute img_18_13_0_bgImage_property =new Attribute("bgImage",img_18_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_13_0_bgImage_property);
        AttributeValue img_18_13_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_13_0_bgImage_property,img_18_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_13_0_bgImage_property_value);

        PGObjectInstance img_19_13_0 = new PGObjectInstance("img_19_13_0",19,13,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_13_0);
        Attribute img_19_13_0_bgImage_property =new Attribute("bgImage",img_19_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_13_0_bgImage_property);
        AttributeValue img_19_13_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_13_0_bgImage_property,img_19_13_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_13_0_bgImage_property_value);
        Attribute img_19_13_0_locked_variable =new Attribute("Locked",img_19_13_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_13_0_locked_variable);
        AttributeValue img_19_13_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_13_0_locked_variable,img_19_13_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_13_0_locked_variable_value);

        PGObjectInstance img_1_14_0 = new PGObjectInstance("img_1_14_0",1,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_14_0);
        Attribute img_1_14_0_bgImage_property =new Attribute("bgImage",img_1_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_14_0_bgImage_property);
        AttributeValue img_1_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_14_0_bgImage_property,img_1_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_14_0_bgImage_property_value);
        Attribute img_1_14_0_locked_variable =new Attribute("Locked",img_1_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_14_0_locked_variable);
        AttributeValue img_1_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_14_0_locked_variable,img_1_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_14_0_locked_variable_value);


        PGObjectInstance img_2_14_1 = new PGObjectInstance("img_2_14_1",2,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_14_1);
        Attribute img_2_14_1_bgImage_property =new Attribute("bgImage",img_2_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_14_1_bgImage_property);
        AttributeValue img_2_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_14_1_bgImage_property,img_2_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_14_1_bgImage_property_value);

        PGObjectInstance img_2_14_0 = new PGObjectInstance("img_2_14_0",2,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_14_0);
        Attribute img_2_14_0_bgImage_property =new Attribute("bgImage",img_2_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_14_0_bgImage_property);
        AttributeValue img_2_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_14_0_bgImage_property,img_2_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_14_0_bgImage_property_value);

        PGObjectInstance img_3_14_0 = new PGObjectInstance("img_3_14_0",3,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_14_0);
        Attribute img_3_14_0_bgImage_property =new Attribute("bgImage",img_3_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_14_0_bgImage_property);
        AttributeValue img_3_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_14_0_bgImage_property,img_3_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_14_0_bgImage_property_value);
        Attribute img_3_14_0_locked_variable =new Attribute("Locked",img_3_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_14_0_locked_variable);
        AttributeValue img_3_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_14_0_locked_variable,img_3_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_14_0_locked_variable_value);

        PGObjectInstance img_4_14_1 = new PGObjectInstance("img_4_14_1",4,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_14_1);
        Attribute img_4_14_1_bgImage_property =new Attribute("bgImage",img_4_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_14_1_bgImage_property);
        AttributeValue img_4_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_14_1_bgImage_property,img_4_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_14_1_bgImage_property_value);

        PGObjectInstance img_4_14_0 = new PGObjectInstance("img_4_14_0",4,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_14_0);
        Attribute img_4_14_0_bgImage_property =new Attribute("bgImage",img_4_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_14_0_bgImage_property);
        AttributeValue img_4_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_14_0_bgImage_property,img_4_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_14_0_bgImage_property_value);

        PGObjectInstance img_5_14_0 = new PGObjectInstance("img_5_14_0",5,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_14_0);
        Attribute img_5_14_0_bgImage_property =new Attribute("bgImage",img_5_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_14_0_bgImage_property);
        AttributeValue img_5_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_14_0_bgImage_property,img_5_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_14_0_bgImage_property_value);
        Attribute img_5_14_0_locked_variable =new Attribute("Locked",img_5_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_14_0_locked_variable);
        AttributeValue img_5_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_14_0_locked_variable,img_5_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_14_0_locked_variable_value);

        PGObjectInstance img_6_14_1 = new PGObjectInstance("img_6_14_1",6,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_14_1);
        Attribute img_6_14_1_bgImage_property =new Attribute("bgImage",img_6_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_14_1_bgImage_property);
        AttributeValue img_6_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_14_1_bgImage_property,img_6_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_14_1_bgImage_property_value);

        PGObjectInstance img_6_14_0 = new PGObjectInstance("img_6_14_0",6,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_14_0);
        Attribute img_6_14_0_bgImage_property =new Attribute("bgImage",img_6_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_14_0_bgImage_property);
        AttributeValue img_6_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_14_0_bgImage_property,img_6_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_14_0_bgImage_property_value);


        PGObjectInstance img_7_14_1 = new PGObjectInstance("img_7_14_1",7,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_14_1);
        Attribute img_7_14_1_bgImage_property =new Attribute("bgImage",img_7_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_14_1_bgImage_property);
        AttributeValue img_7_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_7_14_1_bgImage_property,img_7_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_14_1_bgImage_property_value);


        PGObjectInstance img_7_14_0 = new PGObjectInstance("img_7_14_0",7,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_14_0);
        Attribute img_7_14_0_bgImage_property =new Attribute("bgImage",img_7_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_14_0_bgImage_property);
        AttributeValue img_7_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_7_14_0_bgImage_property,img_7_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_14_0_bgImage_property_value);

        PGObjectInstance img_8_14_1 = new PGObjectInstance("img_8_14_1",8,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_14_1);
        Attribute img_8_14_1_bgImage_property =new Attribute("bgImage",img_8_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_14_1_bgImage_property);
        AttributeValue img_8_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_14_1_bgImage_property,img_8_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_14_1_bgImage_property_value);


        PGObjectInstance img_8_14_0 = new PGObjectInstance("img_8_14_0",8,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_14_0);
        Attribute img_8_14_0_bgImage_property =new Attribute("bgImage",img_8_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_14_0_bgImage_property);
        AttributeValue img_8_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_14_0_bgImage_property,img_8_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_14_0_bgImage_property_value);

        PGObjectInstance img_9_14_1 = new PGObjectInstance("img_9_14_1",9,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_14_1);
        Attribute img_9_14_1_bgImage_property =new Attribute("bgImage",img_9_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_14_1_bgImage_property);
        AttributeValue img_9_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_9_14_1_bgImage_property,img_9_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_14_1_bgImage_property_value);

        PGObjectInstance img_9_14_0 = new PGObjectInstance("img_9_14_0",9,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_14_0);
        Attribute img_9_14_0_bgImage_property =new Attribute("bgImage",img_9_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_14_0_bgImage_property);
        AttributeValue img_9_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_9_14_0_bgImage_property,img_9_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_14_0_bgImage_property_value);

        PGObjectInstance img_10_14_1 = new PGObjectInstance("img_10_14_1",10,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_14_1);
        Attribute img_10_14_1_bgImage_property =new Attribute("bgImage",img_10_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_14_1_bgImage_property);
        AttributeValue img_10_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_10_14_1_bgImage_property,img_10_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_14_1_bgImage_property_value);


        PGObjectInstance img_10_14_0 = new PGObjectInstance("img_10_14_0",10,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_14_0);
        Attribute img_10_14_0_bgImage_property =new Attribute("bgImage",img_10_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_14_0_bgImage_property);
        AttributeValue img_10_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_10_14_0_bgImage_property,img_10_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_14_0_bgImage_property_value);

        PGObjectInstance img_11_14_1 = new PGObjectInstance("img_11_14_1",11,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_14_1);
        Attribute img_11_14_1_bgImage_property =new Attribute("bgImage",img_11_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_14_1_bgImage_property);
        AttributeValue img_11_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_11_14_1_bgImage_property,img_11_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_14_1_bgImage_property_value);

        PGObjectInstance img_11_14_0 = new PGObjectInstance("img_11_14_0",11,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_14_0);
        Attribute img_11_14_0_bgImage_property =new Attribute("bgImage",img_11_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_14_0_bgImage_property);
        AttributeValue img_11_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_11_14_0_bgImage_property,img_11_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_14_0_bgImage_property_value);

        PGObjectInstance img_12_14_1 = new PGObjectInstance("img_12_14_1",12,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_14_1);
        Attribute img_12_14_1_bgImage_property =new Attribute("bgImage",img_12_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_14_1_bgImage_property);
        AttributeValue img_12_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_12_14_1_bgImage_property,img_12_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_14_1_bgImage_property_value);

        PGObjectInstance img_12_14_0 = new PGObjectInstance("img_12_14_0",12,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_14_0);
        Attribute img_12_14_0_bgImage_property =new Attribute("bgImage",img_12_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_14_0_bgImage_property);
        AttributeValue img_12_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_12_14_0_bgImage_property,img_12_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_14_0_bgImage_property_value);

        PGObjectInstance img_13_14_0 = new PGObjectInstance("img_13_14_0",13,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_14_0);
        Attribute img_13_14_0_bgImage_property =new Attribute("bgImage",img_13_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_14_0_bgImage_property);
        AttributeValue img_13_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_14_0_bgImage_property,img_13_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_14_0_bgImage_property_value);
        Attribute img_13_14_0_locked_variable =new Attribute("Locked",img_13_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_14_0_locked_variable);
        AttributeValue img_13_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_14_0_locked_variable,img_13_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_14_0_locked_variable_value);

        PGObjectInstance img_14_14_1 = new PGObjectInstance("img_14_14_1",14,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_14_1);
        Attribute img_14_14_1_bgImage_property =new Attribute("bgImage",img_14_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_14_1_bgImage_property);
        AttributeValue img_14_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_14_1_bgImage_property,img_14_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_14_1_bgImage_property_value);

        PGObjectInstance img_14_14_0 = new PGObjectInstance("img_14_14_0",14,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_14_0);
        Attribute img_14_14_0_bgImage_property =new Attribute("bgImage",img_14_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_14_0_bgImage_property);
        AttributeValue img_14_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_14_0_bgImage_property,img_14_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_14_0_bgImage_property_value);

        PGObjectInstance img_15_14_1 = new PGObjectInstance("img_15_14_1",15,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_14_1);
        Attribute img_15_14_1_bgImage_property =new Attribute("bgImage",img_15_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_14_1_bgImage_property);
        AttributeValue img_15_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_15_14_1_bgImage_property,img_15_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_14_1_bgImage_property_value);

        PGObjectInstance img_15_14_0 = new PGObjectInstance("img_15_14_0",15,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_14_0);
        Attribute img_15_14_0_bgImage_property =new Attribute("bgImage",img_15_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_14_0_bgImage_property);
        AttributeValue img_15_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_15_14_0_bgImage_property,img_15_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_14_0_bgImage_property_value);


        PGObjectInstance img_16_14_1 = new PGObjectInstance("img_16_14_1",16,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_14_1);
        Attribute img_16_14_1_bgImage_property =new Attribute("bgImage",img_16_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_14_1_bgImage_property);
        AttributeValue img_16_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_14_1_bgImage_property,img_16_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_14_1_bgImage_property_value);


        PGObjectInstance img_16_14_0 = new PGObjectInstance("img_16_14_0",16,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_14_0);
        Attribute img_16_14_0_bgImage_property =new Attribute("bgImage",img_16_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_14_0_bgImage_property);
        AttributeValue img_16_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_14_0_bgImage_property,img_16_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_14_0_bgImage_property_value);

        PGObjectInstance img_17_14_0 = new PGObjectInstance("img_17_14_0",17,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_14_0);
        Attribute img_17_14_0_bgImage_property =new Attribute("bgImage",img_17_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_14_0_bgImage_property);
        AttributeValue img_17_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_14_0_bgImage_property,img_17_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_14_0_bgImage_property_value);
        Attribute img_17_14_0_locked_variable =new Attribute("Locked",img_17_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_14_0_locked_variable);
        AttributeValue img_17_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_14_0_locked_variable,img_17_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_14_0_locked_variable_value);

        PGObjectInstance img_18_14_1 = new PGObjectInstance("img_18_14_1",18,14,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_14_1);
        Attribute img_18_14_1_bgImage_property =new Attribute("bgImage",img_18_14_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_14_1_bgImage_property);
        AttributeValue img_18_14_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_14_1_bgImage_property,img_18_14_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_14_1_bgImage_property_value);

        PGObjectInstance img_18_14_0 = new PGObjectInstance("img_18_14_0",18,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_14_0);
        Attribute img_18_14_0_bgImage_property =new Attribute("bgImage",img_18_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_14_0_bgImage_property);
        AttributeValue img_18_14_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_14_0_bgImage_property,img_18_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_14_0_bgImage_property_value);

        PGObjectInstance img_19_14_0 = new PGObjectInstance("img_19_14_0",19,14,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_14_0);
        Attribute img_19_14_0_bgImage_property =new Attribute("bgImage",img_19_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_14_0_bgImage_property);
        AttributeValue img_19_14_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_14_0_bgImage_property,img_19_14_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_14_0_bgImage_property_value);
        Attribute img_19_14_0_locked_variable =new Attribute("Locked",img_19_14_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_14_0_locked_variable);
        AttributeValue img_19_14_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_14_0_locked_variable,img_19_14_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_14_0_locked_variable_value);

        PGObjectInstance img_1_15_0  = new PGObjectInstance("img_1_15_0",1,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_15_0);
        Attribute img_1_15_0_bgImage_property =new Attribute("bgImage",img_1_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_15_0_bgImage_property);
        AttributeValue img_1_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_15_0_bgImage_property,img_1_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_15_0_bgImage_property_value);
        Attribute img_1_15_0_locked_variable =new Attribute("Locked",img_1_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_15_0_locked_variable);
        AttributeValue img_1_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_15_0_locked_variable,img_1_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_15_0_locked_variable_value);

        PGObjectInstance img_2_15_1 = new PGObjectInstance("img_2_15_1",2,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_15_1);
        Attribute img_2_15_1_bgImage_property =new Attribute("bgImage",img_2_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_15_1_bgImage_property);
        AttributeValue img_2_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_15_1_bgImage_property,img_2_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_15_1_bgImage_property_value);

        PGObjectInstance img_2_15_0 = new PGObjectInstance("img_2_15_0",2,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_15_0);
        Attribute img_2_15_0_bgImage_property =new Attribute("bgImage",img_2_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_15_0_bgImage_property);
        AttributeValue img_2_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_15_0_bgImage_property,img_2_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_15_0_bgImage_property_value);

        PGObjectInstance img_3_15_0 = new PGObjectInstance("img_3_15_0",3,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_15_0);
        Attribute img_3_15_0_bgImage_property =new Attribute("bgImage",img_3_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_15_0_bgImage_property);
        AttributeValue img_3_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_15_0_bgImage_property,img_3_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_15_0_bgImage_property_value);
        Attribute img_3_15_0_locked_variable =new Attribute("Locked",img_3_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_15_0_locked_variable);
        AttributeValue img_3_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_15_0_locked_variable,img_3_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_15_0_locked_variable_value);


        PGObjectInstance img_4_15_1 = new PGObjectInstance("img_4_15_1",4,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_15_1);
        Attribute img_4_15_1_bgImage_property =new Attribute("bgImage",img_4_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_15_1_bgImage_property);
        AttributeValue img_4_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_15_1_bgImage_property,img_4_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_15_1_bgImage_property_value);


        PGObjectInstance img_4_15_0 = new PGObjectInstance("img_4_15_0",4,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_15_0);
        Attribute img_4_15_0_bgImage_property =new Attribute("bgImage",img_4_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_15_0_bgImage_property);
        AttributeValue img_4_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_15_0_bgImage_property,img_4_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_15_0_bgImage_property_value);

        PGObjectInstance img_5_15_0 = new PGObjectInstance("img_5_15_0",5,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_15_0);
        Attribute img_5_15_0_bgImage_property =new Attribute("bgImage",img_5_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_15_0_bgImage_property);
        AttributeValue img_5_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_15_0_bgImage_property,img_5_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_15_0_bgImage_property_value);
        Attribute img_5_15_0_locked_variable =new Attribute("Locked",img_5_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_15_0_locked_variable);
        AttributeValue img_5_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_15_0_locked_variable,img_5_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_15_0_locked_variable_value);

        PGObjectInstance img_6_15_0 = new PGObjectInstance("img_6_15_0",6,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_15_0);
        Attribute img_6_15_0_bgImage_property =new Attribute("bgImage",img_6_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_15_0_bgImage_property);
        AttributeValue img_6_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_6_15_0_bgImage_property,img_6_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_15_0_bgImage_property_value);
        Attribute img_6_15_0_locked_variable =new Attribute("Locked",img_6_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_15_0_locked_variable);
        AttributeValue img_6_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_6_15_0_locked_variable,img_6_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_15_0_locked_variable_value);


        PGObjectInstance img_7_15_0 = new PGObjectInstance("img_7_15_0",7,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_15_0);
        Attribute img_7_15_0_bgImage_property =new Attribute("bgImage",img_7_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_15_0_bgImage_property);
        AttributeValue img_7_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_15_0_bgImage_property,img_7_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_15_0_bgImage_property_value);
        Attribute img_7_15_0_locked_variable =new Attribute("Locked",img_7_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_15_0_locked_variable);
        AttributeValue img_7_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_15_0_locked_variable,img_7_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_15_0_locked_variable_value);

        PGObjectInstance img_8_15_0 = new PGObjectInstance("img_8_15_0",8,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_15_0);
        Attribute img_8_15_0_bgImage_property =new Attribute("bgImage",img_8_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_15_0_bgImage_property);
        AttributeValue img_8_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_8_15_0_bgImage_property,img_8_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_15_0_bgImage_property_value);
        Attribute img_8_15_0_locked_variable =new Attribute("Locked",img_8_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_15_0_locked_variable);
        AttributeValue img_8_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_8_15_0_locked_variable,img_8_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_15_0_locked_variable_value);

        PGObjectInstance img_9_15_0 = new PGObjectInstance("img_9_15_0",9,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_15_0);
        Attribute img_9_15_0_bgImage_property =new Attribute("bgImage",img_9_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_15_0_bgImage_property);
        AttributeValue img_9_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_9_15_0_bgImage_property,img_9_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_15_0_bgImage_property_value);
        Attribute img_9_15_0_locked_variable =new Attribute("Locked",img_9_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_15_0_locked_variable);
        AttributeValue img_9_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_9_15_0_locked_variable,img_9_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_15_0_locked_variable_value);

        PGObjectInstance img_10_15_0 = new PGObjectInstance("img_10_15_0",10,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_15_0);
        Attribute img_10_15_0_bgImage_property =new Attribute("bgImage",img_10_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_15_0_bgImage_property);
        AttributeValue img_10_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_10_15_0_bgImage_property,img_10_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_15_0_bgImage_property_value);
        Attribute img_10_15_0_locked_variable =new Attribute("Locked",img_10_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_15_0_locked_variable);
        AttributeValue img_10_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_10_15_0_locked_variable,img_10_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_15_0_locked_variable_value);


        PGObjectInstance img_11_15_0 = new PGObjectInstance("img_11_15_0",11,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_15_0);
        Attribute img_11_15_0_bgImage_property =new Attribute("bgImage",img_11_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_15_0_bgImage_property);
        AttributeValue img_11_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_11_15_0_bgImage_property,img_11_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_15_0_bgImage_property_value);
        Attribute img_11_15_0_locked_variable =new Attribute("Locked",img_11_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_15_0_locked_variable);
        AttributeValue img_11_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_11_15_0_locked_variable,img_11_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_15_0_locked_variable_value);


        PGObjectInstance img_12_15_1 = new PGObjectInstance("img_12_15_1",12,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_15_1);
        Attribute img_12_15_1_bgImage_property =new Attribute("bgImage",img_12_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_15_1_bgImage_property);
        AttributeValue img_12_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_12_15_1_bgImage_property,img_12_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_15_1_bgImage_property_value);

        PGObjectInstance img_12_15_0 = new PGObjectInstance("img_12_15_0",12,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_15_0);
        Attribute img_12_15_0_bgImage_property =new Attribute("bgImage",img_12_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_15_0_bgImage_property);
        AttributeValue img_12_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_12_15_0_bgImage_property,img_12_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_15_0_bgImage_property_value);

        PGObjectInstance img_13_15_1 = new PGObjectInstance("img_13_15_1",13,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_15_1);
        Attribute img_13_15_1_bgImage_property =new Attribute("bgImage",img_13_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_15_1_bgImage_property);
        AttributeValue img_13_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_13_15_1_bgImage_property,img_13_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_15_1_bgImage_property_value);

        PGObjectInstance img_13_15_0 = new PGObjectInstance("img_13_15_0",13,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_15_0);
        Attribute img_13_15_0_bgImage_property =new Attribute("bgImage",img_13_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_15_0_bgImage_property);
        AttributeValue img_13_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_13_15_0_bgImage_property,img_13_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_15_0_bgImage_property_value);

        PGObjectInstance img_14_15_1 = new PGObjectInstance("img_14_15_1",14,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_15_1);
        Attribute img_14_15_1_bgImage_property =new Attribute("bgImage",img_14_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_15_1_bgImage_property);
        AttributeValue img_14_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_15_1_bgImage_property,img_14_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_15_1_bgImage_property_value);

        PGObjectInstance img_14_15_0 = new PGObjectInstance("img_14_15_0",14,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_15_0);
        Attribute img_14_15_0_bgImage_property =new Attribute("bgImage",img_14_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_15_0_bgImage_property);
        AttributeValue img_14_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_15_0_bgImage_property,img_14_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_15_0_bgImage_property_value);


        PGObjectInstance img_15_15_0 = new PGObjectInstance("img_15_15_0",15,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_15_0);
        Attribute img_15_15_0_bgImage_property =new Attribute("bgImage",img_15_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_15_0_bgImage_property);
        AttributeValue img_15_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_15_0_bgImage_property,img_15_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_15_0_bgImage_property_value);
        Attribute img_15_15_0_locked_variable =new Attribute("Locked",img_15_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_15_0_locked_variable);
        AttributeValue img_15_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_15_0_locked_variable,img_15_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_15_0_locked_variable_value);

        PGObjectInstance img_16_15_1 = new PGObjectInstance("img_16_15_1",16,15,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_15_1);
        Attribute img_16_15_1_bgImage_property =new Attribute("bgImage",img_16_15_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_15_1_bgImage_property);
        AttributeValue img_16_15_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_15_1_bgImage_property,img_16_15_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_15_1_bgImage_property_value);

        PGObjectInstance img_16_15_0 = new PGObjectInstance("img_16_15_0",16,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_15_0);
        Attribute img_16_15_0_bgImage_property =new Attribute("bgImage",img_16_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_15_0_bgImage_property);
        AttributeValue img_16_15_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_15_0_bgImage_property,img_16_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_15_0_bgImage_property_value);

        PGObjectInstance img_17_15_0 = new PGObjectInstance("img_17_15_0",17,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_15_0);
        Attribute img_17_15_0_bgImage_property =new Attribute("bgImage",img_17_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_15_0_bgImage_property);
        AttributeValue img_17_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_15_0_bgImage_property,img_17_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_15_0_bgImage_property_value);
        Attribute img_17_15_0_locked_variable =new Attribute("Locked",img_17_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_15_0_locked_variable);
        AttributeValue img_17_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_15_0_locked_variable,img_17_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_15_0_locked_variable_value);

        PGObjectInstance img_18_15_0 = new PGObjectInstance("img_18_15_0",18,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_15_0);
        Attribute img_18_15_0_bgImage_property =new Attribute("bgImage",img_18_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_15_0_bgImage_property);
        AttributeValue img_18_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_18_15_0_bgImage_property,img_18_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_15_0_bgImage_property_value);
        Attribute img_18_15_0_locked_variable =new Attribute("Locked",img_18_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_15_0_locked_variable);
        AttributeValue img_18_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_18_15_0_locked_variable,img_18_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_15_0_locked_variable_value);

        PGObjectInstance img_19_15_0 = new PGObjectInstance("img_19_15_0",19,15,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_15_0);
        Attribute img_19_15_0_bgImage_property =new Attribute("bgImage",img_19_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_15_0_bgImage_property);
        AttributeValue img_19_15_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_15_0_bgImage_property,img_19_15_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_15_0_bgImage_property_value);
        Attribute img_19_15_0_locked_variable =new Attribute("Locked",img_19_15_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_15_0_locked_variable);
        AttributeValue img_19_15_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_15_0_locked_variable,img_19_15_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_15_0_locked_variable_value);

        PGObjectInstance img_1_16_0 = new PGObjectInstance("img_1_16_0",1,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_16_0);
        Attribute img_1_16_0_bgImage_property =new Attribute("bgImage",img_1_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_16_0_bgImage_property);
        AttributeValue img_1_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_16_0_bgImage_property,img_1_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_16_0_bgImage_property_value);
        Attribute img_1_16_0_locked_variable =new Attribute("Locked",img_1_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_16_0_locked_variable);
        AttributeValue img_1_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_16_0_locked_variable,img_1_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_16_0_locked_variable_value);


        PGObjectInstance img_2_16_1 = new PGObjectInstance("img_2_16_1",2,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_16_1);
        Attribute img_2_16_1_bgImage_property =new Attribute("bgImage",img_2_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_16_1_bgImage_property);
        AttributeValue img_2_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_16_1_bgImage_property,img_2_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_16_1_bgImage_property_value);


        PGObjectInstance img_2_16_0 = new PGObjectInstance("img_2_16_0",2,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_16_0);
        Attribute img_2_16_0_bgImage_property =new Attribute("bgImage",img_2_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_16_0_bgImage_property);
        AttributeValue img_2_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_16_0_bgImage_property,img_2_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_16_0_bgImage_property_value);


        PGObjectInstance img_3_16_0 = new PGObjectInstance("img_3_16_0",3,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_16_0);
        Attribute img_3_16_0_bgImage_property =new Attribute("bgImage",img_3_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_16_0_bgImage_property);
        AttributeValue img_3_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_16_0_bgImage_property,img_3_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_16_0_bgImage_property_value);
        Attribute img_3_16_0_locked_variable =new Attribute("Locked",img_3_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_16_0_locked_variable);
        AttributeValue img_3_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_16_0_locked_variable,img_3_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_16_0_locked_variable_value);


        PGObjectInstance img_4_16_1 = new PGObjectInstance("img_4_16_1",4,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_16_1);
        Attribute img_4_16_1_bgImage_property =new Attribute("bgImage",img_4_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_16_1_bgImage_property);
        AttributeValue img_4_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_16_1_bgImage_property,img_4_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_16_1_bgImage_property_value);


        PGObjectInstance img_4_16_0 = new PGObjectInstance("img_4_16_0",4,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_16_0);
        Attribute img_4_16_0_bgImage_property =new Attribute("bgImage",img_4_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_16_0_bgImage_property);
        AttributeValue img_4_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_16_0_bgImage_property,img_4_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_16_0_bgImage_property_value);


        PGObjectInstance img_5_16_1 = new PGObjectInstance("img_5_16_1",5,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_16_1);
        Attribute img_5_16_1_bgImage_property =new Attribute("bgImage",img_5_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_16_1_bgImage_property);
        AttributeValue img_5_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_5_16_1_bgImage_property,img_5_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_16_1_bgImage_property_value);


        PGObjectInstance img_5_16_0 = new PGObjectInstance("img_5_16_0",5,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_16_0);
        Attribute img_5_16_0_bgImage_property =new Attribute("bgImage",img_5_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_16_0_bgImage_property);
        AttributeValue img_5_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_5_16_0_bgImage_property,img_5_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_16_0_bgImage_property_value);


        PGObjectInstance img_6_16_1 = new PGObjectInstance("img_6_16_1",6,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_16_1);
        Attribute img_6_16_1_bgImage_property =new Attribute("bgImage",img_6_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_16_1_bgImage_property);
        AttributeValue img_6_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_16_1_bgImage_property,img_6_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_16_1_bgImage_property_value);




        PGObjectInstance img_6_16_0 = new PGObjectInstance("img_6_16_0",6,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_16_0);
        Attribute img_6_16_0_bgImage_property =new Attribute("bgImage",img_6_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_16_0_bgImage_property);
        AttributeValue img_6_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_16_0_bgImage_property,img_6_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_16_0_bgImage_property_value);


        PGObjectInstance img_7_16_0 = new PGObjectInstance("img_7_16_0",7,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_16_0);
        Attribute img_7_16_0_bgImage_property =new Attribute("bgImage",img_7_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_16_0_bgImage_property);
        AttributeValue img_7_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_16_0_bgImage_property,img_7_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_16_0_bgImage_property_value);
        Attribute img_7_16_0_locked_variable =new Attribute("Locked",img_7_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_16_0_locked_variable);
        AttributeValue img_7_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_16_0_locked_variable,img_7_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_16_0_locked_variable_value);


        PGObjectInstance img_8_16_1 = new PGObjectInstance("img_8_16_1",8,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_16_1);
        Attribute img_8_16_1_bgImage_property =new Attribute("bgImage",img_8_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_16_1_bgImage_property);
        AttributeValue img_8_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_16_1_bgImage_property,img_8_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_16_1_bgImage_property_value);




        PGObjectInstance img_8_16_0 = new PGObjectInstance("img_8_16_0",8,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_16_0);
        Attribute img_8_16_0_bgImage_property =new Attribute("bgImage",img_8_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_16_0_bgImage_property);
        AttributeValue img_8_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_16_0_bgImage_property,img_8_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_16_0_bgImage_property_value);


        PGObjectInstance img_9_16_1 = new PGObjectInstance("img_9_16_1",9,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_16_1);
        Attribute img_9_16_1_bgImage_property =new Attribute("bgImage",img_9_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_16_1_bgImage_property);
        AttributeValue img_9_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_9_16_1_bgImage_property,img_9_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_16_1_bgImage_property_value);


        PGObjectInstance img_9_16_0 = new PGObjectInstance("img_9_16_0",9,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_16_0);
        Attribute img_9_16_0_bgImage_property =new Attribute("bgImage",img_9_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_16_0_bgImage_property);
        AttributeValue img_9_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_9_16_0_bgImage_property,img_9_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_16_0_bgImage_property_value);


        PGObjectInstance img_10_16_1 = new PGObjectInstance("img_10_16_1",10,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_16_1);
        Attribute img_10_16_1_bgImage_property =new Attribute("bgImage",img_10_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_16_1_bgImage_property);
        AttributeValue img_10_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_10_16_1_bgImage_property,img_10_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_16_1_bgImage_property_value);



        PGObjectInstance img_10_16_0 = new PGObjectInstance("img_10_16_0",10,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_16_0);
        Attribute img_10_16_0_bgImage_property =new Attribute("bgImage",img_10_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_16_0_bgImage_property);
        AttributeValue img_10_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_10_16_0_bgImage_property,img_10_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_16_0_bgImage_property_value);

        PGObjectInstance img_11_16_1 = new PGObjectInstance("img_11_16_1",11,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_16_1);
        Attribute img_11_16_1_bgImage_property =new Attribute("bgImage",img_11_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_16_1_bgImage_property);
        AttributeValue img_11_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_11_16_1_bgImage_property,img_11_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_16_1_bgImage_property_value);




        PGObjectInstance img_11_16_0 = new PGObjectInstance("img_11_16_0",11,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_16_0);
        Attribute img_11_16_0_bgImage_property =new Attribute("bgImage",img_11_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_16_0_bgImage_property);
        AttributeValue img_11_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_11_16_0_bgImage_property,img_11_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_16_0_bgImage_property_value);


        PGObjectInstance img_12_16_1 = new PGObjectInstance("img_12_16_1",12,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_16_1);
        Attribute img_12_16_1_bgImage_property =new Attribute("bgImage",img_12_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_16_1_bgImage_property);
        AttributeValue img_12_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_12_16_1_bgImage_property,img_12_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_16_1_bgImage_property_value);

        PGObjectInstance img_12_16_0 = new PGObjectInstance("img_12_16_0",12,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_16_0);
        Attribute img_12_16_0_bgImage_property =new Attribute("bgImage",img_12_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_16_0_bgImage_property);
        AttributeValue img_12_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_12_16_0_bgImage_property,img_12_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_16_0_bgImage_property_value);


        PGObjectInstance img_13_16_0 = new PGObjectInstance("img_13_16_0",13,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_16_0);
        Attribute img_13_16_0_bgImage_property =new Attribute("bgImage",img_13_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_16_0_bgImage_property);
        AttributeValue img_13_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_16_0_bgImage_property,img_13_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_16_0_bgImage_property_value);
        Attribute img_13_16_0_locked_variable =new Attribute("Locked",img_13_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_16_0_locked_variable);
        AttributeValue img_13_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_16_0_locked_variable,img_13_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_16_0_locked_variable_value);



        PGObjectInstance img_14_16_1 = new PGObjectInstance("img_14_16_1",14,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_16_1);
        Attribute img_14_16_1_bgImage_property =new Attribute("bgImage",img_14_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_16_1_bgImage_property);
        AttributeValue img_14_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_16_1_bgImage_property,img_14_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_16_1_bgImage_property_value);

        PGObjectInstance img_14_16_0 = new PGObjectInstance("img_14_16_0",14,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_16_0);
        Attribute img_14_16_0_bgImage_property =new Attribute("bgImage",img_14_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_16_0_bgImage_property);
        AttributeValue img_14_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_16_0_bgImage_property,img_14_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_16_0_bgImage_property_value);

        PGObjectInstance img_15_16_0 = new PGObjectInstance("img_15_16_0",15,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_16_0);
        Attribute img_15_16_0_bgImage_property =new Attribute("bgImage",img_15_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_16_0_bgImage_property);
        AttributeValue img_15_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_16_0_bgImage_property,img_15_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_16_0_bgImage_property_value);
        Attribute img_15_16_0_locked_variable =new Attribute("Locked",img_15_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_16_0_locked_variable);
        AttributeValue img_15_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_16_0_locked_variable,img_15_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_16_0_locked_variable_value);



        PGObjectInstance img_16_16_1 = new PGObjectInstance("img_16_16_1",16,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_16_1);
        Attribute img_16_16_1_bgImage_property =new Attribute("bgImage",img_16_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_16_1_bgImage_property);
        AttributeValue img_16_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_16_1_bgImage_property,img_16_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_16_1_bgImage_property_value);


        PGObjectInstance img_16_16_0 = new PGObjectInstance("img_16_16_0",16,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_16_0);
        Attribute img_16_16_0_bgImage_property =new Attribute("bgImage",img_16_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_16_0_bgImage_property);
        AttributeValue img_16_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_16_0_bgImage_property,img_16_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_16_0_bgImage_property_value);


        PGObjectInstance img_17_16_1 = new PGObjectInstance("img_17_16_1",17,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_16_1);
        Attribute img_17_16_1_bgImage_property =new Attribute("bgImage",img_17_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_16_1_bgImage_property);
        AttributeValue img_17_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_17_16_1_bgImage_property,img_17_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_16_1_bgImage_property_value);


        PGObjectInstance img_17_16_0 = new PGObjectInstance("img_17_16_0",17,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_16_0);
        Attribute img_17_16_0_bgImage_property =new Attribute("bgImage",img_17_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_16_0_bgImage_property);
        AttributeValue img_17_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_17_16_0_bgImage_property,img_17_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_16_0_bgImage_property_value);






        PGObjectInstance img_18_16_1 = new PGObjectInstance("img_18_16_1",18,16,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_16_1);
        Attribute img_18_16_1_bgImage_property =new Attribute("bgImage",img_18_16_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_16_1_bgImage_property);
        AttributeValue img_18_16_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_16_1_bgImage_property,img_18_16_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_16_1_bgImage_property_value);


        PGObjectInstance img_18_16_0 = new PGObjectInstance("img_18_16_0",18,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_16_0);
        Attribute img_18_16_0_bgImage_property =new Attribute("bgImage",img_18_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_16_0_bgImage_property);
        AttributeValue img_18_16_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_16_0_bgImage_property,img_18_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_16_0_bgImage_property_value);


        PGObjectInstance img_19_16_0 = new PGObjectInstance("img_19_16_0",18,16,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_16_0);
        Attribute img_19_16_0_bgImage_property =new Attribute("bgImage",img_19_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_16_0_bgImage_property);
        AttributeValue img_19_16_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_16_0_bgImage_property,img_19_16_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_16_0_bgImage_property_value);
        Attribute img_19_16_0_locked_variable =new Attribute("Locked",img_19_16_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_16_0_locked_variable);
        AttributeValue img_19_16_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_16_0_locked_variable,img_19_16_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_16_0_locked_variable_value);


        PGObjectInstance img_1_17_0 = new PGObjectInstance("img_1_17_0",1,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_17_0);
        Attribute img_1_17_0_bgImage_property =new Attribute("bgImage",img_1_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_17_0_bgImage_property);
        AttributeValue img_1_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_17_0_bgImage_property,img_1_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_17_0_bgImage_property_value);
        Attribute img_1_17_0_locked_variable =new Attribute("Locked",img_1_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_17_0_locked_variable);
        AttributeValue img_1_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_17_0_locked_variable,img_1_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_17_0_locked_variable_value);


        PGObjectInstance img_2_17_1 = new PGObjectInstance("img_2_17_1",2,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_17_1);
        Attribute img_2_17_1_bgImage_property =new Attribute("bgImage",img_2_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_17_1_bgImage_property);
        AttributeValue img_2_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_17_1_bgImage_property,img_2_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_17_1_bgImage_property_value);


        PGObjectInstance img_2_17_0 = new PGObjectInstance("img_2_17_0",2,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_17_0);
        Attribute img_2_17_0_bgImage_property =new Attribute("bgImage",img_2_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_17_0_bgImage_property);
        AttributeValue img_2_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_17_0_bgImage_property,img_2_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_17_0_bgImage_property_value);


        PGObjectInstance img_3_17_1 = new PGObjectInstance("img_3_17_1",3,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_17_1);
        Attribute img_3_17_1_bgImage_property =new Attribute("bgImage",img_3_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_17_1_bgImage_property);
        AttributeValue img_3_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_3_17_1_bgImage_property,img_3_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_17_1_bgImage_property_value);


        PGObjectInstance img_3_17_0 = new PGObjectInstance("img_3_17_0",3,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_17_0);
        Attribute img_3_17_0_bgImage_property =new Attribute("bgImage",img_3_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_17_0_bgImage_property);
        AttributeValue img_3_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_3_17_0_bgImage_property,img_3_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_17_0_bgImage_property_value);



        PGObjectInstance img_4_17_1 = new PGObjectInstance("img_4_17_1",4,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_17_1);
        Attribute img_4_17_1_bgImage_property =new Attribute("bgImage",img_4_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_17_1_bgImage_property);
        AttributeValue img_4_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_17_1_bgImage_property,img_4_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_17_1_bgImage_property_value);



        PGObjectInstance img_4_17_0 = new PGObjectInstance("img_4_17_0",4,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_17_0);
        Attribute img_4_17_0_bgImage_property =new Attribute("bgImage",img_4_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_17_0_bgImage_property);
        AttributeValue img_4_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_17_0_bgImage_property,img_4_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_17_0_bgImage_property_value);


        PGObjectInstance img_5_17_0 = new PGObjectInstance("img_5_17_0",5,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_17_0);
        Attribute img_5_17_0_bgImage_property =new Attribute("bgImage",img_5_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_17_0_bgImage_property);
        AttributeValue img_5_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_17_0_bgImage_property,img_5_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_17_0_bgImage_property_value);
        Attribute img_5_17_0_locked_variable =new Attribute("Locked",img_5_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_17_0_locked_variable);
        AttributeValue img_5_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_17_0_locked_variable,img_5_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_17_0_locked_variable_value);



        PGObjectInstance img_6_17_0 = new PGObjectInstance("img_6_17_0",6,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_17_0);
        Attribute img_6_17_0_bgImage_property =new Attribute("bgImage",img_6_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_17_0_bgImage_property);
        AttributeValue img_6_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_6_17_0_bgImage_property,img_6_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_17_0_bgImage_property_value);
        Attribute img_6_17_0_locked_variable =new Attribute("Locked",img_6_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_17_0_locked_variable);
        AttributeValue img_6_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_6_17_0_locked_variable,img_6_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_17_0_locked_variable_value);



        PGObjectInstance img_7_17_0 = new PGObjectInstance("img_7_17_0",7,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_17_0);
        Attribute img_7_17_0_bgImage_property =new Attribute("bgImage",img_7_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_17_0_bgImage_property);
        AttributeValue img_7_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_17_0_bgImage_property,img_7_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_17_0_bgImage_property_value);
        Attribute img_7_17_0_locked_variable =new Attribute("Locked",img_7_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_17_0_locked_variable);
        AttributeValue img_7_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_17_0_locked_variable,img_7_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_17_0_locked_variable_value);



        PGObjectInstance img_8_17_1 = new PGObjectInstance("img_8_17_1",8,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_17_1);
        Attribute img_8_17_1_bgImage_property =new Attribute("bgImage",img_8_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_17_1_bgImage_property);
        AttributeValue img_8_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_17_1_bgImage_property,img_8_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_17_1_bgImage_property_value);


        PGObjectInstance img_8_17_0 = new PGObjectInstance("img_8_17_0",8,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_17_0);
        Attribute img_8_17_0_bgImage_property =new Attribute("bgImage",img_8_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_17_0_bgImage_property);
        AttributeValue img_8_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_17_0_bgImage_property,img_8_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_17_0_bgImage_property_value);



        PGObjectInstance img_9_17_0 = new PGObjectInstance("img_9_17_0",9,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_17_0);
        Attribute img_9_17_0_bgImage_property =new Attribute("bgImage",img_9_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_17_0_bgImage_property);
        AttributeValue img_9_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_9_17_0_bgImage_property,img_9_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_17_0_bgImage_property_value);
        Attribute img_9_17_0_locked_variable =new Attribute("Locked",img_9_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_17_0_locked_variable);
        AttributeValue img_9_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_9_17_0_locked_variable,img_9_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_17_0_locked_variable_value);



        PGObjectInstance img_10_17_0 = new PGObjectInstance("img_10_17_0",10,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_17_0);
        Attribute img_10_17_0_bgImage_property =new Attribute("bgImage",img_10_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_17_0_bgImage_property);
        AttributeValue img_10_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_10_17_0_bgImage_property,img_10_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_17_0_bgImage_property_value);
        Attribute img_10_17_0_locked_variable =new Attribute("Locked",img_10_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_17_0_locked_variable);
        AttributeValue img_10_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_10_17_0_locked_variable,img_10_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_17_0_locked_variable_value);



        PGObjectInstance img_11_17_0 = new PGObjectInstance("img_11_17_0",11,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_17_0);
        Attribute img_11_17_0_bgImage_property =new Attribute("bgImage",img_11_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_17_0_bgImage_property);
        AttributeValue img_11_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_11_17_0_bgImage_property,img_11_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_17_0_bgImage_property_value);
        Attribute img_11_17_0_locked_variable =new Attribute("Locked",img_11_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_17_0_locked_variable);
        AttributeValue img_11_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_11_17_0_locked_variable,img_11_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_17_0_locked_variable_value);



        PGObjectInstance img_12_17_0 = new PGObjectInstance("img_12_17_0",12,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_17_0);
        Attribute img_12_17_0_bgImage_property =new Attribute("bgImage",img_12_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_17_0_bgImage_property);
        AttributeValue img_12_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_12_17_0_bgImage_property,img_12_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_17_0_bgImage_property_value);
        Attribute img_12_17_0_locked_variable =new Attribute("Locked",img_12_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_17_0_locked_variable);
        AttributeValue img_12_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_12_17_0_locked_variable,img_12_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_17_0_locked_variable_value);



        PGObjectInstance img_13_17_0 = new PGObjectInstance("img_13_17_0",13,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_17_0);
        Attribute img_13_17_0_bgImage_property =new Attribute("bgImage",img_13_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_17_0_bgImage_property);
        AttributeValue img_13_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_17_0_bgImage_property,img_13_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_17_0_bgImage_property_value);
        Attribute img_13_17_0_locked_variable =new Attribute("Locked",img_13_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_17_0_locked_variable);
        AttributeValue img_13_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_17_0_locked_variable,img_13_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_17_0_locked_variable_value);



        PGObjectInstance img_14_17_1 = new PGObjectInstance("img_14_17_1",14,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_17_1);
        Attribute img_14_17_1_bgImage_property =new Attribute("bgImage",img_14_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_17_1_bgImage_property);
        AttributeValue img_14_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_17_1_bgImage_property,img_14_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_17_1_bgImage_property_value);



        PGObjectInstance img_14_17_0 = new PGObjectInstance("img_14_17_0",14,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_17_0);
        Attribute img_14_17_0_bgImage_property =new Attribute("bgImage",img_14_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_17_0_bgImage_property);
        AttributeValue img_14_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_17_0_bgImage_property,img_14_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_17_0_bgImage_property_value);



        PGObjectInstance img_15_17_0 = new PGObjectInstance("img_15_17_0",15,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_17_0);
        Attribute img_15_17_0_bgImage_property =new Attribute("bgImage",img_15_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_17_0_bgImage_property);
        AttributeValue img_15_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_17_0_bgImage_property,img_15_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_17_0_bgImage_property_value);
        Attribute img_15_17_0_locked_variable =new Attribute("Locked",img_15_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_17_0_locked_variable);
        AttributeValue img_15_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_17_0_locked_variable,img_15_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_17_0_locked_variable_value);



        PGObjectInstance img_16_17_0 = new PGObjectInstance("img_16_17_0",16,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_17_0);
        Attribute img_16_17_0_bgImage_property =new Attribute("bgImage",img_16_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_17_0_bgImage_property);
        AttributeValue img_16_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_16_17_0_bgImage_property,img_16_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_17_0_bgImage_property_value);
        Attribute img_16_17_0_locked_variable =new Attribute("Locked",img_16_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_17_0_locked_variable);
        AttributeValue img_16_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_16_17_0_locked_variable,img_16_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_17_0_locked_variable_value);


        PGObjectInstance img_17_17_1 = new PGObjectInstance("img_17_17_1",17,17,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_17_1);
        Attribute img_17_17_1_bgImage_property =new Attribute("bgImage",img_17_17_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_17_1_bgImage_property);
        AttributeValue img_17_17_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_17_17_1_bgImage_property,img_17_17_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_17_1_bgImage_property_value);



        PGObjectInstance img_17_17_0 = new PGObjectInstance("img_17_17_0",17,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_17_0);
        Attribute img_17_17_0_bgImage_property =new Attribute("bgImage",img_17_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_17_0_bgImage_property);
        AttributeValue img_17_17_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_17_17_0_bgImage_property,img_17_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_17_0_bgImage_property_value);



        PGObjectInstance img_18_17_0 = new PGObjectInstance("img_18_17_0",18,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_17_0);
        Attribute img_18_17_0_bgImage_property =new Attribute("bgImage",img_18_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_17_0_bgImage_property);
        AttributeValue img_18_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_18_17_0_bgImage_property,img_18_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_17_0_bgImage_property_value);
        Attribute img_18_17_0_locked_variable =new Attribute("Locked",img_18_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_17_0_locked_variable);
        AttributeValue img_18_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_18_17_0_locked_variable,img_18_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_17_0_locked_variable_value);



        PGObjectInstance img_19_17_0 = new PGObjectInstance("img_19_17_0",19,17,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_17_0);
        Attribute img_19_17_0_bgImage_property =new Attribute("bgImage",img_19_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_17_0_bgImage_property);
        AttributeValue img_19_17_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_17_0_bgImage_property,img_19_17_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_17_0_bgImage_property_value);
        Attribute img_19_17_0_locked_variable =new Attribute("Locked",img_19_17_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_17_0_locked_variable);
        AttributeValue img_19_17_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_17_0_locked_variable,img_19_17_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_17_0_locked_variable_value);



        PGObjectInstance img_1_18_0 = new PGObjectInstance("img_1_18_0",1,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_18_0);
        Attribute img_1_18_0_bgImage_property =new Attribute("bgImage",img_1_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_18_0_bgImage_property);
        AttributeValue img_1_18_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_18_0_bgImage_property,img_1_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_18_0_bgImage_property_value);
        Attribute img_1_18_0_locked_variable =new Attribute("Locked",img_1_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_18_0_locked_variable);
        AttributeValue img_1_18_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_18_0_locked_variable,img_1_18_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_18_0_locked_variable_value);



        PGObjectInstance img_2_18_1 = new PGObjectInstance("img_2_18_1",2,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_18_1);
        Attribute img_2_18_1_bgImage_property =new Attribute("bgImage",img_2_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_18_1_bgImage_property);
        AttributeValue img_2_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_2_18_1_bgImage_property,img_2_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_18_1_bgImage_property_value);


        PGObjectInstance img_2_18_0 = new PGObjectInstance("img_2_18_0",2,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_18_0);
        Attribute img_2_18_0_bgImage_property =new Attribute("bgImage",img_2_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_18_0_bgImage_property);
        AttributeValue img_2_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_2_18_0_bgImage_property,img_2_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_18_0_bgImage_property_value);


        PGObjectInstance img_3_18_0 = new PGObjectInstance("img_3_18_0",3,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_18_0);
        Attribute img_3_18_0_bgImage_property =new Attribute("bgImage",img_3_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_18_0_bgImage_property);
        AttributeValue img_3_18_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_18_0_bgImage_property,img_3_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_18_0_bgImage_property_value);
        Attribute img_3_18_0_locked_variable =new Attribute("Locked",img_3_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_18_0_locked_variable);
        AttributeValue img_3_18_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_18_0_locked_variable,img_3_18_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_18_0_locked_variable_value);


        PGObjectInstance img_4_18_1 = new PGObjectInstance("img_4_18_1",4,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_18_1);
        Attribute img_4_18_1_bgImage_property =new Attribute("bgImage",img_4_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_18_1_bgImage_property);
        AttributeValue img_4_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_4_18_1_bgImage_property,img_4_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_18_1_bgImage_property_value);

        PGObjectInstance img_4_18_0 = new PGObjectInstance("img_4_18_0",4,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_18_0);
        Attribute img_4_18_0_bgImage_property =new Attribute("bgImage",img_4_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_18_0_bgImage_property);
        AttributeValue img_4_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_4_18_0_bgImage_property,img_4_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_18_0_bgImage_property_value);


        PGObjectInstance img_5_18_1 = new PGObjectInstance("img_5_18_1",5,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_18_1);
        Attribute img_5_18_1_bgImage_property =new Attribute("bgImage",img_5_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_18_1_bgImage_property);
        AttributeValue img_5_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_5_18_1_bgImage_property,img_5_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_18_1_bgImage_property_value);


        PGObjectInstance img_5_18_0 = new PGObjectInstance("img_5_18_0",5,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_18_0);
        Attribute img_5_18_0_bgImage_property =new Attribute("bgImage",img_5_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_18_0_bgImage_property);
        AttributeValue img_5_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_5_18_0_bgImage_property,img_5_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_18_0_bgImage_property_value);


        PGObjectInstance img_6_18_1 = new PGObjectInstance("img_6_18_1",6,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_18_1);
        Attribute img_6_18_1_bgImage_property =new Attribute("bgImage",img_6_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_18_1_bgImage_property);
        AttributeValue img_6_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_6_18_1_bgImage_property,img_6_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_18_1_bgImage_property_value);

        PGObjectInstance img_6_18_0 = new PGObjectInstance("img_6_18_0",6,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_18_0);
        Attribute img_6_18_0_bgImage_property =new Attribute("bgImage",img_6_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_18_0_bgImage_property);
        AttributeValue img_6_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_6_18_0_bgImage_property,img_6_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_18_0_bgImage_property_value);


        PGObjectInstance img_7_18_1 = new PGObjectInstance("img_7_18_1",7,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_18_1);
        Attribute img_7_18_1_bgImage_property =new Attribute("bgImage",img_7_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_18_1_bgImage_property);
        AttributeValue img_7_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_7_18_1_bgImage_property,img_7_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_18_1_bgImage_property_value);

        PGObjectInstance img_7_18_0 = new PGObjectInstance("img_7_18_0",7,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_18_0);
        Attribute img_7_18_0_bgImage_property =new Attribute("bgImage",img_7_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_18_0_bgImage_property);
        AttributeValue img_7_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_7_18_0_bgImage_property,img_7_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_18_0_bgImage_property_value);

        PGObjectInstance img_8_18_1 = new PGObjectInstance("img_8_18_1",8,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_18_1);
        Attribute img_8_18_1_bgImage_property =new Attribute("bgImage",img_8_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_18_1_bgImage_property);
        AttributeValue img_8_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_8_18_1_bgImage_property,img_8_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_18_1_bgImage_property_value);

        PGObjectInstance img_8_18_0 = new PGObjectInstance("img_8_18_0",8,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_18_0);
        Attribute img_8_18_0_bgImage_property =new Attribute("bgImage",img_8_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_18_0_bgImage_property);
        AttributeValue img_8_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_8_18_0_bgImage_property,img_8_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_18_0_bgImage_property_value);


        PGObjectInstance img_9_18_1 = new PGObjectInstance("img_9_18_1",9,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_18_1);
        Attribute img_9_18_1_bgImage_property =new Attribute("bgImage",img_9_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_18_1_bgImage_property);
        AttributeValue img_9_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_9_18_1_bgImage_property,img_9_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_18_1_bgImage_property_value);


        PGObjectInstance img_9_18_0 = new PGObjectInstance("img_9_18_0",9,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_18_0);
        Attribute img_9_18_0_bgImage_property =new Attribute("bgImage",img_9_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_18_0_bgImage_property);
        AttributeValue img_9_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_9_18_0_bgImage_property,img_9_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_18_0_bgImage_property_value);



        PGObjectInstance img_10_18_1 = new PGObjectInstance("img_10_18_1",10,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_18_1);
        Attribute img_10_18_1_bgImage_property =new Attribute("bgImage",img_10_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_18_1_bgImage_property);
        AttributeValue img_10_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_10_18_1_bgImage_property,img_10_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_18_1_bgImage_property_value);


        PGObjectInstance img_10_18_0 = new PGObjectInstance("img_10_18_0",10,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_18_0);
        Attribute img_10_18_0_bgImage_property =new Attribute("bgImage",img_10_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_18_0_bgImage_property);
        AttributeValue img_10_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_10_18_0_bgImage_property,img_10_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_18_0_bgImage_property_value);


        PGObjectInstance img_11_18_1 = new PGObjectInstance("img_11_18_1",11,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_18_1);
        Attribute img_11_18_1_bgImage_property =new Attribute("bgImage",img_11_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_18_1_bgImage_property);
        AttributeValue img_11_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_11_18_1_bgImage_property,img_11_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_18_1_bgImage_property_value);


        PGObjectInstance img_11_18_0 = new PGObjectInstance("img_11_18_0",11,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_18_0);
        Attribute img_11_18_0_bgImage_property =new Attribute("bgImage",img_11_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_18_0_bgImage_property);
        AttributeValue img_11_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_11_18_0_bgImage_property,img_11_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_18_0_bgImage_property_value);


        PGObjectInstance img_12_18_1 = new PGObjectInstance("img_12_18_1",12,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_18_1);
        Attribute img_12_18_1_bgImage_property =new Attribute("bgImage",img_12_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_18_1_bgImage_property);
        AttributeValue img_12_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_12_18_1_bgImage_property,img_12_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_18_1_bgImage_property_value);


        PGObjectInstance img_12_18_0 = new PGObjectInstance("img_12_18_0",12,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_18_0);
        Attribute img_12_18_0_bgImage_property =new Attribute("bgImage",img_12_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_18_0_bgImage_property);
        AttributeValue img_12_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_12_18_0_bgImage_property,img_12_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_18_0_bgImage_property_value);


        PGObjectInstance img_13_18_0 = new PGObjectInstance("img_13_18_0",13,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_18_0);
        Attribute img_13_18_0_bgImage_property =new Attribute("bgImage",img_13_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_18_0_bgImage_property);
        AttributeValue img_13_18_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_18_0_bgImage_property,img_13_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_18_0_bgImage_property_value);
        Attribute img_13_18_0_locked_variable =new Attribute("Locked",img_13_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_18_0_locked_variable);
        AttributeValue img_13_18_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_18_0_locked_variable,img_13_18_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_18_0_locked_variable_value);


        PGObjectInstance img_14_18_1 = new PGObjectInstance("img_14_18_1",14,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_18_1);
        Attribute img_14_18_1_bgImage_property =new Attribute("bgImage",img_14_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_18_1_bgImage_property);
        AttributeValue img_14_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_14_18_1_bgImage_property,img_14_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_18_1_bgImage_property_value);


        PGObjectInstance img_14_18_0 = new PGObjectInstance("img_14_18_0",14,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_18_0);
        Attribute img_14_18_0_bgImage_property =new Attribute("bgImage",img_14_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_18_0_bgImage_property);
        AttributeValue img_14_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_14_18_0_bgImage_property,img_14_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_18_0_bgImage_property_value);



        PGObjectInstance img_15_18_1 = new PGObjectInstance("img_15_18_1",15,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_18_1);
        Attribute img_15_18_1_bgImage_property =new Attribute("bgImage",img_15_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_18_1_bgImage_property);
        AttributeValue img_15_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_15_18_1_bgImage_property,img_15_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_18_1_bgImage_property_value);


        PGObjectInstance img_15_18_0 = new PGObjectInstance("img_15_18_0",15,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_18_0);
        Attribute img_15_18_0_bgImage_property =new Attribute("bgImage",img_15_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_18_0_bgImage_property);
        AttributeValue img_15_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_15_18_0_bgImage_property,img_15_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_18_0_bgImage_property_value);



        PGObjectInstance img_16_18_1 = new PGObjectInstance("img_16_18_1",16,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_18_1);
        Attribute img_16_18_1_bgImage_property =new Attribute("bgImage",img_16_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_18_1_bgImage_property);
        AttributeValue img_16_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_16_18_1_bgImage_property,img_16_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_18_1_bgImage_property_value);


        PGObjectInstance img_16_18_0 = new PGObjectInstance("img_16_18_0",16,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_18_0);
        Attribute img_16_18_0_bgImage_property =new Attribute("bgImage",img_16_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_18_0_bgImage_property);
        AttributeValue img_16_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_16_18_0_bgImage_property,img_16_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_18_0_bgImage_property_value);





        PGObjectInstance img_17_18_1 = new PGObjectInstance("img_17_18_1",17,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_18_1);
        Attribute img_17_18_1_bgImage_property =new Attribute("bgImage",img_17_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_18_1_bgImage_property);
        AttributeValue img_17_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_17_18_1_bgImage_property,img_17_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_18_1_bgImage_property_value);



        PGObjectInstance img_17_18_0 = new PGObjectInstance("img_17_18_0",17,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_18_0);
        Attribute img_17_18_0_bgImage_property =new Attribute("bgImage",img_17_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_18_0_bgImage_property);
        AttributeValue img_17_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_17_18_0_bgImage_property,img_17_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_18_0_bgImage_property_value);


        PGObjectInstance img_18_18_1 = new PGObjectInstance("img_18_18_1",18,18,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_18_1);
        Attribute img_18_18_1_bgImage_property =new Attribute("bgImage",img_18_18_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_18_1_bgImage_property);
        AttributeValue img_18_18_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_18_1_bgImage_property,img_18_18_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_18_1_bgImage_property_value);



        PGObjectInstance img_18_18_0 = new PGObjectInstance("img_18_18_0",18,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_18_0);
        Attribute img_18_18_0_bgImage_property =new Attribute("bgImage",img_18_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_18_0_bgImage_property);
        AttributeValue img_18_18_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_18_0_bgImage_property,img_18_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_18_0_bgImage_property_value);


        PGObjectInstance img_19_18_0 = new PGObjectInstance("img_19_18_0",19,18,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_18_0);
        Attribute img_19_18_0_bgImage_property =new Attribute("bgImage",img_19_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_18_0_bgImage_property);
        AttributeValue img_19_18_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_18_0_bgImage_property,img_19_18_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_18_0_bgImage_property_value);
        Attribute img_19_18_0_locked_variable =new Attribute("Locked",img_19_18_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_18_0_locked_variable);
        AttributeValue img_19_18_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_18_0_locked_variable,img_19_18_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_18_0_locked_variable_value);


        PGObjectInstance img_1_19_0 = new PGObjectInstance("img_1_19_0",1,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_1_19_0);
        Attribute img_1_19_0_bgImage_property =new Attribute("bgImage",img_1_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_19_0_bgImage_property);
        AttributeValue img_1_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_1_19_0_bgImage_property,img_1_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_19_0_bgImage_property_value);
        Attribute img_1_19_0_locked_variable =new Attribute("Locked",img_1_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_1_19_0_locked_variable);
        AttributeValue img_1_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_1_19_0_locked_variable,img_1_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_1_19_0_locked_variable_value);


        PGObjectInstance img_2_19_0 = new PGObjectInstance("img_2_19_0",2,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_2_19_0);
        Attribute img_2_19_0_bgImage_property =new Attribute("bgImage",img_2_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_19_0_bgImage_property);
        AttributeValue img_2_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_2_19_0_bgImage_property,img_2_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_19_0_bgImage_property_value);
        Attribute img_2_19_0_locked_variable =new Attribute("Locked",img_2_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_2_19_0_locked_variable);
        AttributeValue img_2_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_2_19_0_locked_variable,img_2_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_2_19_0_locked_variable_value);


        PGObjectInstance img_3_19_0 = new PGObjectInstance("img_3_19_0",3,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_3_19_0);
        Attribute img_3_19_0_bgImage_property =new Attribute("bgImage",img_3_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_19_0_bgImage_property);
        AttributeValue img_3_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_3_19_0_bgImage_property,img_3_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_19_0_bgImage_property_value);
        Attribute img_3_19_0_locked_variable =new Attribute("Locked",img_3_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_3_19_0_locked_variable);
        AttributeValue img_3_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_3_19_0_locked_variable,img_3_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_3_19_0_locked_variable_value);

        PGObjectInstance img_4_19_0 = new PGObjectInstance("img_4_19_0",4,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_4_19_0);
        Attribute img_4_19_0_bgImage_property =new Attribute("bgImage",img_4_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_19_0_bgImage_property);
        AttributeValue img_4_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_4_19_0_bgImage_property,img_4_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_19_0_bgImage_property_value);
        Attribute img_4_19_0_locked_variable =new Attribute("Locked",img_4_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_4_19_0_locked_variable);
        AttributeValue img_4_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_4_19_0_locked_variable,img_4_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_4_19_0_locked_variable_value);

        PGObjectInstance img_5_19_0 = new PGObjectInstance("img_5_19_0",5,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_5_19_0);
        Attribute img_5_19_0_bgImage_property =new Attribute("bgImage",img_5_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_19_0_bgImage_property);
        AttributeValue img_5_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_5_19_0_bgImage_property,img_5_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_19_0_bgImage_property_value);
        Attribute img_5_19_0_locked_variable =new Attribute("Locked",img_5_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_5_19_0_locked_variable);
        AttributeValue img_5_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_5_19_0_locked_variable,img_5_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_5_19_0_locked_variable_value);

        PGObjectInstance img_6_19_0 = new PGObjectInstance("img_6_19_0",6,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_6_19_0);
        Attribute img_6_19_0_bgImage_property =new Attribute("bgImage",img_6_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_19_0_bgImage_property);
        AttributeValue img_6_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_6_19_0_bgImage_property,img_6_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_19_0_bgImage_property_value);
        Attribute img_6_19_0_locked_variable =new Attribute("Locked",img_6_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_6_19_0_locked_variable);
        AttributeValue img_6_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_6_19_0_locked_variable,img_6_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_6_19_0_locked_variable_value);

        PGObjectInstance img_7_19_0 = new PGObjectInstance("img_7_19_0",7,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_7_19_0);
        Attribute img_7_19_0_bgImage_property =new Attribute("bgImage",img_7_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_19_0_bgImage_property);
        AttributeValue img_7_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_7_19_0_bgImage_property,img_7_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_19_0_bgImage_property_value);
        Attribute img_7_19_0_locked_variable =new Attribute("Locked",img_7_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_7_19_0_locked_variable);
        AttributeValue img_7_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_7_19_0_locked_variable,img_7_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_7_19_0_locked_variable_value);

        PGObjectInstance img_8_19_0 = new PGObjectInstance("img_8_19_0",8,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_8_19_0);
        Attribute img_8_19_0_bgImage_property =new Attribute("bgImage",img_8_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_19_0_bgImage_property);
        AttributeValue img_8_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_8_19_0_bgImage_property,img_8_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_19_0_bgImage_property_value);
        Attribute img_8_19_0_locked_variable =new Attribute("Locked",img_8_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_8_19_0_locked_variable);
        AttributeValue img_8_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_8_19_0_locked_variable,img_8_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_8_19_0_locked_variable_value);

        PGObjectInstance img_9_19_0 = new PGObjectInstance("img_9_19_0",9,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_9_19_0);
        Attribute img_9_19_0_bgImage_property =new Attribute("bgImage",img_9_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_19_0_bgImage_property);
        AttributeValue img_9_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_9_19_0_bgImage_property,img_9_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_19_0_bgImage_property_value);
        Attribute img_9_19_0_locked_variable =new Attribute("Locked",img_9_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_9_19_0_locked_variable);
        AttributeValue img_9_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_9_19_0_locked_variable,img_9_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_9_19_0_locked_variable_value);


        PGObjectInstance img_10_19_0 = new PGObjectInstance("img_10_19_0",10,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_10_19_0);
        Attribute img_10_19_0_bgImage_property =new Attribute("bgImage",img_10_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_19_0_bgImage_property);
        AttributeValue img_10_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_10_19_0_bgImage_property,img_10_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_19_0_bgImage_property_value);
        Attribute img_10_19_0_locked_variable =new Attribute("Locked",img_10_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_10_19_0_locked_variable);
        AttributeValue img_10_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_10_19_0_locked_variable,img_10_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_10_19_0_locked_variable_value);

        PGObjectInstance img_11_19_0 = new PGObjectInstance("img_11_19_0",11,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_11_19_0);
        Attribute img_11_19_0_bgImage_property =new Attribute("bgImage",img_11_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_19_0_bgImage_property);
        AttributeValue img_11_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_11_19_0_bgImage_property,img_11_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_19_0_bgImage_property_value);
        Attribute img_11_19_0_locked_variable =new Attribute("Locked",img_11_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_11_19_0_locked_variable);
        AttributeValue img_11_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_11_19_0_locked_variable,img_11_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_11_19_0_locked_variable_value);

        PGObjectInstance img_12_19_0 = new PGObjectInstance("img_12_19_0",12,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_12_19_0);
        Attribute img_12_19_0_bgImage_property =new Attribute("bgImage",img_12_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_19_0_bgImage_property);
        AttributeValue img_12_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_12_19_0_bgImage_property,img_12_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_19_0_bgImage_property_value);
        Attribute img_12_19_0_locked_variable =new Attribute("Locked",img_12_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_12_19_0_locked_variable);
        AttributeValue img_12_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_12_19_0_locked_variable,img_12_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_12_19_0_locked_variable_value);



        PGObjectInstance img_13_19_0 = new PGObjectInstance("img_13_19_0",13,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_13_19_0);
        Attribute img_13_19_0_bgImage_property =new Attribute("bgImage",img_13_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_19_0_bgImage_property);
        AttributeValue img_13_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_13_19_0_bgImage_property,img_13_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_19_0_bgImage_property_value);
        Attribute img_13_19_0_locked_variable =new Attribute("Locked",img_13_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_13_19_0_locked_variable);
        AttributeValue img_13_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_13_19_0_locked_variable,img_13_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_13_19_0_locked_variable_value);


        PGObjectInstance img_14_19_0 = new PGObjectInstance("img_14_19_0",14,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_14_19_0);
        Attribute img_14_19_0_bgImage_property =new Attribute("bgImage",img_14_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_19_0_bgImage_property);
        AttributeValue img_14_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_14_19_0_bgImage_property,img_14_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_19_0_bgImage_property_value);
        Attribute img_14_19_0_locked_variable =new Attribute("Locked",img_14_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_14_19_0_locked_variable);
        AttributeValue img_14_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_14_19_0_locked_variable,img_14_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_14_19_0_locked_variable_value);


        PGObjectInstance img_15_19_0 = new PGObjectInstance("img_15_19_0",15,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_15_19_0);
        Attribute img_15_19_0_bgImage_property =new Attribute("bgImage",img_15_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_19_0_bgImage_property);
        AttributeValue img_15_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_15_19_0_bgImage_property,img_15_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_19_0_bgImage_property_value);
        Attribute img_15_19_0_locked_variable =new Attribute("Locked",img_15_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_15_19_0_locked_variable);
        AttributeValue img_15_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_15_19_0_locked_variable,img_15_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_15_19_0_locked_variable_value);


        PGObjectInstance img_16_19_0 = new PGObjectInstance("img_16_19_0",16,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_16_19_0);
        Attribute img_16_19_0_bgImage_property =new Attribute("bgImage",img_16_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_19_0_bgImage_property);
        AttributeValue img_16_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_16_19_0_bgImage_property,img_16_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_19_0_bgImage_property_value);
        Attribute img_16_19_0_locked_variable =new Attribute("Locked",img_16_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_16_19_0_locked_variable);
        AttributeValue img_16_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_16_19_0_locked_variable,img_16_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_16_19_0_locked_variable_value);


        PGObjectInstance img_17_19_0 = new PGObjectInstance("img_17_19_0",17,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_17_19_0);
        Attribute img_17_19_0_bgImage_property =new Attribute("bgImage",img_17_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_19_0_bgImage_property);
        AttributeValue img_17_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_17_19_0_bgImage_property,img_17_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_19_0_bgImage_property_value);
        Attribute img_17_19_0_locked_variable =new Attribute("Locked",img_17_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_17_19_0_locked_variable);
        AttributeValue img_17_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_17_19_0_locked_variable,img_17_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_17_19_0_locked_variable_value);

        PGObjectInstance img_18_19_1 = new PGObjectInstance("img_18_19_1",18,19,1,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_19_1);
        Attribute img_18_19_1_bgImage_property =new Attribute("bgImage",img_18_19_1.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_19_1_bgImage_property);
        AttributeValue img_18_19_1_bgImage_property_value= new AttributeValue(null,null,white_space_image.getId(),null,null,null,img_18_19_1_bgImage_property,img_18_19_1_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_19_1_bgImage_property_value);

        PGObjectInstance img_18_19_0 = new PGObjectInstance("img_18_19_0",18,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_18_19_0);
        Attribute img_18_19_0_bgImage_property =new Attribute("bgImage",img_18_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_18_19_0_bgImage_property);
        AttributeValue img_18_19_0_bgImage_property_value= new AttributeValue(null,null,yellow_space_image.getId(),null,null,null,img_18_19_0_bgImage_property,img_18_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_18_19_0_bgImage_property_value);

        PGObjectInstance img_19_19_0 = new PGObjectInstance("img_19_19_0",19,19,0,puzzleGroup_puzzleObject,puzzleLevel_Maze,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(img_19_19_0);
        Attribute img_19_19_0_bgImage_property =new Attribute("bgImage",img_19_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Property,alcity_Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_19_0_bgImage_property);
        AttributeValue img_19_19_0_bgImage_property_value= new AttributeValue(null,null,black_space_image.getId(),null,null,null,img_19_19_0_bgImage_property,img_19_19_0_bgImage_property,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_19_0_bgImage_property_value);
        Attribute img_19_19_0_locked_variable =new Attribute("Locked",img_19_19_0.getId(),AttributeOwnerType.PuzzleGroup_Object_Instance_Variable,alcity_Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(img_19_19_0_locked_variable);
        AttributeValue img_19_19_0_locked_variable_value= new AttributeValue(true,null,null,null,null,null,img_19_19_0_locked_variable,img_19_19_0_locked_variable,1L,now,now,admin_1,admin_1);
        attributeValueService.save(img_19_19_0_locked_variable_value);

    }

}
