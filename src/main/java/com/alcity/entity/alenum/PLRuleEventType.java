package com.alcity.entity.alenum;

import com.alcity.entity.base.BaseItemSet;
import com.alcity.entity.users.ApplicationMember;

import javax.persistence.Entity;
import java.io.Serializable;

public enum PLRuleEventType  {
    System_Event,
    Object_Action_Event,
    User_Event,
    Rule_Post_Action_Event
}
