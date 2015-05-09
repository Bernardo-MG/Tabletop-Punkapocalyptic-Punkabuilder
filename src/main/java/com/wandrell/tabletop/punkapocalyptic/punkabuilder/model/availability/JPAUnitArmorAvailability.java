/**
 * Copyright 2015 the original author or authors
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;

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
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitArmorAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.ArmorOption;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability.option.JPAArmorOption;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.JPAUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "UnitArmorAvailability")
@Table(name = "unit_armors")
public final class JPAUnitArmorAvailability implements UnitArmorAvailability,
        PersistenceEntity {

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_armor_options", joinColumns = { @JoinColumn(
            name = "unit_armor_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id",
                    referencedColumnName = "id") })
    private Collection<JPAArmorOption> armorOptions = new LinkedHashSet<JPAArmorOption>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                    id           = -1;
    @OneToOne
    @JoinColumn(name = "initial_armor", referencedColumnName = "id")
    private JPAArmorOption             initialArmor;
    @OneToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private JPAUnitTemplate            unit;

    public JPAUnitArmorAvailability() {
        super();
    }

    public final void addArmorOption(final JPAArmorOption option) {
        getArmorOptionsModifiable().add(option);
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

        JPAUnitArmorAvailability other;

        other = (JPAUnitArmorAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Collection<ArmorOption> getArmorOptions() {
        return Collections.unmodifiableCollection(getArmorOptionsModifiable());
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final ArmorOption getInitialArmor() {
        return initialArmor;
    }

    @Override
    public final UnitTemplate getUnit() {
        return unit;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    public final void removeArmorOption(final JPAArmorOption option) {
        getArmorOptionsModifiable().remove(option);
    }

    public final void setArmorOptions(final Collection<JPAArmorOption> options) {
        getArmorOptionsModifiable().clear();
        getArmorOptionsModifiable().addAll(options);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setInitialArmor(final JPAArmorOption armor) {
        initialArmor = armor;
    }

    public final void setUnit(final JPAUnitTemplate unit) {
        this.unit = unit;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("unit", unit.getNameToken()).add("initial", initialArmor)
                .add("armors", armorOptions).toString();
    }

    private final Collection<JPAArmorOption> getArmorOptionsModifiable() {
        return armorOptions;
    }

}
