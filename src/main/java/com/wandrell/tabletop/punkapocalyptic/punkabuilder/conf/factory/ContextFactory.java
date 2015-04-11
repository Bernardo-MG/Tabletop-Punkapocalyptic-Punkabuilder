package com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory;

import java.util.Properties;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;

import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.ContextConf;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.FactionSelectionController;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.MainPaneController;
import com.wandrell.util.FileUtils;
import com.wandrell.util.ResourceUtils;
import com.wandrell.util.SpringUtils;
import com.wandrell.util.service.application.ApplicationInfoService;

public final class ContextFactory {

    private static final ContextFactory INSTANCE = new ContextFactory();
    private final ApplicationContext    context;

    public static final ContextFactory getInstance() {
        return INSTANCE;
    }

    private ContextFactory() {
        super();
        final Properties config;

        config = FileUtils.getProperties(ResourceUtils
                .getClassPathInputStream(ContextConf.PROPERTIES));

        context = SpringUtils.getClassPathContext(config,
                ContextConf.FILE_COMMAND, ContextConf.FILE_DATA,
                ContextConf.FILE_SERVICE, ContextConf.FILE_REPOSITORY,
                ContextConf.FILE_PROCEDURE, ContextConf.FILE_CONTROLLER,
                ContextConf.FILE_LOCALIZATION, ContextConf.FILE_VIEW);
    }

    public final Stage getAboutDialog() {
        return (Stage) getContext().getBean(ContextConf.VIEW_ABOUT_DIALOG);
    }

    public final ApplicationInfoService getApplicationInfoService() {
        return (ApplicationInfoService) getContext().getBean(
                ContextConf.SERVICE_APPLICATION);
    }

    public final FactionSelectionController getFactionSelectionController() {
        return (FactionSelectionController) getContext().getBean(
                ContextConf.CONTROLLER_FACTION);
    }

    public final Pane getFactionSelectionPane() {
        return (Pane) getContext().getBean(
                ContextConf.VIEW_FACTION_SELECTION_PANE);
    }

    public final GangBuilderManager getGangBuilderManager() {
        return (GangBuilderManager) getContext().getBean(
                ContextConf.MANAGER_GANG_BUILDER);
    }

    public final Pane getGangCreationPane() {
        return (Pane) getContext().getBean(ContextConf.VIEW_GANG_CREATION_PANE);
    }

    public final MainPaneController getMainFrameController() {
        return (MainPaneController) getContext().getBean(
                ContextConf.CONTROLLER_MAIN);
    }

    public final Pane getMainPane() {
        return (Pane) getContext().getBean(ContextConf.VIEW_MAIN);
    }

    public final Stage getSetUpUnitDialog() {
        return (Stage) getContext()
                .getBean(ContextConf.VIEW_SET_UP_UNIT_DIALOG);
    }

    public final FXMLLoader getSetUpWeaponLoader() {
        return (FXMLLoader) getContext().getBean(
                ContextConf.LOADER_SET_UP_WEAPON);
    }

    private final ApplicationContext getContext() {
        return context;
    }

}
