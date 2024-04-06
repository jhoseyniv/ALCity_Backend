package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;

import java.util.Collection;
import java.util.Set;

public class PuzzleObjectDTO extends BaseTableDTO {
    private String title;
    private String objectCategory;
    private BinaryContentDTO picture;
    private BinaryContentDTO icon;
    private Collection<PuzzleGroup_PuzzleObjectDTO> puzzleGroup_puzzleObjectDTOCollection;


}
