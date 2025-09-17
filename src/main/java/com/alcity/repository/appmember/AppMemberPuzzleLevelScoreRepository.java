package com.alcity.repository.appmember;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMemberPuzzleLevelScore;
import com.alcity.entity.appmember.PLObjectiveTransaction;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface AppMemberPuzzleLevelScoreRepository extends CrudRepository<AppMemberPuzzleLevelScore,Long> {

    @Override
    Optional<AppMemberPuzzleLevelScore> findById(Long aLong);

    Collection<AppMemberPuzzleLevelScore> findByPlayer(AppMember player);

    Optional<AppMemberPuzzleLevelScore> findByPuzzleLevelAndPlayer(PuzzleLevel puzzleLevel, AppMember player);
}
