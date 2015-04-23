package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitRepository;

public final class TransactionUnitArmorsParser implements
        Parser<Map<String, Object>, UnitArmorAvailability> {

    private final ArmorRepository armorsRepo;
    private final UnitRepository  unitsRepo;

    public TransactionUnitArmorsParser(final UnitRepository unitsRepo,
            final ArmorRepository armorsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.armorsRepo = armorsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitArmorAvailability parse(final Map<String, Object> input) {
        final Unit unit;
        final Collection<Armor> armors;
        final Collection<String> armorNames;
        final Armor initial;

        unit = unitsRepo.getByName(input.get("unit").toString());

        armorNames = (Collection<String>) input.get("armors");
        armors = armorsRepo.getByNamesList(armorNames);

        if (input.containsKey("initial")) {
            initial = armorsRepo.getByName(input.get("initial").toString());
        } else {
            initial = armorsRepo.getByName("unarmored");
        }

        // TODO: Use a service
        return new DefaultUnitArmorAvailability(unit, armors, initial);
    }

}
