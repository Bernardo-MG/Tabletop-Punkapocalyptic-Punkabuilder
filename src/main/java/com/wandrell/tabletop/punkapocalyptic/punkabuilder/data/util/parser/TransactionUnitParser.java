package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.GroupedUnitWrapper;
import com.wandrell.tabletop.punkapocalyptic.model.unit.RulesetServiceDerivedValuesBuilder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.valuebox.DefaultEditableValueBox;

public final class TransactionUnitParser implements
        Parser<Map<String, Object>, Unit> {

    private final Repository<SpecialRule> rulesRepo;
    private final RulesetService          service;

    public TransactionUnitParser(final Repository<SpecialRule> rulesRepo,
            final RulesetService service) {
        super();

        this.rulesRepo = rulesRepo;
        this.service = service;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Unit parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<SpecialRule> filtered;
        final Collection<String> ruleNames;
        Unit unit;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getCollection(new Predicate<SpecialRule>() {

            @Override
            public final boolean apply(final SpecialRule input) {
                return ruleNames.contains(input.getName());
            }

        });

        // TODO: Use a service
        unit = new DefaultUnit(input.get("name").toString(),
                (Integer) input.get("actions"), (Integer) input.get("agility"),
                (Integer) input.get("combat"),
                (Integer) input.get("precision"),
                (Integer) input.get("strength"), (Integer) input.get("tech"),
                (Integer) input.get("toughness"), (Integer) input.get("cost"),
                rules, new RulesetServiceDerivedValuesBuilder(service));

        filtered = Collections2.filter(unit.getSpecialRules(),
                new Predicate<SpecialRule>() {

                    @Override
                    public final boolean apply(final SpecialRule input) {
                        return input.getName().equals("pack");
                    }

                });

        if (!filtered.isEmpty()) {
            unit = new GroupedUnitWrapper(unit, new DefaultEditableValueBox(0));
        }
        return unit;
    }

}
