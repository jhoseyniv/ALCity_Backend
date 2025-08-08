package com.alcity.importdata;

import com.alcity.ObjectManagmentApplication;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value=4)
@Component
public class ImportMemoryGameProblemData_4 implements CommandLineRunner {

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
   PGSkillLearningContentService puzzleSkillLearningContentService;
    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {

/*
        log.info("Start Application...Import Problem 3 X-O Probelm");
        System.out.println("...Import X-O Problem data");
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);

        Optional<AppMember> admin_1Optional = applicationMemberService.findByUsername("admin");
        Optional<AppMember> jalalHoseyniOptional = applicationMemberService.findByUsername("jalal");
        Optional<AppMember> Alireza_ZareOptional = applicationMemberService.findByUsername("alireza");
        Optional<LearningTopic> hashImage_Topic = learningTopicService.findByTitle("Hash Image");
        AppMember  admin_1=admin_1Optional.get();
        AppMember jalalHoseyni = jalalHoseyniOptional.get();
        AppMember Alireza_Zare = Alireza_ZareOptional.get();


        Optional<LearningTopic> Memory_Game_Topic = learningTopicService.findByTitle("Memory_Game");

        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill memory_booster = learningSkillService.findByValue("memory_booster");
       // Optional<WalletItem> alCoin100WalletItem = walletItemService.findByValue("al_coin_100");
        Optional<WalletItem> alCoin10WalletItem = walletItemService.findByValue("walletItem_3");

        Optional<Journey> journey_1 = journeyService.findByTitle("Journey_1");
        Optional<Journey> journey_2 = journeyService.findByTitle("Journey_2");

        JsonReader reader = Json.createReader(new FileReader("src/main/resources/images/Memory-Game/MemGame-ground.json"));
        JsonObject jsonObject = reader.readObject();
        ObjectMapper objectMapper = new ObjectMapper();
        BoardGraphicDTO boardGraphic = objectMapper.readValue(jsonObject.toString(), BoardGraphicDTO.class);


        byte[] pl_Icon_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","MemGame_icon.png");
        BinaryContent pl_Icon_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Icon",pl_Icon_Memory_Game_bytes.length,pl_Icon_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_Icon_Memory_Game_content);

        byte[] pl_pic_Memory_Game_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","MemGame.png");
        BinaryContent pl_pic_Memory_Game_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory_Game_Picture",pl_pic_Memory_Game_bytes.length,pl_pic_Memory_Game_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pl_pic_Memory_Game_content);

        BinaryContent puzzle_group_1_binary_content_image = binaryContentService.findByfileName("image_puzzle_group_matematic");

        byte[] flippedImage_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","flipimage.png");
        BinaryContent flippedImage_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Flip_Image_Picture",flippedImage_bytes.length,flippedImage_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(flippedImage_bytes_content);

        byte[] A_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","A.png");
        BinaryContent A_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"A_Picture",A_Image_bytes.length,A_Image_bytes,null,"A Text","","",BinaryContentType.Image);
        binaryContentService.save(A_Image_bytes_content);

        byte[] B_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","B.png");
        BinaryContent B_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"B_Picture",B_Image_bytes.length,B_Image_bytes,null,"B Text","","",BinaryContentType.Image);
        binaryContentService.save(B_Image_bytes_content);

        byte[] C_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","C.png");
        BinaryContent C_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"C_Picture",C_Image_bytes.length,C_Image_bytes,null,"C Text","","",BinaryContentType.Image);
        binaryContentService.save(C_Image_bytes_content);

        byte[] D_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","D.png");
        BinaryContent D_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"D_Picture",D_Image_bytes.length,D_Image_bytes,null,"D Text","","",BinaryContentType.Image);
        binaryContentService.save(D_Image_bytes_content);

        byte[] E_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","E.png");
        BinaryContent E_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"E_Picture",E_Image_bytes.length,E_Image_bytes,null,"E Text","","",BinaryContentType.Image);
        binaryContentService.save(E_Image_bytes_content);


        byte[] F_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","F.png");
        BinaryContent F_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"F_Picture",F_Image_bytes.length,F_Image_bytes,null,"F Text","","",BinaryContentType.Image);
        binaryContentService.save(F_Image_bytes_content);

        byte[] G_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","G.png");
        BinaryContent G_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"G_Picture",G_Image_bytes.length,G_Image_bytes,null,"G Text","","",BinaryContentType.Image);
        binaryContentService.save(G_Image_bytes_content);

        byte[] H_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","H.png");
        BinaryContent H_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"H_Picture",H_Image_bytes.length,H_Image_bytes,null,"H Text","","",BinaryContentType.Image);
        binaryContentService.save(H_Image_bytes_content);

        byte[] I_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","I.png");
        BinaryContent I_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"I_Picture",I_Image_bytes.length,I_Image_bytes,null,"I Text","","",BinaryContentType.Image);
        binaryContentService.save(I_Image_bytes_content);

        byte[] J_Image_bytes = ImageUtil.getImage("src/main/resources/images/Memory-Game/","J.png");
        BinaryContent J_Image_bytes_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"J_Picture",J_Image_bytes.length,J_Image_bytes,null,"J Text","","",BinaryContentType.Image);
        binaryContentService.save(J_Image_bytes_content);


        byte[] text_object_content = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",text_object_content.length,text_object_content,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(textObject_binary_content);

        Optional<PuzzleGroup>  IQ_Puzzle_Group =puzzleGroupService.findByTitle("IQ Puzzle Group");

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("Public");

        PuzzleLevel pl_Memory_Game= new PuzzleLevel(admin_1,now,1L,"Memory-Game","4546",8,15,5f,0f,2f,3f,IQ_Puzzle_Group.get(),PLDifficulty.Easy,PLStatus.Active,privacy_1,pl_Icon_Memory_Game_content,pl_Icon_Memory_Game_content,3L,now,now,admin_1,admin_1);
        pl_Memory_Game.setIcon(pl_pic_Memory_Game_content);
        pl_Memory_Game.setPicture(pl_pic_Memory_Game_content);
        puzzleLevelService.save(pl_Memory_Game);


        byte[] X_O_LearningContent_Image_bytes = ImageUtil.getImage("src/main/resources/images/X-O Problem/","puzzle_group_X_O_Image.png");
        BinaryContent pg_Memory_Game_learning_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Memory-Game", X_O_LearningContent_Image_bytes.length, X_O_LearningContent_Image_bytes,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(pg_Memory_Game_learning_content);

        Float xPos=0f;
        Float yPos=15f;
        Float zPos=0f;
        Float xRotation=90f;
        Float yRotation=0f;
        Float zRotation=0f;
        byte[] boardGraphic2 = ImageUtil.convertObjectToBytes(boardGraphic);
        Boolean zoom=false;
        Boolean pan=false;
        Boolean rotation=false;

        PLGround pl_Memory_Game_ground = new PLGround(4,5,xPos,yPos,zPos,xRotation,yRotation,zRotation,zoom,pan,rotation,pl_Memory_Game, boardGraphic2,1L,now,now,admin_1,admin_1);
        puzzleLevelGroundService.save(pl_Memory_Game_ground);

        PermitedPlayer player_1_puzzleLevel_X_O = new PermitedPlayer(Alireza_Zare,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        permitedPlayerRepository.save(player_1_puzzleLevel_X_O);

        LearningContent learningContent_memory=new LearningContent("help to Memory","this content is about Memory Games",pg_Memory_Game_learning_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_memory);


        LearningTopicInPL pllLearningTopic_1 = new LearningTopicInPL(pl_Memory_Game,Memory_Game_Topic.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        plLearningTopicService.save(pllLearningTopic_1);

        Float playScore =1f;
        Integer stars = ToolBox.getPuzzleLevelStars(playScore,pl_Memory_Game.getFirstStarScore(),pl_Memory_Game.getSecondStarScore(),pl_Memory_Game.getThirdStartScore());

        PlayHistory playHistory_1 = new PlayHistory(Alireza_Zare,pl_Memory_Game,now,now,15L,playScore,stars,1L,now,now,Alireza_Zare,Alireza_Zare);
        playHistoryService.save(playHistory_1);

        PGLearningSkillContent puzzleSkillLearningContent_1 = new PGLearningSkillContent(memory_booster,IQ_Puzzle_Group.get(),learningContent_memory,1L,now,now,admin_1,admin_1);
        puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

        PLGameInstance puzzleLevelGameInstance= new PLGameInstance(jalalHoseyni,pl_Memory_Game,now,now,GameStatus.Playing,1L,now,now,Alireza_Zare,Alireza_Zare);
        plGameInstanceService.save(puzzleLevelGameInstance);

        StringBuffer  condition_Objective_1 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)<=20)");

        PLObjective pl_objective_1 = new PLObjective("Finished in max number of possible steps","Finished in max number of possible steps",
                10f,2f,condition_Objective_1,memory_booster,
                                                                 alCoin10WalletItem.get(),pl_Memory_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_1);

        StringBuffer  condition_Objective_2 = new StringBuffer("equal(BoardVar(finished),true) & (BoardVar(steps)>20)");

        PLObjective pl_objective_2 = new PLObjective("Finished in more than max number of possible steps","Finished in morethan max number of possible steps",
                1f,2f,condition_Objective_2,memory_booster,
                alCoin10WalletItem.get(),pl_Memory_Game ,1L,now,now,admin_1,admin_1);
        plObjectiveService.save(pl_objective_2);


        Attribute pl_variable_finished =new Attribute("finished",pl_Memory_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_finished);
        AttributeValue pl_variable_finished_value= new AttributeValue(false,null,null,null,null,null,null,null,false,null,pl_variable_finished,1L,now,now,admin_1,admin_1,pl_Memory_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_finished_value);

        Attribute pl_variable_firstClick =new Attribute("firstClick",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_firstClick);

        AttributeValue pl_variable_firstClick_value= new AttributeValue(true,null,null,null,null,null,null,null,false,null,pl_variable_firstClick,1L,now,now,admin_1,admin_1,pl_Memory_Game.getId(), AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_firstClick_value);

        Attribute pl_variable_firstSelectedObject =new Attribute("firstSelectedObject",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_firstSelectedObject);

        AttributeValue pl_variable_firstSelectedObject_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_firstSelectedObject,1L,now,now,admin_1,admin_1,pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_firstSelectedObject_value);


        Attribute pl_variable_steps =new Attribute("steps",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_steps);

        AttributeValue pl_variable_steps_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_steps,1L,now,now,admin_1,admin_1,pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_steps_value);



        Attribute pl_variable_solved =new Attribute("solved",pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(pl_variable_solved);

        AttributeValue pl_variable_solved_value= new AttributeValue(null,0,null,null,null,null,null,null,false,null,pl_variable_solved,1L,now,now,admin_1,admin_1,pl_Memory_Game.getId(),AttributeOwnerType.Puzzle_Level_Variable);
        attributeValueService.save(pl_variable_solved_value);

        Optional<ALCityObject> imageObject01 =alCityObjectService.findByTitle("ImageObject01");


        ALCityObjectInPG imageObject01_in_memoryGame_puzzleGroup = new ALCityObjectInPG("Image MemoryGame Puzzle Group with Image Object","MemoryGame_ImageObject",IQ_Puzzle_Group.get(),imageObject01.get(),1L,now,now,admin_1,admin_1);
        alCityObjectInPGService.save(imageObject01_in_memoryGame_puzzleGroup);

        DTOUtil.copyActionFromTo(imageObject01.get().getId(), imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Object_Action_Handler_Parameter,
                AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter,actionService,POActionOwnerType.Object,POActionOwnerType.Puzzle_Group_Object,attributeService,attributeValueService);
        Collection<ObjectAction> actions = puzzleObjectActionService.findByOwnerObjectidAndPoActionOwnerType(imageObject01_in_memoryGame_puzzleGroup.getId(), POActionOwnerType.Puzzle_Group_Object);

        Iterator<ObjectAction> actionIterator = actions.iterator();
        while(actionIterator.hasNext()){
            ObjectAction objectAction = actionIterator.next();
            Renderer renderer = objectAction.getActionRenderer();

            if(renderer.getHandler().equalsIgnoreCase("ShowImage")){
                Collection<Attribute> attributes = attributeService.findByOwnerIdAndAttributeOwnerTypeNew(objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                Iterator<Attribute> attributeIterator = attributes.iterator();
                while(attributeIterator.hasNext()){
                    Attribute attribute = attributeIterator.next();
                    AttributeValue attributeValue = new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), flippedImage)",true,null,attribute,1L,now,now,admin_1,admin_1,objectAction.getId(),AttributeOwnerType.Puzzle_Group_Object_Action_Handler_Parameter);
                    attributeValueService.save(attributeValue);
                }
            }
        }

//        Collection<Attribute> property_3_image01_collection =attributeService.findByOwnerId(imageObject01.get().getId());
//        Attribute property_2_image01 = property_3_image01_collection.stream().filter(attribute -> attribute.getName().equalsIgnoreCase("property_3_image01")).findFirst().get();
//        AttributeValue  Image0object_property_0_value= new AttributeValue(null,null,null,"re- Iniit in puzzle group Object (state 3)",null,null,null,null,Boolean.FALSE,null,property_2_image01,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_0_value);
//
//        Collection<Attribute> property_4_image01_collection =attributeService.findByOwnerId(imageObject01.get().getId());
//        Attribute property_4_image01 = property_4_image01_collection.stream().filter(attribute -> attribute.getName().equalsIgnoreCase("property_4_image01")).findFirst().get();
//        AttributeValue  Image0object_property_4_value= new AttributeValue(null,null,null,"re- Iniit in puzzle group Object (state 4)",null,null,null,null,Boolean.FALSE,null,property_4_image01,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_4_value);


        Attribute ImageObject01_property_1 =new Attribute("bgImage",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_1);
        AttributeValue  Image0object_property_1_value= new AttributeValue(null,null,null,null,null,null,puzzle_group_1_binary_content_image.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value);


        Attribute ImageObject01_property_2 =new Attribute("flippedImage",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_property_2);
        AttributeValue  Image0object_property_2_value= new AttributeValue(null,null,null,null,null,null,flippedImage_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_2,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_2_value);

//        Attribute ImageObject01_property_5 =new Attribute("property_5_image01",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(ImageObject01_property_5);
//        AttributeValue  Image0object_property_5_value= new AttributeValue(null,null,null,"Define & Iniit in PG Object only (state 5)",null,null,null,null,Boolean.FALSE,null,ImageObject01_property_5,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_5_value);
//
//        Attribute ImageObject01_property_6 =new Attribute("property_6_image01",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property,DataType.Binary,1L,now,now,admin_1,admin_1);
//        attributeService.save(ImageObject01_property_6);
//        AttributeValue  Image0object_property_6_value= new AttributeValue(null,null,null,"Define & Iniit in PG Object  (state 6)",null,null,null,null,Boolean.FALSE,null,ImageObject01_property_6,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_6_value);



        Attribute ImageObject01_variable_1 =new Attribute("flipped",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_1);
        AttributeValue  ImageObject01_variable_1_value= new AttributeValue(false,null,null,null,null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_1,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_1_value);

        Attribute ImageObject01_variable_2 =new Attribute("value",imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(ImageObject01_variable_2);
        AttributeValue  ImageObject01_variable_2_value= new AttributeValue(null,null,null,"",null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,imageObject01_in_memoryGame_puzzleGroup.getId(),AttributeOwnerType.Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value);


        ALCityInstanceInPL instance_img0 = new ALCityInstanceInPL("instance_img0",1,1,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img0);

        AttributeValue ImageObject01_variable_2_value2 = new AttributeValue(null,null,null,"A",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value2);

        AttributeValue  Image0object_property_1_value2= new AttributeValue(null,null,null,null,null,null,A_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value2);


//        Attribute ImageObject01_variable_3 =new Attribute("ssss1",instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(ImageObject01_variable_3);
//        AttributeValue  ImageObject01_variable_3_value= new AttributeValue(null,null,null,"this is test",null,null,null,null,Boolean.FALSE,null,ImageObject01_variable_3,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
//        attributeValueService.save(ImageObject01_variable_3_value);

//        AttributeValue  Image0object_property_4_value_2= new AttributeValue(null,null,null,"re- Iniit in Instance (state 4)",null,null,null,null,Boolean.FALSE,null,property_4_image01,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_4_value_2);

//        Collection<Attribute> property_2_image01_collection =attributeService.findByOwnerId(imageObject01.get().getId());
//        Attribute property_2_image01_2 = property_2_image01_collection.stream().filter(attribute -> attribute.getName().equalsIgnoreCase("property_2_image01")).findFirst().get();
//        AttributeValue  property_2_image01_2_value= new AttributeValue(null,null,null,"re- Iniit in instance (state 2)",null,null,null,null,Boolean.FALSE,null,property_2_image01_2,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(property_2_image01_2_value);
//
//        AttributeValue  Image0object_property_6_value2= new AttributeValue(null,null,null,"Re init in PGO Instance  (state 6)",null,null,null,null,Boolean.FALSE,null,ImageObject01_property_6,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(Image0object_property_6_value2);
//
//
//        Attribute ImageObject01_property_7_instance_img0 =new Attribute("property_7_instance_img0",instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
//        attributeService.save(ImageObject01_property_7_instance_img0);
//        AttributeValue  ImageObject01_property_7_instance_img0_value= new AttributeValue(null,null,null,"property_7_instance_img0 define and init only (state 7)",null,null,null,null,Boolean.FALSE,null,ImageObject01_property_7_instance_img0,1L,now,now,admin_1,admin_1,instance_img0.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
//        attributeValueService.save(ImageObject01_property_7_instance_img0_value);


        ALCityInstanceInPL instance_img1 = new ALCityInstanceInPL("instance_img1",1,2,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img1);

        AttributeValue ImageObject01_variable_2_value3 = new AttributeValue(null,null,null,"F",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value3);

        AttributeValue  Image0object_property_1_value3= new AttributeValue(null,null,null,null,null,null,F_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img1.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value3);


        ALCityInstanceInPL instance_img2 = new ALCityInstanceInPL("instance_img2",1,3,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img2);

        AttributeValue ImageObject01_variable_2_value4 = new AttributeValue(null,null,null,"B",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value4);

        AttributeValue  Image0object_property_1_value4= new AttributeValue(null,null,null,null,null,null,B_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img2.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value4);


        ALCityInstanceInPL instance_img3 = new ALCityInstanceInPL("instance_img3",1,4,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img3);

        AttributeValue ImageObject01_variable_2_value5 = new AttributeValue(null,null,null,"H",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(ImageObject01_variable_2_value5);

        AttributeValue  Image0object_property_1_value5= new AttributeValue(null,null,null,null,null,null,H_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img3.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(Image0object_property_1_value5);



        ALCityInstanceInPL instance_img4 = new ALCityInstanceInPL("instance_img4",1,5,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img4);
        AttributeValue instance_img4_variable_2_value2 = new AttributeValue(null,null,null,"F",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img4_variable_2_value2);

        AttributeValue  instance_img4_property_1_value2= new AttributeValue(null,null,null,null,null,null,F_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img4.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img4_property_1_value2);



        ALCityInstanceInPL instance_img5 = new ALCityInstanceInPL("instance_img5",2,1,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img5);
        AttributeValue instance_img5_variable_2_value2 = new AttributeValue(null,null,null,"C",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img5_variable_2_value2);

        AttributeValue  instance_img5_property_1_value2= new AttributeValue(null,null,null,null,null,null,C_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img5.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img5_property_1_value2);


        ALCityInstanceInPL instance_img6 = new ALCityInstanceInPL("instance_img6",2,2,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img6);
        AttributeValue instance_img6_variable_2_value2 = new AttributeValue(null,null,null,"G",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img6_variable_2_value2);

        AttributeValue  instance_img6_property_1_value2= new AttributeValue(null,null,null,null,null,null,G_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img6.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img6_property_1_value2);



        ALCityInstanceInPL instance_img7 = new ALCityInstanceInPL("instance_img7",2,3,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img7);
        AttributeValue instance_img7_variable_2_value2 = new AttributeValue(null,null,null,"H",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img7_variable_2_value2);

        AttributeValue  instance_img7_property_1_value2= new AttributeValue(null,null,null,null,null,null,H_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img7.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img7_property_1_value2);


        ALCityInstanceInPL instance_img8 = new ALCityInstanceInPL("instance_img8",2,4,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img8);
        AttributeValue instance_img8_variable_2_value2 = new AttributeValue(null,null,null,"G",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img8_variable_2_value2);

        AttributeValue  instance_img8_property_1_value2= new AttributeValue(null,null,null,null,null,null,G_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img8.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img8_property_1_value2);


        ALCityInstanceInPL instance_img9 = new ALCityInstanceInPL("instance_img9",2,5,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img9);
        AttributeValue instance_img9_variable_2_value2 = new AttributeValue(null,null,null,"E",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img9.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img9_variable_2_value2);

        AttributeValue  instance_img9_property_1_value2= new AttributeValue(null,null,null,null,null,null,E_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img9.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img9_property_1_value2);



        ALCityInstanceInPL instance_img10 = new ALCityInstanceInPL("instance_img10",3,1,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img10);
        AttributeValue instance_img10_variable_2_value2 = new AttributeValue(null,null,null,"E",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img10.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img10_variable_2_value2);

        AttributeValue  instance_img10_property_1_value2= new AttributeValue(null,null,null,null,null,null,E_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img10.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img10_property_1_value2);



        ALCityInstanceInPL instance_img11 = new ALCityInstanceInPL("instance_img11",3,2,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img11);
        AttributeValue instance_img11_variable_2_value2 = new AttributeValue(null,null,null,"D",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img11.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img11_variable_2_value2);

        AttributeValue  instance_img11_property_1_value2= new AttributeValue(null,null,null,null,null,null,D_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img11.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img11_property_1_value2);


        ALCityInstanceInPL instance_img12 = new ALCityInstanceInPL("instance_img12",3,3,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img12);
        AttributeValue instance_img12_variable_2_value2 = new AttributeValue(null,null,null,"I",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img12.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img12_variable_2_value2);

        AttributeValue  instance_img12_property_1_value2= new AttributeValue(null,null,null,null,null,null,I_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img12.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img12_property_1_value2);


        ALCityInstanceInPL instance_img13 = new ALCityInstanceInPL("instance_img13",3,4,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img13);
        AttributeValue instance_img13_variable_2_value2 = new AttributeValue(null,null,null,"C",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img13.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img13_variable_2_value2);

        AttributeValue  instance_img13_property_1_value2= new AttributeValue(null,null,null,null,null,null,C_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img13.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img13_property_1_value2);



        ALCityInstanceInPL instance_img14 = new ALCityInstanceInPL("instance_img14",3,5,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img14);
        AttributeValue instance_img14_variable_2_value2 = new AttributeValue(null,null,null,"J",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img14.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img14_variable_2_value2);

        AttributeValue  instance_img14_property_1_value2= new AttributeValue(null,null,null,null,null,null,J_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img14.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img14_property_1_value2);


        ALCityInstanceInPL instance_img15 = new ALCityInstanceInPL("instance_img15",4,1,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img15);
        AttributeValue instance_img15_variable_2_value2 = new AttributeValue(null,null,null,"D",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img15.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img15_variable_2_value2);

        AttributeValue  instance_img15_property_1_value2= new AttributeValue(null,null,null,null,null,null,D_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img15.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img15_property_1_value2);



        ALCityInstanceInPL instance_img16 = new ALCityInstanceInPL("instance_img16",4,2,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img16);
        AttributeValue instance_img16_variable_2_value2 = new AttributeValue(null,null,null,"I",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img16.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img16_variable_2_value2);

        AttributeValue  instance_img16_property_1_value2= new AttributeValue(null,null,null,null,null,null,I_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img16.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img16_property_1_value2);


        ALCityInstanceInPL instance_img17 = new ALCityInstanceInPL("instance_img17",4,3,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img17);
        AttributeValue instance_img17_variable_2_value2 = new AttributeValue(null,null,null,"J",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img17.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img17_variable_2_value2);

        AttributeValue  instance_img17_property_1_value2= new AttributeValue(null,null,null,null,null,null,J_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img17.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img17_property_1_value2);


        ALCityInstanceInPL instance_img18 = new ALCityInstanceInPL("instance_img18",4,4,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img18);
        AttributeValue instance_img18_variable_2_value2 = new AttributeValue(null,null,null,"B",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img18.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img18_variable_2_value2);

        AttributeValue  instance_img18_property_1_value2= new AttributeValue(null,null,null,null,null,null,B_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img18.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img18_property_1_value2);


        ALCityInstanceInPL instance_img19 = new ALCityInstanceInPL("instance_img19",4,5,0,imageObject01_in_memoryGame_puzzleGroup,pl_Memory_Game,1L,now,now,admin_1,admin_1);
        pgObjectInstanceService.save(instance_img19);
        AttributeValue instance_img19_variable_2_value2 = new AttributeValue(null,null,null,"A",null,null,null,null,false,null,ImageObject01_variable_2,1L,now,now,admin_1,admin_1,instance_img19.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Variable);
        attributeValueService.save(instance_img19_variable_2_value2);

        AttributeValue  instance_img19_property_1_value2= new AttributeValue(null,null,null,null,null,null,A_Image_bytes_content.getId(),null,Boolean.FALSE,null,ImageObject01_property_1,1L,now,now,admin_1,admin_1,instance_img19.getId(),AttributeOwnerType.Instance_Puzzle_Group_Object_Property);
        attributeValueService.save(instance_img19_property_1_value2);


        Optional<PLRuleEvent> click_event = plRuleEventService.findByName("Click");
        Optional<PLRuleEvent> internalEvent = plRuleEventService.findByName("InternalEvent");


        StringBuffer    FirstClick_rule_condition = new StringBuffer("equal(BoardVar(firstClick),true) & equal(BoardVar(finished),false) & unequal(InstVar(InstByPos(EventParam(row), EventParam(col)),flipped),true)");
        Boolean ignoreRemaining = true;
        PLRule rule_for_FirstClick = new PLRule("FirstClick",1
                ,FirstClick_rule_condition,ignoreRemaining,pl_Memory_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_FirstClick);

        PLRulePostAction VariableAssignmentAction_1 = new PLRulePostAction(rule_for_FirstClick.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(firstSelectedObject)",new StringBuffer("InstProp(InstByPos(EventParam(row),EventParam(col)),objectId)"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1);

        PLRulePostAction VariableAssignmentAction_2 = new PLRulePostAction(rule_for_FirstClick.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,2,"","",
                "BoardVar(firstClick)",new StringBuffer("false"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2);

        PLRulePostAction CallObjectAction_3 = new PLRulePostAction(rule_for_FirstClick.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,3,"ApplyProperty","BoardVar(firstSelectedObject)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_3);

        Attribute CallObjectAction_3_param_1 =new Attribute("img",CallObjectAction_3.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_3_param_1);
        AttributeValue CallObjectAction_3_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), bgImage)",true,null,CallObjectAction_3_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_3.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_3_param_1_value);

        StringBuffer    SecondClickOnFirst_rule_condition = new StringBuffer("equal(BoardVar(firstClick),false) & equal(BoardVar(finished),false) & equal(InstProp(InstByPos(EventParam(row), EventParam(col)),objectId),BoardVar(firstSelectedObject))");
        Boolean ignoreRemaining2 = false;
        PLRule rule_for_SecondClickOnFirst = new PLRule("SecondClickOnFirst",2
                ,SecondClickOnFirst_rule_condition,ignoreRemaining2,pl_Memory_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_SecondClickOnFirst);


        PLRulePostAction VariableAssignmentAction_1_SecondClickOnFirst = new PLRulePostAction(rule_for_SecondClickOnFirst.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(firstClick)",new StringBuffer("true"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_SecondClickOnFirst);

        PLRulePostAction VariableAssignmentAction_2_SecondClickOnFirst = new PLRulePostAction(rule_for_SecondClickOnFirst.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,2,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2_SecondClickOnFirst);

        PLRulePostAction CallObjectAction_3_SecondClickOnFirst = new PLRulePostAction(rule_for_SecondClickOnFirst.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,3,"ApplyProperty","BoardVar(firstSelectedObject)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_3_SecondClickOnFirst);

        Attribute CallObjectAction_3_SecondClickOnFirst_param_1 =new Attribute("img",CallObjectAction_3_SecondClickOnFirst.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_3_SecondClickOnFirst_param_1);
        AttributeValue CallObjectAction_3_SecondClickOnFirst_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), flippedImage)",Boolean.TRUE,null,CallObjectAction_3_SecondClickOnFirst_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_3_SecondClickOnFirst.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_3_SecondClickOnFirst_param_1_value);

        StringBuffer    SecondClickCorrect_rule_condition = new StringBuffer("equal(BoardVar(firstClick),false) & equal(BoardVar(finished),false) & unequal(InstVar(InstByPos(EventParam(row), EventParam(col)),flipped),true) & equal(InstVar(InstByPos(EventParam(row), EventParam(col)),value),InstVar(InstById(BoardVar(firstSelectedObject)),value))");
        Boolean ignoreRemaining3 = false;
        PLRule rule_for_SecondClickCorrect = new PLRule("SecondClickCorrect",3
                ,SecondClickCorrect_rule_condition,ignoreRemaining3,pl_Memory_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_SecondClickCorrect);

        PLRulePostAction VariableAssignmentAction_1_SecondClickCorrect = new PLRulePostAction(rule_for_SecondClickCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(firstClick)",new StringBuffer("true"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_SecondClickCorrect);

        PLRulePostAction VariableAssignmentAction_2_SecondClickCorrect = new PLRulePostAction(rule_for_SecondClickCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,2,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2_SecondClickCorrect);

        PLRulePostAction VariableAssignmentAction_3_SecondClickCorrect = new PLRulePostAction(rule_for_SecondClickCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,3,"","",
                "BoardVar(solved)",new StringBuffer("BoardVar(solved)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_3_SecondClickCorrect);

        PLRulePostAction CallObjectAction_4_SecondClickCorrect = new PLRulePostAction(rule_for_SecondClickCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,4,"ApplyProperty","InstProp(InstByPos(EventParam(row),EventParam(col)),objectId)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_4_SecondClickCorrect);

        Attribute CallObjectAction_4_SecondClickCorrect_param_1 =new Attribute("img",CallObjectAction_4_SecondClickCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_4_SecondClickCorrect_param_1);
        AttributeValue CallObjectAction_4_SecondClickCorrect_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), bgImage)",Boolean.TRUE,null,CallObjectAction_4_SecondClickCorrect_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_4_SecondClickCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_4_SecondClickCorrect_param_1_value);

        PLRulePostAction FireEventAction_5_SecondClickCorrect = new PLRulePostAction(rule_for_SecondClickCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.FireEventAction,5,"","",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(FireEventAction_5_SecondClickCorrect);



        StringBuffer    SecondClickInCorrect_rule_condition = new StringBuffer("equal(BoardVar(firstClick),false) & equal(BoardVar(finished),false) & unequal(InstVar(InstByPos(EventParam(row), EventParam(col)),flipped),true) & unequal(InstVar(InstByPos(EventParam(row), EventParam(col)),value),InstVar(InstById(BoardVar(firstSelectedObject)),value))");
        Boolean ignoreRemaining4 = false;
        PLRule rule_for_SecondClickInCorrect = new PLRule("SecondClickInCorrect",4
                ,SecondClickInCorrect_rule_condition,ignoreRemaining4,pl_Memory_Game,click_event.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_SecondClickInCorrect);

        PLRulePostAction VariableAssignmentAction_1_SecondClickInCorrect = new PLRulePostAction(rule_for_SecondClickInCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(firstClick)",new StringBuffer("true"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_SecondClickInCorrect);

        PLRulePostAction VariableAssignmentAction_2_SecondClickInCorrect = new PLRulePostAction(rule_for_SecondClickInCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,2,"","",
                "BoardVar(steps)",new StringBuffer("BoardVar(steps)+1"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_2_SecondClickInCorrect);

        PLRulePostAction CallObjectAction_3_SecondClickInCorrect = new PLRulePostAction(rule_for_SecondClickInCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,3,"ApplyProperty","InstProp(InstByPos(EventParam(row),EventParam(col)),objectId)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_3_SecondClickInCorrect);
        Attribute CallObjectAction_3_SecondClickInCorrect_param_1 =new Attribute("img",CallObjectAction_3_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_3_SecondClickInCorrect_param_1);
        AttributeValue CallObjectAction_3_SecondClickInCorrect_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), bgImage)",Boolean.TRUE,null,CallObjectAction_3_SecondClickInCorrect_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_3_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_3_SecondClickInCorrect_param_1_value);

        PLRulePostAction CallObjectAction_4_SecondClickInCorrect = new PLRulePostAction(rule_for_SecondClickInCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,4,"ApplyProperty","BoardVar(firstSelectedObject)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_4_SecondClickInCorrect);
        Attribute CallObjectAction_4_SecondClickInCorrect_param_1 =new Attribute("img",CallObjectAction_4_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_4_SecondClickInCorrect_param_1);
        AttributeValue CallObjectAction_4_SecondClickInCorrect_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), flippedImage)",Boolean.TRUE,null,CallObjectAction_4_SecondClickInCorrect_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_4_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_4_SecondClickInCorrect_param_1_value);

        PLRulePostAction CallObjectAction_5_SecondClickInCorrect = new PLRulePostAction(rule_for_SecondClickInCorrect.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallObjectAction,5,"ApplyProperty","InstProp(InstByPos(EventParam(row),EventParam(col)),objectId)",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(CallObjectAction_5_SecondClickInCorrect);
        Attribute CallObjectAction_5_SecondClickInCorrect_param_1 =new Attribute("img",CallObjectAction_5_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action,DataType.Binary,1L,now,now,admin_1,admin_1);
        attributeService.save(CallObjectAction_5_SecondClickInCorrect_param_1);
        AttributeValue CallObjectAction_5_SecondClickInCorrect_param_1_value= new AttributeValue(null,null,null,null,null,null,null,"InstProp(CurrentInst(), flippedImage)",Boolean.TRUE,null,CallObjectAction_5_SecondClickInCorrect_param_1,1L,now,now,admin_1,admin_1,CallObjectAction_5_SecondClickInCorrect.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action);
        attributeValueService.save(CallObjectAction_5_SecondClickInCorrect_param_1_value);

        StringBuffer    CheckCompletion_rule_condition = new StringBuffer("equal(BoardVar(solved),10)");
        Boolean ignoreRemaining5 = false;
        PLRule rule_for_CheckCompletion = new PLRule("CheckCompletion",5
                ,CheckCompletion_rule_condition,ignoreRemaining5,pl_Memory_Game,internalEvent.get(),"",1L,now,now,admin_1,admin_1);
        puzzleLevelRuleService.save(rule_for_CheckCompletion);

        PLRulePostAction VariableAssignmentAction_1_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.VariableAssignmentAction,1,"","",
                "BoardVar(finished)",new StringBuffer("true"),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(VariableAssignmentAction_1_CheckCompletion);

        PLRulePostAction UserAlertAction_2_CheckCompletion = new PLRulePostAction(rule_for_CheckCompletion.getId(),PLRulePostActionOwnerType.Puzzle_Level_Rule,PLRulePostActionType.CallSystemAction,2,"ShowMessage","",
                "",new StringBuffer(""),"","","",0L,1L ,now,now,admin_1,admin_1);
        plRulePostActionService.save(UserAlertAction_2_CheckCompletion);

        Attribute UserAlertAction_2_CheckCompletion_param_1=new Attribute("text",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_1);
        AttributeValue  callobjectaction_2_startTimer_param_1_value= new AttributeValue(null,null,null,"mission completed",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_1,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_1_value);

        Attribute UserAlertAction_2_CheckCompletion_param_2=new Attribute("dialogType",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_2);
        AttributeValue  callobjectaction_2_startTimer_param_2_value= new AttributeValue(null,null,null,"DIALOG",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_2,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_2_value);

        Attribute UserAlertAction_2_CheckCompletion_param_3=new Attribute("messageType",UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(UserAlertAction_2_CheckCompletion_param_3);
        AttributeValue  callobjectaction_2_startTimer_param_3_value= new AttributeValue(null,null,null,"INFO",null,null,null,null,Boolean.FALSE,null,UserAlertAction_2_CheckCompletion_param_3,1L,now,now,admin_1,admin_1,UserAlertAction_2_CheckCompletion.getId(),AttributeOwnerType.Puzzle_Level_Rule_Post_Action_Parameter);
        attributeValueService.save(callobjectaction_2_startTimer_param_3_value);
*/



    }





}
