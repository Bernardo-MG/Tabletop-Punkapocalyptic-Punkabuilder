package com.wandrell.tabletop.punkapocalyptic.report.componentbuilder;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.component.VerticalListBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.report.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.report.field.SpecialRuleField;
import com.wandrell.tabletop.punkapocalyptic.report.reportbuilder.ListReportBuilder;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;

public final class UnitRulesComponentBuilder extends VerticalListBuilder {

    private static final long serialVersionUID = -2294628607384279373L;

    public UnitRulesComponentBuilder(
            final LocalizationService localizationService) {
        super();
        final SubreportBuilder subreport;
        final JasperReportBuilder report;
        final DRField<SpecialRule> field;

        field = new SpecialRuleField(ReportConf.CURRENT);

        report = new ListReportBuilder(field,
                localizationService.getReportString(ReportBundleConf.RULES));

        setStyle(Styles.style(Styles.pen1Point()));

        subreport = Components.subreport(report);
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.SPECIAL_RULES));

        add(Components.horizontalList(subreport));
    }

}
