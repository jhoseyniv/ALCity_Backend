package com.alcity.repository.journey;

import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.JourneyStep;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Collection;
import java.util.Optional;

public interface JourneyStepRepository extends CrudRepository<JourneyStep,Long> {
    Optional<JourneyStep> findById(Long id);
    Collection<JourneyStep> findAll();
    Collection<JourneyStep> findByXpos(Integer xpos);
    Collection<JourneyStep> findByYpos(Integer ypos);
    Journey findByTitle(String title);

}
