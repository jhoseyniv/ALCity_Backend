package com.alcity.dto.puzzle;

import com.alcity.dto.alobject.AttributeDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.alobject.Attribute;

import java.util.Collection;

public class ALCityObjectDTO {

    private Long id;
    private String title;

    private Long categoryId;
    private String category;

    private Long pictureId;
    private Long iconId;

    private Collection<AttributeDTO> dependencies;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public ALCityObjectDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

     public Long getPictureId() {
        return pictureId;
    }

    public void setPictureId(Long pictureId) {
        this.pictureId = pictureId;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Collection<AttributeDTO> getDependencies() {
        return dependencies;
    }

    public void setDependencies(Collection<AttributeDTO> dependencies) {
        this.dependencies = dependencies;
    }

    public ALCityObjectDTO(Long id, String title, Long categoryId, String category, Long pictureId, Long iconId, Collection<AttributeDTO> dependencies,
                           Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.category = category;
        this.pictureId = pictureId;
        this.iconId = iconId;
        this.dependencies = dependencies;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
