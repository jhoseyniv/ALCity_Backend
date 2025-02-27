package com.alcity.entity.alenum;

public enum DataType  {

    Integer,
    Long,
    Boolean,
    String,
    Binary,
    Experssion,
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
