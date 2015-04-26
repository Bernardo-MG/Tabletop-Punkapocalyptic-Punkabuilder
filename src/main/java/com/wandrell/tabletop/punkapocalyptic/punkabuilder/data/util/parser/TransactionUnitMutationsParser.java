package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.repository.MutationRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

public final class TransactionUnitMutationsParser implements
        Parser<Map<String, Object>, UnitMutationAvailability> {

    private final MutationRepository     mutationsRepo;
    private final UnitTemplateRepository unitsRepo;

    public TransactionUnitMutationsParser(
            final UnitTemplateRepository unitsRepo,
            final MutationRepository mutationsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.mutationsRepo = mutationsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitMutationAvailability
            parse(final Map<String, Object> input) {
        final UnitTemplate unit;
        final Collection<Mutation> mutations;
        final Collection<String> mutationNames;
        Integer max;

        unit = unitsRepo.getByNameToken(input.get("unit").toString());

        mutationNames = (Collection<String>) input.get("white_list");
        mutations = mutationsRepo.getByNamesList(mutationNames);

        max = (Integer) input.get("max_mutations");

        // TODO: Use a service
        return new DefaultUnitMutationAvailability(unit, max, mutations);
    }

}
