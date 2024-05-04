package com.alcity.entity.base;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ExprFunction extends BaseItemSet implements Serializable {

    @Column(name="numberOfArgs")
    private Integer numberOfArgs;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "output_data_type_id", nullable = false)
    @JsonIgnore
    private DataType outputDataTypeid;

}
