package com.alcity.comparetors;

import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class PLRulePostActionComparator implements java.util.Comparator<PLRulePostActionDTO>{
    @Override
    public int compare(PLRulePostActionDTO a, PLRulePostActionDTO b) {
        return Integer.compare(a.getOrdering(), b.getOrdering());
    }
}
