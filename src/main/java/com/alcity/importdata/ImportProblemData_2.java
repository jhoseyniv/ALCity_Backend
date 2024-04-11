package com.alcity.importdata;


import com.alcity.ObjectManagmentApplication;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BinaryContentType;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.service.base.BinaryContentService;
import com.alcity.service.base.BinaryContentTypeService;
import com.alcity.service.users.ApplicationMemberService;
import com.alcity.utility.Util;
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

    private static final Logger log = LoggerFactory.getLogger(ObjectManagmentApplication.class);

    @Override
    public void run(String... args) throws Exception {
        log.info("Start Application...Import Problem 2");
        System.out.println("...Import Problem 2");

        ZoneId zoneId = ZoneId.of("Europe/London").getRules().getOffset(Instant.now());
        ZonedDateTime startDate = ZonedDateTime.now();
        ZonedDateTime endDate = ZonedDateTime.of(2022, 3, 30, 23, 45, 59, 1234, zoneId);
        ApplicationMember admin_1 = applicationMemberService.findByUsername("admin");
        BinaryContentType imageType= binaryContentTypeService.findByValue("image");

        ZonedDateTime  createdDate= ZonedDateTime.now();
        Long now = createdDate.toEpochSecond();


    }

}
