package com.alcity;

import com.alcity.entity.base.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.ApplicationMember_WalletItem;
import com.alcity.entity.users.WalletItem;
import com.alcity.repository.base.DataTypeRepository;
import com.alcity.service.base.*;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.ApplicationMember_WalletItemService;
import com.alcity.service.users.WalletItemService;
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
	private ALCitySystemUserService alCitySystemUserService;

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
	private WalletItemTypeService walletItemTypeService;

	@Autowired
	private WalletItemService walletItemService;

	@Autowired
	private ApplicationMember_WalletItemService applicationMember_walletItemService;

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


			ALCitySystemUser currentUser = new ALCitySystemUser("admin","admin","admin","09123509000","j_hoseyni@yahoo.com");
			alCitySystemUserService.save(currentUser);

			UserGender female = new UserGender("F","Female",1L,now,now,currentUser,currentUser);
			UserGender male = new UserGender("M","Male",1L,now,now,currentUser,currentUser);
			userGenderService.save(female);
			userGenderService.save(male);

			MemberType puzzlePlayer = new MemberType("Puzzle Player","Puzzle_Player",1L,now,now,currentUser,currentUser);
			memberTypeService.save(puzzlePlayer);

			MemberType guest = new MemberType("Guest","Guest",1L,now,now,currentUser,currentUser);
			memberTypeService.save(guest);

			MemberType puzzleCreator = new MemberType("Puzzle Creator","Puzzle_Creator",1L,now,now,currentUser,currentUser);
			memberTypeService.save(puzzleCreator);

			MemberType administrator = new MemberType("Administrator","Administrator",1L,now,now,currentUser,currentUser);
			memberTypeService.save(administrator);


			ClientType  mobile = new ClientType("mobile","mobile",1L,now,now,currentUser,currentUser);
			ClientType  web = new ClientType("web","web",1L,now,now,currentUser,currentUser);
			ClientType  tablet = new ClientType("tablet","tablet",1L,now,now,currentUser,currentUser);
			clientTypeService.save(mobile);
			clientTypeService.save(web);
			clientTypeService.save(tablet);


			PuzzleCategory  mathematic = new PuzzleCategory("mathematic","mathematic",1L,now,now,currentUser,currentUser);
			PuzzleCategory  physic = new PuzzleCategory("physic","physic",1L,now,now,currentUser,currentUser);
			PuzzleCategory  IQ = new PuzzleCategory("IQ","IQ",1L,now,now,currentUser,currentUser);
			puzzleCategoryService.save(mathematic);
			puzzleCategoryService.save(physic);
			puzzleCategoryService.save(IQ);

			DataType alcity_Int = new DataType("Integer","Integer",1L,now,now,currentUser,currentUser);
			DataType alcity_Long =new DataType("Long","Long",1L,now,now,currentUser,currentUser);

			DataType alcity_Boolean = new DataType("Boolean","Boolean",1L,now,now,currentUser,currentUser);

			DataType alcity_String = new DataType("String","String",1L,now,now,currentUser,currentUser);
			dataTypeRepository.save(alcity_Int);
			dataTypeRepository.save(alcity_Long);
			dataTypeRepository.save(alcity_Boolean);
			dataTypeRepository.save(alcity_String);

			PuzzleDifficulty easy= new PuzzleDifficulty("Easy","Easy",1L,now,now,currentUser,currentUser);
			PuzzleDifficulty medium= new PuzzleDifficulty("Medium","Medium",1L,now,now,currentUser,currentUser);
			PuzzleDifficulty hard= new PuzzleDifficulty("Hard","Hard",1L,now,now,currentUser,currentUser);

			puzzleDifficultyService.save(easy);
			puzzleDifficultyService.save(medium);
			puzzleDifficultyService.save(hard);

			WalletItemType fiat = new WalletItemType("fiat","fiat",1L,now,now,currentUser,currentUser);
			WalletItemType crypto = new WalletItemType("crypto","crypto",1L,now,now,currentUser,currentUser);
			WalletItemType alCoin = new WalletItemType("al_coin","al_coin",1L,now,now,currentUser,currentUser);
			WalletItemType cityObject = new WalletItemType("cityObject","cityObject",1L,now,now,currentUser,currentUser);

			walletItemTypeService.save(fiat);
			walletItemTypeService.save(crypto);
			walletItemTypeService.save(alCoin);
			walletItemTypeService.save(cityObject);

			byte[] tetherIcon = getImage("src/main/resources/images/","Tether.png");
			byte[] carIcon = getImage("src/main/resources/images/","car.png");

			WalletItem teterWalletItem= new WalletItem("teter","teter",1L,now,now,currentUser,currentUser,fiat,tetherIcon);
			WalletItem alCoin10WalletItem= new WalletItem("al_coin_10","al_coin_10",1L,now,now,currentUser,currentUser,alCoin,tetherIcon);
			WalletItem carWalletItem= new WalletItem("car object","car_object",1L,now,now,currentUser,currentUser,cityObject,carIcon);

			walletItemService.save(teterWalletItem);
			walletItemService.save(alCoin10WalletItem);
			walletItemService.save(carWalletItem);

			ApplicationMember member1= new ApplicationMember(35,"admin","admin","admin","09123580100","jhoseyni_yahoo.com",avatar,male,guest,1L,now,now,currentUser,currentUser);
			Set clientTypeSet = new HashSet<ClientType>();
			clientTypeSet.add(mobile);
			member1.setClientTypeSet(clientTypeSet);
			applicationMemberService.save(member1);

			ApplicationMember_WalletItem member1_alcoin_10= new ApplicationMember_WalletItem(member1,alCoin10WalletItem,10f);
			ApplicationMember_WalletItem member1_carObject_10= new ApplicationMember_WalletItem(member1,carWalletItem,3f);
			applicationMember_walletItemService.save(member1_alcoin_10);
			applicationMember_walletItemService.save(member1_carObject_10);



