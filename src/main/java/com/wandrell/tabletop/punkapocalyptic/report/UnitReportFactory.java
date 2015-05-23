package com.wandrell.tabletop.punkapocalyptic.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.ArmorComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.AttributesComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.report.field.EquipmentField;
import com.wandrell.tabletop.punkapocalyptic.report.field.MutationField;
import com.wandrell.tabletop.punkapocalyptic.report.field.SpecialRuleField;
import com.wandrell.tabletop.punkapocalyptic.report.field.UnitField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GroupedUnitNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.UnitValorationFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.ListReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitReportFactory {

    private static final DynamicReportsFactory factory = DynamicReportsFactory
                                                               .getInstance();

    public static final SubreportBuilder getUnitsSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder unitsReport;
        final JasperReportBuilder unitReport;
        final SubreportBuilder subreport;
        final ComponentBuilder<?, ?> unitTitle;

        unitReport = DynamicReports.report();

        unitTitle = getUnitReportTitle(localizationService);

        unitReport.setTemplate(getDynamicReportsFactory().getReportTemplate());
        unitReport.title(unitTitle);
        unitReport.detail(getUnitDetailComponent(localizationService));
        unitReport.detailFooter(Components.verticalGap(20));

        unitReport.setTitleStyle(Styles.style()
                .setTopBorder(Styles.pen2Point()));
        unitReport.setDetailStyle(Styles.style().setBottomBorder(
                Styles.pen2Point()));

        unitsReport = DynamicReports.report();
        unitsReport.setTemplate(getDynamicReportsFactory().getReportTemplate());

        subreport = Components.subreport(unitReport).setDataSource(
                new CurrentObjectDatasourceExpression(ReportConf.CURRENT));

        unitsReport.addDetail(subreport);

        return Components.subreport(unitsReport).setDataSource(
                Expressions.subDatasourceBeanCollection(ReportConf.UNITS));
    }

    private static final DynamicReportsFactory getDynamicReportsFactory() {
        return factory;
    }

    private static final SubreportBuilder getEquipmentSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<Equipment> field;

        field = new EquipmentField(ReportConf.CURRENT);

        report = new ListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.EQUIPMENT));

        return Components.subreport(report);
    }

    private static final SubreportBuilder getMutationSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<Mutation> field;

        field = new MutationField(ReportConf.CURRENT);

        report = new ListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.MUTATIONS));

        return Components.subreport(report);
    }

    private static final SubreportBuilder getRulesSubreportForUnit(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<SpecialRule> field;

        field = new SpecialRuleField(ReportConf.CURRENT);

        report = new ListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.RULES));

        return Components.subreport(report);
    }

    private static final ComponentBuilder<?, ?> getUnitDetailComponent(
            final LocalizationService localizationService) {
        final ComponentBuilder<?, ?> rules;
        final ComponentBuilder<?, ?> attributes;
        final ComponentBuilder<?, ?> armor;
        final ComponentBuilder<?, ?> equipment;
        final ComponentBuilder<?, ?> weapons;
        final ComponentBuilder<?, ?> mutations;

        attributes = new AttributesComponentBuilder(localizationService);
        armor = new ArmorComponentBuilder(localizationService);
        weapons = WeaponReportFactory
                .getUnitWeaponSubreport(localizationService);
        equipment = getUnitEquipmentSubreport(localizationService);
        rules = getUnitRulesSubreport(localizationService);
        mutations = getUnitMutationSubreport(localizationService);

        return Components.verticalList(attributes, armor, weapons, equipment,
                rules, mutations);
    }

    private static final ComponentBuilder<?, ?> getUnitEquipmentSubreport(
            final LocalizationService localizationService) {
        final SubreportBuilder subreport;

        subreport = getEquipmentSubreport(localizationService);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.EQUIPMENT));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                subreport);
    }

    private static final ComponentBuilder<?, ?> getUnitMutationSubreport(
            final LocalizationService localizationService) {
        final SubreportBuilder subreport;

        subreport = getMutationSubreport(localizationService);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.MUTATIONS));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                subreport);
    }

    private static final ComponentBuilder<?, ?> getUnitReportTitle(
            final LocalizationService localizationService) {
        final ComponentBuilder<?, ?> unitName;
        final ComponentBuilder<?, ?> unitValoration;

        // Unit valoration fields
        unitValoration = Components.text(new UnitField(ReportConf.CURRENT,
                new UnitValorationFormatter(localizationService)));

        // Unit name
        unitName = Components.text(new UnitField(ReportConf.CURRENT,
                new GroupedUnitNameFormatter()));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.verticalList(unitName, unitValoration));
    }

    private static final ComponentBuilder<?, ?> getUnitRulesSubreport(
            final LocalizationService localizationService) {
        final SubreportBuilder subreport;

        subreport = getRulesSubreportForUnit(localizationService);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.SPECIAL_RULES));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                subreport);
    }

    public UnitReportFactory() {
        super();
    }

}
