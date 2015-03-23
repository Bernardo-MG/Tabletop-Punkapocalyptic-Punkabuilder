package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetUnitMutationAvailabilityCommand implements
        ReturnCommand<UnitMutationAvailability> {

    private final Integer              max;
    private final Collection<Mutation> mutations;
    private final Unit                 unit;

    public GetUnitMutationAvailabilityCommand(final Unit unit,
            final Integer max, final Collection<Mutation> mutations) {
        super();

        this.unit = unit;
        this.max = max;
        this.mutations = mutations;
    }

    @Override
    public final UnitMutationAvailability execute() {
        return ModelFactory.getInstance().getUnitMutationAvailability(unit,
                max, mutations);
    }

}
