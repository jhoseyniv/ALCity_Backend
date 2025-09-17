package com.alcity.entity.alenum;

public enum DeviceType {
    Web,
    Android,
    IOS;
    public static DeviceType getById(long id)
    {
        for (DeviceType e : DeviceType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static DeviceType getByTitle(String title)
    {
        for (DeviceType e : DeviceType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
