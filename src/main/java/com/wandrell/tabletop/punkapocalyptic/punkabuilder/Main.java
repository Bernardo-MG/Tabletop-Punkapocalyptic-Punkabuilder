package com.wandrell.tabletop.punkapocalyptic.punkabuilder;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.MainPaneController;

public final class Main extends Application {

    public static final void main(final String[] args) {
        launch(args);
    }

    @Override
    public final void start(final Stage primaryStage) throws IOException {
        final String title;
        final String version;

        title = ContextFactory.getInstance().getApplicationInfoService()
                .getApplicationName();
        version = ContextFactory.getInstance().getApplicationInfoService()
                .getVersion();
        primaryStage.setTitle(String.format("%s - v%s", title, version));

        initRootLayout(primaryStage);
    }

    private final void anchorDialogs(final Stage primaryStage) {
        final ContextFactory factory;

        factory = ContextFactory.getInstance();

        factory.getSetUpUnitDialog().initOwner(primaryStage);
        factory.getAboutDialog().initOwner(primaryStage);
    }

    private final void initRootLayout(final Stage primaryStage)
            throws IOException {
        final MainPaneController controller;
        final ContextFactory factory;

        factory = ContextFactory.getInstance();

        controller = factory.getMainFrameController();
        controller.setStage(primaryStage);

        anchorDialogs(primaryStage);

        loadFactionSelectionOverview(controller);

        // Show the scene containing the root layout.
        primaryStage.setScene(new Scene(factory.getMainPane()));
        primaryStage.show();
    }

    private final void loadFactionSelectionOverview(
            final MainPaneController controller) throws IOException {
        final Pane pane;
        final ContextFactory factory;

        factory = ContextFactory.getInstance();

        factory.getFactionSelectionController().loadFactions();

        pane = factory.getFactionSelectionPane();

        controller.setShownPane(pane);
    }

}
