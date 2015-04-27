package com.wandrell.tabletop.punkapocalyptic.punkabuilder.procedure;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.procedure.DefaultUnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitArmorAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitEquipmentAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitMutationAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Component("unitConfigManager")
public final class DesktopUnitConfigurationManager implements
        UnitConfigurationManager {

    private final UnitConfigurationManager baseManager;

    @Autowired
    public DesktopUnitConfigurationManager(
            @Qualifier("missingCompWeaponMessage") final String constraintMessage,
            final UnitArmorAvailabilityRepository armorAvaRepo,
            final UnitEquipmentAvailabilityRepository equipAvaRepo,
            final UnitMutationAvailabilityRepository mutationAvaRepo,
            final UnitWeaponAvailabilityRepository weaponAvaRepo,
            final RulesetService rulesetService) {
        super();

        baseManager = new DefaultUnitConfigurationManager(constraintMessage,
                armorAvaRepo, equipAvaRepo, mutationAvaRepo, weaponAvaRepo,
                rulesetService);
    }

    @Override
    public final Interval getAllowedWeaponsInterval() {
        return getUnitConfigurationManager().getAllowedWeaponsInterval();
    }

    @Override
    public final Collection<ArmorOption> getArmorOptions() {
        return getUnitConfigurationManager().getArmorOptions();
    }

    @Override
    public final Collection<Equipment> getEquipmentOptions() {
        return getUnitConfigurationManager().getEquipmentOptions();
    }

    @Override
    public final Integer getMaxMutations() {
        return getUnitConfigurationManager().getMaxMutations();
    }

    @Override
    public final Collection<Mutation> getMutations() {
        return getUnitConfigurationManager().getMutations();
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
    public final Collection<WeaponEnhancement> getWeaponEnhancements(
            final Weapon weapon) {
        return getUnitConfigurationManager().getWeaponEnhancements(weapon);
    }

    @Override
    public final Collection<Weapon> getWeaponOptions() {
        return getUnitConfigurationManager().getWeaponOptions();
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
