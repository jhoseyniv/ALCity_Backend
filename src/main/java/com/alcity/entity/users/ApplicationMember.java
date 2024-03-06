package com.alcity.entity.users;


import com.alcity.entity.base.BaseTable;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="ApplicationMember")
public class ApplicationMember extends BaseTable implements Serializable {
}
