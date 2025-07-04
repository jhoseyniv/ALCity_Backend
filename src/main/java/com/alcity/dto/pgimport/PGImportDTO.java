package com.alcity.dto.pgimport;

import com.alcity.dto.puzzle.PGLearningSkillContentDTO;

import java.util.Collection;

public class PGImportDTO {
    private Long id;
    private String title;
    private Long iconInfo;
    private Long picInfo;
    private Long puzzleCategoryId;
    private Collection<PGObjectImportDTO> objects;
    private Collection<PGLearningSkillContentImportDTO> skills;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getIconInfo() {
        return iconInfo;
    }

    public void setIconInfo(Long iconInfo) {
        this.iconInfo = iconInfo;
    }

    public Long getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(Long picInfo) {
        this.picInfo = picInfo;
    }

    public Collection<PGObjectImportDTO> getObjects() {
        return objects;
    }

    public void setObjects(Collection<PGObjectImportDTO> objects) {
        this.objects = objects;
    }

    public Collection<PGLearningSkillContentImportDTO> getSkills() {
        return skills;
    }

    public void setSkills(Collection<PGLearningSkillContentImportDTO> skills) {
        this.skills = skills;
    }

    public Long getPuzzleCategoryId() {
        return puzzleCategoryId;
    }

    public void setPuzzleCategoryId(Long puzzleCategoryId) {
        this.puzzleCategoryId = puzzleCategoryId;
    }

    public PGImportDTO() {
    }

    public PGImportDTO(Long id, String title, Long iconInfo, Long picInfo,Long puzzleCategoryId,
                       Collection<PGObjectImportDTO> objects, Collection<PGLearningSkillContentImportDTO> skills) {
        this.id = id;
        this.title = title;
        this.iconInfo = iconInfo;
        this.puzzleCategoryId = puzzleCategoryId;
        this.picInfo = picInfo;
        this.objects = objects;
        this.skills = skills;
    }
}
