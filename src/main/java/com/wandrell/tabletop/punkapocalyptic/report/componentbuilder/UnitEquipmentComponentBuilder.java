package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.EquipmentField;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.ListReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitEquipmentComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = 5042615567519754602L;

    public UnitEquipmentComponentBuilder(
            final LocalizationService localizationService) {
        super();
        final SubreportBuilder subreport;
        final JasperReportBuilder report;
        final DRField<Equipment> field;

        field = new EquipmentField(ReportConf.CURRENT);

        report = new ListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.EQUIPMENT));

        setStyle(Styles.style(Styles.pen1Point()));

        subreport = Components.subreport(report);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.EQUIPMENT));

        add(Components.horizontalList(subreport));
    }

}
