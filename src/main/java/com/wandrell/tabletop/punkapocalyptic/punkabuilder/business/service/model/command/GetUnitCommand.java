package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.RulesetServiceAware;

public final class GetUnitCommand implements ResultCommand<Unit>,
        RulesetServiceAware {

    private final Integer                 actions;
    private final Integer                 agility;
    private final Integer                 combat;
    private final Integer                 cost;
    private final String                  name;
    private final Integer                 precision;
    private final Collection<SpecialRule> rules;
    private RulesetService                service;
    private final Integer                 strength;
    private final Integer                 tech;
    private final Integer                 toughness;
    private Unit                          unit;

    public GetUnitCommand(final String name, final Integer actions,
            final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness, final Integer cost,
            final Collection<SpecialRule> rules) {
        super();

        this.name = name;

        this.actions = actions;
        this.agility = agility;
        this.combat = combat;
        this.precision = precision;
        this.strength = strength;
        this.tech = tech;
        this.toughness = toughness;

        this.cost = cost;

        this.rules = rules;
    }

    @Override
    public final void execute() {
        unit = ModelFactory.getInstance().getUnit(name, actions, agility,
                combat, precision, strength, tech, toughness, cost, rules,
                getRulesetService());
    }

    @Override
    public final Unit getResult() {
        return unit;
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        this.service = service;
    }

    private final RulesetService getRulesetService() {
        return service;
    }

}
