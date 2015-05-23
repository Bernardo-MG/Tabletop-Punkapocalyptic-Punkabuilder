package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.HorizontalListBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;

import com.wandrell.tabletop.punkapocalyptic.report.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class AttributesComponentBuilder extends HorizontalListBuilder {

    private static final long serialVersionUID = 3476746055047563476L;

    public AttributesComponentBuilder(
            final LocalizationService localizationService) {
        super();

        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        add(factory.getBorderedCellComponentThin(Components.text(
                localizationService.getReportString(ReportBundleConf.ACTIONS))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                localizationService.getReportString(ReportBundleConf.COMBAT))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components
                .text(localizationService
                        .getReportString(ReportBundleConf.PRECISION))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                localizationService.getReportString(ReportBundleConf.AGILITY))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                localizationService.getReportString(ReportBundleConf.STRENGTH))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components
                .text(localizationService
                        .getReportString(ReportBundleConf.TOUGHNESS))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                localizationService.getReportString(ReportBundleConf.TECH))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));

        newRow();
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.ACTIONS))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.COMBAT))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.PRECISION))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.AGILITY))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.STRENGTH))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.TOUGHNESS))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
        add(factory.getBorderedCellComponentThin(Components.text(
                factory.getIntegerField(ReportConf.TECH))
                .setHorizontalAlignment(HorizontalAlignment.CENTER)));
    }

}
