package com.wandrell.tabletop.punkapocalyptic.report.reportbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;

import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.GangReportTitleComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

public final class GangReportBuilder extends JasperReportBuilder {

    private static final long serialVersionUID = 1781720918143793266L;

    public GangReportBuilder(final String appName, final String version,
            final String URL, final String imagePath,
            final LocalizationService localizationService,
            final RulesetService rulesetService) {
        super();

        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        setTemplate(factory.getReportTemplate());

        // Title
        title(new GangReportTitleComponentBuilder(appName, version, URL,
                imagePath, localizationService, rulesetService));
        // Units report
        detailFooter(getUnitsSubreport(localizationService));
        // Footer
        pageFooter(factory.getReportFooter());
    }

    private final SubreportBuilder getUnitsSubreport(
            final LocalizationService localizationService) {
        return Components
                .subreport(new UnitsReportBuilder(localizationService))
                .setDataSource(
                        Expressions
                                .subDatasourceBeanCollection(ReportConf.UNITS));
    }

}
