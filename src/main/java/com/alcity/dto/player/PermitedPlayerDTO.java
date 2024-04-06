package com.alcity.dto.player;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.user.ApplicationMemberDTO;
import com.alcity.entity.users.ApplicationMember;

public class PermitedPlayerDTO  extends BaseTableDTO {
    private String playerUsername;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PermitedPlayerDTO() {
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }
}
