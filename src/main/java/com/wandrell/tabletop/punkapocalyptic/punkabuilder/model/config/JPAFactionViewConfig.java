package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.config;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javafx.scene.image.Image;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.faction.JPAFaction;
import com.wandrell.util.persistence.PersistenceEntity;

@Entity(name = "FactionViewConfig")
@Table(name = "faction_view_config")
public final class JPAFactionViewConfig implements FactionViewConfig,
        PersistenceEntity {

    @OneToOne
    @JoinColumn(name = "faction_id", referencedColumnName = "id")
    private JPAFaction faction;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer    id = -1;
    @Transient
    private Image      image;
    @Column(name = "image")
    private String     imagePath;

    public JPAFactionViewConfig() {
        super();
    }

    @Override
    public final Faction getFaction() {
        return faction;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Image getImage() {
        if (image == null) {
            image = new Image(getClassPathInputStream(getImagePath()));
        }

        return image;
    }

    public final String getImagePath() {
        return imagePath;
    }

    public final void setFaction(final JPAFaction faction) {
        this.faction = faction;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setImagePath(final String path) {
        imagePath = path;
    }

    /**
     * Creates an {@code InputStream} pointing to the file specified by the
     * path, if it exists.
     * <p>
     * If any problem occurs during this process a {@code RuntimeException} is
     * thrown, or an {@code IllegalArgumentException} if the file is not found.
     * 
     * @param path
     *            the path to transform
     * @return an {@code InputStream} pointing to the path
     */
    private final InputStream getClassPathInputStream(final String path) {
        final URL url;                  // URL parsed from the path
        final InputStream stream;       // Stream for the file

        url = getClassPathURL(path);

        checkArgument(url != null,
                String.format("The path %s is invalid", path));

        try {
            stream = new BufferedInputStream(url.openStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        return stream;
    }

    /**
     * Creates an {@code URL} pointing to the file specified by the path, if it
     * exists.
     * 
     * @param path
     *            the path to transform
     * @return an URL pointing inside the class path
     */
    private final URL getClassPathURL(final String path) {
        checkNotNull(path, "Received a null pointer as path");

        return this.getClass().getClassLoader().getResource(path);
    }

}
