package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultUnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;
import com.wandrell.util.persistence.JPARepository;

@Component("unitWeaponRepo")
public final class DesktopUnitWeaponAvailabilityRepository extends
        JPARepository<UnitWeaponAvailability> implements
        UnitWeaponAvailabilityRepository {

    public DesktopUnitWeaponAvailabilityRepository() {
        super(
                new DefaultQueryData(
                        "SELECT ava FROM UnitWeaponAvailability ava"));
    }

    @Override
    public final UnitWeaponAvailability
            getAvailabilityForUnit(final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitWeaponAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

    @Override
    public final Collection<UnitWeapon> getAvailableWeaponsForUnit(
            final String unit) {
        final UnitWeaponAvailability ava;
        final Collection<UnitWeapon> weaponOptions;

        // TODO: Use a query
        ava = getAvailabilityForUnit(unit);

        weaponOptions = new LinkedList<>();
        if (ava != null) {
            for (final WeaponOption option : ava.getWeaponOptions()) {
                weaponOptions.add(new DefaultUnitWeapon(option.getWeapon()));
            }
        }

        return weaponOptions;
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancementsForUnitAndWeapon(
            final String unit, final String weapon) {
        final UnitWeaponAvailability ava;
        final Collection<WeaponEnhancement> enhancements;
        final WeaponOption option;

        // TODO: Use a query
        ava = getAvailabilityForUnit(unit);

        enhancements = new LinkedList<>();
        if (ava != null) {
            option = Collections2
                    .filter(ava.getWeaponOptions(),
                            new Predicate<WeaponOption>() {

                                @Override
                                public final boolean apply(
                                        final WeaponOption input) {
                                    return input.getWeapon().getNameToken()
                                            .equals(weapon);
                                }

                            }).iterator().next();

            enhancements.addAll(option.getEnhancements());
        }

        return enhancements;
    }

}
