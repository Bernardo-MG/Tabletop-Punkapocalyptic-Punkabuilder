package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitWeaponAvailabilityRepository;

@Component("unitWeaponRepo")
public final class DesktopUnitWeaponAvailabilityRepository implements
        UnitWeaponAvailabilityRepository {

    private final QueryableRepository<UnitWeaponAvailability, Predicate<UnitWeaponAvailability>> baseRepo;

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
    public final UnitWeaponAvailability getAvailabilityForUnit(final Unit unit) {
        return getBaseRepository().getEntity(
                new Predicate<UnitWeaponAvailability>() {

                    @Override
                    public boolean apply(UnitWeaponAvailability input) {
                        return input.getUnit().getName().equals(unit.getName());
                    }

                });
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
            QueryableRepository<UnitWeaponAvailability, Predicate<UnitWeaponAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
