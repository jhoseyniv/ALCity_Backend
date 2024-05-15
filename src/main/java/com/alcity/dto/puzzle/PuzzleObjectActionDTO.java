package com.alcity.dto.puzzle;

import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.dto.alobject.PuzzleObjectActionOwnerTypeDTO;
import com.alcity.entity.alenum.ObjectAction;

public class PuzzleObjectActionDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
    }

    public PuzzleObjectActionOwnerTypeDTO getPuzzleObjectActionOwnerTypeDTO() {
        return puzzleObjectActionOwnerTypeDTO;
    }

    public void setPuzzleObjectActionOwnerTypeDTO(PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO) {
        this.puzzleObjectActionOwnerTypeDTO = puzzleObjectActionOwnerTypeDTO;
    }

    public ActionRendererDTO getActionRendererDTO() {
        return actionRendererDTO;
    }

    public void setActionRendererDTO(ActionRendererDTO actionRendererDTO) {
        this.actionRendererDTO = actionRendererDTO;
    }

    private Long ownerObjectid;

    private ObjectAction objectAction;
    private PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO;
    private ActionRendererDTO actionRendererDTO;

    public PuzzleObjectActionDTO() {
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

    public PuzzleObjectActionDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                                 Long ownerObjectid, ObjectAction objectAction) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.ownerObjectid = ownerObjectid;
        this.objectAction = objectAction;
    }
}
