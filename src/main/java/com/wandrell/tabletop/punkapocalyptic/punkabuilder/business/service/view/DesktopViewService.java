package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;

import javafx.scene.layout.Pane;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view.command.LoadSetUpWeaponPaneCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller.SetUpWeaponController;

public final class DesktopViewService implements ViewService {

    private final CommandExecutor executor;

    public DesktopViewService(final CommandExecutor executor) {
        super();

        checkNotNull(executor, "Received a null pointer as executor");

        this.executor = executor;
    }

    @Override
    public void loadSetUpWeaponPane(final Collection<Pane> panes,
            final Collection<SetUpWeaponController> controllers) {
        getExecutor().execute(
                new LoadSetUpWeaponPaneCommand(panes, controllers));
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }
}
