package com.alcity.dto.puzzle;

public class PLRulePostActionComparator implements java.util.Comparator<PLRulePostActionDTO>{
    @Override
    public int compare(PLRulePostActionDTO a, PLRulePostActionDTO b) {
        return a.getOrdering() - b.getOrdering();
    }
}
