package com.alcity.importdata;


import com.alcity.entity.alenum.*;
import com.alcity.entity.alobject.*;
import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningSkillTopic;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMember_WalletItem;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.appmember.WalletTransaction;
import com.alcity.service.Journey.JourneyLearningSkillService;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningSkill_LearningTopicService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.puzzle.ALCityObjectService;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PLRuleEventService;
import com.alcity.service.puzzle.PuzzleSkillLearningContentService;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.AppMember_WalletItemService;
import com.alcity.service.appmember.WalletItemService;
import com.alcity.service.appmember.WalletTransactionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.ImageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;


@Order(value=1)
@Component
public class ImportBaseData implements CommandLineRunner {

    @Autowired
    private WalletItemTypeService walletItemTypeService;
    @Autowired
    private AppMemberService appMemberService;
    @Autowired
    private ClientTypeService clientTypeService;

    @Autowired
    private MemberTypeService memberTypeService;
    @Autowired
    private WalletItemService walletItemService;

    @Autowired
    private PuzzleCategoryService puzzleCategoryService;

    @Autowired
    ObjectCategoryService objectCategoryService;

    @Autowired
    private AppMember_WalletItemService applicationMember_walletItemService;

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private LearningSkillService learningSkillService;

    @Autowired
    private LearningTopicService learningTopicService;

    @Autowired
    LearningSkill_LearningTopicService learningSkill_LearningTopicService;

    @Autowired
    private PLPrivacyService puzzleLevelPrivacyService;

    @Autowired
    ALCityObjectService alCityObjectService;

    @Autowired
    PuzzleObjectActionService puzzleObjectActionService;
    @Autowired
    PuzzleSkillLearningContentService puzzleSkillLearningContentService;
    @Autowired
    LearningContentService learningContentService;

    @Autowired
    BinaryContentService binaryContentService;
    @Autowired
    private PGService puzzleGroupService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    AdvertisementService advertisementService;

    @Autowired
    JourneyLearningSkillService journeyLearningSkillService;

    @Autowired
    RendererService actionRendererService;

    @Autowired
    PLRuleEventService plRuleEventService;

    @Autowired
    AttributeService attributeService;
    @Autowired
    AttributeValueService attributeValueService;

    protected final Log log = LogFactory.getLog(getClass());


