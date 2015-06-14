package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.JPAUnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitEquipmentAvailabilityRepository;

@Component("unitEquipmentRepo")
public final class DesktopUnitEquipmentAvailabilityRepository implements
        UnitEquipmentAvailabilityRepository,
        FilteredRepository<UnitEquipmentAvailability, QueryData> {

    private FilteredRepository<JPAUnitEquipmentAvailability, QueryData> repository;

    public DesktopUnitEquipmentAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final UnitEquipmentAvailability entity) {
        checkArgument(entity instanceof JPAUnitEquipmentAvailability);

        getBaseRepository().add((JPAUnitEquipmentAvailability) entity);
    }

    @Override
    public final Collection<UnitEquipmentAvailability> getAll() {
        return new LinkedList<UnitEquipmentAvailability>(getBaseRepository()
                .getAll());
    }

    @Override
    public final UnitEquipmentAvailability getAvailabilityForUnit(
            final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitEquipmentAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

    @Override
    public final Collection<UnitEquipmentAvailability> getCollection(
            final QueryData filter) {
        return new LinkedList<UnitEquipmentAvailability>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final UnitEquipmentAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final UnitEquipmentAvailability entity) {
        checkArgument(entity instanceof JPAUnitEquipmentAvailability);

        getBaseRepository().remove((JPAUnitEquipmentAvailability) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnitEquipmentAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM UnitEquipmentAvailability ava"));
    }

    @Override
    public final void update(final UnitEquipmentAvailability entity) {
        checkArgument(entity instanceof JPAUnitEquipmentAvailability);

        getBaseRepository().update((JPAUnitEquipmentAvailability) entity);
    }

    private final FilteredRepository<JPAUnitEquipmentAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
