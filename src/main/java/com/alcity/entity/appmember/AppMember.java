package com.alcity.entity.appmember;


import com.alcity.entity.alenum.AppMemberStatus;
import com.alcity.entity.alenum.Language;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.*;
import com.alcity.entity.play.PermittedPlayer;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name="ApplicationMember")
public class AppMember extends BaseTable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name="age")
    private Integer age;

    @Column(name="userame" , unique = true)
    private String username;

    @Column(name="password")
    private String password;


    @Column(name="nickname")
    private String nickname;

    @Column(name="mobile")
    private String mobile;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icon_id")
    @JsonIgnore
    private BinaryContent icon;

    @ManyToMany( fetch = FetchType.EAGER)
    @JoinTable(name = "appmember_authority_mapping",
            joinColumns = @JoinColumn(name = "appmember_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private Collection<Authority> authorities;

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

    public BinaryContent getIcon() {
        return icon;
    }

    public void setIcon(BinaryContent icon) {
        this.icon = icon;
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
    private Collection<AppMember_WalletItem> walletItems;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PuzzleLevel> puzzleLevels;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PlayHistory> playHistories;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PermittedPlayer> permitedPlayers;

    public Collection<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<Authority> authorities) {
        this.authorities = authorities;
    }

    public Collection<AppMember_WalletItem> getApplicationMember_walletItems() {
        return walletItems;
    }

    public void setApplicationMember_walletItems(Collection<AppMember_WalletItem> applicationMember_walletItems) {
        this.walletItems = applicationMember_walletItems;
    }

    public Collection<AppMember_WalletItem> getWalletItems() {
        return walletItems;
    }

    public void setWalletItems(Collection<AppMember_WalletItem> walletItems) {
        this.walletItems = walletItems;
    }

    @Enumerated(EnumType.ORDINAL)
    private UserGender gender;

    @Enumerated(EnumType.ORDINAL)
    private AppMemberStatus status;

    @Enumerated(EnumType.ORDINAL)
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_type_id", nullable = false)
    @JsonIgnore
    private MemberType memberType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "application_member_id"),
            inverseJoinColumns = @JoinColumn(name = "client_type_id"))

    private Collection<ClientType> clientTypes;

    public Collection<ClientType> getClientTypes() {
        return clientTypes;
    }

    public void setClientTypes(Collection<ClientType> clientTypes) {
        this.clientTypes = clientTypes;
    }

    public Collection<PlayHistory> getPlayHistories() {
        return playHistories;
    }

    public void setPlayHistories(Collection<PlayHistory> playHistories) {
        this.playHistories = playHistories;
    }

    public Collection<PermittedPlayer> getPermitedPlayers() {
        return permitedPlayers;
    }

    public void setPermitedPlayers(Collection<PermittedPlayer> permitedPlayers) {
        this.permitedPlayers = permitedPlayers;
    }

    public Collection<PuzzleLevel> getPuzzleLevels() {
        return puzzleLevels;
    }

    public void setPuzzleLevels(Collection<PuzzleLevel> puzzleLevels) {
        this.puzzleLevels = puzzleLevels;
    }

    public AppMemberStatus getStatus() {
        return status;
    }

    public void setStatus(AppMemberStatus status) {
        this.status = status;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public AppMember() {
    }

    public AppMember(Integer age,Language language ,String username, String password, String nickname, String mobile, String email,BinaryContent icon, UserGender gender, MemberType memberType, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.age = age;
        this.language = language;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.mobile = mobile;
        this.email = email;
        this.icon = icon;
        this.gender = gender;
        this.memberType = memberType;
    }
}
