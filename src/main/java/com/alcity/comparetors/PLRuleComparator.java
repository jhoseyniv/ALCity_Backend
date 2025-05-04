package com.alcity.comparetors;

import com.alcity.dto.puzzle.PLRuleDTO;
import com.alcity.dto.puzzle.PLRulePostActionDTO;

public class PLRuleComparator implements java.util.Comparator<PLRuleDTO>{
    @Override
    public int compare(PLRuleDTO a, PLRuleDTO b) {
        return a.getOrdering() - b.getOrdering();
    }
}
