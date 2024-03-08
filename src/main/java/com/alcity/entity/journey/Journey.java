package com.alcity.entity.journey;


import com.alcity.entity.base.BaseTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="Journey")
public class Journey  extends BaseTable implements Serializable {
    @Column(name="title")
    private String title;


}
