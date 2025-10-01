package com.alcity.o3rdparty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ALCityAcessRight {
    private Long id;
    private String username;
    private Integer age;
    private String nickname;
    private String mobile;
    private String email;
    private Long iconId;
    private String memberType;
    private String gender;
    private Integer code;
    private String message;
    private String jwtToken;

    public ALCityAcessRight(Long id, String username, Integer code, String message, String jwtToken,Integer age, String nickname, String mobile, String email, Long iconId, String memberType, String gender
                            ) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.iconId = iconId;
        this.age = age;
        this.memberType = memberType;
        this.gender = gender;
        this.code = code;
        this.message = message;
        this.jwtToken = jwtToken;
    }
}
