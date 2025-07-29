package com.alcity.comparetors;

import com.alcity.entity.appmember.AppMemberStepInfo;

public class JourneyStepComparator implements java.util.Comparator<AppMemberStepInfo> {
    @Override
    public int compare(AppMemberStepInfo a, AppMemberStepInfo b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }

}
