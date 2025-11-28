package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberEnergyDTO  implements Serializable {

    private Integer energy;
    private LocalDateTime refillEnergyExpirationTime;

    public AppMemberEnergyDTO(Integer energy, LocalDateTime refillEnergyExpirationTime) {
        this.energy = energy;
        this.refillEnergyExpirationTime = refillEnergyExpirationTime;
    }
}
