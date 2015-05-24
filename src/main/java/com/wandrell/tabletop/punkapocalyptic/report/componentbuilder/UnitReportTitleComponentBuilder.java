package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.UnitField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.GroupedUnitNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.UnitValorationFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitReportTitleComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = 6516766875180439907L;

    public UnitReportTitleComponentBuilder(
            final LocalizationService localizationService) {
        super();
        final ComponentBuilder<?, ?> unitName;
        final ComponentBuilder<?, ?> unitValoration;

        // Unit valoration fields
        unitValoration = Components.text(new UnitField(ReportConf.CURRENT,
                new UnitValorationFormatter(localizationService)));

        // Unit name
        unitName = Components.text(new UnitField(ReportConf.CURRENT,
                new GroupedUnitNameFormatter()));

        add(Components.horizontalList(Components.verticalList(unitName,
                unitValoration)));
    }

}
