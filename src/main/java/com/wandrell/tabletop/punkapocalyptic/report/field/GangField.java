package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.unit.Gang;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.GangDataType;

public final class GangField extends DRField<Gang> {

    private static final long serialVersionUID = 1L;

    public GangField(final String fieldName,
            final DRIValueFormatter<String, Gang> formatter) {
        super(fieldName, Gang.class);

        setDataType(new GangDataType(formatter));
    }

}
