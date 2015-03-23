package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.RulesetServiceAware;

public final class GetGangCommand implements ReturnCommand<Gang>,
        RulesetServiceAware {

    private final Faction  faction;
    private RulesetService service;

    public GetGangCommand(final Faction faction) {
        super();

        this.faction = faction;
    }

    @Override
    public final Gang execute() {
        return ModelFactory.getInstance().getGang(faction, getRulesetService());
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        this.service = service;
    }

    private final RulesetService getRulesetService() {
        return service;
    }

}
