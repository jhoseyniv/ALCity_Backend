package com.alcity.entity.base;


import com.alcity.entity.alenum.Language;
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

public class Locale extends BaseTable implements Serializable {
    @Column(name="country")
    private String country;

    @Column(name="countryCode")
    private String countryCode;

    @Enumerated(EnumType.ORDINAL)
    private Language language;

    public Locale(String country, String countryCode, Language language ,
                  Long version, String created, String updated, AppMember createdBy, AppMember updatedBy ) {
        super(version, created, updated, createdBy, updatedBy);
        this.country = country;
        this.countryCode = countryCode;
        this.language = language;
    }
}
