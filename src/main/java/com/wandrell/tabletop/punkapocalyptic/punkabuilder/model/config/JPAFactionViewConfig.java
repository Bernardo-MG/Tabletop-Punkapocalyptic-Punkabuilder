package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config;

import javafx.scene.image.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;
import com.wandrell.util.ResourceUtils;

@Entity(name = "FactionViewConfig")
@Table(name = "faction_view_config")
public final class JPAFactionViewConfig implements FactionViewConfig,
        PersistenceEntity {

    @Column(name = "faction")
    private String  faction;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;
    @Transient
    private Image   image;
    @Column(name = "image")
    private String  imagePath;

    public JPAFactionViewConfig() {
        super();
    }

    @Override
    public final String getFaction() {
        return faction;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Image getImage() {
        if (image == null) {
            image = new Image(
                    ResourceUtils.getClassPathInputStream(getImagePath()));
        }

        return image;
    }

    public final String getImagePath() {
        return imagePath;
    }

    public final void setFaction(final String faction) {
        this.faction = faction;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setImagePath(final String path) {
        imagePath = path;
    }

}
