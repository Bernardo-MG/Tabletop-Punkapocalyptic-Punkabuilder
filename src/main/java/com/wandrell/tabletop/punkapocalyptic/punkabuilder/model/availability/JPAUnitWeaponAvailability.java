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
import javax.persistence.Column;
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
import com.wandrell.tabletop.punkapocalyptic.model.availability.UnitWeaponAvailability;
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.unit.UnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability.option.JPAWeaponOption;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.unit.JPAUnitTemplate;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "UnitWeaponAvailability")
@Table(name = "unit_weapons")
public final class JPAUnitWeaponAvailability implements UnitWeaponAvailability,
        PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                           id            = -1;
    @Column(name = "max")
    private Integer                           maxWeapons;
    @Column(name = "min")
    private Integer                           minWeapons;
    @OneToOne
    @JoinColumn(name = "unit", referencedColumnName = "id")
    private JPAUnitTemplate                   unit;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "unit_weapons_options", joinColumns = { @JoinColumn(
            name = "unit_weapon_id", referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "option_id",
                    referencedColumnName = "id") })
    private final Collection<JPAWeaponOption> weaponOptions = new LinkedHashSet<JPAWeaponOption>();

    public JPAUnitWeaponAvailability() {
        super();
    }

    public final void addWeaponOption(final JPAWeaponOption option) {
        getWeaponOptionsModifiable().add(option);
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

        JPAUnitWeaponAvailability other;

        other = (JPAUnitWeaponAvailability) obj;
        return Objects.equal(unit, other.unit);
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Integer getMaxWeapons() {
        return maxWeapons;
    }

    @Override
    public final Integer getMinWeapons() {
        return minWeapons;
    }

    @Override
    public final UnitTemplate getUnit() {
        return unit;
    }

    @Override
    public final Collection<WeaponOption> getWeaponOptions() {
        return Collections.unmodifiableCollection(getWeaponOptionsModifiable());
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(unit);
    }

    public final void removeWeaponOption(final JPAWeaponOption option) {
        getWeaponOptionsModifiable().remove(option);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setMaxWeapons(final Integer max) {
        maxWeapons = max;
    }

    public final void setMinWeapons(final Integer min) {
        minWeapons = min;
    }

    public final void
            setWeaponOptions(final Collection<JPAWeaponOption> options) {
        getWeaponOptionsModifiable().clear();
        getWeaponOptionsModifiable().addAll(options);
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this)
                .add("unit", unit.getNameToken()).add("min", minWeapons)
                .add("max", maxWeapons).add("weapons", weaponOptions)
                .toString();
    }

    private final Collection<JPAWeaponOption> getWeaponOptionsModifiable() {
        return weaponOptions;
    }

}
