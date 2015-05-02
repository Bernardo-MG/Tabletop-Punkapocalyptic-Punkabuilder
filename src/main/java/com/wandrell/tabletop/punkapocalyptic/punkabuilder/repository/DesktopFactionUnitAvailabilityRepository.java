package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionUnitAvailabilityRepository;

@Component("factionUnitRepo")
public final class DesktopFactionUnitAvailabilityRepository implements
        FactionUnitAvailabilityRepository {

    private final QueryableRepository<FactionUnitAvailability, Predicate<FactionUnitAvailability>> baseRepo;

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
                        return input.getFaction().getName().equals(faction);
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
    public final void remove(final FactionUnitAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(FactionUnitAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            QueryableRepository<FactionUnitAvailability, Predicate<FactionUnitAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
