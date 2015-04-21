package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.procedure.ConstraintData;
import com.wandrell.tabletop.procedure.DefaultConstraintData;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class TransactionFactionUnitsParser implements
        Parser<Map<String, Object>, FactionUnitAvailability> {

    private final QueryableRepository<Faction, Predicate<Faction>> factionsRepo;
    private final QueryableRepository<Unit, Predicate<Unit>>       unitsRepo;

    public TransactionFactionUnitsParser(
            final QueryableRepository<Unit, Predicate<Unit>> unitsRepo,
            final QueryableRepository<Faction, Predicate<Faction>> factionsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.factionsRepo = factionsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FactionUnitAvailability parse(final Map<String, Object> input) {
        final Unit unit;
        final Faction faction;
        final Collection<ConstraintData> constraints;
        final ModelFactory modelFactory;
        ConstraintData constrData;

        modelFactory = ModelFactory.getInstance();

        unit = unitsRepo.getCollection(new Predicate<Unit>() {

            @Override
            public final boolean apply(final Unit unit) {
                return unit.getName().equals(input.get("unit"));
            }

        }).iterator().next();

        faction = factionsRepo.getCollection(new Predicate<Faction>() {

            @Override
            public final boolean apply(final Faction faction) {
                return faction.getName().equals(input.get("faction"));
            }

        }).iterator().next();

        constraints = new LinkedList<>();
        for (final Map<String, Object> constraint : (Collection<Map<String, Object>>) input
                .get("constraints")) {
            if (constraint.containsKey("tags")) {
                constrData = new DefaultConstraintData(constraint.get("name")
                        .toString(),
                        (Collection<String>) constraint.get("tags"));
            } else {
                constrData = new DefaultConstraintData(constraint.get("name")
                        .toString());
            }

            constraints.add(constrData);
        }

        return modelFactory.getFactionUnitAvailability(faction, unit,
                constraints);
    }

}
