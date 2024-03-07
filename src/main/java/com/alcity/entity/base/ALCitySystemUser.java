package com.alcity.entity.base;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ALCitySystemUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    public Long getId() {
        return id;
    }
    @Column(name="userame")
    private String username;

    @Column(name="password")
    private String password;


    @Column(name="nickname")
    private String nickname;

    @Column(name="mobile")
    private String mobile;

    @Column(name="email")
    private String email;

    public ALCitySystemUser() {
    }

    public ALCitySystemUser(String username, String password, String nickname, String mobile, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
    }
}
