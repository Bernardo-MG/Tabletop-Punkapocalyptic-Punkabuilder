package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Collection;
import java.util.Properties;

import com.wandrell.pattern.command.CommandExecutor;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.MeleeWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command.GetTwoHandedMeleeEquivalentCommand;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command.SetUpMaxUnitsValueHandlerCommand;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.service.ruleset.command.FilterWeaponOptionsCommand;
import com.wandrell.tabletop.punkapocalyptic.service.ruleset.command.GetGangValorationCommand;
import com.wandrell.tabletop.punkapocalyptic.service.ruleset.command.GetMaxAllowedUnitsCommand;
import com.wandrell.tabletop.punkapocalyptic.service.ruleset.command.GetUnitValorationCommand;
import com.wandrell.tabletop.valuebox.ValueBox;

public final class DesktopRulesetService implements RulesetService {

    private final CommandExecutor executor;
    private final Properties      rulesConfig;

    public DesktopRulesetService(final CommandExecutor executor,
            final Properties properties) {
        super();

        checkNotNull(executor, "Received a null pointer as executor");
        checkNotNull(properties, "Received a null pointer as properties");

        this.executor = executor;

        rulesConfig = properties;
    }

    @Override
    public final Collection<Weapon> filterWeaponOptions(
            final Collection<Weapon> weaponsHas,
            final Collection<Weapon> weapons) {
        return getExecutor().execute(
                new FilterWeaponOptionsCommand(weaponsHas, weapons));
    }

    @Override
    public final Integer getBulletCost() {
        return Integer.parseInt(getRulesConfiguration().getProperty(
                "bullet_cost"));
    }

    @Override
    public final Integer getGangValoration(final Gang gang) {
        return getExecutor().execute(new GetGangValorationCommand(gang));
    }

    @Override
    public final Integer getMaxAllowedUnits(final Gang gang) {
        return getExecutor().execute(new GetMaxAllowedUnitsCommand(gang));
    }

    @Override
    public final Integer getPackMaxSize() {
        return Integer.parseInt(getRulesConfiguration().getProperty(
                "pack_max_size"));
    }

    @Override
    public final MeleeWeapon getTwoHandedMeleeEquivalent() {
        return getExecutor().execute(new GetTwoHandedMeleeEquivalentCommand());
    }

    @Override
    public final Integer getUnitValoration(final Unit unit) {
        return getExecutor().execute(new GetUnitValorationCommand(unit));
    }

    @Override
    public final void setUpMaxUnitsValueHandler(final ValueBox value,
            final Gang gang) {
        getExecutor()
                .execute(new SetUpMaxUnitsValueHandlerCommand(value, gang));
    }

    private final CommandExecutor getExecutor() {
        return executor;
    }

    private final Properties getRulesConfiguration() {
        return rulesConfig;
    }

}
