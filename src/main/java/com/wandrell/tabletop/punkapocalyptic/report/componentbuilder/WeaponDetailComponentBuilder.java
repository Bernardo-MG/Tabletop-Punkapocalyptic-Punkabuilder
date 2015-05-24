package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.report.field.SpecialRuleField;
import com.wandrell.tabletop.punkapocalyptic.report.field.WeaponEnhancementField;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.OptionalListReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class WeaponDetailComponentBuilder extends HorizontalListBuilder {

    private static final long serialVersionUID = 7962009211729701176L;

    private final static SubreportBuilder getWeaponEnhancementsSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<WeaponEnhancement> field;
        final SubreportBuilder subReport;

        field = new WeaponEnhancementField(ReportConf.CURRENT);

        report = new OptionalListReportBuilder(field,
                localizationService
                        .getReportString(ReportBundleConf.ENHANCEMENTS));

        subReport = Components.subreport(report);

        subReport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPON_ENHANCEMENTS));
        subReport.setHeight(10);

        return subReport;
    }

    private final static SubreportBuilder getWeaponRulesSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<SpecialRule> field;
        final SubreportBuilder subReport;

        field = new SpecialRuleField(ReportConf.CURRENT);

        report = new OptionalListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.RULES));

        subReport = Components.subreport(report).setDataSource(
                new CurrentObjectDatasourceExpression(
                        ReportConf.WEAPON_TEMPLATE));

        subReport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPON_ENHANCEMENTS));
        subReport.setHeight(10);

        return subReport;
    }

    public WeaponDetailComponentBuilder(
            final LocalizationService localizationService) {
        super();

        final SubreportBuilder rules;
        final ComponentBuilder<?, ?> enhancements;
        final DynamicReportsFactory factory;
        final SubreportBuilder subReport;

        factory = DynamicReportsFactory.getInstance();
        rules = getWeaponRulesSubreport(localizationService);
        enhancements = factory
                .getBorderedCellComponentThin(getWeaponEnhancementsSubreport(localizationService));

        subReport = Components.subreport(DynamicReports.report().detail(
                new WeaponAttributesComponentBuilder(localizationService)));

        subReport.setDataSource(new CurrentObjectDatasourceExpression(
                ReportConf.WEAPON_TEMPLATE));

        add(subReport);
        add(factory.getBorderedCellComponentThin(Components.verticalList(rules,
                enhancements)));
    }

}
