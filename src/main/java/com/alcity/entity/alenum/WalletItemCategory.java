package com.alcity.entity.alenum;

public enum WalletItemCategory {

    Money,
    CryptoCurrency,
    Object,
    AL_Coin;

    public static WalletItemCategory getById(long id)
    {
        for (WalletItemCategory e : WalletItemCategory.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }

    public static WalletItemCategory getByTitle(String title)
    {
        for (WalletItemCategory e : WalletItemCategory.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }


}
