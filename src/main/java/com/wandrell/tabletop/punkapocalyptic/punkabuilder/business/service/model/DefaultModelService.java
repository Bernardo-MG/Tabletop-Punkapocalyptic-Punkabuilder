package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

public final class DefaultModelService implements ModelService {

    private final ModelFactory        factory = ModelFactory.getInstance();
    private final LocalizationService localizationService;
    private final RulesetService      rulesetService;

    public DefaultModelService(final RulesetService rulesetService,
            final LocalizationService localizationService) {
        super();

        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");
        checkNotNull(localizationService,
                "Received a null pointer as localization service");

        this.rulesetService = rulesetService;
        this.localizationService = localizationService;
    }

    @Override
    public final Armor getArmor(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        return getFactory().getArmor(name, armor, rules);
    }

    @Override
    public final Equipment getEquipment(final String name, final Integer cost) {
        return getFactory().getEquipment(name, cost);
    }

    @Override
    public final Faction getFaction(final String name) {
        return getFactory().getFaction(name);
    }

    @Override
    public final FactionUnitAvailability getFactionUnitAvailability(
            final Faction faction, final Unit unit,
            final Collection<Constraint> constraints) {
        return getFactory().getFactionUnitAvailability(faction, unit,
                constraints);
    }

    @Override
    public final Gang getGang(final Faction faction) {
        return ModelFactory.getInstance().getGang(faction, getRulesetService());
    }

    @Override
    public final MeleeWeapon getMeleeWeapon(final String name,
            final Integer cost, final Integer strength,
            final Integer penetration, final Integer combat,
            final Collection<SpecialRule> rules) {
        return getFactory().getMeleeWeapon(name, cost, strength, penetration,
                combat, rules);
    }

    @Override
    public final Mutation getMutation(final String name, final Integer cost,
            final Integer actions, final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness) {
        return getFactory().getMutation(name, cost, actions, agility, combat,
                precision, strength, tech, toughness);
    }

    @Override
    public final RangedValue getRangedValue(final Integer distanceShort,
            final Integer distanceMedium, final Integer distanceLong) {
        return getFactory().getRangedValue(distanceShort, distanceMedium,
                distanceLong);
    }

    @Override
    public final RangedWeapon getRangedWeapon(final String name,
            final Integer cost, final Collection<SpecialRule> rules,
            final RangedValue penetration, final RangedValue strength,
            final RangedValue distanceCM, final RangedValue distanceInches,
            final MeleeWeapon weaponMelee) {
        return getFactory().getRangedWeapon(name, cost, rules, penetration,
                strength, distanceCM, distanceInches, weaponMelee);
    }

    @Override
    public final SpecialRule getSpecialRule(final String name) {
        return getFactory().getSpecialRule(name, getRulesetService());
    }

    @Override
    public final Unit getUnit(final String name, final Integer actions,
            final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness, final Integer cost,
            final Collection<SpecialRule> rules) {
        return getFactory().getUnit(name, actions, agility, combat, precision,
                strength, tech, toughness, cost, rules, getRulesetService());
    }

    @Override
    public final UnitArmorAvailability getUnitArmorAvailability(
            final Unit unit, final Collection<Armor> armorOptions,
            final Armor initialArmor) {
        return getFactory().getUnitArmorAvailability(unit, armorOptions,
                initialArmor);
    }

    @Override
    public final UnitEquipmentAvailability getUnitEquipmentAvailability(
            final Unit unit, final Collection<Equipment> equipment) {
        return new DefaultUnitEquipmentAvailability(unit, equipment);
    }

    @Override
    public final Constraint getUnitGangConstraint(final String name,
            final String unit, final String... context) {
        return getFactory().getConstraint(name, unit, context,
                getLocalizationService());
    }

    @Override
    public final UnitMutationAvailability getUnitMutationAvailability(
            final Unit unit, final Integer max,
            final Collection<Mutation> mutations) {
        return getFactory().getUnitMutationAvailability(unit, max, mutations);
    }

    @Override
    public final UnitWeaponAvailability getUnitWeaponAvailability(
            final Unit unit, final Collection<WeaponOption> weaponOptions,
            final Integer minWeapons, final Integer maxWeapons) {
        return getFactory().getUnitWeaponAvailability(unit, weaponOptions,
                minWeapons, maxWeapons);
    }

    @Override
    public final WeaponEnhancement getWeaponEnhancement(final String name,
            final Integer cost) {
        return getFactory().getWeaponEnhancement(name, cost);
    }

    @Override
    public final WeaponOption getWeaponOption(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        return getFactory().getWeaponOption(weapon, enhancements);
    }

    private final ModelFactory getFactory() {
        return factory;
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final RulesetService getRulesetService() {
        return rulesetService;
    }

}
