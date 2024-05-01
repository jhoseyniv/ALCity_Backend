package com.alcity.entity.alenum;


import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;

public enum BinaryContentType {
    Image,
    File,
    Video,
    Icon,
    Voice;

    public static BinaryContentType getById(long id)
    {
        for (BinaryContentType e : BinaryContentType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    }
