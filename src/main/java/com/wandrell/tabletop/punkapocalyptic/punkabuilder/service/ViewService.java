package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.util.Collection;

import javafx.scene.layout.Pane;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller.SetUpWeaponController;

public interface ViewService {

    public void loadSetUpWeaponPane(final Collection<Pane> panes,
            final Collection<SetUpWeaponController> controllers);

}
