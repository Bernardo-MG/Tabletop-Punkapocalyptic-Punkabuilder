package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultArmor;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

public final class TransactionArmorParser implements
        Parser<Map<String, Object>, Armor> {

    private final SpecialRuleRepository rulesRepo;

    public TransactionArmorParser(final SpecialRuleRepository rulesRepo) {
        super();

        this.rulesRepo = rulesRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Armor parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<String> ruleNames;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getByNamesList(ruleNames);

        // TODO: Use a service
        return new DefaultArmor(input.get("name").toString(),
                (Integer) input.get("protection"), rules);
    }

}
