package com.alcity.dto.puzzle;

import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.dto.alobject.PuzzleObjectActionOwnerTypeDTO;
import com.alcity.entity.alobject.ObjectAction;

public class PuzzleObject_ObjectActionDTO extends BaseTableDTO {

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

    public PuzzleObject_ObjectActionDTO() {
    }

    public PuzzleObject_ObjectActionDTO(Long id, Long version, String created, String updated, Long ownerObjectid, ObjectAction objectAction, PuzzleObjectActionOwnerTypeDTO puzzleObjectActionOwnerTypeDTO, ActionRendererDTO actionRendererDTO) {
        super(id, version, created, updated);
        this.ownerObjectid = ownerObjectid;
        this.objectAction = objectAction;
        this.puzzleObjectActionOwnerTypeDTO = puzzleObjectActionOwnerTypeDTO;
        this.actionRendererDTO = actionRendererDTO;
    }
}
