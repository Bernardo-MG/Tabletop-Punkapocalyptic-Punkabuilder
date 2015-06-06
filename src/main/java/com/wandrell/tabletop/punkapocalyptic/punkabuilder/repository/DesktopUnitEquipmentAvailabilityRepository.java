package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.jpa.JPARepository;
import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitEquipmentAvailabilityRepository;

@Component("unitEquipmentRepo")
public final class DesktopUnitEquipmentAvailabilityRepository extends
        JPARepository<UnitEquipmentAvailability> implements
        UnitEquipmentAvailabilityRepository {

    public DesktopUnitEquipmentAvailabilityRepository() {
        super(new DefaultQueryData(
                "SELECT ava FROM UnitEquipmentAvailability ava"));
    }

    @Override
    public final UnitEquipmentAvailability getAvailabilityForUnit(
            final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitEquipmentAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

}
