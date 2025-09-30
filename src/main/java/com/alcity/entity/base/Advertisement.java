package com.alcity.entity.base;


import com.alcity.entity.alenum.ADSType;
import com.alcity.entity.alenum.MessageType;
import com.alcity.entity.appmember.AppMember;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class Advertisement extends BaseTable implements Serializable {
    @Column(name="adText")
    private String adText;

    @Enumerated(EnumType.ORDINAL)
    private ADSType adsType;

    public Advertisement (String adText, ADSType adsType,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.adText = adText;
        this.adsType = adsType;
    }
}
