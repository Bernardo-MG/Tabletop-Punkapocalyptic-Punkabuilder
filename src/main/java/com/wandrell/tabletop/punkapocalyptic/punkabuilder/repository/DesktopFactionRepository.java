package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.Collection;
import java.util.LinkedList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.pattern.repository.QueryData;
import com.wandrell.persistence.repository.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.faction.JPAFaction;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;

@Component("factionRepo")
public final class DesktopFactionRepository implements FactionRepository,
        FilteredRepository<Faction, QueryData> {

    private FilteredRepository<JPAFaction, QueryData> repository;

    public DesktopFactionRepository() {
        super();
    }

    @Override
    public final void add(final Faction entity) {
        checkArgument(entity instanceof JPAFaction);

        getBaseRepository().add((JPAFaction) entity);
    }

    @Override
    public final Collection<Faction> getAll() {
        return new LinkedList<Faction>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<Faction> getCollection(final QueryData filter) {
        return new LinkedList<Faction>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final Faction getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final Faction entity) {
        checkArgument(entity instanceof JPAFaction);

        getBaseRepository().remove((JPAFaction) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAFaction>(entityManager,
                new DefaultQueryData("SELECT faction FROM Faction faction"));
    }

    @Override
    public final void update(final Faction entity) {
        checkArgument(entity instanceof JPAFaction);

        getBaseRepository().update((JPAFaction) entity);
    }

    private final FilteredRepository<JPAFaction, QueryData> getBaseRepository() {
        return repository;
    }

}
