package com.alcity.dto.puzzle;

import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.dto.alobject.ObjectActionDTO;
import com.alcity.dto.alobject.PuzzleObjectActionOwnerTypeDTO;
import com.alcity.dto.base.BaseTableDTO;

public class PuzzleObject_ObjectActionDTO extends BaseTableDTO {

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }

    public ObjectActionDTO getObjectActionDTO() {
        return objectActionDTO;
    }

    public void setObjectActionDTO(ObjectActionDTO objectActionDTO) {
        this.objectActionDTO = objectActionDTO;
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
    private ObjectActionDTO objectActionDTO;
    private PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO;
    private ActionRendererDTO actionRendererDTO;

    public PuzzleObject_ObjectActionDTO() {
    }

    public PuzzleObject_ObjectActionDTO(Long id, Long version, String created, String updated, Long ownerObjectid, ObjectActionDTO objectActionDTO, PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO, ActionRendererDTO actionRendererDTO) {
        super(id, version, created, updated);
        this.ownerObjectid = ownerObjectid;
        this.objectActionDTO = objectActionDTO;
        this.puzzleObjectActionOwnerTypeDTO = puzzleObjectActionOwnerTypeDTO;
        this.actionRendererDTO = actionRendererDTO;
    }
}
