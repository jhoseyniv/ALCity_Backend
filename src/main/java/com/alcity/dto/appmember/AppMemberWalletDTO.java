package com.alcity.dto.appmember;

import com.alcity.dto.plimpexport.BoardCenterDTO;

import java.util.Collection;

public class AppMemberWalletDTO {

    private Long id;
    private Long appMemberId;
    private String appMemberUsername;

    private Long walletItemId;
    private String walletItemTitle;

    private Long iconId;

    private String walletItemType;
    private Boolean isCurrency;

    private  Float amount;

    public Long getWalletItemId() {
        return walletItemId;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }

    public String getWalletItemTitle() {
        return walletItemTitle;
    }

    public void setWalletItemTitle(String walletItemTitle) {
        this.walletItemTitle = walletItemTitle;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public AppMemberWalletDTO() {
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getWalletItemType() {
        return walletItemType;
    }

    public void setWalletItemType(String walletItemType) {
        this.walletItemType = walletItemType;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public String getAppMemberUsername() {
        return appMemberUsername;
    }

    public void setAppMemberUsername(String appMemberUsername) {
        this.appMemberUsername = appMemberUsername;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getCurrency() {
        return isCurrency;
    }

    public void setCurrency(Boolean currency) {
        isCurrency = currency;
    }

    public AppMemberWalletDTO(Long id, Long appMemberId, String appMemberUsername, Long walletItemId, String walletItemTitle, String walletItemType,Boolean isCurrency, Float amount, Long iconId) {
        this.id = id;
        this.appMemberId = appMemberId;
        this.appMemberUsername = appMemberUsername;
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.walletItemType = walletItemType;
        this.amount = amount;
        this.iconId = iconId;
        this.isCurrency = isCurrency;
    }
}
