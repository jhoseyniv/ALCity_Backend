package com.alcity.importdata;


import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningSkill_LearningTopic;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.service.Journey.JourneyLearningSkillService;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.alobject.ObjectActionService;
import com.alcity.service.alobject.ObjectCategoryService;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningSkill_LearningTopicService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.ApplicationMember_WalletItemService;
import com.alcity.service.users.WalletItemService;
import com.alcity.service.users.WalletTransactionService;
import com.alcity.utility.ImageUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


@Order(value=1)
@Component
public class ImportBaseData_1 implements CommandLineRunner {

    @Autowired
    private WalletItemTypeService walletItemTypeService;
    @Autowired
    private ApplicationMemberService applicationMemberService;
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
    private ApplicationMember_WalletItemService applicationMember_walletItemService;

    @Autowired
    private WalletTransactionService walletTransactionService;

    @Autowired
    private DataTypeService dataTypeService;


    @Autowired
    private LearningSkillService learningSkillService;

    @Autowired
    private LearningTopicService learningTopicService;

    @Autowired
    LearningSkill_LearningTopicService learningSkill_LearningTopicService;

    @Autowired
    private PuzzleLevelPrivacyService puzzleLevelPrivacyService;


    @Autowired
    BinaryContentTypeService binaryContentTypeService;

    @Autowired
    BinaryContentService binaryContentService;

    @Autowired
    JourneyService journeyService;

    @Autowired
    JourneyLearningSkillService journeyLearningSkillService;


    @Autowired
    ObjectActionService objectActionService;

     protected final Log log = LogFactory.getLog(getClass());


    @Override
    public void run(String... args) throws Exception {

        log.info("Start Application...Import Problem 1");
        System.out.println("...Import Problem 1");

        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);

        ZonedDateTime  createdDate= ZonedDateTime.now();
        Long now = createdDate.toEpochSecond();


        byte[] avatar = ImageUtil.getImage("src/main/resources/images/","avatar.png");

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

        ApplicationMember admin_1= new ApplicationMember(35,"admin","admin","admin0","0912350550","j_hoseyni@yahoo.com",avatar,UserGender.Male,administrator,1L,now,now,null,null);
        Set clientTypeSet = new HashSet<ClientType>();
        clientTypeSet.add(web);
        admin_1.setClientTypeSet(clientTypeSet);
        applicationMemberService.save(admin_1);


        ApplicationMember jalalHoseyni= new ApplicationMember(35,"jalal","jalal","jalal","0912350550","j_hoseyni@yahoo.com",avatar,UserGender.Male,guest,1L,now,now,null,null);
        Set jalalClientTypeSet = new HashSet<ClientType>();
        jalalClientTypeSet.add(mobile);
        jalalHoseyni.setClientTypeSet(jalalClientTypeSet);
        applicationMemberService.save(jalalHoseyni);

        ApplicationMember Moslem_Balavandi= new ApplicationMember(35,"moslem","moslem","moslem","0912350550","balavandi@gmail.com",avatar,UserGender.Male,guest,1L,now,now,null,null);
        Set moslem_ClientTypeSet = new HashSet<ClientType>();
        moslem_ClientTypeSet.add(mobile);
        moslem_ClientTypeSet.add(web);
        Moslem_Balavandi.setClientTypeSet(moslem_ClientTypeSet);
        applicationMemberService.save(Moslem_Balavandi);

        ApplicationMember alireza_zarei= new ApplicationMember(35,"alireza","alireza","alireza","0912350550","zare@sharif.edu",avatar,UserGender.Male,guest,1L,now,now,null,null);
        Set alireza_ClientTypeSet = new HashSet<ClientType>();
        alireza_ClientTypeSet.add(mobile);
        alireza_ClientTypeSet.add(web);
        alireza_ClientTypeSet.add(tablet);
        alireza_zarei.setClientTypeSet(alireza_ClientTypeSet);
        applicationMemberService.save(alireza_zarei);



