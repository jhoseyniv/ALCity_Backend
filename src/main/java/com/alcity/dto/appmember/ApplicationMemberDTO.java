package com.alcity.dto.appmember;


public class ApplicationMemberDTO  {

    private Long id;
    private Integer age;
    private String username;
    private String password;
    private String nickname;
    private String mobile;
    private String email;
    private byte[] avatar;
    private Long createdById;
    private Long updatedById;


    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public ApplicationMemberDTO() {
    }

    public ApplicationMemberDTO(Long id, Integer age, String username, String password, String nickname, String mobile, String email, byte[] avatar, Long createdById, Long updatedById) {
        this.id = id;
        this.age = age;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.avatar = avatar;
        this.createdById = createdById;
        this.updatedById = updatedById;
    }
}