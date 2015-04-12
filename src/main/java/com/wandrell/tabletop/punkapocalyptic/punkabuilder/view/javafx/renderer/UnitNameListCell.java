package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class UnitNameListCell extends ListCell<Unit> {

    public UnitNameListCell() {
        super();
    }

    @Override
    protected final void updateItem(final Unit item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            setText(item.getName());
        }
    }
}
