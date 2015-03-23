package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetMeleeWeaponCommand implements ReturnCommand<MeleeWeapon> {

    private final Integer                 combat;
    private final Integer                 cost;
    private final String                  name;
    private final Integer                 penetration;
    private final Collection<SpecialRule> rules;
    private final Integer                 strength;

    public GetMeleeWeaponCommand(final String name, final Integer cost,
            final Integer strength, final Integer penetration,
            final Integer combat, final Collection<SpecialRule> rules) {
        super();

        this.name = name;
        this.cost = cost;
        this.strength = strength;
        this.penetration = penetration;
        this.combat = combat;

        this.rules = rules;
    }

    @Override
    public final MeleeWeapon execute() throws Exception {
        return ModelFactory.getInstance().getMeleeWeapon(name, cost, strength,
                penetration, combat, rules);
    }

}
