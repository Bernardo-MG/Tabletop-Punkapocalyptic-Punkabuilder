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
import com.wandrell.tabletop.punkapocalyptic.model.availability.JPAUnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitArmorAvailabilityRepository;

@Component("unitArmorRepo")
public final class DesktopUnitArmorAvailabilityRepository implements
        UnitArmorAvailabilityRepository,
        FilteredRepository<UnitArmorAvailability, QueryData> {

    private FilteredRepository<JPAUnitArmorAvailability, QueryData> repository;

    public DesktopUnitArmorAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final UnitArmorAvailability entity) {
        checkArgument(entity instanceof JPAUnitArmorAvailability);

        getBaseRepository().add((JPAUnitArmorAvailability) entity);
    }

    @Override
    public final Collection<UnitArmorAvailability> getAll() {
        return new LinkedList<UnitArmorAvailability>(getBaseRepository()
                .getAll());
    }

    @Override
    public final UnitArmorAvailability
            getAvailabilityForUnit(final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitArmorAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

    @Override
    public final Collection<UnitArmorAvailability> getCollection(
            final QueryData filter) {
        return new LinkedList<UnitArmorAvailability>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final UnitArmorAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final UnitArmorAvailability entity) {
        checkArgument(entity instanceof JPAUnitArmorAvailability);

        getBaseRepository().remove((JPAUnitArmorAvailability) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnitArmorAvailability>(entityManager,
                new DefaultQueryData(
                        "SELECT ava FROM UnitArmorAvailability ava"));
    }

    @Override
    public final void update(final UnitArmorAvailability entity) {
        checkArgument(entity instanceof JPAUnitArmorAvailability);

        getBaseRepository().update((JPAUnitArmorAvailability) entity);
    }

    private final FilteredRepository<JPAUnitArmorAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
