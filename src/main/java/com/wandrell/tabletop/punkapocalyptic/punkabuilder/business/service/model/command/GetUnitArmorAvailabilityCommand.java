package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetUnitArmorAvailabilityCommand implements
        ReturnCommand<UnitArmorAvailability> {

    private final Collection<Armor> armorOptions;
    private final Armor             initialArmor;
    private final Unit              unit;

    public GetUnitArmorAvailabilityCommand(final Unit unit,
            final Collection<Armor> armorOptions, final Armor initialArmor) {
        super();

        this.unit = unit;
        this.armorOptions = armorOptions;
        this.initialArmor = initialArmor;
    }

    @Override
    public final UnitArmorAvailability execute() {
        return ModelFactory.getInstance().getUnitArmorAvailability(unit,
                armorOptions, initialArmor);
    }

}
