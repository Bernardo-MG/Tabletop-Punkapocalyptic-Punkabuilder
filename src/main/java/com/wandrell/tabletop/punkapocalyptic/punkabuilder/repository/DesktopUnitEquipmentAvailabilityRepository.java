package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitEquipmentAvailabilityRepository;

@Component("unitEquipmentRepo")
public final class DesktopUnitEquipmentAvailabilityRepository implements
        UnitEquipmentAvailabilityRepository {

    private final QueryableRepository<UnitEquipmentAvailability, Predicate<UnitEquipmentAvailability>> baseRepo;

    public DesktopUnitEquipmentAvailabilityRepository() {
        super();

        baseRepo = new CollectionRepository<UnitEquipmentAvailability>();
    }

    @Override
    public final void add(final UnitEquipmentAvailability entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<UnitEquipmentAvailability> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final UnitEquipmentAvailability getAvailabilityForUnit(
            final String unit) {
        return getBaseRepository().getEntity(
                new Predicate<UnitEquipmentAvailability>() {

                    @Override
                    public boolean apply(UnitEquipmentAvailability input) {
                        return input.getUnit().getNameToken().equals(unit);
                    }

                });
    }

    @Override
    public final void remove(final UnitEquipmentAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final UnitEquipmentAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            QueryableRepository<UnitEquipmentAvailability, Predicate<UnitEquipmentAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
