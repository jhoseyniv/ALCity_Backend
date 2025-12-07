package com.alcity.repository.challenge;

import com.alcity.entity.challenge.Challenge;
import com.alcity.entity.challenge.ChallengeInitiator;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ChallengeInitiatorRepository extends CrudRepository<ChallengeInitiator,Long> {
    Collection<ChallengeInitiator> findAll();
}
