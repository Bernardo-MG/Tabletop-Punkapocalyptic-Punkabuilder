package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.interval.Interval;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.GroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitEvent;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListener;
import com.wandrell.tabletop.punkapocalyptic.model.unit.event.UnitListenerAdapter;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.MutantUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.procedure.GangBuilderManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangBuilderStatusChangedListener;
import com.wandrell.tabletop.punkapocalyptic.procedure.event.GangChangedEvent;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ContextFactory;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.util.comparator.ArmorOptionCostComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.util.comparator.MutationNameComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.util.comparator.UnitNameComparator;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.event.WeaponPickedControllerQueueEventHandler;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.ArmorNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.MutationNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.MutationNameListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.SpecialRuleListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.UnitNameListCell;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.ArmorUtils;
import com.wandrell.tabletop.stats.controller.DefaultValueController;
import com.wandrell.tabletop.stats.controller.ValueController;
import com.wandrell.tabletop.stats.event.ValueChangeEvent;
import com.wandrell.tabletop.stats.event.ValueChangeListener;
import com.wandrell.tabletop.stats.valuebox.ValueBox;

@Component
public final class SetUpUnitController {

    @FXML
    private Button                                  addMutationButton;
    @FXML
    private Pane                                    armorBox;
    @FXML
    private ComboBox<ArmorOption>                   armorCombo;
    @FXML
    private Pane                                    armorPane;
    @FXML
    private Label                                   armorProtectionLabel;
    @FXML
    private ListView<SpecialRule>                   armorSpecialRulesList;
    @FXML
    private Label                                   basePointsLabel;
    @FXML
    private Button                                  decreaseGroupButton;
    @FXML
    private Pane                                    equipmentPane;
    @FXML
    private ListView<String>                        errorsList;
    private final GangBuilderManager                gangBuilderManager;
    @FXML
    private Pane                                    groupBox;
    @FXML
    private Pane                                    groupPane;
    private final ValueController                   groupSizeController    = new DefaultValueController();
    @FXML
    private Label                                   groupSizeLabel;
    @FXML
    private Button                                  increaseGroupButton;
    @FXML
    private Label                                   labelActions;
    @FXML
    private Label                                   labelAgility;
    @FXML
    private Label                                   labelCombat;
    @FXML
    private Label                                   labelPrecision;
    @FXML
    private Label                                   labelStrength;
    @FXML
    private Label                                   labelTech;
    @FXML
    private Label                                   labelToughness;
    @FXML
    private ComboBox<Mutation>                      mutationCombo;
    @FXML
    private Pane                                    mutationsBox;
    @FXML
    private ListView<Mutation>                      mutationsList;
    @FXML
    private Pane                                    mutationsPane;
    @FXML
    private Pane                                    rulesBox;
    @FXML
    private Pane                                    rulesPane;
    private final ModelLocalizationService          serviceLocalization;
    private final ModelService                      serviceModel;
    private final RulesetService                    serviceRuleset;
    private Stage                                   setUpUnitDialog;
    private final LinkedList<SetUpWeaponController> setUpWeaponControllers = new LinkedList<>();
    private final List<Pane>                        setUpWeaponPanes       = new LinkedList<>();
    @FXML
    private ListView<SpecialRule>                   specialRulesList;
    @FXML
    private Label                                   totalPointsLabel;
    private final UnitConfigurationManager          unitConfigManager;
    private final UnitListener                      unitListener;
    @FXML
    private Label                                   unitNameLabel;
    @FXML
    private ListView<Unit>                          unitSelectionList;
    @FXML
    private Pane                                    weaponsPane;

