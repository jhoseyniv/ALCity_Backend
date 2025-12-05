package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberEnergyDTO  implements Serializable {

    private Integer energy;
    private ZonedDateTime refillEnergyExpirationTime;

    public AppMemberEnergyDTO(Integer energy, ZonedDateTime refillEnergyExpirationTime) {
        this.energy = energy;
        this.refillEnergyExpirationTime = refillEnergyExpirationTime;
    }
}
