package com.alcity.dto.appmember;

import com.alcity.dto.plimpexport.BoardCenterDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
@Getter
@Setter
@NoArgsConstructor

public class AppMemberWalletDTO {

    private Long id;
    private Long appMemberId;
    private String appMemberUsername;

    private Long walletItemId;
    private String walletItemTitle;

    private Long iconId;

    private String walletItemType;
    private Boolean currency;

    private  Float amount;

    public AppMemberWalletDTO(Long id, Long appMemberId, String appMemberUsername,
                              Long walletItemId, String walletItemTitle, String walletItemType,
                              Boolean currency, Float amount, Long iconId) {
        this.id = id;
        this.appMemberId = appMemberId;
        this.appMemberUsername = appMemberUsername;
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.walletItemType = walletItemType;
        this.amount = amount;
        this.iconId = iconId;
        this.currency = currency;
    }
}
