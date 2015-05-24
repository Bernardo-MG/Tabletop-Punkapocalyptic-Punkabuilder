package com.wandrell.tabletop.punkapocalyptic.report.reportbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.UnitDetailComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.UnitReportTitleComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitsReportBuilder extends JasperReportBuilder {

    private static final long serialVersionUID = 8126817607152371478L;

    public UnitsReportBuilder(final LocalizationService localizationService) {
        super();
        final JasperReportBuilder unitReport;
        final SubreportBuilder subreport;
        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        unitReport = DynamicReports.report();

        unitReport.setTemplate(factory.getReportTemplate());
        unitReport.title(new UnitReportTitleComponentBuilder(
                localizationService));
        unitReport.detail(new UnitDetailComponentBuilder(localizationService));
        unitReport.detailFooter(Components.verticalGap(20));

        unitReport.setTitleStyle(Styles.style()
                .setTopBorder(Styles.pen2Point()));
        unitReport.setDetailStyle(Styles.style().setBottomBorder(
                Styles.pen2Point()));

        setTemplate(factory.getReportTemplate());

        subreport = Components.subreport(unitReport).setDataSource(
                new CurrentObjectDatasourceExpression(ReportConf.CURRENT));

        addDetail(subreport);
    }

}
