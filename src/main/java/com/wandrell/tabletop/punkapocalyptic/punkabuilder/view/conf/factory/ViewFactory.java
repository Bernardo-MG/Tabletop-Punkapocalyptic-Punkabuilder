package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.conf.factory;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class ViewFactory {

    public static final Stage getDialog(final Pane pane) {
        final Stage dialogStage;
        final Scene scene;

        // TODO: Remove this

        scene = new Scene(pane);

        dialogStage = new Stage();

        dialogStage.setScene(scene);
        dialogStage.initModality(Modality.APPLICATION_MODAL);

        return dialogStage;
    }

    public ViewFactory() {
        super();
    }

}
