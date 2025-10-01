package com.alcity.dto.RemoteAccess;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RemoteRequestDTO {

    private String remoteHost;
    private String remoteUserName;
    private Integer birthYear;
    private String puzzleCode;

    public RemoteRequestDTO(String remoteHost, String remoteUserName, Integer birthYear, String puzzleCode) {
        this.remoteHost = remoteHost;
        this.remoteUserName = remoteUserName;
        this.birthYear = birthYear;
        this.puzzleCode = puzzleCode;
    }
}
