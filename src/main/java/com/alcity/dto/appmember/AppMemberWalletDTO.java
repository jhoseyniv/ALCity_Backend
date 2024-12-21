package com.alcity.dto.appmember;

import java.util.Collection;

public class AppMemberWalletDTO {

    private Long id;
    private Long appMemberId;
    private String appMemberUsername;

    private Long walletItemId;
    private String walletItemTitle;

    private byte[] thumbnail;

    private String walletItemType;

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

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
        this.thumbnail = thumbnail;
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

    public AppMemberWalletDTO(Long id, Long appMemberId, String appMemberUsername, Long walletItemId, String walletItemTitle, byte[] thumbnail, String walletItemType, Float amount) {
        this.id = id;
        this.appMemberId = appMemberId;
        this.appMemberUsername = appMemberUsername;
        this.walletItemId = walletItemId;
        this.walletItemTitle = walletItemTitle;
        this.thumbnail = thumbnail;
        this.walletItemType = walletItemType;
        this.amount = amount;
    }
}
