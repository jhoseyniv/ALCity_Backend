package com.alcity.dto.learning;

import com.alcity.dto.base.BaseItemSetDTO;
import com.alcity.dto.base.BaseTableDTO;

public class LearningSkillDTO extends BaseItemSetDTO {

    public LearningSkillDTO() {
    }

    public LearningSkillDTO(Long id, String label, String value, Long version, String created, String updated) {
        super(id, label, value, version, created, updated);
    }
}
