package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;

public final class TransactionUnitMutationsParser implements
        Parser<Map<String, Object>, UnitMutationAvailability> {

    private final QueryableRepository<Mutation, Predicate<Mutation>> mutationsRepo;
    private final QueryableRepository<Unit, Predicate<Unit>>         unitsRepo;

    public TransactionUnitMutationsParser(
            final QueryableRepository<Unit, Predicate<Unit>> unitsRepo,
            final QueryableRepository<Mutation, Predicate<Mutation>> mutationsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.mutationsRepo = mutationsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitMutationAvailability
            parse(final Map<String, Object> input) {
        final Unit unit;
        final Collection<Mutation> mutations;
        final Collection<String> mutationNames;
        Integer max;

        unit = unitsRepo.getCollection(new Predicate<Unit>() {

            @Override
            public final boolean apply(final Unit unit) {
                return unit.getName().equals(input.get("unit"));
            }

        }).iterator().next();

        mutationNames = (Collection<String>) input.get("white_list");
        mutations = mutationsRepo.getCollection(new Predicate<Mutation>() {

            @Override
            public final boolean apply(final Mutation mutation) {
                return mutationNames.contains(mutation.getName());
            }

        });

        max = (Integer) input.get("max_mutations");

        // TODO: Use a service
        return new DefaultUnitMutationAvailability(unit, max, mutations);
    }

}
