package com.alcity.entity.base;

import com.alcity.entity.alenum.MessageType;
import com.alcity.entity.alenum.WalletItemCategory;
import com.alcity.entity.appmember.AppMember;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Set;

@Entity
public class ALMessage extends BaseItemSet implements Serializable {


    @Column(name="msgText")
    private String msgText;

    @Enumerated(EnumType.ORDINAL)
    private MessageType messageType;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinTable(name = "ApplicationMember_ClientType_MAPPING", joinColumns = @JoinColumn(name = "client_type_id"),
            inverseJoinColumns = @JoinColumn(name = "application_member_id"))
    private Set<AppMember> applicationMemberSet;

}
