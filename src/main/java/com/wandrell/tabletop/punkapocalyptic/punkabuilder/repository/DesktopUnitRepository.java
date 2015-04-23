package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitRepository;

@Component("unitRepo")
public final class DesktopUnitRepository implements UnitRepository {

    private final QueryableRepository<Unit, Predicate<Unit>> baseRepo;

    public DesktopUnitRepository() {
        super();

        baseRepo = new CollectionRepository<Unit>();
    }

    @Override
    public final void add(final Unit entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Unit> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public Unit getByName(String name) {
        return getBaseRepository().getEntity(new Predicate<Unit>() {

            @Override
            public final boolean apply(final Unit unit) {
                return unit.getName().equals(name);
            }

        });
    }

    @Override
    public final void remove(final Unit entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Unit entity) {
        getBaseRepository().update(entity);
    }

    private final QueryableRepository<Unit, Predicate<Unit>>
            getBaseRepository() {
        return baseRepo;
    }

}
