package com.alcity.dto.puzzle;

public class PlLearningTopicDTO {
    private Long id;
    private Long puzzleLevelId;
    private String puzzleLevelTitle;

    private Long learningTopicId;
    private String learningTopicTitle;


    private Long learningContentId;
    private String learningContentDescText;
    private String learningContentDescBrief;
    private String learningContentFileName;
    private Long binaryContentId;

    public PlLearningTopicDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public String getPuzzleLevelTitle() {
        return puzzleLevelTitle;
    }

    public void setPuzzleLevelTitle(String puzzleLevelTitle) {
        this.puzzleLevelTitle = puzzleLevelTitle;
    }

    public Long getLearningTopicId() {
        return learningTopicId;
    }

    public void setLearningTopicId(Long learningTopicId) {
        this.learningTopicId = learningTopicId;
    }

    public String getLearningTopicTitle() {
        return learningTopicTitle;
    }

    public void setLearningTopicTitle(String learningTopicTitle) {
        this.learningTopicTitle = learningTopicTitle;
    }

    public Long getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(Long learningContentId) {
        this.learningContentId = learningContentId;
    }

    public String getLearningContentDescText() {
        return learningContentDescText;
    }

    public void setLearningContentDescText(String learningContentDescText) {
        this.learningContentDescText = learningContentDescText;
    }

    public String getLearningContentDescBrief() {
        return learningContentDescBrief;
    }

    public void setLearningContentDescBrief(String learningContentDescBrief) {
        this.learningContentDescBrief = learningContentDescBrief;
    }

    public String getLearningContentFileName() {
        return learningContentFileName;
    }

    public void setLearningContentFileName(String learningContentFileName) {
        this.learningContentFileName = learningContentFileName;
    }

    public Long getBinaryContentId() {
        return binaryContentId;
    }

    public void setBinaryContentId(Long binaryContentId) {
        this.binaryContentId = binaryContentId;
    }

    public PlLearningTopicDTO(Long id, Long puzzleLevelId, String puzzleLevelTitle, Long learningTopicId, String learningTopicTitle,
                              Long learningContentId, String learningContentDescText, String learningContentDescBrief, String learningContentFileName, Long binaryContentId) {
        this.id = id;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.learningTopicId = learningTopicId;
        this.learningTopicTitle = learningTopicTitle;
        this.learningContentId = learningContentId;
        this.learningContentDescText = learningContentDescText;
        this.learningContentDescBrief = learningContentDescBrief;
        this.learningContentFileName = learningContentFileName;
        this.binaryContentId = binaryContentId;
    }
}
