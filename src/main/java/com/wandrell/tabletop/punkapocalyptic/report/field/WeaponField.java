package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.WeaponDataType;

public final class WeaponField extends DRField<Weapon> {

    private static final long serialVersionUID = 1L;

    public WeaponField(final String fieldName,
            final DRIValueFormatter<String, Weapon> formatter) {
        super(fieldName, Weapon.class);

        setDataType(new WeaponDataType(formatter));
    }

}
