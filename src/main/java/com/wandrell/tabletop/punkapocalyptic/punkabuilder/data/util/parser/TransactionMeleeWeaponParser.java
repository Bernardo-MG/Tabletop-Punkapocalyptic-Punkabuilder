package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultMeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

public final class TransactionMeleeWeaponParser implements
        Parser<Map<String, Object>, Weapon> {

    private final SpecialRuleRepository rulesRepo;

    public TransactionMeleeWeaponParser(final SpecialRuleRepository rulesRepo) {
        super();

        this.rulesRepo = rulesRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Weapon parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<String> ruleNames;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getByNamesList(ruleNames);

        // TODO: Use a service
        return new DefaultMeleeWeapon(input.get("name").toString(),
                (Integer) input.get("cost"), (Integer) input.get("strength"),
                (Integer) input.get("penetration"),
                (Integer) input.get("combat"), rules);
    }

}