        BinaryContentType imageType= new BinaryContentType("image","image",1L,now,now,admin_1,admin_1);
        BinaryContentType fileType= new BinaryContentType("file","file",1L,now,now,admin_1,admin_1);
        BinaryContentType videoType= new BinaryContentType("video","video",1L,now,now,admin_1,admin_1);
        BinaryContentType iconType= new BinaryContentType("icon","icon",1L,now,now,admin_1,admin_1);
        BinaryContentType voiceType= new BinaryContentType("voice","voice",1L,now,now,admin_1,admin_1);
        binaryContentTypeService.save(imageType);
        binaryContentTypeService.save(fileType);
        binaryContentTypeService.save(videoType);
        binaryContentTypeService.save(iconType);
        binaryContentTypeService.save(voiceType);


        byte[] tetherIcon = ImageUtil.getImage("src/main/resources/images/","Tether.png");
        byte[] carIcon = ImageUtil.getImage("src/main/resources/images/","car.png");

        WalletItemType fiat = new WalletItemType(WalletItemCategory.CryptoCurrency,"fiat","fiat",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType crypto = new WalletItemType(WalletItemCategory.CryptoCurrency,"crypto","crypto",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType alCoin = new WalletItemType(WalletItemCategory.AL_Coin,"al_coin","al_coin",Boolean.TRUE,1L,now,now,admin_1,admin_1);
        WalletItemType cityObject = new WalletItemType(WalletItemCategory.Object,"cityObject","cityObject",Boolean.FALSE,1L,now,now,admin_1,admin_1);

        walletItemTypeService.save(fiat);
        walletItemTypeService.save(crypto);
        walletItemTypeService.save(alCoin);
        walletItemTypeService.save(cityObject);

       WalletItem teterWalletItem= new WalletItem(fiat,tetherIcon,"tether","tether",1L,now,now,admin_1,admin_1);
        WalletItem alCoin10WalletItem= new WalletItem(alCoin,tetherIcon,"al_coin_10","al_coin_10",1L,now,now,admin_1,admin_1);
        WalletItem alCoin_100_WalletItem= new WalletItem(alCoin,tetherIcon,"al_coin_100","al_coin_100",1L,now,now,admin_1,admin_1);
        WalletItem carWalletItem= new WalletItem(cityObject,carIcon,"car object","car_object",1L,now,now,admin_1,admin_1);
        WalletItem TVWalletItem= new WalletItem(cityObject,carIcon,"TV object","TV_object",1L,now,now,admin_1,admin_1);

        walletItemService.save(teterWalletItem);
        walletItemService.save(alCoin10WalletItem);
        walletItemService.save(carWalletItem);
        walletItemService.save(alCoin_100_WalletItem);
        walletItemService.save(TVWalletItem);

        ApplicationMember_WalletItem jalalHoseyni_alcoin_10= new ApplicationMember_WalletItem(jalalHoseyni,alCoin10WalletItem,10f,1L,now,now,admin_1,admin_1);
        ApplicationMember_WalletItem jalalHoseyni_carObject_20= new ApplicationMember_WalletItem(jalalHoseyni,carWalletItem,20f,1L,now,now,admin_1,admin_1);
        applicationMember_walletItemService.save(jalalHoseyni_alcoin_10);
        applicationMember_walletItemService.save(jalalHoseyni_carObject_20);

        ApplicationMember_WalletItem admin_1_alcoin_10= new ApplicationMember_WalletItem(admin_1,alCoin10WalletItem,10f,1L,now,now,admin_1,admin_1);
        ApplicationMember_WalletItem admin_1_carObject_10= new ApplicationMember_WalletItem(admin_1,carWalletItem,20f,1L,now,now,admin_1,admin_1);
        applicationMember_walletItemService.save(admin_1_alcoin_10);
        applicationMember_walletItemService.save(admin_1_carObject_10);

        WalletTransaction transaction1 = new WalletTransaction(now,1.5f,Boolean.TRUE,"desc",jalalHoseyni_alcoin_10,1L,now,now,admin_1,admin_1);
        WalletTransaction transaction2 = new WalletTransaction(now,1f,Boolean.TRUE,"desc",jalalHoseyni_carObject_20,1L,now,now,admin_1,admin_1);
        walletTransactionService.save(transaction1);
        walletTransactionService.save(transaction2);



        PuzzleCategory mathematic = new PuzzleCategory("mathematic","mathematic",1L,now,now,admin_1,admin_1);
        PuzzleCategory  physic = new PuzzleCategory("physic","physic",1L,now,now,admin_1,admin_1);
        PuzzleCategory  Maze = new PuzzleCategory("Maze","Maze",1L,now,now,admin_1,admin_1);
        PuzzleCategory  IQ = new PuzzleCategory("IQ","IQ",1L,now,now,admin_1,admin_1);
        puzzleCategoryService.save(mathematic);
        puzzleCategoryService.save(physic);
        puzzleCategoryService.save(IQ);
        puzzleCategoryService.save(Maze);


        ObjectCategory objectCategory_animal = new ObjectCategory("Animal","Animal",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_bird = new ObjectCategory("Bird","Bird",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_cereal = new ObjectCategory("cereal","cereal",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_Image = new ObjectCategory("Image","Image",1L,now,now,admin_1,admin_1);
        ObjectCategory objectCategory_Mamals = new ObjectCategory("Mamals","Mamals",1L,now,now,admin_1,admin_1);
        objectCategoryService.save(objectCategory_animal);
        objectCategoryService.save(objectCategory_bird);
        objectCategoryService.save(objectCategory_cereal);
        objectCategoryService.save(objectCategory_Image);
        objectCategoryService.save(objectCategory_Mamals);


        byte[] goose_Image = ImageUtil.getImage("src/main/resources/images/","goose.png");
        BinaryContent goose_Image_binary_content = new BinaryContent("goose image",goose_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(goose_Image_binary_content);

        byte[] eagle_Image = ImageUtil.getImage("src/main/resources/images/","eagle.png");
        BinaryContent eagle_Image_binary_content = new BinaryContent("eagle image",eagle_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(eagle_Image_binary_content);

        byte[] fox_Image = ImageUtil.getImage("src/main/resources/images/","fox.png");
        BinaryContent fox_Image_binary_content = new BinaryContent("fox image",fox_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(fox_Image_binary_content);

        byte[] wheat_Image = ImageUtil.getImage("src/main/resources/images/","wheat.png");
        BinaryContent wheat_Image_binary_content = new BinaryContent("wheat image",wheat_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(wheat_Image_binary_content);

        byte[] image_object = ImageUtil.getImage("src/main/resources/images/","image_object.png");
        BinaryContent image_object_binary_content = new BinaryContent("image_object",image_object,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(image_object_binary_content);





        DataType alcity_Int = new DataType("Integer","Integer",1L,now,now,admin_1,admin_1);
        DataType alcity_Long =new DataType("Long","Long",1L,now,now,admin_1,admin_1);
        DataType alcity_Boolean = new DataType("Boolean","Boolean",1L,now,now,admin_1,admin_1);
        DataType alcity_String = new DataType("String","String",1L,now,now,admin_1,admin_1);
        DataType alcity_Binary = new DataType("Binary","Binary",1L,now,now,admin_1,admin_1);

        dataTypeService.save(alcity_Int);
        dataTypeService.save(alcity_Long);
        dataTypeService.save(alcity_Boolean);
        dataTypeService.save(alcity_String);
        dataTypeService.save(alcity_Binary);


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

        learningSkillService.save(constraint);
        learningSkillService.save(loop);
        learningSkillService.save(timeManagement);
        learningSkillService.save(division);
        learningSkillService.save(fast_descioin);
        learningSkillService.save(find_paths);

        LearningTopic root_Topic = new LearningTopic("Root Topic",null,1L,now,now,admin_1,admin_1);
        LearningTopic hashImage_Topic = new LearningTopic("Hash Image",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic magic_matrix_Topic = new LearningTopic("Magic Matrix",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic maze_table_Topic = new LearningTopic("Maze Table",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic algorithm_Topic = new LearningTopic("Algorithm",root_Topic,1L,now,now,admin_1,admin_1);
        LearningTopic routing_in_the_table = new LearningTopic("Routing in the Table",root_Topic,1L,now,now,admin_1,admin_1);
        learningTopicService.save(root_Topic);
        learningTopicService.save(hashImage_Topic);
        learningTopicService.save(magic_matrix_Topic);
        learningTopicService.save(algorithm_Topic);
        learningTopicService.save(maze_table_Topic);
        learningTopicService.save(routing_in_the_table);


        LearningSkill_LearningTopic learningSkill_learningTopic_1 = new LearningSkill_LearningTopic("title1",timeManagement,algorithm_Topic,1L,now,now,admin_1,admin_1);
        LearningSkill_LearningTopic learningSkill_learningTopic_2 = new LearningSkill_LearningTopic("title2",division,algorithm_Topic,1L,now,now,admin_1,admin_1);
        learningSkill_LearningTopicService.save(learningSkill_learningTopic_1);
        learningSkill_LearningTopicService.save(learningSkill_learningTopic_2);


        byte[] jouerny_1_Image = ImageUtil.getImage("src/main/resources/images/","jungle.png");
        byte[] jouerny_2_Image = ImageUtil.getImage("src/main/resources/images/","desert.png");
        byte[] jouerny_3_Image = ImageUtil.getImage("src/main/resources/images/","city.png");

        BinaryContent image_journey_1 = new BinaryContent("image_journey_1",jouerny_1_Image,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_journey_2 = new BinaryContent("image_journey_2",jouerny_2_Image,imageType,1L,now,now,admin_1,admin_1);
        BinaryContent image_journey_3 = new BinaryContent("image_journey_3",jouerny_3_Image,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(image_journey_1);
        binaryContentService.save(image_journey_2);
        binaryContentService.save(image_journey_3);

        Journey journey_1 = new Journey("Journey_1",image_journey_1,1L,now,now,admin_1,admin_1);
        Journey journey_2 = new Journey("Journey_2",image_journey_2,1L,now,now,admin_1,admin_1);
        Journey journey_3 = new Journey("Journey_3",image_journey_3,1L,now,now,admin_1,admin_1);
        journeyService.save(journey_1);
        journeyService.save(journey_2);
        journeyService.save(journey_3);

        JourneyLearningSkill journey_1_Skill_1 = new JourneyLearningSkill(0.5f,journey_1,timeManagement,1L,now,now,admin_1,admin_1);
        JourneyLearningSkill journey_1_Skill_2 = new JourneyLearningSkill(0.5f,journey_1,division,1L,now,now,admin_1,admin_1);
        journeyLearningSkillService.save(journey_1_Skill_1);
        journeyLearningSkillService.save(journey_1_Skill_2);

//        GameStatus gameStatus_1 = new GameStatus("gameStatus_1","gameStatus_1",1L,now,now,admin_1,admin_1);
//        GameStatus gameStatus_2 = new GameStatus("gameStatus_2","gameStatus_2",1L,now,now,admin_1,admin_1);
//        GameStatus gameStatus_3 = new GameStatus("gameStatus_3","gameStatus_3",1L,now,now,admin_1,admin_1);
//        gameStatusService.save(gameStatus_1);
//        gameStatusService.save(gameStatus_2);
//        gameStatusService.save(gameStatus_3);

        ObjectAction moveAction= new ObjectAction("Move","Move",1L,now,now,admin_1,admin_1);
        ObjectAction removeAction= new ObjectAction("Remove","Remove",1L,now,now,admin_1,admin_1);
        ObjectAction rotateAction= new ObjectAction("Rotate","Rotate",1L,now,now,admin_1,admin_1);
        ObjectAction showAction= new ObjectAction("Show","Show",1L,now,now,admin_1,admin_1);
        ObjectAction hideAction= new ObjectAction("Hide","Hide",1L,now,now,admin_1,admin_1);
        ObjectAction enableAction= new ObjectAction("Enable","Enable",1L,now,now,admin_1,admin_1);
        ObjectAction disableAction= new ObjectAction("Disable","Disable",1L,now,now,admin_1,admin_1);
        ObjectAction createAction= new ObjectAction("Create","Create",1L,now,now,admin_1,admin_1);
        ObjectAction convertAction= new ObjectAction("Convert","Convert",1L,now,now,admin_1,admin_1);
        ObjectAction playSoundAction= new ObjectAction("PlaySound","PlaySound",1L,now,now,admin_1,admin_1);
        objectActionService.save(moveAction);
        objectActionService.save(removeAction);
        objectActionService.save(rotateAction);
        objectActionService.save(enableAction);
        objectActionService.save(disableAction);
        objectActionService.save(hideAction);
        objectActionService.save(createAction);
        objectActionService.save(convertAction);
        objectActionService.save(showAction);
        objectActionService.save(playSoundAction);
    }
}
