package com.alcity.entity.alenum;

public enum MessageType {
    TermCondition,
    Advertisement,
    Chat,
    Other;

    public static MessageType getById(long id)
    {
        for (MessageType e : MessageType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static MessageType getByTitle(String title)
    {
        for (MessageType e : MessageType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
