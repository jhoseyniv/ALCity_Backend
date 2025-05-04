package com.alcity.comparetors;

import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class RuleDataComparator implements java.util.Comparator<RuleData>{
    @Override
    public int compare(RuleData a, RuleData b) {
        return a.getOrdering() - b.getOrdering();
    }
}
