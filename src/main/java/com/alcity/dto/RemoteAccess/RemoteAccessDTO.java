package com.alcity.dto.RemoteAccess;

public class RemoteAccessDTO {

    public String remoteHost;
    public String remoteUserName;
    public Integer birthYear;
    public String puzzleCode;

    public String getRemoteHost() {
        return remoteHost;
    }

    public void setRemoteHost(String remoteHost) {
        this.remoteHost = remoteHost;
    }

    public String getRemoteUserName() {
        return remoteUserName;
    }

    public void setRemoteUserName(String remoteUserName) {
        this.remoteUserName = remoteUserName;
    }

    public String getPuzzleCode() {
        return puzzleCode;
    }

    public void setPuzzleCode(String puzzleCode) {
        this.puzzleCode = puzzleCode;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public RemoteAccessDTO() {
    }

    public RemoteAccessDTO(String remoteHost, String remoteUserName, Integer birthYear, String puzzleCode) {
        this.remoteHost = remoteHost;
        this.remoteUserName = remoteUserName;
        this.birthYear = birthYear;
        this.puzzleCode = puzzleCode;
    }
}
