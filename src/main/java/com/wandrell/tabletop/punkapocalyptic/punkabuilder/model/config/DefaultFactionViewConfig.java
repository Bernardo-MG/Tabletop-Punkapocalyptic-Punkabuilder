package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config;

import javafx.scene.image.Image;

import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;

public final class DefaultFactionViewConfig implements FactionViewConfig {

    final Faction faction;
    final Image   image;

    public DefaultFactionViewConfig(final Faction faction, final Image image) {
        super();

        this.faction = faction;
        this.image = image;
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Image getImage() {
        return image;
    }

}
