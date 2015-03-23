package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetEquipmentCommand implements ReturnCommand<Equipment> {

    private final Integer cost;
    private final String  name;

    public GetEquipmentCommand(final String name, final Integer cost) {
        super();

        this.name = name;
        this.cost = cost;
    }

    @Override
    public final Equipment execute() {
        return ModelFactory.getInstance().getEquipment(name, cost);
    }

}
