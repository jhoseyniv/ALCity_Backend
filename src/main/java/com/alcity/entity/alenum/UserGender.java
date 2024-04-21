package com.alcity.entity.alenum;

public enum UserGender  {

    Female,
    Male;

    public static UserGender getById(long id)
    {
        for (UserGender e : UserGender.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
