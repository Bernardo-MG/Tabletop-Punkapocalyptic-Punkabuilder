package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.ArmorOptionDataType;

public final class ArmorOptionField extends DRField<ArmorOption> {

    private static final long serialVersionUID = 1L;

    public ArmorOptionField(final String fieldName,
            final DRIValueFormatter<String, ArmorOption> formatter) {
        super(fieldName, ArmorOption.class);

        setDataType(new ArmorOptionDataType(formatter));
    }

}
