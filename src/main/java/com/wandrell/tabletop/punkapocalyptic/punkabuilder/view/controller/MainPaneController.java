package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.service.FileService;

@Component
public final class MainPaneController {

    private Stage                    aboutDialog;
    private final GangBuilderManager gangBuilderManager;
    @FXML
    private MenuItem                 printPDFItem;
    private BorderPane               rootLayout;
    private final FileService        serviceFile;
    private Stage                    stage;

    @Autowired
    public MainPaneController(final FileService fileService,
            final GangBuilderManager gangBuilderManager) {
        super();

        checkNotNull(fileService, "Received a null pointer as file service");
        checkNotNull(gangBuilderManager,
                "Received a null pointer as controller");

        serviceFile = fileService;
        this.gangBuilderManager = gangBuilderManager;
    }

    @FXML
    public final void handleAboutAction(final ActionEvent event) {
        getAboutDialog().showAndWait();
    }

    @FXML
    public final void handleExitAction(final ActionEvent event) {
        System.exit(0);
    }

    @FXML
    public final void handleNewGangAction(final ActionEvent event) {
        final ContextFactory factory;

        factory = ContextFactory.getInstance();

        setShownPane(factory.getFactionSelectionPane());
    }

    @FXML
    public final void handlePrintPDFAction(final ActionEvent event) {
        final File file;

        file = getFileService().getFileToPrintGangOnDesktop(getStage());

        if (file != null) {
            getFileService().saveGang(getGangBuilderManager().getGang(), file);
        }
    }

    @FXML
    public final void initialize() {
        getPrintPDFItem().setDisable(true);
    }

    @Autowired
    public final void setAboutDialog(
            @Qualifier("aboutDialog") final Stage dialog) {
        checkNotNull(dialog, "Received a null pointer as dialog");

        aboutDialog = dialog;
    }

    @Autowired
    public final void setRootLayout(@Qualifier("mainPane") final Object root) {
        rootLayout = (BorderPane) root;
    }

    public final void setSaveAllowed(final Boolean save) {
        getPrintPDFItem().setDisable(!save);
    }

    public final void setShownPane(final Pane pane) {
        getRootLayout().setCenter(pane);
    }

    public final void setStage(final Stage stage) {
        this.stage = stage;
    }

    private final Stage getAboutDialog() {
        return aboutDialog;
    }

    private final FileService getFileService() {
        return serviceFile;
    }

    private final GangBuilderManager getGangBuilderManager() {
        return gangBuilderManager;
    }

    private final MenuItem getPrintPDFItem() {
        return printPDFItem;
    }

    private final BorderPane getRootLayout() {
        return rootLayout;
    }

    private final Stage getStage() {
        return stage;
    }

}
