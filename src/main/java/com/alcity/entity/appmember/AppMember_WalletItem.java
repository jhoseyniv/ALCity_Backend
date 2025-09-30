package com.alcity.entity.appmember;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PLGameInstance;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"application_member_id", "wallet_item_id"})
})
@Entity
@Getter
@Setter
@NoArgsConstructor

public class AppMember_WalletItem extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember applicationMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "wallet_item_id", referencedColumnName = "id")
    @JsonIgnore
    private WalletItem walletItem;

    @OneToMany(mappedBy = "appMemberWalletItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<WalletTransaction> walletTransactions;

    @Column(name="amount")
    private Float amount;



    public AppMember_WalletItem(AppMember applicationMember, WalletItem walletItem, Float amount, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.applicationMember = applicationMember;
        this.walletItem = walletItem;
        this.amount = amount;
    }
}
