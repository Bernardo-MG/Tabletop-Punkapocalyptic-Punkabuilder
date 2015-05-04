package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionUnitAvailabilityRepository;

@Component("factionUnitRepo")
public final class DesktopFactionUnitAvailabilityRepository implements
        FactionUnitAvailabilityRepository {

    private final FilteredRepository<FactionUnitAvailability, Predicate<FactionUnitAvailability>> baseRepo;

    public DesktopFactionUnitAvailabilityRepository() {
        super();

        baseRepo = new CollectionRepository<FactionUnitAvailability>();
    }

    @Override
    public final void add(final FactionUnitAvailability entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<FactionUnitAvailability> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<FactionUnitAvailability>
            getAvailabilitiesForFaction(final String faction) {
        return getBaseRepository().getCollection(
                new Predicate<FactionUnitAvailability>() {

                    @Override
                    public boolean apply(FactionUnitAvailability input) {
                        return input.getFaction().getNameToken()
                                .equals(faction);
                    }

                });
    }

    @Override
    public final FactionUnitAvailability getAvailabilityForUnit(
            final String unit) {
        return getBaseRepository().getEntity(
                new Predicate<FactionUnitAvailability>() {

                    @Override
                    public boolean apply(FactionUnitAvailability input) {
                        return input.getUnit().getNameToken().equals(unit);
                    }

                });
    }

    @Override
    public final Collection<UnitTemplate> getUnitsForFaction(
            final String faction) {
        final Collection<UnitTemplate> result;
        final Collection<FactionUnitAvailability> avas;

        avas = getAvailabilitiesForFaction(faction);

        result = new LinkedList<>();
        for (final FactionUnitAvailability ava : avas) {
            result.add(ava.getUnit());
        }

        return result;
    }

    @Override
    public final void remove(final FactionUnitAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(FactionUnitAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            FilteredRepository<FactionUnitAvailability, Predicate<FactionUnitAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
