package com.alcity.entity.alenum;

public enum PLRuleEventType  {

    SystemEvent,
    ObjectActionEvent,
    UserEvent;
   public static PLRuleEventType getById(long id)
    {
        for (PLRuleEventType e : PLRuleEventType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static PLRuleEventType getByTitle(String title)
    {
        for (PLRuleEventType e : PLRuleEventType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }


}
