package com.alcity.entity.base;


import com.alcity.entity.alobject.Renderer;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class EnergyConfig  extends BaseItemSet implements Serializable {

    @Column(name="energy")
    private Integer energy;

    @Column(name="time_to_refill")
    private Integer timeToRefill;

    @OneToMany(mappedBy = "energyConfig", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<AppMember> members;


    @Column(name="expire")
    private Boolean expire;

}
