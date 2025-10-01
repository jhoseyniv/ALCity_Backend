package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class InstanceDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;

    private String updatedBy;
    private String createdBy;


    private String name;

    private Integer row;
    private Integer col;
    private Integer zorder;
    private Long PGObjectId;
    private String PGObjectTitle;
    private String PGObjectCode ;
    private Long puzzleLevelId;

    public InstanceDTO(Long id,String name, Integer row, Integer col, Integer zorder, Long PGObjectId, String PGObjectTitle, String PGObjectCode, Long puzzleLevelId,
                         Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.PGObjectId = PGObjectId;
        this.PGObjectTitle = PGObjectTitle;
        this.PGObjectCode = PGObjectCode;
        this.puzzleLevelId = puzzleLevelId;
    }
}
