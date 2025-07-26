package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLCell;
import com.alcity.entity.puzzle.PLGround;
import com.alcity.entity.puzzle.PLTemplate;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PLCellRepository extends CrudRepository<PLCell,Long> {
}
