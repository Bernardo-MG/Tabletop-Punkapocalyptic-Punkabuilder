package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.builder.component.TextFieldBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.ArmorOptionField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorOptionArmorFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.ArmorOptionNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class ArmorComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = 19804920123105664L;

    public ArmorComponentBuilder(final LocalizationService localizationService) {
        super();
        final TextFieldBuilder<String> armorNameLabelText;
        final TextFieldBuilder<ArmorOption> armorNameText;
        final TextFieldBuilder<ArmorOption> armorArmorText;
        final HorizontalListBuilder list;

        setStyle(Styles.style(Styles.pen1Point()));

        armorNameLabelText = Components.text(localizationService
                .getReportString(ReportBundleConf.ARMOR_NAME));
        armorNameText = Components.text(new ArmorOptionField(ReportConf.ARMOR,
                new ArmorOptionNameFormatter()));

        armorArmorText = Components.text(new ArmorOptionField(ReportConf.ARMOR,
                new ArmorOptionArmorFormatter(localizationService)));

        list = Components.horizontalList();
        list.add(armorNameLabelText);
        list.newRow();
        list.add(Components.horizontalList(Components.horizontalGap(10),
                armorNameText, armorArmorText).setFixedWidth(300));

        add(Components.horizontalList(list));
    }

}
