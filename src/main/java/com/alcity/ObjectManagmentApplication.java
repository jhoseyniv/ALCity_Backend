package com.alcity;

import com.alcity.repository.play.PermitedPlayerRepository;
import com.alcity.service.Journey.JourneyLearningSkillService;
import com.alcity.service.alobject.*;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.base.PuzzleCategoryService;
import com.alcity.service.learning.LearningSkill_LearningTopicService;
import com.alcity.service.play.PlayHistoryService;
import com.alcity.service.puzzle.*;
import com.alcity.service.appmember.AppMemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

			System.out.println("All Things is OK!!!!");
		};


	}
}
