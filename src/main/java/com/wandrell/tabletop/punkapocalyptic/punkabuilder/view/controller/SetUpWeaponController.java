package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.controller;

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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitDependantWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.procedure.UnitConfigurationManager;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.SpecialRuleListCell;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer.WeaponNameAndCostListCell;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.WeaponUtils;

@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public final class SetUpWeaponController {

    @FXML
    private Label                            cmLongLabel;
    @FXML
    private Label                            cmMediumLabel;
    @FXML
    private Label                            cmShortLabel;
    @FXML
    private Label                            combatLabel;
    private UnitWeapon                       current;
    private ObservableList<Node>             enhancementNodes;
    @FXML
    private Pane                             enhancementPane;
    @FXML
    private Label                            inchLongLabel;
    @FXML
    private Label                            inchMediumLabel;
    @FXML
    private Label                            inchShortLabel;
    private final ModelLocalizationService   localizationService;
    @FXML
    private Label                            penetrationLabel;
    @FXML
    private Button                           pickButton;
    @FXML
    private ListView<SpecialRule>            specialRulesList;
    @FXML
    private Label                            strengthLabel;
    private Unit                             unit;
    private final UnitConfigurationManager   unitConfigManager;
    @FXML
    private ComboBox<UnitWeapon>             weaponCombo;
    private final ObservableList<UnitWeapon> weapons = FXCollections
                                                             .observableArrayList();

    @Autowired
    public SetUpWeaponController(
            final UnitConfigurationManager unitConfigManager,
            final ModelLocalizationService localizationService) {
        super();

        this.unitConfigManager = unitConfigManager;
        this.localizationService = localizationService;
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
        final EventHandler<ActionEvent> handler;

        enhancementNodes = getEnhancementsPane().getChildren();

        initializeWeaponComboBox();

        getSpecialRulesList().setCellFactory(column -> {
            return new SpecialRuleListCell(localizationService);
        });

        handler = new EventHandler<ActionEvent>() {

            @Override
            public final void handle(final ActionEvent event) {
                getUnit().addWeapon(current);
                setEnabled(false);
            }

        };

        getPickButton().addEventHandler(ActionEvent.ACTION, handler);
    }

    public final void setEnabled(final Boolean enabled) {
        checkNotNull(enabled, "Received a null pointer as enabled flag");

        getWeaponComboBox().setDisable(!enabled);
        getPickButton().setDisable(!enabled);

        for (final Node node : getEnhancementNodes()) {
            node.setDisable(!enabled);
        }

        if (enabled) {
            loadWeapons();
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

    private final ComboBox<UnitWeapon> getWeaponComboBox() {
        return weaponCombo;
    }

    private final ObservableList<UnitWeapon> getWeapons() {
        return weapons;
    }

    private final void initializeWeaponComboBox() {
        getWeaponComboBox().setButtonCell(
                new WeaponNameAndCostListCell(localizationService));

        getWeaponComboBox().setCellFactory(column -> {
            return new WeaponNameAndCostListCell(localizationService);
        });

        getWeaponComboBox()
                .getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (observable, oldValue, newValue) -> {
                            if (newValue != null) {
                                final UnitWeapon weapon;

                                weapon = newValue;
                                if (newValue.getWeaponTemplate() instanceof UnitDependantWeapon) {
                                    ((UnitDependantWeapon) weapon
                                            .getWeaponTemplate())
                                            .setUnit(getUnitConfigurationManager()
                                                    .getUnit());
                                }

                                loadWeaponData(weapon);
                                loadWeaponEnhancements(getUnitConfigurationManager()
                                        .getOptions().getWeaponEnhancements(
                                                weapon));
                            }
                        });

        getWeaponComboBox().setItems(getWeapons());
    }

    private final void loadWeaponData(final UnitWeapon weapon) {
        final Weapon template;

        template = weapon.getWeaponTemplate();

        current = weapon;
        if (template instanceof RangedWeapon) {
            getStrengthLabel().setText(
                    WeaponUtils
                            .getRangedWeaponStrength((RangedWeapon) template));
        } else {
            getStrengthLabel().setText(
                    ((MeleeWeapon) template).getStrength().toString());
        }

        if (template instanceof RangedWeapon) {
            getPenetrationLabel()
                    .setText(
                            WeaponUtils
                                    .getRangedWeaponPenetration((RangedWeapon) template));
        } else {
            getPenetrationLabel().setText(
                    ((MeleeWeapon) template).getPenetration().toString());
        }

        if (template instanceof RangedWeapon) {
            getCombatLabel().setText("-");
        } else {
            getCombatLabel().setText(
                    ((MeleeWeapon) template).getCombat().toString());
        }

        if (template instanceof RangedWeapon) {
            final RangedWeapon w;

            w = (RangedWeapon) template;
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

        getSpecialRulesList().getItems().setAll(template.getSpecialRules());
    }

    private final void loadWeaponEnhancements(
            final Collection<WeaponEnhancement> enhancements) {
        final String template = "%s (%d)";
        String name;

        getEnhancementNodes().clear();

        for (final WeaponEnhancement enhancement : enhancements) {
            final CheckBox check;

            name = String.format(template, enhancement.getNameToken(),
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

    private final void loadWeapons() {
        final Comparator<UnitWeapon> comparator;

        // TODO: Get the comparator out of here
        comparator = (UnitWeapon o1, UnitWeapon o2) -> o1.getCost().compareTo(
                o2.getCost());

        getWeapons().setAll(
                getUnitConfigurationManager().getOptions().getWeaponOptions());

        FXCollections.sort(getWeapons(), comparator);

        if (getWeaponComboBox().getItems().size() > 0) {
            getWeaponComboBox().getSelectionModel().select(0);
        }
    }

}
