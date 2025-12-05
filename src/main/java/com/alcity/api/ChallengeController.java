package com.alcity.api;

import com.alcity.dto.appmember.AppMemberDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.challenge.Challenge;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.challenge.ChallengeService;
import com.alcity.utility.DTOUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Tag(name = "Challenge Entity  API's ", description = "Get Challenge API for ...")
@CrossOrigin(origins = "*" ,maxAge = 3600)
@RestController
@RequestMapping("/challange")

public class ChallengeController {

    @Autowired
    private ChallengeService challengeService;


    @GetMapping("/all")
    @CrossOrigin(origins = "*")
    //@Cacheable("all-AppMemberDTO")
    public Collection<Challenge> getAllChallenges() {
        Collection<Challenge> challenges = challengeService.findAll();
        return challenges;
    }


}
