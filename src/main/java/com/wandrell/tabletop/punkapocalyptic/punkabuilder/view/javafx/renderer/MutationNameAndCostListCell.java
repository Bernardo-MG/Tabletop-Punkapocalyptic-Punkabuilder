package com.wandrell.tabletop.punkapocalyptic.punkabuilder.view.javafx.renderer;

import javafx.scene.control.ListCell;

import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.service.ModelLocalizationService;

public final class MutationNameAndCostListCell extends ListCell<Mutation> {

    private final ModelLocalizationService localizationService;
    private final String                   template = "%s (%d)";

    public MutationNameAndCostListCell(
            final ModelLocalizationService localizationService) {
        super();

        this.localizationService = localizationService;
    }

    @Override
    protected final void updateItem(final Mutation item, final boolean empty) {
        super.updateItem(item, empty);

        if (item == null || empty) {
            setText(" ");
            setStyle("");
        } else {
            setText(String
                    .format(template, localizationService
                            .getMutationNameString(item.getNameToken()), item
                            .getCost()));
        }
    }

}
