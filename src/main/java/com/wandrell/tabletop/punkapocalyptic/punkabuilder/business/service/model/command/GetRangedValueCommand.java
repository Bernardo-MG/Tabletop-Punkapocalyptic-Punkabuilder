package com.wandrell.tabletop.punkapocalyptic.punkabuilder.business.service.model.command;

import com.wandrell.pattern.command.ReturnCommand;
import com.wandrell.tabletop.punkapocalyptic.model.util.RangedValue;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.conf.factory.ModelFactory;

public final class GetRangedValueCommand implements ReturnCommand<RangedValue> {

    private final Integer distanceMedium;
    private final Integer distanceShort;
    final Integer         distanceLong;

    public GetRangedValueCommand(final Integer distanceShort,
            final Integer distanceMedium, final Integer distanceLong) {
        super();

        this.distanceShort = distanceShort;
        this.distanceMedium = distanceMedium;
        this.distanceLong = distanceLong;
    }

    @Override
    public final RangedValue execute() {
        return ModelFactory.getInstance().getRangedValue(distanceShort,
                distanceMedium, distanceLong);
    }

}
