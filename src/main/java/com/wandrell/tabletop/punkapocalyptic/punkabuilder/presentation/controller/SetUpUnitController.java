package com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Separator;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import com.wandrell.tabletop.event.ValueChangeEvent;
import com.wandrell.tabletop.event.ValueChangeListener;
import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.procedure.DefaultValueHandler;
import com.wandrell.tabletop.procedure.ValueHandler;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Armor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.DefaultArmor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.GroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangChangedEvent;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangChangedListener;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.view.ViewService;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator.ArmorCostComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator.MutationNameComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.util.comparator.UnitNameComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.event.WeaponPickedEventHandler;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.ArmorNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.MutationNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.MutationNameListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.SpecialRuleListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.UnitNameListCell;
import com.wandrell.tabletop.punkapocalyptic.util.ArmorUtils;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class SetUpUnitController {

    @FXML
    private Button                            addMutationButton;
    @FXML
    private Pane                              armorBox;
    @FXML
    private ComboBox<Armor>                   armorCombo;
    @FXML
    private Pane                              armorPane;
    @FXML
    private Label                             armorProtectionLabel;
    @FXML
    private ListView<SpecialRule>             armorSpecialRulesList;
    @FXML
    private Label                             basePointsLabel;
    @FXML
    private Button                            decreaseGroupButton;
    @FXML
    private Pane                              equipmentPane;
    @FXML
    private ListView<String>                  errorsList;
    private final GangBuilderManager          gangBuilderManager;
    @FXML
    private Pane                              groupBox;
    @FXML
    private Pane                              groupPane;
    private final ValueHandler                groupSizeHandler       = new DefaultValueHandler();
    @FXML
    private Label                             groupSizeLabel;
    @FXML
    private Button                            increaseGroupButton;
    @FXML
    private Label                             labelActions;
    @FXML
    private Label                             labelAgility;
    @FXML
    private Label                             labelCombat;
    @FXML
    private Label                             labelPrecision;
    @FXML
    private Label                             labelStrength;
    @FXML
    private Label                             labelTech;
    @FXML
    private Label                             labelToughness;
    @FXML
    private ComboBox<Mutation>                mutationCombo;
    @FXML
    private Pane                              mutationsBox;
    @FXML
    private ListView<Mutation>                mutationsList;
    @FXML
    private Pane                              mutationsPane;
    @FXML
    private Pane                              rulesBox;
    @FXML
    private Pane                              rulesPane;
    private Stage                             setUpUnitDialog;
    private final List<SetUpWeaponController> setUpWeaponControllers = new LinkedList<>();
    private final List<Pane>                  setUpWeaponPanes       = new LinkedList<>();
    @FXML
    private ListView<SpecialRule>             specialRulesList;
    @FXML
    private Label                             totalPointsLabel;
    private final UnitConfigurationManager    unitConfigManager;
    private final UnitListener                unitListener;
    @FXML
    private Label                             unitNameLabel;
    @FXML
    private ListView<Unit>                    unitSelectionList;
    private final ViewService                 viewService;
    @FXML
    private Pane                              weaponsPane;

    {
        unitListener = new UnitListener() {

            @Override
            public final void actionsChanged(final EventObject e) {
                labelActions.setText(getUnit().getActions().toString());
            }

            @Override
            public final void agilityChanged(final EventObject e) {
                labelAgility.setText(getUnit().getAgility().toString());
            }

            @Override
            public final void combatChanged(final EventObject e) {
                labelCombat.setText(getUnit().getCombat().toString());
            }

            @Override
            public final void mutationChanged(final EventObject e) {}

            @Override
            public final void precisionChanged(final EventObject e) {
                labelPrecision.setText(getUnit().getPrecision().toString());
            }

            @Override
            public final void strengthChanged(final EventObject e) {
                labelStrength.setText(getUnit().getStrength().toString());
            }

            @Override
            public final void techChanged(final EventObject e) {
                labelTech.setText(getUnit().getTech().toString());
            }

            @Override
            public final void toughnessChanged(final EventObject e) {
                labelToughness.setText(getUnit().getToughness().toString());
            }

            @Override
            public final void valorationChanged(final EventObject e) {
                loadPointsLabels();
            }

        };
    }

    public SetUpUnitController(final GangBuilderManager gangBuilderManager,
            final UnitConfigurationManager unitConfigManager,
            final ViewService viewService) {
        super();

        checkNotNull(viewService, "Received a null pointer as view service");
        checkNotNull(gangBuilderManager,
                "Received a null pointer as gang builder manager");
        checkNotNull(unitConfigManager,
                "Received a null pointer as unit configuration manager");

        this.viewService = viewService;
        this.gangBuilderManager = gangBuilderManager;
        this.unitConfigManager = unitConfigManager;

        gangBuilderManager.addGangChangedListener(new GangChangedListener() {

            @Override
            public final void gangChanged(final GangChangedEvent event) {
                loadUnitsList();
            }

        });
    }

    @FXML
    public final void handleAddMutation(final ActionEvent event) {
        final Mutation mutation;
        final Integer index;

        if (getUnit() instanceof MutantUnit) {
            mutation = getMutationsComboBox().getSelectionModel()
                    .getSelectedItem();
            index = getMutationsComboBox().getSelectionModel()
                    .getSelectedIndex();

            getMutationsComboBox().getItems().remove(mutation);

            ((MutantUnit) getUnit()).addMutation(mutation);
            getMutationsList().getItems().add(mutation);
            if (getMutationsComboBox().getItems().isEmpty()) {
                getMutationsComboBox().setDisable(true);
                getAddMutationButton().setDisable(true);
            } else if (index == 0) {
                getMutationsComboBox().getSelectionModel().select(0);
            }

            if (getUnitConfigurationManager().getMaxMutations() == ((MutantUnit) getUnit())
                    .getMutations().size()) {
                getAddMutationButton().setDisable(true);
                getMutationsComboBox().setDisable(true);
            }
        }
    }

    @FXML
    public final void handleDecreaseGroupAction(final ActionEvent event) {
        if (getUnit() instanceof GroupedUnit) {
            getGroupSizeHandler().decreaseValue();

            getDecreaseGroupButton().setDisable(
                    !getGroupSizeHandler().isAbleToDecrease());
            getIncreaseGroupButton().setDisable(
                    !getGroupSizeHandler().isAbleToIncrease());
        }
    }

    @FXML
    public final void handleIncreaseGroupAction(final ActionEvent event) {
        if (getUnit() instanceof GroupedUnit) {
            getGroupSizeHandler().increaseValue();

            getDecreaseGroupButton().setDisable(
                    !getGroupSizeHandler().isAbleToDecrease());
            getIncreaseGroupButton().setDisable(
                    !getGroupSizeHandler().isAbleToIncrease());
        }
    }

    @FXML
    public final void handlePickUnitAction(final ActionEvent event) {
        if (getUnitConfigurationManager().validate()) {
            if (getUnitsList().getItems().size() > 0) {
                getGangBuilderManager().getGang().addUnit(getUnit());
                getUnitsList().getSelectionModel().select(0);
                reset();
            }
            getDialog().close();
        } else {
            getErrorsList().getItems().setAll(
                    getUnitConfigurationManager().getValidationMessages());
        }
    }

    @FXML
    public final void handleResetUnitAction(final ActionEvent event) {
        reset();
    }

    @FXML
    public final void initialize() {
        initializeUnitsList();
        initializeArmorComboBox();
        initializeMutationsComboBox();
        initializeMutationsList();
        initializeArmorRulesList();
        initializeSpecialRulesList();
    }

    public final void setDialog(final Stage dialog) {
        checkNotNull(dialog, "Received a null pointer as dialog");

        setUpUnitDialog = dialog;

        dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent we) {
                reset();
            }
        });
    }

    private final Button getAddMutationButton() {
        return addMutationButton;
    }

    private final Pane getArmorBox() {
        return armorBox;
    }

    private final ComboBox<Armor> getArmorComboBox() {
        return armorCombo;
    }

    private final Pane getArmorPane() {
        return armorPane;
    }

    private final Label getArmorProtectionLabel() {
        return armorProtectionLabel;
    }

    private final ListView<SpecialRule> getArmorRulesList() {
        return armorSpecialRulesList;
    }

    private final Button getDecreaseGroupButton() {
        return decreaseGroupButton;
    }

    private final Stage getDialog() {
        return setUpUnitDialog;
    }

    private final Pane getEquipmentPane() {
        return equipmentPane;
    }

    private final ListView<String> getErrorsList() {
        return errorsList;
    }

    private final GangBuilderManager getGangBuilderManager() {
        return gangBuilderManager;
    }

    private final Pane getGroupedBox() {
        return groupBox;
    }

    private final Pane getGroupedPane() {
        return groupPane;
    }

    private final ValueHandler getGroupSizeHandler() {
        return groupSizeHandler;
    }

    private final Label getGroupSizeLabel() {
        return groupSizeLabel;
    }

    private final Button getIncreaseGroupButton() {
        return increaseGroupButton;
    }

    private final Pane getMutationsBox() {
        return mutationsBox;
    }

    private final ComboBox<Mutation> getMutationsComboBox() {
        return mutationCombo;
    }

    private final ListView<Mutation> getMutationsList() {
        return mutationsList;
    }

    private final Pane getMutationsPane() {
        return mutationsPane;
    }

    private final List<SetUpWeaponController> getSetUpWeaponControllers() {
        return setUpWeaponControllers;
    }

    private final List<Pane> getSetUpWeaponPanes() {
        return setUpWeaponPanes;
    }

    private final Pane getSpecialRulesBox() {
        return rulesBox;
    }

    private final ListView<SpecialRule> getSpecialRulesList() {
        return specialRulesList;
    }

    private final Pane getSpecialRulesPane() {
        return rulesPane;
    }

    private final Unit getUnit() {
        return getUnitConfigurationManager().getUnit();
    }

    private final UnitConfigurationManager getUnitConfigurationManager() {
        return unitConfigManager;
    }

    private final UnitListener getUnitListener() {
        return unitListener;
    }

    private final ListView<Unit> getUnitsList() {
        return unitSelectionList;
    }

    private final ViewService getViewService() {
        return viewService;
    }

    private final Pane getWeaponsPane() {
        return weaponsPane;
    }

    private final void initializeArmorComboBox() {
        getArmorComboBox()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        getUnit().setArmor(newValue);
                    } else {
                        // TODO: Do not instantiate manually
                        getUnit().setArmor(
                                new DefaultArmor("unarmored", 0,
                                        new LinkedList<>()));
                    }

                    loadArmorData(getUnit().getArmor());
                });

        getArmorComboBox().setCellFactory(column -> {
            return new ArmorNameAndCostListCell();
        });
        getArmorComboBox().setButtonCell(new ArmorNameAndCostListCell());
    }

    private final void initializeArmorRulesList() {
        getArmorRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell();
        });
    }

    private final void initializeCompulsoryPanels(
            final Queue<SetUpWeaponController> controllers) {
        Pane pane;
        SetUpWeaponController controller;

        for (int i = 0; i < getUnitConfigurationManager()
                .getAllowedWeaponsInterval().getLowerLimit(); i++) {
            if (i > 0) {
                getWeaponsPane().getChildren().add(
                        new Separator(Orientation.VERTICAL));
            }

            if (getSetUpWeaponPanes().size() <= i) {
                getViewService().loadSetUpWeaponPane(getSetUpWeaponPanes(),
                        getSetUpWeaponControllers());
                getSetUpWeaponControllers().get(i).setUnit(getUnit());
            }

            pane = getSetUpWeaponPanes().get(i);
            controller = getSetUpWeaponControllers().get(i);

            initializeSetUpWeaponPane(controller, i == 0, controllers);

            getWeaponsPane().getChildren().add(pane);
        }
    }

    private final void initializeMutationsComboBox() {
        getMutationsComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {});

        getMutationsComboBox().setCellFactory(column -> {
            return new MutationNameAndCostListCell();
        });
        getMutationsComboBox().setButtonCell(new MutationNameAndCostListCell());
    }

    private final void initializeMutationsList() {
        getMutationsList().setCellFactory(column -> {
            return new MutationNameListCell();
        });
    }

    private final void initializeOptionalPanels(
            final Queue<SetUpWeaponController> controllers) {
        final Interval intervalWeapons;
        Pane pane;
        SetUpWeaponController controller;

        intervalWeapons = getUnitConfigurationManager()
                .getAllowedWeaponsInterval();

        for (int i = intervalWeapons.getLowerLimit(); i < intervalWeapons
                .getUpperLimit(); i++) {
            if ((intervalWeapons.getLowerLimit() > 0) || (i > 0)) {
                getWeaponsPane().getChildren().add(
                        new Separator(Orientation.VERTICAL));
            }

            if (getSetUpWeaponPanes().size() <= i) {
                getViewService().loadSetUpWeaponPane(getSetUpWeaponPanes(),
                        getSetUpWeaponControllers());
                getSetUpWeaponControllers().get(i).setUnit(getUnit());
            }

            pane = getSetUpWeaponPanes().get(i);
            controller = getSetUpWeaponControllers().get(i);

            initializeSetUpWeaponPane(controller, i == 0, controllers);

            getWeaponsPane().getChildren().add(pane);
        }
    }

    private final void initializeSetUpWeaponPane(
            final SetUpWeaponController controller, final Boolean enabled,
            final Queue<SetUpWeaponController> controllers) {
        if (enabled) {
            controller.loadWeapons();
        }

        controller.setEnabled(enabled);

        controllers.add(controller);

        controller.addOnPickEventHandler(new WeaponPickedEventHandler(
                controllers));
    }

    private final void initializeSpecialRulesList() {
        getSpecialRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell();
        });
    }

    private final void initializeUnitsList() {
        getUnitsList().setCellFactory(view -> {
            return new UnitNameListCell();
        });

        getUnitsList()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if ((newValue != null)
                                    && (!newValue
                                            .equals(getUnitConfigurationManager()
                                                    .getUnit()))) {
                                setUnit(newValue.createNewInstance());
                            }
                        });
    }

    private final void loadArmorComboBox() {
        getArmorComboBox().getItems().setAll(
                getUnitConfigurationManager().getArmorOptions());

        FXCollections.sort(getArmorComboBox().getItems(),
                new ArmorCostComparator());

        if (getArmorComboBox().getItems().isEmpty()) {
            getArmorBox().getChildren().clear();
        } else {
            if (getArmorBox().getChildren().isEmpty()) {
                getArmorBox().getChildren().add(getArmorPane());
            }
            getArmorComboBox().getSelectionModel().select(0);
        }
    }

    private final void loadArmorData(final Armor armor) {
        if (armor == null) {
            getArmorProtectionLabel().setText("0");

            getArmorRulesList().getItems().clear();
        } else {
            getArmorProtectionLabel().setText(ArmorUtils.getArmor(armor));

            getArmorRulesList().getItems().setAll(armor.getSpecialRules());
        }
    }

    private final void loadAttributeLabels(final Unit unit) {
        labelActions.setText(String.valueOf(unit.getActions()));
        labelAgility.setText(String.valueOf(unit.getAgility()));
        labelCombat.setText(String.valueOf(unit.getCombat()));
        labelPrecision.setText(String.valueOf(unit.getPrecision()));
        labelStrength.setText(String.valueOf(unit.getStrength()));
        labelTech.setText(String.valueOf(unit.getTech()));
        labelToughness.setText(String.valueOf(unit.getToughness()));
    }

    private final void loadEquipment() {
        final String template = "%s (%d)";
        String name;

        getEquipmentPane().getChildren().clear();

        for (final Equipment equipment : getUnitConfigurationManager()
                .getEquipmentOptions()) {
            final CheckBox check;

            name = String.format(template, equipment.getName(),
                    equipment.getCost());

            check = new CheckBox(name);

            getEquipmentPane().getChildren().add(check);

            check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                    if ((new_val) && (!old_val)) {
                        getUnit().addEquipment(equipment);
                    }

                    if ((!new_val) && (old_val)) {
                        getUnit().removeEquipment(equipment);
                    }
                }
            });
        }

        if (getEquipmentPane().getChildren().size() > 0) {
            getEquipmentPane().getChildren().add(
                    new Separator(Orientation.HORIZONTAL));
        }
    }

    private final void loadGroupPane() {
        final ValueChangeListener countListener;
        final ValueBox size;

        if (getUnitConfigurationManager().isGrouped()) {
            if (getGroupedBox().getChildren().isEmpty()) {
                getGroupedBox().getChildren().add(getGroupedPane());
            }
            size = ((GroupedUnit) getUnit()).getGroupSize();

            getGroupSizeLabel().setText(size.getValue().toString());

            countListener = new ValueChangeListener() {

                @Override
                public final void valueChanged(final ValueChangeEvent evt) {
                    getGroupSizeLabel().setText(evt.getNewValue().toString());
                }

            };

            size.addValueChangeListener(countListener);
        } else {
            getGroupedBox().getChildren().clear();
        }
    }

    private final void loadMutationsPane() {
        final Collection<Mutation> mutationOptions;

        if (getUnit() instanceof MutantUnit) {
            getMutationsList().getItems().setAll(
                    ((MutantUnit) getUnit()).getMutations());
        } else {
            getMutationsList().getItems().clear();
        }

        mutationOptions = getUnitConfigurationManager().getMutations();
        if (mutationOptions.isEmpty()) {
            getMutationsBox().getChildren().clear();
        } else if (getMutationsBox().getChildren().isEmpty()) {
            getMutationsBox().getChildren().add(getMutationsPane());
        }

        getMutationsComboBox().getItems().setAll(mutationOptions);
        FXCollections.sort(getMutationsComboBox().getItems(),
                new MutationNameComparator());

        if (!getMutationsComboBox().getItems().isEmpty()) {
            getMutationsComboBox().getSelectionModel().select(0);
        }

        getAddMutationButton().setDisable(false);
        getMutationsComboBox().setDisable(false);
    }

    private final void loadNameLabels() {
        unitNameLabel.setText(getUnit().getName());
    }

    private final void loadPointsLabels() {
        basePointsLabel.setText(String.valueOf(getUnit().getBaseCost()));
        totalPointsLabel.setText(String.valueOf(getUnit().getValoration()));
    }

    private final void loadSpecialRules() {
        getSpecialRulesList().getItems().setAll(getUnit().getSpecialRules());

        if (getUnit().getSpecialRules().isEmpty()) {
            getSpecialRulesBox().getChildren().clear();
        } else if (getSpecialRulesBox().getChildren().isEmpty()) {
            getSpecialRulesBox().getChildren().add(getSpecialRulesPane());
        }
    }

    private final void loadUnitsList() {
        final List<Unit> units;
        final Collection<Unit> options;

        options = getGangBuilderManager().getUnitOptions();
        if (options instanceof List<?>) {
            units = (List<Unit>) getGangBuilderManager().getUnitOptions();
        } else {
            units = new LinkedList<>(getGangBuilderManager().getUnitOptions());
        }

        Collections.sort(units, new UnitNameComparator());

        getUnitsList().getItems().setAll(units);

        if (!getUnitsList().getItems().isEmpty()) {
            // getUnitsList().getSelectionModel().select(0);
        }
    }

    private final void loadWeaponsPane() {
        final Queue<SetUpWeaponController> controllers;

        getWeaponsPane().getChildren().clear();

        controllers = new LinkedList<>();

        initializeCompulsoryPanels(controllers);
        initializeOptionalPanels(controllers);

        controllers.poll();
    }

    private final void reset() {
        final Unit unit;

        unit = getUnitsList().getSelectionModel().getSelectedItem();

        if (unit != null) {
            setUnit(getUnitsList().getSelectionModel().getSelectedItem()
                    .createNewInstance());
        }
    }

    private final void setUnit(final Unit unit) {
        final ValueBox group;

        if (unit != getUnitConfigurationManager().getUnit()) {
            if (getUnit() != null) {
                getUnit().removeUnitListener(getUnitListener());
            }

            unit.addUnitListener(getUnitListener());

            loadAttributeLabels(unit);

            getUnitConfigurationManager().setUnit(unit);

            for (final SetUpWeaponController controller : getSetUpWeaponControllers()) {
                controller.setUnit(unit);
                controller.setEnabled(false);
                controller.clear();
            }

            loadPointsLabels();
            loadNameLabels();
            loadArmorComboBox();
            loadEquipment();
            loadWeaponsPane();
            loadGroupPane();
            loadSpecialRules();
            loadMutationsPane();

            getErrorsList().getItems().clear();

            if (getUnit() instanceof GroupedUnit) {
                group = ((GroupedUnit) getUnit()).getGroupSize();
                getDecreaseGroupButton().setDisable(
                        group.getValue() == groupSizeHandler.getLowerLimit());
                getIncreaseGroupButton().setDisable(
                        group.getValue() == groupSizeHandler.getUpperLimit());

                getGroupSizeHandler().setValueBox(group);
            }
        }
    }

}
