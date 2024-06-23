package com.alcity.repository.puzzle;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PGRepository extends CrudRepository<PuzzleGroup,Long> {
    Optional<PuzzleGroup> findById(Long id);
    Collection<PuzzleGroup> findAll();
    Optional<PuzzleGroup> findByTitle(String title);
    Optional<PuzzleGroup> findByIcon(BinaryContent icon);
    Optional<PuzzleGroup> findByPic(BinaryContent pic);

}
