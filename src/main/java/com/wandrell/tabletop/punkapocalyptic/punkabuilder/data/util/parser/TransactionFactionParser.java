package com.wandrell.tabletop.punkapocalyptic.punkabuilder.data.util.parser;

import java.util.Map;

import com.wandrell.pattern.parser.Parser;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;

public final class TransactionFactionParser implements
        Parser<Map<String, Object>, Faction> {

    private final ModelService service;

    public TransactionFactionParser(final ModelService service) {
        super();

        this.service = service;
    }

    @Override
    public final Faction parse(final Map<String, Object> input) {
        return service.getFaction(input.get("name").toString());
    }

}
