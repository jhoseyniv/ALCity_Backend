package com.alcity.entity.alenum;


public enum PLObjectiveTransactionType {
    WalletItem,
    LearningSkill;

    public static PLObjectiveTransactionType getById(long id)
    {
        for (PLObjectiveTransactionType e : PLObjectiveTransactionType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static PLObjectiveTransactionType getByTitle(String title)
    {
        for (PLObjectiveTransactionType e : PLObjectiveTransactionType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }


}
