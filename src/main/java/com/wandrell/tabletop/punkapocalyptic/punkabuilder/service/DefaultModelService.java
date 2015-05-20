package com.wandrell.tabletop.punkapocalyptic.punkabuilder.service;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.ModelService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;

@Service("modelService")
public final class DefaultModelService implements ModelService {

    private final ModelFactory        factory = ModelFactory.getInstance();
    private final LocalizationService localizationService;
    private final RulesetService      rulesetService;

    @Autowired
    public DefaultModelService(final RulesetService rulesetService,
            final LocalizationService localizationService) {
        super();

        checkNotNull(rulesetService,
                "Received a null pointer as ruleset service");
        checkNotNull(localizationService,
                "Received a null pointer as localization service");

        this.rulesetService = rulesetService;
        this.localizationService = localizationService;
    }

    @Override
    public final Constraint getConstraint(final Gang gang, final String name,
            final String unit, final List<String> context) {
        return getFactory().getConstraint(gang, name, unit, context,
                getLocalizationService());
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

    private final LocalizationService getLocalizationService() {
        return localizationService;
    }

    private final RulesetService getRulesetService() {
        return rulesetService;
    }

}
