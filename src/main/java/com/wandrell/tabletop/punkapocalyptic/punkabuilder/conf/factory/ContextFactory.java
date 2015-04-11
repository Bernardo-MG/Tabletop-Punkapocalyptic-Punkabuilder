package com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory;

import java.util.Properties;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.springframework.context.ApplicationContext;

import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.SpringConf;
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
                .getClassPathInputStream(SpringConf.PROPERTIES));

        context = SpringUtils.getClassPathContext(config,
                SpringConf.FILE_COMMAND, SpringConf.FILE_DATA,
                SpringConf.FILE_SERVICE, SpringConf.FILE_REPOSITORY,
                SpringConf.FILE_PROCEDURE, SpringConf.FILE_CONTROLLER,
                SpringConf.FILE_LOCALIZATION, SpringConf.FILE_VIEW);
    }

    public final Stage getAboutDialog() {
        return (Stage) getContext().getBean(SpringConf.VIEW_ABOUT_DIALOG);
    }

    public final ApplicationInfoService getApplicationInfoService() {
        return (ApplicationInfoService) getContext().getBean(
                SpringConf.SERVICE_APPLICATION);
    }

    public final FactionSelectionController getFactionSelectionController() {
        return (FactionSelectionController) getContext().getBean(
                SpringConf.CONTROLLER_FACTION);
    }

    public final Pane getFactionSelectionPane() {
        return (Pane) getContext().getBean(
                SpringConf.VIEW_FACTION_SELECTION_PANE);
    }

    public final GangBuilderManager getGangBuilderManager() {
        return (GangBuilderManager) getContext().getBean(
                SpringConf.MANAGER_GANG_BUILDER);
    }

    public final Pane getGangCreationPane() {
        return (Pane) getContext().getBean(SpringConf.VIEW_GANG_CREATION_PANE);
    }

    public final MainPaneController getMainFrameController() {
        return (MainPaneController) getContext().getBean(
                SpringConf.CONTROLLER_MAIN);
    }

    public final Pane getMainPane() {
        return (Pane) getContext().getBean(SpringConf.VIEW_MAIN);
    }

    public final Stage getSetUpUnitDialog() {
        return (Stage) getContext().getBean(SpringConf.VIEW_SET_UP_UNIT_DIALOG);
    }

    public final FXMLLoader getSetUpWeaponLoader() {
        return (FXMLLoader) getContext().getBean(
                SpringConf.LOADER_SET_UP_WEAPON);
    }

    private final ApplicationContext getContext() {
        return context;
    }

}
