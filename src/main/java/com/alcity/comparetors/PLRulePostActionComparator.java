package com.alcity.comparetors;

import com.alcity.dto.puzzle.PLRulePostActionDTO;

import java.util.Comparator;

public class PLRulePostActionComparator implements java.util.Comparator<PLRulePostActionDTO>{
    public int compare(PLRulePostActionDTO a, PLRulePostActionDTO b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
