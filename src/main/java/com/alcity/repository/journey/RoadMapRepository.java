package com.alcity.repository.journey;

import com.alcity.dto.journey.RoadMapDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.journey.RoadMap;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface RoadMapRepository extends CrudRepository<RoadMap,Long> {
    Optional<RoadMap> findById(Long id);
    Collection<RoadMap> findByJourney(Journey journey);
    Journey save(RoadMapDTO roadMapDTO);

}
