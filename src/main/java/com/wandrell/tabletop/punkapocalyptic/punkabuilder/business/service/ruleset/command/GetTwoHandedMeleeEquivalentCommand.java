package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command;

import com.google.common.base.Predicate;
import com.wandrell.pattern.command.ResultCommand;
import com.wandrell.pattern.repository.QueryableRepository;
import com.wandrell.tabletop.punkapocalyptic.conf.WeaponNameConf;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.util.tag.repository.WeaponRepositoryAware;

public final class GetTwoHandedMeleeEquivalentCommand implements
        ResultCommand<MeleeWeapon>, WeaponRepositoryAware {

    private MeleeWeapon                                    weapon;
    private QueryableRepository<Weapon, Predicate<Weapon>> weaponRepo;

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
    public final void setWeaponRepository(
            final QueryableRepository<Weapon, Predicate<Weapon>> repository) {
        weaponRepo = repository;
    }

    private final QueryableRepository<Weapon, Predicate<Weapon>>
            getUnitWeaponRepository() {
        return weaponRepo;
    }

}
