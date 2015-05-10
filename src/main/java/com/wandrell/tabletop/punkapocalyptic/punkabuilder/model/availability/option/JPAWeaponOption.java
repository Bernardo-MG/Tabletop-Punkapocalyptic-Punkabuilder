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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.availability.option;

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
import com.wandrell.tabletop.punkapocalyptic.model.availability.option.WeaponOption;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory.AbstractJPAWeapon;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory.JPAWeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "WeaponOption")
@Table(name = "weapon_options")
public final class JPAWeaponOption implements WeaponOption, PersistenceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer                                id          = -1;
    @OneToOne
    @JoinColumn(name = "weapon", referencedColumnName = "id")
    private AbstractJPAWeapon                      weapon;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "weapon_options_enhancements",
            joinColumns = { @JoinColumn(name = "weapon_option_id",
                    referencedColumnName = "id") },
            inverseJoinColumns = { @JoinColumn(name = "enhancement_id",
                    referencedColumnName = "id") })
    private final Collection<JPAWeaponEnhancement> weaponEnhcn = new LinkedHashSet<JPAWeaponEnhancement>();

    public JPAWeaponOption() {
        super();
    }

    public final void addEnhancement(final JPAWeaponEnhancement enhancement) {
        getEnhancementsModifiable().add(enhancement);
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

        JPAWeaponOption other;

        other = (JPAWeaponOption) obj;
        return Objects.equal(weapon, other.weapon);
    }

    @Override
    public final Collection<WeaponEnhancement> getEnhancements() {
        return Collections.unmodifiableCollection(getEnhancementsModifiable());
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final Weapon getWeapon() {
        return weapon;
    }

    @Override
    public final int hashCode() {
        return Objects.hashCode(weapon);
    }

    public final void removeEnhancement(final JPAWeaponEnhancement enhancement) {
        getEnhancementsModifiable().remove(enhancement);
    }

    public final void setEnhancements(
            final Collection<JPAWeaponEnhancement> enhancements) {
        getEnhancementsModifiable().clear();
        getEnhancementsModifiable().addAll(enhancements);
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setWeapon(final AbstractJPAWeapon weapon) {
        this.weapon = weapon;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("weapon", weapon)
                .add("enhancements", weaponEnhcn).toString();
    }

    private final Collection<JPAWeaponEnhancement> getEnhancementsModifiable() {
        return weaponEnhcn;
    }

}
