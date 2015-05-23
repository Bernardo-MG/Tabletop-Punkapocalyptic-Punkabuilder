package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;

import com.wandrell.tabletop.punkapocalyptic.model.ruleset.SpecialRule;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.SpecialRulesDataType;

public final class SpecialRuleField extends DRField<SpecialRule> {

    private static final long serialVersionUID = 1L;

    public SpecialRuleField(final String fieldName) {
        super(fieldName, SpecialRule.class);

        setDataType(new SpecialRulesDataType());
    }

}
