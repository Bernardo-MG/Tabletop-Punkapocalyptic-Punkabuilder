package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;

public final class SpecialRuleListCell extends ListCell<SpecialRule> {

    public SpecialRuleListCell() {
        super();
    }

    @Override
    protected final void
            updateItem(final SpecialRule item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(item.getNameToken());
        }
    }
}
