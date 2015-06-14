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
import com.wandrell.tabletop.punkapocalyptic.model.availability.JPAUnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitMutationAvailabilityRepository;

@Component("unitMutationRepo")
public final class DesktopUnitMutationAvailabilityRepository implements
        UnitMutationAvailabilityRepository,
        FilteredRepository<UnitMutationAvailability, QueryData> {

    private FilteredRepository<JPAUnitMutationAvailability, QueryData> repository;

    public DesktopUnitMutationAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final UnitMutationAvailability entity) {
        checkArgument(entity instanceof JPAUnitMutationAvailability);

        getBaseRepository().add((JPAUnitMutationAvailability) entity);
    }

    @Override
    public final Collection<UnitMutationAvailability> getAll() {
        return new LinkedList<UnitMutationAvailability>(getBaseRepository()
                .getAll());
    }

    @Override
    public final UnitMutationAvailability getAvailabilityForUnit(String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitMutationAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

    @Override
    public final Collection<UnitMutationAvailability> getCollection(
            final QueryData filter) {
        return new LinkedList<UnitMutationAvailability>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final UnitMutationAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final UnitMutationAvailability entity) {
        checkArgument(entity instanceof JPAUnitMutationAvailability);

        getBaseRepository().remove((JPAUnitMutationAvailability) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAUnitMutationAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM UnitMutationAvailability ava"));
    }

    @Override
    public final void update(final UnitMutationAvailability entity) {
        checkArgument(entity instanceof JPAUnitMutationAvailability);

        getBaseRepository().update((JPAUnitMutationAvailability) entity);
    }

    private final FilteredRepository<JPAUnitMutationAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
