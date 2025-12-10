package com.alcity.entity.appmember;


import com.alcity.entity.base.BaseItemSet;
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

@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id"))
})

public class EnergyConfig  extends BaseItemSet implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public Long getId() {
        return getId();
    }

    @Column(name="energy")
    private Integer energy;

    @Column(name="time_to_refill")
    private Integer timeToRefill;

    @OneToMany(mappedBy = "energyConfig", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<AppMember> members;

    @Column(name="expire", unique = true)
    private Boolean expire;

}
