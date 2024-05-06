package com.alcity.repository.puzzle;

import com.alcity.dto.journey.JourneyDTO;
import com.alcity.dto.puzzle.PLObjectiveDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.puzzle.PLObjective;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLObjectiveRepository extends CrudRepository<PLObjective,Long> {
    Optional<PLObjective> findById(Long id);
    Collection<PLObjective> findAll();
    Collection<PLObjective> findPuzzleLevelObjectiveByPuzzleLevelId(Long plId);
    Collection<PLObjective> findByTitle(String title);
    Collection<PLObjective> findByCondition(StringBuffer condition);

}
