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
package com.wandrell.tabletop.punkapocalyptic.punkabuilder.model.inventory;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.RangedWeapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.Weapon;
import com.wandrell.tabletop.punkapocalyptic.model.inventory.WeaponEnhancement;
import com.wandrell.tabletop.punkapocalyptic.punkabuilder.repository.jpa.PersistenceEntity;

@Entity(name = "WeaponEnhancement")
@Table(name = "weapon_enhancement")
public final class JPAWeaponEnhancement implements WeaponEnhancement,
        PersistenceEntity {

    @Column(name = "cost")
    private Integer cost;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id = -1;
    @Column(name = "name")
    private String  name;
    @Column(name = "firearm")
    private Boolean requiresFirearm;

    public JPAWeaponEnhancement() {
        super();
    }

    public JPAWeaponEnhancement(final JPAWeaponEnhancement enhancement) {
        super();

        checkNotNull(enhancement,
                "Received a null pointer as weapon enhancement");

        name = enhancement.name;
        cost = enhancement.cost;
    }

    @Override
    public final Integer getCost() {
        return cost;
    }

    @Override
    public final Integer getId() {
        return id;
    }

    @Override
    public final String getName() {
        return name;
    }

    public final Boolean getRequiresFirearm() {
        return requiresFirearm;
    }

    @Override
    public final Boolean isValid(final Weapon weapon) {
        final Boolean valid;

        checkNotNull(weapon, "Received a null pointer as weapon");

        if ((getRequiresFirearm()) && (weapon instanceof RangedWeapon)
                && (((RangedWeapon) weapon).isFirearm())) {
            valid = true;
        } else {
            valid = false;
        }

        return valid;
    }

    public final void setCost(final Integer cost) {
        this.cost = cost;
    }

    @Override
    public final void setId(final Integer id) {
        this.id = id;
    }

    public final void setName(final String name) {
        this.name = name;
    }

    public final void setRequiresFirearm(final Boolean firearm) {
        requiresFirearm = firearm;
    }

    @Override
    public final String toString() {
        return MoreObjects.toStringHelper(this).add("name", name)
                .add("cost", cost).toString();
    }

}
