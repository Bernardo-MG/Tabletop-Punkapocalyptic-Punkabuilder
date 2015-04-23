package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Map;

import javafx.scene.image.Image;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.DefaultFactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config.FactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;
import com.wandrell.util.ResourceUtils;

public final class TransactionFactionViewParser implements
        Parser<Map<String, Object>, FactionViewConfig> {

    private final FactionRepository factionRepo;

    public TransactionFactionViewParser(final FactionRepository factionRepo) {
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

        faction = factionRepo.getByName(name);

        return new DefaultFactionViewConfig(faction, image);
    }

}
