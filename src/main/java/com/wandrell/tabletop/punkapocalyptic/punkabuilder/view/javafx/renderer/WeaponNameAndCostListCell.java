package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class WeaponNameAndCostListCell extends ListCell<UnitWeapon> {

    private final ModelLocalizationService localizationService;
    private final String                   patternWeapon = "%s (%d)";

    public WeaponNameAndCostListCell(
            final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void updateItem(final UnitWeapon item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(String.format(patternWeapon, localizationService
                    .getWeaponNameString(item.getTemplate().getNameToken()),
                    item.getCost()));
        }
    }

}
