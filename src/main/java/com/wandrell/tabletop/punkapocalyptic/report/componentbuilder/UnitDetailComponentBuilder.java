package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitDetailComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = -6081722811681882602L;

    public UnitDetailComponentBuilder(
            final LocalizationService localizationService) {
        super();

        add(Components.subreport(
                DynamicReports.report().detail(
                        new AttributesComponentBuilder(localizationService)))
                .setDataSource(
                        new CurrentObjectDatasourceExpression(
                                ReportConf.ATTRIBUTES)));
        add(new ArmorComponentBuilder(localizationService));
        add(new UnitWeaponsComponentBuilder(localizationService));
        add(new UnitEquipmentComponentBuilder(localizationService));
        add(Components.subreport(
                DynamicReports.report().detail(
                        new UnitRulesComponentBuilder(localizationService)))
                .setDataSource(
                        new CurrentObjectDatasourceExpression(
                                ReportConf.UNIT_TEMPLATE)));
        add(new UnitMutationsComponentBuilder(localizationService));
    }

}
