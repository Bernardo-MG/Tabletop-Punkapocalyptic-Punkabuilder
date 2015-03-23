package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view;

import java.util.Collection;

import javafx.scene.layout.Pane;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.SetUpWeaponController;

public interface ViewService {

    public void loadSetUpWeaponPane(final Collection<Pane> panes,
            final Collection<SetUpWeaponController> controllers);

}