    @Override
    public void run(String... args) throws Exception {

        log.info("Start Application...Import Problem 1");
        System.out.println("...Import Problem 1");

//        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
//        ZonedDateTime startDate = ZonedDateTime.now();
//        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);

//        ZonedDateTime  createdDate= ZonedDateTime.now();
//        Long now = createdDate.toEpochSecond();
        LocalDateTime current = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String now = current.format(format);


        MemberType administrator = new MemberType("Administrator","Administrator",1L,now,now,null,null);
        memberTypeService.save(administrator);
        MemberType puzzlePlayer = new MemberType("Puzzle Player","Puzzle_Player",1L,now,now,null,null);
        memberTypeService.save(puzzlePlayer);

        MemberType guest = new MemberType("Guest","Guest",1L,now,now,null,null);
        memberTypeService.save(guest);

        MemberType puzzleCreator = new MemberType("Puzzle Creator","Puzzle_Creator",1L,now,now,null,null);
        memberTypeService.save(puzzleCreator);


        ClientType  mobile = new ClientType("mobile","mobile",1L,now,now,null,null);
        ClientType  web = new ClientType("web","web",1L,now,now,null,null);
        ClientType  tablet = new ClientType("tablet","tablet",1L,now,now,null,null);
        clientTypeService.save(mobile);
        clientTypeService.save(web);
        clientTypeService.save(tablet);
        byte[] avatar = ImageUtil.getImage("src/main/resources/images/","avatar.png");
        byte[]  tumb = ImageUtil.getThumbnail(avatar,"avatar.png");

        BinaryContent no_photo_avatar = new BinaryContent(1L, now, now,null , null,"no_photo_avatar",avatar.length,avatar,tumb,"no_photo_avatar","no_photo_avatar","no_photo_avatar",BinaryContentType.Image);
        binaryContentService.save(no_photo_avatar);

        BinaryContent avatar_content_1 = new BinaryContent(1L, now, now,null , null,"avatar",avatar.length,avatar,tumb,"tag1","tag2","tag3",BinaryContentType.Image);
        binaryContentService.save(avatar_content_1);

        AppMember admin_1= new AppMember(35,"admin","admin","admin0","0912350550","j_hoseyni@yahoo.com",avatar_content_1,
                UserGender.Male,administrator,1L,now,now,null,null);
        Set clientTypeSet = new HashSet<ClientType>();
        clientTypeSet.add(web);
        admin_1.setClientTypeSet(clientTypeSet);
        appMemberService.save(admin_1);
        avatar_content_1.setCreatedBy(admin_1);
        avatar_content_1.setUpdatedBy(admin_1);
        binaryContentService.save(avatar_content_1);

        no_photo_avatar.setCreatedBy(admin_1);
        no_photo_avatar.setUpdatedBy(admin_1);
        binaryContentService.save(no_photo_avatar);

        AppMember jalalHoseyni= new AppMember(35,"jalal","jalal","jalal","0912350550","j_hoseyni@yahoo.com",avatar_content_1,UserGender.Male,guest,1L,now,now,null,null);
        Set jalalClientTypeSet = new HashSet<ClientType>();
        jalalClientTypeSet.add(mobile);
        jalalHoseyni.setClientTypeSet(jalalClientTypeSet);
        appMemberService.save(jalalHoseyni);

        AppMember Moslem_Balavandi= new AppMember(35,"moslem","moslem","moslem","0912350550","balavandi@gmail.com",avatar_content_1,UserGender.Male,guest,1L,now,now,null,null);
        Set moslem_ClientTypeSet = new HashSet<ClientType>();
        moslem_ClientTypeSet.add(mobile);
        moslem_ClientTypeSet.add(web);
        Moslem_Balavandi.setClientTypeSet(moslem_ClientTypeSet);
        appMemberService.save(Moslem_Balavandi);

        AppMember alireza_zarei= new AppMember(35,"alireza","alireza","alireza","0912350550","zare@sharif.edu",avatar_content_1,UserGender.Male,guest,1L,now,now,null,null);
        Set alireza_ClientTypeSet = new HashSet<ClientType>();
        alireza_ClientTypeSet.add(mobile);
        alireza_ClientTypeSet.add(web);
        alireza_ClientTypeSet.add(tablet);
        alireza_zarei.setClientTypeSet(alireza_ClientTypeSet);
        appMemberService.save(alireza_zarei);

        PLRuleEvent click_ruleEvent = new PLRuleEvent("Click", PLRuleEventType.User_Event, UserEvent.Click.ordinal(),1L,now,now,admin_1,admin_1);
        PLRuleEvent Internal_Event = new PLRuleEvent("Internal_Event", PLRuleEventType.System_Event, SystemEvent.Internal_Event.ordinal(),1L,now,now,admin_1,admin_1);
        plRuleEventService.save(click_ruleEvent);
        plRuleEventService.save(Internal_Event);

        Advertisement termsAndCondition = new Advertisement("this is a terms and Condtion",ADSType.TermAndCondition,1L,now,now,jalalHoseyni,jalalHoseyni);
        advertisementService.save(termsAndCondition);

        //update some fileds
        administrator.setCreatedBy(admin_1);
        administrator.setUpdatedBy(admin_1);

        memberTypeService.save(administrator);
        guest.setUpdatedBy(admin_1);
        guest.setCreatedBy(admin_1);
        memberTypeService.save(guest);

        puzzleCreator.setCreatedBy(admin_1);
        puzzleCreator.setUpdatedBy(admin_1);
        memberTypeService.save(puzzleCreator);

        puzzlePlayer.setCreatedBy(admin_1);
        puzzlePlayer.setUpdatedBy(admin_1);
        memberTypeService.save(puzzlePlayer);

        PuzzleCategory mathematic = new PuzzleCategory("mathematic","mathematic",1L,now,now,admin_1,admin_1);
        PuzzleCategory  physic = new PuzzleCategory("physic","physic",1L,now,now,admin_1,admin_1);
        PuzzleCategory  maze = new PuzzleCategory("Maze","Maze",1L,now,now,admin_1,admin_1);
        PuzzleCategory  iq = new PuzzleCategory("IQ","IQ",1L,now,now,admin_1,admin_1);
        PuzzleCategory  fun = new PuzzleCategory("Fun","Fun",1L,now,now,admin_1,admin_1);
        PuzzleCategory  maze2 = new PuzzleCategory("Maze 2","Maze 2",1L,now,now,admin_1,admin_1);
        PuzzleCategory  maze3 = new PuzzleCategory("Maze 3","Maze 3",1L,now,now,admin_1,admin_1);
        PuzzleCategory  maze4 = new PuzzleCategory("Maze Maz","Maze Maze",1L,now,now,admin_1,admin_1);
        puzzleCategoryService.save(mathematic);
        puzzleCategoryService.save(physic);
        puzzleCategoryService.save(iq);
        puzzleCategoryService.save(maze);
        puzzleCategoryService.save(fun);

        byte[] puzzle_Ground_Maze_Image_1 = ImageUtil.getImage("src/main/resources/images/","playGround.png");
        byte[]  puzzle_Ground_Maze_Image_1_tumb = ImageUtil.getThumbnail(puzzle_Ground_Maze_Image_1,"playGround.png");
        BinaryContent puzzle_ground_Maze_image_binary_content_1 = new BinaryContent(1L, now, now,admin_1 , admin_1,"puzzle ground for Maze image",puzzle_Ground_Maze_Image_1.length,puzzle_Ground_Maze_Image_1,puzzle_Ground_Maze_Image_1_tumb,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_ground_Maze_image_binary_content_1);

        byte[] puzzle_group_Icon_2 = ImageUtil.getImage("src/main/resources/images/","physic.png");
        BinaryContent puzzle_group_binary_content_2 = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_physic",puzzle_group_Icon_2.length,puzzle_group_Icon_2,null,"tag1","kjkjk","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_binary_content_2);

