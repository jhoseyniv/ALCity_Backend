package com.alcity.dto.puzzle;

import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
public class PLGroundDTO  implements Serializable {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Integer numRows;
    private Integer numColumns;

    private Float xposition;
    private Float yposition;
    private Float zposition;
    private Float xrotation;
    private Float yrotation;
    private Float zrotation;

    private Boolean zoom;
    private Boolean pan;
    private Boolean rotation;
    private Integer    initValueZoomLimit;
    private Integer    boardCenterX;
    private Integer    boardCenterY;
    private Integer    boardCenterZ;
    private Integer    panLimit;
    private Integer    initValueZoom;
    private Integer    initPanOffsetX;
    private Integer    initPanOffsetY;
    private Integer    initPanOffsetZ;
    private Long    skybox_id;
    private Long    background_id;
    private Integer    backgroundScale;
    private Long puzzleLevelId;

    public PLGroundDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                       Integer numRows, Integer numColumns, Float xPosition,Float yPosition,Float zPosition,Float xRotation,Float yRotation ,Float zRotation,
                       Long puzzleLevelId, Integer  boardCenterX, Integer boardCenterY, Integer boardCenterZ, Integer panLimit,Integer initValueZoomLimit,Integer initValueZoom,
                       Integer initPanOffsetX,Integer initPanOffsetY,Integer initPanOffsetZ
                      ) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.xposition= xPosition;
        this.yposition = yPosition;
        this.zposition = zPosition;
        this.xrotation = xRotation;
        this.yrotation = yRotation;
        this.zrotation = zRotation;
        this.boardCenterX = boardCenterX;
        this.boardCenterY = boardCenterY;
        this.boardCenterZ = boardCenterZ;
        this.initPanOffsetX = initPanOffsetX;
        this.initPanOffsetY = initPanOffsetY;
        this.initPanOffsetZ = initPanOffsetZ;
        this.panLimit = panLimit;
        this.initValueZoom = initValueZoom;
        this.initValueZoomLimit = initValueZoomLimit;
        this.puzzleLevelId = puzzleLevelId;
    }
}
