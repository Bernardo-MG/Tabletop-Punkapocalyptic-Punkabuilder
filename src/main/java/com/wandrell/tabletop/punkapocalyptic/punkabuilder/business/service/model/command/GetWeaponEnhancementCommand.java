package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import static com.google.common.base.Preconditions.checkNotNull;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetWeaponEnhancementCommand implements
        ReturnCommand<WeaponEnhancement> {

    private final Integer cost;
    private final String  name;

    public GetWeaponEnhancementCommand(final String name, final Integer cost) {
        super();

        checkNotNull(name, "Received a null pointer as name");
        checkNotNull(cost, "Received a null pointer as cost");

        this.name = name;
        this.cost = cost;
    }

    @Override
    public final WeaponEnhancement execute() {
        return ModelFactory.getInstance().getWeaponEnhancement(name, cost);
    }
}
