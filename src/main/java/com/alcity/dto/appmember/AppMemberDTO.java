package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberDTO {

    private Long id;
    private Integer age;

    private String language;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private Long iconId;
    private Integer energy;

    private String memberType;
    private String gender;

    private Long version;

    private String created;
    private String updated;

    private String createdBy;
    private String updatedBy;


    public AppMemberDTO(Long id, Integer age,String language, String username, String password,Long iconId, String nickname, String mobile, String email, String gender, String memberType ,Integer energy ,
                        Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.age = age;
        this.language=language;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.memberType = memberType;
        this.gender = gender;
        this.version = version;
        this.energy = energy;
        this.iconId = iconId;
        this.gender = gender;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
