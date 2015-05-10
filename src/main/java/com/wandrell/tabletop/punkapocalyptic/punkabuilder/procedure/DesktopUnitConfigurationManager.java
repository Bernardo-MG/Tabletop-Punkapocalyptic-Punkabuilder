package com.wandrell.tabletop.punkapocalyptic.punkabuilder.procedure;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.procedure.DefaultUnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationOptions;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitArmorAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitEquipmentAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitMutationAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;

@Component("unitConfigManager")
public final class DesktopUnitConfigurationManager implements
        UnitConfigurationManager {

    private final UnitConfigurationManager baseManager;

    @Autowired
    public DesktopUnitConfigurationManager(
            @Qualifier("missingCompWeaponMessage") final String constraintMessage,
            final MutationRepository mutationRepo,
            final UnitArmorAvailabilityRepository armorAvaRepo,
            final UnitEquipmentAvailabilityRepository equipAvaRepo,
            final UnitMutationAvailabilityRepository mutationAvaRepo,
            final UnitWeaponAvailabilityRepository weaponAvaRepo) {
        super();

        baseManager = new DefaultUnitConfigurationManager(constraintMessage,
                mutationRepo, armorAvaRepo, equipAvaRepo, mutationAvaRepo,
                weaponAvaRepo);
    }

    @Override
    public final UnitConfigurationOptions getOptions() {
        return getUnitConfigurationManager().getOptions();
    }

    @Override
    public final Unit getUnit() {
        return getUnitConfigurationManager().getUnit();
    }

    @Override
    public final Collection<String> getValidationMessages() {
        return getUnitConfigurationManager().getValidationMessages();
    }

    @Override
    public final Boolean isGrouped() {
        return getUnitConfigurationManager().isGrouped();
    }

    @Override
    public final void setUnit(final Unit unit) {
        getUnitConfigurationManager().setUnit(unit);
    }

    @Override
    public final Boolean validate() {
        return getUnitConfigurationManager().validate();
    }

    private final UnitConfigurationManager getUnitConfigurationManager() {
        return baseManager;
    }

}
