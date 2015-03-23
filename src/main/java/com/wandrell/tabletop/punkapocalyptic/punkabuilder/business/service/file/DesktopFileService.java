package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import javafx.stage.Stage;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.builder.component.Components;
import net.sf.dynamicreports.report.builder.component.SubreportBuilder;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.Styles;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.punkapocalyptic.conf.ReportConf;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.DynamicReportsFactory;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.report.expression.CurrentObjectDatasourceExpression;
import com.wandrell.tabletop.punkapocalyptic.service.FileService;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildGangReportTitleCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildRulesSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitArmorSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitAttributesSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitEquipmentSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitMutationSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitReportTitleCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.BuildUnitWeaponSubreportCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.GetFileToPrintGangOnDesktopCommand;
import com.wandrell.tabletop.punkapocalyptic.service.file.command.PrintDynamicReportToPDF;

public final class DesktopFileService implements FileService {

    private static final String   KEY_TITLE_IMAGE = "image.title";
    private final CommandExecutor executor;
    private final Properties      fileProp;

    public DesktopFileService(final CommandExecutor executor,
            final Properties fileProp) {
        super();

        checkNotNull(executor, "Received a null pointer as executor");
        checkNotNull(fileProp, "Received a null pointer as file properties");

        this.executor = executor;
        this.fileProp = fileProp;
    }

    @Override
    public final File getFileToPrintGangOnDesktop(final Stage stage) {
        return getExecutor().execute(
                new GetFileToPrintGangOnDesktopCommand(stage));
    }

    @Override
    public final String getTitleImagePath() {
        return getFileProperties().getProperty(KEY_TITLE_IMAGE);
    }

    @Override
    public final void saveGang(final Gang gang, final File file) {
        final Collection<Gang> gangs;
        final JasperReportBuilder report;

        checkNotNull(gang, "Received a null pointer as gang");
        checkNotNull(file, "Received a null pointer as file");

        report = getReport();

        gangs = new LinkedList<>();
        gangs.add(gang);
        report.setDataSource(gangs);

        getExecutor().execute(new PrintDynamicReportToPDF(report, file));
    }

    private final JasperReportBuilder buildReport() {
        final JasperReportBuilder report;
        final ComponentBuilder<?, ?> title;
        final ComponentBuilder<?, ?> footer;
        final SubreportBuilder subreport;
        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        report = DynamicReports.report();
        report.setTemplate(factory.getReportTemplate());

        title = getExecutor().execute(new BuildGangReportTitleCommand());
        subreport = getUnitsSubreport();
        footer = factory.getReportFooter();

        report.title(title);
        report.detailFooter(subreport);
        report.pageFooter(footer);

        return report;
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }

    private final Properties getFileProperties() {
        return fileProp;
    }

    private final JasperReportBuilder getReport() {
        return buildReport();
    }

    private final ComponentBuilder<?, ?> getUnitDetailComponent() {
        final ComponentBuilder<?, ?> rules;
        final ComponentBuilder<?, ?> attributes;
        final ComponentBuilder<?, ?> armor;
        final ComponentBuilder<?, ?> equipment;
        final ComponentBuilder<?, ?> weapons;
        final ComponentBuilder<?, ?> mutations;

        attributes = getExecutor().execute(
                new BuildUnitAttributesSubreportCommand());
        armor = getExecutor().execute(new BuildUnitArmorSubreportCommand());
        weapons = getExecutor().execute(new BuildUnitWeaponSubreportCommand());
        equipment = getExecutor().execute(
                new BuildUnitEquipmentSubreportCommand());
        rules = getExecutor().execute(new BuildRulesSubreportCommand());
        mutations = getExecutor().execute(
                new BuildUnitMutationSubreportCommand());

        return Components.verticalList(attributes, armor, weapons, equipment,
                rules, mutations);
    }

    private final SubreportBuilder getUnitsSubreport() {
        final JasperReportBuilder unitsReport;
        final JasperReportBuilder unitReport;
        final SubreportBuilder subreport;
        final ComponentBuilder<?, ?> unitTitle;
        final DynamicReportsFactory factory;

        factory = DynamicReportsFactory.getInstance();

        unitReport = DynamicReports.report();

        unitTitle = getExecutor().execute(new BuildUnitReportTitleCommand());

        unitReport.setTemplate(factory.getReportTemplate());
        unitReport.title(unitTitle);
        unitReport.detail(getUnitDetailComponent());
        unitReport.detailFooter(Components.verticalGap(20));

        unitReport.setTitleStyle(Styles.style()
                .setTopBorder(Styles.pen2Point()));
        unitReport.setDetailStyle(Styles.style().setBottomBorder(
                Styles.pen2Point()));

        unitsReport = DynamicReports.report();
        unitsReport.setTemplate(factory.getReportTemplate());

        subreport = Components.subreport(unitReport).setDataSource(
                new CurrentObjectDatasourceExpression(ReportConf.CURRENT));

        unitsReport.addDetail(subreport);

        return Components.subreport(unitsReport).setDataSource(
                Expressions.subDatasourceBeanCollection(ReportConf.UNITS));
    }

}
