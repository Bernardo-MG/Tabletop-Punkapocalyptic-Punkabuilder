package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.EquipmentRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitRepository;

public final class TransactionUnitEquipmentParser implements
        Parser<Map<String, Object>, UnitEquipmentAvailability> {

    private final EquipmentRepository equipmentRepo;
    private final UnitRepository      unitsRepo;

    public TransactionUnitEquipmentParser(final UnitRepository unitsRepo,
            final EquipmentRepository equipmentRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.equipmentRepo = equipmentRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitEquipmentAvailability
            parse(final Map<String, Object> input) {
        final Unit unit;
        final Collection<String> equipmentNames;
        final Collection<Equipment> equipment;

        unit = unitsRepo.getByName(input.get("unit").toString());

        equipmentNames = (Collection<String>) input.get("equipment");
        equipment = equipmentRepo.getByNamesList(equipmentNames);

        // TODO: Use a service
        return new DefaultUnitEquipmentAvailability(unit, equipment);
    }

}
