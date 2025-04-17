package com.alcity.dto.journey;

import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.journey.JourneyLearningSkill;

import java.util.Set;

public class JourneyDTO   {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedById;

    private String title;

    private Long picId;
    private Long buttonPassedIconId;
    private Long buttonCurrenIconId;
    private Long buttonLockedIconId;

    private Integer ordering;

    private Integer minToPassStar;

    private Integer minToOpenStar;

    public Integer getMinToPassStar() {
        return minToPassStar;
    }

    public void setMinToPassStar(Integer minToPassStar) {
        this.minToPassStar = minToPassStar;
    }

    public Integer getMinToOpenStar() {
        return minToOpenStar;
    }

    public void setMinToOpenStar(Integer minToOpenStar) {
        this.minToOpenStar = minToOpenStar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPicId() {
        return picId;
    }

    public void setPicId(Long picId) {
        this.picId = picId;
    }

    public JourneyDTO() {
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
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

    public Long getButtonPassedIconId() {
        return buttonPassedIconId;
    }

    public void setButtonPassedIconId(Long buttonPassedIconId) {
        this.buttonPassedIconId = buttonPassedIconId;
    }

    public Long getButtonCurrenIconId() {
        return buttonCurrenIconId;
    }

    public void setButtonCurrenIconId(Long buttonCurrenIconId) {
        this.buttonCurrenIconId = buttonCurrenIconId;
    }

    public Long getButtonLockedIconId() {
        return buttonLockedIconId;
    }

    public void setButtonLockedIconId(Long buttonLockedIconId) {
        this.buttonLockedIconId = buttonLockedIconId;
    }

    public JourneyDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById,
                      String title, Long picId, Long buttonPassedIconId, Long buttonCurrenIconId, Long buttonLockedIconId, Integer ordering, Integer minToPassStar, Integer minToOpenStar) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.title = title;
        this.picId = picId;
        this.buttonPassedIconId = buttonPassedIconId;
        this.buttonCurrenIconId = buttonCurrenIconId;
        this.buttonLockedIconId = buttonLockedIconId;
        this.ordering = ordering;
        this.minToPassStar = minToPassStar;
        this.minToOpenStar = minToOpenStar;
    }
}
