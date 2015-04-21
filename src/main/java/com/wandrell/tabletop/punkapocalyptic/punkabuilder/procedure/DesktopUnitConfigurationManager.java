package com.wandrell.tabletop.punkapocalyptic.punkabuilder.procedure;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.procedure.DefaultUnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Component("unitConfigManager")
public final class DesktopUnitConfigurationManager implements
        UnitConfigurationManager {

    private final UnitConfigurationManager baseManager;

    @Autowired
    public DesktopUnitConfigurationManager(
            @Qualifier("missingCompWeaponMessage") final String constraintMessage,
            @Qualifier("unitArmorRepo") final QueryableRepository<UnitArmorAvailability, Predicate<UnitArmorAvailability>> armorAvaRepo,
            @Qualifier("unitEquipmentRepo") final QueryableRepository<UnitEquipmentAvailability, Predicate<UnitEquipmentAvailability>> equipAvaRepo,
            @Qualifier("unitMutationRepo") final QueryableRepository<UnitMutationAvailability, Predicate<UnitMutationAvailability>> mutationAvaRepo,
            @Qualifier("unitWeaponRepo") final QueryableRepository<UnitWeaponAvailability, Predicate<UnitWeaponAvailability>> weaponAvaRepo,
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
    public final Collection<Armor> getArmorOptions() {
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
