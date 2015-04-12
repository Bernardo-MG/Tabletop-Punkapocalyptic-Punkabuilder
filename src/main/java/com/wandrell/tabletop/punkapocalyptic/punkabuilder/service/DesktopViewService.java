package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.io.IOException;
import java.util.Collection;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller.SetUpWeaponController;

@Service("viewService")
public final class DesktopViewService implements ViewService {

    public DesktopViewService() {
        super();
    }

    @Override
    public void loadSetUpWeaponPane(final Collection<Pane> panes,
            final Collection<SetUpWeaponController> controllers) {
        final FXMLLoader loader;

        loader = ContextFactory.getInstance().getSetUpWeaponLoader();

        try {
            panes.add(loader.load());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }

        controllers.add(loader.getController());
    }

}
