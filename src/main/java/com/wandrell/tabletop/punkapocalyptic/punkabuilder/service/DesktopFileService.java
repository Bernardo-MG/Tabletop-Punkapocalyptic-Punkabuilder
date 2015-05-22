package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Properties;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.exception.DRException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.report.ReportFactory;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.util.service.application.ApplicationInfoService;

@Service("fileService")
public final class DesktopFileService implements FileService {

    private static final String          KEY_TITLE_IMAGE = "image.title";
    private final ApplicationInfoService appInfoService;
    private final Properties             fileProp;
    private final LocalizationService    localizationService;
    private final RulesetService         rulesetService;

    @Autowired
    public DesktopFileService(final Properties fileConfig,
            final ApplicationInfoService appInfoService,
            final LocalizationService localizationService,
            final RulesetService rulesetService) {
        super();

        checkNotNull(fileConfig, "Received a null pointer as file properties");
        checkNotNull(localizationService,
                "Received a null pointer as localization service");
        checkNotNull(appInfoService,
                "Received a null pointer as application info service");
        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");

        this.fileProp = fileConfig;
        this.localizationService = localizationService;
        this.appInfoService = appInfoService;
        this.rulesetService = rulesetService;
    }

    @Override
    public final File getFileToPrintGangOnDesktop(final Stage stage) {
        final FileChooser fileChooser;
        final ExtensionFilter extFilter;

        fileChooser = new FileChooser();

        fileChooser.setTitle(getLocalizationService().getViewString(
                "pick.save_file"));

        extFilter = new FileChooser.ExtensionFilter("PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        return fileChooser.showSaveDialog(stage);
    }

    @Override
    public final String getTitleImagePath() {
        return getFileProperties().getProperty(KEY_TITLE_IMAGE);
    }

    @Override
    public final void saveGang(final Gang gang, final File file) {
        final Collection<Gang> gangs;
        final JasperReportBuilder report;

        final OutputStream stream;

        checkNotNull(gang, "Received a null pointer as gang");
        checkNotNull(file, "Received a null pointer as file");

        report = ReportFactory.getGangReport(getApplicationInfoService()
                .getApplicationName(),
                getApplicationInfoService().getVersion(),
                getApplicationInfoService().getProjectURI().toString(),
                getTitleImagePath(), getLocalizationService(),
                getRulesetService());

        gangs = new LinkedList<>();
        gangs.add(gang);
        report.setDataSource(gangs);

        try {
            stream = new BufferedOutputStream(new FileOutputStream(file));
        } catch (final FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            report.toPdf(stream);
        } catch (final DRException e) {
            throw new RuntimeException(e);
        }

        try {
            stream.close();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    private final ApplicationInfoService getApplicationInfoService() {
        return appInfoService;
    }

    private final Properties getFileProperties() {
        return fileProp;
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final RulesetService getRulesetService() {
        return rulesetService;
    }

}
