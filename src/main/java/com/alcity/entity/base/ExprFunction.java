package com.alcity.entity.base;

import com.alcity.entity.alenum.DataType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ExprFunction extends BaseItemSet implements Serializable {

    @Column(name="numberOfArgs")
    private Integer numberOfArgs;

    @Enumerated(EnumType.ORDINAL)
    private DataType outputDataTypeid;

}
