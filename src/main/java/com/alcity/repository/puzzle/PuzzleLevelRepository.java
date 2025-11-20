package com.alcity.repository.puzzle;

import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRepository  extends CrudRepository<PuzzleLevel,Long> {
    Optional<PuzzleLevel> findById(Long id);
    Collection<PuzzleLevel> findAll();
    Collection<PuzzleLevel> findByTitle(String title);
    Optional<PuzzleLevel> findByCode(String code);
    Optional<PuzzleLevel>  findByPicture(BinaryContent pic);

    Optional<PuzzleLevel>  findByIcon(BinaryContent icon);
    Collection<PuzzleLevel> findByPuzzleLevelPrivacy(PLPrivacy privacy);

    @Query("SELECT pl FROM PuzzleLevel pl WHERE (:age between pl.fromAge and pl.toAge) and pl.puzzleLevelPrivacy=:privacy")
    Collection<PuzzleLevel> findByPuzzleLevelPrivacyByAge(@Param("privacy") PLPrivacy privacy, @Param("age") Integer age);

}
