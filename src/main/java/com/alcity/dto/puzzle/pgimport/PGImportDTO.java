package com.alcity.dto.puzzle.pgimport;

import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.dto.puzzle.PGLearningSkillContentDTO;
import com.alcity.dto.puzzle.object.CityObjectDTO;

import java.util.Collection;

public class PGImportDTO {
    private Long id;
    private String title;
    private Long iconInfo;
    private Long picInfo;
    private Collection<CityObjectDTO> objects;
    private Collection<PGLearningSkillContentDTO> skills;

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

    public Collection<CityObjectDTO> getObjects() {
        return objects;
    }

    public void setObjects(Collection<CityObjectDTO> objects) {
        this.objects = objects;
    }

    public Collection<PGLearningSkillContentDTO> getSkills() {
        return skills;
    }

    public void setSkills(Collection<PGLearningSkillContentDTO> skills) {
        this.skills = skills;
    }

    public PGImportDTO() {
    }

    public PGImportDTO(Long id, String title, Long iconInfo, Long picInfo,
                       Collection<CityObjectDTO> objects, Collection<PGLearningSkillContentDTO> skills) {
        this.id = id;
        this.title = title;
        this.iconInfo = iconInfo;
        this.picInfo = picInfo;
        this.objects = objects;
        this.skills = skills;
    }
}
