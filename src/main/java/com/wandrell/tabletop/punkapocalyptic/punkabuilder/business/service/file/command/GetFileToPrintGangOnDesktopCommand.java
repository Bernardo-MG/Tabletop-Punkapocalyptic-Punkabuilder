package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.file.command;

import java.io.File;

import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;

public final class GetFileToPrintGangOnDesktopCommand implements
        ReturnCommand<File>, LocalizationServiceAware {

    private File                file;
    private LocalizationService localizationService;
    private final Stage         stage;

    public GetFileToPrintGangOnDesktopCommand(final Stage stage) {
        super();

        this.stage = stage;
    }

    @Override
    public final void execute() {
        final FileChooser fileChooser;
        final ExtensionFilter extFilter;

        fileChooser = new FileChooser();

        fileChooser.setTitle(getLocalizationService().getViewString(
                "pick.save_file"));

        extFilter = new FileChooser.ExtensionFilter("PDF (*.pdf)", "*.pdf");
        fileChooser.getExtensionFilters().add(extFilter);

        file = fileChooser.showSaveDialog(getStage());
    }

    @Override
    public final File getResult() {
        return file;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        localizationService = service;
    }

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final Stage getStage() {
        return stage;
    }

}
