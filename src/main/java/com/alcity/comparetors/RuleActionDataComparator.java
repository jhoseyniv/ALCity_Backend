package com.alcity.comparetors;

import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class RuleActionDataComparator implements java.util.Comparator<RuleActionData>{
    @Override
    public int compare(RuleActionData a, RuleActionData b) {
        return a.getOrdering() - b.getOrdering();
    }
}
