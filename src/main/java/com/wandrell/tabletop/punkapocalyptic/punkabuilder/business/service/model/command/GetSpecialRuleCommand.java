package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.RulesetServiceAware;

public final class GetSpecialRuleCommand implements ReturnCommand<SpecialRule>,
        RulesetServiceAware {

    private final String   name;
    private RulesetService service;

    public GetSpecialRuleCommand(final String name) {
        super();

        this.name = name;
    }

    @Override
    public final SpecialRule execute() {
        return ModelFactory.getInstance().getSpecialRule(name,
                getRulesetService());
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        this.service = service;
    }

    private final RulesetService getRulesetService() {
        return service;
    }

}
