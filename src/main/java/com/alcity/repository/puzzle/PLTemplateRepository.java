package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLRule;
import com.alcity.entity.puzzle.PLTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLTemplateRepository extends CrudRepository<PLTemplate,Long> {
    Optional<PLTemplate> findById(Long id);
    Collection<PLTemplate> findAll();
    Collection<PLTemplate> findByTitle(String title);

}
