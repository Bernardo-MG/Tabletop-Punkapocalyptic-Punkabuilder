package com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory;

import java.util.Collection;
import java.util.Iterator;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.wandrell.tabletop.procedure.Constraint;
import com.wandrell.tabletop.punkapocalyptic.conf.ConstraintsConf;
import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultGang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultGang.ValorationBuilder;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultGroupedUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.DefaultUnit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.procedure.constraint.DependantUnitConstraint;
import com.wandrell.tabletop.punkapocalyptic.procedure.constraint.UnitUpToACountConstraint;
import com.wandrell.tabletop.punkapocalyptic.procedure.constraint.UnitUpToHalfGangLimitConstraint;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.MessageBundleKey;
import com.wandrell.tabletop.punkapocalyptic.service.LocalizationService;
import com.wandrell.tabletop.punkapocalyptic.service.RulesetService;
import com.wandrell.tabletop.punkapocalyptic.valuebox.GangValorationValueBox;
import com.wandrell.tabletop.stats.valuebox.ValueBox;

public final class ModelFactory {

    private static final ModelFactory INSTANCE = new ModelFactory();

    public static final ModelFactory getInstance() {
        return INSTANCE;
    }

    private ModelFactory() {
        super();
    }

    public final Constraint getConstraint(final Gang gang, final String name,
            final String unit, final Collection<String> context,
            final LocalizationService service) {
        final Constraint constraint;
        final String message;
        final Iterator<String> itrContext;
        final String context1;
        final String context2;
        // TODO: The localization service should not be required in here

        switch (name) {
            case ConstraintsConf.UNIQUE:
                message = String
                        .format(service
                                .getMessageString(MessageBundleKey.UNIQUE),
                                unit);

                constraint = new UnitUpToACountConstraint(gang, unit, 1,
                        message);
                break;
            case ConstraintsConf.UP_TO_HALF_POINTS:
                message = String.format(
                        service.getMessageString(MessageBundleKey.HALF_GANG),
                        unit);

                constraint = new UnitUpToHalfGangLimitConstraint(gang, unit,
                        message);
                break;
            case ConstraintsConf.DEPENDANT:
                itrContext = context.iterator();

                context1 = itrContext.next();
                context2 = itrContext.next();

                message = String.format(service
                        .getMessageString(MessageBundleKey.DEPENDS_ON_UNIT),
                        unit, context1, Integer.parseInt(context2));

                constraint = new DependantUnitConstraint(gang, context1, unit,
                        Integer.parseInt(context2), message);
                break;
            default:
                constraint = null;
        }

        return constraint;
    }

    public final Gang getGang(final Faction faction,
            final RulesetService service) {
        final ValorationBuilder valorationBuilder;
        final Gang gang;

        valorationBuilder = new DefaultGang.ValorationBuilder() {

            @Override
            public final ValueBox getValoration(final Gang gang) {
                return new GangValorationValueBox(gang, service);
            }

        };
        gang = new DefaultGang(faction, valorationBuilder);

        return gang;
    }

    public final Unit getUnit(final UnitTemplate template,
            final RulesetService service) {
        final Collection<? extends SpecialRule> filtered;
        Unit unit;

        filtered = Collections2.filter(template.getSpecialRules(),
                new Predicate<SpecialRule>() {

                    @Override
                    public final boolean apply(final SpecialRule input) {
                        return input.getNameToken().equals("pack");
                    }

                });

        if (filtered.isEmpty()) {
            unit = new DefaultUnit(template);
        } else {
            unit = new DefaultGroupedUnit(template);
        }

        return unit;
    }

}
