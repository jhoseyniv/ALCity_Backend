package com.alcity.repository.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLGameInstanceRepository extends CrudRepository<PLGameInstance,Long> {
    Optional<PLGameInstance> findById(Long id);
    Collection<PLGameInstance> findAll();

    Collection<PLGameInstance> findByPlayerAndPuzzleLevel(AppMember player, PuzzleLevel puzzleLevel);
    Collection<PLGameInstance> findByPlayer(AppMember player);

}
