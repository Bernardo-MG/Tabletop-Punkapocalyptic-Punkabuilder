package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class UnitNameComparator implements Comparator<Unit> {

    public UnitNameComparator() {
        super();
    }

    @Override
    public final int compare(final Unit unit1, final Unit unit2) {
        return unit1.getName().compareTo(unit2.getName());
    }

}
