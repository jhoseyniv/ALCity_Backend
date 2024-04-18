package com.alcity.entity.users;


import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="ApplicationMember")
public class ApplicationMember extends BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

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
    @Type(type = "org.hibernate.type.ImageType")
    private byte[] avatar;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public UserGender getGender() {
        return gender;
    }

    public void setGender(UserGender gender) {
        this.gender = gender;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    @OneToMany(mappedBy = "applicationMember", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ApplicationMember_WalletItem> walletItems;

    public Set<ApplicationMember_WalletItem> getApplicationMember_walletItems() {
        return walletItems;
    }

    public void setApplicationMember_walletItems(Set<ApplicationMember_WalletItem> applicationMember_walletItems) {
        this.walletItems = applicationMember_walletItems;
    }

    @Enumerated(EnumType.ORDINAL)
    private UserGender gender;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_type_id", nullable = false)
    @JsonIgnore
    private MemberType memberType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "application_member_id"),
            inverseJoinColumns = @JoinColumn(name = "client_type_id"))

    private Set<ClientType> clientTypeSet;

    public Set<ClientType> getClientTypeSet() {
        return clientTypeSet;
    }

    public void setClientTypeSet(Set<ClientType> clientTypeSet) {
        this.clientTypeSet = clientTypeSet;
    }



    public ApplicationMember() {
    }

    public ApplicationMember(Integer age, String username, String password, String nickname, String mobile, String email, byte[] avatar, UserGender gender, MemberType memberType,Long version, Long creationDate, Long lastModifiedDate, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, creationDate, lastModifiedDate, createdBy, updatedBy);
        this.age = age;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.avatar = avatar;
        this.gender = gender;
        this.memberType = memberType;
    }
}
