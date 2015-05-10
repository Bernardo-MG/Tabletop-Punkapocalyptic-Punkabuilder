package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitMutationAvailability;
import com.wandrell.tabletop.punkapocalyptic.repository.UnitMutationAvailabilityRepository;
import com.wandrell.util.persistence.JPARepository;

@Component("unitMutationRepo")
public final class DesktopUnitMutationAvailabilityRepository extends
        JPARepository<UnitMutationAvailability> implements
        UnitMutationAvailabilityRepository {

    public DesktopUnitMutationAvailabilityRepository() {
        super(new DefaultQueryData(
                "SELECT ava FROM UnitMutationAvailability ava"));
    }

    @Override
    public final UnitMutationAvailability getAvailabilityForUnit(String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM UnitMutationAvailability ava WHERE ava.unit.name = :unit",
                params));
    }

}
