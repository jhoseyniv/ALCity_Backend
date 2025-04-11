package com.alcity.o3rdparty;

public class O3rdPartyResponse {

    private String referenceId ;
    private Long puzzleLevelId ;
    private Long status ;


    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public O3rdPartyResponse() {
    }

    public O3rdPartyResponse(String referenceId, Long puzzleLevelId,Long status) {
        this.referenceId = referenceId;
        this.puzzleLevelId = puzzleLevelId;
        this.status = status;
    }
}
