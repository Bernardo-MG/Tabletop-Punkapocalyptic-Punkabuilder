package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.ruleset.command;

import com.wandrell.pattern.command.Command;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.RulesetServiceAware;
import com.wandrell.tabletop.punkapocalyptic.valuebox.derived.MaxUnitsDerivedValueViewPoint;
import com.wandrell.tabletop.valuebox.ValueBox;
import com.wandrell.tabletop.valuebox.derived.DerivedValueBox;

public final class SetUpMaxUnitsValueHandlerCommand implements Command,
        RulesetServiceAware {

    private final Gang     gang;
    private RulesetService serviceRuleset;
    private final ValueBox value;

    public SetUpMaxUnitsValueHandlerCommand(final ValueBox value,
            final Gang gang) {
        super();

        this.value = value;
        this.gang = gang;
    }

    @Override
    public void execute() throws Exception {
        if (value instanceof ValueBox) {
            if (((DerivedValueBox) value).getViewPoint() instanceof MaxUnitsDerivedValueViewPoint) {
                ((MaxUnitsDerivedValueViewPoint) ((DerivedValueBox) value)
                        .getViewPoint()).setGang(gang);
            } else {
                ((DerivedValueBox) value)
                        .setViewPoint(new MaxUnitsDerivedValueViewPoint(gang,
                                getRulesetService()));
            }
        }
    }

    @Override
    public final void setRulesetService(final RulesetService service) {
        serviceRuleset = service;
    }

    private final RulesetService getRulesetService() {
        return serviceRuleset;
    }

}
