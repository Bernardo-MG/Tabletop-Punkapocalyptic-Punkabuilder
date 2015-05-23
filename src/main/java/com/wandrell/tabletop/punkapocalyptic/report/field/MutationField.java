package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;

import com.wandrell.tabletop.punkapocalyptic.model.unit.mutation.Mutation;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.MutationDataType;

public final class MutationField extends DRField<Mutation> {

    private static final long serialVersionUID = 1L;

    public MutationField(final String fieldName) {
        super(fieldName, Mutation.class);

        setDataType(new MutationDataType());
    }

}
