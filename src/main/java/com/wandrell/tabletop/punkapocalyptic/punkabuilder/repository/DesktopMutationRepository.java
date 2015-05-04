package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;

import org.springframework.stereotype.Component;

import com.google.common.base.Predicate;
import com.wandrell.pattern.repository.CollectionRepository;
import com.wandrell.pattern.repository.FilteredRepository;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;

@Component("mutationRepo")
public final class DesktopMutationRepository implements MutationRepository {

    private final FilteredRepository<Mutation, Predicate<Mutation>> baseRepo;

    public DesktopMutationRepository() {
        super();

        baseRepo = new CollectionRepository<Mutation>();
    }

    @Override
    public final void add(final Mutation entity) {
        getBaseRepository().add(entity);
    }

    @Override
    public final Collection<Mutation> getAll() {
        return getBaseRepository().getAll();
    }

    @Override
    public final Collection<Mutation> getByNamesList(
            final Collection<String> names) {
        return getBaseRepository().getCollection(new Predicate<Mutation>() {

            @Override
            public final boolean apply(final Mutation mutation) {
                return names.contains(mutation.getName());
            }

        });
    }

    @Override
    public final void remove(final Mutation entity) {
        getBaseRepository().remove(entity);
    }

    @Override
    public final void update(final Mutation entity) {
        getBaseRepository().update(entity);
    }

    private final FilteredRepository<Mutation, Predicate<Mutation>>
            getBaseRepository() {
        return baseRepo;
    }

}
