package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class UnitNameListCell extends ListCell<Unit> {

    private final ModelLocalizationService localizationService;

    public UnitNameListCell(final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void updateItem(final Unit item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            setText(localizationService.getUnitNameString(item.getName()));
        }
    }
}
