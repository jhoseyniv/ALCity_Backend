package com.alcity;

import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.entity.users.WalletTransaction;
import com.alcity.repository.base.DataTypeRepository;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.base.*;
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
			dataTypeRepository.save(alcity_Int);
			dataTypeRepository.save(alcity_Long);
			dataTypeRepository.save(alcity_Boolean);
			dataTypeRepository.save(alcity_String);

			PuzzleDifficulty easy= new PuzzleDifficulty("Easy","Easy",1L,now,now,admin_1,admin_1);
			PuzzleDifficulty medium= new PuzzleDifficulty("Medium","Medium",1L,now,now,admin_1,admin_1);
			PuzzleDifficulty hard= new PuzzleDifficulty("Hard","Hard",1L,now,now,admin_1,admin_1);

			puzzleDifficultyService.save(easy);
			puzzleDifficultyService.save(medium);
			puzzleDifficultyService.save(hard);

			PuzzleLevelStatus compeleted = new PuzzleLevelStatus("Completed","Completed",1L,now,now,admin_1,admin_1);
			PuzzleLevelStatus canceled = new PuzzleLevelStatus("canceled","canceled",1L,now,now,admin_1,admin_1);
			PuzzleLevelStatus ongoing = new PuzzleLevelStatus("ongoing","ongoing",1L,now,now,admin_1,admin_1);
			puzzleLevelStatusService.save(compeleted);
			puzzleLevelStatusService.save(canceled);
			puzzleLevelStatusService.save(ongoing);

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

			WalletItemType fiat = new WalletItemType("fiat","fiat",1L,now,now,admin_1,admin_1);
			WalletItemType crypto = new WalletItemType("crypto","crypto",1L,now,now,admin_1,admin_1);
			WalletItemType alCoin = new WalletItemType("al_coin","al_coin",1L,now,now,admin_1,admin_1);
			WalletItemType cityObject = new WalletItemType("cityObject","cityObject",1L,now,now,admin_1,admin_1);

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

			BinaryContent image_journey_1 = new BinaryContent("image_journey_1",jouerny_1_Image,imageType,1L,now,now,admin_1,admin_1);
			binaryContentService.save(image_journey_1);


			Journey journey_1 = new Journey("Journey_1",image_journey_1,1L,now,now,admin_1,admin_1);


			System.out.println("All Things is OK!!!!");
		};


	}
}
