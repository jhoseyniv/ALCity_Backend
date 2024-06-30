package com.alcity.entity.alenum;

import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Set;

public enum DataType  {

    Integer,
    Long,
    Boolean,
    String,
    Binary,
    Object;
    public static DataType getById(long id)
    {
        for (DataType e : DataType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static DataType getByTitle(String title)
    {
        for (DataType e : DataType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }




}
