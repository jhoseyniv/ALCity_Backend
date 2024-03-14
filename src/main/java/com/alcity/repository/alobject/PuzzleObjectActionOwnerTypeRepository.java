package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PuzzleObjectActionOwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleObjectActionOwnerTypeRepository extends CrudRepository<PuzzleObjectActionOwnerType,Long> {

    Optional<PuzzleObjectActionOwnerType> findById(Long id);
    Collection<PuzzleObjectActionOwnerType> findAll();
    PuzzleObjectActionOwnerType findByLabel(String label);
    PuzzleObjectActionOwnerType findByValue(String value);

}