        byte[] puzzle_group_Icon_3 = ImageUtil.getImage("src/main/resources/images/","IQ.png");
        BinaryContent puzzle_group_binary_content_3 = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_IQ",puzzle_group_Icon_3.length,puzzle_group_Icon_3,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_binary_content_3);

        byte[] puzzle_group_Maze_Image = ImageUtil.getImage("src/main/resources/images/","MazeImage.png");
        BinaryContent puzzle_group_Maze_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"MazeImage",puzzle_group_Maze_Image.length,puzzle_group_Maze_Image,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_Maze_Image_binary_content);

        byte[] puzzle_group_Icon_1 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_1.png");
        BinaryContent puzzle_group_binary_content_Icon = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_puzzle_group_matematic",puzzle_group_Icon_1.length,puzzle_group_Icon_1,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(puzzle_group_binary_content_Icon);

        LearningContent learningContent_Maze=new LearningContent("help to maze","this content is about maze tables",puzzle_group_Maze_Image_binary_content,1L,now,now,admin_1,admin_1);
        learningContentService.save(learningContent_Maze);


        PuzzleGroup puzzleGroup_HashImage = new PuzzleGroup("Hash Image - Puzzle Group 1",mathematic,puzzle_group_binary_content_Icon,puzzle_group_binary_content_Icon,1L,now,now,admin_1,admin_1);
        PuzzleGroup puzzleGroup_IQ = new PuzzleGroup("IQ Puzzle Group",mathematic,puzzle_group_binary_content_2,puzzle_group_binary_content_3,1L,now,now,admin_1,admin_1);
        puzzleGroupService.save(puzzleGroup_HashImage);
        puzzleGroupService.save(puzzleGroup_IQ);

        byte[] tetherIcon = ImageUtil.getImage("src/main/resources/images/","Tether.png");
        byte[] carIcon = ImageUtil.getImage("src/main/resources/images/","car.png");
        byte[]  carIcon_tumb = ImageUtil.getThumbnail(carIcon,"carIcon.png");
        byte[]  tetherIcon_tumb = ImageUtil.getThumbnail(tetherIcon,"Tether.png");

        BinaryContent teterIcon_Content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Tether Icon",tetherIcon.length,tetherIcon,tetherIcon_tumb,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(teterIcon_Content);

        BinaryContent carIcon_Content = new BinaryContent(1L, now, now,admin_1 , admin_1,"Car Icon",carIcon.length,carIcon,carIcon_tumb,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(carIcon_Content);


        WalletItemType fiat = new WalletItemType(WalletItemCategory.CryptoCurrency,"fiat","fiat",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType crypto = new WalletItemType(WalletItemCategory.CryptoCurrency,"crypto","crypto",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType alCoin = new WalletItemType(WalletItemCategory.AL_Coin,"al_coin","al_coin",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType cityObject = new WalletItemType(WalletItemCategory.Object,"cityObject","cityObject",Boolean.FALSE,1L,now,now,admin_1,admin_1);

        walletItemTypeService.save(fiat);
        walletItemTypeService.save(crypto);
        walletItemTypeService.save(alCoin);
        walletItemTypeService.save(cityObject);

         WalletItem teterWalletItem= new WalletItem(fiat,teterIcon_Content,"tether","tether",1L,now,now,admin_1,admin_1);
        WalletItem alCoin10WalletItem= new WalletItem(alCoin,teterIcon_Content,"al_coin_10","al_coin_10",1L,now,now,admin_1,admin_1);
        WalletItem alCoin_100_WalletItem= new WalletItem(alCoin,teterIcon_Content,"al_coin_100","al_coin_100",1L,now,now,admin_1,admin_1);
        WalletItem carWalletItem= new WalletItem(cityObject,carIcon_Content,"car object","car_object",1L,now,now,admin_1,admin_1);
        WalletItem TVWalletItem= new WalletItem(cityObject,carIcon_Content,"TV object","TV_object",1L,now,now,admin_1,admin_1);

        walletItemService.save(teterWalletItem);
        walletItemService.save(alCoin10WalletItem);
        walletItemService.save(carWalletItem);
        walletItemService.save(alCoin_100_WalletItem);
        walletItemService.save(TVWalletItem);

        AppMember_WalletItem jalalHoseyni_alcoin_10= new AppMember_WalletItem(jalalHoseyni,alCoin10WalletItem,10f,1L,now,now,admin_1,admin_1);
        AppMember_WalletItem jalalHoseyni_carObject_20= new AppMember_WalletItem(jalalHoseyni,carWalletItem,20f,1L,now,now,admin_1,admin_1);
        applicationMember_walletItemService.save(jalalHoseyni_alcoin_10);
        applicationMember_walletItemService.save(jalalHoseyni_carObject_20);

        AppMember_WalletItem admin_1_alcoin_10= new AppMember_WalletItem(admin_1,alCoin10WalletItem,10f,1L,now,now,admin_1,admin_1);
        AppMember_WalletItem admin_1_carObject_10= new AppMember_WalletItem(admin_1,carWalletItem,20f,1L,now,now,admin_1,admin_1);
        applicationMember_walletItemService.save(admin_1_alcoin_10);
        applicationMember_walletItemService.save(admin_1_carObject_10);

        WalletTransaction transaction1 = new WalletTransaction(now,1.5f,Boolean.TRUE,"desc",jalalHoseyni_alcoin_10,1L,now,now,admin_1,admin_1);
        WalletTransaction transaction2 = new WalletTransaction(now,1f,Boolean.TRUE,"desc",jalalHoseyni_carObject_20,1L,now,now,admin_1,admin_1);
        walletTransactionService.save(transaction1);
        walletTransactionService.save(transaction2);

        ObjectCategory objectCategory_animal = new ObjectCategory("Animal","Animal",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_bird = new ObjectCategory("Bird","Bird",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_cereal = new ObjectCategory("cereal","cereal",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_Image = new ObjectCategory("Image","Image",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_Mamals = new ObjectCategory("Mamals","Mamals",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_TextObject = new ObjectCategory("TextObject","TextObject",1L,now,now,admin_1,admin_1);
        objectCategoryService.save(objectCategory_animal);
        objectCategoryService.save(objectCategory_bird);
        objectCategoryService.save(objectCategory_cereal);
        objectCategoryService.save(objectCategory_Image);
        objectCategoryService.save(objectCategory_Mamals);
        objectCategoryService.save(objectCategory_TextObject);


        byte[] goose_Image = ImageUtil.getImage("src/main/resources/images/","goose.png");
        BinaryContent goose_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"goose image",goose_Image.length,goose_Image, null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(goose_Image_binary_content);

        byte[] eagle_Image = ImageUtil.getImage("src/main/resources/images/","eagle.png");
        BinaryContent eagle_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"eagle image",eagle_Image.length,eagle_Image,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(eagle_Image_binary_content);

        byte[] fox_Image = ImageUtil.getImage("src/main/resources/images/","fox.png");
        BinaryContent fox_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"fox image",fox_Image.length,fox_Image,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(fox_Image_binary_content);

        byte[] wheat_Image = ImageUtil.getImage("src/main/resources/images/","wheat.png");
        BinaryContent wheat_Image_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"wheat image",wheat_Image.length,wheat_Image,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(wheat_Image_binary_content);

        byte[] image_object = ImageUtil.getImage("src/main/resources/images/","image_object.png");
        BinaryContent image_object_binary_content = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_object", image_object.length, image_object,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(image_object_binary_content);


        PLPrivacy privacy_1 = new PLPrivacy("privacy 1","privacy1",1L,now,now,admin_1,admin_1);
        PLPrivacy privacy_2 = new PLPrivacy("privacy 2","privacy2",1L,now,now,admin_1,admin_1);
        PLPrivacy privacy_3 = new PLPrivacy("privacy 3","privacy3",1L,now,now,admin_1,admin_1);
        puzzleLevelPrivacyService.save(privacy_1);
        puzzleLevelPrivacyService.save(privacy_2);
        puzzleLevelPrivacyService.save(privacy_3);

        LearningSkill constraint = new LearningSkill("Constraint","Constraint",1L,now,now,admin_1,admin_1);
        LearningSkill loop = new LearningSkill("loop","loop",1L,now,now,admin_1,admin_1);
        LearningSkill timeManagement = new LearningSkill("time Management","timeManagement",1L,now,now,admin_1,admin_1);
        LearningSkill division = new LearningSkill("division","division",1L,now,now,admin_1,admin_1);
        LearningSkill routing = new LearningSkill("division","division",1L,now,now,admin_1,admin_1);
        LearningSkill fast_descioin = new LearningSkill("fast decisioning","fast decisioning",1L,now,now,admin_1,admin_1);
        LearningSkill find_paths = new LearningSkill("Find Paths","Find Paths",1L,now,now,admin_1,admin_1);
        LearningSkill matching = new LearningSkill("matching","matching",1L,now,now,admin_1,admin_1);
        LearningSkill memory_booster = new LearningSkill("memory_booster","memory_booster",1L,now,now,admin_1,admin_1);

        learningSkillService.save(constraint);
        learningSkillService.save(loop);
        learningSkillService.save(timeManagement);
        learningSkillService.save(division);
        learningSkillService.save(fast_descioin);
        learningSkillService.save(find_paths);
        learningSkillService.save(matching);
        learningSkillService.save(memory_booster);

        LearningTopic root_Topic = new LearningTopic("Root Topic",null,1L,now,now,admin_1,admin_1);
        LearningTopic hashImage_Topic = new LearningTopic("Hash Image",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic X_O_Topic = new LearningTopic("X-O Game",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic Memory_Game_Topic = new LearningTopic("Memory_Game",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic magic_matrix_Topic = new LearningTopic("Magic Matrix",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic maze_table_Topic = new LearningTopic("Maze Table",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic algorithm_Topic = new LearningTopic("Algorithm",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic routing_in_the_table = new LearningTopic("Routing in the Table",root_Topic,1L,now,now,admin_1,admin_1);
        learningTopicService.save(root_Topic);
        learningTopicService.save(hashImage_Topic);
        learningTopicService.save(X_O_Topic);
        learningTopicService.save(magic_matrix_Topic);
        learningTopicService.save(algorithm_Topic);
        learningTopicService.save(maze_table_Topic);
        learningTopicService.save(routing_in_the_table);
        learningTopicService.save(Memory_Game_Topic);


        LearningSkillTopic learningSkill_learningTopic_1 = new LearningSkillTopic("title1",timeManagement,algorithm_Topic,1L,now,now,admin_1,admin_1);
        LearningSkillTopic learningSkill_learningTopic_2 = new LearningSkillTopic("title2",division,algorithm_Topic,1L,now,now,admin_1,admin_1);
        learningSkill_LearningTopicService.save(learningSkill_learningTopic_1);
        learningSkill_LearningTopicService.save(learningSkill_learningTopic_2);


        byte[] jouerny_1_Image = ImageUtil.getImage("src/main/resources/images/","jungle.png");
        byte[]  tumb_jouerny_1 = ImageUtil.getThumbnail(jouerny_1_Image,"jungle.png");

        byte[] jouerny_2_Image = ImageUtil.getImage("src/main/resources/images/","desert.png");
        byte[]  tumb_jouerny_2 = ImageUtil.getThumbnail(jouerny_2_Image,"desert.png");

        byte[] jouerny_3_Image = ImageUtil.getImage("src/main/resources/images/","city.png");
        byte[]  tumb_jouerny_3 = ImageUtil.getThumbnail(jouerny_3_Image,"city.png");

        BinaryContent image_journey_1 = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_journey_1",jouerny_1_Image.length,jouerny_1_Image,tumb_jouerny_1,"tag1","","",BinaryContentType.Image);
        BinaryContent image_journey_2 = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_journey_2",jouerny_2_Image.length,jouerny_2_Image,tumb_jouerny_2,"tag1","","",BinaryContentType.Image);
        BinaryContent image_journey_3 = new BinaryContent(1L, now, now,admin_1 , admin_1,"image_journey_3",jouerny_3_Image.length,jouerny_3_Image,tumb_jouerny_3,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(image_journey_1);
        binaryContentService.save(image_journey_2);
        binaryContentService.save(image_journey_3);

        Journey journey_1 = new Journey("Journey_1",1,10,50,image_journey_1,1L,now,now,admin_1,admin_1);
        Journey journey_2 = new Journey("Journey_2",2,5,70,image_journey_2,1L,now,now,admin_1,admin_1);
        Journey journey_3 = new Journey("Journey_3",3,15,100,image_journey_3,1L,now,now,admin_1,admin_1);
        journeyService.save(journey_1);
        journeyService.save(journey_2);
        journeyService.save(journey_3);

        JourneyLearningSkill journey_1_Skill_1 = new JourneyLearningSkill(0.5f,journey_1,timeManagement,1L,now,now,admin_1,admin_1);
        JourneyLearningSkill journey_1_Skill_2 = new JourneyLearningSkill(0.5f,journey_1,division,1L,now,now,admin_1,admin_1);
        journeyLearningSkillService.save(journey_1_Skill_1);
        journeyLearningSkillService.save(journey_1_Skill_2);

        byte[] text_object_content_pic_byte = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_pic = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",text_object_content_pic_byte.length,text_object_content_pic_byte,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(textObject_pic);

        byte[] text_object_content_icon_byte = ImageUtil.getImage("src/main/resources/images/X-O Problem/","TextObject.png");
        BinaryContent textObject_icon = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",text_object_content_icon_byte.length,text_object_content_icon_byte,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(textObject_icon);

        byte[] image_object_content_pic_byte = ImageUtil.getImage("src/main/resources/images/","image_object.png");
        BinaryContent image_Object_pic = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",image_object_content_pic_byte.length,image_object_content_pic_byte,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(image_Object_pic);

        byte[] image_object_content_icon_byte = ImageUtil.getImage("src/main/resources/images/","image_object_icon.png");
        BinaryContent image_Object_icon = new BinaryContent(1L, now, now,admin_1 , admin_1,"text_object_x-o",image_object_content_icon_byte.length,image_object_content_icon_byte,null,"tag1","","",BinaryContentType.Image);
        binaryContentService.save(image_Object_icon);


        Renderer create_Renderer = new Renderer("Show",mobile, ObjectAction.Create,1L,now,now,admin_1,admin_1);
        actionRendererService.save(create_Renderer);

        //add parameters and default values
        Attribute create_ActionRenderer_param_1 =new Attribute("text",create_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(create_ActionRenderer_param_1);
        AttributeValue create_ActionRenderer_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,null,create_ActionRenderer_param_1,create_ActionRenderer_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(create_ActionRenderer_param_1_value);

        Attribute create_ActionRenderer_param_2 =new Attribute("CODE",create_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(create_ActionRenderer_param_2);
        AttributeValue create_ActionRenderer_param_2_value= new AttributeValue(null,null,null,"CODE",null,null,null,create_ActionRenderer_param_2,create_ActionRenderer_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(create_ActionRenderer_param_2_value);


        Renderer show_Renderer = new Renderer("Show",mobile, ObjectAction.Show,1L,now,now,admin_1,admin_1);
        actionRendererService.save(show_Renderer);

        Attribute show_ActionRenderer_param_1 =new Attribute("text",show_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(show_ActionRenderer_param_1);
        AttributeValue show_ActionRenderer_param_1_value= new AttributeValue(null,null,null,"InstProp(CurrentInst(), text)",null,null,null,show_ActionRenderer_param_1,show_ActionRenderer_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(show_ActionRenderer_param_1_value);


        Renderer move_Renderer = new Renderer("Move",mobile, ObjectAction.Move,1L,now,now,admin_1,admin_1);
        actionRendererService.save(move_Renderer);

        //add parameters and default values
        Attribute move_ActionRenderer_param_1 =new Attribute("actionId",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_1);
        AttributeValue  move_ActionRenderer_param_actionId_value= new AttributeValue(null,null,ObjectAction.getOrdinalId("Move"),null,null,null,null,move_ActionRenderer_param_1,move_ActionRenderer_param_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_actionId_value);

        Attribute move_ActionRenderer_param_2 =new Attribute("aSync",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Boolean,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_2);
        AttributeValue  move_ActionRenderer_param_aSync_value= new AttributeValue(false,null,null,null,null,null,null,move_ActionRenderer_param_2,move_ActionRenderer_param_2,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_aSync_value);

        Attribute move_ActionRenderer_param_3 =new Attribute("formRow",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_3);
        AttributeValue  move_ActionRenderer_param_formRow_value= new AttributeValue(null,0,null,null,null,null,null,move_ActionRenderer_param_3,move_ActionRenderer_param_3,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_formRow_value);

        Attribute move_ActionRenderer_param_4 =new Attribute("toRow",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_4);
        AttributeValue  move_ActionRenderer_param_toRow_value= new AttributeValue(null,0,null,null,null,null,null,move_ActionRenderer_param_4,move_ActionRenderer_param_4,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_toRow_value);

        Attribute move_ActionRenderer_param_5 =new Attribute("FromCol",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_5);
        AttributeValue  move_ActionRenderer_param_FromCol_value= new AttributeValue(null,0,null,null,null,null,null,move_ActionRenderer_param_5,move_ActionRenderer_param_5,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_FromCol_value);

        Attribute move_ActionRenderer_param_6 =new Attribute("toCol",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Integer,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_6);
        AttributeValue  move_ActionRenderer_param_toCol_value= new AttributeValue(null,0,null,null,null,null,null,move_ActionRenderer_param_6,move_ActionRenderer_param_6,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_toCol_value);

        Attribute move_ActionRenderer_param_7 =new Attribute("ObjectId",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.Long,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_7);
        AttributeValue  move_ActionRenderer_param_ObjectId_value= new AttributeValue(null,null,0L,null,null,null,null,move_ActionRenderer_param_7,move_ActionRenderer_param_7,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_ObjectId_value);

        Attribute move_ActionRenderer_param_8 =new Attribute("moveType",move_Renderer.getId(),AttributeOwnerType.Action_Renderer_Parameter,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(move_ActionRenderer_param_8);
        AttributeValue  move_ActionRenderer_param_moveType_value= new AttributeValue(null,null,null,"jump",null,null,null,move_ActionRenderer_param_8,move_ActionRenderer_param_8,1L,now,now,admin_1,admin_1);
        attributeValueService.save(move_ActionRenderer_param_moveType_value);

        ALCityObject textObject = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"TextObject",objectCategory_TextObject,textObject_pic,textObject_icon);
        alCityObjectService.save(textObject);

        PuzzleObjectAction textObject_Create_Action = new PuzzleObjectAction(POActionOwnerType.ALCity_Object,textObject.getId(),ObjectAction.Create,create_Renderer,1L,now,now,admin_1,admin_1);
        puzzleObjectActionService.save(textObject_Create_Action);
        DTOUtil.copyAttributesActionFromTo(create_Renderer.getId(),textObject_Create_Action.getId(),AttributeOwnerType.Action_Renderer_Parameter,AttributeOwnerType.AlCity_Object,
                                 attributeService,attributeValueService);

        PuzzleObjectAction textObject_Show_Action = new PuzzleObjectAction(POActionOwnerType.ALCity_Object,textObject.getId(),ObjectAction.Show,show_Renderer,1L,now,now,admin_1,admin_1);
        puzzleObjectActionService.save(textObject_Show_Action);
        DTOUtil.copyAttributesActionFromTo(show_Renderer.getId(),textObject_Show_Action.getId(),AttributeOwnerType.Action_Renderer_Parameter,AttributeOwnerType.AlCity_Object,
                attributeService,attributeValueService);


        Attribute alCity_object_property_1 =new Attribute("text",textObject.getId(),AttributeOwnerType.AlCity_Object_Property,DataType.String,1L,now,now,admin_1,admin_1);
        attributeService.save(alCity_object_property_1);
        AttributeValue  object_property_1_value= new AttributeValue(null,null,null,"",null,null,null,alCity_object_property_1,alCity_object_property_1,1L,now,now,admin_1,admin_1);
        attributeValueService.save(object_property_1_value);




        ALCityObject ImageObject01 = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"ImageObject01",objectCategory_Image,image_Object_pic,image_Object_pic);
        alCityObjectService.save(ImageObject01);

        PuzzleObjectAction ImageObject01_Move_Action = new PuzzleObjectAction(POActionOwnerType.ALCity_Object,ImageObject01.getId(),ObjectAction.Move,move_Renderer,1L,now,now,admin_1,admin_1);
        puzzleObjectActionService.save(ImageObject01_Move_Action);
        DTOUtil.copyAttributesActionFromTo(move_Renderer.getId(),ImageObject01_Move_Action.getId(),AttributeOwnerType.Action_Renderer_Parameter,AttributeOwnerType.AlCity_Object,
                attributeService,attributeValueService);

        ALCityObject ImageObject02 = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"ImageObject02",objectCategory_Image,image_Object_pic,image_Object_icon);
        alCityObjectService.save(ImageObject02);

        ALCityObject eagle = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"eagle",objectCategory_bird,eagle_Image_binary_content,eagle_Image_binary_content);
        alCityObjectService.save(eagle);

        ALCityObject goose = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"Goose",objectCategory_bird,goose_Image_binary_content,goose_Image_binary_content);
        alCityObjectService.save(goose);

        ALCityObject fox = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"Fox",objectCategory_Mamals,fox_Image_binary_content,fox_Image_binary_content);
        alCityObjectService.save(fox);

        ALCityObject wheat = new ALCityObject(1L,now,now,jalalHoseyni,jalalHoseyni,"Wheat",objectCategory_cereal,wheat_Image_binary_content,wheat_Image_binary_content);
        alCityObjectService.save(wheat);

    }
}
