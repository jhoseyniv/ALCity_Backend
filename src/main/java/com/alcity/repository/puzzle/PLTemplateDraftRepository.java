package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLTemplateDraft;
import org.springframework.data.repository.CrudRepository;
import java.util.Collection;
import java.util.Optional;

public interface PLTemplateDraftRepository extends CrudRepository<PLTemplateDraft,Long> {
    Optional<PLTemplateDraft> findById(Long id);
    Collection<PLTemplateDraft> findAll();
    Collection<PLTemplateDraft> findByTitle(String title);

}
