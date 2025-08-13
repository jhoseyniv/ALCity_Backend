package com.alcity.entity.alenum;

public enum Language {

    English,
    Persian,
    Arabic;
    public static Language getById(long id)
    {
        for (Language e : Language.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static Language getByTitle(String title)
    {
        for (Language e : Language.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
       return Language.English;
    }

}
