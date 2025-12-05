package com.alcity.repository.challenge;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.challenge.Challenge;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ChallengeRepository extends CrudRepository<Challenge,Long> {


    Collection<Challenge> findAll();
}
