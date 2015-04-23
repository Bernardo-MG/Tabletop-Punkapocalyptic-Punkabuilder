package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.FactionViewConfig;

public interface FactionViewConfigRepository extends
        Repository<FactionViewConfig> {

    public FactionViewConfig getConfigForFaction(final Faction faction);

}
