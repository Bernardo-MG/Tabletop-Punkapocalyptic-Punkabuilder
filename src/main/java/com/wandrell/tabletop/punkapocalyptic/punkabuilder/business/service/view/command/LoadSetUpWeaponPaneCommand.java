package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view.command;

import java.io.IOException;
import java.util.Collection;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import com.wandrell.pattern.command.Command;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.SetUpWeaponController;

public final class LoadSetUpWeaponPaneCommand implements Command {

    private final Collection<SetUpWeaponController> controllers;
    private final Collection<Pane>                  panes;

    public LoadSetUpWeaponPaneCommand(final Collection<Pane> panes,
            final Collection<SetUpWeaponController> controllers) {
        super();

        this.panes = panes;
        this.controllers = controllers;
    }

    @Override
    public final void execute() throws IOException {
        final FXMLLoader loader;

        loader = ContextFactory.getInstance().getSetUpWeaponLoader();

        panes.add(loader.load());
        controllers.add(loader.getController());
    }

}
