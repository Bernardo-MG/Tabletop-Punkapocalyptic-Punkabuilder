package com.wandrell.tabletop.punkapocalyptic.punkabuilder.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;

public final class ArmorOptionCostComparator implements Comparator<ArmorOption> {

    public ArmorOptionCostComparator() {
        super();
    }

    @Override
    public final int
            compare(final ArmorOption armor1, final ArmorOption armor2) {
        return armor1.getCost().compareTo(armor2.getCost());
    }

}
