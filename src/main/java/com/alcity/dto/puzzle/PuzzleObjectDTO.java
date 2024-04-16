package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;

import java.util.Collection;
import java.util.Set;

public class PuzzleObjectDTO extends BaseTableDTO {
    private String title;
    private String objectCategory;

    private String code;

    private BinaryContentDTO picture;
    private BinaryContentDTO icon;

    public PuzzleObjectDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public BinaryContentDTO getPicture() {
        return picture;
    }

    public void setPicture(BinaryContentDTO picture) {
        this.picture = picture;
    }

    public BinaryContentDTO getIcon() {
        return icon;
    }

    public void setIcon(BinaryContentDTO icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


}
