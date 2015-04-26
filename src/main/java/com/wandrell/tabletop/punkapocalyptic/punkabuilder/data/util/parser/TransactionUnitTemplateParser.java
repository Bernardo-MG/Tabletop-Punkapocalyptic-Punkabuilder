package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.DefaultEditableAttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.stats.EditableAttributesHolder;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

public final class TransactionUnitTemplateParser implements
        Parser<Map<String, Object>, UnitTemplate> {

    private final SpecialRuleRepository rulesRepo;

    public TransactionUnitTemplateParser(final SpecialRuleRepository rulesRepo) {
        super();

        this.rulesRepo = rulesRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitTemplate parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<String> ruleNames;
        final EditableAttributesHolder attributes;
        UnitTemplate unit;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getByNamesList(ruleNames);

        attributes = new DefaultEditableAttributesHolder();

        attributes.setActions((Integer) input.get("actions"));
        attributes.setAgility((Integer) input.get("agility"));
        attributes.setCombat((Integer) input.get("combat"));
        attributes.setPrecision((Integer) input.get("precision"));
        attributes.setStrength((Integer) input.get("strength"));
        attributes.setTech((Integer) input.get("tech"));
        attributes.setToughness((Integer) input.get("toughness"));

        // TODO: Use a service
        unit = new DefaultUnitTemplate(input.get("name").toString(),
                attributes, (Integer) input.get("cost"), rules);

        return unit;
    }

}
