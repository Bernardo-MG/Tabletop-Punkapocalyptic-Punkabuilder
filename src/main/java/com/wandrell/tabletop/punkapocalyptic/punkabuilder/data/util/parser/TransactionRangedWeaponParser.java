package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultRangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.util.DefaultRangedValue;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;
import com.wandrell.tabletop.punkapocalyptic.repository.SpecialRuleRepository;

public final class TransactionRangedWeaponParser implements
        Parser<Map<String, Object>, Weapon> {

    private final SpecialRuleRepository rulesRepo;
    private final MeleeWeapon           weaponMelee;

    public TransactionRangedWeaponParser(final SpecialRuleRepository rulesRepo,
            final MeleeWeapon weaponMelee) {
        super();

        this.rulesRepo = rulesRepo;

        this.weaponMelee = weaponMelee;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final Weapon parse(final Map<String, Object> input) {
        final Collection<SpecialRule> rules;
        final Collection<String> ruleNames;
        final RangedValue penetration;
        final RangedValue strength;
        final RangedValue distanceCM;
        final RangedValue distanceInches;
        final Weapon weapon;

        ruleNames = (Collection<String>) input.get("rules");

        rules = rulesRepo.getByNamesList(ruleNames);

        penetration = new DefaultRangedValue(
                (Integer) input.get("penetration_short"),
                (Integer) input.get("penetration_medium"),
                (Integer) input.get("penetration_long"));

        strength = new DefaultRangedValue(
                (Integer) input.get("strength_short"),
                (Integer) input.get("strength_medium"),
                (Integer) input.get("strength_long"));

        distanceCM = new DefaultRangedValue(
                (Integer) input.get("distance_short_cm"),
                (Integer) input.get("distance_medium_cm"),
                (Integer) input.get("distance_long_cm"));

        distanceInches = new DefaultRangedValue(
                (Integer) input.get("distance_short_inches"),
                (Integer) input.get("distance_medium_inches"),
                (Integer) input.get("distance_long_inches"));

        // TODO: Use a service
        weapon = new DefaultRangedWeapon(input.get("name").toString(),
                (Integer) input.get("cost"), rules, penetration, strength,
                distanceCM, distanceInches, weaponMelee);

        if (ruleNames.contains("firearm")) {
            ((RangedWeapon) weapon).setFirearm(true);
        }

        return weapon;
    }

}
