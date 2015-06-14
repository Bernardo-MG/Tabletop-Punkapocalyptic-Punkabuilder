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
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.JPAMutation;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;

@Component("mutationRepo")
public final class DesktopMutationRepository implements MutationRepository,
        FilteredRepository<Mutation, QueryData> {

    private FilteredRepository<JPAMutation, QueryData> repository;

    public DesktopMutationRepository() {
        super();
    }

    @Override
    public final void add(final Mutation entity) {
        checkArgument(entity instanceof JPAMutation);

        getBaseRepository().add((JPAMutation) entity);
    }

    @Override
    public final Collection<Mutation> getAll() {
        return new LinkedList<Mutation>(getBaseRepository().getAll());
    }

    @Override
    public final Collection<Mutation> getCollection(final QueryData filter) {
        return new LinkedList<Mutation>(getBaseRepository().getCollection(
                filter));
    }

    @Override
    public final Mutation getEntity(final QueryData filter) {
        return getBaseRepository().getEntity(filter);
    }

    @Override
    public final void remove(final Mutation entity) {
        checkArgument(entity instanceof JPAMutation);

        getBaseRepository().remove((JPAMutation) entity);
    }

    @PersistenceContext
    public final void setEntityManager(final EntityManager entityManager) {
        repository = new JPARepository<JPAMutation>(entityManager,
                new DefaultQueryData("SELECT mutation FROM Mutation mutation"));
    }

    @Override
    public final void update(final Mutation entity) {
        checkArgument(entity instanceof JPAMutation);

        getBaseRepository().update((JPAMutation) entity);
    }

    private final FilteredRepository<JPAMutation, QueryData>
            getBaseRepository() {
        return repository;
    }

}
