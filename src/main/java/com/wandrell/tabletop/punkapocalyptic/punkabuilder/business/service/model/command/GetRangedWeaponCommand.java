package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetRangedWeaponCommand implements
        ReturnCommand<RangedWeapon> {

    private final Integer                 cost;
    private final RangedValue             distanceCM;
    private final RangedValue             distanceInches;
    private final String                  name;
    private final RangedValue             penetration;
    private final Collection<SpecialRule> rules;
    private final RangedValue             strength;
    private final MeleeWeapon             weaponMelee;

    public GetRangedWeaponCommand(final String name, final Integer cost,
            final Collection<SpecialRule> rules, final RangedValue penetration,
            final RangedValue strength, final RangedValue distanceCM,
            final RangedValue distanceInches, final MeleeWeapon weaponMelee) {
        super();

        this.name = name;
        this.cost = cost;

        this.penetration = penetration;
        this.strength = strength;
        this.distanceCM = distanceCM;
        this.distanceInches = distanceInches;
        this.weaponMelee = weaponMelee;
        this.rules = rules;
    }

    @Override
    public final RangedWeapon execute() {
        return ModelFactory.getInstance().getRangedWeapon(name, cost, rules,
                penetration, strength, distanceCM, distanceInches, weaponMelee);
    }

}
