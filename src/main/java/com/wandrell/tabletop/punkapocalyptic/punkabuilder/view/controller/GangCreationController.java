package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

import static com.google.common.base.Preconditions.checkNotNull;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.GangListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitEvent;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangBuilderStatusChangedListener;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangChangedEvent;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.UnitNameTableCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.UnitValorationTableCell;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;
import com.wandrell.tabletop.stats.controller.DefaultValueController;
import com.wandrell.tabletop.stats.controller.ValueController;
import com.wandrell.tabletop.stats.event.ValueChangeEvent;
import com.wandrell.tabletop.stats.event.ValueChangeListener;
import com.wandrell.tabletop.stats.valuebox.DefaultValueBox;
import com.wandrell.tabletop.stats.valuebox.ValueBox;

@Component
public final class GangCreationController {

    @FXML
    private Button                         addBulletButton;
    private final ValueController          bulletsController = new DefaultValueController();
    @FXML
    private Label                          bulletsLabel;
    @FXML
    private ListView<String>               errorsList;
    private final GangBuilderManager       gangBuilderManager;
    private final GangListener             gangListener;
    private final ModelLocalizationService localizationService;
    @FXML
    private Label                          maxUnitsLabel;
    @FXML
    private Button                         removeBulletButton;
    private Stage                          setUpUnitDialog;
    @FXML
    private Label                          totalPointsLabel;
    @FXML
    private TableView<Unit>                unitsTable;

    {
        bulletsController.setInterval(0, Integer.MAX_VALUE);
    }

    {
        gangListener = new GangListener() {

            @Override
            public final void bulletsChanged(final ValueChangeEvent e) {
                getBulletsLabel().setText(
                        getGangBuilderManager().getGang().getBullets()
                                .toString());
            }

            @Override
            public final void unitAdded(final UnitEvent e) {
                validate();
            }

            @Override
            public final void unitRemoved(final UnitEvent e) {
                validate();
            }

            @Override
            public void valorationChanged(final ValueChangeEvent e) {
                getTotalPointsLabel().setText(
                        getGangBuilderManager().getGang().getValoration()
                                .toString());
            }

        };
    }

    @Autowired
    public GangCreationController(final GangBuilderManager gangBuilderManager,
            final ModelLocalizationService localizationService) {
        super();

        checkNotNull(gangBuilderManager,
                "Received a null pointer as the army builder procedure controller");
        checkNotNull(localizationService,
                "Received a null pointer as the army builder model localization service");

        this.gangBuilderManager = gangBuilderManager;

        this.localizationService = localizationService;

        setMaxUnitsListener();
        setUnitChangedListeners();
        setGangChangedListeners();
    }

    @FXML
    public final void handleAddBulletAction(final ActionEvent event) {
        getBulletsController().increaseValue();

        setBulletButtonsStatus();
    }

    @FXML
    public final void handleAddUnitAction(final ActionEvent event) {
        getDialog().showAndWait();
    }

    @FXML
    public final void handleDeleteUnitAction(final ActionEvent event) {
        final Integer selectedIndex;
        final Unit unit;

        selectedIndex = getUnitsTable().getSelectionModel().getSelectedIndex();
        unit = getUnitsTable().getItems().get(selectedIndex);

        getGangBuilderManager().getGang().removeUnit(unit);
    }

    @FXML
    public final void handleRemoveBulletAction(final ActionEvent event) {
        getBulletsController().decreaseValue();

        setBulletButtonsStatus();
    }

    @FXML
    public final void initialize() {
        initializeTableColumnsCellValuesFactories();
        initializeTableColumnsCellFactories();

        setBulletButtonsStatus();
    }

    @Autowired
    public final void
            setDialog(@Qualifier("setUpUnitDialog") final Stage dialog) {
        checkNotNull(dialog, "Received a null pointer as dialog");

        setUpUnitDialog = dialog;
    }

    private final Button getAddBulletButton() {
        return addBulletButton;
    }

    private final ValueController getBulletsController() {
        return bulletsController;
    }

    private final Label getBulletsLabel() {
        return bulletsLabel;
    }

    private final Stage getDialog() {
        return setUpUnitDialog;
    }

    private final ListView<String> getErrorsList() {
        return errorsList;
    }

    private final GangBuilderManager getGangBuilderManager() {
        return gangBuilderManager;
    }

    private final GangListener getGangListener() {
        return gangListener;
    }

    private final Label getMaxUnitsLabel() {
        return maxUnitsLabel;
    }

    private final Button getRemoveBulletButton() {
        return removeBulletButton;
    }

