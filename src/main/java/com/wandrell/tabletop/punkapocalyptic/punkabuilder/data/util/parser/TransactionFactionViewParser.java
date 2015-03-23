package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Map;

import javafx.scene.image.Image;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.model.config.DefaultFactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.model.config.FactionViewConfig;
import com.wandrell.util.ResourceUtils;

public final class TransactionFactionViewParser implements
        Parser<Map<String, Object>, FactionViewConfig> {

    private final Repository<Faction> factionRepo;

    public TransactionFactionViewParser(final Repository<Faction> factionRepo) {
        super();

        this.factionRepo = factionRepo;
    }

    @Override
    public final FactionViewConfig parse(final Map<String, Object> input) {
        final String name;
        final Image image;
        final String path;
        final Faction faction;

        path = input.get("image").toString();

        name = input.get("faction").toString();
        image = new Image(ResourceUtils.getClassPathInputStream(path));

        faction = factionRepo.getCollection(f -> f.getName().equals(name))
                .iterator().next();

        return new DefaultFactionViewConfig(faction, image);
    }

}
