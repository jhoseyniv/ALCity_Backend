package com.alcity.repository.journey;

import com.alcity.dto.journey.JourneyDTO;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.WalletItem;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JourneyRepository extends CrudRepository<Journey,Long> {
    Optional<Journey> findById(Long id);
    Collection<Journey> findAll();
    Collection<Journey> findByTitleContains(String criteria);
    Journey findByTitle(String title);
    Journey save(JourneyDTO journeyDTO);
}
