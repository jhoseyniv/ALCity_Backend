package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PLCellDTO  implements Serializable {
    private Long id;
    private Integer row;
    private Integer col;
    private Integer zorder;
    private Long plGroundId;


    public PLCellDTO(Long id, Integer row, Integer col, Integer zorder,Long plGroundId) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.zorder = zorder;
        this.plGroundId = plGroundId;
    }
}
