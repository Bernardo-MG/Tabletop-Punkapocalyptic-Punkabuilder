package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.command;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportBundleConf;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.MutationDataType;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;

public final class BuildUnitMutationSubreportCommand implements
        ResultCommand<ComponentBuilder<?, ?>>, LocalizationServiceAware {

    private ComponentBuilder<?, ?> builder;
    private LocalizationService    localizationService;

    public BuildUnitMutationSubreportCommand() {
        super();
    }

    @Override
    public final void execute() {
        final SubreportBuilder subreport;

        subreport = getMutationSubreport();
        subreport.setDataSource(Expressions
                .subDatasourceBeanCollection(ReportConf.MUTATIONS));

        builder = DynamicReportsFactory.getInstance()
                .getBorderedCellComponentThin(subreport);
    }

    @Override
    public final ComponentBuilder<?, ?> getResult() {
        return builder;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        localizationService = service;
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final SubreportBuilder getMutationSubreport() {
        final JasperReportBuilder report;
        final DRField<Mutation> field;

        field = new DRField<Mutation>(ReportConf.CURRENT, Mutation.class);
        field.setDataType(new MutationDataType());

        report = DynamicReports.report();
        report.detail(Components.horizontalList(Components.horizontalGap(10),
                Components.verticalList(Components.text(field))));
        report.title(Components.text(getLocalizationService().getReportString(
                ReportBundleConf.MUTATIONS)));

        return Components.subreport(report);
    }

}
