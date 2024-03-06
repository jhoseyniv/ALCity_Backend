package com.alcity;

import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.MemberType;
import com.alcity.entity.users.UserGender;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.service.users.MemberTypeService;
import com.alcity.service.users.UserGenderService;
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

	public byte[] getAvatarImage(String imageDirectory, String imageName) throws IOException {
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
			byte[] avatar = getAvatarImage("src/main/resources/images/","avatar.png");

			ZonedDateTime  createdDate= ZonedDateTime.now();
			Long now = createdDate.toInstant().toEpochMilli();
			UserGender female = new UserGender(1L,now,1L,now,now,"F","Female");
			UserGender male = new UserGender(1L,now,1L,now,now,"M","Male");
			userGenderService.save(female);
			userGenderService.save(male);

			MemberType puzzlePlayer = new MemberType(1L,now,1L,now,1L,"Puzzle Player","Puzzle_Player");
			memberTypeService.save(puzzlePlayer);

			MemberType guest = new MemberType(1L,now,1L,now,1L,"Guest","Guest");
			memberTypeService.save(guest);

			MemberType puzzleCreator = new MemberType(1L,now,1L,now,1L,"Puzzle Creator","Puzzle_Creator");
			memberTypeService.save(puzzleCreator);

			MemberType administrator = new MemberType(1L,now,1L,now,1L,"Administrator","Administrator");
			memberTypeService.save(administrator);

			ApplicationMember admin= new ApplicationMember(1L,now,1L,now,1L,35,"admin","admin","admin","09123580100","jhoseyni_yahoo.com",avatar,male,administrator);

			applicationMemberService.save(admin);


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
