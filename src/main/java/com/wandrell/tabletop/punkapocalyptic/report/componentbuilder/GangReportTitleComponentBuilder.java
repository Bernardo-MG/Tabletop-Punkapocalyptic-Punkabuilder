package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import java.io.InputStream;

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
import com.wandrell.util.ResourceUtils;

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

        imageStream = ResourceUtils.getClassPathInputStream(imagePath);

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

}
