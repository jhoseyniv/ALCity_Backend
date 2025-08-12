package com.alcity.entity.alenum;

public enum ErrorType {
    SaveSuccess,
    ImportSuccess,
    UpdateSuccess,
    CopySuccess,
    DeleteSuccess,
    SaveFail,
    UpdateFail,
    RecordNotFound,
    UniquenessViolation,
    ForeignKeyViolation,
    NullValueViolation,
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
