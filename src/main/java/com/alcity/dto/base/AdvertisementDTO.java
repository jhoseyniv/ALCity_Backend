package com.alcity.dto.base;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AdvertisementDTO {

    private Long id;
    private String adText;

    private String adsType;

    private Long version;

    private String created;
    private String updated;

    private String createdBy;
    private String updatedBy;


    public AdvertisementDTO(Long id,String adText, String adsType, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.adText = adText;
        this.adsType = adsType;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
