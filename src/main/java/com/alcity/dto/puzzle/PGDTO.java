package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.dto.journey.JourneyStepDTO;

import java.util.Collection;

public class PGDTO extends BaseTableDTO {

    private String title;
    private BinaryContentDTO icon;
    private BinaryContentDTO pic;

    private Collection<JourneyStepDTO> journeyStepDTOCollection;
    private Collection<PLDTO> puzzleLevelDTOCollection;

    private Collection<PuzzleGroup_PuzzleObjectDTO> puzzleGroup_puzzleObjectDTOCollection;

    private Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BinaryContentDTO getIcon() {
        return icon;
    }

    public void setIcon(BinaryContentDTO icon) {
        this.icon = icon;
    }

    public BinaryContentDTO getPic() {
        return pic;
    }

    public void setPic(BinaryContentDTO pic) {
        this.pic = pic;
    }

    public Collection<JourneyStepDTO> getJourneyStepDTOCollection() {
        return journeyStepDTOCollection;
    }

    public void setJourneyStepDTOCollection(Collection<JourneyStepDTO> journeyStepDTOCollection) {
        this.journeyStepDTOCollection = journeyStepDTOCollection;
    }

    public Collection<PLDTO> getPuzzleLevelDTOCollection() {
        return puzzleLevelDTOCollection;
    }

    public void setPuzzleLevelDTOCollection(Collection<PLDTO> puzzleLevelDTOCollection) {
        this.puzzleLevelDTOCollection = puzzleLevelDTOCollection;
    }

    public Collection<PuzzleSkillLearningContentDTO> getPuzzleSkillLearningContentDTOCollection() {
        return puzzleSkillLearningContentDTOCollection;
    }

    public void setPuzzleSkillLearningContentDTOCollection(Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection) {
        this.puzzleSkillLearningContentDTOCollection = puzzleSkillLearningContentDTOCollection;
    }

    public PGDTO() {
    }

    public Collection<PuzzleGroup_PuzzleObjectDTO> getPuzzleGroup_puzzleObjectDTOCollection() {
        return puzzleGroup_puzzleObjectDTOCollection;
    }

    public void setPuzzleGroup_puzzleObjectDTOCollection(Collection<PuzzleGroup_PuzzleObjectDTO> puzzleGroup_puzzleObjectDTOCollection) {
        this.puzzleGroup_puzzleObjectDTOCollection = puzzleGroup_puzzleObjectDTOCollection;
    }

    public PGDTO(Long id, Long version, String created, String updated, String title, BinaryContentDTO icon, BinaryContentDTO pic, Collection<JourneyStepDTO> journeyStepDTOCollection, Collection<PLDTO> puzzleLevelDTOCollection, Collection<PuzzleSkillLearningContentDTO> puzzleSkillLearningContentDTOCollection) {
        super(id, version, created, updated);
        this.title = title;
        this.icon = icon;
        this.pic = pic;
        this.journeyStepDTOCollection = journeyStepDTOCollection;
        this.puzzleLevelDTOCollection = puzzleLevelDTOCollection;
        this.puzzleSkillLearningContentDTOCollection = puzzleSkillLearningContentDTOCollection;
    }
}