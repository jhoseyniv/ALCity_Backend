package com.alcity.importdata;


import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.base.*;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.entity.users.WalletItem;
import com.alcity.service.Journey.JourneyService;
import com.alcity.service.base.*;
import com.alcity.service.learning.LearningContentService;
import com.alcity.service.learning.LearningSkillService;
import com.alcity.service.learning.LearningTopicService;
import com.alcity.service.puzzle.PuzzleGroupService;
import com.alcity.service.puzzle.PuzzleLevelService;
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
import java.time.ZoneId;
import java.time.ZonedDateTime;


@Order(value=3)
@Component
public class ImportProblemData_2 implements CommandLineRunner {

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

    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 2");
        System.out.println("...Import Problem 2");

        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);
        ZonedDateTime  createdDate= ZonedDateTime.now();
        Long now = createdDate.toEpochSecond();


        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        BinaryContentType imageType= binaryContentTypeService.findByValue("image");
        ApplicationMember jalalHoseyni = applicationMemberService.findByUsername("jalal");
        LearningTopic routing_in_the_table = learningTopicService.findByTitle("Routing in the Table");


        DataType alcity_Int = dataTypeService.findByValue("Integer");
        DataType alcity_Binary = dataTypeService.findByValue("Binary");
        DataType alcity_Long = dataTypeService.findByValue("Long");
        DataType alcity_Boolean = dataTypeService.findByValue("Boolean");
        DataType alcity_String =  dataTypeService.findByValue("String");


        LearningSkill timeManagement = learningSkillService.findByValue("timeManagement");
        LearningSkill routing = learningSkillService.findByValue("routing");
        LearningSkill find_paths = learningSkillService.findByValue("Find Paths");

        WalletItem alCoin_100_WalletItem = walletItemService.findByValue("al_coin_100");
        Journey journey_1 = journeyService.findByTitle("Journey_1");

        byte[] playGround_image_problem_2 = ImageUtil.getImage("src/main/resources/images/","playGround_2.png");
        BinaryContent play_ground_binary_content_2 = new BinaryContent("puzzle ground for Maze Problem",playGround_image_problem_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(play_ground_binary_content_2);

        byte[] puzzle_group_Pic_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group_2.png");
        BinaryContent puzzle_group_2_binary_content_pic = new BinaryContent("image_puzzle_group_Mazes",puzzle_group_Pic_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_2_binary_content_pic);


        byte[] puzzle_group_Icon_2 = ImageUtil.getImage("src/main/resources/images/","puzzle_group2_icon.png");
        BinaryContent puzzle_group_2_binary_content_icon = new BinaryContent("image_puzzle_group_Mazes",puzzle_group_Icon_2,imageType,1L,now,now,admin_1,admin_1);
        binaryContentService.save(puzzle_group_2_binary_content_icon);


        PuzzleCategory maze = puzzleCategoryService.findByValue("Maze");


        PuzzleGroup puzzleGroup_2 = new PuzzleGroup("Maze Image - Puzzle Group 2",maze,puzzle_group_2_binary_content_icon,puzzle_group_2_binary_content_pic,1L,now,now,admin_1,admin_1);
        puzzleGroupService.save(puzzleGroup_2);

        JourneyStep step_3_journey_1 = new JourneyStep("step_3_journey_1",1,30,30,journey_1,puzzleGroup_2,1L,now,now,admin_1,admin_1);

        PLPrivacy privacy_1 = puzzleLevelPrivacyService.findByValue("privacy1");

        PuzzleLevel puzzleLevel_Maze = new PuzzleLevel(now,1L,"Find shortest path from start to end in maze","4546",10,14,5f,puzzleGroup_2, PLDifficulty.Medium, PLStatus.Not_Started,privacy_1,puzzle_group_2_binary_content_pic,puzzle_group_2_binary_content_icon,3L,now,now,admin_1,admin_1);
        puzzleLevelService.save(puzzleLevel_Maze);

    }

}
