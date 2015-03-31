package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.command;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.SpecialRulesDataType;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.WeaponDataType;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.WeaponEnhancementDataType;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponCombatFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceImperialFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceMetricFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponPenetrationFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponStrengthFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;

public final class BuildUnitWeaponSubreportCommand implements
        ResultCommand<ComponentBuilder<?, ?>>, LocalizationServiceAware {

    private ComponentBuilder<?, ?> builder;
    private LocalizationService    localizationService;

    public BuildUnitWeaponSubreportCommand() {
        super();
    }

    @Override
    public final void execute() {
        final SubreportBuilder subreport;

        subreport = createWeaponsSubreport();
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPONS));

        builder = DynamicReportsFactory.getInstance()
                .getBorderedCellComponentThin(subreport);
    }

    @Override
    public final ComponentBuilder<?, ?> getResult() {
        return builder;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        localizationService = service;
    }

    private final SubreportBuilder createWeaponsSubreport() {
        final JasperReportBuilder report;

        report = DynamicReports.report();
        report.detail(DynamicReportsFactory.getInstance()
                .getBorderedCellComponentThin(getWeaponDetailComponent()));

        return Components.subreport(report);
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final SubreportBuilder getRulesSubreport() {
        final JasperReportBuilder report;
        final DRField<SpecialRule> field;

        field = new DRField<SpecialRule>(ReportConf.CURRENT, SpecialRule.class);
        field.setDataType(new SpecialRulesDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(getLocalizationService().getReportString(
                ReportBundleConf.RULES)));
        report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);

        return Components.subreport(report);
    }

    private final DRField<Weapon> getWeaponCombatField(final String fieldName) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(new WeaponCombatFormatter()));

        return field;
    }

    private final ComponentBuilder<?, ?> getWeaponDetailComponent() {
        final ComponentBuilder<?, ?> data;
        final ComponentBuilder<?, ?> attributes;
        final SubreportBuilder rules;
        final SubreportBuilder equipment;

        rules = getRulesSubreport();
        rules.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.SPECIAL_RULES));
        rules.setHeight(10);

        equipment = getWeaponEnhancementsSubreport();
        equipment.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPON_ENHANCEMENTS));
        equipment.setHeight(10);

        attributes = Components
                .verticalList(
                        Components.text(getWeaponNameField(ReportConf.CURRENT,
                                getLocalizationService())),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(getLocalizationService()
                                        .getReportString(
                                                ReportBundleConf.COMBAT)),
                                Components
                                        .text(getWeaponCombatField(ReportConf.CURRENT))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(getLocalizationService()
                                        .getReportString(
                                                ReportBundleConf.STRENGTH)),
                                Components
                                        .text(getWeaponStrengthField(ReportConf.CURRENT))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components.text(getLocalizationService()
                                        .getReportString(
                                                ReportBundleConf.PENETRATION)),
                                Components
                                        .text(getWeaponPenetrationField(ReportConf.CURRENT))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components
                                        .text(getLocalizationService()
                                                .getReportString(
                                                        ReportBundleConf.DISTANCE_METRIC)),
                                Components
                                        .text(getWeaponDistanceMetricField(ReportConf.CURRENT))),
                        Components.horizontalList(
                                Components.horizontalGap(10),
                                Components
                                        .text(getLocalizationService()
                                                .getReportString(
                                                        ReportBundleConf.DISTANCE_IMPERIAL)),
                                Components
                                        .text(getWeaponDistanceImperialField(ReportConf.CURRENT))));

        data = Components.horizontalList(
                attributes,
                DynamicReportsFactory.getInstance()
                        .getBorderedCellComponentThin(
                                Components.verticalList(
                                        rules,
                                        DynamicReportsFactory.getInstance()
                                                .getBorderedCellComponentThin(
                                                        equipment))));

        return data;
    }

    private final DRField<Weapon> getWeaponDistanceImperialField(
            final String fieldName) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(
                new WeaponDistanceImperialFormatter()));

        return field;
    }

    private final DRField<Weapon> getWeaponDistanceMetricField(
            final String fieldName) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(
                new WeaponDistanceMetricFormatter()));

        return field;
    }

    private final SubreportBuilder getWeaponEnhancementsSubreport() {
        final JasperReportBuilder report;
        final DRField<WeaponEnhancement> field;

        field = new DRField<WeaponEnhancement>(ReportConf.CURRENT,
                WeaponEnhancement.class);
        field.setDataType(new WeaponEnhancementDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(getLocalizationService().getReportString(
                ReportBundleConf.ENHANCEMENTS)));
        report.setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL);

        return Components.subreport(report);
    }

    private final DRField<Weapon> getWeaponNameField(final String fieldName,
            final LocalizationService service) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(new WeaponNameFormatter()));

        return field;
    }

    private final DRField<Weapon> getWeaponPenetrationField(
            final String fieldName) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(new WeaponPenetrationFormatter()));

        return field;
    }

    private final DRField<Weapon>
            getWeaponStrengthField(final String fieldName) {
        final DRField<Weapon> field;

        field = new DRField<Weapon>(fieldName, Weapon.class);
        field.setDataType(new WeaponDataType(new WeaponStrengthFormatter()));

        return field;
    }

}
