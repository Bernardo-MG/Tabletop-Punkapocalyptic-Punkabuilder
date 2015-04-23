package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import java.util.Collection;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.repository.WeaponRepository;
import com.wandrell.tabletop.punkapocalyptic.service.DefaultRulesetService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Service("rulesetService")
public final class DesktopRulesetService implements RulesetService {

    private final RulesetService baseService;

    @Autowired
    public DesktopRulesetService(final Properties rulesetConfig,
            final WeaponRepository weaponRepo) {
        super();

        baseService = new DefaultRulesetService(rulesetConfig, weaponRepo);
    }

    @Override
    public final Collection<Weapon> filterWeaponOptions(
            final Collection<Weapon> weaponsHas,
            final Collection<Weapon> weapons) {
        return getBaseService().filterWeaponOptions(weaponsHas, weapons);
    }

    @Override
    public final Integer getBulletCost() {
        return getBaseService().getBulletCost();
    }

    @Override
    public final Integer getGangValoration(final Gang gang) {
        return getBaseService().getGangValoration(gang);
    }

    @Override
    public final Integer getMaxAllowedUnits(final Gang gang) {
        return getBaseService().getMaxAllowedUnits(gang);
    }

    @Override
    public final Integer getPackMaxSize() {
        return getBaseService().getPackMaxSize();
    }

    @Override
    public final MeleeWeapon getTwoHandedMeleeEquivalent() {
        return getBaseService().getTwoHandedMeleeEquivalent();
    }

    @Override
    public final Integer getUnitValoration(final Unit unit) {
        return getBaseService().getUnitValoration(unit);
    }

    private final RulesetService getBaseService() {
        return baseService;
    }

}
