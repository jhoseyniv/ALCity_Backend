package com.alcity.entity.alenum;



public enum BinaryContentType {
    Image,
    File,
    Video,
    Icon,
    Voice,
    D2Object,
    D3Object,
    BoardGraphic_JSON,
    Other;

    public static BinaryContentType getById(long id)
    {
        for (BinaryContentType e : BinaryContentType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static BinaryContentType getByTitle(String title)
    {
        for (BinaryContentType e : BinaryContentType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
