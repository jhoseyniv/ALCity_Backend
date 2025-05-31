package com.alcity.entity.alenum;

public enum WalletTransactionType {
    Puzzle_Objective,
    Transfer_By_User,
    Transfer_By_Shop;

    public static WalletTransactionType getById(long id)
    {
        for (WalletTransactionType e : WalletTransactionType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static WalletTransactionType getByTitle(String title)
    {
        for (WalletTransactionType e : WalletTransactionType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
