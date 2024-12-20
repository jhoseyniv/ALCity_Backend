package com.alcity.entity.alenum;

public enum ADSType {
    TermAndCondition,
    Advertisement,
    Other;

    public static ADSType getById(long id)
    {
        for (ADSType e : ADSType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static ADSType getByTitle(String title)
    {
        for (ADSType e : ADSType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
