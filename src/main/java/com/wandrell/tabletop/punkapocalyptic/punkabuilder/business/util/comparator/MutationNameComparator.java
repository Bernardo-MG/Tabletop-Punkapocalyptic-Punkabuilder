package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator;

import java.util.Comparator;

import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;

public final class MutationNameComparator implements Comparator<Mutation> {

    public MutationNameComparator() {
        super();
    }

    @Override
    public final int compare(final Mutation unit1, final Mutation unit2) {
        return unit1.getName().compareTo(unit2.getName());
    }

}
