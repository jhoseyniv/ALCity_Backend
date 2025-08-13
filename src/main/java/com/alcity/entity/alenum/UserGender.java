package com.alcity.entity.alenum;

public enum UserGender  {

    Female,
    Male,
    Unknow;


    public static UserGender getById(long id)
    {
        for (UserGender e : UserGender.values())
        {
            if (id == e.ordinal()) return e;
        }
        return UserGender.Unknow;
    }
    public static UserGender getByTitle(String title)
    {
        for (UserGender e : UserGender.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        return UserGender.Unknow;
    }

}
