package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitArmorAvailabilityRepository;
import com.wandrell.util.persistence.JPARepository;

@Component("unitArmorRepo")
public final class DesktopUnitArmorAvailabilityRepository extends
        JPARepository<UnitArmorAvailability> implements
        UnitArmorAvailabilityRepository {

    public DesktopUnitArmorAvailabilityRepository() {
        super(new DefaultQueryData("SELECT ava FROM UnitArmorAvailability ava"));
    }

    @Override
    public final UnitArmorAvailability
            getAvailabilityForUnit(final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitArmorAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

}
