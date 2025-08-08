package com.alcity.comparetors;

import com.alcity.dto.plexport.object.RuleData;

public class RuleDataComparator implements java.util.Comparator<RuleData>{
    @Override
    public int compare(RuleData a, RuleData b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
