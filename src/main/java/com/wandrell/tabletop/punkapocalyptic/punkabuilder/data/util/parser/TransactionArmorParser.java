package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultArmor;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;

public final class TransactionArmorParser implements
        Parser<Map<String, Object>, Armor> {

    private final QueryableRepository<SpecialRule, Predicate<SpecialRule>> rulesRepo;

    public TransactionArmorParser(
            final QueryableRepository<SpecialRule, Predicate<SpecialRule>> rulesRepo) {
        super();

        this.rulesRepo = rulesRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Armor parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<String> ruleNames;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getCollection(new Predicate<SpecialRule>() {

            @Override
            public final boolean apply(final SpecialRule input) {
                return ruleNames.contains(input.getName());
            }

        });

        // TODO: Use a service
        return new DefaultArmor(input.get("name").toString(),
                (Integer) input.get("protection"), rules);
    }

}
