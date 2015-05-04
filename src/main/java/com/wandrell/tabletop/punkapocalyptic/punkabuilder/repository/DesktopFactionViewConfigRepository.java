package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.FactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.JPARepository;

@Component("factionViewRepo")
public final class DesktopFactionViewConfigRepository extends
        JPARepository<FactionViewConfig> implements FactionViewConfigRepository {

    public DesktopFactionViewConfigRepository() {
        super(new DefaultQueryData(
                "SELECT config FROM FactionViewConfig config"));
    }

    @Override
    public final FactionViewConfig getConfigForFaction(final String faction) {
        final Map<String, Object> params;

        params = new LinkedHashMap<>();
        params.put("faction", faction);

        return getEntity(new DefaultQueryData(
                "SELECT config FROM FactionViewConfig config WHERE config.faction = :faction",
                params));
    }

}
