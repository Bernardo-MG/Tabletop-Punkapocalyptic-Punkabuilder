package com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository;

import org.springframework.stereotype.Component;

import com.wandrell.pattern.repository.DefaultQueryData;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;
import com.wandrell.util.persistence.JPARepository;

@Component("factionRepo")
public final class DesktopFactionRepository extends JPARepository<Faction>
        implements FactionRepository {

    public DesktopFactionRepository() {
        super(new DefaultQueryData("SELECT faction FROM Faction faction"));
    }

}
