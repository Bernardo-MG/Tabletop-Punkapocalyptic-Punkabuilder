package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.FactionViewConfig;

@Component("factionViewRepo")
public final class DesktopFactionViewConfigRepository implements
        FactionViewConfigRepository {

    private final QueryableRepository<FactionViewConfig, Predicate<FactionViewConfig>> baseRepo;

    public DesktopFactionViewConfigRepository() {
        super();

        baseRepo = new CollectionRepository<FactionViewConfig>();
    }

    @Override
    public final void add(final FactionViewConfig entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<FactionViewConfig> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final FactionViewConfig getConfigForFaction(final String faction) {
        return getBaseRepository().getEntity(
                f -> f.getFaction().getName().equals(faction));
    }

    @Override
    public final void remove(final FactionViewConfig entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final FactionViewConfig entity) {
        getBaseRepository().update(entity);
    }

    private final
            QueryableRepository<FactionViewConfig, Predicate<FactionViewConfig>>
            getBaseRepository() {
        return baseRepo;
    }

}
