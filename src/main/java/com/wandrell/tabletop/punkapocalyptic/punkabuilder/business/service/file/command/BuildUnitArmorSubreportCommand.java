package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.command;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.ArmorDataType;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorArmorFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;

public final class BuildUnitArmorSubreportCommand implements
        ResultCommand<ComponentBuilder<?, ?>>, LocalizationServiceAware {

    private ComponentBuilder<?, ?> builder;
    private LocalizationService    localizationService;

    public BuildUnitArmorSubreportCommand() {
        super();
    }

    @Override
    public final void execute() {
        final TextFieldBuilder<String> armorNameLabelText;
        final TextFieldBuilder<Armor> armorNameText;
        final TextFieldBuilder<Armor> armorArmorText;

        armorNameLabelText = Components.text(getLocalizationService()
                .getReportString(ReportBundleConf.ARMOR_NAME));
        armorNameText = Components.text(getArmorNameField(ReportConf.ARMOR));

        armorArmorText = Components.text(getArmorArmorField(ReportConf.ARMOR));

        builder = DynamicReportsFactory.getInstance()
                .getBorderedCellComponentThin(
                        Components
                                .horizontalList(armorNameLabelText)
                                .newRow()
                                .add(Components.horizontalList(
                                        Components.horizontalGap(10),
                                        armorNameText, armorArmorText)
                                        .setFixedWidth(300)));
    }

    @Override
    public final ComponentBuilder<?, ?> getResult() {
        return builder;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        localizationService = service;
    }

    private final DRField<Armor> getArmorArmorField(final String fieldName) {
        final DRField<Armor> field;

        field = new DRField<Armor>(fieldName, Armor.class);
        field.setDataType(new ArmorDataType(new ArmorArmorFormatter(
                getLocalizationService())));

        return field;
    }

    private final DRField<Armor> getArmorNameField(final String fieldName) {
        final DRField<Armor> field;

        field = new DRField<Armor>(fieldName, Armor.class);
        field.setDataType(new ArmorDataType(new ArmorNameFormatter()));

        return field;
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

}
