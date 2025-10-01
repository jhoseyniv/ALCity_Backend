package com.alcity.dto.player;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PermitedPlayerDTO   {

    private Long id;
    private Long plId;

    private String plTitle;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private Long playerId;
    private String playerUsername;
    private String email;

    public PermitedPlayerDTO(Long id,Long plId,String plTitle, Long version, String created, String updated, String createdBy, String updatedBy,
                             Long plyerId,String playerUsername, String email) {
        this.id =id;
        this.plId = plId;
        this.plTitle = plTitle;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.playerId = plyerId;
        this.playerUsername = playerUsername;
        this.email = email;
    }
}