    {
        unitListener = new UnitListenerAdapter() {

            @Override
            public final void actionsChanged(final ValueChangeEvent e) {
                labelActions.setText(getUnit().getAttributes().getActions()
                        .toString());
            }

            @Override
            public final void agilityChanged(final ValueChangeEvent e) {
                labelAgility.setText(getUnit().getAttributes().getAgility()
                        .toString());
            }

            @Override
            public final void combatChanged(final ValueChangeEvent e) {
                labelCombat.setText(getUnit().getAttributes().getCombat()
                        .toString());
            }

            @Override
            public final void precisionChanged(final ValueChangeEvent e) {
                labelPrecision.setText(getUnit().getAttributes().getPrecision()
                        .toString());
            }

            @Override
            public final void strengthChanged(final ValueChangeEvent e) {
                labelStrength.setText(getUnit().getAttributes().getStrength()
                        .toString());
            }

            @Override
            public final void techChanged(final ValueChangeEvent e) {
                labelTech.setText(getUnit().getAttributes().getTech()
                        .toString());
            }

            @Override
            public final void toughnessChanged(final ValueChangeEvent e) {
                labelToughness.setText(getUnit().getAttributes().getToughness()
                        .toString());
            }

            @Override
            public final void valorationChanged(final ValueChangeEvent e) {
                loadPointsLabels();
            }

        };
    }

    @Autowired
    public SetUpUnitController(final GangBuilderManager gangBuilderManager,
            final UnitConfigurationManager unitConfigManager,
            final RulesetService rulesetService,
            final ModelLocalizationService localizationService,
            final ModelService modelService) {
        super();

        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");
        checkNotNull(localizationService,
                "Received a null pointer as localization service");
        checkNotNull(modelService, "Received a null pointer as model service");

        checkNotNull(gangBuilderManager,
                "Received a null pointer as gang builder manager");
        checkNotNull(unitConfigManager,
                "Received a null pointer as unit configuration manager");

        serviceRuleset = rulesetService;
        serviceLocalization = localizationService;
        serviceModel = modelService;

        this.gangBuilderManager = gangBuilderManager;
        this.unitConfigManager = unitConfigManager;

        gangBuilderManager
                .addStatusChangedListener(new GangBuilderStatusChangedListener() {

                    @Override
                    public final void gangChanged(final GangChangedEvent event) {
                        loadUnitsList();
                    }

                    @Override
                    public final void maxUnitsChanged(
                            final ValueChangeEvent event) {}

                    @Override
                    public final void unitAdded(final UnitEvent event) {}

                    @Override
                    public final void unitRemoved(final UnitEvent event) {}

                });
    }

    @FXML
    public final void handleAddMutation(final ActionEvent event) {
        final Mutation mutation;
        final Integer index;
        final MutantUnit unitMutant;

        if (getUnit() instanceof MutantUnit) {
            mutation = getMutationsComboBox().getSelectionModel()
                    .getSelectedItem();
            index = getMutationsComboBox().getSelectionModel()
                    .getSelectedIndex();

            unitMutant = ((MutantUnit) getUnit());

            unitMutant.addMutation(mutation);

            getMutationsList().getItems().add(mutation);

            getMutationsComboBox().getItems().remove(mutation);
            if (getMutationsComboBox().getItems().isEmpty()) {
                getMutationsComboBox().setDisable(true);
                getAddMutationButton().setDisable(true);
            } else if (index == 0) {
                getMutationsComboBox().getSelectionModel().select(0);
            }

            if (getUnitConfigurationManager().getOptions().getMaxMutations() == unitMutant
                    .getMutations().size()) {
                getAddMutationButton().setDisable(true);
                getMutationsComboBox().setDisable(true);
            }
        }
    }

    @FXML
    public final void handleDecreaseGroupAction(final ActionEvent event) {
        if (getUnit() instanceof GroupedUnit) {
            getGroupSizeController().decreaseValue();

            setGroupButtonsStatus();
        }
    }

