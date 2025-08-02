package com.alcity.comparetors;

import com.alcity.dto.Interpreter.object.PostActionTreeExport;
import com.alcity.dto.Interpreter.object.RuleActionData;
import com.alcity.dto.Interpreter.object.RuleData;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class RuleActionDataComparator implements java.util.Comparator<PostActionTreeExport>{
    @Override
    public int compare(PostActionTreeExport a, PostActionTreeExport b) {
        if (a.getPostAction().getOrdering() < b.getPostAction().getOrdering()) return -1; // The first car has a smaller year
        if (a.getPostAction().getOrdering() > b.getPostAction().getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
