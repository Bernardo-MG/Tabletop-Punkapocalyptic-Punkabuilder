package com.wandrell.tabletop.punkapocalyptic.report.reportbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;

import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.componentbuilder.WeaponDetailComponentBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class WeaponReportBuilder extends JasperReportBuilder {

    private static final long serialVersionUID = -5729685974869554424L;

    public WeaponReportBuilder(final LocalizationService localizationService) {
        super();
        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        detail(factory
                .getBorderedCellComponentThin(new WeaponDetailComponentBuilder(
                        localizationService)));
    }

}
