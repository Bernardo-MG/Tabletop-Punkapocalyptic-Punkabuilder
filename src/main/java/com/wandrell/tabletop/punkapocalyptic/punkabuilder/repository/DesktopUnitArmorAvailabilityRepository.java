package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitArmorAvailabilityRepository;

@Component("unitArmorRepo")
public final class DesktopUnitArmorAvailabilityRepository implements
        UnitArmorAvailabilityRepository {

    private final QueryableRepository<UnitArmorAvailability, Predicate<UnitArmorAvailability>> baseRepo;

    public DesktopUnitArmorAvailabilityRepository() {
        super();

        baseRepo = new CollectionRepository<UnitArmorAvailability>();
    }

    @Override
    public final void add(final UnitArmorAvailability entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<UnitArmorAvailability> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final UnitArmorAvailability getAvailabilityForUnit(final Unit unit) {
        return getBaseRepository().getEntity(
                new Predicate<UnitArmorAvailability>() {

                    @Override
                    public boolean apply(UnitArmorAvailability input) {
                        return input.getUnit().getName().equals(unit.getName());
                    }

                });
    }

    @Override
    public final void remove(final UnitArmorAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final UnitArmorAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            QueryableRepository<UnitArmorAvailability, Predicate<UnitArmorAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
