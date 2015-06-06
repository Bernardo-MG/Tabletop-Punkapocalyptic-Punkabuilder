package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.FactionField;
import com.wandrell.tabletop.punkapocalyptic.report.field.GangField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.FactionNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GangBulletsFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GangUnitsRangeFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GangValorationFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

public final class GangReportTitleComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = 468162922510495820L;

    public GangReportTitleComponentBuilder(final String appName,
            final String version, final String URL, final String imagePath,
            final LocalizationService localizationService,
            final RulesetService rulesetService) {
        super();
        final ComponentBuilder<?, ?> brand;
        final VerticalListBuilder gangData;
        final InputStream imageStream;
        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        imageStream = getClassPathInputStream(imagePath);

        brand = factory.getTitleLabelComponent(imageStream, appName, version,
                URL);
        brand.setStyle(Styles.style().setRightBorder(Styles.pen1Point()));

        gangData = Components.verticalList();
        // Faction
        gangData.add(Components
                .text(new FactionField(ReportConf.FACTION,
                        new FactionNameFormatter())).setStyle(
                        factory.getTitleStyle()));
        // Valoration
        gangData.add(Components.text(new GangField(ReportConf.CURRENT,
                new GangValorationFormatter(localizationService))));
        // Units
        gangData.add(Components
                .text(new GangField(ReportConf.CURRENT,
                        new GangUnitsRangeFormatter(localizationService,
                                rulesetService))));

        // Bullets
        gangData.add(Components.text(new GangField(ReportConf.CURRENT,
                new GangBulletsFormatter(localizationService))));

        add(Components.horizontalList().add(brand, Components.horizontalGap(5),
                gangData), Components.line(), Components.verticalGap(10));
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
