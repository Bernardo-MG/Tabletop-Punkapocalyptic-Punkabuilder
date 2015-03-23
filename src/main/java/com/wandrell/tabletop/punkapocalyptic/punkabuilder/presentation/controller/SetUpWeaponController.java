package com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.controller;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Comparator;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.SpecialRuleListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer.WeaponNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.util.WeaponUtils;
import com.wandrell.tabletop.punkapocalyptic.util.tag.UnitAware;

public final class SetUpWeaponController {

    @FXML
    private Label                          cmLongLabel;
    @FXML
    private Label                          cmMediumLabel;
    @FXML
    private Label                          cmShortLabel;
    @FXML
    private Label                          combatLabel;
    private Weapon                         current;
    private ObservableList<Node>           enhancementNodes;
    @FXML
    private Pane                           enhancementPane;
    @FXML
    private Label                          inchLongLabel;
    @FXML
    private Label                          inchMediumLabel;
    @FXML
    private Label                          inchShortLabel;
    @FXML
    private Label                          penetrationLabel;
    @FXML
    private Button                         pickButton;
    @FXML
    private ListView<SpecialRule>          specialRulesList;
    @FXML
    private Label                          strengthLabel;
    private Unit                           unit;
    private final UnitConfigurationManager unitConfigManager;
    @FXML
    private ComboBox<Weapon>               weaponCombo;
    private final ObservableList<Weapon>   weapons = FXCollections
                                                           .observableArrayList();

    public SetUpWeaponController(
            final UnitConfigurationManager unitConfigManager) {
        super();

        this.unitConfigManager = unitConfigManager;
    }

    public final void addOnPickEventHandler(
            final EventHandler<ActionEvent> handler) {
        checkNotNull(handler, "Received a null pointer as event handler");

        // TODO: Add the dependency instead of the handler
        getPickButton().addEventHandler(ActionEvent.ACTION, handler);
    }

    public final void clear() {
        getWeaponComboBox().getItems().clear();
        getSpecialRulesList().getItems().clear();
        getEnhancementNodes().clear();

        getInchesShortLabel().setText("0");
        getInchesMediumLabel().setText("0");
        getInchesLongLabel().setText("0");

        getCMShortLabel().setText("0");
        getCMMediumLabel().setText("0");
        getCMLongLabel().setText("0");

        getStrengthLabel().setText("0");
        getCombatLabel().setText("0");
        getPenetrationLabel().setText("0");
    }

