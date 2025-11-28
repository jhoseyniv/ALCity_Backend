package com.alcity.entity.alenum;

public enum ErrorType {
    SaveSuccess,
    ImportSuccess,
    InitSkillsSuccess,
    UpdateSuccess,
    CopySuccess,
    DeleteSuccess,
    SaveFail,
    UpdateFail,
    RecordNotFound,
    UniquenessViolation,
    ForeignKeyViolation,
    NullValueViolation,
    HUDAction_WalletItem_Must_NOT_Apply,
    PUZZLE_LEVEL_NOT_CREATED,
    JSON_CREATED_SUCCESSFULLY,
    Energy_Is_Not_Sufficient_To_PLay_Game,
    Energy_Is_Low_And_Timer_Start_To_Refill
    ;
    public static ErrorType getById(long id)
    {
        for (ErrorType e : ErrorType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static ErrorType getByTitle(String title)
    {
        for (ErrorType e : ErrorType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }
}
