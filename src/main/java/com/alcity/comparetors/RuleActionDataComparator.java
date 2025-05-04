package com.alcity.comparetors;

import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class RuleActionDataComparator implements java.util.Comparator<RuleActionData>{
    @Override
    public int compare(RuleActionData a, RuleActionData b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
