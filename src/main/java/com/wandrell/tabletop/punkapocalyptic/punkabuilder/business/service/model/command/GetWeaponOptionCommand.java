package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import java.util.Collection;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.availability.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetWeaponOptionCommand implements
        ReturnCommand<WeaponOption> {

    private final Collection<WeaponEnhancement> enhancements;
    private final Weapon                        weapon;

    public GetWeaponOptionCommand(final Weapon weapon,
            final Collection<WeaponEnhancement> enhancements) {
        super();

        this.weapon = weapon;
        this.enhancements = enhancements;
    }

    @Override
    public final WeaponOption execute() {
        return ModelFactory.getInstance().getWeaponOption(weapon, enhancements);
    }

}
