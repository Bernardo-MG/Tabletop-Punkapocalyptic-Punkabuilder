package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.WeaponReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitWeaponsComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = -1678462161590246435L;

    public UnitWeaponsComponentBuilder(
            final LocalizationService localizationService) {
        super();
        final SubreportBuilder subreport;

        setStyle(Styles.style(Styles.pen1Point()));

        subreport = Components.subreport(new WeaponReportBuilder(
                localizationService));
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.WEAPONS));

        add(Components.horizontalList(subreport));
    }

}
