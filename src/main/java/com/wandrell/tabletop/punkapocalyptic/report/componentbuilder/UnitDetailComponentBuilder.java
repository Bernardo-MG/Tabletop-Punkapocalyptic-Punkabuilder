package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitDetailComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = -6081722811681882602L;

    public UnitDetailComponentBuilder(
            final LocalizationService localizationService) {
        super();

        add(new AttributesComponentBuilder(localizationService));
        add(new ArmorComponentBuilder(localizationService));
        add(new UnitWeaponsComponentBuilder(localizationService));
        add(new UnitEquipmentComponentBuilder(localizationService));
        add(new UnitRulesComponentBuilder(localizationService));
        add(new UnitMutationsComponentBuilder(localizationService));
    }

}
