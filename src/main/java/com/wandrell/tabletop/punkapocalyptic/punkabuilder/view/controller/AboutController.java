package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

import com.wandrell.util.service.application.ApplicationInfoService;

public final class AboutController {

    @FXML
    private Label                  appNameLabel;
    private ApplicationInfoService appService;
    @FXML
    private Label                  authorEmailLabel;
    @FXML
    private Label                  authorNameLabel;
    @FXML
    private Hyperlink              downloadLabel;
    @FXML
    private Label                  versionLabel;

    public AboutController(final ApplicationInfoService service) {
        super();

        appService = service;
    }

    @FXML
    public final void handleDownloadURL() {
        try {
            Desktop.getDesktop().browse(new URI(downloadLabel.getText()));
        } catch (final IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public final void initialize() {
        appNameLabel.setText(getApplicationInfoService().getApplicationName());
        versionLabel.setText(getApplicationInfoService().getVersion());
        authorNameLabel.setText(getApplicationInfoService().getAuthors()
                .iterator().next().getName());
        authorEmailLabel.setText(getApplicationInfoService().getAuthors()
                .iterator().next().getEmail());
        downloadLabel.setText(getApplicationInfoService().getProjectURI()
                .toString());
    }

    private final ApplicationInfoService getApplicationInfoService() {
        return appService;
    }

}
