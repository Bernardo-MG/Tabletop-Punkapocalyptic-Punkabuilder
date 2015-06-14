package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.JPAUnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultUnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;

@Component("unitWeaponRepo")
public final class DesktopUnitWeaponAvailabilityRepository implements
        UnitWeaponAvailabilityRepository,
        FilteredRepository<UnitWeaponAvailability, QueryData> {

    private FilteredRepository<JPAUnitWeaponAvailability, QueryData> repository;

    public DesktopUnitWeaponAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final UnitWeaponAvailability entity) {
        checkArgument(entity instanceof JPAUnitWeaponAvailability);

        getBaseRepository().add((JPAUnitWeaponAvailability) entity);
    }

    @Override
    public final Collection<UnitWeaponAvailability> getAll() {
        return new LinkedList<UnitWeaponAvailability>(getBaseRepository()
                .getAll());
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
    public final Collection<UnitWeaponAvailability> getCollection(
            final QueryData filter) {
        return new LinkedList<UnitWeaponAvailability>(getBaseRepository()
                .getCollection(filter));
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

    @Override
    public final UnitWeaponAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final UnitWeaponAvailability entity) {
        checkArgument(entity instanceof JPAUnitWeaponAvailability);

        getBaseRepository().remove((JPAUnitWeaponAvailability) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnitWeaponAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM UnitWeaponAvailability ava"));
    }

    @Override
    public final void update(final UnitWeaponAvailability entity) {
        checkArgument(entity instanceof JPAUnitWeaponAvailability);

        getBaseRepository().update((JPAUnitWeaponAvailability) entity);
    }

    private final FilteredRepository<JPAUnitWeaponAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
