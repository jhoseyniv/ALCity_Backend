package com.alcity.dto.learning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LearningTopicDTO  {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;

    private Long parentId;


    public LearningTopicDTO(Long id,String title,  Long parentId, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.parentId = parentId;
    }
}
