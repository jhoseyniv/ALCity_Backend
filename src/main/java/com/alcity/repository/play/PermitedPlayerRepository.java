package com.alcity.repository.play;

import com.alcity.entity.play.PermittedPlayer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PermitedPlayerRepository extends CrudRepository<PermittedPlayer,Long> {
    Optional<PermittedPlayer> findById(Long id);
    Collection<PermittedPlayer> findAll();

}
