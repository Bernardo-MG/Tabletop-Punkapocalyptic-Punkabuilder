package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.procedure.ConstraintData;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Service("modelService")
public final class DefaultModelService implements ModelService {

    private final ModelFactory        factory = ModelFactory.getInstance();
    private final LocalizationService localizationService;
    private final RulesetService      rulesetService;

    @Autowired
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
            final Faction faction, final UnitTemplate unit,
            final Collection<ConstraintData> constraints) {
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
            final Boolean twoHanded, final Collection<SpecialRule> rules) {
        return getFactory().getMeleeWeapon(name, cost, strength, penetration,
                combat, twoHanded, rules);
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
    public final RangedWeapon getRangedWeapon(final Unit unit,
            final String name, final Integer cost, final Boolean twoHanded,
            final Collection<SpecialRule> rules, final RangedValue penetration,
            final RangedValue strength, final RangedValue distanceCM,
            final RangedValue distanceInches, final Boolean firearm) {
        return getFactory().getRangedWeapon(unit, name, cost, twoHanded, rules,
                penetration, strength, distanceCM, distanceInches, firearm);
    }

    @Override
    public final SpecialRule getSpecialRule(final String name) {
        return getFactory().getSpecialRule(name, getRulesetService());
    }

    @Override
    public final Unit getUnit(final UnitTemplate template) {
        return getFactory().getUnit(template, getRulesetService());
    }

    @Override
    public final UnitArmorAvailability getUnitArmorAvailability(
            final UnitTemplate unit,
            final Collection<ArmorOption> armorOptions,
            final ArmorOption initialArmor) {
        return getFactory().getUnitArmorAvailability(unit, armorOptions,
                initialArmor);
    }

    @Override
    public final UnitEquipmentAvailability getUnitEquipmentAvailability(
            final UnitTemplate unit, final Collection<Equipment> equipment) {
        return new DefaultUnitEquipmentAvailability(unit, equipment);
    }

    @Override
    public final Constraint getUnitGangConstraint(final Gang gang,
            final String name, final String unit, final String... context) {
        return getFactory().getConstraint(gang, name, unit,
                Arrays.asList(context), getLocalizationService());
    }

    @Override
    public final UnitMutationAvailability getUnitMutationAvailability(
            final UnitTemplate unit, final Integer max,
            final Collection<Mutation> mutations) {
        return getFactory().getUnitMutationAvailability(unit, max, mutations);
    }

    @Override
    public final UnitWeaponAvailability getUnitWeaponAvailability(
            final UnitTemplate unit,
            final Collection<WeaponOption> weaponOptions,
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
    public final WeaponOption getWeaponOption(final UnitWeapon weapon,
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
