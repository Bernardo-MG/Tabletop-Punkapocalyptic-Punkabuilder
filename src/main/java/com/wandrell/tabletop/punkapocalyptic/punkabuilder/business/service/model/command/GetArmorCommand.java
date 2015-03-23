package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetArmorCommand implements ReturnCommand<Armor> {

    private final Integer                 armor;
    private final String                  name;
    private final Collection<SpecialRule> rules;

    public GetArmorCommand(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        super();

        this.name = name;
        this.armor = armor;
        this.rules = rules;
    }

    @Override
    public final Armor execute() {
        return ModelFactory.getInstance().getArmor(name, armor, rules);
    }

}
