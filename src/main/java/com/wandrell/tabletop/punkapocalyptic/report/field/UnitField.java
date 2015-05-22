package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Unit;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.UnitDataType;

public final class UnitField extends DRField<Unit> {

    private static final long serialVersionUID = 1L;

    public UnitField(final String fieldName,
            final DRIValueFormatter<String, Unit> formatter) {
        super(fieldName, Unit.class);

        setDataType(new UnitDataType(formatter));
    }

}
