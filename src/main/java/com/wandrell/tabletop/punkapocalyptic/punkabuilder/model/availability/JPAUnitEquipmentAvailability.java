package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitEquipmentAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Equipment;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory.JPAEquipment;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.JPAUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "UnitEquipmentAvailability")
@Table(name = "unit_equipment")
public final class JPAUnitEquipmentAvailability implements
        UnitEquipmentAvailability, PersistenceEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_equipment_equipment", joinColumns = { @JoinColumn(
            name = "unit_equipment_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "equipment_id",
                    referencedColumnName = "id") })
    private final Collection<JPAEquipment> equipmentOptions = new LinkedList<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                        id               = -1;
    @OneToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private JPAUnitTemplate                unit;

    public JPAUnitEquipmentAvailability() {
        super();
    }

    public final void addEquipmentOption(final JPAEquipment equipment) {
        getEquipmentOptionsModifiable().add(equipment);
    }

    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        JPAUnitEquipmentAvailability other;

        other = (JPAUnitEquipmentAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Collection<Equipment> getEquipmentOptions() {
        return Collections
                .unmodifiableCollection(getEquipmentOptionsModifiable());
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final UnitTemplate getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    public final void removeEquipmentOption(final JPAEquipment equipment) {
        getEquipmentOptionsModifiable().remove(equipment);
    }

    public final void setEquipmentOptions(
            final Collection<JPAEquipment> equipment) {
        getEquipmentOptionsModifiable().clear();
        getEquipmentOptionsModifiable().addAll(equipment);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setUnit(final JPAUnitTemplate unit) {
        this.unit = unit;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("unit", unit.getNameToken())
                .add("equipment", equipmentOptions).toString();
    }

    private final Collection<JPAEquipment> getEquipmentOptionsModifiable() {
        return equipmentOptions;
    }

}
