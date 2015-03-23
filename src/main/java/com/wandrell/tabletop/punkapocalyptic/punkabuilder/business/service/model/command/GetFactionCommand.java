package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.faction.DefaultFaction;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;

public final class GetFactionCommand implements ReturnCommand<Faction> {

    private final String name;

    public GetFactionCommand(final String name) {
        super();

        this.name = name;
    }

    @Override
    public final Faction execute() {
        return new DefaultFaction(name);
    }

}
