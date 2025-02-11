package com.alcity.service.customexception;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ALCityAcessRight() {
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getMemberType() {
        return memberType;
    }

    public void setMemberType(String memberType) {
        this.memberType = memberType;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

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
