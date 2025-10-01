package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class GuestUserDTO {
    private Long id;
    private String username;
    private String password;
    private String memberType;
    private Long version;

    private String created;
    private String updated;

    private String createdBy;
    private String updatedBy;

    public GuestUserDTO(Long id, String username, String password, String memberType, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.memberType = memberType;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
