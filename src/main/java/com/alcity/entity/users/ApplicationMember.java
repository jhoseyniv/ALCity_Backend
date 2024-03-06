package com.alcity.entity.users;


import com.alcity.entity.base.BaseTable;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="ApplicationMember")
public class ApplicationMember extends BaseTable implements Serializable {

    @Column(name="age")
    private Integer age;

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

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] avatar;


}
