package com.alcity.repository.challenge;

import com.alcity.entity.challenge.ChallengeLeaderBoard;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ChallengeLeaderBoardRepository extends CrudRepository<ChallengeLeaderBoard, Long> {

}
