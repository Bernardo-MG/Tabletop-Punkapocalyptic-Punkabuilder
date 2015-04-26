package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

@Component("unitRepo")
public final class DesktopUnitTemplateRepository implements
        UnitTemplateRepository {

    private final QueryableRepository<UnitTemplate, Predicate<UnitTemplate>> baseRepo;

    public DesktopUnitTemplateRepository() {
        super();

        baseRepo = new CollectionRepository<UnitTemplate>();
    }

    @Override
    public final void add(final UnitTemplate entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<UnitTemplate> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public UnitTemplate getByNameToken(String name) {
        return getBaseRepository().getEntity(new Predicate<UnitTemplate>() {

            @Override
            public final boolean apply(final UnitTemplate unit) {
                return unit.getNameToken().equals(name);
            }

        });
    }

    @Override
    public final void remove(final UnitTemplate entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final UnitTemplate entity) {
        getBaseRepository().update(entity);
    }

    private final QueryableRepository<UnitTemplate, Predicate<UnitTemplate>>
            getBaseRepository() {
        return baseRepo;
    }

}
