package com.alcity.entity.users;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ApplicationMember_WalletItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonProperty("applicationMember")
    private ApplicationMember applicationMember;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "wallet_item_id", referencedColumnName = "id")
    @JsonIgnore
    private WalletItem walletItem;

    @Column(name="amount")
    private Float amount;

    public ApplicationMember_WalletItem() {
    }

    public ApplicationMember_WalletItem(ApplicationMember applicationMember, WalletItem walletItem, Float amount) {
        this.applicationMember = applicationMember;
        this.walletItem = walletItem;
        this.amount = amount;
    }
}
