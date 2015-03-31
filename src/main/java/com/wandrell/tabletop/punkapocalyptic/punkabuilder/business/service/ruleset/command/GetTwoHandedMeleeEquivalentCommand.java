package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command;

import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.pattern.repository.Repository;
import com.wandrell.tabletop.punkapocalyptic.conf.WeaponNameConf;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.util.tag.repository.WeaponRepositoryAware;

public final class GetTwoHandedMeleeEquivalentCommand implements
        ResultCommand<MeleeWeapon>, WeaponRepositoryAware {

    private MeleeWeapon        weapon;
    private Repository<Weapon> weaponRepo;

    public GetTwoHandedMeleeEquivalentCommand() {
        super();
    }

    @Override
    public final void execute() {
        weapon = (MeleeWeapon) getUnitWeaponRepository()
                .getCollection(
                        w -> w.getName().equals(
                                WeaponNameConf.IMPROVISED_WEAPON)).iterator()
                .next();
    }

    @Override
    public final MeleeWeapon getResult() {
        return weapon;
    }

    @Override
    public final void setWeaponRepository(final Repository<Weapon> repository) {
        weaponRepo = repository;
    }

    private final Repository<Weapon> getUnitWeaponRepository() {
        return weaponRepo;
    }

}
