package com.alcity.dto.puzzle.object;

import java.util.Collection;

public class CityObjectDTO {

    private Long id;
    private String title;

    private Long categoryId;
    private String category;

    private Long pictureId;
    private Long iconId;

    private Collection<PropertyDTO> properties;
    private Collection<ActionDTO> actions;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public CityObjectDTO() {
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

    public Collection<PropertyDTO> getProperties() {
        return properties;
    }

    public void setProperties(Collection<PropertyDTO> properties) {
        this.properties = properties;
    }

    public Collection<ActionDTO> getActions() {
        return actions;
    }

    public void setActions(Collection<ActionDTO> actions) {
        this.actions = actions;
    }

    public CityObjectDTO(Long id, String title, Long categoryId, String category, Long pictureId, Long iconId, Collection<PropertyDTO> properties,
                         Collection<ActionDTO> actions,
                         Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.title = title;
        this.categoryId = categoryId;
        this.category = category;
        this.pictureId = pictureId;
        this.iconId = iconId;
        this.properties = properties;
        this.actions = actions;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
