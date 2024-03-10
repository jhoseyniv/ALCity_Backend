package com.alcity.entity.users;


import com.alcity.entity.base.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name="ApplicationMember")
public class ApplicationMember extends RecordInformation implements Serializable {
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
    private byte[] avatar;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "gender_id", nullable = false)
    @JsonIgnore
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

    @OneToMany(mappedBy = "applicationMember", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<ApplicationMember_WalletItem> walletItems;


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