    @FXML
    public final void handleIncreaseGroupAction(final ActionEvent event) {
        if (getUnit() instanceof GroupedUnit) {
            getGroupSizeController().increaseValue();

            setGroupButtonsStatus();
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

        groupSizeController
                .setInterval(0, getRulesetService().getPackMaxSize());
    }

    @Autowired
    public final void
            setDialog(@Qualifier("setUpUnitDialog") final Stage dialog) {
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

    private final ComboBox<ArmorOption> getArmorComboBox() {
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

    private final ValueController getGroupSizeController() {
        return groupSizeController;
    }

    private final Label getGroupSizeLabel() {
        return groupSizeLabel;
    }

    private final Button getIncreaseGroupButton() {
        return increaseGroupButton;
    }

    private final ModelService getModelService() {
        return serviceModel;
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

    private final RulesetService getRulesetService() {
        return serviceRuleset;
    }

    private final LinkedList<SetUpWeaponController> getSetUpWeaponControllers() {
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

    private final List<Unit> getUnits(final Collection<UnitTemplate> templates) {
        final List<Unit> units;

        units = new LinkedList<>();
        for (final UnitTemplate template : templates) {
            units.add(getModelService().getUnit(template));
        }

        return units;
    }

    private final ListView<Unit> getUnitsList() {
        return unitSelectionList;
    }

    private final Pane getWeaponsPane() {
        return weaponsPane;
    }

    private final void initializeArmorComboBox() {
        getArmorComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        getUnit().setArmor(newValue);

                        loadArmorData(getUnit().getArmor());
                    }
                });

        getArmorComboBox().setCellFactory(column -> {
            return new ArmorNameAndCostListCell(serviceLocalization);
        });
        getArmorComboBox().setButtonCell(
                new ArmorNameAndCostListCell(serviceLocalization));
    }

    private final void initializeArmorRulesList() {
        getArmorRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell(serviceLocalization);
        });
    }

    private final void initializeMutationsComboBox() {
        getMutationsComboBox().getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> {});

