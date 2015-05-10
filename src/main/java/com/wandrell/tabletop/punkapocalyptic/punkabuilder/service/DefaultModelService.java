package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.punkapocalyptic.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Service("modelService")
public final class DefaultModelService implements ModelService {

    private final ModelFactory   factory = ModelFactory.getInstance();
    private final RulesetService rulesetService;

    @Autowired
    public DefaultModelService(final RulesetService rulesetService) {
        super();

        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");

        this.rulesetService = rulesetService;
    }

    @Override
    public final Gang getGang(final Faction faction) {
        return ModelFactory.getInstance().getGang(faction, getRulesetService());
    }

    @Override
    public final Unit getUnit(final UnitTemplate template) {
        return getFactory().getUnit(template, getRulesetService());
    }

    private final ModelFactory getFactory() {
        return factory;
    }

    private final RulesetService getRulesetService() {
        return rulesetService;
    }

}
