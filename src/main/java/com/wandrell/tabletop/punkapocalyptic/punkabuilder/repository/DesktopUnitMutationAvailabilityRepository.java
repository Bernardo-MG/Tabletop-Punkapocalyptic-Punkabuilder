package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitMutationAvailabilityRepository;

@Component("unitMutationRepo")
public final class DesktopUnitMutationAvailabilityRepository implements
        UnitMutationAvailabilityRepository {

    private final FilteredRepository<UnitMutationAvailability, Predicate<UnitMutationAvailability>> baseRepo;

    public DesktopUnitMutationAvailabilityRepository() {
        super();

        baseRepo = new CollectionRepository<UnitMutationAvailability>();
    }

    @Override
    public final void add(final UnitMutationAvailability entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<UnitMutationAvailability> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final UnitMutationAvailability getAvailabilityForUnit(String unit) {
        return getBaseRepository().getEntity(
                new Predicate<UnitMutationAvailability>() {

                    @Override
                    public boolean apply(UnitMutationAvailability input) {
                        return input.getUnit().getNameToken().equals(unit);
                    }

                });
    }

    @Override
    public final void remove(final UnitMutationAvailability entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final UnitMutationAvailability entity) {
        getBaseRepository().update(entity);
    }

    private final
            FilteredRepository<UnitMutationAvailability, Predicate<UnitMutationAvailability>>
            getBaseRepository() {
        return baseRepo;
    }

}