        getMutationsComboBox().setCellFactory(column -> {
            return new MutationNameAndCostListCell(serviceLocalization);
        });
        getMutationsComboBox().setButtonCell(
                new MutationNameAndCostListCell(serviceLocalization));
    }

    private final void initializeMutationsList() {
        getMutationsList().setCellFactory(column -> {
            return new MutationNameListCell(serviceLocalization);
        });
    }

    private final void initializeSpecialRulesList() {
        getSpecialRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell(serviceLocalization);
        });
    }

    private final void initializeUnitsList() {
        getUnitsList().setCellFactory(view -> {
            return new UnitNameListCell(serviceLocalization);
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

    private final void initializeWeaponSelectionPanels(final Integer min,
            final Integer max) {
        final EventHandler<ActionEvent> handler;
        FXMLLoader loader;
        Pane pane;
        SetUpWeaponController controller;

        handler = new WeaponPickedControllerQueueEventHandler(
                getSetUpWeaponControllers());

        for (int i = min; i < max; i++) {
            loader = ContextFactory.getInstance().getSetUpWeaponLoader();

            try {
                pane = loader.load();
            } catch (final IOException e) {
                throw new RuntimeException(e);
            }

            controller = loader.getController();

            controller.setUnit(getUnit());

            controller.setEnabled(i == 0);

            controller.addOnPickEventHandler(handler);

            getSetUpWeaponControllers().add(controller);

            getSetUpWeaponPanes().add(pane);

            if (i > 0) {
                getWeaponsPane().getChildren().add(
                        new Separator(Orientation.VERTICAL));
            }

            getWeaponsPane().getChildren().add(pane);
        }
    }

    private final void loadArmorComboBox() {
        getArmorComboBox().getItems().setAll(
                getUnitConfigurationManager().getOptions().getArmorOptions());

        FXCollections.sort(getArmorComboBox().getItems(),
                new ArmorOptionCostComparator());

        if (getArmorComboBox().getItems().isEmpty()) {
            getArmorBox().getChildren().clear();
        } else {
            if (getArmorBox().getChildren().isEmpty()) {
                getArmorBox().getChildren().add(getArmorPane());
            }
            getArmorComboBox().getSelectionModel().select(0);
        }
    }

    private final void loadArmorData(final ArmorOption armor) {
        if (armor == null) {
            getArmorProtectionLabel().setText("0");

            getArmorRulesList().getItems().clear();
        } else {
            getArmorProtectionLabel().setText(
                    ArmorUtils.getArmor(armor.getArmor()));

            getArmorRulesList().getItems().setAll(
                    armor.getArmor().getSpecialRules());
        }
    }

    private final void loadAttributeLabels(final Unit unit) {
        labelActions.setText(String.valueOf(unit.getAttributes().getActions()));
        labelAgility.setText(String.valueOf(unit.getAttributes().getAgility()));
        labelCombat.setText(String.valueOf(unit.getAttributes().getCombat()));
        labelPrecision.setText(String.valueOf(unit.getAttributes()
                .getPrecision()));
        labelStrength.setText(String
                .valueOf(unit.getAttributes().getStrength()));
        labelTech.setText(String.valueOf(unit.getAttributes().getTech()));
        labelToughness.setText(String.valueOf(unit.getAttributes()
                .getToughness()));
    }

    private final void loadEquipment() {
        final String template = "%s (%d)";
        String name;

        getEquipmentPane().getChildren().clear();

        for (final Equipment equipment : getUnitConfigurationManager()
                .getOptions().getEquipmentOptions()) {
            final CheckBox check;

            name = String.format(template, serviceLocalization
                    .getEquipmentNameString(equipment.getNameToken()),
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

        mutationOptions = getUnitConfigurationManager().getOptions()
                .getMutations();
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
        basePointsLabel.setText(String.valueOf(getUnit().getUnitTemplate()
                .getBaseCost()));
        totalPointsLabel.setText(String.valueOf(getUnit().getValoration()));
    }

    private final void loadSpecialRules() {
        getSpecialRulesList().getItems().setAll(
                getUnit().getUnitTemplate().getSpecialRules());

        if (getUnit().getUnitTemplate().getSpecialRules().isEmpty()) {
            getSpecialRulesBox().getChildren().clear();
        } else if (getSpecialRulesBox().getChildren().isEmpty()) {
            getSpecialRulesBox().getChildren().add(getSpecialRulesPane());
        }
    }

    private final void loadUnitsList() {
        final List<Unit> units;

        units = getUnits(getGangBuilderManager().getOptions().getUnitOptions());

        Collections.sort(units, new UnitNameComparator());

        getUnitsList().getItems().setAll(units);

        if (!getUnitsList().getItems().isEmpty()) {
            // getUnitsList().getSelectionModel().select(0);
        }
    }

    private final void loadWeaponsPane() {
        final Interval intervalWeapons;

        getSetUpWeaponPanes().clear();
        getWeaponsPane().getChildren().clear();
        getSetUpWeaponControllers().clear();

        intervalWeapons = getUnitConfigurationManager().getOptions()
                .getAllowedWeaponsInterval();

        initializeWeaponSelectionPanels(0, intervalWeapons.getUpperLimit());

        getSetUpWeaponControllers().poll();
    }

    private final void reset() {
        final Unit unit;

        unit = getUnitsList().getSelectionModel().getSelectedItem();

        if (unit != null) {
            setUnit(getUnitsList().getSelectionModel().getSelectedItem()
                    .createNewInstance());
        }
    }

    private final void setGroupButtonsStatus() {
        getDecreaseGroupButton().setDisable(
                !getGroupSizeController().isAbleToDecrease());
        getIncreaseGroupButton().setDisable(
                !getGroupSizeController().isAbleToIncrease());
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
                getDecreaseGroupButton()
                        .setDisable(
                                group.getValue() == groupSizeController
                                        .getLowerLimit());
                getIncreaseGroupButton()
                        .setDisable(
                                group.getValue() == groupSizeController
                                        .getUpperLimit());

                getGroupSizeController().setValueBox(group);
            }
        }
    }

}
