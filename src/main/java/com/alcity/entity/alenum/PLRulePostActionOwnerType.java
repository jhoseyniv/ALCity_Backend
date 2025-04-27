package com.alcity.entity.alenum;

public enum PLRulePostActionOwnerType {

    Puzzle_Level_Rule,
    Inner_Rule_Post_Action,
    Else;

    public static PLRulePostActionOwnerType getById(long id)
    {
        for (PLRulePostActionOwnerType e : PLRulePostActionOwnerType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static PLRulePostActionOwnerType getByTitle(String title)
    {
        for (PLRulePostActionOwnerType e : PLRulePostActionOwnerType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
