package com.alcity.repository.puzzle;

import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelGround;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelGroundRepository extends CrudRepository<PuzzleLevelGround,Long> {
    Optional<PuzzleLevelGround> findById(Long id);
    Optional<PuzzleLevelGround> findByPuzzleLevelId(Long pid);
    Collection<PuzzleLevelGround> findAll();
    Collection<PuzzleLevelGround>  findByNumColumns(Integer cols);
    Collection<PuzzleLevelGround>  findByNumRows(Integer rows);


}
