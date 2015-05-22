package com.wandrell.tabletop.punkapocalyptic.report;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.EquipmentDataType;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.MutationDataType;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.SpecialRulesDataType;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.report.field.ArmorOptionField;
import com.wandrell.tabletop.punkapocalyptic.report.field.UnitField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorOptionArmorFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorOptionNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GroupedUnitNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.UnitValorationFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitFactory {

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

        field = new DRField<Equipment>(ReportConf.CURRENT, Equipment.class);
        field.setDataType(new EquipmentDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(localizationService
                .getReportString(ReportBundleConf.EQUIPMENT)));

        return Components.subreport(report);
    }

    private static final SubreportBuilder getMutationSubreport(
            final LocalizationService localizationService) {
        final JasperReportBuilder report;
        final DRField<Mutation> field;

        field = new DRField<Mutation>(ReportConf.CURRENT, Mutation.class);
        field.setDataType(new MutationDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(localizationService
                .getReportString(ReportBundleConf.MUTATIONS)));

        return Components.subreport(report);
    }

    private static final SubreportBuilder getRulesSubreportForUnit(
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

        return Components.subreport(report);
    }

    private static final ComponentBuilder<?, ?> getUnitArmorSubreport(
            final LocalizationService localizationService) {
        final TextFieldBuilder<String> armorNameLabelText;
        final TextFieldBuilder<ArmorOption> armorNameText;
        final TextFieldBuilder<ArmorOption> armorArmorText;

        armorNameLabelText = Components.text(localizationService
                .getReportString(ReportBundleConf.ARMOR_NAME));
        armorNameText = Components.text(new ArmorOptionField(ReportConf.ARMOR,
                new ArmorOptionNameFormatter()));

        armorArmorText = Components.text(new ArmorOptionField(ReportConf.ARMOR,
                new ArmorOptionArmorFormatter(localizationService)));

        return getDynamicReportsFactory().getBorderedCellComponentThin(
                Components
                        .horizontalList(armorNameLabelText)
                        .newRow()
                        .add(Components.horizontalList(
                                Components.horizontalGap(10), armorNameText,
                                armorArmorText).setFixedWidth(300)));
    }

    private static final ComponentBuilder<?, ?> getUnitAttributesSubreport(
            final LocalizationService localizationService) {
        final HorizontalListBuilder list;

        list = Components.horizontalList();
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.ACTIONS))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.COMBAT))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.PRECISION))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.AGILITY))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.STRENGTH))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.TOUGHNESS))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        localizationService
                                .getReportString(ReportBundleConf.TECH))
                        .setHorizontalAlignment(HorizontalAlignment.CENTER)));

        list.newRow();
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.ACTIONS)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.COMBAT)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.PRECISION)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.AGILITY)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.STRENGTH)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.TOUGHNESS)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));
        list.add(getDynamicReportsFactory().getBorderedCellComponentThin(
                Components.text(
                        getDynamicReportsFactory().getIntegerField(
                                ReportConf.TECH)).setHorizontalAlignment(
                        HorizontalAlignment.CENTER)));

        return list;
    }

    private static final ComponentBuilder<?, ?> getUnitDetailComponent(
            final LocalizationService localizationService) {
        final ComponentBuilder<?, ?> rules;
        final ComponentBuilder<?, ?> attributes;
        final ComponentBuilder<?, ?> armor;
        final ComponentBuilder<?, ?> equipment;
        final ComponentBuilder<?, ?> weapons;
        final ComponentBuilder<?, ?> mutations;

        attributes = getUnitAttributesSubreport(localizationService);
        armor = getUnitArmorSubreport(localizationService);
        weapons = WeaponFactory.getUnitWeaponSubreport(localizationService);
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

    public UnitFactory() {
        super();
    }

}
