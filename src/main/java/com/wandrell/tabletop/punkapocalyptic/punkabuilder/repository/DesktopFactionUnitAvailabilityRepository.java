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
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.JPAFactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionUnitAvailabilityRepository;

@Component("factionUnitRepo")
public final class DesktopFactionUnitAvailabilityRepository implements
        FactionUnitAvailabilityRepository,
        FilteredRepository<FactionUnitAvailability, QueryData> {

    private FilteredRepository<JPAFactionUnitAvailability, QueryData> repository;

    public DesktopFactionUnitAvailabilityRepository() {
        super();
    }

    @Override
    public final void add(final FactionUnitAvailability entity) {
        checkArgument(entity instanceof JPAFactionUnitAvailability);

        getBaseRepository().add((JPAFactionUnitAvailability) entity);
    }

    @Override
    public final Collection<FactionUnitAvailability> getAll() {
        return new LinkedList<FactionUnitAvailability>(getBaseRepository()
                .getAll());
    }

    @Override
    public final Collection<FactionUnitAvailability>
            getAvailabilitiesForFaction(final String faction) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("faction", faction);

        return getCollection(new DefaultQueryData(
                "SELECT ava FROM FactionUnitAvailability ava WHERE ava.faction.nameToken = :faction",
                params));
    }

    @Override
    public final FactionUnitAvailability getAvailabilityForUnit(
            final String faction, final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("faction", faction);
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM FactionUnitAvailability ava WHERE ava.faction.nameToken = :faction AND ava.availUnit.name = :unit",
                params));
    }

    @Override
    public final Collection<FactionUnitAvailability> getCollection(
            final QueryData filter) {
        return new LinkedList<FactionUnitAvailability>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final JPAFactionUnitAvailability getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final Collection<UnitTemplate> getUnitsForFaction(
            final String faction) {
        final Collection<UnitTemplate> result;
        final Collection<FactionUnitAvailability> avas;

        // TODO: This maybe can be done somehow else

        avas = getAvailabilitiesForFaction(faction);

        result = new LinkedList<>();
        for (final FactionUnitAvailability ava : avas) {
            result.add(ava.getUnit());
        }

        return result;
    }

    @Override
    public final void remove(final FactionUnitAvailability entity) {
        checkArgument(entity instanceof JPAFactionUnitAvailability);

        getBaseRepository().remove((JPAFactionUnitAvailability) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAFactionUnitAvailability>(
                entityManager, new DefaultQueryData(
                        "SELECT ava FROM FactionUnitAvailability ava"));
    }

    @Override
    public final void update(final FactionUnitAvailability entity) {
        checkArgument(entity instanceof JPAFactionUnitAvailability);

        getBaseRepository().update((JPAFactionUnitAvailability) entity);
    }

    private final FilteredRepository<JPAFactionUnitAvailability, QueryData>
            getBaseRepository() {
        return repository;
    }

}
