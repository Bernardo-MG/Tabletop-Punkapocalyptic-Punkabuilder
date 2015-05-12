package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

import static com.google.common.base.Preconditions.checkNotNull;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.FactionViewConfigRepository;
import com.wandrell.tabletop.punkapocalyptic.repository.FactionRepository;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;

@Component
public final class FactionSelectionController {

    private final FactionRepository           factionRepo;
    private final FactionViewConfigRepository factionViewRepo;
    private final GangBuilderManager          gangBuilderManager;
    private final Pane                        gangCreationPane;
    private final ModelLocalizationService    localizationService;
    private final MainPaneController          mainPaneController;
    private final ModelService                modelService;
    @FXML
    private HBox                              selectionBox;

    @Autowired
    public FactionSelectionController(final FactionRepository factionRepo,
            final ModelService modelService,
            final ModelLocalizationService localizationService,
            final GangBuilderManager gangBuilderManager,
            @Qualifier("gangCreationPane") final Object gangCreationPane,
            final MainPaneController mainController,
            final FactionViewConfigRepository factionViewRepo) {
        super();

        checkNotNull(gangBuilderManager,
                "Received a null pointer as the army builder procedure controller");
        checkNotNull(factionRepo,
                "Received a null pointer as the faction repository");
        checkNotNull(modelService,
                "Received a null pointer as the model service");
        checkNotNull(localizationService,
                "Received a null pointer as the localization service");
        checkNotNull(factionViewRepo,
                "Received a null pointer as faction view config repo");
        checkNotNull(mainController,
                "Received a null pointer as the main pane controller");
        checkNotNull(gangCreationPane,
                "Received a null pointer as the gang creation pane");

        this.modelService = modelService;
        this.localizationService = localizationService;

        this.factionRepo = factionRepo;
        this.gangBuilderManager = gangBuilderManager;
        this.gangCreationPane = (Pane) gangCreationPane;
        mainPaneController = mainController;
        this.factionViewRepo = factionViewRepo;
    }

    public final void loadFactions() {
        ImageView icon;
        Button button;

        for (final Faction faction : getFactionRepository().getAll()) {
            icon = new ImageView(factionViewRepo.getConfigForFaction(
                    faction.getNameToken()).getImage());

            button = new Button(getModelLocalizationService()
                    .getFactionNameString(faction.getNameToken()), icon);
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

    private final FactionRepository getFactionRepository() {
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

    private final ModelLocalizationService getModelLocalizationService() {
        return localizationService;
    }

    private final ModelService getModelService() {
        return modelService;
    }

}
