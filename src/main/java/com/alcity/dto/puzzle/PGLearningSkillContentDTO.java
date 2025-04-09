package com.alcity.dto.puzzle;

public class PGLearningSkillContentDTO {


    private Long id;

    private String learningSkillTitle;
    private Long learningSkillId;

    private String puzzleGroupTitle;
    private Long puzzleGroupId;
    private Long learningContentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


     public String getLearningSkillTitle() {
        return learningSkillTitle;
    }

    public void setLearningSkillTitle(String learningSkillTitle) {
        this.learningSkillTitle = learningSkillTitle;
    }

    public Long getLearningSkillId() {
        return learningSkillId;
    }

    public void setLearningSkillId(Long learningSkillId) {
        this.learningSkillId = learningSkillId;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(Long learningContentId) {
        this.learningContentId = learningContentId;
    }

    public PGLearningSkillContentDTO() {
    }

    public PGLearningSkillContentDTO(Long id , String learningSkillTitle,
                                     Long learningSkillId, String puzzleGroupTitle, Long puzzleGroupId, Long learningContentId) {
        this.id = id;
        this.learningSkillTitle = learningSkillTitle;
        this.learningSkillId = learningSkillId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.learningContentId = learningContentId;
    }
}
