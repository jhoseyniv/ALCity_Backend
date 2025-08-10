package com.alcity.comparetors;

import com.alcity.dto.plimpexport.pexport.PostActionTreeExport;

public class RuleActionDataComparator implements java.util.Comparator<PostActionTreeExport>{
    @Override
    public int compare(PostActionTreeExport a, PostActionTreeExport b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
