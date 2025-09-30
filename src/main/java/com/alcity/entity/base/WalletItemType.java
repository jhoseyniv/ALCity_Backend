package com.alcity.entity.base;

import com.alcity.entity.appmember.AppMember;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class WalletItemType extends BaseItemSet implements Serializable {

    @NotNull(message = "{bName.notempty}")
    private Boolean currency;

    public WalletItemType(String label, String value, Boolean currency, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(label, value, version, created, updated, createdBy,updatedBy);
        this.currency = currency ;
    }
}
