package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class SpecialRuleListCell extends ListCell<SpecialRule> {

    private final ModelLocalizationService localizationService;

    public SpecialRuleListCell(
            final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void
            updateItem(final SpecialRule item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(localizationService.getSpecialRuleNameString(item
                    .getNameToken()));
        }
    }
}
