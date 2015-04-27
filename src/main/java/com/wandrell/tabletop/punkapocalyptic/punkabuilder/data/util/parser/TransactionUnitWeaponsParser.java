package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.DefaultWeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponEnhancementRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;

public final class TransactionUnitWeaponsParser implements
        Parser<Map<String, Object>, UnitWeaponAvailability> {

    private final WeaponEnhancementRepository enhancementsRepo;
    private final UnitTemplateRepository      unitsRepo;
    private final WeaponRepository            weaponsRepo;

    public TransactionUnitWeaponsParser(final UnitTemplateRepository unitsRepo,
            final WeaponRepository weaponsRepo,
            final WeaponEnhancementRepository enhancementsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.weaponsRepo = weaponsRepo;
        this.enhancementsRepo = enhancementsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitWeaponAvailability parse(final Map<String, Object> input) {
        final UnitTemplate unit;
        final Collection<Weapon> weapons;
        final Collection<WeaponEnhancement> enhancements;
        final Collection<String> weaponNames;
        final Collection<String> enhancementNames;
        final Collection<WeaponOption> options;
        final Integer min;
        final Integer max;
        Collection<WeaponEnhancement> validEnhancements;

        unit = unitsRepo.getByNameToken(input.get("unit").toString());

        weaponNames = (Collection<String>) input.get("weapons");
        weapons = weaponsRepo.getByNamesList(weaponNames);

        enhancementNames = (Collection<String>) input.get("enhancements");
        enhancements = enhancementsRepo.getByNamesList(enhancementNames);

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
