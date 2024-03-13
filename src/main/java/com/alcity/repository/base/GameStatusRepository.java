package com.alcity.repository.base;

import com.alcity.entity.base.ClientType;
import com.alcity.entity.base.GameStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface GameStatusRepository extends CrudRepository<GameStatus,Long> {

    Optional<GameStatus> findById(Long id);
    Collection<GameStatus> findAll();
    GameStatus findByLabel(String label);
    GameStatus findByValue(String value);

}
