package com.alcity.dto.learning;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.base.BinaryContent;

public class LearningContentDTO extends BaseTableDTO {
    private String descText;
    private String descBrief;
    private BinaryContentDTO binaryContentDTO;

    public LearningContentDTO() {
    }

    public LearningContentDTO(Long id, Long version, String created, String updated, String descText, String descBrief, BinaryContentDTO binaryContentDTO) {
        super(id, version, created, updated);
        this.descText = descText;
        this.descBrief = descBrief;
        this.binaryContentDTO = binaryContentDTO;
    }

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getDescBrief() {
        return descBrief;
    }

    public void setDescBrief(String descBrief) {
        this.descBrief = descBrief;
    }

    public BinaryContentDTO getBinaryContentDTO() {
        return binaryContentDTO;
    }

    public void setBinaryContentDTO(BinaryContentDTO binaryContentDTO) {
        this.binaryContentDTO = binaryContentDTO;
    }
}
