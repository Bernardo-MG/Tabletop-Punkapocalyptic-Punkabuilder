package com.wandrell.tabletop.punkapocalyptic.report;

import java.io.InputStream;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;

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

public final class GangReportFactory {

    private static final DynamicReportsFactory factory = DynamicReportsFactory
                                                               .getInstance();

    public final static JasperReportBuilder getGangReport(final String appName,
            final String version, final String URL, final String imagePath,
            final LocalizationService localizationService,
            final RulesetService rulesetService) {
        final JasperReportBuilder report;

        // Report template
        report = DynamicReports.report();
        report.setTemplate(getDynamicReportsFactory().getReportTemplate());

        // Title
        report.title(getGangReportTitle(appName, version, URL, imagePath,
                localizationService, rulesetService));
        // Units report
        report.detailFooter(UnitReportFactory
                .getUnitsSubreport(localizationService));
        // Footer
        report.pageFooter(getDynamicReportsFactory().getReportFooter());

        return report;
    }

    private static final DynamicReportsFactory getDynamicReportsFactory() {
        return factory;
    }

    private static final ComponentBuilder<?, ?> getGangReportTitle(
            final String appName, final String version, final String URL,
            final String imagePath,
            final LocalizationService localizationService,
            final RulesetService rulesetService) {
        final ComponentBuilder<?, ?> brand;
        final VerticalListBuilder gangData;
        final InputStream imageStream;

        imageStream = ResourceUtils.getClassPathInputStream(imagePath);

        brand = getDynamicReportsFactory().getTitleLabelComponent(imageStream,
                appName, version, URL);
        brand.setStyle(Styles.style().setRightBorder(Styles.pen1Point()));

        gangData = Components.verticalList();
        // Faction
        gangData.add(Components
                .text(new FactionField(ReportConf.FACTION,
                        new FactionNameFormatter())).setStyle(
                        getDynamicReportsFactory().getTitleStyle()));
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

        return Components.verticalList().add(
                Components.horizontalList().add(brand,
                        Components.horizontalGap(5), gangData),
                Components.line(), Components.verticalGap(10));
    }

    private GangReportFactory() {
        super();
    }

}
