package com.alcity.dto.plimpexport;

import com.alcity.dto.plimpexport.rulemport.PLRuleImport;

import java.io.Serializable;
import java.util.Collection;

public class PLImportDTO implements Serializable {

    private Long puzzleGroupId;
    private Long puzzleTemplateId;
    private Long id;

    private String code;
    private String title;

    private Long ordering;

    private Integer fromAge;
    private Integer toAge;
    private Float maxScore;
    private Float firstStarScore;
    private Float secondStarScore;
    private Float thirdStartScore;

    private Long iconId;
    private Long picId;

    private Integer cols;
    private Integer rows;

    private String puzzleLevelDifficulty;

    private String puzzleLevelStatus;

    private String puzzleLevelPrivacy;

    private String approveDate;

    private CameraSetupData cameraSetup;

    private Long boardGraphicId;
    private Collection<PLObjectiveData> objectives;

    private Collection<AttributeData> variables;

    private Collection<PGObjectData> objects;

    private Collection<PLCellImport> cells;
    private Collection<PLRuleImport> rules;
    private Collection<PLLearningTopicData> learningTopics;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Collection<AttributeData> getVariables() {
        return variables;
    }

    public void setVariables(Collection<AttributeData> variables) {
        this.variables = variables;
    }

    public Collection<PLCellImport> getCells() {
        return cells;
    }

    public void setCells(Collection<PLCellImport> cells) {
        this.cells = cells;
    }

    public Collection<PLObjectiveData> getObjectives() {
        return objectives;
    }

    public void setObjectives(Collection<PLObjectiveData> objectives) {
        this.objectives = objectives;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public Integer getCols() {
        return cols;
    }

    public void setCols(Integer cols) {
        this.cols = cols;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public Float getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }

    public Float getFirstStarScore() {
        return firstStarScore;
    }

    public void setFirstStarScore(Float firstStarScore) {
        this.firstStarScore = firstStarScore;
    }

    public Float getSecondStarScore() {
        return secondStarScore;
    }

    public void setSecondStarScore(Float secondStarScore) {
        this.secondStarScore = secondStarScore;
    }

    public Float getThirdStartScore() {
        return thirdStartScore;
    }

    public void setThirdStartScore(Float thirdStartScore) {
        this.thirdStartScore = thirdStartScore;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public String getPuzzleLevelDifficulty() {
        return puzzleLevelDifficulty;
    }

    public void setPuzzleLevelDifficulty(String puzzleLevelDifficulty) {
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }

    public String getPuzzleLevelStatus() {
        return puzzleLevelStatus;
    }

    public void setPuzzleLevelStatus(String puzzleLevelStatus) {
        this.puzzleLevelStatus = puzzleLevelStatus;
    }

    public String getPuzzleLevelPrivacy() {
        return puzzleLevelPrivacy;
    }

    public void setPuzzleLevelPrivacy(String puzzleLevelPrivacy) {
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public CameraSetupData getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupData cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public Collection<PLRuleImport> getRules() {
        return rules;
    }

    public void setRules(Collection<PLRuleImport> rules) {
        this.rules = rules;
    }

    public Collection<PGObjectData> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PGObjectData> objects) {
        this.objects = objects;
    }

    public Long getBoardGraphicId() {
        return boardGraphicId;
    }

    public void setBoardGraphicId(Long boardGraphicId) {
        this.boardGraphicId = boardGraphicId;
    }

    public Collection<PLLearningTopicData> getLearningTopics() {
        return learningTopics;
    }

    public void setLearningTopics(Collection<PLLearningTopicData> learningTopics) {
        this.learningTopics = learningTopics;
    }

    public Long getPuzzleTemplateId() {
        return puzzleTemplateId;
    }

    public void setPuzzleTemplateId(Long puzzleTemplateId) {
        this.puzzleTemplateId = puzzleTemplateId;
    }

    public PLImportDTO() {
    }

    public PLImportDTO(Long puzzleGroupId,Long puzzleTemplateId, Long id, String code, String title, Long ordering, Integer fromAge, Integer toAge,
                       Float maxScore, Float firstStarScore, Float secondStarScore, Float thirdStartScore, Long iconId, Long picId,
                       Integer cols, Integer rows,Long boardGraphicId,
                       String puzzleLevelDifficulty, String puzzleLevelStatus, String puzzleLevelPrivacy, String approveDate) {
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleTemplateId = puzzleTemplateId;
        this.id = id;
        this.code = code;
        this.title = title;
        this.ordering = ordering;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.firstStarScore = firstStarScore;
        this.secondStarScore = secondStarScore;
        this.thirdStartScore = thirdStartScore;
        this.iconId = iconId;
        this.picId = picId;
        this.cols = cols;
        this.rows = rows;
        this.boardGraphicId =boardGraphicId;
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.approveDate = approveDate;
    }
}
