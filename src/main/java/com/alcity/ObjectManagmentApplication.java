package com.alcity;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.entity.alobject.*;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningSkill_LearningTopic;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.ruleengine.SystemEvent;
import com.alcity.entity.ruleengine.UserEvent;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.repository.base.DataTypeRepository;
import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyLearningSkillService;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.Journey.JourneyStepService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningSkill_LearningTopicService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.ruleengine.SystemEventService;
import com.alcity.service.ruleengine.UserEventService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.ApplicationMember_WalletItemService;
import com.alcity.service.users.WalletItemService;
import com.alcity.service.users.WalletTransactionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class ObjectManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObjectManagmentApplication.class, args);
	}


	@Autowired
	private UserGenderService userGenderService;

	@Autowired
	private MemberTypeService memberTypeService;

	@Autowired
	private ApplicationMemberService applicationMemberService;

	@Autowired
	private ClientTypeService clientTypeService;

	@Autowired
	private PuzzleCategoryService puzzleCategoryService;

	@Autowired
	private DataTypeRepository dataTypeRepository;

	@Autowired
	private PuzzleDifficultyService puzzleDifficultyService;

	@Autowired
	private PuzzleLevelStatusService puzzleLevelStatusService;

	@Autowired
	private PuzzleLevelPrivacyService puzzleLevelPrivacyService;

	@Autowired
	private WalletItemTypeService walletItemTypeService;

	@Autowired
	private WalletItemService walletItemService;

	@Autowired
	private ApplicationMember_WalletItemService applicationMember_walletItemService;

	@Autowired
 	private WalletTransactionService  walletTransactionService;

	@Autowired
	private  BinaryContentTypeService  binaryContentTypeService;

	@Autowired
	private  BinaryContentService  binaryContentService;

	@Autowired
	private JourneyService journeyService;

	@Autowired
	private JourneyStepService journeyStepService;

	@Autowired
	private PuzzleGroupService puzzleGroupService;

	@Autowired
	private LearningSkillService learningSkillService;

	@Autowired
	private LearningTopicService learningTopicService;


	@Autowired
	JourneyLearningSkillService journeyLearningSkillService;

	@Autowired
	LearningSkill_LearningTopicService learningSkill_LearningTopicService;

	@Autowired
	PuzzleSkillLearningContentService puzzleSkillLearningContentService;

	@Autowired
	PuzzleLevelObjectiveService puzzleLevelObjectiveService;

	@Autowired
	LearningContentService learningContentService;

	@Autowired
	PuzzleLevelService puzzleLevelService;

	@Autowired
	PuzzleLevelLearningTopicService puzzleLevelLearningTopicService;

	@Autowired
	PermitedPlayerRepository permitedPlayerRepository;

	@Autowired
	PlayHistoryService playHistoryService;

	@Autowired
	PuzzleLevelGroundService  puzzleLevelGroundService;

	@Autowired
	GameStatusService gameStatusService;

	@Autowired
	PuzzleLevelGameInstanceService puzzleLevelGameInstanceService;

	@Autowired
	ObjectCategoryService objectCategoryService;

	@Autowired
	PuzzleObjectService puzzleObjectService;

	@Autowired
	PuzzleGroup_PuzzleObjectService puzzleGroup_PuzzleObjectService;

	@Autowired
	ObjectActionService objectActionService;

	@Autowired
	PuzzleObjectActionOwnerTypeService puzzleObjectActionOwnerTypeService;


	@Autowired
	UserEventService userEventService;

	@Autowired
	SystemEventService systemEventService;

	@Autowired
	AttributeOwnerTypeService attributeOwnerTypeService;

	@Autowired
	PuzzleGroupObjectInstanceService puzzleGroupObjectInstanceService;

	@Autowired
	ALCityAttributeService alCityAttributeService;

	@Autowired
	ALCityAttributeValueService alCityAttributeValueService;


	@Autowired
	ActionRendererService actionRendererService;
	@Autowired
	PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;

	@Autowired
	PuzzleLevelRuleEventTypeService  puzzleLevelRuleEventTypeService;


	public byte[] getImage(String imageDirectory, String imageName) throws IOException {
		Path imagePath = Path.of(imageDirectory, imageName);

		if (Files.exists(imagePath)) {
			byte[] imageBytes = Files.readAllBytes(imagePath);
			return imageBytes;
		} else {
			return null; // Handle missing images
		}
	}

	@Autowired
	private Environment environment;

	private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			;
			log.info("Start Application...Tassk Management");
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
			ZonedDateTime startDate = ZonedDateTime.now();
			ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);
			byte[] avatar = getImage("src/main/resources/images/","avatar.png");

			ZonedDateTime  createdDate= ZonedDateTime.now();
			Long now = createdDate.toInstant().toEpochMilli();



			UserGender female = new UserGender("F","Female",1L,now,now,null,null);
			UserGender male = new UserGender("M","Male",1L,now,now,null,null);
			userGenderService.save(female);
			userGenderService.save(male);

			MemberType puzzlePlayer = new MemberType("Puzzle Player","Puzzle_Player",1L,now,now,null,null);
			memberTypeService.save(puzzlePlayer);

			MemberType guest = new MemberType("Guest","Guest",1L,now,now,null,null);
			memberTypeService.save(guest);

			MemberType puzzleCreator = new MemberType("Puzzle Creator","Puzzle_Creator",1L,now,now,null,null);
			memberTypeService.save(puzzleCreator);

			MemberType administrator = new MemberType("Administrator","Administrator",1L,now,now,null,null);
			memberTypeService.save(administrator);

			ClientType  mobile = new ClientType("mobile","mobile",1L,now,now,null,null);
			ClientType  web = new ClientType("web","web",1L,now,now,null,null);
			ClientType  tablet = new ClientType("tablet","tablet",1L,now,now,null,null);
			clientTypeService.save(mobile);
			clientTypeService.save(web);
			clientTypeService.save(tablet);

			//ApplicationMember admin_1= new ApplicationMember(35,"admin","admin","admin","09123580100","jhoseyni_yahoo.com",avatar,male,administrator,now,now,null,null);
			ApplicationMember admin_1= new ApplicationMember(35,"admin","admin","admin0","0912350550","j_hoseyni@yahoo.com",avatar,male,administrator,1L,now,now,null,null);
			Set clientTypeSet = new HashSet<ClientType>();
			clientTypeSet.add(web);
			admin_1.setClientTypeSet(clientTypeSet);
			applicationMemberService.save(admin_1);

			PuzzleCategory  mathematic = new PuzzleCategory("mathematic","mathematic",1L,now,now,admin_1,admin_1);
			PuzzleCategory  physic = new PuzzleCategory("physic","physic",1L,now,now,admin_1,admin_1);
			PuzzleCategory  IQ = new PuzzleCategory("IQ","IQ",1L,now,now,admin_1,admin_1);
			puzzleCategoryService.save(mathematic);
			puzzleCategoryService.save(physic);
			puzzleCategoryService.save(IQ);

			DataType alcity_Int = new DataType("Integer","Integer",1L,now,now,admin_1,admin_1);
			DataType alcity_Long =new DataType("Long","Long",1L,now,now,admin_1,admin_1);
			DataType alcity_Boolean = new DataType("Boolean","Boolean",1L,now,now,admin_1,admin_1);
			DataType alcity_String = new DataType("String","String",1L,now,now,admin_1,admin_1);
			DataType alcity_Binary = new DataType("Binary","Binary",1L,now,now,admin_1,admin_1);

			dataTypeRepository.save(alcity_Int);
			dataTypeRepository.save(alcity_Long);
			dataTypeRepository.save(alcity_Boolean);
			dataTypeRepository.save(alcity_String);
			dataTypeRepository.save(alcity_Binary);

			PuzzleLevelDifficulty easy= new PuzzleLevelDifficulty("Easy","Easy",1L,now,now,admin_1,admin_1);
			PuzzleLevelDifficulty medium= new PuzzleLevelDifficulty("Medium","Medium",1L,now,now,admin_1,admin_1);
			PuzzleLevelDifficulty hard= new PuzzleLevelDifficulty("Hard","Hard",1L,now,now,admin_1,admin_1);

			puzzleDifficultyService.save(easy);
			puzzleDifficultyService.save(medium);
			puzzleDifficultyService.save(hard);

			PuzzleLevelStatus compeleted = new PuzzleLevelStatus("Completed","Completed",1L,now,now,admin_1,admin_1);
			PuzzleLevelStatus canceled = new PuzzleLevelStatus("canceled","canceled",1L,now,now,admin_1,admin_1);
			PuzzleLevelStatus ongoing = new PuzzleLevelStatus("ongoing","ongoing",1L,now,now,admin_1,admin_1);
			puzzleLevelStatusService.save(compeleted);
			puzzleLevelStatusService.save(canceled);
			puzzleLevelStatusService.save(ongoing);

			PuzzleLevelPrivacy privacy_1 = new PuzzleLevelPrivacy("privacy 1","privacy1",1L,now,now,admin_1,admin_1);
			PuzzleLevelPrivacy privacy_2 = new PuzzleLevelPrivacy("privacy 2","privacy2",1L,now,now,admin_1,admin_1);
			PuzzleLevelPrivacy privacy_3 = new PuzzleLevelPrivacy("privacy 3","privacy3",1L,now,now,admin_1,admin_1);
			puzzleLevelPrivacyService.save(privacy_1);
			puzzleLevelPrivacyService.save(privacy_2);
			puzzleLevelPrivacyService.save(privacy_3);

			LearningSkill constraint = new LearningSkill("Constraint","Constraint",1L,now,now,admin_1,admin_1);
			LearningSkill loop = new LearningSkill("loop","loop",1L,now,now,admin_1,admin_1);
			LearningSkill timeManagement = new LearningSkill("time Management","timeManagement",1L,now,now,admin_1,admin_1);
			LearningSkill division = new LearningSkill("division","division",1L,now,now,admin_1,admin_1);

			learningSkillService.save(constraint);
			learningSkillService.save(loop);
			learningSkillService.save(timeManagement);
			learningSkillService.save(division);

			LearningTopic root_Topic = new LearningTopic("Root Topic",null,1L,now,now,admin_1,admin_1);
			LearningTopic hashImage_Topic = new LearningTopic("Hash Image",root_Topic,1L,now,now,admin_1,admin_1);
			LearningTopic magic_matrix_Topic = new LearningTopic("Magic Matrix",root_Topic,1L,now,now,admin_1,admin_1);
			LearningTopic algorithm_Topic = new LearningTopic("Magic Matrix",root_Topic,1L,now,now,admin_1,admin_1);
			learningTopicService.save(root_Topic);
			learningTopicService.save(hashImage_Topic);
			learningTopicService.save(magic_matrix_Topic);
			learningTopicService.save(algorithm_Topic);


			LearningSkill_LearningTopic learningSkill_learningTopic_1 = new LearningSkill_LearningTopic("title1",timeManagement,algorithm_Topic,1L,now,now,admin_1,admin_1);
			LearningSkill_LearningTopic learningSkill_learningTopic_2 = new LearningSkill_LearningTopic("title2",division,algorithm_Topic,1L,now,now,admin_1,admin_1);
			learningSkill_LearningTopicService.save(learningSkill_learningTopic_1);
			learningSkill_LearningTopicService.save(learningSkill_learningTopic_2);


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

			WalletItemType fiat = new WalletItemType("fiat","fiat",Boolean.TRUE,1L,now,now,admin_1,admin_1);
			WalletItemType crypto = new WalletItemType("crypto","crypto",Boolean.TRUE,1L,now,now,admin_1,admin_1);
			WalletItemType alCoin = new WalletItemType("al_coin","al_coin",Boolean.TRUE,1L,now,now,admin_1,admin_1);
			WalletItemType cityObject = new WalletItemType("cityObject","cityObject",Boolean.FALSE,1L,now,now,admin_1,admin_1);

			walletItemTypeService.save(fiat);
			walletItemTypeService.save(crypto);
			walletItemTypeService.save(alCoin);
			walletItemTypeService.save(cityObject);

			byte[] tetherIcon = getImage("src/main/resources/images/","Tether.png");
			byte[] carIcon = getImage("src/main/resources/images/","car.png");

			WalletItem teterWalletItem= new WalletItem(fiat,tetherIcon,"tether","tether",1L,now,now,admin_1,admin_1);
			WalletItem alCoin10WalletItem= new WalletItem(alCoin,tetherIcon,"al_coin_10","al_coin_10",1L,now,now,admin_1,admin_1);
			WalletItem carWalletItem= new WalletItem(cityObject,carIcon,"car object","car_object",1L,now,now,admin_1,admin_1);

			walletItemService.save(teterWalletItem);
			walletItemService.save(alCoin10WalletItem);
			walletItemService.save(carWalletItem);

			ApplicationMember jalalHoseyni= new ApplicationMember(35,"jalal","jalal","jalal","0912350550","j_hoseyni@yahoo.com",avatar,male,guest,1L,now,now,null,null);
			Set jalalClientTypeSet = new HashSet<ClientType>();
			clientTypeSet.add(mobile);
			jalalHoseyni.setClientTypeSet(jalalClientTypeSet);
			applicationMemberService.save(jalalHoseyni);

			ApplicationMember_WalletItem jalalHoseyni_alcoin_10= new ApplicationMember_WalletItem(jalalHoseyni,alCoin10WalletItem,10f,1L,now,now,admin_1,admin_1);
			ApplicationMember_WalletItem jalalHoseyni_carObject_10= new ApplicationMember_WalletItem(jalalHoseyni,carWalletItem,20f,1L,now,now,admin_1,admin_1);
			applicationMember_walletItemService.save(jalalHoseyni_alcoin_10);
			applicationMember_walletItemService.save(jalalHoseyni_carObject_10);

			WalletTransaction transaction1 = new WalletTransaction(now,1.5f,Boolean.TRUE,"desc",jalalHoseyni_alcoin_10,1L,now,now,jalalHoseyni,jalalHoseyni);
			WalletTransaction transaction2 = new WalletTransaction(now,1f,Boolean.TRUE,"desc",jalalHoseyni_carObject_10,1L,now,now,jalalHoseyni,jalalHoseyni);
			walletTransactionService.save(transaction1);
			walletTransactionService.save(transaction2);

			byte[] jouerny_1_Image = getImage("src/main/resources/images/","jungle.png");
			byte[] jouerny_2_Image = getImage("src/main/resources/images/","desert.png");
			byte[] jouerny_3_Image = getImage("src/main/resources/images/","city.png");

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

			byte[] puzzle_Ground_Image_1 = getImage("src/main/resources/images/","playGround.png");
			BinaryContent puzzle_ground_binary_content_1 = new BinaryContent("puzzle ground for hash image",puzzle_Ground_Image_1,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_ground_binary_content_1);


			byte[] puzzle_group_Icon_1 = getImage("src/main/resources/images/","puzzle_group_1.png");
			BinaryContent puzzle_group_binary_content_1 = new BinaryContent("image_puzzle_group_matematic",puzzle_group_Icon_1,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_binary_content_1);

			byte[] puzzle_group_Icon_2 = getImage("src/main/resources/images/","physic.png");
			BinaryContent puzzle_group_binary_content_2 = new BinaryContent("image_puzzle_group_physic",puzzle_group_Icon_2,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_binary_content_2);

			byte[] puzzle_group_Icon_3 = getImage("src/main/resources/images/","IQ.png");
			BinaryContent puzzle_group_binary_content_3 = new BinaryContent("image_puzzle_group_IQ",puzzle_group_Icon_3,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_binary_content_3);

			byte[] puzzle_group_Hash_Image = getImage("src/main/resources/images/","hashImage.png");
			BinaryContent puzzle_group_Hash_Image_binary_content = new BinaryContent("hashImage",puzzle_group_Hash_Image,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_Hash_Image_binary_content);


			LearningContent learningContent_Division=new LearningContent("help to math","this content is about hash images",puzzle_group_Hash_Image_binary_content,1L,now,now,admin_1,admin_1);
			learningContentService.save(learningContent_Division);

			PuzzleGroup puzzleGroup_1 = new PuzzleGroup("Hash Image - Puzzle Group 1",mathematic,puzzle_group_binary_content_1,puzzle_group_binary_content_1,1L,now,now,admin_1,admin_1);
			PuzzleGroup puzzleGroup_2 = new PuzzleGroup("Physics Puzzle Group",physic,puzzle_group_binary_content_2,puzzle_group_binary_content_3,1L,now,now,admin_1,admin_1);
			PuzzleGroup puzzleGroup_3 = new PuzzleGroup("IQ Puzzle Group",IQ,puzzle_group_binary_content_3,puzzle_group_binary_content_3,1L,now,now,admin_1,admin_1);
			puzzleGroupService.save(puzzleGroup_1);
			puzzleGroupService.save(puzzleGroup_2);
			puzzleGroupService.save(puzzleGroup_3);


			JourneyStep journey_1_Step_1 = new JourneyStep("step1_journey_1",1,30,30,journey_1,puzzleGroup_1,1L,now,now,admin_1,admin_1);
			JourneyStep journey_1_Step_2 = new JourneyStep("step2_journey_1",1,30,30,journey_1,puzzleGroup_1,1L,now,now,admin_1,admin_1);
			JourneyStep journey_2_Step_1 = new JourneyStep("step1_journey_2",1,30,30,journey_2,puzzleGroup_1,1L,now,now,admin_1,admin_1);
			journeyStepService.save(journey_1_Step_1);
			journeyStepService.save(journey_1_Step_2);
			journeyStepService.save(journey_2_Step_1);


			PuzzleSkillLearningContent puzzleSkillLearningContent_1 = new PuzzleSkillLearningContent(division,puzzleGroup_1,learningContent_Division,1L,now,now,admin_1,admin_1);
			puzzleSkillLearningContentService.save(puzzleSkillLearningContent_1);

			PuzzleLevel puzzleLevel_hashimage = new PuzzleLevel(now,1L,"arrange hash image","HASH_IMAGe",10,14,5f,puzzleGroup_1,easy,ongoing,privacy_1,puzzle_group_binary_content_1,puzzle_group_binary_content_2,3L,now,now,admin_1,admin_1);
			puzzleLevelService.save(puzzleLevel_hashimage);

			PuzzleLevel puzzleLevel_hashimage2 = new PuzzleLevel(now,1L,"arrange hash image 2","HASH_IMAGe 2",5,8,8f,puzzleGroup_1,hard,ongoing,privacy_2,puzzle_group_binary_content_1,puzzle_group_binary_content_2, 3L,now,now,admin_1,admin_1);
			puzzleLevelService.save(puzzleLevel_hashimage2);
			CameraSetupDTO cameraSetupDTO = new CameraSetupDTO(5,5,5,5,5,5);
			PuzzleLevelGround puzzleLevel_hashImage_ground = new PuzzleLevelGround(3,3,cameraSetupDTO.getCameraSetupInfo(),puzzleLevel_hashimage,puzzle_ground_binary_content_1,1L,now,now,admin_1,admin_1);
			puzzleLevelGroundService.save(puzzleLevel_hashImage_ground);


			PermitedPlayer player_1_puzzleLevel_hashimage = new PermitedPlayer(jalalHoseyni,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			permitedPlayerRepository.save(player_1_puzzleLevel_hashimage);


			PuzzleLevel_LearningTopic puzzleLevelLearningTopic_1 = new PuzzleLevel_LearningTopic(puzzleLevel_hashimage,hashImage_Topic,learningContent_Division,1L,now,now,admin_1,admin_1);
			PuzzleLevel_LearningTopic puzzleLevelLearningTopic_2 = new PuzzleLevel_LearningTopic(puzzleLevel_hashimage,magic_matrix_Topic,learningContent_Division,1L,now,now,admin_1,admin_1);
			puzzleLevelLearningTopicService.save(puzzleLevelLearningTopic_1);
			puzzleLevelLearningTopicService.save(puzzleLevelLearningTopic_2);

			PlayHistory playHistory_1 = new PlayHistory(jalalHoseyni,puzzleLevel_hashimage,now,100,10f,1L,now,now,jalalHoseyni,jalalHoseyni);
			playHistoryService.save(playHistory_1);


			StringBuffer  condition = new StringBuffer("(img1.x == img1.targetX)&&(img1.y == img1.targetY)" +","
			 			   + "(img2.x == img2.targetX)&&(img2.y == img2.targetY)" + ","
					       + "(img3.x == img3.targetX)&&(img3.y == img3.targetY)" + ","
						   + "(img4.x == img4.targetX)&&(img4.y == img4.targetY)" + ","
						   + "(img5.x == img5.targetX)&&(img5.y == img5.targetY)" +  ","
					       + "(img6.x == img6.targetX)&&(img6.y == img6.targetY)" +	","
					       + "(img7.x == img7.targetX)&&(img7.y == img7.targetY)"+","
						   + "(img8.x == img8.targetX)&&(img8.y == img8.targetY)");

			PuzzleLevelObjective puzzleLevelObjective = new PuzzleLevelObjective("arrange image pieces","arrange image pieces as correct image",10f,2f,condition,timeManagement,alCoin10WalletItem,puzzleLevel_hashimage
																					,1L,now,now,admin_1,admin_1);
			puzzleLevelObjectiveService.save(puzzleLevelObjective);

			GameStatus gameStatus_1 = new GameStatus("gameStatus_1","gameStatus_1",1L,now,now,admin_1,admin_1);
			GameStatus gameStatus_2 = new GameStatus("gameStatus_2","gameStatus_2",1L,now,now,admin_1,admin_1);
			GameStatus gameStatus_3 = new GameStatus("gameStatus_3","gameStatus_3",1L,now,now,admin_1,admin_1);
			gameStatusService.save(gameStatus_1);
			gameStatusService.save(gameStatus_2);
			gameStatusService.save(gameStatus_3);

			PuzzleLevelGameInstance puzzleLevelGameInstance= new PuzzleLevelGameInstance(jalalHoseyni,puzzleLevel_hashimage,gameStatus_1,1L,now,now,jalalHoseyni,jalalHoseyni);
			puzzleLevelGameInstanceService.save(puzzleLevelGameInstance);

			byte[] goose_Image = getImage("src/main/resources/images/","goose.png");
			BinaryContent goose_Image_binary_content = new BinaryContent("goose image",goose_Image,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(goose_Image_binary_content);

			byte[] fox_Image = getImage("src/main/resources/images/","fox.png");
			BinaryContent fox_Image_binary_content = new BinaryContent("fox image",fox_Image,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(fox_Image_binary_content);

			byte[] wheat_Image = getImage("src/main/resources/images/","wheat.png");
			BinaryContent wheat_Image_binary_content = new BinaryContent("wheat image",wheat_Image,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(wheat_Image_binary_content);

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

			AttributeOwnerType puzzleObjectProperty = new AttributeOwnerType("PuzzleObjectProperty","PuzzleObjectProperty",1L,now,now,admin_1,admin_1);
			AttributeOwnerType puzzleGroupObjectProperty = new AttributeOwnerType("PuzzleGroupObjectProperty","PuzzleGroupObjectProperty",1L,now,now,admin_1,admin_1);
			AttributeOwnerType puzzleGroupObjectInstanceProperty = new AttributeOwnerType("PuzzleGroupObjectInstanceProperty","PuzzleGroupObjectInstanceProperty",1L,now,now,admin_1,admin_1);
			AttributeOwnerType PuzzleGroupObjectVariable = new AttributeOwnerType("PuzzleGroupObjectVariable","PuzzleGroupObjectVariable",1L,now,now,admin_1,admin_1);
			AttributeOwnerType puzzleGroupObjectInstanceVariable = new AttributeOwnerType("PuzzleGroupObjectInstanceVariable","PuzzleGroupObjectInstanceVariable",1L,now,now,admin_1,admin_1);

			attributeOwnerTypeService.save(puzzleObjectProperty);
			attributeOwnerTypeService.save(puzzleGroupObjectProperty);
			attributeOwnerTypeService.save(puzzleGroupObjectInstanceProperty);
			attributeOwnerTypeService.save(PuzzleGroupObjectVariable);
			attributeOwnerTypeService.save(puzzleGroupObjectInstanceVariable);

			PuzzleGroupObjectInstance instance_img0 = new PuzzleGroupObjectInstance(1,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img1 = new PuzzleGroupObjectInstance(2,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img2 = new PuzzleGroupObjectInstance(1,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img3 = new PuzzleGroupObjectInstance(2,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img4 = new PuzzleGroupObjectInstance(3,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img5 = new PuzzleGroupObjectInstance(1,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img6 = new PuzzleGroupObjectInstance(2,2,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img7 = new PuzzleGroupObjectInstance(3,3,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);
			PuzzleGroupObjectInstance instance_img8 = new PuzzleGroupObjectInstance(3,1,1,puzzleGroup_puzzleObject,puzzleLevel_hashimage,1L,now,now,admin_1,admin_1);

			puzzleGroupObjectInstanceService.save(instance_img0);
			puzzleGroupObjectInstanceService.save(instance_img1);
			puzzleGroupObjectInstanceService.save(instance_img2);
			puzzleGroupObjectInstanceService.save(instance_img3);
			puzzleGroupObjectInstanceService.save(instance_img4);
			puzzleGroupObjectInstanceService.save(instance_img5);
			puzzleGroupObjectInstanceService.save(instance_img6);
			puzzleGroupObjectInstanceService.save(instance_img7);
			puzzleGroupObjectInstanceService.save(instance_img8);


			byte[] image_0_hash = getImage("src/main/resources/images/hashImage_Puzzle/","0.png");
			byte[] image_1_hash = getImage("src/main/resources/images/hashImage_Puzzle/","1.png");
			byte[] image_2_hash = getImage("src/main/resources/images/hashImage_Puzzle/","2.png");
			byte[] image_3_hash = getImage("src/main/resources/images/hashImage_Puzzle/","3.png");
			byte[] image_4_hash = getImage("src/main/resources/images/hashImage_Puzzle/","4.png");
			byte[] image_5_hash = getImage("src/main/resources/images/hashImage_Puzzle/","5.png");
			byte[] image_6_hash = getImage("src/main/resources/images/hashImage_Puzzle/","6.png");
			byte[] image_7_hash = getImage("src/main/resources/images/hashImage_Puzzle/","7.png");
			byte[] image_8_hash = getImage("src/main/resources/images/hashImage_Puzzle/","8.png");


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

			ALCityAttribute alCityAttribute_bgImage =new ALCityAttribute("bgImage",ImageObject01.getId(),puzzleObjectProperty,alcity_Binary,1L,now,now,admin_1,admin_1);
			alCityAttributeService.save(alCityAttribute_bgImage);

			ALCityAttributeValue alCityAttributeValue_binary_0= new ALCityAttributeValue(null,null,null,null,null,image_0_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_1= new ALCityAttributeValue(null,null,null,null,null,image_1_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_2= new ALCityAttributeValue(null,null,null,null,null,image_2_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_3= new ALCityAttributeValue(null,null,null,null,null,image_3_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_4= new ALCityAttributeValue(null,null,null,null,null,image_4_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_5= new ALCityAttributeValue(null,null,null,null,null,image_5_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_6= new ALCityAttributeValue(null,null,null,null,null,image_6_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_7= new ALCityAttributeValue(null,null,null,null,null,image_7_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			ALCityAttributeValue alCityAttributeValue_binary_8= new ALCityAttributeValue(null,null,null,null,null,image_8_Instance_content,alCityAttribute_bgImage,alCityAttribute_bgImage,1L,now,now,admin_1,admin_1);
			alCityAttributeValueService.save(alCityAttributeValue_binary_0);
			alCityAttributeValueService.save(alCityAttributeValue_binary_1);
			alCityAttributeValueService.save(alCityAttributeValue_binary_2);
			alCityAttributeValueService.save(alCityAttributeValue_binary_3);
			alCityAttributeValueService.save(alCityAttributeValue_binary_4);
			alCityAttributeValueService.save(alCityAttributeValue_binary_5);
			alCityAttributeValueService.save(alCityAttributeValue_binary_6);
			alCityAttributeValueService.save(alCityAttributeValue_binary_7);
			alCityAttributeValueService.save(alCityAttributeValue_binary_8);

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

			PuzzleObjectActionOwnerType  puzzleObjectIsOwner = new PuzzleObjectActionOwnerType("Puzzle Object","Puzzle Object",1L,now,now,admin_1,admin_1);
			PuzzleObjectActionOwnerType  puzzleGroupObjectIsOwner = new PuzzleObjectActionOwnerType("Puzzle Group Object","Puzzl Group Object",1L,now,now,admin_1,admin_1);
			PuzzleObjectActionOwnerType  puzzleGroupObjectInstanceIsOwner = new PuzzleObjectActionOwnerType("Puzzle Group Object Instance","Puzzle Group Object Instance",1L,now,now,admin_1,admin_1);

			puzzleObjectActionOwnerTypeService.save(puzzleObjectIsOwner);
			puzzleObjectActionOwnerTypeService.save(puzzleGroupObjectIsOwner);
			puzzleObjectActionOwnerTypeService.save(puzzleGroupObjectInstanceIsOwner);

			ActionRenderer moveActionRenderer = new ActionRenderer("Move",mobile,moveAction,1L,now,now,admin_1,admin_1);
			actionRendererService.save(moveActionRenderer);

			PuzzleObject_ObjectAction  ImageObject01_MoveAction = new PuzzleObject_ObjectAction(puzzleObjectIsOwner,ImageObject01.getId(),moveAction,moveActionRenderer,1L,now,now,admin_1,admin_1);
			puzzleObject_ObjectActionService.save(ImageObject01_MoveAction);

			ALCityAttribute alCityAttribute_move_action =new ALCityAttribute("actionId",ImageObject01.getId(),puzzleObjectProperty,alcity_Long,1L,now,now,admin_1,admin_1);
			alCityAttributeService.save(alCityAttribute_move_action);
			ALCityAttributeValue alCity_moveAction_parameter_action_id= new ALCityAttributeValue(null,null,moveAction.getId(),null,null,null,alCityAttribute_move_action,alCityAttribute_move_action,1L,now,now,admin_1,admin_1);

			alCityAttributeValueService.save(alCity_moveAction_parameter_action_id);


			PuzzleLevelRuleEventType systemEvent = new PuzzleLevelRuleEventType("System Event","System Event",1L,now,now,admin_1,admin_1);
			PuzzleLevelRuleEventType objectActionEvent = new PuzzleLevelRuleEventType("Object Action Event","Object Action Event",1L,now,now,admin_1,admin_1);
			PuzzleLevelRuleEventType userEvent = new PuzzleLevelRuleEventType("User Event","User Event",1L,now,now,admin_1,admin_1);
			PuzzleLevelRuleEventType rulePostActionEvent = new PuzzleLevelRuleEventType("Rule Post Action Event","Rule Post Action Event",1L,now,now,admin_1,admin_1);
			puzzleLevelRuleEventTypeService.save(systemEvent);
			puzzleLevelRuleEventTypeService.save(objectActionEvent);
			puzzleLevelRuleEventTypeService.save(userEvent);
			puzzleLevelRuleEventTypeService.save(rulePostActionEvent);


			UserEvent clickEvent = new UserEvent("Click","Click",1L,now,now,admin_1,admin_1);
			UserEvent doubleClickEvent = new UserEvent("Double Click","Double Click",1L,now,now,admin_1,admin_1);
			UserEvent startDragEvent = new UserEvent("Start Drag","Start Drag",1L,now,now,admin_1,admin_1);
			UserEvent endDragEvent = new UserEvent("End Drag","End Drag",1L,now,now,admin_1,admin_1);

			userEventService.save(clickEvent);
			userEventService.save(doubleClickEvent);
			userEventService.save(startDragEvent);
			userEventService.save(endDragEvent);

			SystemEvent timerSystemEvent = new SystemEvent("Timer","Timer",1L,now,now,admin_1,admin_1);
			systemEventService.save(timerSystemEvent);

			System.out.println("All Things is OK!!!!");
		};


	}
}
