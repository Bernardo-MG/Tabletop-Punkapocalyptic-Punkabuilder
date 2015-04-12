package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.TableCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.GroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class UnitNameTableCell extends TableCell<Unit, Unit> {

    public UnitNameTableCell() {
        super();
    }

    @Override
    protected final void updateItem(final Unit item, final boolean empty) {
        String text;

        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            text = item.getName();

            if (item instanceof GroupedUnit) {
                text = String.format("%dx %s", ((GroupedUnit) item)
                        .getGroupSize().getValue(), text);
            }

            setText(text);
        }
    }
}
