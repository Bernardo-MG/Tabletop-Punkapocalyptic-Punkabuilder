package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class ArmorNameAndCostListCell extends ListCell<ArmorOption> {

    private final ModelLocalizationService localizationService;
    private final String                   template = "%s (%d)";

    public ArmorNameAndCostListCell(
            final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void
            updateItem(final ArmorOption item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(String.format(template, localizationService
                    .getArmorNameString(item.getArmor().getNameToken()), item
                    .getCost()));
        }
    }

}
