package com.alcity.dto.puzzle;

import com.alcity.dto.journey.JourneyStepDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor


public class PGDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;
    private Long puzzleCategoryId;
    private Long iconId;
    private Long picId;

    private Collection<JourneyStepDTO> journeyStepDTOCollection;
    private Collection<PLDTO> puzzleLevelDTOCollection;

    private Collection<PGObjectDTO> puzzleGroup_puzzleObjectDTOCollection;

    private Collection<PGLearningSkillDTO> puzzleSkillLearningContentDTOCollection;

    public PGDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String title, Long puzzleCategoryId, Long iconId, Long picId) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.iconId = iconId;
        this.picId = picId;
        this.puzzleCategoryId= puzzleCategoryId;
    }
}
