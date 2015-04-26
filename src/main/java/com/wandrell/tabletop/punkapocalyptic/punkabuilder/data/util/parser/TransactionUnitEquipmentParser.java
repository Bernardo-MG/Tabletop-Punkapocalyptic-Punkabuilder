package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Collection;
import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.availability.DefaultUnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.repository.EquipmentRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitTemplateRepository;

public final class TransactionUnitEquipmentParser implements
        Parser<Map<String, Object>, UnitEquipmentAvailability> {

    private final EquipmentRepository    equipmentRepo;
    private final UnitTemplateRepository unitsRepo;

    public TransactionUnitEquipmentParser(
            final UnitTemplateRepository unitsRepo,
            final EquipmentRepository equipmentRepo) {
        super();

        this.unitsRepo = unitsRepo;
        this.equipmentRepo = equipmentRepo;
    }

    @SuppressWarnings("unchecked")
    @Override
    public final UnitEquipmentAvailability
            parse(final Map<String, Object> input) {
        final UnitTemplate unit;
        final Collection<String> equipmentNames;
        final Collection<Equipment> equipment;

        unit = unitsRepo.getByNameToken(input.get("unit").toString());

        equipmentNames = (Collection<String>) input.get("equipment");
        equipment = equipmentRepo.getByNamesList(equipmentNames);

        // TODO: Use a service
        return new DefaultUnitEquipmentAvailability(unit, equipment);
    }

}