    private final Label getTotalPointsLabel() {
        return totalPointsLabel;
    }

    private final TableView<Unit> getUnitsTable() {
        return unitsTable;
    }

    @SuppressWarnings("unchecked")
    private final void initializeTableColumnsCellFactories() {
        final TableColumn<Unit, Unit> nameColumn;
        final TableColumn<Unit, Integer> valorationColumn;

        nameColumn = (TableColumn<Unit, Unit>) getUnitsTable().getColumns()
                .get(0);
        valorationColumn = (TableColumn<Unit, Integer>) getUnitsTable()
                .getColumns().get(1);

        nameColumn.setCellFactory((column -> {
            return new UnitNameTableCell(localizationService);
        }));

        valorationColumn.setCellFactory((column -> {
            return new UnitValorationTableCell();
        }));
    }

    @SuppressWarnings("unchecked")
    private final void initializeTableColumnsCellValuesFactories() {
        final TableColumn<Unit, Unit> nameColumn;
        final TableColumn<Unit, Integer> valorationColumn;

        nameColumn = (TableColumn<Unit, Unit>) getUnitsTable().getColumns()
                .get(0);
        valorationColumn = (TableColumn<Unit, Integer>) getUnitsTable()
                .getColumns().get(1);

        nameColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Unit>(
                        cellData.getValue()));

        valorationColumn
                .setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<Integer>(
                        cellData.getValue().getValoration()));
    }

    private final void setBulletButtonsStatus() {
        getAddBulletButton().setDisable(
                !getBulletsController().isAbleToIncrease());
        getRemoveBulletButton().setDisable(
                !getBulletsController().isAbleToDecrease());
    }

    private final void setGangChangedListeners() {
        final GangBuilderStatusChangedListener gangChangedListener;

        gangChangedListener = new GangBuilderStatusChangedListener() {

            @Override
            public final void gangChanged(final GangChangedEvent event) {
                final ValueBox valueBox;
                final Integer bullets;

                if (event.getOldGang() != null) {
                    event.getOldGang().removeGangListener(getGangListener());
                }

                event.getNewGang().addGangListener(getGangListener());

                getMaxUnitsLabel().setText(
                        getGangBuilderManager().getMaxUnits().getValue()
                                .toString());

                bullets = getGangBuilderManager().getGang().getBullets();

                valueBox = new DefaultValueBox(bullets);
                getBulletsController().setValueBox(valueBox);

                setBulletButtonsStatus();

                valueBox.addValueChangeListener(new ValueChangeListener() {

                    @Override
                    public final void
                            valueChanged(final ValueChangeEvent event) {
                        getGangBuilderManager().getGang().setBullets(
                                event.getNewValue());
                    }

                });

                getUnitsTable().getItems().clear();

                getErrorsList().getItems().clear();
            }

            @Override
            public final void maxUnitsChanged(final ValueChangeEvent event) {}

            @Override
            public final void unitAdded(final UnitEvent event) {}

            @Override
            public final void unitRemoved(final UnitEvent event) {}

        };

        getGangBuilderManager().addStatusChangedListener(gangChangedListener);
    }

    private final void setMaxUnitsListener() {
        getGangBuilderManager().addStatusChangedListener(
                new GangBuilderStatusChangedListener() {

                    @Override
                    public final void gangChanged(final GangChangedEvent event) {}

                    @Override
                    public final void maxUnitsChanged(
                            final ValueChangeEvent event) {
                        getMaxUnitsLabel().setText(
                                event.getNewValue().toString());
                    }

                    @Override
                    public final void unitAdded(final UnitEvent event) {}

                    @Override
                    public final void unitRemoved(final UnitEvent event) {}

                });
    }

    private final void setUnitChangedListeners() {
        getGangBuilderManager().addStatusChangedListener(
                new GangBuilderStatusChangedListener() {

                    @Override
                    public final void gangChanged(final GangChangedEvent event) {}

                    @Override
                    public final void maxUnitsChanged(
                            final ValueChangeEvent event) {}

                    @Override
                    public final void unitAdded(final UnitEvent event) {
                        getUnitsTable().getItems().add(event.getUnit());
                    }

                    @Override
                    public final void unitRemoved(final UnitEvent event) {
                        getUnitsTable().getItems().remove(event.getUnit());
                    }

                });
    }

    private final void validate() {
        if (getGangBuilderManager().validate()) {
            getErrorsList().getItems().clear();
        } else {
            getErrorsList().getItems().setAll(
                    getGangBuilderManager().getValidationMessages());
        }
    }

}
