package com.wandrell.tabletop.punkapocalyptic.punkabuilder.presentation.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;

public final class MutationNameListCell extends ListCell<Mutation> {

    public MutationNameListCell() {
        super();
    }

    @Override
    protected final void updateItem(final Mutation item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(item.getName());
        }
    }
}
