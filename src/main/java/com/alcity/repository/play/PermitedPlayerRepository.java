package com.alcity.repository.play;

import com.alcity.entity.play.PermitedPlayer;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PermitedPlayerRepository extends CrudRepository<PermitedPlayer,Long> {
    Optional<PermitedPlayer> findById(Long id);
    Collection<PermitedPlayer> findAll();

}
