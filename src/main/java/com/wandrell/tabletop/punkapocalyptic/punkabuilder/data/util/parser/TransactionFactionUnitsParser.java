package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.procedure.ConstraintData;
import com.wandrell.tabletop.procedure.DefaultConstraintData;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

public final class TransactionFactionUnitsParser implements
        Parser<Map<String, Object>, FactionUnitAvailability> {

    private final FactionRepository      factionsRepo;
    private final UnitTemplateRepository unitsRepo;

    public TransactionFactionUnitsParser(
            final UnitTemplateRepository unitsRepo,
            final FactionRepository factionsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.factionsRepo = factionsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final FactionUnitAvailability parse(final Map<String, Object> input) {
        final UnitTemplate unit;
        final Faction faction;
        final Collection<ConstraintData> constraints;
        final ModelFactory modelFactory;
        ConstraintData constrData;

        modelFactory = ModelFactory.getInstance();

        unit = unitsRepo.getByNameToken(input.get("unit").toString());

        faction = factionsRepo.getByName(input.get("faction").toString());

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
