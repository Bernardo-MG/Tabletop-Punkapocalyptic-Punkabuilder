package com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer;

import javafx.scene.control.TableCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;

public final class UnitValorationTableCell extends TableCell<Unit, Integer> {

    public UnitValorationTableCell() {
        super();
    }

    @Override
    protected final void updateItem(final Integer item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(null);
            setStyle("");
        } else {
            setText(Integer.toString(item));
        }
    }

}
