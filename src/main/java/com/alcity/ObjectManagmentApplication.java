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
	private PGService puzzleGroupService;



	@Autowired
	JourneyLearningSkillService journeyLearningSkillService;

	@Autowired
	LearningSkill_LearningTopicService learningSkill_LearningTopicService;

	@Autowired
	PuzzleSkillLearningContentService puzzleSkillLearningContentService;

	@Autowired
	PLObjectiveService puzzleLevelObjectiveService;

	@Autowired
	LearningContentService learningContentService;

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
	PuzzleObjectService puzzleObjectService;

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
	PuzzleObject_ObjectActionService puzzleObject_ObjectActionService;

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

			ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");

			byte[] puzzle_Ground_Maze_Image_1 = ImageUtil.getImage("src/main/resources/images/","playGround.png");
			BinaryContent puzzle_ground_Maze_image_binary_content_1 = new BinaryContent("puzzle ground for Maze image",puzzle_Ground_Maze_Image_1,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_ground_Maze_image_binary_content_1);

			byte[] puzzle_group_Icon_2 = ImageUtil.getImage("src/main/resources/images/","physic.png");
			BinaryContent puzzle_group_binary_content_2 = new BinaryContent("image_puzzle_group_physic",puzzle_group_Icon_2,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_binary_content_2);

			byte[] puzzle_group_Icon_3 = ImageUtil.getImage("src/main/resources/images/","IQ.png");
			BinaryContent puzzle_group_binary_content_3 = new BinaryContent("image_puzzle_group_IQ",puzzle_group_Icon_3,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_binary_content_3);

			byte[] puzzle_group_Maze_Image = ImageUtil.getImage("src/main/resources/images/","MazeImage.png");
			BinaryContent puzzle_group_Maze_Image_binary_content = new BinaryContent("MazeImage",puzzle_group_Maze_Image,BinaryContentType.Image,1L,now,now,admin_1,admin_1);
			binaryContentService.save(puzzle_group_Maze_Image_binary_content);

			LearningContent learningContent_Maze=new LearningContent("help to maze","this content is about maze tables",puzzle_group_Maze_Image_binary_content,1L,now,now,admin_1,admin_1);
			learningContentService.save(learningContent_Maze);

			PuzzleCategory mathematic = puzzleCategoryService.findByValue("mathematic");

			PuzzleGroup puzzleGroup_2 = new PuzzleGroup("Physics Puzzle Group",mathematic,puzzle_group_binary_content_2,puzzle_group_binary_content_3,1L,now,now,admin_1,admin_1);
			PuzzleGroup puzzleGroup_3 = new PuzzleGroup("IQ Puzzle Group",mathematic,puzzle_group_binary_content_3,puzzle_group_binary_content_3,1L,now,now,admin_1,admin_1);
			PuzzleGroup puzzleGroup_4 = new PuzzleGroup("Maze Table Group",mathematic,puzzle_group_Maze_Image_binary_content,puzzle_group_Maze_Image_binary_content,1L,now,now,admin_1,admin_1);
			puzzleGroupService.save(puzzleGroup_2);
			puzzleGroupService.save(puzzleGroup_3);
			puzzleGroupService.save(puzzleGroup_4);

			System.out.println("All Things is OK!!!!");
		};


	}
}
