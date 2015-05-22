package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.UnitWeapon;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.UnitWeaponDataType;

public final class UnitWeaponField extends DRField<UnitWeapon> {

    private static final long serialVersionUID = 1L;

    public UnitWeaponField(final String fieldName,
            final DRIValueFormatter<String, UnitWeapon> formatter) {
        super(fieldName, UnitWeapon.class);

        setDataType(new UnitWeaponDataType(formatter));
    }

}
