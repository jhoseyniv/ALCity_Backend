package com.alcity.entity.alobject;

import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Set;

public enum   ObjectAction  {

    Move,
    Remove,
    Rotate,
    Enable,
    Disable,
    Hide,
    Create,
    Convert,
    Show,
    PlaySound;
    public static ObjectAction getById(Integer id)
    {
        for (ObjectAction e : ObjectAction.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no id");
    }


    }
