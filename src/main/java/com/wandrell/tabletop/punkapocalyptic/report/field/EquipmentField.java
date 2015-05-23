package com.wandrell.tabletop.punkapocalyptic.report.field;

import net.sf.dynamicreports.report.base.DRField;

import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.report.datatype.EquipmentDataType;

public final class EquipmentField extends DRField<Equipment> {

    private static final long serialVersionUID = 1L;

    public EquipmentField(final String fieldName) {
        super(fieldName, Equipment.class);

        setDataType(new EquipmentDataType());
    }

}
