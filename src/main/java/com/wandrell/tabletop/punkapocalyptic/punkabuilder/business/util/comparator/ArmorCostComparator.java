package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;

public final class ArmorCostComparator implements Comparator<Armor> {

    public ArmorCostComparator() {
        super();
    }

    @Override
    public final int compare(final Armor armor1, final Armor armor2) {
        return armor1.getCost().compareTo(armor2.getCost());
    }

}
