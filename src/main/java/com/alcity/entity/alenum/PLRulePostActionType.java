package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseTable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.io.Serializable;

public enum PLRulePostActionType  {

    FireEventAction,
    CallObjectAction,
    CallSystemAction,
    UserAlertAction,
    VariableAssignmentAction,
    ShowMessage,
    ObjectActionList,
    Condition,
    Loop,
    BreakLoop,
    ContinueLoop,
    EndRule
    ;
    public static PLRulePostActionType getById(long id)
    {
        for (PLRulePostActionType e : PLRulePostActionType.values())
        {
            if (id == e.ordinal()) return e;
        }
        throw new IllegalArgumentException("no");
    }
    public static PLRulePostActionType getByTitle(String title)
    {
        for (PLRulePostActionType e : PLRulePostActionType.values())
        {
            if (title.equalsIgnoreCase(e.name())) return e;
        }
        throw new IllegalArgumentException("no");
    }

}
