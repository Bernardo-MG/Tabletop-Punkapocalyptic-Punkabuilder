package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetMutationCommand implements ReturnCommand<Mutation> {

    private final Integer actions;
    private final Integer agility;
    private final Integer combat;
    private final Integer cost;
    private final String  name;
    private final Integer precision;
    private final Integer strength;
    private final Integer tech;
    private final Integer toughness;

    public GetMutationCommand(final String name, final Integer cost,
            final Integer actions, final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness) {
        super();

        this.name = name;
        this.cost = cost;

        this.actions = actions;
        this.agility = agility;
        this.combat = combat;
        this.precision = precision;
        this.strength = strength;
        this.tech = tech;
        this.toughness = toughness;
    }

    @Override
    public final Mutation execute() {
        return ModelFactory.getInstance().getMutation(name, cost, actions,
                agility, combat, precision, strength, tech, toughness);
    }

}
