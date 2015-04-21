package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import com.google.common.base.Predicate;
import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultWeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class TransactionUnitWeaponsParser implements
        Parser<Map<String, Object>, UnitWeaponAvailability> {

    private final QueryableRepository<WeaponEnhancement, Predicate<WeaponEnhancement>> enhancementsRepo;
    private final QueryableRepository<Unit, Predicate<Unit>>                           unitsRepo;
    private final QueryableRepository<Weapon, Predicate<Weapon>>                       weaponsRepo;

    public TransactionUnitWeaponsParser(
            final QueryableRepository<Unit, Predicate<Unit>> unitsRepo,
            final QueryableRepository<Weapon, Predicate<Weapon>> weaponsRepo,
            final QueryableRepository<WeaponEnhancement, Predicate<WeaponEnhancement>> enhancementsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.weaponsRepo = weaponsRepo;
        this.enhancementsRepo = enhancementsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitWeaponAvailability parse(final Map<String, Object> input) {
        final Unit unit;
        final Collection<Weapon> weapons;
        final Collection<WeaponEnhancement> enhancements;
        final Collection<String> weaponNames;
        final Collection<String> enhancementNames;
        final Collection<WeaponOption> options;
        final Integer min;
        final Integer max;
        Collection<WeaponEnhancement> validEnhancements;

        unit = unitsRepo.getCollection(new Predicate<Unit>() {

            @Override
            public final boolean apply(final Unit unit) {
                return unit.getName().equals(input.get("unit"));
            }

        }).iterator().next();

        weaponNames = (Collection<String>) input.get("weapons");
        weapons = weaponsRepo.getCollection(new Predicate<Weapon>() {

            @Override
            public final boolean apply(final Weapon weapon) {
                return weaponNames.contains(weapon.getName());
            }

        });

        enhancementNames = (Collection<String>) input.get("enhancements");
        enhancements = enhancementsRepo
                .getCollection(new Predicate<WeaponEnhancement>() {

                    @Override
                    public final boolean apply(
                            final WeaponEnhancement enhancement) {
                        return enhancementNames.contains(enhancement.getName());
                    }

                });

        options = new LinkedList<>();
        for (final Weapon weapon : weapons) {
            validEnhancements = new LinkedList<>();
            for (final WeaponEnhancement enhancement : enhancements) {
                if (enhancement.isValid(weapon)) {
                    validEnhancements.add(enhancement);
                }
            }
            options.add(new DefaultWeaponOption(weapon, validEnhancements));
        }

        min = (Integer) input.get("min_weapons");
        max = (Integer) input.get("max_weapons");

        // TODO: Use a service
        return new DefaultUnitWeaponAvailability(unit, options, min, max);
    }

}
