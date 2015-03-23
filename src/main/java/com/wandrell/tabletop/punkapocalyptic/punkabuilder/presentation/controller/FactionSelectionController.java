package com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller;

import static com.google.common.base.Preconditions.checkNotNull;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.model.config.FactionViewConfig;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;

public final class FactionSelectionController {

    private final Repository<Faction>           factionRepo;
    private final Repository<FactionViewConfig> factionViewRepo;
    private final GangBuilderManager            gangBuilderManager;
    private final Pane                          gangCreationPane;
    private final MainPaneController            mainPaneController;
    private final ModelService                  modelService;
    @FXML
    private HBox                                selectionBox;

    public FactionSelectionController(final Repository<Faction> factionRepo,
            final ModelService modelService,
            final GangBuilderManager gangBuilderManager,
            final Pane gangCreationPane,
            final MainPaneController mainController,
            final Repository<FactionViewConfig> factionViewRepo) {
        super();

        checkNotNull(gangBuilderManager,
                "Received a null pointer as the army builder procedure controller");
        checkNotNull(factionRepo,
                "Received a null pointer as the faction repository");
        checkNotNull(modelService,
                "Received a null pointer as the model service");
        checkNotNull(factionViewRepo,
                "Received a null pointer as faction view config repo");
        checkNotNull(mainController,
                "Received a null pointer as the main pane controller");
        checkNotNull(gangCreationPane,
                "Received a null pointer as the gang creation pane");

        this.modelService = modelService;
        this.factionRepo = factionRepo;
        this.gangBuilderManager = gangBuilderManager;
        this.gangCreationPane = gangCreationPane;
        mainPaneController = mainController;
        this.factionViewRepo = factionViewRepo;
    }

    public final void loadFactions() {
        ImageView icon;
        Button button;

        for (final Faction faction : getFactionRepository().getCollection(
                f -> true)) {
            icon = new ImageView(factionViewRepo
                    .getCollection(
                            f -> f.getFaction().getName()
                                    .equals(faction.getName())).iterator()
                    .next().getImage());

            button = new Button(faction.getName(), icon);
            button.setContentDisplay(ContentDisplay.TOP);

            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    final Gang gang;

                    gang = getModelService().getGang(faction);

                    getGangBuilderManager().setGang(gang);

                    getMainPaneController().setSaveAllowed(true);
                    getMainPaneController().setShownPane(getGangCreationPane());
                }
            });

            selectionBox.getChildren().add(button);
        }
    }

    private final Repository<Faction> getFactionRepository() {
        return factionRepo;
    }

    private final GangBuilderManager getGangBuilderManager() {
        return gangBuilderManager;
    }

    private final Pane getGangCreationPane() {
        return gangCreationPane;
    }

    private final MainPaneController getMainPaneController() {
        return mainPaneController;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
