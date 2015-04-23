package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitRepository;

public final class TransactionUnitMutationsParser implements
        Parser<Map<String, Object>, UnitMutationAvailability> {

    private final MutationRepository mutationsRepo;
    private final UnitRepository     unitsRepo;

    public TransactionUnitMutationsParser(final UnitRepository unitsRepo,
            final MutationRepository mutationsRepo) {
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

        unit = unitsRepo.getByName(input.get("unit").toString());

        mutationNames = (Collection<String>) input.get("white_list");
        mutations = mutationsRepo.getByNamesList(mutationNames);

        max = (Integer) input.get("max_mutations");

        // TODO: Use a service
        return new DefaultUnitMutationAvailability(unit, max, mutations);
    }

}
