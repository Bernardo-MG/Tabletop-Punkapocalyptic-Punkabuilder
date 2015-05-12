package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.TableCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.GroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class UnitNameTableCell extends TableCell<Unit, Unit> {

    private final ModelLocalizationService localizationService;

    public UnitNameTableCell(final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void updateItem(final Unit item, final boolean empty) {
        String text;

        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            text = localizationService.getUnitNameString(item.getName());

            if (item instanceof GroupedUnit) {
                text = String.format("%dx %s", ((GroupedUnit) item)
                        .getGroupSize().getValue(), text);
            }

            setText(text);
        }
    }
}
