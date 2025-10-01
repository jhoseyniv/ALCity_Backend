package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
public class PuzzleCategoryDTO {
    private Long id;

    private String label;
    private String value;

    private String templateEditor;

    private Long version;
    private String created;
    private String updated;

    private String createdBy;
    private String updatedBy;

    private Long updatedById;
    private Long createdById;

    public PuzzleCategoryDTO(Long id, String label, String value,String templateEditor ,Long version,
                             String created, String updated, String createdBy, String updatedBy, Long updatedById, Long getCreatedById) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.templateEditor = templateEditor;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.updatedById = updatedById;
        this.createdById = createdById;
    }
}
