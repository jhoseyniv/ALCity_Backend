package com.alcity.dto.learning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor


public class LearningContentDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;


    private String descText;
    private String descBrief;
    private String fileName;
    private Integer size;

    private String contentType;

    private Long binaryContentId;

    public LearningContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String descText, String descBrief, String fileName, Integer size, String contentType, Long binaryContentId) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.descText = descText;
        this.descBrief = descBrief;
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.binaryContentId = binaryContentId;
    }
}
