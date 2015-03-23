package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetFactionUnitAvailabilityCommand implements
        ReturnCommand<FactionUnitAvailability> {

    private final Collection<Constraint> constraints;
    private final Faction                faction;
    private final Unit                   unit;

    public GetFactionUnitAvailabilityCommand(final Faction faction,
            final Unit unit, final Collection<Constraint> constraints) {
        super();

        this.faction = faction;
        this.unit = unit;
        this.constraints = constraints;
    }

    @Override
    public final FactionUnitAvailability execute() {
        return ModelFactory.getInstance().getFactionUnitAvailability(faction,
                unit, constraints);
    }

}
