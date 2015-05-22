package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.faction.Faction;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.FactionDataType;

public final class FactionField extends DRField<Faction> {

    private static final long serialVersionUID = 1L;

    public FactionField(final String fieldName,
            final DRIValueFormatter<String, Faction> formatter) {
        super(fieldName, Faction.class);

        setDataType(new FactionDataType(formatter));
    }

}
