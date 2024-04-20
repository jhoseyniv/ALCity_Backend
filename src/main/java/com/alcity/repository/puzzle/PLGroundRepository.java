package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLGround;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLGroundRepository extends CrudRepository<PLGround,Long> {
    Optional<PLGround> findById(Long id);
    Optional<PLGround> findByPuzzleLevelId(Long pid);
    Collection<PLGround> findAll();
    Collection<PLGround>  findByNumColumns(Integer cols);
    Collection<PLGround>  findByNumRows(Integer rows);


}
