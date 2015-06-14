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
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.FactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.JPAFactionViewConfig;

@Component("factionViewRepo")
public final class DesktopFactionViewConfigRepository implements
        FactionViewConfigRepository,
        FilteredRepository<FactionViewConfig, QueryData> {

    private FilteredRepository<JPAFactionViewConfig, QueryData> repository;

    public DesktopFactionViewConfigRepository() {
        super();
    }

    @Override
    public final void add(final FactionViewConfig entity) {
        checkArgument(entity instanceof JPAFactionViewConfig);

        getBaseRepository().add((JPAFactionViewConfig) entity);
    }

    @Override
    public final Collection<FactionViewConfig> getAll() {
        return new LinkedList<FactionViewConfig>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<FactionViewConfig> getCollection(
            final QueryData filter) {
        return new LinkedList<FactionViewConfig>(getBaseRepository()
                .getCollection(filter));
    }

    @Override
    public final FactionViewConfig getConfigForFaction(final String faction) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("faction", faction);

        return getEntity(new DefaultQueryData(
                "SELECT config FROM FactionViewConfig config WHERE config.faction.nameToken = :faction",
                params));
    }

    @Override
    public final FactionViewConfig getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final FactionViewConfig entity) {
        checkArgument(entity instanceof JPAFactionViewConfig);

        getBaseRepository().remove((JPAFactionViewConfig) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAFactionViewConfig>(entityManager,
                new DefaultQueryData(
                        "SELECT config FROM FactionViewConfig config"));
    }

    @Override
    public final void update(final FactionViewConfig entity) {
        checkArgument(entity instanceof JPAFactionViewConfig);

        getBaseRepository().update((JPAFactionViewConfig) entity);
    }

    private final FilteredRepository<JPAFactionViewConfig, QueryData>
            getBaseRepository() {
        return repository;
    }

}
