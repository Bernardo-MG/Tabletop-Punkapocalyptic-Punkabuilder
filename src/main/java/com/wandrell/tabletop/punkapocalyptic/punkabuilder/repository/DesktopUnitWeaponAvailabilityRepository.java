package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;

@Component("unitWeaponRepo")
public final class DesktopUnitWeaponAvailabilityRepository implements
        UnitWeaponAvailabilityRepository {

    private final FilteredRepository<UnitWeaponAvailability, Predicate<UnitWeaponAvailability>> baseRepo;

    public DesktopUnitWeaponAvailabilityRepository() {
        super();

        baseRepo = new CollectionRepository<UnitWeaponAvailability>();
    }

    @Override
    public final void add(final UnitWeaponAvailability entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<UnitWeaponAvailability> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final UnitWeaponAvailability
            getAvailabilityForUnit(final String unit) {
        return getBaseRepository().getEntity(
                new Predicate<UnitWeaponAvailability>() {

                    @Override
                    public boolean apply(UnitWeaponAvailability input) {
                        return input.getUnit().getNameToken().equals(unit);
                    }

                });
    }

    @Override
    public final Collection<UnitWeapon> getAvailableWeaponsForUnit(
            final String unit) {
        final UnitWeaponAvailability ava;
        final Collection<UnitWeapon> weaponOptions;

        ava = getAvailabilityForUnit(unit);

        weaponOptions = new LinkedList<>();
        if (ava != null) {
            for (final WeaponOption option : ava.getWeaponOptions()) {
                weaponOptions.add(option.getWeapon());
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

        ava = getAvailabilityForUnit(unit);

        enhancements = new LinkedList<>();
        if (ava != null) {
            option = Collections2
                    .filter(ava.getWeaponOptions(),
                            new Predicate<WeaponOption>() {

                                @Override
                                public final boolean apply(
                                        final WeaponOption input) {
                                    return input.getWeapon().getTemplate()
                                            .getName().equals(weapon);
                                }

                            }).iterator().next();

            enhancements.addAll(option.getEnhancements());
        }

        return enhancements;
    }

    @Override
    public final void remove(final UnitWeaponAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final UnitWeaponAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            FilteredRepository<UnitWeaponAvailability, Predicate<UnitWeaponAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
