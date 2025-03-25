package com.alcity.dto.puzzle;

import com.alcity.dto.puzzle.boardgraphic.BoardGraphicDTO;
import org.json.JSONObject;

import java.io.Serializable;

public class PLGroundDTO  implements Serializable {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Integer numRows;
    private Integer numColumns;

    private Integer xposition;
    private Integer yposition;
    private Integer zposition;
    private Integer xrotation;
    private Integer yrotation;
    private Integer zrotation;
    private Long puzzleLevelId;

  //  private String puzzleLevelTitle;


    private BoardGraphicDTO boardGraphicDTO;

    public Integer getNumRows() {
        return numRows;
    }

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(Integer numColumns) {
        this.numColumns = numColumns;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

//    public String getPuzzleLevelTitle() {
//        return puzzleLevelTitle;
//    }
//
//    public void setPuzzleLevelTitle(String puzzleLevelTitle) {
//        this.puzzleLevelTitle = puzzleLevelTitle;
//    }


    public BoardGraphicDTO getBoardGraphicDTO() {
        return boardGraphicDTO;
    }

    public void setBoardGraphicDTO(BoardGraphicDTO boardGraphicDTO) {
        this.boardGraphicDTO = boardGraphicDTO;
    }

    public Integer getXposition() {
        return xposition;
    }

    public void setXposition(Integer xposition) {
        this.xposition = xposition;
    }

    public Integer getYposition() {
        return yposition;
    }

    public void setYposition(Integer yposition) {
        this.yposition = yposition;
    }

    public Integer getZposition() {
        return zposition;
    }

    public void setZposition(Integer zposition) {
        this.zposition = zposition;
    }

    public Integer getXrotation() {
        return xrotation;
    }

    public void setXrotation(Integer xrotation) {
        this.xrotation = xrotation;
    }

    public Integer getYrotation() {
        return yrotation;
    }

    public void setYrotation(Integer yrotation) {
        this.yrotation = yrotation;
    }

    public Integer getZrotation() {
        return zrotation;
    }

    public void setZrotation(Integer zrotation) {
        this.zrotation = zrotation;
    }

    public PLGroundDTO() {
    }


    public PLGroundDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                       Integer numRows, Integer numColumns, Integer xPosition,Integer yPosition,Integer zPosition,Integer xRotation,Integer yRotation ,Integer zRotation,
                       Long puzzleLevelId, //String puzzleLevelTitle,
                       BoardGraphicDTO boardGraphicDTO) {
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

        this.puzzleLevelId = puzzleLevelId;
     //   this.puzzleLevelTitle = puzzleLevelTitle;
        this.boardGraphicDTO = boardGraphicDTO;
    }
}
