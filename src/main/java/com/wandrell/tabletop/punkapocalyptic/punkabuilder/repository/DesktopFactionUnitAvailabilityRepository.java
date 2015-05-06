package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.availability.FactionUnitAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionUnitAvailabilityRepository;

@Component("factionUnitRepo")
public final class DesktopFactionUnitAvailabilityRepository extends
        JPARepository<FactionUnitAvailability> implements
        FactionUnitAvailabilityRepository {

    public DesktopFactionUnitAvailabilityRepository() {
        super(new DefaultQueryData(
                "SELECT ava FROM FactionUnitAvailability ava"));
    }

    @Override
    public final Collection<FactionUnitAvailability>
            getAvailabilitiesForFaction(final String faction) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("faction", faction);

        return getCollection(new DefaultQueryData(
                "SELECT ava FROM FactionUnitAvailability ava WHERE ava.faction.nameToken = :faction",
                params));
    }

    @Override
    public final FactionUnitAvailability getAvailabilityForUnit(
            final String unit) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("unit", unit);

        return getEntity(new DefaultQueryData(
                "SELECT ava FROM FactionUnitAvailability ava WHERE ava.unitTemplate.nameToken = :unit",
                params));
    }

    @Override
    public final Collection<UnitTemplate> getUnitsForFaction(
            final String faction) {
        final Collection<UnitTemplate> result;
        final Collection<FactionUnitAvailability> avas;

        // TODO: This maybe can be done somehow else

        avas = getAvailabilitiesForFaction(faction);

        result = new LinkedList<>();
        for (final FactionUnitAvailability ava : avas) {
            result.add(ava.getUnit());
        }

        return result;
    }

}
