package com.alcity.comparetors;

import com.alcity.dto.puzzle.PLDTO;

public class PLComparatorByFromAge implements java.util.Comparator<PLDTO>{
    @Override
    public int compare(PLDTO a, PLDTO b) {
        if (a.getFromAge() < b.getFromAge()) return -1; // The first car has a smaller year
        if (a.getFromAge() > b.getFromAge()) return 1;  // The first car has a larger year
        return 0; // Both cars have the same year
    }
}
