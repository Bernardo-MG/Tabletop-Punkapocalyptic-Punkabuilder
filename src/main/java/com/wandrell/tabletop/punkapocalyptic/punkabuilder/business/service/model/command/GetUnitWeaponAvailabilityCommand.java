package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetUnitWeaponAvailabilityCommand implements
        ReturnCommand<UnitWeaponAvailability> {

    private final Integer                  maxWeapons;
    private final Integer                  minWeapons;
    private final Unit                     unit;
    private final Collection<WeaponOption> weaponOptions;

    public GetUnitWeaponAvailabilityCommand(final Unit unit,
            final Collection<WeaponOption> weaponOptions,
            final Integer minWeapons, final Integer maxWeapons) {
        super();

        this.unit = unit;
        this.weaponOptions = weaponOptions;
        this.minWeapons = minWeapons;
        this.maxWeapons = maxWeapons;
    }

    @Override
    public final UnitWeaponAvailability execute() {
        return ModelFactory.getInstance().getUnitWeaponAvailability(unit,
                weaponOptions, minWeapons, maxWeapons);
    }

}
