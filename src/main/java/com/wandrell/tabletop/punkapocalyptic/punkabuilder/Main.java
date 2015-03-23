package com.wandrell.tabletop.punkapocalyptic.punkabuilder;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import joptsimple.OptionException;
import joptsimple.OptionParser;
import joptsimple.OptionSet;

import org.apache.log4j.Logger;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.pattern.command.DefaultCommandExecutor;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.MainPaneController;
import com.wandrell.util.CommandLineUtils;
import com.wandrell.util.command.jopt.SetUpBasicOptionParserCommandsCommand;

public final class Main extends Application {

    public static final void main(final String[] args) {
        checkNotNull(args, "Received a null pointer as arguments");

        // Loads line commands
        if (parseCommandLine(args)) {
            launch(args);
        }
    }

    private static final Boolean parseCommandLine(final String[] args) {
        final OptionParser parser;
        final CommandExecutor executor;
        final Boolean accepted;
        OptionSet options;

        executor = new DefaultCommandExecutor();
        parser = new OptionParser();

        executor.execute(new SetUpBasicOptionParserCommandsCommand(parser));

        try {
            options = parser.parse(args);
        } catch (final OptionException ex) {
            options = parser.parse("");
            System.err.println(ex.getMessage());
        }

        // Checks if the help command was received
        if (CommandLineUtils.readHelpCommand(System.out, options, parser)) {
            accepted = false;
        } else {
            // Log file
            if (!CommandLineUtils.readLog4JFileCommand(Logger.getRootLogger(),
                    options)) {
                // Console logging
                CommandLineUtils.readLog4JConsoleLogCommand(
                        Logger.getRootLogger(), options);
            }

            accepted = true;
        }

        return accepted;
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
