package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;


public enum PLRulePostActionType  {
    Call_Object_Action,
    Variable_Assignment_Action,
    Fire_Event,
    User_Alert
}
