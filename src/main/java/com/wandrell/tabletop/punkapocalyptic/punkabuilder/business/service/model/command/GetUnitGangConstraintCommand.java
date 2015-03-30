package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.util.tag.service.LocalizationServiceAware;

public final class GetUnitGangConstraintCommand implements
        ReturnCommand<Constraint>, LocalizationServiceAware {

    private Constraint          constraint;
    private final String[]      context;
    private final String        name;
    private LocalizationService service;
    private final String        unit;

    public GetUnitGangConstraintCommand(final String name, final String unit,
            final String... context) {
        super();

        this.name = name;
        this.unit = unit;
        this.context = context;
    }

    @Override
    public final void execute() {
        constraint = ModelFactory.getInstance().getConstraint(name, unit,
                getContext(), getLocalizationService());
    }

    @Override
    public final Constraint getResult() {
        return constraint;
    }

    @Override
    public final void setLocalizationService(final LocalizationService service) {
        this.service = service;
    }

    private final String[] getContext() {
        return context;
    }

    private final LocalizationService getLocalizationService() {
        return service;
    }

}