//			Commodity hat = new Commodity("hat",10d,"krona",null,null,null);
//			Commodity shirt = new Commodity("shirt",20d,"krona",null,null,null);
//			Commodity pants = new Commodity("pants",30d,"krona",null,null,null);
//			Commodity shoe = new Commodity("shoe",50d,"krona",null,null,null);
//			Commodity mobile = new Commodity("mobile",500d,"krona",null,null,null);
//			Commodity tablet = new Commodity("tablet",600d,"krona",null,null,null);
//			Commodity tv = new Commodity("tv",600d,"krona",null,null,null);
//
//			commodityService.save(hat);
//			commodityService.save(shirt);
//			commodityService.save(pants);
//			commodityService.save(shoe);
//			commodityService.save(mobile);
//			commodityService.save(tablet);
//			commodityService.save(tv);
//
//
//			Cart shopingCartAli= new Cart(ali,"cart100","ALi Ordered 2 hats",createdDate,null, cartStatusDraft,null);
//			cartService.save(shopingCartAli);
//
//			CartCommodity hatItem= new CartCommodity(hat,shopingCartAli,1L);
//			cartCommodityService.save(hatItem);
//
//			CartCommodity shoeItem= new CartCommodity(shoe,shopingCartAli,2L);
//			cartCommodityService.save(shoeItem);
//
//
//			Cart orderReza= new Cart(reza,"cart200","Reza Ordered a Shirt and 2 pants",createdDate,null, cartStatusDraft,null);
//			cartService.save(orderReza);
//
//			CartCommodity shirtItem= new CartCommodity(shirt,orderReza,1L);
//			cartCommodityService.save(shirtItem);
//
//			CartCommodity pantsItem= new CartCommodity(pants,orderReza,2L);
//			cartCommodityService.save(pantsItem);
//
//			CartCommodity shoeItem2= new CartCommodity(shoe,orderReza,6L);
//			cartCommodityService.save(shoeItem2);
//
//			CartCommodity hatItem2= new CartCommodity(hat,orderReza,10L);
//			cartCommodityService.saveAndFlush(hatItem2);
//
//			DiscountStrategy giftAHatStrategy = new DiscountStrategy("Gift a Hat to client if buy 1 shirt and 2 pants",StrategyTypes.GIFT.toString(),1L,null,startDate,endDate);
//			DiscountStrategy giftAHatStrategyIfFiveHat = new DiscountStrategy("Gift 2 Hats to client if buy a 5 hat ",StrategyTypes.GIFT.toString(),2L,null,startDate,endDate);
//			DiscountStrategy discountOnAHatStrategy = new DiscountStrategy("DisCount 20% in a Hat at this time",StrategyTypes.DISCOUNT.toString(),20L,null,startDate,endDate);
//			discountStrategyService.save(giftAHatStrategy);
//			discountStrategyService.save(discountOnAHatStrategy);
//			discountStrategyService.save(giftAHatStrategyIfFiveHat);
//
//			CommodityDiscountStrategy hatDiscountByHatStrategy= new CommodityDiscountStrategy(hat,discountOnAHatStrategy,1L);
//			CommodityDiscountStrategy hatGfitByHatStrategy= new CommodityDiscountStrategy(hat,giftAHatStrategyIfFiveHat,5L);
//
//			commodityDiscountStrategyService.save(hatDiscountByHatStrategy);
//			commodityDiscountStrategyService.save(hatGfitByHatStrategy);
//
//			CommodityDiscountStrategy shirtDiscountByGiftHatStrategy= new CommodityDiscountStrategy(shirt,giftAHatStrategy,1L);
//			CommodityDiscountStrategy pantsDiscountByGiftHatStrategy= new CommodityDiscountStrategy(pants,giftAHatStrategy,2L);
//			commodityDiscountStrategyService.save(shirtDiscountByGiftHatStrategy);
//			commodityDiscountStrategyService.save(pantsDiscountByGiftHatStrategy);

			System.out.println("All Things is OK!!!!");
		};


	}
}
