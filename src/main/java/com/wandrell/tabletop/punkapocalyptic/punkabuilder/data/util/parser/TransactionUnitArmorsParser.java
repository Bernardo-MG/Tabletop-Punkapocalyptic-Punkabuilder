package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.stream.Collectors;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.DefaultArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.ArmorRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

public final class TransactionUnitArmorsParser implements
        Parser<Map<String, Object>, UnitArmorAvailability> {

    private final ArmorRepository        armorsRepo;
    private final UnitTemplateRepository unitsRepo;

    public TransactionUnitArmorsParser(final UnitTemplateRepository unitsRepo,
            final ArmorRepository armorsRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.armorsRepo = armorsRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitArmorAvailability parse(final Map<String, Object> input) {
        final UnitTemplate unit;
        final Collection<Armor> armors;
        final Collection<ArmorOption> options;
        final Collection<String> armorNames;
        final ArmorOption initial;
        final Collection<Map<String, Object>> armorData;
        Integer cost;

        unit = unitsRepo.getByNameToken(input.get("unit").toString());

        armorData = (Collection<Map<String, Object>>) input.get("armors");

        armorNames = new LinkedList<>();
        for (final Map<String, Object> data : armorData) {
            armorNames.add(data.get("name").toString());
        }
        armors = armorsRepo.getByNamesList(armorNames);

        options = new LinkedList<>();
        for (final Armor armor : armors) {
            cost = Integer.valueOf(armorData.stream()
                    .filter(d -> d.get("name").equals(armor.getName()))
                    .collect(Collectors.toList()).iterator().next().get("cost")
                    .toString());

            options.add(new DefaultArmorOption(armor, cost));
        }

        if (input.containsKey("initial")) {
            initial = new DefaultArmorOption(armorsRepo.getByName(input.get(
                    "initial").toString()), 0);
        } else {
            initial = new DefaultArmorOption(armorsRepo.getByName("unarmored"),
                    0);
        }

        // TODO: Use a service
        return new DefaultUnitArmorAvailability(unit, options, initial);
    }

}
