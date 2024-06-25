package com.alcity.repository.puzzle;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRepository  extends CrudRepository<PuzzleLevel,Long> {
    Optional<PuzzleLevel> findById(Long id);
    Collection<PuzzleLevel> findAll();
    Collection<PuzzleLevel> findByTitle(String title);
    PuzzleLevel findByCode(String code);
    Optional<PuzzleLevel>  findByPicture(BinaryContent pic);

    Optional<PuzzleLevel>  findByIcon(BinaryContent icon);

}