    @FXML
    public final void initialize() {
        enhancementNodes = getEnhancementsPane().getChildren();

        initializeWeaponComboBox();

        // TODO: Use a method handler
        getPickButton().addEventHandler(ActionEvent.ACTION,
                new EventHandler<ActionEvent>() {
                    @Override
                    public final void handle(final ActionEvent event) {
                        getUnit().addWeapon(current);
                        setEnabled(false);
                    }
                });

        getSpecialRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell();
        });
    }

    public final void loadWeapons() {
        final Comparator<Weapon> comparator;

        // TODO: Get the comparator out of here
        comparator = (Weapon o1, Weapon o2) -> o1.getCost().compareTo(
                o2.getCost());

        getWeapons().setAll(getUnitConfigurationManager().getWeaponOptions());

        FXCollections.sort(getWeapons(), comparator);

        if (getWeaponComboBox().getItems().size() > 0) {
            getWeaponComboBox().getSelectionModel().select(0);
        }
    }

    public final void setEnabled(final Boolean enabled) {
        checkNotNull(enabled, "Received a null pointer as enabled flag");

        getWeaponComboBox().setDisable(!enabled);
        getPickButton().setDisable(!enabled);

        for (final Node node : getEnhancementNodes()) {
            node.setDisable(!enabled);
        }
    }

    public final void setUnit(final Unit unit) {
        checkNotNull(unit, "Received a null pointer as unit");

        this.unit = unit;
    }

    private final Label getCMLongLabel() {
        return cmLongLabel;
    }

    private final Label getCMMediumLabel() {
        return cmMediumLabel;
    }

    private final Label getCMShortLabel() {
        return cmShortLabel;
    }

    private final Label getCombatLabel() {
        return combatLabel;
    }

    private final ObservableList<Node> getEnhancementNodes() {
        return enhancementNodes;
    }

    private final Pane getEnhancementsPane() {
        return enhancementPane;
    }

    private final Label getInchesLongLabel() {
        return inchLongLabel;
    }

    private final Label getInchesMediumLabel() {
        return inchMediumLabel;
    }

    private final Label getInchesShortLabel() {
        return inchShortLabel;
    }

    private final Label getPenetrationLabel() {
        return penetrationLabel;
    }

    private final Button getPickButton() {
        return pickButton;
    }

    private final ListView<SpecialRule> getSpecialRulesList() {
        return specialRulesList;
    }

    private final Label getStrengthLabel() {
        return strengthLabel;
    }

    private final Unit getUnit() {
        return unit;
    }

    private final UnitConfigurationManager getUnitConfigurationManager() {
        return unitConfigManager;
    }

    private final ComboBox<Weapon> getWeaponComboBox() {
        return weaponCombo;
    }

    private final ObservableList<Weapon> getWeapons() {
        return weapons;
    }

    private final void initializeWeaponComboBox() {
        getWeaponComboBox().setButtonCell(new WeaponNameAndCostListCell());

        getWeaponComboBox().setCellFactory(column -> {
            return new WeaponNameAndCostListCell();
        });

        getWeaponComboBox()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                final Weapon weapon;

                                weapon = newValue.createNewInstance();
                                if (newValue instanceof UnitAware) {
                                    ((UnitAware) weapon)
                                            .setUnit(getUnitConfigurationManager()
                                                    .getUnit());
                                }

                                loadWeaponData(weapon);
                                loadWeaponEnhancements(getUnitConfigurationManager()
                                        .getWeaponEnhancements(weapon));
                            }
                        });

        getWeaponComboBox().setItems(getWeapons());
    }

    private final void loadWeaponData(final Weapon weapon) {
        current = weapon;
        if (weapon instanceof RangedWeapon) {
            getStrengthLabel().setText(
                    WeaponUtils.getRangedWeaponStrength((RangedWeapon) weapon));
        } else {
            getStrengthLabel().setText(
                    ((MeleeWeapon) weapon).getStrength().toString());
        }

        if (weapon instanceof RangedWeapon) {
            getPenetrationLabel().setText(
                    WeaponUtils
                            .getRangedWeaponPenetration((RangedWeapon) weapon));
        } else {
            getPenetrationLabel().setText(
                    ((MeleeWeapon) weapon).getPenetration().toString());
        }

        if (weapon instanceof RangedWeapon) {
            getCombatLabel().setText("-");
        } else {
            getCombatLabel().setText(
                    ((MeleeWeapon) weapon).getCombat().toString());
        }

        if (weapon instanceof RangedWeapon) {
            final RangedWeapon w;

            w = (RangedWeapon) weapon;
            getInchesShortLabel().setText(
                    w.getDistancesImperialUnits().getShortValue().toString());
            getInchesMediumLabel().setText(
                    w.getDistancesImperialUnits().getMediumValue().toString());
            getInchesLongLabel().setText(
                    w.getDistancesImperialUnits().getLongValue().toString());

            getCMShortLabel().setText(
                    w.getDistancesMetricSystem().getShortValue().toString());
            getCMMediumLabel().setText(
                    w.getDistancesMetricSystem().getMediumValue().toString());
            getCMLongLabel().setText(
                    w.getDistancesMetricSystem().getLongValue().toString());
        } else {
            getInchesShortLabel().setText("-");
            getInchesMediumLabel().setText("-");
            getInchesLongLabel().setText("-");

            getCMShortLabel().setText("-");
            getCMMediumLabel().setText("-");
            getCMLongLabel().setText("-");
        }

        getSpecialRulesList().getItems().setAll(weapon.getSpecialRules());
    }

    private final void loadWeaponEnhancements(
            final Collection<WeaponEnhancement> enhancements) {
        final String template = "%s (%d)";
        String name;

        getEnhancementNodes().clear();

        for (final WeaponEnhancement enhancement : enhancements) {
            final CheckBox check;

            name = String.format(template, enhancement.getName(),
                    enhancement.getCost());

            check = new CheckBox(name);

            getEnhancementNodes().add(check);

            check.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> ov,
                        Boolean old_val, Boolean new_val) {
                    if ((new_val) && (!old_val)) {
                        current.addEnhancement(enhancement);
                    }

                    if ((!new_val) && (old_val)) {
                        current.removeEnhancement(enhancement);
                    }
                }
            });
        }
    }

}
