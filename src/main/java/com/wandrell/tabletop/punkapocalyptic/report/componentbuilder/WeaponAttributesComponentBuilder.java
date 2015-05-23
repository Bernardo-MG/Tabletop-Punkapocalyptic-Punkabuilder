package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;

import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.UnitWeaponField;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponCombatFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceImperialFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponDistanceMetricFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponNameFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponPenetrationFormatter;
import com.wandrell.tabletop.punkapocalyptic.report.formatter.WeaponStrengthFormatter;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class WeaponAttributesComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = 3476746055047563476L;

    public WeaponAttributesComponentBuilder(
            final LocalizationService localizationService) {
        super();

        add(Components.text(new UnitWeaponField(ReportConf.CURRENT,
                new WeaponNameFormatter())));
        add(Components.horizontalList(Components.horizontalGap(10), Components
                .text(localizationService
                        .getReportString(ReportBundleConf.COMBAT)), Components
                .text(new UnitWeaponField(ReportConf.CURRENT,
                        new WeaponCombatFormatter()))));
        add(Components.horizontalList(Components.horizontalGap(10), Components
                .text(localizationService
                        .getReportString(ReportBundleConf.STRENGTH)),
                Components.text(new UnitWeaponField(ReportConf.CURRENT,
                        new WeaponStrengthFormatter()))));
        add(Components.horizontalList(Components.horizontalGap(10), Components
                .text(localizationService
                        .getReportString(ReportBundleConf.PENETRATION)),
                Components.text(new UnitWeaponField(ReportConf.CURRENT,
                        new WeaponPenetrationFormatter()))));
        add(Components.horizontalList(Components.horizontalGap(10), Components
                .text(localizationService
                        .getReportString(ReportBundleConf.DISTANCE_METRIC)),
                Components.text(new UnitWeaponField(ReportConf.CURRENT,
                        new WeaponDistanceMetricFormatter()))));
        add(Components.horizontalList(Components.horizontalGap(10), Components
                .text(localizationService
                        .getReportString(ReportBundleConf.DISTANCE_IMPERIAL)),
                Components.text(new UnitWeaponField(ReportConf.CURRENT,
                        new WeaponDistanceImperialFormatter()))));
    }

}
