package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.event;

import java.util.Queue;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller.SetUpWeaponController;

public final class WeaponPickedEventHandler implements
        EventHandler<ActionEvent> {

    private final Queue<SetUpWeaponController> controllers;

    public WeaponPickedEventHandler(
            final Queue<SetUpWeaponController> controllers) {
        super();

        this.controllers = controllers;
    }

    @Override
    public final void handle(final ActionEvent event) {
        final SetUpWeaponController controller;

        controller = getControllers().poll();

        if (controller != null) {
            controller.loadWeapons();
            controller.setEnabled(true);
        }
    }

    private final Queue<SetUpWeaponController> getControllers() {
        return controllers;
    }

}
