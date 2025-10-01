package com.alcity.o3rdparty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class O3rdPartyResponse {

    private String referenceId ;
    private Long puzzleLevelId ;
    private Long status ;

    public O3rdPartyResponse(String referenceId, Long puzzleLevelId,Long status) {
        this.referenceId = referenceId;
        this.puzzleLevelId = puzzleLevelId;
        this.status = status;
    }
}
