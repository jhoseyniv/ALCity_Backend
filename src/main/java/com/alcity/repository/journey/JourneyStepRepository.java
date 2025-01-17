package com.alcity.repository.journey;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JourneyStepRepository extends CrudRepository<JourneyStep,Long> {
    Optional<JourneyStep> findById(Long id);
    Collection<JourneyStep> findAll();
    Collection<JourneyStep> findByXpos(Integer xpos);
    Collection<JourneyStep> findByYpos(Integer ypos);
    Collection<JourneyStep> findByJourney(Journey journey);
    Optional<JourneyStep> findByTitle(String title);

}
