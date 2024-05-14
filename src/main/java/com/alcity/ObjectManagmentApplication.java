package com.alcity;

import com.alcity.entity.alenum.BinaryContentType;
import com.alcity.entity.base.*;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.puzzle.*;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyLearningSkillService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkill_LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.utility.ImageUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Order(value=2)
@SpringBootApplication
public class ObjectManagmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObjectManagmentApplication.class, args);
	}

	@Autowired
	private ApplicationMemberService applicationMemberService;



	@Autowired
	private  BinaryContentService  binaryContentService;





	@Autowired
	JourneyLearningSkillService journeyLearningSkillService;

	@Autowired
	LearningSkill_LearningTopicService learningSkill_LearningTopicService;


	@Autowired
	PLObjectiveService puzzleLevelObjectiveService;


	@Autowired
	PuzzleLevelService puzzleLevelService;

	@Autowired
    PLLearningTopicService puzzleLevelLearningTopicService;

	@Autowired
	PermitedPlayerRepository permitedPlayerRepository;

	@Autowired
	PlayHistoryService playHistoryService;

	@Autowired
	private PuzzleCategoryService puzzleCategoryService;

	@Autowired
    PLGroundService puzzleLevelGroundService;


	@Autowired
	PLGameInstanceService puzzleLevelGameInstanceService;


	@Autowired
    ALCityObjectService puzzleObjectService;

	@Autowired
    ALCityObjectInPGService puzzleGroup_PuzzleObjectService;


	@Autowired
    ALCityInstanceInPLService alCityInstanceService;

	@Autowired
    AttributeService alCityAttributeService;

	@Autowired
	AttributeValueService alCityAttributeValueService;


	@Autowired
	ObjectCategoryService objectCategoryService;

	@Autowired
	ActionRendererService actionRendererService;
	@Autowired
	PuzzleObjectActionService puzzleObject_ObjectActionService;

	@Autowired
    PLRuleService puzzleLevelRuleService;

	@Autowired
	PLRuleEventService puzzleLevelRuleEventService;


	@Autowired
	PLRulePostActionService puzzleLevelRulePostActionService;


	@Autowired
	private Environment environment;

	private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			;
			log.info("Start Application...Tassk Management");
			System.out.println("Let's inspect the beans provided by Spring Boot:");

			LocalDateTime current = LocalDateTime.now();
			DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String now = current.format(format);

			//ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");


			System.out.println("All Things is OK!!!!");
		};


	}
}
