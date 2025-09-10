package com.alcity.comparetors;

import com.alcity.dto.appmember.AppMemberJourneyDTO;
import com.alcity.entity.appmember.AppMemberStepInfo;

public class JourneyComparator implements java.util.Comparator<AppMemberJourneyDTO> {

    @Override
    public int compare(AppMemberJourneyDTO a, AppMemberJourneyDTO b) {
        if (a.getOrdering() < b.getOrdering()) return -1; // The first car has a smaller year
        if (a.getOrdering() > b.getOrdering()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }

}
