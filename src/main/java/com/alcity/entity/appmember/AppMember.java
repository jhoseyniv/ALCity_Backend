package com.alcity.entity.appmember;


import com.alcity.entity.alenum.AppMemberStatus;
import com.alcity.entity.alenum.Language;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.*;
import com.alcity.entity.play.PermittedPlayer;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PLGameInstance;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="ApplicationMember")
public class AppMember extends BaseTable implements Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    private Long id;

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




    @OneToMany(mappedBy = "applicationMember", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<AppMember_WalletItem> walletItems;

    @OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PuzzleLevel> puzzleLevels;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PLGameInstance> plGameInstances;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<PermittedPlayer> permitedPlayers;

    @OneToMany(mappedBy = "player", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<AppMemberPuzzleLevelScore> puzzleLevelScores;


    public Collection<AppMember_WalletItem> getApplicationMember_walletItems() {
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

    @Column(name="notificationCode")
    private StringBuffer notificationCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "member_type_id", nullable = false)
    @JsonIgnore
    private MemberType memberType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "application_member_id"),
            inverseJoinColumns = @JoinColumn(name = "client_type_id"))

    private Collection<ClientType> clientTypes;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "energyConfig_id", nullable = false)
    @JsonIgnore
    private EnergyConfig energyConfig;

    @Column(name="refillEnergyExpirationTime")
    private ZonedDateTime refillEnergyExpirationTime;

    @Column(name="energy")
    private Integer energy;

    public AppMember(Integer age, Language language , String username, String password, String nickname, String mobile, String email, BinaryContent icon, UserGender gender, MemberType memberType,ZonedDateTime refillEnergyExpirationTime,Integer energy,EnergyConfig energyConfig
                     ,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
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
        this.refillEnergyExpirationTime = refillEnergyExpirationTime;
        this.energy = energy;
        this.energyConfig = energyConfig;
    }
}
