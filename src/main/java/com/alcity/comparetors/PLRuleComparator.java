package com.alcity.comparetors;

import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class PLRuleComparator implements java.util.Comparator<PLRuleDTO>{
    @Override
    public int compare(PLRuleDTO a, PLRuleDTO b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
