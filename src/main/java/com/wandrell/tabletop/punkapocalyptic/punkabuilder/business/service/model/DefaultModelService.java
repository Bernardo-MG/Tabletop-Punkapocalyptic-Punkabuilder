package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import com.wandrell.pattern.command.CommandExecutor;
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
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetArmorCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetEquipmentCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetFactionCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetFactionUnitAvailabilityCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetGangCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetMeleeWeaponCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetMutationCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetRangedValueCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetRangedWeaponCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetSpecialRuleCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetUnitArmorAvailabilityCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetUnitCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetUnitGangConstraintCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetUnitMutationAvailabilityCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetUnitWeaponAvailabilityCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetWeaponEnhancementCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command.GetWeaponOptionCommand;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;

public final class DefaultModelService implements ModelService {

    private final CommandExecutor executor;

    public DefaultModelService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as executor");

        this.executor = executor;
    }

    @Override
    public final Armor getArmor(final String name, final Integer armor,
            final Collection<SpecialRule> rules) {
        return getExecutor().execute(new GetArmorCommand(name, armor, rules));
    }

    @Override
    public final Equipment getEquipment(final String name, final Integer cost) {
        return getExecutor().execute(new GetEquipmentCommand(name, cost));
    }

    @Override
    public final Faction getFaction(final String name) {
        return getExecutor().execute(new GetFactionCommand(name));
    }

    @Override
    public final FactionUnitAvailability getFactionUnitAvailability(
            final Faction faction, final Unit unit,
            final Collection<Constraint> constraints) {
        return getExecutor().execute(
                new GetFactionUnitAvailabilityCommand(faction, unit,
                        constraints));
    }

    @Override
    public final Gang getGang(final Faction faction) {
        return getExecutor().execute(new GetGangCommand(faction));
    }

    @Override
    public final MeleeWeapon getMeleeWeapon(final String name,
            final Integer cost, final Integer strength,
            final Integer penetration, final Integer combat,
            final Collection<SpecialRule> rules) {
        return getExecutor().execute(
                new GetMeleeWeaponCommand(name, cost, strength, penetration,
                        combat, rules));
    }

    @Override
    public final Mutation getMutation(final String name, final Integer cost,
            final Integer actions, final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness) {
        return getExecutor().execute(
                new GetMutationCommand(name, cost, actions, agility, combat,
                        precision, strength, tech, toughness));
    }

    @Override
    public final RangedValue getRangedValue(final Integer distanceShort,
            final Integer distanceMedium, final Integer distanceLong) {
        return getExecutor().execute(
                new GetRangedValueCommand(distanceShort, distanceMedium,
                        distanceLong));
    }

    @Override
    public final RangedWeapon getRangedWeapon(final String name,
            final Integer cost, final Collection<SpecialRule> rules,
            final RangedValue penetration, final RangedValue strength,
            final RangedValue distanceCM, final RangedValue distanceInches,
            final MeleeWeapon weaponMelee) {
        return getExecutor().execute(
                new GetRangedWeaponCommand(name, cost, rules, penetration,
                        strength, distanceCM, distanceInches, weaponMelee));
    }

    @Override
    public final SpecialRule getSpecialRule(final String name) {
        return getExecutor().execute(new GetSpecialRuleCommand(name));
    }

    @Override
    public final Unit getUnit(final String name, final Integer actions,
            final Integer agility, final Integer combat,
            final Integer precision, final Integer strength,
            final Integer tech, final Integer toughness, final Integer cost,
            final Collection<SpecialRule> rules) {
        return getExecutor().execute(
                new GetUnitCommand(name, actions, agility, combat, precision,
                        strength, tech, toughness, cost, rules));
    }

    @Override
    public final UnitArmorAvailability getUnitArmorAvailability(
            final Unit unit, final Collection<Armor> armorOptions,
            final Armor initialArmor) {
        return getExecutor().execute(
                new GetUnitArmorAvailabilityCommand(unit, armorOptions,
                        initialArmor));
    }

    @Override
    public final UnitEquipmentAvailability getUnitEquipmentAvailability(
            final Unit unit, final Collection<Equipment> equipment) {
        return new DefaultUnitEquipmentAvailability(unit, equipment);
    }

    @Override
    public final Constraint getUnitGangConstraint(final String name,
            final String unit, final String... context) {
        return getExecutor().execute(
                new GetUnitGangConstraintCommand(name, unit, context));
    }

    @Override
    public final UnitMutationAvailability getUnitMutationAvailability(
            final Unit unit, final Integer max,
            final Collection<Mutation> mutations) {
        return getExecutor().execute(
                new GetUnitMutationAvailabilityCommand(unit, max, mutations));
    }

    @Override
    public final UnitWeaponAvailability getUnitWeaponAvailability(
            final Unit unit, final Collection<WeaponOption> weaponOptions,
            final Integer minWeapons, final Integer maxWeapons) {
        return getExecutor().execute(
                new GetUnitWeaponAvailabilityCommand(unit, weaponOptions,
                        minWeapons, maxWeapons));
    }

    @Override
    public final WeaponEnhancement getWeaponEnhancement(final String name,
            final Integer cost) {
        return getExecutor().execute(
                new GetWeaponEnhancementCommand(name, cost));
    }

    @Override
    public final WeaponOption getWeaponOption(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        return getExecutor().execute(
                new GetWeaponOptionCommand(weapon, enhancements));
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }

}
