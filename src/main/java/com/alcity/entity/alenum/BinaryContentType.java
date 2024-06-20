package com.alcity.entity.alenum;



public enum BinaryContentType {
    Image,
    File,
    Video,
    Icon,
    Voice,
    Other;

    public static BinaryContentType getById(long id)
    {
        for (BinaryContentType e : BinaryContentType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    }
