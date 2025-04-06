package com.alcity.dto.puzzle;

import com.alcity.dto.player.PermitedPlayerDTO;

import java.util.Collection;

public class PLDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;
    private String updatedBy;

    private Long createdById;
    private Long updatedById;

    private Long creatorId;

    private String creator;
    private String approveDate;
    private Long ordering;
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private Float maxScore;
    private Float firstStarScore;
    private Float secondStarScore;
    private Float thirdStartScore;
    private Long puzzleGroupId;

    private String puzzleGroupTitle;

    private Long plGroundId;

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    private String puzzleLevelStatus;

    private String puzzleLevelPrivacy;

    private String puzzleLevelDifficulty;

    private Long iconId;
    private Long picId;

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

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public Long getOrdering() {
        return ordering;
    }

    public void setOrdering(Long ordering) {
        this.ordering = ordering;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getPuzzleLevelDifficulty() {
        return puzzleLevelDifficulty;
    }

    public void setPuzzleLevelDifficulty(String puzzleLevelDifficulty) {
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }

    public Long getPlGroundId() {
        return plGroundId;
    }

    public void setPlGroundId(Long plGroundId) {
        this.plGroundId = plGroundId;
    }

    public PLDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public PLDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById,
                 Long creatorId, String creator,String approveDate,Long plGroundId,Long puzzleGroupId,String puzzleGroupTitle, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore,Float firstStarScore ,Float secondStarScore,Float thirdStarScore,String puzzleLevelStatus, String puzzleLevelPrivacy, String puzzleLevelDifficulty) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.creatorId = creatorId;
        this.creator = creator;
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.approveDate =approveDate;
        this.puzzleGroupId = plGroundId;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.firstStarScore = firstStarScore;
        this.secondStarScore =secondStarScore;
        this.thirdStartScore = thirdStartScore;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.puzzleLevelDifficulty = puzzleLevelDifficulty;
    }
}
