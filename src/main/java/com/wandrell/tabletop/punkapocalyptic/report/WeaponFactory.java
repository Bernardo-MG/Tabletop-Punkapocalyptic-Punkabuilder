package com.wandrell.tabletop.punkapocalyptic.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.SpecialRulesDataType;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.WeaponEnhancementDataType;
import com.wandrell.tabletop.punkapocalyptic.report.field.UnitWeaponField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponCombatFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceImperialFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceMetricFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponPenetrationFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponStrengthFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class WeaponFactory {

    private final static DynamicReportsFactory factory = getDynamicReportsFactory();

    public final static SubreportBuilder createWeaponsSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;

        report = DynamicReports.report();
        report.detail(getDynamicReportsFactory().getBorderedCellComponentThin(
                getWeaponDetailComponent(localizationService)));

        return Components.subreport(report);
    }

    public static final ComponentBuilder<?, ?> getUnitWeaponSubreport(
            final LocalizationService localizationService) {
        final SubreportBuilder subreport;

        subreport = WeaponFactory.createWeaponsSubreport(localizationService);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPONS));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                subreport);
    }

    private final static DynamicReportsFactory getDynamicReportsFactory() {
        return factory;
    }

    private final static ComponentBuilder<?, ?> getWeaponDetailComponent(
            final LocalizationService localizationService) {
        final ComponentBuilder<?, ?> data;
        final ComponentBuilder<?, ?> attributes;
        final SubreportBuilder rules;
        final SubreportBuilder equipment;

        rules = getWeaponRulesSubreport(localizationService);
        rules.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.SPECIAL_RULES));
        rules.setHeight(10);

        equipment = getWeaponEnhancementsSubreport(localizationService);
        equipment.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPON_ENHANCEMENTS));
        equipment.setHeight(10);

        attributes = Components
                .verticalList(
                        Components.text(new UnitWeaponField(ReportConf.CURRENT,
                                new WeaponNameFormatter())),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(localizationService
                                        .getReportString(ReportBundleConf.COMBAT)),
                                Components.text(new UnitWeaponField(
                                        ReportConf.CURRENT,
                                        new WeaponCombatFormatter()))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(localizationService
                                        .getReportString(ReportBundleConf.STRENGTH)),
                                Components.text(new UnitWeaponField(
                                        ReportConf.CURRENT,
                                        new WeaponStrengthFormatter()))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(localizationService
                                        .getReportString(ReportBundleConf.PENETRATION)),
                                Components.text(new UnitWeaponField(
                                        ReportConf.CURRENT,
                                        new WeaponPenetrationFormatter()))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(localizationService
                                        .getReportString(ReportBundleConf.DISTANCE_METRIC)),
                                Components.text(new UnitWeaponField(
                                        ReportConf.CURRENT,
                                        new WeaponDistanceMetricFormatter()))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(localizationService
                                        .getReportString(ReportBundleConf.DISTANCE_IMPERIAL)),
                                Components.text(new UnitWeaponField(
                                        ReportConf.CURRENT,
                                        new WeaponDistanceImperialFormatter()))));

        data = Components.horizontalList(
                attributes,
                getDynamicReportsFactory().getBorderedCellComponentThin(
                        Components
                                .verticalList(
                                        rules,
                                        getDynamicReportsFactory()
                                                .getBorderedCellComponentThin(
                                                        equipment))));

        return data;
    }

    private final static SubreportBuilder getWeaponEnhancementsSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<WeaponEnhancement> field;

        field = new DRField<WeaponEnhancement>(ReportConf.CURRENT,
                WeaponEnhancement.class);
        field.setDataType(new WeaponEnhancementDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(localizationService
                .getReportString(ReportBundleConf.ENHANCEMENTS)));
        report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);

        return Components.subreport(report);
    }

    private final static SubreportBuilder getWeaponRulesSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<SpecialRule> field;

        field = new DRField<SpecialRule>(ReportConf.CURRENT, SpecialRule.class);
        field.setDataType(new SpecialRulesDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(localizationService
                .getReportString(ReportBundleConf.RULES)));
        report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);

        return Components.subreport(report);
    }

    private WeaponFactory() {
        super();
    }

}
