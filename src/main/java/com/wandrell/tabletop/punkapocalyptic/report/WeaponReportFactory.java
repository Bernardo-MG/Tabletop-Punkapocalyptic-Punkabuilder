package com.wandrell.tabletop.punkapocalyptic.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;

import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.WeaponDetailComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class WeaponReportFactory {

    private final static DynamicReportsFactory factory = DynamicReportsFactory
                                                               .getInstance();

    public static final ComponentBuilder<?, ?> getUnitWeaponSubreport(
            final LocalizationService localizationService) {
        final SubreportBuilder subreport;

        subreport = WeaponReportFactory
                .createWeaponsSubreport(localizationService);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPONS));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                subreport);
    }

    private final static SubreportBuilder createWeaponsSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;

        report = DynamicReports.report();
        report.detail(getDynamicReportsFactory().getBorderedCellComponentThin(
                new WeaponDetailComponentBuilder(localizationService)));

        return Components.subreport(report);
    }

    private final static DynamicReportsFactory getDynamicReportsFactory() {
        return factory;
    }

    private WeaponReportFactory() {
        super();
    }

}
