package com.alcity.dto.learning;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.entity.base.BaseTable;

public class LearningTopicDTO extends BaseTableDTO {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LearningTopicDTO() {
    }
}
