package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;

@Component("factionRepo")
public final class DesktopFactionRepository implements FactionRepository {

    private final FilteredRepository<Faction, Predicate<Faction>> baseRepo;

    public DesktopFactionRepository() {
        super();

        baseRepo = new CollectionRepository<Faction>();
    }

    @Override
    public final void add(final Faction entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Faction> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Faction getByName(final String faction) {
        return getBaseRepository().getEntity(
                f -> f.getNameToken().equals(faction));
    }

    @Override
    public final void remove(final Faction entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Faction entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<Faction, Predicate<Faction>>
            getBaseRepository() {
        return baseRepo;
    }

}
