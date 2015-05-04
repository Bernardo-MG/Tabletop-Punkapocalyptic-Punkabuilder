package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;

@Component("factionRepo")
public final class DesktopFactionRepository extends JPARepository<Faction>
        implements FactionRepository {

    public DesktopFactionRepository() {
        super(new DefaultQueryData("SELECT faction FROM Faction faction"));
    }

    @Override
    public final Faction getByName(final String faction) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("name", faction);

        return getEntity(new DefaultQueryData(
                "SELECT faction FROM Faction faction WHERE faction.name = :name",
                params));
    }

}
