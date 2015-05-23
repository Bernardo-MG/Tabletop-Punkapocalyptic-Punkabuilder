package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.WeaponEnhancementDataType;

public final class WeaponEnhancementField extends DRField<WeaponEnhancement> {

    private static final long serialVersionUID = 1L;

    public WeaponEnhancementField(final String fieldName) {
        super(fieldName, WeaponEnhancement.class);

        setDataType(new WeaponEnhancementDataType());
    }

}
