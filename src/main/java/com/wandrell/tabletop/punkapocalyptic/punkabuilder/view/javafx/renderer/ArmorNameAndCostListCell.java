package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;

public final class ArmorNameAndCostListCell extends ListCell<ArmorOption> {

    private final String template = "%s (%d)";

    public ArmorNameAndCostListCell() {
        super();
    }

    @Override
    protected final void
            updateItem(final ArmorOption item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(String.format(template, item.getArmor().getName(),
                    item.getCost()));
        }
    }

}
